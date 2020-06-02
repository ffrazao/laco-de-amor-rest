package com.frazao.lacodeamorrest.bo.laco_de_amor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.bo.BOException;
import com.frazao.lacodeamorrest.bo.CRUDBO;
import com.frazao.lacodeamorrest.bo.RecursoNaoEncontradoBoException;
import com.frazao.lacodeamorrest.dao.laco_de_amor.UsuarioDAO;
import com.frazao.lacodeamorrest.modelo.dominio.Confirmacao;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.AutorizarTrocarSenhaDTO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.RecuperarSenhaDTO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.TrocarSenhaDTO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.UsuarioFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Usuario;
import com.frazao.lacodeamorrest.util.email.EmailService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UsuarioBO extends CRUDBO<Usuario, Integer, UsuarioFiltroDTO, UsuarioDAO> {

	@Autowired
	private EmailService emailService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public UsuarioBO(@Autowired final UsuarioDAO dao) {
		super(Usuario.class, dao);
	}

	public void autorizarTrocarSenha(@Valid final AutorizarTrocarSenhaDTO valor) throws Exception {
		UsuarioBO.log.debug("Autorizando troca de senha para [{}]", valor);

		final Usuario usuario = this.getDAO().findByEmail(valor.getEmail());
		if (usuario == null) {
			throw new RecursoNaoEncontradoBoException("Email não cadastrado");
		}

		if (usuario.getRecuperarSenhaToken() == null || usuario.getRecuperarSenhaExpira() == null) {
			throw new BOException("Troca de senha não solicitada!");
		}

		// verificar se o token é válido
		if (!usuario.getRecuperarSenhaToken().equals(valor.getToken())) {
			throw new BOException("Token inválido!");
		}

		final Calendar hojeCl = Calendar.getInstance();
		final Calendar expiraCl = Calendar.getInstance();

		hojeCl.setTime(new Date());
		expiraCl.setTimeInMillis(usuario.getRecuperarSenhaExpira());

		if (hojeCl.after(expiraCl)) {
			throw new BOException("Token expirado!");

		}

		UsuarioBO.log.debug("Troca de senha para [{}] autorizada", valor);
	}

	public boolean emailDisponivel(final String valor, final Integer id) {
		final Usuario result = this.getDAO().findByEmail(valor);
		return result == null || result != null && result.getId().equals(id);
	}

	@Override
	public Usuario entrando(@Valid final Usuario t, @Valid final Usuario anterior, final String acao)
			throws BOException {
		final List<String> msgErro = new ArrayList<>();
		t.getPessoa().ifPresent((p) -> {
			if (!this.pessoaDisponivel(p.getId(), t.getId())) {
				msgErro.add(" Pessoa já vinculada a outro cadastro");
			}
		});
		if (!this.loginDisponivel(t.getLogin(), t.getId())) {
			msgErro.add(" Login já vinculado a outro cadastro");
		}
		Optional.ofNullable(t.getEmail()).ifPresent((p) -> {
			if (!this.emailDisponivel(p, t.getId())) {
				msgErro.add(" E-mail já vinculado a outro cadastro");
			}
		});

		if (msgErro.size() > 0) {
			throw new BOException(msgErro.stream().collect(Collectors.joining(",")));
		}

		return super.entrando(t, anterior, acao);
	}

	public Usuario findByLogin(final String valor) {
		return this.getDAO().findByLogin(valor);
	}

	public boolean loginDisponivel(@Valid @Pattern(regexp = "^[a-z0-9_.]{1,16}$") final String valor,
			final Integer id) {
		final Usuario result = this.getDAO().findByLogin(valor);
		return result == null || result != null && result.getId().equals(id);
	}

	@Override
	public Usuario novo(@Valid final Usuario modelo) throws BOException {
		final Usuario result = super.novo(modelo);
		result.setAtivo(Confirmacao.S);
		result.setPerfil("");
		return result;
	}

	public boolean pessoaDisponivel(final Integer valor, final Integer id) {
		final Usuario result = this.getDAO().findByPessoaId(valor);
		return result == null || result != null && result.getId().equals(id);
	}

	@Transactional
	public void recuperarSenha(@Valid final RecuperarSenhaDTO valor, final String enderecoOrigem) throws Exception {

		UsuarioBO.log.debug("Início recuperação de senha para [{}]", valor);

		final Usuario usuario = this.getDAO().findByEmail(valor.getEmail());
		if (usuario == null) {
			throw new RecursoNaoEncontradoBoException("Email não cadastrado");
		}
		String token = usuario.getRecuperarSenhaToken();
		Long expira = usuario.getRecuperarSenhaExpira();

		final Calendar hojeCl = Calendar.getInstance();
		final Calendar expiraCl = Calendar.getInstance();

		hojeCl.setTime(new Date());

		if (expira != null && expira > 0) {
			expiraCl.setTimeInMillis(expira);
			if (hojeCl.after(expiraCl)) {
				UsuarioBO.log.debug("Token para [{}] expirado, gerando novo token", valor);
				token = null;
				expira = null;
			}
		}

		// token de recuperação de senha
		if (token == null) {
			token = String.format("%06d", new Random().nextInt(999999));
			hojeCl.add(Calendar.MINUTE, 20);
			expira = hojeCl.getTimeInMillis();
			UsuarioBO.log.debug("Token para [{}] gerado. Novo token => [{}] - expira em [{}]", valor, token,
					new SimpleDateFormat("dd/MM/yyyy hh:mm:ss:SSS").format(hojeCl.getTime()));
		}

		// tempo de expiração 20 minutos
		usuario.setRecuperarSenhaToken(token);
		usuario.setRecuperarSenhaExpira(expira);

		this.getDAO().save(usuario);

		this.emailService.sendRecuperarToken(new AutorizarTrocarSenhaDTO(valor, token, enderecoOrigem));
	}

	@Transactional
	public void reiniciarSenha(Usuario valor, final String enderecoOrigem) throws Exception {
		valor = this.getDAO().getOne(valor.getId());
		this.recuperarSenha(new RecuperarSenhaDTO(valor.getEmail()), enderecoOrigem);
	}

	@Transactional
	public void trocarSenha(@Valid final TrocarSenhaDTO valor) throws Exception {
		UsuarioBO.log.debug("Trocando a senha para [{}]", valor.getEmail());
		this.autorizarTrocarSenha(valor);

		UsuarioBO.log.debug("Trocando a senha!");

		final Usuario usuario = this.getDAO().findByEmailAndRecuperarSenhaToken(valor.getEmail(), valor.getToken());

		this.getDAO().updateSenhaAndRecuperarSenhaTokenAndRecuperarSenhaExpiraById(usuario.getId(),
				this.passwordEncoder.encode(valor.getSenha()));

		UsuarioBO.log.debug("Senha para [{}] trocada!", valor.getEmail());
	}

}

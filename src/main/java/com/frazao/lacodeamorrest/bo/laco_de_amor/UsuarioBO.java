package com.frazao.lacodeamorrest.bo.laco_de_amor;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.bo.BoException;
import com.frazao.lacodeamorrest.bo.CRUDBO;
import com.frazao.lacodeamorrest.bo.RecursoNaoEncontradoBoException;
import com.frazao.lacodeamorrest.dao.laco_de_amor.UsuarioDAO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.AutorizarTrocarSenhaDTO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.RecuperarSenhaDTO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.TrocarSenhaDTO;
import com.frazao.lacodeamorrest.modelo.dto.laco_de_amor.UsuarioFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.laco_de_amor.Usuario;
import com.frazao.lacodeamorrest.util.email.EmailService;

@Service
public class UsuarioBO extends CRUDBO<Usuario, Integer, UsuarioFiltroDTO> {

	private static final Logger LOG = LoggerFactory.getLogger(UsuarioBO.class);

	@Autowired
	private EmailService emailService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public UsuarioBO(@Autowired final UsuarioDAO dao) {
		super(Usuario.class, dao);
	}

	public void autorizarTrocarSenha(@Valid final AutorizarTrocarSenhaDTO valor) throws Exception {
		UsuarioBO.LOG.debug("Autorizando troca de senha para [%s]", valor);

		final Usuario usuario = this.getDAO().findByEmail(valor.getEmail());
		if (usuario == null) {
			throw new RecursoNaoEncontradoBoException("Email não cadastrado");
		}

		if (usuario.getRecuperarSenhaToken() == null || usuario.getRecuperarSenhaExpira() == null) {
			throw new BoException("Troca de senha não solicitada!");
		}

		// verificar se o token é válido
		if (!usuario.getRecuperarSenhaToken().equals(valor.getToken())) {
			throw new BoException("Token inválido!");
		}

		final Calendar hojeCl = Calendar.getInstance();
		final Calendar expiraCl = Calendar.getInstance();

		hojeCl.setTime(new Date());
		expiraCl.setTimeInMillis(usuario.getRecuperarSenhaExpira());

		if (hojeCl.after(expiraCl)) {
			throw new BoException("Token expirado!");

		}

		UsuarioBO.LOG.debug("Troca de senha para [%s] autorizada", valor);
	}

	public Usuario findByLogin(final String valor) {
		return this.getDAO().findByLogin(valor);
	}

	@Override
	public UsuarioDAO getDAO() {
		return (UsuarioDAO) super.getDAO();
	}

	@Transactional
	public void recuperarSenha(@Valid final RecuperarSenhaDTO valor) throws Exception {

		UsuarioBO.LOG.debug("Início recuperação de senha para [%s]", valor);

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
				UsuarioBO.LOG.debug("Token para [%s] expirado, gerando novo token", valor);
				token = null;
				expira = null;
			}
		}

		// token de recuperação de senha
		if (token == null) {
			token = String.format("%06d", new Random().nextInt(999999));
			hojeCl.add(Calendar.MINUTE, 20);
			expira = hojeCl.getTimeInMillis();
			UsuarioBO.LOG.debug("Token para [%s] gerado. Novo token => [%s] - expira em [%s]", valor, token,
					new SimpleDateFormat("dd/MM/yyyy hh:mm:ss:SSS").format(hojeCl.getTime()));
		}

		// tempo de expiração 20 minutos
		usuario.setRecuperarSenhaToken(token);
		usuario.setRecuperarSenhaExpira(expira);

		this.getDAO().save(usuario);

		this.emailService.sendRecuperarToken(new AutorizarTrocarSenhaDTO(valor, token));
	}

	@Transactional
	public void trocarSenha(@Valid final TrocarSenhaDTO valor) throws Exception {
		UsuarioBO.LOG.debug("Trocando a senha para [%s]", valor.getEmail());
		this.autorizarTrocarSenha(valor);

		UsuarioBO.LOG.debug("Trocando a senha!");

		final Usuario usuario = this.getDAO().findByEmailAndRecuperarSenhaToken(valor.getEmail(), valor.getToken());

		usuario.setSenha(this.passwordEncoder.encode(valor.getSenha()));
		usuario.setRecuperarSenhaToken(null);
		usuario.setRecuperarSenhaExpira(null);

		this.getDAO().save(usuario);
		UsuarioBO.LOG.debug("Senha para [%s] trocada!", valor.getEmail());

	}

}

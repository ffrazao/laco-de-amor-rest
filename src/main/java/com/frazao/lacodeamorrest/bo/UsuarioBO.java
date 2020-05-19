package com.frazao.lacodeamorrest.bo;

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

import com.frazao.lacodeamorrest.dao.UsuarioDAO;
import com.frazao.lacodeamorrest.modelo.dto.AutorizarTrocarSenhaDTO;
import com.frazao.lacodeamorrest.modelo.dto.RecuperarSenhaDTO;
import com.frazao.lacodeamorrest.modelo.dto.TrocarSenhaDTO;
import com.frazao.lacodeamorrest.modelo.dto.UsuarioFiltroDTO;
import com.frazao.lacodeamorrest.modelo.entidade.Usuario;
import com.frazao.lacodeamorrest.util.email.EmailService;

@Service
public class UsuarioBO extends CRUDBO<Usuario, Integer, UsuarioFiltroDTO> {

	private static final Logger LOG = LoggerFactory.getLogger(UsuarioBO.class);

	@Autowired
	private EmailService emailService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public UsuarioBO(@Autowired UsuarioDAO dao) {
		super(dao);
	}

	public void autorizarTrocarSenha(@Valid AutorizarTrocarSenhaDTO valor) throws Exception {
		LOG.debug("Autorizando troca de senha para [%s]", valor);

		Usuario usuario = this.getDAO().findByEmail(valor.getEmail());
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

		Calendar hojeCl = Calendar.getInstance();
		Calendar expiraCl = Calendar.getInstance();

		hojeCl.setTime(new Date());
		expiraCl.setTimeInMillis(usuario.getRecuperarSenhaExpira());

		if (hojeCl.after(expiraCl)) {
			throw new BoException("Token expirado!");

		}

		LOG.debug("Troca de senha para [%s] autorizada", valor);
	}

	public Usuario findByLogin(String valor) {
		return this.getDAO().findByLogin(valor);
	}

	public UsuarioDAO getDAO() {
		return (UsuarioDAO) super.getDAO();
	}

	@Transactional
	public void recuperarSenha(@Valid RecuperarSenhaDTO valor) throws Exception {

		LOG.debug("Início recuperação de senha para [%s]", valor);

		Usuario usuario = this.getDAO().findByEmail(valor.getEmail());
		if (usuario == null) {
			throw new RecursoNaoEncontradoBoException("Email não cadastrado");
		}
		String token = usuario.getRecuperarSenhaToken();
		Long expira = usuario.getRecuperarSenhaExpira();

		Calendar hojeCl = Calendar.getInstance();
		Calendar expiraCl = Calendar.getInstance();

		hojeCl.setTime(new Date());

		if (expira != null && expira > 0) {
			expiraCl.setTimeInMillis(expira);
			if (hojeCl.after(expiraCl)) {
				LOG.debug("Token para [%s] expirado, gerando novo token", valor);
				token = null;
				expira = null;
			}
		}

		// token de recuperação de senha
		if (token == null) {
			token = String.format("%06d", new Random().nextInt(999999));
			hojeCl.add(Calendar.MINUTE, 20);
			expira = hojeCl.getTimeInMillis();
			LOG.debug("Token para [%s] gerado. Novo token => [%s] - expira em [%s]", valor, token,
					new SimpleDateFormat("dd/MM/yyyy hh:mm:ss:SSS").format(hojeCl.getTime()));
		}

		// tempo de expiração 20 minutos
		usuario.setRecuperarSenhaToken(token);
		usuario.setRecuperarSenhaExpira(expira);

		this.getDAO().save(usuario);

		this.emailService.sendRecuperarToken(new AutorizarTrocarSenhaDTO(valor, token));
	}

	@Transactional
	public void trocarSenha(@Valid TrocarSenhaDTO valor) throws Exception {
		LOG.debug("Trocando a senha para [%s]", valor.getEmail());
		this.autorizarTrocarSenha((AutorizarTrocarSenhaDTO) valor);

		LOG.debug("Trocando a senha!");

		Usuario usuario = this.getDAO().findByEmailAndRecuperarSenhaToken(valor.getEmail(), valor.getToken());

		usuario.setSenha(passwordEncoder.encode(valor.getSenha()));
		usuario.setRecuperarSenhaToken(null);
		usuario.setRecuperarSenhaExpira(null);

		this.getDAO().save(usuario);
		LOG.debug("Senha para [%s] trocada!", valor.getEmail());

	}

}

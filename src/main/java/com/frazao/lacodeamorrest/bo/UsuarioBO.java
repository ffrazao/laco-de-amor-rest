package com.frazao.lacodeamorrest.bo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.frazao.lacodeamorrest.dao.UsuarioDAO;
import com.frazao.lacodeamorrest.modelo.dto.AutorizarTrocarSenhaDTO;
import com.frazao.lacodeamorrest.modelo.dto.RecuperarSenhaDTO;
import com.frazao.lacodeamorrest.modelo.dto.TrocarSenhaDTO;
import com.frazao.lacodeamorrest.modelo.entidade.Usuario;

@Service
public class UsuarioBO extends CRUDBO<Usuario, Integer> {

	@Autowired
	private PasswordEncoder passwordEncoder;

	public UsuarioBO(@Autowired UsuarioDAO dao) {
		super(dao);
	}

	public UsuarioDAO getDAO() {
		return (UsuarioDAO) super.getDAO();
	}

	public Usuario findByLogin(String valor) {
		return this.getDAO().findByLogin(valor);
	}

	public void recuperarSenha(@Valid RecuperarSenhaDTO valor) throws Exception {
		Usuario usuario = this.getDAO().findByEmail(valor.getEmail());
		if (usuario == null) {
			throw new RecursoNaoEncontradoBoException("Email não cadastrado");
		}

		boolean tokenGerado = false;
		Calendar hojeCl = Calendar.getInstance();
		Calendar expiraCl = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss:SSS");

		hojeCl.setTime(new Date());

		String token = usuario.getRecuperarSenhaToken();
		Long expira = usuario.getRecuperarSenhaExpira();

		if (expira != null && expira > 0) {
			expiraCl.setTimeInMillis(expira);

			System.out.println(sdf.format(hojeCl.getTime()));
			System.out.println(sdf.format(expiraCl.getTime()));

			if (hojeCl.after(expiraCl)) {
				token = null;
				expira = null;
			}
		}

		// token de recuperação de senha
		if (token == null) {
			token = String.format("%06d", new Random().nextInt(999999));

			hojeCl.add(Calendar.MINUTE, 20);
			expira = hojeCl.getTimeInMillis();
			tokenGerado = true;
		}

		// tempo de expiração 20 minutos
		usuario.setRecuperarSenhaToken(token);
		usuario.setRecuperarSenhaExpira(expira);

		this.getDAO().save(usuario);

		System.out.format("Enviando email para [%s] token(%s) (%s) expira(%s)\n", usuario.getEmail(), token,
				tokenGerado ? "Novo" : "Recuperado", sdf.format(expira));
	}

	public void autorizarTrocarSenha(@Valid AutorizarTrocarSenhaDTO valor) throws Exception {
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

		System.out.format("Troca de senha autorizada!");
	}

	public void trocarSenha(@Valid TrocarSenhaDTO valor) throws Exception {
		this.autorizarTrocarSenha((AutorizarTrocarSenhaDTO) valor);

		System.out.format("Trocando a senha!");

		Usuario usuario = this.getDAO().findByEmailAndRecuperarSenhaToken(valor.getEmail(), valor.getToken());

		usuario.setSenha(passwordEncoder.encode(valor.getSenha()));
		usuario.setRecuperarSenhaToken(null);
		usuario.setRecuperarSenhaExpira(null);

		this.getDAO().save(usuario);

	}

}

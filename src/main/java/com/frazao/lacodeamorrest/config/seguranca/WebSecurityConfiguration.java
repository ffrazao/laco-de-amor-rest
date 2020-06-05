package com.frazao.lacodeamorrest.config.seguranca;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Created by Frazão
 */
@EnableWebSecurity
@Configuration
// @EnableGlobalMethodSecurity(prePostEnabled = true)
// @Order(2)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userDetailsServiceBean()).passwordEncoder(this.passwordEncoder());
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.anonymous().and().authorizeRequests()
				.antMatchers(HttpMethod.POST, "/usuario/recuperar-senha", "/usuario/autorizar-trocar-senha", "/usuario/trocar-senha").permitAll()
				.antMatchers(HttpMethod.GET, "/produto-modelo/imagem-venda").permitAll()
				.antMatchers(HttpMethod.OPTIONS).permitAll().antMatchers("/**").authenticated().and()
				.formLogin().loginProcessingUrl("/login.do").usernameParameter("username").passwordParameter("password")
				.loginPage("/login").and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout.do")).and()
				.userDetailsService(this.userDetailsServiceBean());
	}

	@Override
	public void configure(final WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/webjars/**", "/resources/**");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(4);
	}

	@Bean
	@Override
	public UserDetailsService userDetailsServiceBean() throws Exception {
		return new JdbcUserDetails();
	}

}

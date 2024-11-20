package com.dev.ProtoFin;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
@Order(2)
public class SecurityAdmin extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.jdbcAuthentication().dataSource(dataSource)
//				.usersByUsernameQuery(
//						"select email as username, senha as password, 1 as enable from funcionario where email=?")
//				.authoritiesByUsernameQuery(
//						"select funcionario.email as username, papel.nome as authority from permissao inner join funcionario on funcionario.id=permissao.funcionario_id inner join papel on permissao.papel_id=papel.id where funcionario.email=?")
//				.passwordEncoder(new BCryptPasswordEncoder());
//	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery(
						"select email as username, senha as password, 1 as enable from cliente where email=?")
				.authoritiesByUsernameQuery(
						"select cliente.email as username, 'cliente' as authority from cliente where cliente.email=?")
				.passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
//		http.csrf().disable()
//		.authorizeRequests()
//		.antMatchers("/finalizar/**").hasAuthority("cliente")
//		.and()
//		.formLogin()	
//		.loginPage("/clientes/cadastrar").permitAll()
//		.failureUrl("/clientes/cadastrar")
//		.loginProcessingUrl("/finalizar/login")
//		.defaultSuccessUrl("/finalizar")
//			.usernameParameter("username")
//			.passwordParameter("password")
//		.and()
//		.logout()
//		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//		.logoutSuccessUrl("/").deleteCookies("JSESSIONID").permitAll()
//		.and()
//		.exceptionHandling().accessDeniedPage("/negado");
		
//		http.csrf().disable()
//		.authorizeRequests()
//		.antMatchers("/admin/cadastrar/**").hasAnyAuthority("admin")
//		.antMatchers("/finalizar/**").hasAnyAuthority("cliente")
//		.antMatchers("/admin/**").hasAnyAuthority("admin", "professor")
//		.and()
//		.formLogin()
//		.loginPage("/login")
//		.usernameParameter("username")
//		.passwordParameter("password").permitAll()
//		.failureUrl("/login")
//		.loginProcessingUrl("/admin/login")
//		.defaultSuccessUrl("/")
//		.and()
//		.logout()
//		.logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout"))
//		.logoutSuccessUrl("/").deleteCookies("JSESSIONID")
//		.and()
//		.exceptionHandling().accessDeniedPage("/admin");
	}
}

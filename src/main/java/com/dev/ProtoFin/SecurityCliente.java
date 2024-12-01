package com.dev.ProtoFin;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
@Order(1)
public class SecurityCliente extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery(
						"select email as username, senha as password, 1 as enable from funcionario where email=?")
				.authoritiesByUsernameQuery(
						"select funcionario.email as username, papel.nome as authority from permissao inner join funcionario on funcionario.id=permissao.funcionario_id inner join papel on permissao.papel_id=papel.id where funcionario.email=?")
				.passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/login").permitAll().antMatchers("/admin/cadastrar/**")
				.hasAnyAuthority("admin").antMatchers("/finalizar/**").hasAnyAuthority("cliente")
				.antMatchers("/mensagemFinalizou/**").hasAnyAuthority("cliente")
				.antMatchers("/cliente/funcionarios/cadastrar/**").hasAnyAuthority("cliente")
				.antMatchers("/perfil/cadastro**").hasAnyAuthority("cliente").antMatchers("/admin/**")
				.hasAnyAuthority("admin", "professor").antMatchers("/admin/homeProf**")
				.hasAnyAuthority("admin", "professor").antMatchers("/prof**").hasAnyAuthority("admin", "professor")
				.and().formLogin().loginPage("/login").failureUrl("/login").loginProcessingUrl("/admin")
				.defaultSuccessUrl("/").usernameParameter("username").passwordParameter("password").and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout")).logoutSuccessUrl("/")
				.deleteCookies("JSESSIONID").and().exceptionHandling().accessDeniedPage("/").and().csrf().disable();

	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}

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

@Configuration
@EnableWebSecurity
@Order(2)
public class SecurityAdmin extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//		auth.inMemoryAuthentication().withUser("user").password(new BCryptPasswordEncoder().encode("123")).roles("USER")
//				.and().withUser("admin").password(new BCryptPasswordEncoder().encode("admin")).roles("USER", "ADMIN");

		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery(
						"select email as username, senha as password, 1 as enable from funcionario where email=?")
				.authoritiesByUsernameQuery(
						"select funcionario.email as username, papel.nome as authority from permissao inner join funcionario on funcionario.id=permissao.funcionario_id inner join papel on permissao.papel_id=papel.id where funcionario.email=?")
				.passwordEncoder(new BCryptPasswordEncoder());

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests().antMatchers("/admin/entrada/**").hasAuthority("gerente")
				.antMatchers("/admin/**").hasAnyAuthority("gerente", "vendedor", "professor").and().formLogin().loginPage("/login")
				.permitAll().and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/").deleteCookies("JSESSIONID").and().exceptionHandling().accessDeniedPage("/negado");

//		http.authorizeRequests().antMatchers("/login").permitAll().antMatchers("/administrativo/cadastrar/**")
//				.hasAnyAuthority("gerente").antMatchers("/administrativo/**").authenticated().and().formLogin()
//				.loginPage("/login").failureUrl("/login").loginProcessingUrl("/admin")
//				.defaultSuccessUrl("/administrativo").usernameParameter("username").passwordParameter("password").and()
//				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/administrativo/logout"))
//				.logoutSuccessUrl("/login").deleteCookies("JSESSIONID").and().exceptionHandling()
//				.accessDeniedPage("/negado").and().csrf().disable();

//		http.csrf().disable().authorizeRequests().antMatchers("/login").permitAll().antMatchers("/admin/cadastrar/**")
//				.hasAnyAuthority("gerente").antMatchers("/admin/**").authenticated().and().formLogin()
//				.loginPage("/login").failureUrl("/").loginProcessingUrl("/admin").defaultSuccessUrl("/admin")
//				.usernameParameter("username").passwordParameter("password").and().logout()
//				.logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout")).logoutSuccessUrl("/").and()
//				.exceptionHandling().accessDeniedPage("/negado");

	}
}

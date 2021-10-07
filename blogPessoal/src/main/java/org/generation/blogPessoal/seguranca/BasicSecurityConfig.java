package org.generation.blogPessoal.seguranca;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
//habilitar web security
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired//injecao de dependencia, um objeto da classe 
	private UserDetailsService userDetailsService;
	
	@Override
	//responsavel por fazer login
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{ 
		auth.userDetailsService(userDetailsService);
	}
	
	@Bean
	//metodo pode ser aplicado em qlqr classe 
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override//sobrecarga de metodo
	protected void configure (HttpSecurity http) throws Exception{
		http.authorizeRequests()
		.antMatchers("/usuarios/logar").permitAll()
		.antMatchers("/usuarios/cadastrar").permitAll()
		.anyRequest().authenticated()
		.and().httpBasic()//tipo de autenticacao
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().cors()// eh o CrossOrigin
		.and().csrf().disable();//desabilitar 
	}
}

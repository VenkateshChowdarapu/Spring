package com.dbs.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.dbs.web.service.AccountUserDetailsService;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	//	@Override
	//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	//		auth.inMemoryAuthentication()
	//		.withUser("abc").password(encoder().encode("abc")).roles("USER")
	//		.and()
	//		.withUser("admin").password(encoder().encode("admin")).roles("ADMIN");
	//	}
	

	@Autowired
	private AccountUserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
	private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/","/products/**","/register"
            // other public endpoints of your API may be appended to this array
    };
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		/**
		 * disable the csrf and authorize any other request using form
		 * based authentication
		 */
		
//		http.csrf().disable()
//		.authorizeRequests().anyRequest().authenticated()
//		.and().formLogin().and().logout();
		
		/**
		 * disable the csrf and permit the urls passed without authentication 
		 * and 
		 * authorize any other request using form
		 * based authentication
		 */
//		http.csrf().disable()
//		.authorizeRequests()//whitelist
//		.antMatchers(HttpMethod.GET,"/","/products/**","/register").permitAll()
//		.anyRequest().authenticated().and()
//		.formLogin().and().logout();
		
		http.csrf().disable()
		.authorizeRequests()//whitelist
		.antMatchers(HttpMethod.GET,AUTH_WHITELIST).permitAll()
		.and()
		.authorizeRequests()
		.antMatchers("/invoices/**", "/sigin","/dashboard","/invoice")
		.hasAnyRole("USER","ADMIN")
		.antMatchers(HttpMethod.POST,"/products").hasAnyRole("ADMIN")
		.antMatchers("/user").hasAnyRole("ADMIN")
		.anyRequest().authenticated().and()
		.formLogin().and().logout();
		
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/h2-console/**");
	}
}

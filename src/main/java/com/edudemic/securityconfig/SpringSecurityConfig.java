package com.edudemic.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private JpaUserDetailsService userDetailsService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() 
	{
		return new BCryptPasswordEncoder();
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers("/","/auth/**","/landing/**", "/public/**","/css/**", "/js/**", "/img/**").permitAll().anyRequest().authenticated().
		and().formLogin().loginPage("/auth/login").defaultSuccessUrl("/private/index",true).failureUrl("/auth/login?error=true").loginProcessingUrl("/auth/login-post").permitAll()
		.and().logout().logoutUrl("/logout").logoutSuccessUrl("/public/index");

	}


}
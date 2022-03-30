package com.gilsonbraggion.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gilsonbraggion.service.MyCustomDetailService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private MyCustomDetailService customDetailService;
	
	@Autowired
	private MyAccessDeniedHandler accessDeniedHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
        .authorizeRequests()
          .antMatchers("/", "/home","/h2-console/**", "/carregarUsuarios/**", "/resources/**", "/static/**","/webjars/**").permitAll()
          .antMatchers("/admin/**").hasAnyAuthority("ADMIN")
          .antMatchers("/user/**").hasAnyAuthority("USER", "ADMIN")
          .antMatchers("/general/**").hasAnyAuthority("GENERAL", "ADMIN")
          .anyRequest().authenticated()
          .and()
          .headers().frameOptions().disable()
          .and()
          .csrf().disable()
       .formLogin()
         .loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/home", true)
         .permitAll()
         .and()
      .logout()
        .permitAll()
        .and()
      .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
		
//		// Allow access to those URL whithout authentication
//		http.authorizeHttpRequests().antMatchers("/h2-console/**").permitAll()
//		// Allow to open frame options (Otherwise h2-console doesnt render correct)
//		.and()
//		.headers().frameOptions().disable()
//		.and()
//		.csrf().disable().authorizeHttpRequests().antMatchers("/", "/home").permitAll()
//		.anyRequest().authenticated()
//		.and()
//		.formLogin().loginPage("/login").permitAll().loginProcessingUrl("/login").defaultSuccessUrl("/principal")
//		.and()
//		.logout().permitAll().logoutUrl("/logout");
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customDetailService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
}

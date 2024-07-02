package com.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	/*@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.csrf().disable().authorizeHttpRequests()
			.requestMatchers("/", "/register", "/login")
			.permitAll()
	
			.requestMatchers(HttpMethod.POST, "/home").authenticated()
	
			.anyRequest().authenticated()
			
			.and().formLogin().loginPage("/login").defaultSuccessUrl("/home", true) .permitAll().and().logout().logoutSuccessUrl("/").invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
            .permitAll(). and().exceptionHandling()
			
	
			.accessDeniedPage("/access-denied.html");

		return http.build();
		
	}*/
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests()
                .anyRequest().permitAll() // Permette tutte le richieste senza autenticazione
            .and()
            .formLogin().disable() // Disabilita il form di login
            .logout().disable(); // Disabilita il logout

        return http.build();
    }
	
	@Bean
	DatabaseUserDetailsService userDetailsService() {
		return new DatabaseUserDetailsService();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		//System.out.println(passwordEncoder().encode("ciccio"));

		return authProvider;
	}
	
}
	


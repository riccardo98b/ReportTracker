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
	
	
    // Definisce la catena di filtri di sicurezza
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	// Disabilita la protezione CSRF 
        http.csrf().disable()
        
            .authorizeHttpRequests()
                .anyRequest().permitAll() // Permette tutte le richieste senza autenticazione
            .and()
            .formLogin().disable() // Disabilita il form di login
            .logout().disable(); // Disabilita il logout

        return http.build();// Costruisce e restituisce l'oggetto SecurityFilterChain
    }
	
    
    
    
	@Bean
    // Definisce il servizio per caricare i dettagli dell'utente dal database

	DatabaseUserDetailsService userDetailsService() {
		// Crea un'istanza di DatabaseUserDetailsService
		return new DatabaseUserDetailsService();
	}
	

	@Bean
    // Definisce il codificatore di password
    PasswordEncoder passwordEncoder() {
		 // Restituisce un codificatore di password delegante
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
    // Configura il provider di autenticazione
    DaoAuthenticationProvider authenticationProvider() {
		// Crea un'istanza di DaoAuthenticationProvider
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        // Imposta il servizio per caricare i dettagli dell'utente dal database
		authProvider.setUserDetailsService(userDetailsService());
		
        // Imposta il codificatore di password
		authProvider.setPasswordEncoder(passwordEncoder());

        // Può essere usato per stampare la password codificata (utile per debug)
		//System.out.println(passwordEncoder().encode("ciccio"));

		// Restituisce il provider di autenticazione configurato
		return authProvider;
	}
	
}
	


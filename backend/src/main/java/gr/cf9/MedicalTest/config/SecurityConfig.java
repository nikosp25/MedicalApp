package gr.cf9.MedicalTest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 1. Define the Encryption Encoder Bean
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2. Configure the security rules for our endpoints
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disabled for testing via Postman
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/user/register").permitAll() // Open the registration door
                        .requestMatchers("/api/user/update").permitAll()
                        .requestMatchers("/error").permitAll()
                        .requestMatchers("/api/user/delete").permitAll()
                        .requestMatchers("/api/user/getUser/*").permitAll()
                        .anyRequest().authenticated() // Lock every other door
                );

        return http.build();
    }
}

package apontamento.apontaai.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
@Configuration
public class SecurityConfig {

    @Bean
    public Argon2PasswordEncoder passwordEncoder() {
        return Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // libera qualquer método para /graphql
                        .requestMatchers("/graphql").permitAll()
                        // (opcional) libera o playground
                        .requestMatchers("/graphiql/**", "/playground/**").permitAll()
                        // o resto exige auth
                        .anyRequest().authenticated()
                )
                .build();
    }

}

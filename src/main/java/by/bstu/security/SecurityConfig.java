package by.bstu.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Component
public class SecurityConfig {

    @Bean
    SecurityFilterChain getSecurityConfig(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(req -> req
                    .requestMatchers("/members").hasRole("USER")
                    .requestMatchers("/admin").hasAuthority("ROLE_ADMIN")
                    .anyRequest().permitAll()
                )
                .formLogin(l->l
                        .passwordParameter("pass")
                        .usernameParameter("name")
                )
                .logout(l->l
                        .invalidateHttpSession(true)
                );
        return http.build();
    }

    @Bean
    UserDetailsService getPredefinedUsers() {
        UserDetails user1 = User.builder()
                .username("user1")
                .password("{noop}user1")
                .roles("USER").build();
        UserDetails user2 = User.builder()
                .username("user2")
                .password("{bcrypt}$2y$10$jMnH8eav7QRPrbpsce5VCuqvns2Q7FO3xB8u0Tj8EO4SKcPL8FnG2")
                .roles("USER", "ADMIN").build();
        return new InMemoryUserDetailsManager(user1, user2);
    }
}

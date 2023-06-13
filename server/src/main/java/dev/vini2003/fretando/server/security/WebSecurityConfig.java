package dev.vini2003.fretando.server.security;

import dev.vini2003.fretando.server.security.user.details.RepositoryUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final RepositoryUserDetailsService userDetailsService;

    @Autowired
    public WebSecurityConfig(RepositoryUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(it -> {
                    it.ignoringRequestMatchers("/login*");

                    it.ignoringRequestMatchers("/api/**");
                })
                .authorizeHttpRequests(it -> {
                    it.requestMatchers("/login*")
                            .permitAll();

                    it.requestMatchers("/api/**")
                            .permitAll();

                    it.anyRequest().authenticated();
                })
                .formLogin((it) -> {
                    it.loginProcessingUrl("/login")
                            .successHandler(authenticationSuccessHandler())
                            .failureHandler(authenticationFailureHandler());
                })
                .logout((logout) -> logout.permitAll())
                .userDetailsService(userDetailsService);

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        return (request, response, authentication) -> {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"message\":\"Login successful\"}");
        };
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler(){
        return (request, response, exception) -> {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"message\":\"Login failed\"}");
        };
    }
}
package com.tech.ada.spring_cinestream.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder().encode("password"))
                .roles("ADMIN")
                .and()
                .withUser("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll() // Permite acesso ao console H2 sem autenticação
                .antMatchers("/filmes/**", "/series/**").hasRole("ADMIN") // Requer papel ADMIN para filmes e séries
                .anyRequest().permitAll() // Permite acesso a qualquer outra requisição sem autenticação
                .and()
                .formLogin() // Habilita formulário de login padrão do Spring Security
                .defaultSuccessUrl("/filmes", true) // Redireciona após login bem-sucedido
                .and()
                .logout() // Configura logout
                .logoutSuccessUrl("/") // Redireciona após logout
                .and()
                .csrf().disable(); // Desativa o CSRF para o console H2 (não recomendado em produção)

        // Configuração adicional para permitir que o console H2 funcione corretamente
        http.headers().frameOptions().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Usado para encriptar senhas
    }
}

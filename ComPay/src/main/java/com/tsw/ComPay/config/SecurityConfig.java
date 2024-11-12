package com.tsw.ComPay.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {


    private final CustomUserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) ->
                        {
                            authorize.requestMatchers("/", "/login/**", "/register","/css/**", "/js/**", "/images/**", "/resources/**" ).permitAll()
                            //        .requestMatchers("/perfil").hasAnyAuthority("ADMIN")
                                    .anyRequest().authenticated();
                        }
                )
                //.httpBasic(Customizer.withDefaults())
                .formLogin(form ->
                                form.loginPage("/login").permitAll()
                                      .loginProcessingUrl("/login")
                                        .defaultSuccessUrl("/groups?lang=es", true)
                                 .failureUrl("/login/error")

                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")     // Redirección tras logout
                        .permitAll()
                );

        return http.build();
    }
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}
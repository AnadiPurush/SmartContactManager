package com.utsavsharma.smartContactManager.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.utsavsharma.smartContactManager.services.impl.SecurityCustomUserDetailService;

/**
 * @file SecurityConfig.java
 *       Author: Utsav
 *       Date: 12-08-2024
 *       Time: 10:14:49
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final SecurityCustomUserDetailService UserDetailService;
    private final oauth2AuthenticationSuccessHandler oauth2AuthenticationSuccessHandler;

    public SecurityConfig(SecurityCustomUserDetailService UserDetailService,
            oauth2AuthenticationSuccessHandler oauth2AuthenticationSuccessHandler) {
        this.UserDetailService = UserDetailService;
        this.oauth2AuthenticationSuccessHandler = oauth2AuthenticationSuccessHandler;

    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(UserDetailService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        /*
         * @author utsavsharma
         * for more security managing url access from the blow method
         */
        httpSecurity.authorizeHttpRequests(authorize -> {

            authorize.requestMatchers("/resources/**", "/static/**", "/css/**", "/js/**",
                    "/images/**").permitAll();

            authorize.requestMatchers("/user/**", "/services", "/contact")

                    .authenticated();

            authorize.requestMatchers(HttpMethod.POST, "/do-login").permitAll();
            authorize.anyRequest()

                    .permitAll();

        })

                /*
                 * @author utsavsharma
                 * blow here we will manage the login form default spring security login form
                 * our own login form
                 */
                .formLogin(formlogin -> formlogin.loginPage("/login")
                        .loginProcessingUrl("/do-login")

                        .successForwardUrl("/contact")
                        .failureForwardUrl("/login?error=true")
                        .usernameParameter("email")
                        .passwordParameter("password")

                        .permitAll())

                .logout(logout -> {
                    logout.logoutUrl("/logout")
                            // .invalidateHttpSession(true)
                            // // .logoutSuccessUrl("/login?logout=true")
                            // .clearAuthentication(true)
                            .permitAll();

                });

        httpSecurity.oauth2Login(oauth2LoginCustomizer -> {
            oauth2LoginCustomizer.loginPage("/login")
                    .successHandler(oauth2AuthenticationSuccessHandler)

                    .permitAll();

        });

        System.out.println("working on login" + this.getClass());

        return httpSecurity.build();
    }

}

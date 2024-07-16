package com.example.ecomApp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.ecomApp.security.CustomUserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
	
	
    @Autowired
    private CustomUserDetailsServiceImpl customUserDetailsServiceImpl;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Use BCrypt for password hashing
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsServiceImpl);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.inMemoryAuthentication()
        .withUser("user@gmail.com").password("{noop}password").roles("USER")
        .and()
        .withUser("admin@gmail.com").password("{noop}password").roles("ADMIN");
    	auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(requests -> requests
                .antMatchers("/admin/**").hasRole("ADMIN") // Only allow users with ADMIN role to access /admin/**
                .antMatchers("/user/**").hasRole("USER") // Only allow users with USER role to access /user/**
                .antMatchers("/register", "/login", "/shop/**", "/", "/home").permitAll() // Allow everyone to access registration and login
                .antMatchers("/css/**").permitAll() // Permit access to CSS files
                .antMatchers("/js/**").permitAll() // Permit access to JS files
                .antMatchers("/images/**", "/productImages/**").permitAll() // Permit access to image directories
                .antMatchers("/h2-console/**").permitAll() // Permit access to H2 console
                .anyRequest().authenticated())
                .formLogin(login -> login
                        .loginPage("/login") // Specify custom login page
                        .failureUrl("/login?error=true")
                        .defaultSuccessUrl("/home", true) // Redirect to /home on successful login
                        .permitAll())
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // Specify custom logout URL
                        .logoutSuccessUrl("/login?logout") // Redirect to login page on logout
                        .permitAll())
                .csrf(csrf -> csrf.ignoringAntMatchers("/h2-console/**"))
                .headers(headers -> headers.frameOptions().sameOrigin()); // Allow H2 console to be accessed in a frame
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}

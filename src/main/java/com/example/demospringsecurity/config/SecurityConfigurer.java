package com.example.demospringsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/profile")
                .authenticated()
                .and().authorizeRequests().antMatchers("/admin").hasAnyRole("ADMIN", "USER", "STAFF")
                .and().authorizeRequests().antMatchers("/*").permitAll()
                .and().formLogin().loginPage("/my-login").and().logout().logoutUrl("/my-logout").and().exceptionHandling()
                .accessDeniedPage("/my-access-deny");
    }

/*    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("12345678").roles("ADMIN","USER","STAFF").build();
        UserDetails user = User.withDefaultPasswordEncoder().username("user").password("123456789").roles("USER").build();
        return new InMemoryUserDetailsManager(admin, user);
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        return new DelegatingPasswordEncoder("{bcrypt}", encoders);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("{bcrypt}$2a$10$4dAt6dC.wMvQa9WaqppSH.jRUTkGEqwBms.ePqlbHzkakFK.rfKL2").roles("USER");
        auth.inMemoryAuthentication().withUser("staff").password("{scrypt}$2a$10$rjhdi6qDnE0Oz733KF/6DOfqInJhYUaaB60ATEVqs/hhMcwZwWhH.").roles("STAFF");

    }
}

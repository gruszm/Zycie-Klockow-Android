package pl.morozgrusz.zycieklockow.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig
{
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity.authorizeHttpRequests(customizer -> customizer
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/orders/**").hasRole("CUSTOMER")
                        .requestMatchers("/deliveryMethods/**").hasRole("ADMIN")
                        .anyRequest().permitAll())
                .formLogin(login -> login
                        .permitAll())
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/?logout")
                        .permitAll())
                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/accessDenied"));

        return httpSecurity.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager()
    {
        UserDetails testUser = User.builder()
                .username("test")
                .password("{noop}test")
                .roles("CUSTOMER")
                .build();

        UserDetails testUser2 = User.builder()
                .username("test2")
                .password("{noop}test")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(testUser, testUser2);
    }
}

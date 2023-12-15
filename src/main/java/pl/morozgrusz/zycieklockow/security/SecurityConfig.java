package pl.morozgrusz.zycieklockow.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig
{
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity.authorizeHttpRequests(customizer -> customizer
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/cart/**").authenticated()
                        .requestMatchers("/orders/**").hasRole("CUSTOMER")
                        .requestMatchers("/deliveryMethods/*/").hasRole("ADMIN")
                        .requestMatchers("/products/*/").hasRole("ADMIN")
                        .requestMatchers("/cart/addCartElement/").authenticated()
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
    public JdbcUserDetailsManager userDetailsManager(DataSource dataSource)
    {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);

        userDetailsManager.setUsersByUsernameQuery(
                "SELECT email, password, active FROM user WHERE user.email = ?"
        );

        userDetailsManager.setAuthoritiesByUsernameQuery(
                "SELECT email, role FROM user INNER JOIN role on user.id = role.user_id WHERE user.email = ?"
        );

        return userDetailsManager;
    }
}

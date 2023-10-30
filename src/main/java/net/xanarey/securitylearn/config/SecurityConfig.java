package net.xanarey.securitylearn.config;

import net.xanarey.securitylearn.model.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    protected UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username("admin")
                        .password(passwordEncoder().encode("admin"))
                        .roles(Role.ADMIN.name())
                        .build(),
                User.builder()
                        .username("user")
                        .password(passwordEncoder().encode("user"))
                        .roles(Role.USER.name())
                        .build()
        );
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorizeRequests ->
                        {
                            try {
                                authorizeRequests
                                        .requestMatchers("/admin").hasRole(Role.ADMIN.name())
                                        .requestMatchers("/user").hasRole(Role.USER.name())
                                        .anyRequest().authenticated()
                                        .and().httpBasic();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                ).build();
    }


}
//Весь следующий код на последней версии Java , Spring, Security!!!
//Без всяких antMatchers, WebSecurityConfigurerAdapter, formLogin, authorizeRequests, mvcMatchers
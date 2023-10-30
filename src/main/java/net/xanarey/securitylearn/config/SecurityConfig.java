package net.xanarey.securitylearn.config;

import net.xanarey.securitylearn.model.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
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
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    protected UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username("admin")
                        .password(passwordEncoder().encode("admin"))
                        .authorities(Role.ADMIN.getAuthorities())
                        .build(),
                User.builder()
                        .username("user")
                        .password(passwordEncoder().encode("user"))
                        .authorities(Role.USER.getAuthorities())
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
//                                        .requestMatchers("/admin").hasAuthority(Permission.DEV_ADMIN.getPermission())
//                                        .requestMatchers("/user").hasAuthority(Permission.DEV_USER.getPermission())
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
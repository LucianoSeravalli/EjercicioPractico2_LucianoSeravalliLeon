package EjercicioPractico2_LucianoSeravalliLeon;

import EjercicioPractico2_LucianoSeravalliLeon.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login", "/css/**", "/js/**", "/img/**").permitAll()
                .requestMatchers("/usuarios/**", "/roles/**").hasRole("ADMIN")
                .requestMatchers("/eventos/nuevo", "/eventos/guardar", "/eventos/editar/**", "/eventos/eliminar/**")
                .hasAnyRole("ADMIN", "ORGANIZADOR")
                .requestMatchers("/eventos/**").hasAnyRole("ADMIN", "ORGANIZADOR", "CLIENTE")
                .anyRequest().authenticated()
                )
                .formLogin(form -> form
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/", true)
                .permitAll()
                )
                .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
                )
                .exceptionHandling(exception -> exception
                .accessDeniedPage("/error/403")
                )
                .authenticationProvider(authenticationProvider())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider proveedor = new DaoAuthenticationProvider(userDetailsService);
        proveedor.setPasswordEncoder(passwordEncoder());
        return proveedor;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
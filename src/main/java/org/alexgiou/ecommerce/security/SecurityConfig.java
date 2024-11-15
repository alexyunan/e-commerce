package org.alexgiou.ecommerce.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;


import java.util.List;

/**
 * @author: Alexandros Giounan
 * @code @created: 10/24/2024
 */

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(m -> m
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("/api/**").authenticated()
                                .requestMatchers("/api/products/*/reviews").permitAll()
                                .anyRequest().permitAll())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(customizer ->
                        customizer.configurationSource(configurationSource()));


        return http.build();
    }

    /**
     * Configures and returns a {@link CorsConfigurationSource} for handling Cross-Origin Resource Sharing (CORS).
     *
     * <p>This configuration allows for:
     * <ul>
     *     <li>Credentials to be included in requests.</li>
     *     <li>All origins (i.e., any domain) to be permitted with wildcard support.</li>
     *     <li>All headers to be allowed.</li>
     *     <li>HTTP methods GET, POST, PUT, DELETE, and PATCH to be accepted.</li>
     *     <li>Specific response headers (Authorization, Origin, Content-Type, Accept) to be exposed to the client.</li>
     * </ul>
     *
     * @return a {@link CorsConfigurationSource} that configures CORS policy for requests.
     */
    @Bean
    public CorsConfigurationSource configurationSource() {
        return request -> {
            CorsConfiguration corsConfiguration = new CorsConfiguration();
            corsConfiguration.setAllowCredentials(true);
            corsConfiguration.setAllowedOrigins(List.of("*"));
            corsConfiguration.setAllowedHeaders(List.of("*"));
            corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH"));
            corsConfiguration.setExposedHeaders(List.of(
                    HttpHeaders.AUTHORIZATION,
                    HttpHeaders.ORIGIN,
                    HttpHeaders.CONTENT_TYPE,
                    HttpHeaders.ACCEPT));

            return corsConfiguration;
        };
    }

    /**
     * Provides a {@link PasswordEncoder} bean for encoding passwords.
     * This method creates and returns an instance of {@link BCryptPasswordEncoder}.
     * The purpose is to ensure that passwords stored in the application's database
     * are securely hashed and encoded, protecting user credentials from unauthorized access.
     *
     * @return A {@link PasswordEncoder} instance for encoding passwords.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

package ch.csbe.productstore.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RepositoryRestResource(exported = false)
/**
 * Configuration class responsible for setting up authentication and authorization rules.
 */
@Configuration
public class AuthConfig {

    // Autowire the JWT filter which will validate the token in the request.
    @Autowired
    private JwtFilter jwtFilter;

    /**
     * Defines and configures the security filter chain for the application.
     *
     * @param httpSecurity the HttpSecurity instance to configure.
     * @return the configured SecurityFilterChain.
     * @throws Exception if an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                // Add the JWT filter before the default UsernamePasswordAuthenticationFilter.
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
                    // Endpoints accessible for everyone (no authentication needed)
                    authorizationManagerRequestMatcherRegistry.requestMatchers("/user").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers("/user/login").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.GET, "/product").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.GET, "/product/*").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.GET, "/category/*").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.GET, "/category").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.GET, "/category/*/products").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers("swagger-ui/*").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers("/swagger-ui/index.html").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers("/swagger-ui/index.html").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers("/swagger-ui/*").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers("/swagger-ui/*").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers("/v3/api-docs").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers("/v3/api-docs/swagger-config").permitAll();



                    // Endpoints requiring admin authority
                    authorizationManagerRequestMatcherRegistry.requestMatchers("/makeadmin/*").hasAuthority("admin");
                    authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.POST, "/product/*").hasAuthority("admin");
                    authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.PUT, "/product/*").hasAuthority("admin");
                    authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.DELETE, "/product/*").hasAuthority("admin");
                    authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.POST, "/category").hasAuthority("admin");
                    authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.PUT, "/category/*").hasAuthority("admin");
                    authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.DELETE, "/category/*").hasAuthority("admin");
                })
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }


}

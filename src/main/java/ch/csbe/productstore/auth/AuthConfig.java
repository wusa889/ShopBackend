package ch.csbe.productstore.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
public class AuthConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
                    // Endpoints accessible for everyone (without authentication)
                    authorizationManagerRequestMatcherRegistry.requestMatchers("/user").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers("/user/login").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.GET,"/product").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.GET,"/product/*").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.GET,"/category/*").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.GET,"/category").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.GET,"/category/*/products").permitAll();

                    // Endpoints requiring the "admin" authority
                    authorizationManagerRequestMatcherRegistry.requestMatchers("/makeadmin/*").hasAuthority("admin");
                    authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.POST,"/product/*").hasAuthority("admin");
                    authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.PUT,"/product/*").hasAuthority("admin");
                    authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.DELETE,"/product/*").hasAuthority("admin");
                    authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.POST,"/category").hasAuthority("admin");
                    authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.PUT,"/category/*").hasAuthority("admin");
                    authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.DELETE,"/category/*").hasAuthority("admin");
                })
                .httpBasic(AbstractHttpConfigurer::disable) // Disabling basic auth
                .csrf(AbstractHttpConfigurer::disable) // Disabling CSRF as per your existing configuration
                .build();
    }


}

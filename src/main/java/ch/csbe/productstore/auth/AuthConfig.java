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
                    //TODO Authorisation auf die einzelnen Endpoints setzen wie in LB verlangt.
                    authorizationManagerRequestMatcherRegistry.requestMatchers("/makeadmin/*").hasAuthority("admin");
                    authorizationManagerRequestMatcherRegistry.requestMatchers("/user").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers("/user/login").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers("/product").hasAuthority("admin");
                    authorizationManagerRequestMatcherRegistry.requestMatchers("/product/*").hasAnyAuthority("admin","user");
                    authorizationManagerRequestMatcherRegistry.requestMatchers("/category").hasAuthority("admin");
                })
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }


}

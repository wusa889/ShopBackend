package ch.csbe.productstore.auth;

import ch.csbe.productstore.user.User;
import ch.csbe.productstore.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getRequestURI().startsWith("/user");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        try {
            if (authorization != null && authorization.startsWith("Bearer ")) {
                String jwt = authorization.substring("Bearer ".length());
                String username = jwtService.getUserName(jwt);

                Optional<User> user = userRepository.findByUsername(username);

                if (user.isPresent()) {
                    List<GrantedAuthority> authorities = List.of(() -> user.get().isAdmin() ? "admin" : "user");
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(username, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    logger.error("User not found for the provided token");
                }
            } else {
                if (authorization == null) {
                    logger.info("Missing 'Authorization' header");
                } else {
                    logger.info("'Authorization' header does not start with 'Bearer '");
                }
            }
        } catch (Exception e) {
            logger.error("Failed to authenticate user", e);
        }

        filterChain.doFilter(request, response);
    }
}

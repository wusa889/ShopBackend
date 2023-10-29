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
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
@RepositoryRestResource(exported = false)
/**
 * Filter responsible for intercepting requests and extracting JWT tokens from the 'Authorization' header.
 * It then validates and sets the authentication in the security context.
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

    // Logger for this class.
    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    // creating JwtService instance
    @Autowired
    private JwtService jwtService;

    // creating UserRepository instance
    @Autowired
    private UserRepository userRepository;

    /**
     * Determine if a request should not be filtered.
     *
     * @param request The incoming request.
     * @return true if the request URI starts with "/user", otherwise false.
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        System.out.println(request.getRequestURI());
        boolean filter = false;
        if (request.getRequestURI().startsWith("/swagger")){
            filter = true;
        }
        if (request.getRequestURI().startsWith("/user")){
            filter = true;
        }
        if (request.getRequestURI().startsWith("/v3")){
            filter = true;
        }
        return filter;
    }

    /**
     * Process each request to check for a valid JWT, extracts the username, and sets the authentication.
     *
     * @param request     The incoming request.
     * @param response    The outgoing response.
     * @param filterChain The filter chain.
     * @throws ServletException In case of servlet errors.
     * @throws IOException      In case of I/O errors.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        try {
            if (authorization != null && authorization.startsWith("Bearer ")) {
                // Extract JWT token from header.
                String jwt = authorization.substring("Bearer ".length());
                String username = jwtService.getUserName(jwt);

                Optional<User> user = userRepository.findByUsername(username);

                // Check if the user is present in the repository.
                if (user.isPresent()) {
                    // Grant authorities based on user role (admin/user).
                    List<GrantedAuthority> authorities = List.of(() -> user.get().isAdmin() ? "admin" : "user");
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(username, null, authorities);
                    // Set authentication in the security context.
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

        // Continue the filter chain.
        filterChain.doFilter(request, response);
    }
}

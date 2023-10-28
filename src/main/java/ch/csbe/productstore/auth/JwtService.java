package ch.csbe.productstore.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Service;

import java.util.Date;
@RepositoryRestResource(exported = false)
/**
 * Service class responsible for generating and parsing JWT tokens.
 */
@Service
public class JwtService {

    // Secret key used for signing the JWT.
    private final String secret = "SecretStuff";

    /**
     * Creates a JWT token for the given username.
     *
     * @param username The subject for which the JWT should be generated.
     * @return A JWT string.
     */
    public String createJwt(String username) {
        return Jwts.builder().setIssuer("Brecht") // Token issuer
                .setIssuedAt(new Date(System.currentTimeMillis())) // Issue date
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 20)) // Expiration date
                .setSubject(username) // The subject or user for which the JWT is created
                .signWith(SignatureAlgorithm.HS256, secret) // Signing the token with the specified algorithm and secret
                .compact();
    }

    /**
     * Parses a JWT token to extract the username (subject).
     *
     * @param jwt The JWT string to be parsed.
     * @return The username extracted from the JWT.
     */
    public String getUserName(String jwt) {
        return Jwts.parser().setSigningKey(secret) // Specify the secret key to decode
                .parseClaimsJws(jwt) // Parse the JWT string
                .getBody().getSubject(); // Extract the subject (username) from the token body
    }
}
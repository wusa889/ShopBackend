package ch.csbe.productstore.auth;

import ch.csbe.productstore.user.User;
import ch.csbe.productstore.user.UserDto;
import ch.csbe.productstore.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Service class responsible for authentication-related functionalities, such as generating JWT tokens.
 */
@Service
public class AuthService {

    // creating userRepository instance
    @Autowired
    private UserRepository userRepository;
    // creating JwtService instance
    @Autowired
    private JwtService jwtService;
    // creating BCryptPasswordEncoder instance
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    /**
     * Generate JWT for a user based on their credentials.
     *
     * @param userDto Data transfer object containing user's credentials.
     * @return A JWT as string if the user is authenticated, or an error message.
     */
    public String getJwt(UserDto userDto) {
        // Get the user from the database based on the username provided in the DTO.
        User user = userRepository.findByUsername(userDto.getUsername()).orElse(null);

        // If user is not found in DB, return not found message.
        if (user == null) {
            return HttpStatus.NOT_FOUND + " User not found";
        }

        // If the provided password does not match the stored encoded password, return Username or Password Wrong.
        if (!bCryptPasswordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            return HttpStatus.FORBIDDEN + " Username or Password Wrong";
        }

        // Generate and return a JWT String for the authenticated user.
        return jwtService.createJwt(user.getUsername());
    }
}


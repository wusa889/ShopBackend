package ch.csbe.productstore.user;

import ch.csbe.productstore.products.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class responsible for logic related to User.
 */
@RepositoryRestResource(exported = false)
@Service
public class UserService {

    // Creating instance of UserRepository
    @Autowired
    private UserRepository userRepository;

    // Creating instance of BCryptPasswordEncoder
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public void createUser(UserDto user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        newUser.setAdmin(false);
        userRepository.save(newUser);
    }

    public String makeUserAdmin(long id, User user) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setAdmin(user.getAdmin());
            userRepository.save(existingUser);
            return "User is made Admin";
        } else {
            return null;
        }
    }
}



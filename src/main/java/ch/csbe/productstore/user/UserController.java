package ch.csbe.productstore.user;

import ch.csbe.productstore.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller responsible for handling HTTP requests related to User.
 * Provides endpoints to manage products and to view products.
 */
@RestController
public class UserController {

    // Service class instance responsible for operations related to User
    @Autowired
    private UserService userService;

    // Service class instance responsible for operations related to Authentication
    @Autowired
    private AuthService authService;

    @PostMapping("/user")
    public String createUser(@RequestBody UserDto user) {
        // Registers a new User
        userService.createUser(user);
        return "user Created";
    }

    @PostMapping("/user/login")
    // Logs a user in and provides a JWT
    public ResponseEntity<String> login(@RequestBody UserDto userDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authService.getJwt(userDto));
    }

    @PutMapping("/makeadmin/{id}")
    // Promotes a User to Admin status
    public String makeAdmin(@PathVariable long id, @RequestBody User user) {
        userService.makeUserAdmin(id, user);
        return "user is made admin";
    }

}

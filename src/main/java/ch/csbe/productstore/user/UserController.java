package ch.csbe.productstore.user;

import ch.csbe.productstore.auth.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "UserController",
        description = "Controller für User Aktionen")
public class UserController {

    // Service class instance responsible for operations related to User
    @Autowired
    private UserService userService;

    // Service class instance responsible for operations related to Authentication
    @Autowired
    private AuthService authService;

    @PostMapping("/user")
    @Operation(summary = "Erlaubt die Registration für einen Benutzer",
            description = "Erlaubt den User sich zu registrieren")
    public String createUser(@RequestBody UserDto user) {
        // Registers a new User
        userService.createUser(user);
        return "user Created";
    }

    @PostMapping("/user/login")
    @Operation(summary = "Erlaubt das einloggen eines Benutzers und generiert ein JWT",
            description = "Erlaubt das einloggen eines Benutzers und generiert ein JWT")
    // Logs a user in and provides a JWT
    public ResponseEntity<String> login(@RequestBody UserDto userDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authService.getJwt(userDto));
    }

    @PutMapping("/makeadmin/{id}")
    @Operation(summary = "Erlaubt es einem Admin einen User zum Admin zu promoten",
            description = "Lässt einen Admin einen User zum Admin hochstufen")
    // Promotes a User to Admin status
    public String makeAdmin(
            @Parameter(description = "Die ID des Users der hochgestuft werden soll")
            @PathVariable long id,
            @RequestBody User user
    ) {
        userService.makeUserAdmin(id, user);
        return "user is made admin";
    }

}

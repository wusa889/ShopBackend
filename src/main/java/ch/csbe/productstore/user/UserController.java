package ch.csbe.productstore.user;

import ch.csbe.productstore.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;
    @PostMapping("/user")
    public String createUser(@RequestBody UserDto user){
        userService.createUser(user);
        return "user Created";
    }
    @PostMapping("/user/login")
    public ResponseEntity<String> login(@RequestBody UserDto userDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authService.getJwt(userDto));
    }
    @PutMapping("/makeadmin/{id}")
    public String makeAdmin(@PathVariable long id, @RequestBody User user){
        userService.makeUserAdmin(id, user);
        return "user is made admin";
    }

}

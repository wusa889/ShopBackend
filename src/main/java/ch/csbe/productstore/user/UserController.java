package ch.csbe.productstore.user;

import ch.csbe.productstore.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;
    @GetMapping()
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable long id){
        return userService.getUserById(id);
    }

    @PostMapping()
    public String createUser(@RequestBody UserDto user){
        userService.createUser(user);
        return "user Created";
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable long id, @RequestBody User user){
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable long id){
        return userService.deleteUser(id);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto userDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authService.getJwt(userDto));
    }

}

package ch.csbe.productstore.auth;

import ch.csbe.productstore.user.User;
import ch.csbe.productstore.user.UserDto;
import ch.csbe.productstore.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public String getJwt(UserDto userDto) {
        User user = userRepository.findByUsername(userDto.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!bCryptPasswordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            System.out.println("User not Found");
        }
        return jwtService.createJwt(user.getUsername());
    }
}

package pl.morozgrusz.zycieklockow.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.morozgrusz.zycieklockow.entities.User;
import pl.morozgrusz.zycieklockow.services.UserService;

@RestController
@RequestMapping("/api/auth")
public class SecurityController
{
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityController(UserService userService, PasswordEncoder passwordEncoder)
    {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody LoginRequest loginRequest)
    {
        User foundUser = userService.findUserByEmail(loginRequest.getEmail());
        String encodedPassword;

        if (foundUser == null)
        {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }

        encodedPassword = foundUser.getPassword();

        if (passwordEncoder.matches(loginRequest.getPassword(), encodedPassword))
        {
            return ResponseEntity
                    .ok()
                    .body(JwtUtils.createToken(loginRequest.getEmail(), foundUser.getRoles().stream().map(role -> role.getRole()).toList()));
        }
        else
        {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(null);
        }
    }
}

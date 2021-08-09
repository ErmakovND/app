package nd.ermakov.app.controller;

import nd.ermakov.app.model.User;
import nd.ermakov.app.request.AuthRequest;
import nd.ermakov.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody AuthRequest authRequest) {
        if (userService.checkByLogin(authRequest.getLogin())) {
            return new ResponseEntity<>("логин занят", HttpStatus.BAD_REQUEST);
        }
        if (userService.checkByEmail(authRequest.getEmail())) {
            return new ResponseEntity<>("e-mail занят", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setLogin(authRequest.getLogin());
        user.setEmail(authRequest.getEmail());
        user.setPassword(authRequest.getPassword());
        userService.create(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody AuthRequest authRequest) {
        if (userService.checkByLogin(authRequest.getLogin())) {
            return new ResponseEntity<>(userService.findByLogin(authRequest.getLogin()), HttpStatus.OK);
        }
        if (userService.checkByEmail(authRequest.getEmail())) {
            return new ResponseEntity<>(userService.findByEmail(authRequest.getEmail()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

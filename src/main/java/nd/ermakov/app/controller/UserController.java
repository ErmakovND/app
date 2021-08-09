package nd.ermakov.app.controller;

import nd.ermakov.app.model.User;
import nd.ermakov.app.repository.UserRepository;
import nd.ermakov.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<Principal> me(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return new ResponseEntity<>(principal, HttpStatus.OK);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<User>> all() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }
}

package nd.ermakov.app.controller;

import nd.ermakov.app.model.Post;
import nd.ermakov.app.model.User;
import nd.ermakov.app.service.PostService;
import nd.ermakov.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;
    private UserService userService;

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Post>> findAll(
            @RequestParam("page") int page,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date
    ) {
        return new ResponseEntity<>(postService.findAllOlderThan(page, date), HttpStatus.OK);
    }

    @GetMapping("/all/{category}")
    public ResponseEntity<List<Post>> findAllByCategory(
            @RequestParam("page") int page,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @PathVariable String category
    ) {
        return new ResponseEntity<>(postService.findAllOlderThanInCategory(page, date, category), HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> add(@RequestBody Post post, Principal principal) {
        User user = userService.findByLogin(principal.getName());
        post.setUser(user);
        post.setDate(LocalDateTime.now());
        postService.create(post);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

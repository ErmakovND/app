package nd.ermakov.app.controller;

import nd.ermakov.app.model.Reply;
import nd.ermakov.app.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/replies")
public class ReplyController {

    private ReplyService replyService;

    @Autowired
    public void setReplyService(ReplyService replyService) {
        this.replyService = replyService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(Reply reply) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

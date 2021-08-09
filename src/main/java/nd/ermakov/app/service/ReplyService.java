package nd.ermakov.app.service;

import nd.ermakov.app.model.Reply;
import nd.ermakov.app.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyService {

    private ReplyRepository replyRepository;

    @Autowired
    public void setReplyRepository(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    public void create(Reply reply) {
        replyRepository.save(reply);
    }
}

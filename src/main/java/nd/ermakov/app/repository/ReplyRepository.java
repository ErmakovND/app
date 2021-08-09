package nd.ermakov.app.repository;

import nd.ermakov.app.model.Post;
import nd.ermakov.app.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findAllByPost(Post post);
}

package nd.ermakov.app.service;

import nd.ermakov.app.model.Post;
import nd.ermakov.app.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {

    private static int PAGE_SIZE = 1;

    private PostRepository postRepository;

    @Autowired
    public void setPostRepository(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void create(Post post) {
        postRepository.save(post);
    }

    public List<Post> findAllOlderThan(int page, LocalDateTime date) {
        PageRequest pageRequest = PageRequest.of(page, PAGE_SIZE, Sort.Direction.DESC, "date");
        Page<Post> posts = postRepository.findAllByDateBefore(date, pageRequest);
        return posts.toList();
    }

    public List<Post> findAllOlderThanInCategory(int page, LocalDateTime date, String category) {
        PageRequest pageRequest = PageRequest.of(page, PAGE_SIZE, Sort.Direction.DESC, "date");
        Page<Post> posts = postRepository.findAllByDateBeforeAndCategory(date, category, pageRequest);
        return posts.toList();
    }
}

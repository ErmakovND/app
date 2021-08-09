package nd.ermakov.app.repository;

import nd.ermakov.app.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;

public interface PostRepository extends PagingAndSortingRepository<Post, Long> {
    Page<Post> findAllByDateBefore(LocalDateTime date, Pageable pageable);
    Page<Post> findAllByDateBeforeAndCategory(LocalDateTime date, String category, Pageable pageable);
}

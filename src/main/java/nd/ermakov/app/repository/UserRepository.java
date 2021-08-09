package nd.ermakov.app.repository;

import nd.ermakov.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByLogin(String login);
    Boolean existsByEmail(String email);
    User findByLogin(String login);
    User findByEmail(String email);
}

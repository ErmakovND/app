package nd.ermakov.app.service;

import nd.ermakov.app.model.User;
import nd.ermakov.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public Boolean checkByLogin(String login) {
        return userRepository.existsByLogin(login);
    }

    public Boolean checkByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}

package nig.ger.service;

import lombok.AllArgsConstructor;
import nig.ger.entity.User;
import nig.ger.repository.UserRepository;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(long userId) {
        return userRepository.findById(userId).orElseThrow(RuntimeException::new);
    }
}

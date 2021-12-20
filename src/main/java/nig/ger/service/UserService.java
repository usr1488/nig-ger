package nig.ger.service;

import nig.ger.entity.User;
import nig.ger.exception.UserNotFoundException;
import nig.ger.repository.UserRepository;
import nig.ger.util.Constants;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import static nig.ger.util.Constants.IMAGE_FORMAT;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user, MultipartFile profileImage) throws IOException {
        user = userRepository.save(user);

        ImageIO.write(
                ImageIO.read(profileImage.getInputStream()),
                IMAGE_FORMAT,
                new File(Constants.PROFILE_IMAGE_LOCATION + user.getUserId() + "." + IMAGE_FORMAT)
        );

        return user;
    }

    public User getUserById(long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }
}

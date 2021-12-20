package nig.ger.controller;

import nig.ger.entity.Role;
import nig.ger.entity.Sex;
import nig.ger.entity.User;
import nig.ger.service.UserService;
import nig.ger.util.Constants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

import static nig.ger.util.Constants.*;


@Controller
public class RegistrationController {
    private UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String getRegistration() {
        return "registration";
    }

    @PostMapping(value = "/registration", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String postRegistration(@RequestParam String email,
                                   @RequestParam String username,
                                   @RequestParam String password,
                                   @RequestParam String sex,
                                   @RequestParam String country,
                                   @RequestParam String city,
                                   @RequestParam MultipartFile profilePhoto) throws IOException {

        long id = userService.saveUser(
                User.builder()
                        .username(username)
                        .password(password)
                        .email(email)
                        .sex(Arrays.stream(Sex.values())
                                .filter(x -> x.getSex().equalsIgnoreCase(sex))
                                .findFirst()
                                .orElseThrow(RuntimeException::new))
                        .role(Role.USER)
                        .country(country)
                        .city(city)
                        .isOnline(true)
                        .status(" ")
                        .build()).getUserId();

        ImageIO.write(ImageIO.read(profilePhoto.getInputStream()), IMAGE_FORMAT, new File(PROFILE_PHOTO_LOCATION + id + ".jpg"));

        return PROFILE_REDIRECT;
    }
}

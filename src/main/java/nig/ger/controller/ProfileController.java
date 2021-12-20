package nig.ger.controller;

import nig.ger.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileController {
    private UserService userService;

    @GetMapping("/profile/{userId}")
    public ModelAndView getProfile(@PathVariable long userId) {
        return new ModelAndView("profile", "userList", userService.getUserById(userId));
    }
}

package ru.itis.javalab.controllers.dev;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("profile")
@Profile("dev")
public class DevProfileController {

    @GetMapping
    public String getProfilePage() {
        return "test_profile";
    }

}

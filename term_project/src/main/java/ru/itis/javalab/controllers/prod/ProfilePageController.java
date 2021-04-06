package ru.itis.javalab.controllers.prod;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("profile")
@Profile("prod")
public class ProfilePageController {

    @GetMapping
    public String getProfilePage() {
        return "profile";
    }

}

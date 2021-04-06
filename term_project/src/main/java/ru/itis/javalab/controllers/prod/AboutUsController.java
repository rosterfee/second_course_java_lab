package ru.itis.javalab.controllers.prod;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("about_us")
public class AboutUsController {

    @GetMapping
    public String getAboutUsPage() {
        return "about_us";
    }

}

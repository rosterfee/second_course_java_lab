package ru.itis.javalab.controllers.prod;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class SignInController {

    @GetMapping("/sign_in")
    public String getSignInPage() {
        return "sign_in";
    }

}

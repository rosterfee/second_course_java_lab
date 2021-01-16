package ru.itis.javaLab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.javaLab.models.User;
import ru.itis.javaLab.services.UsersService;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("sign_in")
public class SignInController {

    @Autowired
    HttpSession session;
    @Autowired
    UsersService usersService;

    @GetMapping
    public String getSignInPage(@RequestParam(value = "status", required = false) String status,
                                Model model) {
        model.addAttribute("status", status);
        if (status != null && status.equals("user deleted")) {
            session.removeAttribute("user");
        }
        return "sign_in";
    }

    @PostMapping
    public String signInUser(@RequestParam(value = "login") String login,
                             @RequestParam(value = "password") String password) {
        Optional<User> optionalUser = usersService.getUserByLoginAndPassword(login, password);
        if (optionalUser.isPresent()) {
            session.setAttribute("user", optionalUser.get());
            return "redirect:profile";
        }
        return "redirect:sign_in?status=wrong login or password";
    }

}

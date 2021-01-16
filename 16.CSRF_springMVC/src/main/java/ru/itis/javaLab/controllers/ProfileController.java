package ru.itis.javaLab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.itis.javaLab.models.User;
import ru.itis.javaLab.services.UsersService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("profile")
public class ProfileController {

    @Autowired
    UsersService usersService;
    @Autowired
    HttpSession session;

    @GetMapping
    public String getProfilePage(Model model) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user_id", user.getId());
        return "profile";
    }

    @PostMapping("delete")
    public String deleteUser(@RequestParam("user_id") long userId) {
        usersService.deleteUserById(userId);
        return "redirect:/sign_in?status=user deleted";

    }

}

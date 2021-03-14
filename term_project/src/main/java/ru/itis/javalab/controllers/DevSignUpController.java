package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.javalab.dto.RegistrationForm;
import ru.itis.javalab.services.SignUpService;

import javax.validation.Valid;
import java.util.Objects;

@Controller
@RequestMapping("sign_up")
@Profile("dev")
public class DevSignUpController {

    @Autowired
    SignUpService signUpService;

    @GetMapping
    public String getSignUpPage(Model model) {
        model.addAttribute("regForm", new RegistrationForm());
        return "sign_up";
    }

    @PostMapping
    public String fakeSignUpUser(@Valid @ModelAttribute("regForm") RegistrationForm form,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().stream().anyMatch(error -> {
                if (Objects.requireNonNull(error.getCodes())[0].equals("regForm.EqualPasswords")) {
                    model.addAttribute("passwordMismatchMessage", error.getDefaultMessage());
                }
                return true;
            });
            model.addAttribute("regForm", form);
            return "sign_up";
        }
        else {
            signUpService.signUpUser(form);
            return "redirect:sign_up/success";
        }
    }

    @GetMapping("success")
    public String getSuccessPage() {
        return "dev_success_sign_up";
    }

}

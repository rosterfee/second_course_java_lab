package ru.itis.javalab.controllers.prod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.dto.RegistrationForm;
import ru.itis.javalab.exceptions.ResourceNotFoundException;
import ru.itis.javalab.models.User;
import ru.itis.javalab.services.SignUpService;
import ru.itis.javalab.services.UsersService;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;

@Controller
@Profile("prod")
@RequestMapping("sign_up")
public class ProdSignUpController {

    @Autowired
    SignUpService signUpService;

    @Autowired
    UsersService usersService;

    @GetMapping
    public String getSignUpPage(Model model) {
        model.addAttribute("regForm", new RegistrationForm());
        return "sign_up";
    }

    @PostMapping
    public String signUpUser(@Valid @ModelAttribute("regForm") RegistrationForm form,
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
            return "redirect:sign_up/confirm";
        }
    }

    @GetMapping("confirm")
    public String getSuccessPage() {
        return "sign_up_confirm_page";
    }

    @GetMapping("confirm/{confirm_code}")
    public String confirmMail(@PathVariable("confirm_code") String code) {

        Optional<User> optionalUser = usersService.getUserByConfirmCode(code);

        if (optionalUser.isPresent()) {

            User user = optionalUser.get();
            user.setStatus(User.Status.CONFIRMED);
            usersService.updateUser(user);

            return "success_confirmation";
        }
        else {
            throw new ResourceNotFoundException();
        }

    }

}

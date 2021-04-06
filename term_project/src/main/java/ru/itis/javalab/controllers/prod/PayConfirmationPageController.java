package ru.itis.javalab.controllers.prod;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("pay_confirmation")
public class PayConfirmationPageController {

    @GetMapping
    public String getPayConfirmationPage() {
        return "pay_confirmation";
    }

}

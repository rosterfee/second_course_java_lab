package ru.itis.javalab.controllers.prod;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("pay_offer")
public class PayOfferPageController {

    @GetMapping
    public String getPayOfferPage() {
        return "pay_offer";
    }

}

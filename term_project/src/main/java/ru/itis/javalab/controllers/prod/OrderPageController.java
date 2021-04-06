package ru.itis.javalab.controllers.prod;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.PermitAll;

@Controller
@RequestMapping("my_order")
public class OrderPageController {

    @GetMapping
    public String getOrderPage() {
        return "my_order";
    }

}

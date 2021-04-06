package ru.itis.javalab.controllers.prod;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/add_car")
public class CarAdditionController {

    @GetMapping
    public String getCarAdditionPage() {
        return "add_car";
    }

}

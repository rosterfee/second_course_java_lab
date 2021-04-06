package ru.itis.javalab.controllers.prod;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("car_profile")
public class CarProfileController {

    @GetMapping
    public String getCarProfilePage() {
        return "car_profile";
    }

}

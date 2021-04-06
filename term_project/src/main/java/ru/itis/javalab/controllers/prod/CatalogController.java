package ru.itis.javalab.controllers.prod;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.PermitAll;

@Controller
@RequestMapping("catalog")
public class CatalogController {

    @GetMapping
    public String getCatalogPage() {
        return "catalog";
    }

}

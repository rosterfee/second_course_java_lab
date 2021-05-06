package ru.itis.javalab.web.controllers.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.javalab.api.dtos.UserDto;
import ru.itis.javalab.impl.entities.User;
import ru.itis.javalab.web.security.details.UserDetailsImpl;

@RestController
@RequestMapping("methods")
public class ApiController {

    @GetMapping(value = "user_info", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserInfo(
            @AuthenticationPrincipal User user) {

        return ResponseEntity.ok(user);

    }

}

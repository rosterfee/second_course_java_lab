package ru.itis.javalab.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.api.dtos.UserDto;
import ru.itis.javalab.api.services.UsersService;

@RestController
@RequestMapping("methods")
public class ApiMethodsController {

    @Autowired
    private UsersService usersService;

    @GetMapping(value = "user_info", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getUserInfo(@AuthenticationPrincipal UserDto user) {

        return ResponseEntity.ok(user);

    }

    @PostMapping("ban/{user_id}")
    public ResponseEntity<?> banUser(@PathVariable("user_id") Long userId) {
        System.out.println("hello");
        usersService.banUser(userId);
        return ResponseEntity.ok().build();
    }

}

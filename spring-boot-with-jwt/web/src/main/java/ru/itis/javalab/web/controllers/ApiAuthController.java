package ru.itis.javalab.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.api.dtos.JwtTokens;
import ru.itis.javalab.api.dtos.LoginFormDto;
import ru.itis.javalab.api.dtos.SignUpFormDto;
import ru.itis.javalab.api.dtos.UserDto;
import ru.itis.javalab.api.services.SignUpService;
import ru.itis.javalab.api.services.UsersService;
import ru.itis.javalab.impl.services.JwtAuthenticationService;
import ru.itis.javalab.web.validation.dtos.ValidationErrors;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("auth")
public class ApiAuthController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private JwtAuthenticationService jwtAuthenticationService;

    @PostMapping(value = "sign_up", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ValidationErrors> signUpUser(@Valid @RequestBody SignUpFormDto form,
                                                        BindingResult bindingResult) {

        ValidationErrors validationErrors = null;

        if (bindingResult.hasErrors()) {

            Map<String, String> fieldsErrors = new HashMap<>();
            List<String> globalErrors = new ArrayList<>();

            bindingResult.getFieldErrors().forEach(fieldError -> {
                fieldsErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
            });
            bindingResult.getGlobalErrors().forEach(globalError -> {
                globalErrors.add(globalError.getDefaultMessage());
            });

            validationErrors = ValidationErrors.builder()
                    .fieldsErrors(fieldsErrors)
                    .globalErrors(globalErrors)
                    .build();
        }

        if (signUpService.userWithSuchEmailExists(form.getEmail())) {
            if (validationErrors == null) {
                validationErrors = new ValidationErrors(new HashMap<>(), new ArrayList<>());
            }
            validationErrors.getGlobalErrors().add("User with such email already exists");
        }

        if (validationErrors == null) {
            signUpService.signUpUser(form);
        }

        if (validationErrors == null) {
            return ResponseEntity.ok().build();
        }
        else return ResponseEntity.ok(validationErrors);

    }

    @PostMapping(value = "login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtTokens> authenticate(@RequestBody LoginFormDto loginFormDto) {

        Optional<UserDto> optionalUserDto = usersService.getUserByEmailAndPassword(
                loginFormDto.getEmail(),
                loginFormDto.getPassword());

        JwtTokens jwtTokens = null;
        if (optionalUserDto.isPresent()) {
            jwtTokens = jwtAuthenticationService.createTokens(optionalUserDto.get());
        }
        return ResponseEntity.of(Optional.ofNullable(jwtTokens));
    }

}

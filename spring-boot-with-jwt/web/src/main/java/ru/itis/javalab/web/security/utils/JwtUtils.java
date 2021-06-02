package ru.itis.javalab.web.security.utils;

import com.auth0.jwt.interfaces.DecodedJWT;
import ru.itis.javalab.api.enums.Role;
import ru.itis.javalab.impl.entities.User;
import ru.itis.javalab.web.security.authentications.JwtAuthentication;
import ru.itis.javalab.web.security.details.UserDetailsImpl;

public class JwtUtils {

    public static JwtAuthentication createJwtAuthentication(DecodedJWT decodedJWT) {

        JwtAuthentication jwtAuthentication = new JwtAuthentication();

        User user = User.builder()
                .id(decodedJWT.getClaim("id").asLong())
                .email(decodedJWT.getClaim("email").asString())
                .name(decodedJWT.getClaim("name").asString())
                .role(Role.valueOf(decodedJWT.getClaim("role").asString()))
                    .build();

        UserDetailsImpl userDetails = new UserDetailsImpl(user);
        jwtAuthentication.setUserDetails(userDetails);

        return jwtAuthentication;
    }

}

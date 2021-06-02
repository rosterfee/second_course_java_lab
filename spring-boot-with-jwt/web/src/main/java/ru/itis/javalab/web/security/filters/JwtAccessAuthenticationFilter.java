package ru.itis.javalab.web.security.filters;

import com.auth0.jwt.*;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;
import ru.itis.javalab.web.security.authentications.JwtAuthentication;
import ru.itis.javalab.web.security.utils.JwtUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAccessAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        String accessTokenValue = httpServletRequest.getHeader("access-token");
        JWTVerifier verifier = JWT.require(
                Algorithm.HMAC256("damir_habirovich")).build();
        try {
            DecodedJWT decodedJWT = verifier.verify(accessTokenValue);
            JwtAuthentication jwtAuthentication = JwtUtils.createJwtAuthentication(decodedJWT);
            SecurityContextHolder.getContext().setAuthentication(jwtAuthentication);

        } catch (JWTVerificationException e) {
            if (!(e instanceof TokenExpiredException)) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Wrong token", e);
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
}

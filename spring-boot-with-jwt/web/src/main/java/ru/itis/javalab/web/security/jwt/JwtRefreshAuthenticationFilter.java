package ru.itis.javalab.web.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;
import ru.itis.javalab.api.dtos.JwtTokens;
import ru.itis.javalab.api.dtos.UserDto;
import ru.itis.javalab.api.exceptions.InvalidRefreshTokenException;
import ru.itis.javalab.api.services.UsersService;
import ru.itis.javalab.impl.entities.RefreshToken;
import ru.itis.javalab.impl.repositories.RefreshTokenRepository;
import ru.itis.javalab.impl.services.JwtAuthenticationService;
import ru.itis.javalab.impl.services.RefreshTokenService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtRefreshAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private JwtAuthenticationService jwtAuthenticationService;

    @Autowired
    private UsersService usersService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        System.out.println("refresh working");

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        System.out.println(authentication);

        if (authentication == null) {

            String refreshTokenUUID = httpServletRequest.getHeader("refresh-token");
            Optional<RefreshToken> optionalRefreshToken = refreshTokenService
                    .getByUUID(refreshTokenUUID);

            if (optionalRefreshToken.isPresent()) {
                RefreshToken refreshToken = optionalRefreshToken.get();

                if (refreshToken.getExpires_at().getTime() < new Date().getTime())
                    httpServletResponse.sendError(403, "Refresh token expired");
                else {

                    refreshTokenService.delete(refreshToken);

                    UserDto userDto = usersService.getById(refreshToken.getUserId());
                    JwtTokens jwtTokens = jwtAuthenticationService.createTokens(userDto);
                    httpServletResponse.setHeader("access-token",
                            jwtTokens.getAccessToken());
                    httpServletResponse.setHeader("refresh-token",
                            jwtTokens.getRefreshToken());

                    authentication = new JwtAuthentication(userDto.getEmail());
                    securityContext.setAuthentication(authentication);
                    System.out.println("auth: " + securityContext.getAuthentication());
                }
            }
            else httpServletResponse.sendError(403, "Invalid refresh token");
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}

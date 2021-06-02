package ru.itis.javalab.web.security.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.javalab.api.dtos.JwtTokens;
import ru.itis.javalab.api.dtos.UserDto;
import ru.itis.javalab.api.services.UsersService;
import ru.itis.javalab.impl.entities.RefreshToken;
import ru.itis.javalab.impl.services.JwtAuthenticationService;
import ru.itis.javalab.impl.services.RefreshTokenService;
import ru.itis.javalab.web.security.authentications.JwtAuthentication;
import ru.itis.javalab.web.security.utils.JwtUtils;

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

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        if (authentication == null) {

            String refreshTokenUUID = httpServletRequest.getHeader("refresh-token");
            Optional<RefreshToken> optionalRefreshToken = refreshTokenService
                    .getByUUID(refreshTokenUUID);

            if (optionalRefreshToken.isPresent()) {
                RefreshToken refreshToken = optionalRefreshToken.get();

                if (refreshToken.getExpires_at().getTime() < new Date().getTime()) {
                    httpServletResponse.sendError(403, "Refresh token expired");
                }
                else {
                    refreshTokenService.delete(refreshToken);

                    UserDto userDto = usersService.getById(refreshToken.getUserId());
                    JwtTokens jwtTokens = jwtAuthenticationService.createTokens(userDto);

                    String accessToken = jwtTokens.getAccessToken();
                    httpServletResponse.setHeader("access-token", accessToken);
                    httpServletResponse.setHeader("refresh-token", jwtTokens.getRefreshToken());

                    JWTVerifier verifier = JWT
                            .require(Algorithm.HMAC256("damir_habirovich"))
                                .build();
                    DecodedJWT decodedJWT = verifier.verify(accessToken);

                    JwtAuthentication jwtAuthentication = JwtUtils.createJwtAuthentication(decodedJWT);
                    securityContext.setAuthentication(jwtAuthentication);
                }
            }
            else httpServletResponse.sendError(403, "Invalid refresh token");
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}

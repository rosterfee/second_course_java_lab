package ru.itis.javalab.web.security.hadlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import ru.itis.javalab.api.services.BlackListService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class BlackListLogoutHandler implements LogoutHandler {

    @Autowired
    private BlackListService blackListService;

    @Override
    public void logout(HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse,
                       Authentication authentication) {

        String token = httpServletRequest.getHeader("access-token");
        System.out.println("token: " + token);
        blackListService.addToBlackList(token);
    }
}

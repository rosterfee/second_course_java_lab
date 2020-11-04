package ru.itis.javalab.filters;

import ru.itis.javalab.models.User;
import ru.itis.javalab.services.UsersService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@WebFilter("/profile")
public class AuthFilter implements Filter {

    UsersService usersService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        usersService = (UsersService) filterConfig.getServletContext().getAttribute("usersService");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        Cookie[] cookies = req.getCookies();
        Cookie authCookie = null;
        for (Cookie cookie: cookies) {
            if (cookie.getName().equals("auth")) {
                authCookie = cookie;
            }
        }

        if (authCookie != null) {

            Optional<User> optionalUser = usersService.getUserByUUID(UUID.fromString(authCookie.getValue()));
            if (optionalUser.isPresent()) {
                filterChain.doFilter(req, resp);
            }
            else {
                resp.sendRedirect(req.getContextPath() + "/sign_in");
            }

        }
        else {
            resp.sendRedirect(req.getContextPath() + "/sign_in");
        }
    }

    @Override
    public void destroy() {

    }
}

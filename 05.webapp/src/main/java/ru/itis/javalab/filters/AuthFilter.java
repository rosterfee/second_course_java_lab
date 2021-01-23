package ru.itis.javalab.filters;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.javalab.config.AppConfiguration;
import ru.itis.javalab.models.User;
import ru.itis.javalab.services.UsersService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@WebFilter("/profile")
public class AuthFilter implements Filter {

    UsersService usersService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);
        usersService = applicationContext.getBean(UsersService.class);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            Cookie[] cookies = req.getCookies();
            Cookie authCookie = null;
            for (Cookie cookie: cookies) {
                if (cookie.getName().equals("auth")) {
                    authCookie = cookie;
                    break;
                }
            }
            if (authCookie != null) {

                UUID uuid = UUID.fromString(authCookie.getValue());
                Optional<User> optionalUser = usersService.getUserByUUID(uuid);

                if (optionalUser.isPresent()) {
                    user = optionalUser.get();
                    session.setAttribute("user", user);
                }

            }
        }

        user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
        else {
            filterChain.doFilter(req, resp);
        }

    }

    @Override
    public void destroy() {

    }
}

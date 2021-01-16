package ru.itis.javalab.servlets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.javalab.config.AppConfiguration;
import ru.itis.javalab.models.User;
import ru.itis.javalab.services.PasswordEncoderService;
import ru.itis.javalab.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LogInServlet extends HttpServlet {

    UsersService usersService;
    PasswordEncoderService passwordEncoderService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);
        usersService = applicationContext.getBean(UsersService.class);
        passwordEncoderService = applicationContext.getBean(PasswordEncoderService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("pages/login.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        Optional<User> optionalUser= usersService.getUserByLogin(login);

        if (optionalUser.isPresent()) {

            User user = optionalUser.get();
            String hashedPassword = user.getPassword();

            if (passwordEncoderService.matches(password, hashedPassword)) {

                Cookie cookie = new Cookie("auth", user.getUuid().toString());
                cookie.setMaxAge(60 * 60 * 24);
                resp.addCookie(cookie);

                HttpSession session = req.getSession();
                session.setAttribute("user", user);

                resp.getWriter().write("successfully logged in");

            }
            else {
                req.getRequestDispatcher("pages/login.ftl").forward(req, resp);
            }
        }
        else {
            req.getRequestDispatcher("pages/login.ftl").forward(req, resp);
        }
    }
}

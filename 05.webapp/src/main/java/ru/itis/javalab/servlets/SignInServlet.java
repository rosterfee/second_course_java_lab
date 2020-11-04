package ru.itis.javalab.servlets;

import ru.itis.javalab.models.User;
import ru.itis.javalab.services.UsersService;
import ru.itis.javalab.utils.MD5PasswordHasher;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/sign_in")
public class SignInServlet extends HttpServlet {

    UsersService usersService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        usersService = (UsersService) config.getServletContext().getAttribute("usersService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("pages/sign_in.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        String hashedPassword = MD5PasswordHasher.getHashedPassword(password);

        Optional<User> optionalUser = usersService.getUserByLoginAndPassword(login, hashedPassword);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Cookie cookie = new Cookie("auth", user.getUuid().toString());
            cookie.setMaxAge(60*60*24);
            resp.addCookie(cookie);
        }
        else {
            req.getRequestDispatcher("pages/sign_in").forward(req, resp);
        }
    }
}

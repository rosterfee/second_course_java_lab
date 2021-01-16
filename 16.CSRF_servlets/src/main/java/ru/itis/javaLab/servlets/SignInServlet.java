package ru.itis.javaLab.servlets;

import ru.itis.javaLab.models.User;
import ru.itis.javaLab.repositories.UsersRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/sign_in")
public class SignInServlet extends HttpServlet {

    UsersRepository usersRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        usersRepository = (UsersRepository) servletContext.getAttribute("usersRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("sign_in.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        Optional<User> optionalUser = usersRepository.findUser(login, password);
        if (optionalUser.isPresent()) {
            HttpSession session = req.getSession();
            session.setAttribute("user", optionalUser.get());
            resp.sendRedirect("/profile");
        }
        else {
            resp.sendRedirect("/sign_in");
        }

    }
}

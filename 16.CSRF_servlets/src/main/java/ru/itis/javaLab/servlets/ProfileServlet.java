package ru.itis.javaLab.servlets;

import ru.itis.javaLab.models.User;
import ru.itis.javaLab.repositories.UsersRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    UsersRepository usersRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        usersRepository = (UsersRepository) config.getServletContext().getAttribute("usersRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        req.setAttribute("user_id", user.getId());
        req.getRequestDispatcher("profile.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null && action.equals("delete")) {
            Long id = Long.parseLong(req.getParameter("user_id"));
            usersRepository.deleteUser(id);
            req.getSession().removeAttribute("user");

            resp.sendRedirect("/sign_in?action=User successfully deleted");
        }
    }
}

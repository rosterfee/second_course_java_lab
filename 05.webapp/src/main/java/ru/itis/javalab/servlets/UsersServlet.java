package ru.itis.javalab.servlets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import ru.itis.javalab.config.AppConfiguration;
import ru.itis.javalab.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * 08.10.2020
 * 05. WebApp
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 * */

@WebServlet(urlPatterns = "/users")
public class UsersServlet extends HttpServlet {

    private UsersService usersService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);
        usersService = applicationContext.getBean(UsersService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Юзеры, которым 19 лет:");
        System.out.println(usersService.getAllUsersByAge(19));

        System.out.println("--------------------------------");

        System.out.println("Все юзеры:");
        System.out.println(usersService.getAllUsers());
    }
}


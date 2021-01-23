package ru.itis.javaLab.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

public class CsrfInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("csrf interceptor is working");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        HttpSession session = request.getSession();

        if (request.getMethod().equals("GET")) {
            String _csrf_token = UUID.randomUUID().toString();
            session.setAttribute("_csrf_token", _csrf_token);
            request.setAttribute("_csrf_token", _csrf_token);
        }
        else if (request.getMethod().equals("POST")) {
            if (!session.getAttribute("_csrf_token").equals(request.getParameter("_csrf_token"))) {
                response.sendRedirect("/sign_in");
            } else request.getRequestDispatcher("forbidden").forward(request, response);
        }
    }

}

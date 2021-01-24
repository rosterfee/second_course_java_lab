package ru.itis.javaLab.filters;

import org.springframework.http.converter.json.GsonBuilderUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

@WebFilter("/*")
public class CsrfFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("csrf filter is working");

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        HttpSession session = httpServletRequest.getSession();

        String method = httpServletRequest.getMethod();
        if (method.equals("GET")) {

            String _csrf_token = UUID.randomUUID().toString();
            httpServletRequest.setAttribute("_csrf_token", _csrf_token);
            session.setAttribute("_csrf_token", _csrf_token);

            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
        else if (method.equals("POST")) {

            if (session.getAttribute("_csrf_token").equals(httpServletRequest.getParameter("_csrf_token"))) {
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            } else httpServletRequest.getRequestDispatcher("forbidden.ftl").forward(httpServletRequest, httpServletResponse);
        }

    }
}

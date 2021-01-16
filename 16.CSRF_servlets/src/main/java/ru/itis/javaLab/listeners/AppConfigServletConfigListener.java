package ru.itis.javaLab.listeners;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.itis.javaLab.repositories.UsersRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppConfigServletConfigListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext servletContext = sce.getServletContext();

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/javaLab_16.CSRF");
        hikariConfig.setUsername("postgres");
        hikariConfig.setPassword("gev56poison");
        hikariConfig.setMaximumPoolSize(2);
        HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);

        servletContext.setAttribute("usersRepository", new UsersRepository(hikariDataSource));

    }
}

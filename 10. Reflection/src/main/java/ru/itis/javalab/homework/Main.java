package ru.itis.javalab.homework;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.javalab.config.ApplicationConfig;

import javax.sql.DataSource;

public class Main {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        DataSource dataSource = applicationContext.getBean(DataSource.class);

        EntityManager entityManager = new EntityManager(dataSource);

        entityManager.createTable("account", Account.class);

        Account account = Account.builder()
                .id(5L)
                .firstName("Федя")
                .lastName("Букер")
                .isWorker(true)
                    .build();

        entityManager.save("account", account);

        System.out.println(entityManager.findById("account", Account.class, 1));
    }
}

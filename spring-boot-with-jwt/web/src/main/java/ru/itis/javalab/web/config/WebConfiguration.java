package ru.itis.javalab.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.itis.javalab.api.config.ApiConfiguration;
import ru.itis.javalab.impl.config.ImplConfiguration;

@Configuration
@Import({ApiConfiguration.class, ImplConfiguration.class})
@EnableJpaRepositories
public class WebConfiguration {

}

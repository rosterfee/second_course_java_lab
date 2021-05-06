package ru.itis.javalab.impl.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.javalab.api.config.ApiConfiguration;

@Configuration
@Import(ApiConfiguration.class)
@EnableJpaRepositories(basePackages = "ru.itis.javalab.impl.repositories")
@ComponentScan(basePackages = "ru.itis.javalab.impl")
public class ImplConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

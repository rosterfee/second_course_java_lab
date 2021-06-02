package ru.itis.javalab.web.security.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.itis.javalab.web.security.filters.JwtAccessAuthenticationFilter;
import ru.itis.javalab.web.security.filters.JwtBlackListFilter;
import ru.itis.javalab.web.security.hadlers.BlackListLogoutHandler;
import ru.itis.javalab.web.security.providers.JwtAuthenticationProvider;
import ru.itis.javalab.web.security.filters.JwtRefreshAuthenticationFilter;

@EnableWebSecurity
public class GlobalSecurityConfiguration {

    @Configuration
    @Order(1)
    public static class ApiAuthSecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Autowired
        @Qualifier("custom")
        private UserDetailsService userDetailsService;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .mvcMatcher("/auth/**")
                    .authorizeRequests()
                    .anyRequest().permitAll()
                        .and()
                    .csrf().disable()
                    .sessionManagement().disable();
        }
    }

    @Configuration
    @Order(2)
    public static class ApiMethodsSecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Autowired
        private JwtAccessAuthenticationFilter jwtAccessAuthenticationFilter;

        @Autowired
        private JwtRefreshAuthenticationFilter jwtRefreshAuthenticationFilter;

        @Autowired
        private JwtAuthenticationProvider jwtAuthenticationProvider;

        @Autowired
        private BlackListLogoutHandler blackListLogoutHandler;

        @Autowired
        private JwtBlackListFilter jwtBlackListFilter;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/methods/*")
                    .authorizeRequests()
                    .anyRequest().authenticated()
                        .and()
                    .logout()
                    .logoutUrl("/methods/log_out")
                    .addLogoutHandler(blackListLogoutHandler)
                        .and()
                    .addFilterAt(jwtBlackListFilter,
                            UsernamePasswordAuthenticationFilter.class)
                    .addFilterAfter(jwtAccessAuthenticationFilter,
                            JwtBlackListFilter.class)
                    .addFilterAfter(jwtRefreshAuthenticationFilter,
                            JwtAccessAuthenticationFilter.class)
                    .csrf().disable()
                    .sessionManagement().disable();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(jwtAuthenticationProvider);
        }

        @Bean
        public FilterRegistrationBean<JwtAccessAuthenticationFilter> jwtAccessFilter() {
            FilterRegistrationBean<JwtAccessAuthenticationFilter> filterRegistrationBean =
                    new FilterRegistrationBean<>();
            filterRegistrationBean.setFilter(jwtAccessAuthenticationFilter);
            filterRegistrationBean.addUrlPatterns("/methods/*", "/methods/**");
            filterRegistrationBean.setOrder(1);
            return filterRegistrationBean;
        }

        @Bean
        public FilterRegistrationBean<JwtRefreshAuthenticationFilter> jwtRefreshFilter() {
            FilterRegistrationBean<JwtRefreshAuthenticationFilter> filterRegistrationBean =
                    new FilterRegistrationBean<>();
            filterRegistrationBean.setFilter(jwtRefreshAuthenticationFilter);
            filterRegistrationBean.addUrlPatterns("/methods/*", "/methods/**");
            filterRegistrationBean.setOrder(2);
            return filterRegistrationBean;
        }
    }

}

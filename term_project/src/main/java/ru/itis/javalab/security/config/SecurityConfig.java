package ru.itis.javalab.security.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.javalab.security.handlers.UserAuthenticationSuccessHandler;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier(value = "customUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserAuthenticationSuccessHandler userAuthenticationSuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/profile").authenticated()
                .antMatchers("/add_car").hasAuthority("ADMIN")
                .antMatchers("/my_order").authenticated()
                .antMatchers("/my_order").hasAuthority("CONFIRMED")
                .antMatchers("/pay_confirmation").authenticated()
                .antMatchers("/pay_confirmation").hasAuthority("CONFIRMED")
                .antMatchers("pay_offer").authenticated()
                .antMatchers("pay_offer").hasAuthority("CONFIRMED")
                    .and()
                .formLogin()
                .loginPage("/sign_in")
                .usernameParameter("email")
                .passwordParameter("password")
                .successHandler(userAuthenticationSuccessHandler)
                .failureUrl("/main")
                    .and()
                .logout()
                .logoutUrl("/log_out")
                .logoutSuccessUrl("/sign_in")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
    }
}

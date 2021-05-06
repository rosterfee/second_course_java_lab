package ru.itis.javalab.web.security.details;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.javalab.impl.entities.User;
import ru.itis.javalab.impl.repositories.UsersRepository;

import java.util.Optional;

@Service(value = "custom")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = usersRepository.getByEmail(email);
        if (optionalUser.isPresent()) {
            return new UserDetailsImpl(optionalUser.get());
        }
        else throw new UsernameNotFoundException("User not found");
    }

}

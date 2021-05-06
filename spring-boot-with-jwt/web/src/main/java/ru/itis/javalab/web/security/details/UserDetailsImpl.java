package ru.itis.javalab.web.security.details;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.itis.javalab.api.dtos.UserDto;
import ru.itis.javalab.impl.entities.User;

import javax.jws.WebParam;
import java.util.Collection;
import java.util.Collections;

@Data
public class UserDetailsImpl implements UserDetails {

    private User user;
    private UserDto userDto;

    public UserDetailsImpl(User user) {
        this.user = user;
        ModelMapper modelMapper = new ModelMapper();
        userDto = modelMapper.map(user, UserDto.class);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(user.getRole().toString()));
    }

    @Override
    public String getPassword() {
        return user.getHashedPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

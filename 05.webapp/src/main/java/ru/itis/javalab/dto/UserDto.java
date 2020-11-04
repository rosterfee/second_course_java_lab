package ru.itis.javalab.dto;

import lombok.Builder;
import lombok.Data;
import ru.itis.javalab.models.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class UserDto {

    private String firstName;
    private String lastName;

    public static UserDto from(User user) {
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                    .build();
    }

    public static List<UserDto> from(List<User> users) {
        return users.stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
    }


}

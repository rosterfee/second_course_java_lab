package ru.itis.javalab.impl.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.api.dtos.UserDto;
import ru.itis.javalab.api.services.RedisService;
import ru.itis.javalab.api.services.UsersService;
import ru.itis.javalab.impl.entities.RedisUser;
import ru.itis.javalab.impl.entities.User;
import ru.itis.javalab.impl.repositories.RedisJwtUserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class RedisJwtUserService implements RedisService {

    @Autowired
    private RedisJwtUserRepository redisJwtUserRepository;

    @Autowired
    private UsersService usersService;

    @Override
    public void addTokenToUser(String token, UserDto userDto) {

        Long redisId = userDto.getRedisId();
        if (redisId == null) {

            List<String> tokens = new ArrayList<>();
            tokens.add(token);

            RedisUser redisUser = RedisUser.builder()
                    .userId(userDto.getId())
                    .tokens(tokens)
                        .build();
            redisJwtUserRepository.save(redisUser);
            usersService.setRedisId(userDto.getId(), redisUser.getId());
        }
        else {
            RedisUser redisUser = redisJwtUserRepository.findById(redisId).get();
            redisUser.getTokens().add(token);
            redisJwtUserRepository.save(redisUser);
        }
    }


}

package ru.itis.javalab.impl.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.javalab.api.dtos.UserDto;
import ru.itis.javalab.api.services.UsersService;
import ru.itis.javalab.impl.entities.RedisUser;
import ru.itis.javalab.impl.entities.User;
import ru.itis.javalab.impl.repositories.RedisJwtUserRepository;
import ru.itis.javalab.impl.repositories.UsersRepository;

import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedisJwtUserRepository redisJwtUserRepository;

    @Autowired
    private RedisJwtBlackListService redisJwtBlackListService;

    @Override
    public Optional<UserDto> getUserByEmailAndPassword(String email, String password) {

        UserDto userDto = null;

        Optional<User> optionalUser = usersRepository.getByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(password, user.getHashedPassword())) {
                userDto = modelMapper.map(user, UserDto.class);
            }
        }

        return Optional.ofNullable(userDto);

    }

    @Override
    public UserDto getById(long id) {
        return modelMapper.map(usersRepository.getById(id).get(), UserDto.class);
    }

    @Override
    public void banUser(Long id) {
        User user = usersRepository.getById(id).orElseThrow(IllegalArgumentException::new);
        RedisUser redisUser = redisJwtUserRepository.findById(user.getRedisId()).get();
        redisJwtBlackListService.addAllTokensToBlackList(redisUser.getTokens());
    }

    @Override
    public void setRedisId(Long userId, Long redisId) {
        usersRepository.setRedisId(redisId, userId);
    }

}

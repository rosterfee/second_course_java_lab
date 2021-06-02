package ru.itis.javalab.impl.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import ru.itis.javalab.api.repos.BlackListRepository;

@Repository
public class RedisJwtBlackListRepository implements BlackListRepository {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void addToBlackList(String jwt) {
        System.out.println("repo");
        redisTemplate.opsForValue().set(jwt, "");
    }

    @Override
    public Boolean exists(String jwt) {
        return redisTemplate.hasKey(jwt);
    }

}

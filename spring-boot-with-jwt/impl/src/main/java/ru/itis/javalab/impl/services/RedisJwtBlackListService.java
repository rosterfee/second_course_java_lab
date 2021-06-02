package ru.itis.javalab.impl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.api.repos.BlackListRepository;
import ru.itis.javalab.api.services.BlackListService;

import java.util.List;

@Service
public class RedisJwtBlackListService implements BlackListService {

    @Autowired
    private BlackListRepository blackListRepository;

    @Override
    public void addToBlackList(String jwt) {
        blackListRepository.addToBlackList(jwt);
    }

    @Override
    public Boolean tokenExists(String jwt) {
        return blackListRepository.exists(jwt);
    }

    @Override
    public void addAllTokensToBlackList(List<String> tokens) {
        tokens.forEach(token -> {
            blackListRepository.addToBlackList(token);
        });
    }

}

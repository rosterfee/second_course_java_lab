package ru.itis.javalab.api.services;

import java.util.List;

public interface BlackListService {

    void addToBlackList(String token);

    Boolean tokenExists(String token);

    void addAllTokensToBlackList(List<String> tokens);

}

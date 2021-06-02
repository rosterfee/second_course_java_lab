package ru.itis.javalab.api.repos;

public interface BlackListRepository {

    void addToBlackList(String token);

    Boolean exists(String token);

}

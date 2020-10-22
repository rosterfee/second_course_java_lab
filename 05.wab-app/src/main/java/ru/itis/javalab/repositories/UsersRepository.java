package ru.itis.javalab.repositories;

import ru.itis.javalab.models.User;

import java.util.List;
import java.util.Optional;

/**
 * 08.10.2020
 * 05. WebApp
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface UsersRepository extends CrudRepository<User> {
    List<User> findAllByAge(Integer age);
    Optional<User> findFirstByFirstnameAndLastname(String firstName, String lastName);
}


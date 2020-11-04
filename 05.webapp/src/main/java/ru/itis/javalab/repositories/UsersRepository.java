package ru.itis.javalab.repositories;

import ru.itis.javalab.models.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * 08.10.2020
 * 05. WebApp
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface UsersRepository extends CrudRepository<User> {

    List<User> findAllByAge(Integer age);

    Optional<User> findFirstByFirstNameAndLastName(String firstName, String lastName);

    Optional<User> findByLoginAndPassword(String login, String password);

    Optional<User> findByUUID(UUID uuid);

    Optional<String> findPasswordByLogin(String login);

    Optional<User> findUserByLogin(String login);
}


package ru.itis.javalab.impl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.javalab.impl.entities.User;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {

    Optional<User> getByEmail(String email);

    boolean existsByEmail(String email);

    Optional<User> getById(long id);

}

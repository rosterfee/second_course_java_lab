package ru.itis.javalab.impl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.javalab.impl.entities.User;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {

    Optional<User> getByEmail(String email);

    boolean existsByEmail(String email);

    Optional<User> getById(long id);

    @Transactional
    @Modifying
    @Query(value = "update User user set user.redisId = :redisId where user.id = :userId")
    void setRedisId(@Param("redisId") Long redisId,
                    @Param("userId") Long userId);

}

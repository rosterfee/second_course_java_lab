package ru.itis.javalab.impl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.javalab.impl.entities.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> getByUserId(long userId);

    Optional<RefreshToken> getByUuid(String uuid);

}

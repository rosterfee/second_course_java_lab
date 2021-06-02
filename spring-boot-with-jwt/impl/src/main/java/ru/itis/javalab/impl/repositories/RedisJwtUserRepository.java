package ru.itis.javalab.impl.repositories;

import org.springframework.data.keyvalue.repository.KeyValueRepository;
import ru.itis.javalab.impl.entities.RedisUser;

public interface RedisJwtUserRepository extends KeyValueRepository<RedisUser, Long> {

}

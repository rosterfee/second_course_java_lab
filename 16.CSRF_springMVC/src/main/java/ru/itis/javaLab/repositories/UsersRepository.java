package ru.itis.javaLab.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.itis.javaLab.models.User;

import javax.sql.DataSource;
import java.util.*;

@Repository
public class UsersRepository implements CrudRepository<User> {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;
    Map<String, Object> params;
    RowMapper<User> userRowMapper = (row, i) -> User.builder()
                .id(row.getLong("id"))
                .login(row.getString("login"))
                .password(row.getString("password"))
                    .build();

    //language=sql
    private static final String SQL_FIND_USER_BY_LOGIN_AND_PASSWORD = "select * from account where " +
            "login = :login and password = :password";

    //language=sql
    private static final String SQL_DELETE_USER_BY_ID = "delete from account where id = :id";

    public Optional<User> findUserByLoginAndPassword(String login, String password) {
        params = new HashMap<>();
        params.put("login", login);
        params.put("password", password);
        User user;
        try {
            user = jdbcTemplate.queryForObject(SQL_FIND_USER_BY_LOGIN_AND_PASSWORD, params, userRowMapper);
        } catch (EmptyResultDataAccessException e) {
            user = null;
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public List<User> findAll(int page, int size) {
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void deleteById(Long id) {
        params = new HashMap<>();
        params.put("id", id);
        jdbcTemplate.update(SQL_DELETE_USER_BY_ID, params);
    }

    @Override
    public void delete(User entity) {

    }
}
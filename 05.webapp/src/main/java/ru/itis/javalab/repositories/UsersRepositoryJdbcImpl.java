package ru.itis.javalab.repositories;

import ru.itis.javalab.models.User;

import javax.sql.DataSource;
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
public class UsersRepositoryJdbcImpl implements UsersRepository {

    //language=SQL
    private static final String SQL_SELECT_BY_AGE = "select * from account where age = ?";

    //language=SQL
    private static final String SQL_SELECT = "select * from account";

    //language=sql
    private static final String SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD = "select * from account where login = ? " +
            "and password = ?";

    //language=sql
    private static final String SQL_SELECT_USER_BY_UUID = "select * from account where uuid = ?";

    private SimpleJdbcTemplate template;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        template = new SimpleJdbcTemplate(dataSource);
    }

    private RowMapper<User> userRowMapper = resultSet -> User.builder()
            .id(resultSet.getLong("id"))
            .firstName(resultSet.getString("first_name"))
            .lastName(resultSet.getString("last_name"))
            .age(resultSet.getInt("age"))
            .login(resultSet.getString("login"))
            .password(resultSet.getString("password"))
            .uuid((UUID) resultSet.getObject("uuid"))
                .build();

    @Override
    public List<User> findAllByAge(Integer age) {
        return template.selectQuery(SQL_SELECT_BY_AGE, userRowMapper, age);
    }

    @Override
    public Optional<User> findFirstByFirstNameAndLastName(String firstName, String lastName) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        User user = template.queryForObject(SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD, userRowMapper, login, password);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByUUID(UUID uuid) {
        User user = template.queryForObject(SQL_SELECT_USER_BY_UUID, userRowMapper, uuid);
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        return template.selectQuery(SQL_SELECT, userRowMapper);
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

    }

    @Override
    public void delete(User entity) {

    }
}

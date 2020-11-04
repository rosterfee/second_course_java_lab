package ru.itis.javalab.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.itis.javalab.models.User;

import javax.sql.DataSource;
import java.util.*;

/**
 * 22.10.2020
 * 05. WebApp
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;




    //language=SQL
    private static final String SQL_SELECT_BY_ID = "select * from account where id = ?";

    //language=SQL
    private static final String SQL_SELECT_ALL_WITH_PAGES = "select * from account order by id limit :limit offset :offset ;";

    //language=SQL
    private static final String SQL_SELECT_ALL = "select * from account";

    //language=SQL
    private static final String SQL_SELECT_BY_AGE = "select * from account where age = ?";

    //language=sql
    private static final String SQL_SAVE_USER = "insert into account (first_name, last_name, age, login, password," +
            " uuid) values (:firstName, :lastName, :age, :login, :password, uuid_generate_v4())";

    //language=sql
    private static final String SQL_SELECT_FIRST_BY_FIRSTNAME_AND_LASTNAME = "select * from account limit 1 " +
            "where first_name = :firstName, last_name = :lastName";

    //language=sql
    private static final String SQL_SELECT_USER_BY_UUID = "select * from account where uuid = ?";

    //language=sql
    private static final String SQL_SELECT_USER_BY_LOGIN = "select * from account where login = ?";

    //language=sql
    private static final String SQL_UPDATE = "update account set first_name = :firstName, last_name = :lastName," +
            " age = :age, login = :login, password = :password";

    //language=sql
    private static final String SQL_DELETE_BY_ID = "delete from account where id = ?";

    //language=sql
    private static final String SQL_DELETE = "delete from account where first_name = :firstName, last_name = :lastName," +
            " age = :age, login = :login, password = :password";





    private RowMapper<User> userRowMapper = (row, i) -> User.builder()
            .id(row.getLong("id"))
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .age(row.getInt("age"))
            .login(row.getString("login"))
            .password(row.getString("password"))
            .uuid((UUID) row.getObject("uuid"))
                .build();





    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }






    @Override
    public List<User> findAllByAge(Integer age) {
        return jdbcTemplate.query(SQL_SELECT_BY_AGE, userRowMapper, age);
    }

    @Override
    public Optional<User> findFirstByFirstNameAndLastName(String firstName, String lastName) {

        Map<String, Object> params = new HashMap<>();
        params.put("firstName", firstName);
        params.put("lastName", lastName);

        User user;
        try {
            user = namedParameterJdbcTemplate.queryForObject(SQL_SELECT_FIRST_BY_FIRSTNAME_AND_LASTNAME, params,
                    userRowMapper);
        } catch (EmptyResultDataAccessException e) {
            user = null;
        }

        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByUUID(UUID uuid) {
        User user;
        try {
            user = jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_UUID, userRowMapper, uuid);
        } catch (EmptyResultDataAccessException e) {
            user = null;
        }

        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findUserByLogin(String login) {
        User user;
        try {
            user = jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_LOGIN, userRowMapper, login);
        } catch (EmptyResultDataAccessException e) {
            user = null;
        }

        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
    }

    @Override
    public List<User> findAll(int page, int size) {
        Map<String, Object> params = new HashMap<>();
        params.put("limit", size);
        params.put("offset", page * size);
        return namedParameterJdbcTemplate.query(SQL_SELECT_ALL_WITH_PAGES, params, userRowMapper);
    }

    @Override
    public Optional<User> findById(Long id) {
        User user;
        try {
            user = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, userRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            user = null;
        }

        return Optional.ofNullable(user);

    }

    @Override
    public void save(User entity) {
        Map<String, Object> params = getAllUserParamsFrom(entity);
        namedParameterJdbcTemplate.update(SQL_SAVE_USER, params);
    }

    @Override
    public void update(User entity) {
        Map<String, Object> params = getAllUserParamsFrom(entity);
        namedParameterJdbcTemplate.update(SQL_UPDATE, params);
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }

    @Override
    public void delete(User entity) {
        Map<String, Object> params = getAllUserParamsFrom(entity);
        namedParameterJdbcTemplate.update(SQL_DELETE, params);
    }

    public static Map<String, Object> getAllUserParamsFrom(User entity) {

        Map<String, Object> params = new HashMap<>();

        params.put("firstName", entity.getFirstName());
        params.put("lastName", entity.getLastName());
        params.put("age", entity.getAge());
        params.put("login", entity.getLogin());
        params.put("password", entity.getPassword());

        return params;
    }
}


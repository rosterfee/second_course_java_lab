package ru.itis.javaLab.repositories;

import ru.itis.javaLab.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UsersRepository {

    DataSource dataSource;

    RowMapper<User> userRowMapper = resultSet -> User.builder()
                .id(resultSet.getLong("id"))
                .login(resultSet.getString("login"))
                .password(resultSet.getString("password"))
                    .build();

    //language=sql
    private static final String SQL_FIND_USER_BY_LOGIN_AND_PASSWORD = "select * from account where login = ? " +
            "and password = ?";

    //language=sql
    private static final String SQL_DELETE_USER = "delete from account where id = ?";

    public UsersRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Optional<User> findUser(String login, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN_AND_PASSWORD);

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = userRowMapper.mapRow(resultSet);
            } else user = null;

            return Optional.ofNullable(user);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    //ignore
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    //ignore
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    //ignore
                }
            }
        }
        return null;
    }

    public void deleteUser(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE_USER);

            preparedStatement.setLong(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    //ignore
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    //ignore
                }
            }
        }
    }

}
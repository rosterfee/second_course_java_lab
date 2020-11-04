package ru.itis.javalab.repositories.old;

import ru.itis.javalab.repositories.old.RowMapper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 08.10.2020
 * 05. WebApp
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */

public class SimpleJdbcTemplate {

    private DataSource dataSource;

    public SimpleJdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public <T> List<T> selectQuery(String sql, RowMapper<T> rowMapper, Object ... args) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            if (args != null) {
                for (int i = 0; i < args.length; i++) {
                    preparedStatement.setObject(i + 1, args[i]);
                }
            }

            resultSet = preparedStatement.executeQuery();

            List<T> resultList = new ArrayList<>();
            while (resultSet.next()) {
                resultList.add(rowMapper.mapRow(resultSet));
            }

            return resultList;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ignore) { }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ignore) { }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignore) { }
            }
        }

        return null;
    }

    public <T> T queryForObject(String sql, RowMapper<T> rowMapper, Object ... args) {

        List<T> list = selectQuery(sql, rowMapper, args);
        int listSize = list.size();

        if (listSize == 1) {
            return list.get(0);
        }
        else if (listSize == 0) {
            return null;
        }
        else throw new IllegalStateException();
    }

}


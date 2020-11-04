package ru.itis.javalab.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 08.10.2020
 * 05. WebApp
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface RowMapper<T> {
    T mapRow(ResultSet resultSet) throws SQLException;
}


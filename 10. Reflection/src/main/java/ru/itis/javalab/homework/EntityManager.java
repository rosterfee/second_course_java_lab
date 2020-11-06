package ru.itis.javalab.homework;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

public class EntityManager {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private Map<Class<?>, String> typeMapper = new HashMap<Class<?>, String>();

    public EntityManager(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("C:\\Users\\dakal\\programming\\IdeaProjects\\" +
                    "second_course_java_lab\\10. Reflection\\src\\main\\resources\\typeMapping.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        typeMapper.put(String.class, properties.getProperty("String"));
        typeMapper.put(Integer.class, properties.getProperty("Integer"));
        typeMapper.put(Long.class, properties.getProperty("Long"));
        typeMapper.put(Boolean.class, properties.getProperty("Boolean"));
    }

    // createTable("account", User.class);
    public <T> void createTable(String tableName, Class<T> entityClass) {

        final StringBuilder SQL_CREATE_TABLE = new StringBuilder();
        SQL_CREATE_TABLE.append("create table ").append(tableName).append(" (");

        List<Field> fields = Arrays.asList(entityClass.getDeclaredFields());
        for (Field field: fields) {

            SQL_CREATE_TABLE.append(field.getName()).append(" ");

            if (field.getName().equals("id")) {
                SQL_CREATE_TABLE.append("bigserial primary key");
            }
            else {
                SQL_CREATE_TABLE.append(typeMapper.get(field.getType()));
            }

            if (fields.indexOf(field) != fields.size() - 1) {
                SQL_CREATE_TABLE.append(",");
            }
        }
        SQL_CREATE_TABLE.append(");");

        final String FINAL_SQL_CREATE_TABLE = SQL_CREATE_TABLE.toString();

        jdbcTemplate.execute(FINAL_SQL_CREATE_TABLE);
    }

    public void save(String tableName, Object entity) {

        Class<?> classOfEntity = entity.getClass();

        final StringBuilder SQL_SAVE = new StringBuilder();
        SQL_SAVE.append("insert into ").append(tableName).append(" (");

        List<Field> fields = Arrays.asList(classOfEntity.getDeclaredFields());
        for (Field field: fields) {
            if (!field.getName().equals("id")) {
                SQL_SAVE.append(field.getName());
            }
            if (fields.indexOf(field) != fields.size() - 1 && !field.getName().equals("id")) {
                SQL_SAVE.append(", ");
            }
        }
        SQL_SAVE.append(") values (");

        for (Field field: fields) {
            if (!field.getName().equals("id")) {
                field.setAccessible(true);
                try {
                    SQL_SAVE.append("'").append(field.get(entity)).append("'");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            if (fields.indexOf(field) != fields.size() - 1 && !field.getName().equals("id")) {
                SQL_SAVE.append(", ");
            }
        }
        SQL_SAVE.append(");");

        final String FINAL_SQL_SAVE = SQL_SAVE.toString();
        jdbcTemplate.update(FINAL_SQL_SAVE);
    }

    // User user = entityManager.findById("account", User.class, Long.class, 10L);
    public <T, ID> T findById(String tableName, final Class<T> resultType, ID idValue) {

        RowMapper<T> rowMapper = (row, i) -> {
            Field[] fields = resultType.getDeclaredFields();
            try {
                T t = resultType.newInstance();
                for (Field field: fields) {
                    field.setAccessible(true);
                    field.set(t, (row.getObject(field.getName())));
                }

                return t;

            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }

            return null;
        };

        //language=sql
        final String SQL_SELECT_BY_ID = "select * from " + tableName + " where id = ?";
        T t;
        try {
            t = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, rowMapper, idValue);
        } catch (EmptyResultDataAccessException e) {
            t = null;
        }

        return t;
    }
}


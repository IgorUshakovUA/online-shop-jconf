package com.study.shop.dao.jdbc;

import com.study.shop.dao.UserDao;
import com.study.shop.dao.jdbc.mapper.UserRowMapper;
import com.study.shop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcUserDao implements UserDao {
    private static final String GET_USER_BY_NAME_AND_PASSWORD_SQL = "SELECT username, password, salt, user_role FROM app_user WHERE username = UPPER(?) AND password = MD5(CONCAT(?,salt))";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcUserDao() {

    }

    @Override
    public User getUser(String userName, String password) {
        User user = jdbcTemplate.queryForObject(GET_USER_BY_NAME_AND_PASSWORD_SQL, new Object[] { userName, password }, new UserRowMapper());

        return user;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}

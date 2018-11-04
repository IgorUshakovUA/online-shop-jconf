package com.study.shop.dao.jdbc.mapper;

import com.study.shop.entity.User;
import com.study.shop.security.entity.UserRole;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("rawtypes")
public class UserRowMapper implements RowMapper<User> {
    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new User(
                         resultSet.getString("USERNAME")
                       , resultSet.getString("PASSWORD")
                       , resultSet.getString("SALT")
                       , UserRole.valueOf(resultSet.getString("USER_ROLE"))
                       );
    }
}

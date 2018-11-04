package com.study.shop.dao.jdbc.mapper;

import com.study.shop.entity.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("rawtypes")
public class ProductRowMapper implements RowMapper<Product> {
    public Product mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new Product(resultSet.getInt("ID"), resultSet.getString("NAME"),
                resultSet.getDouble("PRICE"), resultSet.getTimestamp("ADD_DATE").toLocalDateTime(),
                resultSet.getString("PICTURE_PATH"));
    }
}


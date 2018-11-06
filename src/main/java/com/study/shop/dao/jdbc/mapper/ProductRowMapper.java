package com.study.shop.dao.jdbc.mapper;

import com.study.shop.entity.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("rawtypes")
public class ProductRowMapper implements RowMapper<Product> {
    public Product mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt("ID"));
        product.setName(resultSet.getString("NAME"));
        product.setPrice(resultSet.getDouble("PRICE"));
        product.setAddDate(resultSet.getTimestamp("ADD_DATE").toLocalDateTime());
        product.setPicturePath(resultSet.getString("PICTURE_PATH"));
        return product;
    }
}


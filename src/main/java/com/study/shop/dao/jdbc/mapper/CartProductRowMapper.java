package com.study.shop.dao.jdbc.mapper;

import com.study.shop.entity.CartProduct;
import com.study.shop.entity.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartProductRowMapper implements RowMapper<CartProduct> {
    public CartProduct mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt("ID"));
        product.setName(resultSet.getString("NAME"));
        product.setPrice(resultSet.getDouble("PRICE"));
        product.setAddDate(resultSet.getTimestamp("ADD_DATE").toLocalDateTime());
        product.setPicturePath(resultSet.getString("PICTURE_PATH"));
        return new CartProduct(product);
    }
}

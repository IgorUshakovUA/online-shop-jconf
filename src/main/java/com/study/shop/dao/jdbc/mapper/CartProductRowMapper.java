package com.study.shop.dao.jdbc.mapper;

import com.study.shop.entity.CartProduct;
import com.study.shop.entity.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartProductRowMapper implements RowMapper<CartProduct> {
    public CartProduct mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new CartProduct(new Product(resultSet.getInt("ID"), resultSet.getString("NAME"),
                resultSet.getDouble("PRICE"), resultSet.getTimestamp("ADD_DATE").toLocalDateTime(),
                resultSet.getString("PICTURE_PATH")));
    }
}

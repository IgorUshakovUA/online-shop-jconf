package com.study.shop.dao.jdbc;

import com.study.shop.dao.ProductDao;
import com.study.shop.dao.jdbc.mapper.CartProductRowMapper;
import com.study.shop.dao.jdbc.mapper.ProductRowMapper;
import com.study.shop.entity.Cart;
import com.study.shop.entity.CartProduct;
import com.study.shop.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.StringJoiner;

@Repository
public class JdbcProductDao implements ProductDao {
    private static final String GET_ALL_PRODUCTS_SQL = "SELECT id, name, price, add_date, picture_path FROM product";
    private static final String GET_PRODUCT_BY_ID_SQL = "SELECT id, name, price, add_date, picture_path FROM product WHERE id = ?";
    private static final String GET_PRODUCTS_BY_CART_SQL = "SELECT id, name, price, add_date, picture_path FROM product WHERE ? LIKE CONCAT('%,',id,',%')";
    private static final String DELETE_PRODUCT_BY_ID_SQL = "DELETE FROM product WHERE id = ?";
    private static final String UPDATE_PRODUCT_BY_ID_SQL = "UPDATE product SET name = ?, price = ?, add_date = ?, picture_path = ? WHERE id = ?";
    private static final String INSERT_NEW_PRODUCT_SQL = "INSERT INTO product (id, name, price, picture_path) VALUES(DEFAULT, ?, ?, ?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcProductDao() {

    }

    @Override
    public List<Product> getAll() {
        return jdbcTemplate.query(GET_ALL_PRODUCTS_SQL, new ProductRowMapper());
    }

    @Override
    public List<Product> getById(int id) {
        return jdbcTemplate.query(GET_PRODUCT_BY_ID_SQL, new Object[]{id}, new ProductRowMapper());
    }

    @Override
    public List<CartProduct> getByCart(Cart cart) {
        StringJoiner idList = new StringJoiner(",", ",", ",");

        for (Integer key : cart.getProductList().keySet()) {
            idList.add(key.toString());
        }

        return jdbcTemplate.query(GET_PRODUCTS_BY_CART_SQL, new Object[]{idList.toString()}, new CartProductRowMapper());
    }

    @Override
    public void update(int id, String name, double price, LocalDateTime addTime, String picturePath) {
        jdbcTemplate.update(UPDATE_PRODUCT_BY_ID_SQL, new Object[]{name, price, Timestamp.valueOf(addTime), picturePath, id});
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(DELETE_PRODUCT_BY_ID_SQL, new Object[]{id});
    }

    @Override
    public void add(String name, double price, String picturePath) {
        jdbcTemplate.update(INSERT_NEW_PRODUCT_SQL, new Object[]{name, price, picturePath});
    }
}

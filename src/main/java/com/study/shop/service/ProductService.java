package com.study.shop.service;

import com.study.shop.entity.Cart;
import com.study.shop.entity.CartProduct;
import com.study.shop.entity.Product;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductService {
    List<Product> getAll();

    List<Product> getById(int id);

    List<CartProduct> getByCart(Cart cart);

    void update(Product product);

    void delete(int id);

    void add(Product product);
}

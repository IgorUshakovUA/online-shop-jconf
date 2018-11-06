package com.study.shop.service;

import com.study.shop.dao.ProductDao;
import com.study.shop.entity.Cart;
import com.study.shop.entity.CartProduct;
import com.study.shop.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class DefaultProductService implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> getAll() {
        List<Product> products = productDao.getAll();
        Collections.sort(products, Comparator.comparingInt(Product::getId));
        return products;
    }

    @Override
    public List<Product> getById(int id) {
        List<Product> products = productDao.getById(id);
        return products;
    }

    @Override
    public List<CartProduct> getByCart(Cart cart) {
        List<CartProduct> products = productDao.getByCart(cart);
        return products;
    }

    @Override
    public void update(Product product) {
        productDao.update(product.getId(), product.getName(), product.getPrice(), product.getAddDate(), product.getPicturePath());
    }

    @Override
    public void delete(int id) {
        productDao.delete(id);
    }

    @Override
    public void add(Product product) {
        productDao.add(product.getName(), product.getPrice(), product.getPicturePath());
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }
}

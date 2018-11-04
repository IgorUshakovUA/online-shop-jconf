package com.study.shop.web.restcontroller;

import com.study.shop.entity.Cart;
import com.study.shop.entity.CartProduct;
import com.study.shop.entity.Product;
import com.study.shop.service.ProductService;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class ProductRestControllerTest {
    @Test
    public void testGetAll() {
        ProductRestController productRestController = new ProductRestController();
        ProductService productService = new TestProductService();
        productRestController.setProductService(productService);

        List<Product> actualProducts = productRestController.getAll();

        String expectedString1 = productService.getById(1).get(0).toString();
        String expectedString2 = productService.getById(2).get(0).toString();
        assertEquals(2, actualProducts.size());
        assertEquals(expectedString1, actualProducts.get(0).toString());
        assertEquals(expectedString2, actualProducts.get(1).toString());
    }

    @Test
    public void testGetById() {
        ProductRestController productRestController = new ProductRestController();
        ProductService productService = new TestProductService();
        productRestController.setProductService(productService);

        Product actualProduct = productRestController.getById(1);

        String expectedString1 = productService.getById(1).get(0).toString();
        assertEquals(expectedString1, actualProduct.toString());
    }

    @Test
    public void testDelete() {
        ProductRestController productRestController = new ProductRestController();
        ProductService productService = new TestProductService();
        productRestController.setProductService(productService);
        List<Product> actualProducts = productRestController.getAll();
        String expectedString2 = productService.getById(2).get(0).toString();

        productRestController.delete(1);

        assertEquals(1, actualProducts.size());
        assertEquals(expectedString2, actualProducts.get(0).toString());
    }

    @Test
    public void testEdit() {
        ProductRestController productRestController = new ProductRestController();
        ProductService productService = new TestProductService();
        productRestController.setProductService(productService);
        Product product = new Product(999, "test_name_new", 999.09, LocalDateTime.now(), "the_link_new");
        List<Product> actualProducts = productRestController.getAll();

        productRestController.edit(2, product);

        product.setId(2);
        assertEquals(2, actualProducts.size());
        assertEquals(product.toString(), actualProducts.get(1).toString());
    }

    @Test
    public void testAdd() {
        ProductRestController productRestController = new ProductRestController();
        ProductService productService = new TestProductService();
        productRestController.setProductService(productService);
        Product product = new Product(1, "test_name_new", 999.09, LocalDateTime.now(), "the_link_new");

        productRestController.add(product);

        List<Product> actualProductList = productRestController.getAll();
        assertEquals(3, actualProductList.size());
        Product actualProduct = actualProductList.get(2);
        assertEquals(product.getName(), actualProduct.getName());
        assertEquals(product.getPrice(),actualProduct.getPrice(),1e-10);
        assertEquals(product.getPicturePath(),actualProduct.getPicturePath());
    }

}

class TestProductService implements ProductService {
    private List<Product> products = new ArrayList<>();

    public TestProductService() {
        Product product = new Product(1, "test_product_1", 99.99, LocalDateTime.now(), "the_link_1");
        products.add(product);
        product = new Product(2, "test_product_2", 99.99, LocalDateTime.now(), "the_link_2");
        products.add(product);
    }

    @Override
    public List<Product> getAll() {
        return products;
    }

    @Override
    public List<Product> getById(int id) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getId() == id) {
                result.add(product);
            }
        }
        return result;
    }

    @Override
    public List<CartProduct> getByCart(Cart cart) {
        return null;
    }

    @Override
    public void update(int id, String name, double price, LocalDateTime addTime, String picturePath) {
        for (Product product : products) {
            if (product.getId() == id) {
                product.setName(name);
                product.setPrice(price);
                product.setAddDate(addTime);
                product.setPicturePath(picturePath);
            }
        }
    }

    @Override
    public void delete(int id) {
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product current = iterator.next();
            if (current.getId() == id) {
                iterator.remove();
            }
        }
    }

    @Override
    public void add(String name, double price, String picturePath) {
        products.add(new Product(999, name, price, LocalDateTime.now(), picturePath));
    }
}
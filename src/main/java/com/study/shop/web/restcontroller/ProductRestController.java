package com.study.shop.web.restcontroller;

import com.study.shop.entity.Product;
import com.study.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductRestController {
    @Autowired
    private ProductService productService;

    @RequestMapping(path="/api/v1/product/{id}", method = RequestMethod.GET)
    public Product getById(@PathVariable int id) {
        return productService.getById(id).get(0);
    }

    @RequestMapping(path="/api/v1/product/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) {
        productService.delete(id);
    }

    @RequestMapping(path="/api/v1/product/{id}", method = RequestMethod.PUT)
    public void edit(@PathVariable int id, @RequestBody Product product) {
        productService.update(id, product.getName(), product.getPrice(), product.getAddDate(), product.getPicturePath());
    }

    @RequestMapping(path="/api/v1/product", method = RequestMethod.POST)
    public void add(@RequestBody Product product) {
        productService.add(product.getName(), product.getPrice(), product.getPicturePath());
    }

    @RequestMapping(path="/api/v1/products", method = RequestMethod.GET)
    public List<Product> getAll() {
        return productService.getAll();
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}

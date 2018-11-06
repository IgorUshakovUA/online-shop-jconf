package com.study.shop.entity;

import java.util.HashMap;

public class Cart {
    private HashMap<Integer, Integer> productList = new HashMap();

   public HashMap<Integer, Integer> getProductList() {
        return productList;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "productList=" + productList +
                '}';
    }
}

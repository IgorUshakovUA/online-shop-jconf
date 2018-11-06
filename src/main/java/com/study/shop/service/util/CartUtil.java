package com.study.shop.service.util;

import com.study.shop.entity.Cart;

import java.util.Map;

public class CartUtil {

    public static void addProduct(Cart cart, int id, int count) {
        Integer key = id;
        Map<Integer, Integer> productList = cart.getProductList();
        Integer value = productList.get(key);
        if (value != null) {
            value += count;
        } else {
            value = count;
        }
        productList.put(key, value);
    }

    public static int getCountById(Cart cart, int id) {
        Integer key = id;
        return cart.getProductList().get(key);
    }

    public static void deleteProduct(Cart cart, int id) {
        Integer key = id;
        cart.getProductList().remove(key);
    }

    public static void decreaseCount(Cart cart, int id) {
        Integer key = id;
        Map<Integer, Integer> productList = cart.getProductList();
        Integer value = productList.get(key);
        if (value != null) {
            if (value > 1) {
                value--;
                productList.put(key, value);
            } else {
                deleteProduct(cart, id);
            }
        }

    }

    public static void clear(Cart cart) {
        cart.getProductList().clear();
    }

    public static int getItemCount(Cart cart) {
        int result = 0;
        for (Integer value : cart.getProductList().values()) {
            result += value;
        }

        return result;
    }
}

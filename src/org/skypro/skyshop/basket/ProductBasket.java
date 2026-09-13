package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductBasket {
    private final Map<String, List<Product>> products = new HashMap<>();

    public void addProduct(Product product) {
        products.computeIfAbsent(product.getName(), k -> new ArrayList<>()).add(product);
    }

    public int getTotalPrice() {
        int total = 0;
        for (List<Product> list : products.values()) {
            for (Product p : list) {
                total += p.getPrice();
            }
        }
        return total;
    }

    public void printContents() {
        if (products.isEmpty()) {
            System.out.println("в корзине пусто");
            return;
        }
        for (List<Product> list : products.values()) {
            for (Product p : list) {
                System.out.println(p.getName() + ": " + p.getPrice());
            }
        }
        System.out.println("Итого: " + getTotalPrice());
    }
    public boolean containsProduct(String name) {
        return products.containsKey(name);
    }

    public List<Product> removeProductByName(String name) {
        List<Product> removed = products.remove(name);
        return removed == null ? new ArrayList<>() : removed;
    }

    public void clear() {
        products.clear();
    }
}
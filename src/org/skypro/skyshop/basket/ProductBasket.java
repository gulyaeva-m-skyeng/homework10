package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductBasket {
    private final Map<String, List<Product>> products = new HashMap<>();

    public void addProduct(Product product) {
        products.computeIfAbsent(product.getName(), k -> new ArrayList<>()).add(product);
    }

    public int getTotalPrice() {
        return products.values().stream()
                .flatMap(Collection::stream)
                .mapToInt(Product::getPrice)
                .sum();
    }

    public void printContents() {
        if (products.isEmpty()) {
            System.out.println("в корзине пусто");
            return;
        }

        products.values().stream()
                .flatMap(Collection::stream)
                .forEach(p -> System.out.println(p.getStringRepresentation()));

        System.out.println("Итого: " + getTotalPrice());
        System.out.println("Специальных товаров: " + getSpecialCount());
    }

    private long getSpecialCount() {
        return products.values().stream()
            .flatMap(Collection::stream)
            .filter(Product::isSpecial)
            .count();
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
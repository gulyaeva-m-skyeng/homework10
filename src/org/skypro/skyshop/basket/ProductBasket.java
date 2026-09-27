package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
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
                .forEach(p -> System.out.println(p.getName() + ": " + p.getPrice()));

        long specialCount = getSpecialCount();

        System.out.println("Итого: " + getTotalPrice());
        System.out.println("Специальных товаров: " + specialCount);
    }

    private long getSpecialCount() {
    return products.values().stream()
            .flatMap(Collection::stream)
            .filter(p -> p instanceof DiscountedProduct || p instanceof FixPriceProduct)
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
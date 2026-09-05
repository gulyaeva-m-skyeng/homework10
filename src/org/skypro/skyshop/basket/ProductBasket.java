package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductBasket {
    private final List<Product> products;

    public ProductBasket() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    // Метод удаления всех продуктов с заданным именем
public List<Product> removeProduct(String name) {
    List<Product> removed = new ArrayList<>();
    Iterator<Product> iterator = products.iterator();

    while (iterator.hasNext()) {
        Product product = iterator.next();
        if (product.getName().equals(name)) {
            removed.add(product);
            iterator.remove();
        }
    }
    return removed;
    }

    public void printBasket() {
        if (products.isEmpty()) {
            System.out.println("Корзина пуста.");
            return;
        }

       int total = 0;
        int specialCount = 0;

        for (Product product : products) {
            System.out.println(product);
            total += product.getPrice();
            if (product.isSpecial()) {
                specialCount++;
            }
        }

      System.out.println("Итого: " + total);
        System.out.println("Специальных товаров: " + specialCount);
    }
}
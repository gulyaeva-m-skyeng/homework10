package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

public class ProductBasket {
    private static final int MAX_SIZE = 5;
    private final Product[] items;


    public ProductBasket() {
        items = new Product[MAX_SIZE];
        for (int i = 0; i < items.length; i++) {
          items[i] = null;
        }
    }
public void addProduct(Product product) {
    for (int i = 0; i < items.length; i++) {
        if (items[i] == null) {
            items[i] = product;
            return;
        }
    }
    System.out.println("Невозможно добавить продукт");
}

public int getTotalPrice() {
        int sum = 0;
        for (Product item : items) {
            if (item != null) {
                sum += item.getPrice();
            }
        }
        return sum;
}

// Считаем только специальные товары
    private int countSpecialProducts() {
        int count = 0;
        for (Product item : items) {
            if (item != null && item.isSpecial()) {
                count++;
            }
        }
        return count;
    }

    public void printBasket() {
        boolean hasItems = false;

        for (Product item : items) {
            if (item != null) {
                hasItems = true;
                System.out.println(item.toString());

            }
        }
        if (!hasItems) {
        System.out.println("В корзине пусто");
    } else {
        System.out.println("Итого: " + getTotalPrice());
        System.out.println("Специальных товаров: " + countSpecialProducts());
        }
}

public boolean containsByName(String name) {
    for (Product item: items) {
        if (item != null && item.getName().equals(name)) {
            return true;
        }
    }
    return false;
}
public void clearBasket() {
    for (int i = 0; i < items.length; i++) {
        items[i] = null;
    }
}
}
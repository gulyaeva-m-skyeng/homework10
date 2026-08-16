package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

public class ProductBasket {
    private static final int MAX_ITEMS = 100;
    private Product[] items;
    private int count;

    public ProductBasket() {
        items = new Product[MAX_ITEMS];
        count = 0;
    }

    // Добавление продукта в корзину
    public void addProduct(Product product) {
        if (count < MAX_ITEMS) {
            items[count] = product;
            count++;
        } else {
            System.out.println("Корзина заполнена.");
        }
    }

    // Получение общей стоимости корзины
    public int getTotalCost() {
        int total = 0;
        for (int i = 0; i < count; i++) {
            if (items[i] != null) {
                total += items[i].getPrice();
            }
        }
        return total;
    }

    // Печать содержимого корзины
    public void printBasket() {
        if (count == 0) {
            System.out.println("в корзине пусто");
            return;
        }
        for (int i = 0; i < count; i++) {
            if (items[i] != null) {
                System.out.println(items[i].getName() + ": " + items[i].getPrice());

            }
        }
        System.out.println("Итого: " + getTotalCost());
    }

//Проверка наличия продукта по имени
    public boolean containsByName(String name) {
        for (int i = 0; i < count; i++) {
            if (items[i] != null && items[i].getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    //Очистка корзины, проставить null всем элементам
    public void clear() {
        for (int i = 0; i < items.length; i++) {
            items[i] = null;
        }
        count = 0;
    }
}

package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.Product;

public class App {
    public static void main(String[] args) {

        //Создание продуктов
        Product p1 = new Product("Кокос", 23);
        Product p2 = new Product("Ананас", 17);

        //Создание корзины
        ProductBasket basket = new ProductBasket();

        //Демонстрация работы методов
        basket.addProduct(p1);
        basket.addProduct(p2);

        System.out.println("Содержимое корзины");
        basket.printBasket();

        System.out.println("Поиск кокоса в корзине: " + basket.containsByName("Кокос"));
        System.out.println("Поиск яблока в корзине: " + basket.containsByName("Яблоко"));

        basket.clear();
        System.out.println("Содержимое после очистки: ");
        basket.printBasket();

    }
}

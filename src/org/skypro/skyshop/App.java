package org.skypro.skyshop;

import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.basket.ProductBasket;

import java.sql.SQLOutput;

public class App {
    public static void main(String[] args) {
        ProductBasket basket = new ProductBasket();
        //Обычные товары
        SimpleProduct milk = new SimpleProduct("Молоко", 120);
        SimpleProduct eggs = new SimpleProduct("Яйца", 100);

        // Товары со скидкой
        DiscountedProduct cheese = new DiscountedProduct("Сыр", 300, 15); // 15% скидка

        // Товары с фиксированной ценой
        FixPriceProduct coconuts = new FixPriceProduct("Кокосы");
        FixPriceProduct pineapples = new FixPriceProduct("Ананасы");

        // Добавление товар в корзину
        basket.addProduct(milk);
        basket.addProduct(eggs);
        basket.addProduct(cheese);
        basket.addProduct(coconuts);
        basket.addProduct(pineapples);

        // Попытка добавить 6 товар
        System.out.println("... Попытка добавить 6-й товар ... ");
        SimpleProduct extra = new SimpleProduct("Лишний товар", 77);
        basket.addProduct(extra);

        // Печать содержимое корзины (с новым форматом)
        System.out.println("...Содержимое корзины...");
        basket.printBasket();

        // Получение стоимости корзины
        System.out.println("Общая стоимость корзины: " + basket.getTotalPrice());

        //Поиск товара, который есть в корзине
        System.out.println("Есть ли в корзине «Сыр»? " + basket.containsByName("Сыр"));

        //Поиск товара, которого нет в корзине
        System.out.println("Есть ли в корзине «Шоколад»? " + basket.containsByName("Шоколад"));

        //Очистка корзины
        System.out.println("...Очистка корзины...");
        basket.clearBasket();

        // Печать пустой корзины
        System.out.println("...Содержимое пустой корзины ...");
        basket.printBasket();

        // Стоимость пустой корзины
        System.out.println("Стоимость пустой карзины: " + basket.getTotalPrice());

        // Поиск в пустой корзине
        System.out.println("Есть ли в пустой корзине «Молоко»? " + basket.containsByName("Молоко"));
    }
}
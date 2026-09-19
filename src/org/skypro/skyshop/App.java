package org.skypro.skyshop;

import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;
import org.skypro.skyshop.exception.BestResultNotFound;
import org.skypro.skyshop.product.Product;

import java.util.List;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        System.out.println("=== Проверка валидации ===");

        try {
            new SimpleProduct("", 777);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        try {
            new SimpleProduct("Помада", 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        try {
            new SimpleProduct("Тушь", 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        try {
            new DiscountedProduct("Румяна", -555, 17);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        // Корректные товары
        System.out.println("\n===Корректные товары===");

        SimpleProduct lipstick = new SimpleProduct("Помада", 1000);
        SimpleProduct mascara = new SimpleProduct("Тушь", 800);
        DiscountedProduct blush = new DiscountedProduct("Румяна", 700, 15);
        FixPriceProduct eyeliner = new FixPriceProduct("Подводка", 169);
        SimpleProduct mascara2 = new SimpleProduct("Тушь", 750);

        System.out.println(lipstick.getName() + ": " + lipstick.getPrice());
        System.out.println(mascara.getName() + ": " + mascara.getPrice());
        System.out.println(blush.getName() + ": " + blush.getPrice());
        System.out.println(eyeliner.getName() + ": " + eyeliner.getPrice());

        // Корзина
        System.out.println("\n===Корзина ===");
        ProductBasket basket = new ProductBasket();
        basket.addProduct(lipstick);
        basket.addProduct(mascara);
        basket.addProduct(blush);
        basket.addProduct(mascara2);
        basket.addProduct(eyeliner);

        basket.printContents();
        System.out.println("Товар \"Тушь\" найден: " +basket.containsProduct("Тушь"));
        System.out.println("Товар \"Хайлайтер\" найден: " + basket.containsProduct("Хайлайтер"));


        // Удаляем продукт по названию
        System.out.println("\n=== Удаление продукта по названию ===");

        List<Product> removed = basket.removeProductByName("Тушь");
        System.out.println("Удалённые продукты:");
        for (Product p : removed) {
            System.out.println(" " + p.getName() + ": " + p.getPrice());
        }

        System.out.println("\nКорзина после удаления:");
        basket.printContents();

        List<Product> removedEmpty = basket.removeProductByName("Хайлайтер");
        if (removedEmpty.isEmpty()) {
            System.out.println("\nСписок пуст");
        }

        System.out.println("\nКорзина после попытки удаления несуществующего:");
        basket.printContents();

        // Поиск
        System.out.println("\n=== Работа с поиском ===\n");

        Article article1 = new Article("Как выбрать помаду с ароматом ванили", "В этой статье мы расскажем какие помады бывают.");
        Article article2 = new Article("Обзор карандашей для глаз", "Модельный показ карандашей разных цветов.");

        SearchEngine engine = new SearchEngine();
        engine.add(lipstick);
        engine.add(mascara);
        engine.add(blush);
        engine.add(eyeliner);
        engine.add(article1);
        engine.add(article2);

        // Демонстрация дубликатов: mascara2 имеет такое же наименование как и mascara
        engine.add(mascara2);
        System.out.println("Попытка добавить дубликат (Тушь mascara) - не должна добавиться");

        // Поиск результатов - TreeSet
        System.out.println("\n Результаты поиска \"Карандаш\" :");
        Set<Searchable> results = engine.search("Карандаш");
        for (Searchable s : results) {
            System.out.println(" " + s.getStringRepresentation());
        }

        System.out.println("\nРезультаты поиска \"Несуществующего товара\" :");
        Set<Searchable> emptyResults = engine.search("Несуществующий товар");
        if (emptyResults.isEmpty()) {
            System.out.println(" Ничего не найдено");
        } else {
            for (Searchable s : emptyResults) {
                System.out.println(" " + s.getStringRepresentation());
            }
        }

        // Лучший результат - объект существует
        try {
            Searchable best = engine.findBestMatch("Помада");
            System.out.println("\nЛучший результат: " + best.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        // Лучший результат - исключение
        try {
            Searchable best = engine.findBestMatch("Несуществующий Товар");
            System.out.println("Лучший результат: " + best.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
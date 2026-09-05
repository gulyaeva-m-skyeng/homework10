package org.skypro.skyshop;

import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;
import org.skypro.skyshop.search.BestResultNotFound;

import java.util.List;

public class App {
    public static void main(String[] args) {
        // Демонстрация ProductBasket (список + удаление)
        System.out.println("=== Работа с корзиной ===\n");

        ProductBasket basket = new ProductBasket();
        basket.addProduct(new SimpleProduct("Помада", 1000));
        basket.addProduct(new SimpleProduct("Тушь", 800));
        basket.addProduct(new DiscountedProduct("Румяна", 750, 15));
        basket.addProduct(new FixPriceProduct("Подводка"));
        basket.addProduct(new SimpleProduct("Карандаш", 200));

        System.out.println("===Корзина до удаления ===");
        basket.printBasket();
        System.out.println();

        // Удаляем существующий продукт
    System.out.println("=== Удаление продукта \"Помада\" ===");
            List<org.skypro.skyshop.product.Product> removed = basket.removeProduct("Помада");

    System.out.println("Удалённые продукты:");
    for (org.skypro.skyshop.product.Product p : removed) {
    System.out.println(" " + p);
    }

    System.out.println("\nКорзина после удаления:");
    basket.printBasket();
    System.out.println();

    // Удаляемый несуществующий продукт
        System.out.println("=== Удаление несуществующего продукта \"Хайлайтер\" ===");
        List<org.skypro.skyshop.product.Product> removedEmpty = basket.removeProduct("Хайлайтер");

        if (removedEmpty.isEmpty()) {
            System.out.println("Список пуст");
        }

        System.out.println("\nКорзина после попытки удаления:");
        basket.printBasket();
        System.out.println();

        // Демонстрация SearchEngine (список + все результаты)
        System.out.println("=== Работа с поиском ===\n");

        SearchEngine engine = new SearchEngine();
        engine.add(new SimpleProduct("Помада", 1000));
        engine.add(new SimpleProduct("Тушь", 800));
        engine.add(new SimpleProduct("Карандаш", 200));
        engine.add(new DiscountedProduct("Румяна", 750, 15));
        engine.add(new FixPriceProduct("Подводка"));
        engine.add(new Article("Как выбрать помаду с ароматом ванили", "В этой статье мы расскажем какие помады бывают."));
        engine.add(new Article("Обзор карандашей для глаз", "Модельный показ карандашей разных цветов."));

        // Поиск по продукту "помада" - вернет все результаты
        System.out.println("=== Поиск по запросу \"помада\" ===");
        List<Searchable> results = engine.search("помада");
        for (Searchable item : results) {
            System.out.println("> " + item.getStringRepresentation());
        }

        System.out.println("\nВсего найдено: " + results.size());
        System.out.println();

        // Поиск по продукту "карандаш"
        System.out.println("=== Поиск по запросу \"карандаш\" === ");
        List<Searchable> results2 = engine.search("карандаш");
        for (Searchable item : results2) {
            System.out.println("> " + item.getStringRepresentation());
        }

        System.out.println("\nВсего найдено: " + results2.size());
        System.out.println();

        // Поиск несуществующего товара
        System.out.println("=== Поиск по запросу \"блестки\" ===");
        List<Searchable> results3 = engine.search("блестки");
        if (results3.isEmpty()) {
            System.out.println("Ничего не найдено.");
        }
         System.out.println();

        // Поиск лучшего совпадения
        System.out.println(" === Поиск лучшего совпадения по \"помада\" ===");
        try {
            Searchable best = engine.findBestMatch("помада");
            System.out.println("Лучший результат: " + best.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println(e.getMessage());
        }

        System.out.println();

        // Поиск лучшего совпадения - нет результата
        System.out.println("=== Поиск лучшего совпадения по \"блестки\" ===");
        try {
            Searchable best = engine.findBestMatch("блестки");
            System.out.println("Лучший результат: " + best.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println(e.getMessage());
        }
    }
}
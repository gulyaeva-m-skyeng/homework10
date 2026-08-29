package org.skypro.skyshop;

import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;
import org.skypro.skyshop.search.BestResultNotFound;

public class App {
    public static void main(String[] args) {
        SearchEngine engine = new SearchEngine(20);

        // 1. Объекты
        engine.add(new SimpleProduct("Помада", 1000));
        engine.add(new SimpleProduct("Тушь", 800));
        engine.add(new DiscountedProduct("Румяна", 750, 15));
        engine.add(new Article("Как выбрать помаду c ароматом ванили", "В этой статье мы расскажем какие ароматные помады бывают..."));
        engine.add(new Article("Обзор видов туши", "Модельные показ туши разных цветов..."));

        System.out.println("===Проверка валидации (некорректные данные) ===\n");

        // 2. Демонстрация IllegalArgumentException
        try {
            new SimpleProduct("", 100); // пустое название
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано исключение (пустое название): " + e.getMessage());
        }

        try {
            new SimpleProduct("  ", 100); // только пробелы
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано исключение (название из пробелов): " + e.getMessage());
        }

        try {
            new SimpleProduct("Товар", 0); // цена 0
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано исключение (цена <= 0): " + e.getMessage());
        }

        try {
            new DiscountedProduct("Скидка", 100, -7); // скидка < 0
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано исключение (скидка < 0) " + e.getMessage());
        }
         try {
             new DiscountedProduct("Скидка", 100, 109); // скидка > 100
         } catch (IllegalArgumentException e) {
             System.out.println("Поймано исключение (скидка > 100): " + e.getMessage());
         }
         System.out.println();

         // 3. Демонстрация findBestMatch: есть совпадение
        System.out.println("=== Поиск лучшего совпадения (есть результат) ===");
         try {
             Searchable best = engine.findBestMatch("Туши");
             System.out.println("Лучший результат: " + best.getStringRepresentation());
             if (best instanceof Article) {
                 System.out.println("Детали: " + best.toString());
             }
         } catch (BestResultNotFound e) {
             System.out.println(e.getMessage());
         }
         System.out.println();

         // 4. Демонстрация findBestMatch: нет совпадений
        System.out.println("===Поиск лучшего совпадения (нет результата) ===");
        try {
            Searchable best = engine.findBestMatch("несуществующий товар");
            System.out.println("Лучший результат: " + best.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        // 5. Дополнительно: старый поиск (до 5 результатов)
        System.out.println("===Старый поиск (до 5 результатов) ===");
        Searchable[] results = engine.search("Помада");
        for (Searchable r : results) {
            if (r != null) {
                System.out.println("> " + r.getStringRepresentation());
            }
        }
    }
}

package org.skypro.skyshop;

import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

public class App {
    public static void main(String[] args) {
        //Инициализация движка поиска
        SearchEngine engine = new SearchEngine(20);

        // Добавляем товары
        engine.add(new SimpleProduct("Помада", 1000));
        engine.add(new SimpleProduct("Тушь", 800));
        engine.add(new SimpleProduct("Карандаш", 200));
        engine.add(new DiscountedProduct("Румяна", 750, 15));
        engine.add(new DiscountedProduct("Пудра", 500, 10));
        engine.add(new FixPriceProduct("Подводка"));
        engine.add(new FixPriceProduct("Хайлайтер"));

        // Добавляем статьи
        engine.add(new Article("Как выбрать помаду c ароматом ванили", "В этой статье мы расскажем какие ароматные помады бывают..."));
        engine.add(new Article("Обзор видов туши", "Модельные показ туши разных цветов..."));
        engine.add(new Article("Эффект румян", "Как румяна переливаются на солнце..."));
        engine.add(new Article("Выразительность глаз", "Благодаря подводки взгляд становится выразительным"));

        System.out.println("===Тестирование поиска===\n");

        // Тест 1. Запрос, совпадающий с товаром и статьей ("тушь")
        runTest(engine, "тушь");

        // Тест 2. Запрос, совпадающий только со статьей ("эффект")
        runTest(engine,"эффект");

        // Тест 3. Частичное совпадение ("хайлай")
        runTest(engine, "хайлай");

        // Тест 4. Несуществующий запрос
        runTest(engine, "блестки");

        // Тест 5. Проверка лимита в 5 результатов
        //Добавляем много товаров с похожим названием, чтобы превысить лимит

        engine.add(new SimpleProduct("Помада розовая", 756));
        engine.add(new SimpleProduct("Помада красная", 720));
        engine.add(new SimpleProduct("Помада фиолетовая", 650));
        engine.add(new SimpleProduct("Помада бордовая", 680));
        engine.add(new SimpleProduct("Помада малиновая", 850));

        System.out.println("Тест 5: Поиск 'помада' (ожидает не более 5 результатов:");
        printResults(engine.search("помада"));
    }

    private static void runTest(SearchEngine engine, String query) {
    System.out.println("Поиск по запросу: \"" + query + "\"");
    Searchable[] results = engine.search(query);
    printResults(results);
    System.out.println("-----------\n");
    }

    private static void printResults(Searchable[] results) {
        boolean foudAny = false;
        for (Searchable item : results) {
            if (item != null) {
                foudAny = true;
                System.out.println("> " + item.getStringRepresentation());

                // Выводим полный текст статьи, если это статья
                if (item instanceof Article) {
                    System.out.println("  Детали: " + item.toString());
                }
            }
        }
        if (!foudAny) {
            System.out.println("Ничего не найдено.");
        }
    }
}
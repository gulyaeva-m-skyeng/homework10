package org.skypro.skyshop.search;

public class BestResultNotFound extends Exception {
    public BestResultNotFound(String query) {
        super("Не удалось найти подходящий результат для запроса: \"" + query + "\"");
    }
}

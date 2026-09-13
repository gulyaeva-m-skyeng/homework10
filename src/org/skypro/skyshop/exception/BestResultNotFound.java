package org.skypro.skyshop.exception;

public class BestResultNotFound extends Exception {
    public BestResultNotFound(String searchTerm) {
        super("Не найти подходящий результат для запроса: " + searchTerm);
    }
}

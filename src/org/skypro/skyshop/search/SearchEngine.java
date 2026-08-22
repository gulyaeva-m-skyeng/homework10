package org.skypro.skyshop.search;

public class SearchEngine {
    private final Searchable[] items;
    private int currentSize;

    // Конструктор принимает размер массива
public SearchEngine (int capacity) {
    this.items = new Searchable[capacity];
    this.currentSize = 0;
}

// Метод добавление нового объекта
    public void add(Searchable item) {
    if (currentSize < items.length) {
        items[currentSize] = item;
        currentSize++;
    }
}

// Метод поиска: возвращает массив из максимум 5 элементов
    public Searchable[] search(String query) {
    Searchable[] results = new Searchable[5];
    int count = 0;
    String lowerQuery = query.toLowerCase();

    for (int i=0; i < currentSize; i++) {
        Searchable item = items[i];
        String searchTerm = item.getSearchTerm().toLowerCase();

        if (searchTerm.contains(lowerQuery)) {
            results[count] = item;
            count++;

            // Прерываем цикл, если найдено 5 элементов
            if (count == 5) {
                break;
            }
        }
    }
    return results;
    }
}

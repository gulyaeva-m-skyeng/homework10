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
        String term = item.getSearchTerm().toLowerCase();

        if (term.contains(lowerQuery)) {
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

    // Метод поиска: найти "лучший" результат (максимальное число вхождений подстроки)
    public Searchable findBestMatch(String query) throws BestResultNotFound {
    if (query == null || query.isBlank()) {
        throw new BestResultNotFound(query);
    }
    String lowerQuery = query.toLowerCase();
    Searchable bestItem = null;
    int maxCount = -1;

    for (int i = 0; i < currentSize; i++) {
        Searchable item = items[i];
        String term = item.getSearchTerm().toLowerCase();
        int count = countOccurrences(term, lowerQuery);

        if (count > maxCount && count > 0) {
            maxCount = count;
            bestItem = item;
        }
    }

    if (bestItem == null) {
        throw new BestResultNotFound(query);
    }
    return  bestItem;
    }

    // Вспомогательный метод: подсчет непересекающихся вхождений подстроки
    private static int countOccurrences(String text, String sub) {
    if (sub.isEmpty()) {
        return 0;
    }
    int count = 0;
    int index = 0;
    while ((index = text.indexOf(sub, index)) != -1) {
        count++;
        index += sub.length();
    }
    return count;
    }
}

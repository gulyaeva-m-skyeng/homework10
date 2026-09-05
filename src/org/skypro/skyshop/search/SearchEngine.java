package org.skypro.skyshop.search;

import java.util.ArrayList;
import java.util.List;

public class SearchEngine {
    private final List<Searchable> items;

    public SearchEngine() {
        this.items = new ArrayList<>();
    }

    public void add(Searchable item) {
        items.add(item);
    }

    // Возвращает все подходящие результаты
    public List<Searchable> search(String query) {
        List<Searchable> results = new ArrayList<>();
        String lowerQuery = query.toLowerCase();

        for (Searchable item : items) {
            String term = item.getSearchTerm().toLowerCase();
            if (term.contains(lowerQuery)) {
                results.add(item);
            }
        }
        return results;
    }

    public Searchable findBestMatch(String query) throws BestResultNotFound {
        if (query == null || query.isBlank()) {
            throw new BestResultNotFound(query);
        }

        String lowerQuery = query.toLowerCase();
        Searchable bestItem = null;
        int maxCount = -1;

        for (Searchable item : items) {
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
        return bestItem;
    }

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
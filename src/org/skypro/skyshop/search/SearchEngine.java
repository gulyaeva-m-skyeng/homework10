package org.skypro.skyshop.search;

import org.skypro.skyshop.exception.BestResultNotFound;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SearchEngine {
    private final List<Searchable> searchables = new ArrayList<>();

    public void add(Searchable searchable) {
        searchables.add(searchable);
    }

    public Searchable findBestMatch(String search) throws BestResultNotFound {
        if (search == null || search.isBlank()) {
            throw new BestResultNotFound(search == null ? "null" : search);
        }

        Searchable best = null;
        int maxCount = 0;

        for (Searchable s : searchables) {
            String term = s.getSearchTerm();
            int count = countOccurrences(term, search);
            if (count > maxCount) {
                maxCount = count;
                best = s;
            }
        }

        if (best == null) {
            throw new BestResultNotFound(search);
        }
        return best;
    }

    public Map<String, Searchable> search(String search) {
        Map<String, Searchable> results = new TreeMap<>();
        if (search == null || search.isBlank()) {
            return results;
        }
        for (Searchable s : searchables) {
            if (s.getSearchTerm().toLowerCase().contains(search.toLowerCase())) {
                results.put(s.getName(), s);
            }
        }
        return results;
    }

    private int countOccurrences(String term, String search) {
        if (term == null || term.isEmpty()) {
            return 0;
        }
        int count = 0;
        int index = 0;
        while ((index = term.indexOf(search, index)) != -1) {
            count++;
            index += search.length();
        }
        return count;
    }
}
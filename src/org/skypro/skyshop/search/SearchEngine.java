package org.skypro.skyshop.search;

import org.skypro.skyshop.exception.BestResultNotFound;

import java.util.*;
import java.util.stream.Collectors;

public class SearchEngine {
    private final Set<Searchable> searchables = new HashSet<>();

    public boolean add(Searchable searchable) {
        return searchables.add(searchable);
    }

    public Set<Searchable> search(String search) {
        if (search == null || search.isBlank()) {
            return new TreeSet<>(new SearchableComparator());
        }
        String lowerSearch = search.toLowerCase();

        return searchables.stream()
                .filter(s -> s.getSearchTerm().toLowerCase().contains(lowerSearch))
                .collect(Collectors.toCollection(() -> new TreeSet<>(new SearchableComparator())));
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

    private static class SearchableComparator implements Comparator<Searchable> {
        @Override
        public int compare(Searchable s1, Searchable s2) {
            int lenCompare = Integer.compare(
                    s2.getName().length(),
                    s1.getName().length()
            );
            if (lenCompare != 0) {
                return lenCompare;
            }
            return s1.getName().compareTo(s2.getName());
        }
    }
}
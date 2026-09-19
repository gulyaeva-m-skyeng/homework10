package org.skypro.skyshop.search;

import org.skypro.skyshop.exception.BestResultNotFound;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SearchEngine {
    private final Set<Searchable> searchables = new HashSet<>();

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

    public Set<Searchable> search(String search) {
        Set<Searchable> results = new TreeSet<>(new SearchableComparator());
        if (search == null || search.isBlank()) {
            return results;
        }
        for (Searchable s : searchables) {
            if (s.getSearchTerm().toLowerCase().contains(search.toLowerCase())) {
                results.add(s);
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

    private static class SearchableComparator implements Comparator<Searchable> {
        @Override
        public int compare(Searchable s1, Searchable s2) {
            int lenCompare = Integer.compare(
                    s2.getName().length(),
                    s1.getName().length()
            );
            if (lenCompare !=0) {
                return lenCompare;
            }
            return s1.getName().compareTo(s2.getName());
        }
    }
}
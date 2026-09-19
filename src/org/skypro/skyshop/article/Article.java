package org.skypro.skyshop.article;

import org.skypro.skyshop.search.Searchable;
import java.util.Objects;

public class Article implements Searchable {
    private final String title;
    private final String text;

    public Article(String title, String text) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Название статьи не может быть пустым или null");
        }
        if (text == null) {
            throw new IllegalArgumentException("Текст статьи не может быть null");
        }
       this.title = title;
       this.text = text;
    }

    @Override
    public String getName() {
        return title;
    }
    @Override
    public String getSearchTerm() {
        return title + " " + text;
    }

    @Override
    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public String getStringRepresentation() {
        return title + " — ARTICLE";
    }
    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(title, article.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}

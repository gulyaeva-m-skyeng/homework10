package org.skypro.skyshop.product;

import org.skypro.skyshop.search.Searchable;

public abstract class Product implements Searchable {
    private final String name;

    protected Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract int getPrice();
    public abstract boolean isSpecial ();
    public abstract String toString ();

    // Реализация методов интерфейса Searchable
    @Override
    public String getSearchTerm() {
        //Ищем по имени товара
        return name;
    }

    @Override
    public String getContentType() {
        return "PRODUCT";
    }
}
package org.skypro.skyshop.product;

public abstract class Product {
    private final String name;

    protected Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Цена определяется в наследниках
    public abstract int getPrice();

        // По умолчанию товар не специальный
        public boolean isSpecial () {
            return false;
        }

        @Override
        public String toString () {
            return name + ": " + getPrice();
        }
    }
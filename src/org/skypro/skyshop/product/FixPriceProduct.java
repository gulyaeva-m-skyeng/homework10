package org.skypro.skyshop.product;

public class FixPriceProduct extends Product {
    private final int price;

    public FixPriceProduct(String name, int price) {
        super(name);
        if (price <= 0) {
            throw new IllegalArgumentException("Цена не может быть нулевой или отрицательной");
        }
        this.price = price;
    }

    @Override
    public int getPrice() {
        return price;
    }
    @Override
    public String toString() {
        return getName() + ": " + price + " руб. — FIX_PRICE";
    }

    @Override
    public String getStringRepresentation() {
        return toString();
    }
}
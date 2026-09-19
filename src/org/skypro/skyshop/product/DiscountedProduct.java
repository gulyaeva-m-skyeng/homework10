package org.skypro.skyshop.product;
import org.skypro.skyshop.search.Searchable;

public class DiscountedProduct extends Product {
    public final int basePrice;
    public final int discountPercent;

    public DiscountedProduct(String name, int basePrice, int discountPercent) {
        super(name);

        if (basePrice <= 0) {
            throw new IllegalArgumentException("Базовая цена должна быть строго больше 0");
        }
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException( "Процент скидки должен быть в диапазоне от 0 до 100 включительно");
        }

        this.basePrice = basePrice;
        this.discountPercent = discountPercent;
    }

    @Override
    public int getPrice() {
        return basePrice * (100 - discountPercent) / 100;
    }

    @Override
    public String getContentType() {
        return "PRODUCT";
    }

    @Override
    public String getSearchTerm() {
    return getName();
    }
}
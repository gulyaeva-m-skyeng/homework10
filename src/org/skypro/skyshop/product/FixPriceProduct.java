package org.skypro.skyshop.product;

import org.skypro.skyshop.search.Searchable;

public class FixPriceProduct extends Product {
        private final int price;

        public FixPriceProduct(String name, int price) {
            super(name);
            if (price <=0) {
                throw new IllegalArgumentException("Цена продукта должна быть строго 0");
            }
            this.price = price;
        }

        @Override
        public int getPrice() {
        return price;
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
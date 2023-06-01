package com.example.shoppinglistjavafx;

import java.util.ArrayList;

public class ShoppingCategory {
    private final String categoryName;
    private final ArrayList<String> products;

    public ShoppingCategory(String categoryName, String product) {
        this.categoryName = categoryName;
        this.products = new ArrayList<>();
        this.products.add(product);
    }

    public void addProduct(String product) {
        if (!products.contains(product)) {
            products.add(product);
        }
    }

    public void deleteProduct(String product) {
        products.remove(product);
    }

    public void deleteAllProducts() {
        products.clear();
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public String getProductName(int productIndex) {
        return products.get(productIndex);
    }

    public int getCategorySize() {
        return products.size();
    }

}

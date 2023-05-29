package com.example.shoppinglistjavafx;

import java.util.ArrayList;

public class ShoppingCategory {
    private String categoryName;

    private ArrayList<String> products;

    public ShoppingCategory(String categoryName, String product) {
        this.categoryName = categoryName;
        this.products = new ArrayList<String>();
        this.products.add(product);
    }

    public void addProduct(String product) {
        if (products.contains(product)) {
            System.out.println("Produkt juz znajduje sie na liscie");
            return;
        }
        products.add(product);
    }

    public void deleteProduct(String product) {
        products.remove(product);
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

    public void deleteAllProducts() {
        products.clear();
    }
}

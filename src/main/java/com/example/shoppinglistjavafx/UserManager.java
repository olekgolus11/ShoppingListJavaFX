package com.example.shoppinglistjavafx;

import java.util.Scanner;

public class UserManager {
    private ShoppingList shoppingList;

    private ShoppingList availableProductsShoppingList;

    public UserManager() {
        shoppingList = new ShoppingList();
        shoppingList.loadShoppingList("ShoppingList.txt");
        availableProductsShoppingList = new ShoppingList();
        availableProductsShoppingList.loadShoppingList("AllProducts.txt");
    }

    public int selectCategory() {
        Scanner scanner = new Scanner(System.in);
        int selectedCategory;
        try {
            selectedCategory = scanner.nextInt() - 1;
        } catch (Exception e) {
            System.out.println("Podano niewlasciwy numer");
            return -1;
        }
        if (selectedCategory < 0) {
            System.out.println("Podano niewlasciwy numer");
            return -1;
        }
        return selectedCategory;
    }

    public int selectProduct(int selectedCategoryIndex) {
        Scanner scanner = new Scanner(System.in);
        int selectedProduct;
        try {
            selectedProduct = scanner.nextInt() - 1;
        } catch (Exception e) {
            System.out.println("Podano niewlasciwy numer");
            return -1;
        }
        if (selectedProduct < 0 || selectedProduct >= availableProductsShoppingList.getCategories().get(selectedCategoryIndex).getCategorySize()) {
            System.out.println("Podano niewlasciwy numer");
            return -1;
        }
        return selectedProduct;
    }

    public String getSelectedCategoryName(int selectedCategoryIndex) {
        return availableProductsShoppingList.getCategories().get(selectedCategoryIndex).getCategoryName();
    }

    public String getSelectedProductName(int selectedCategoryIndex, int selectedProductIndex) {
        return availableProductsShoppingList.getCategories().get(selectedCategoryIndex).getProductName(selectedProductIndex);
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public ShoppingList getAvailableProductsShoppingList() {
        return availableProductsShoppingList;
    }
}

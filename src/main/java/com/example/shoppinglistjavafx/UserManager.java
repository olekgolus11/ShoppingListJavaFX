package com.example.shoppinglistjavafx;

public class UserManager {
    private ShoppingList shoppingList;
    private ShoppingList availableProductsShoppingList;

    public UserManager() {
        shoppingList = new ShoppingList();
        shoppingList.loadShoppingList("ShoppingList.txt");
        availableProductsShoppingList = new ShoppingList();
        availableProductsShoppingList.loadShoppingList("AllProducts.txt");
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public ShoppingList getAvailableProductsShoppingList() {
        return availableProductsShoppingList;
    }
}

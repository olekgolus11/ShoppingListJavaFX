package com.example.shoppinglistjavafx;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ShoppingListsManager {
    private final ShoppingList[] shoppingLists;
    private final ShoppingList availableProductsShoppingList;
    private int shoppingListsNumber;

    public ShoppingListsManager() {
        setShoppingListsNumber();
        shoppingLists = new ShoppingList[shoppingListsNumber];
        loadShoppingLists();
        availableProductsShoppingList = new ShoppingList();
        availableProductsShoppingList.loadShoppingList("AllProducts.txt");
    }

    private void loadShoppingLists() {
        String filenameStartPath = "ShoppingList";
        String filenameEndPath = ".txt";
        try {
            for (int i = 0; i < shoppingListsNumber; i++) {
                shoppingLists[i] = new ShoppingList();
                shoppingLists[i].loadShoppingList(filenameStartPath + i + filenameEndPath);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void setShoppingListsNumber() {
        String filename = "ShoppingListsInfo.txt";
        try {
            File inputFile = new File(filename);
            Scanner scanner = new Scanner(inputFile);
            shoppingListsNumber = Integer.parseInt(scanner.nextLine());
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ShoppingList getShoppingList(int shoppingListIndex) {
        return shoppingLists[shoppingListIndex];
    }

    public ShoppingList getAvailableProductsShoppingList() {
        return availableProductsShoppingList;
    }
}

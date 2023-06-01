package com.example.shoppinglistjavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingListsManager {
    private final ArrayList<ShoppingList> shoppingLists;
    private final ShoppingList availableProductsShoppingList;
    private int shoppingListsNumber;
    private int selectedShoppingListIndex;

    public ShoppingListsManager() {
        setShoppingListsNumber();
        shoppingLists = new ArrayList<>();
        loadShoppingLists();
        availableProductsShoppingList = new ShoppingList();
        availableProductsShoppingList.loadShoppingList("AllProducts.txt");
    }

    private void loadShoppingLists() {
        String filenameStartPath = "ShoppingList";
        String filenameEndPath = ".txt";
        try {
            for (int i = 0; i < shoppingListsNumber; i++) {
                shoppingLists.add(new ShoppingList());
                shoppingLists.get(i).loadShoppingList(filenameStartPath + i + filenameEndPath);
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

    public void addShoppingList() {
        shoppingLists.add(new ShoppingList());
        shoppingListsNumber += 1;
    }

    public void deleteShoppingList(int shoppingListIndex) {
        shoppingLists.remove(shoppingListIndex);
        shoppingListsNumber -= 1;
    }

    public ShoppingList getShoppingList(int shoppingListIndex) {
        return shoppingLists.get(shoppingListIndex);
    }

    public ShoppingList getAvailableProductsShoppingList() {
        return availableProductsShoppingList;
    }

    public ObservableList<String> getShoppingListsNumberArray() {
        ArrayList<String> shoppingListsNumberArray = new ArrayList<>();
        for (int i = 0; i < shoppingListsNumber; i++) {
            shoppingListsNumberArray.add(String.valueOf(i));
        }
        return FXCollections.observableArrayList(shoppingListsNumberArray);
    }

    public int getSelectedShoppingListIndex() {
        return selectedShoppingListIndex;
    }

    public void setSelectedShoppingListIndex(int selectedShoppingListIndex) {
        this.selectedShoppingListIndex = selectedShoppingListIndex;
    }
}

package com.example.shoppinglistjavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingList {
    private final ArrayList<ShoppingCategory> categories;
    private int shoppingListSize;

    public ShoppingList() {
        shoppingListSize = 0;
        categories = new ArrayList<>();
    }

    public int findCategoryIndex(String categoryName) {
        int categoryIndex = 0;
        for (; categoryIndex < shoppingListSize; categoryIndex++) {
            if (categoryName.equals(categories.get(categoryIndex).getCategoryName())) {
                return categoryIndex;
            }
        }
        return -1;
    }

    public void addCategory(String categoryName, String product) {
        categories.add(new ShoppingCategory(categoryName, product));
        shoppingListSize += 1;
    }

    public void addProductFromTextfile(String productEntry) {
        String category = productEntry.substring(0, productEntry.indexOf(','));
        String product = productEntry.substring(productEntry.indexOf(' ') + 1);

        int categoryIndex = findCategoryIndex(category);
        if (categoryIndex == -1) addCategory(category, product);
        else categories.get(categoryIndex).addProduct(product);
    }

    public void addProduct(String product, String categoryName){
        int categoryIndex = findCategoryIndex(categoryName);
        if (categoryIndex == -1) addCategory(categoryName, product);
        else categories.get(categoryIndex).addProduct(product);
    }


    public void deleteAllProducts() {
        categories.clear();
        shoppingListSize = 0;
    }

    public void deleteAllProductsFromCategory(int categoryIndex) {
        if (categoryIndex < 0 || categoryIndex >= shoppingListSize) {
            throw new IllegalArgumentException("Nie ma takiej kategorii");
        }

        ShoppingCategory currentCategory = categories.get(categoryIndex);
        currentCategory.deleteAllProducts();
        categories.remove(currentCategory);
        shoppingListSize -= 1;
    }

    public void deleteProductFromCategory(int categoryIndex, String productName) {
        if (categoryIndex < 0 || categoryIndex >= shoppingListSize) {
            throw new IllegalArgumentException("Nie ma takiej kategorii");
        }

        ShoppingCategory currentCategory = categories.get(categoryIndex);
        currentCategory.deleteProduct(productName);

        if (currentCategory.getCategorySize() == 0) {
            categories.remove(currentCategory);
            shoppingListSize -= 1;
        }
    }

    public void loadShoppingList(String filename) {
        try {
            File inputFile = new File(filename);
            Scanner scanner = new Scanner(inputFile);
            while (scanner.hasNextLine()) {
                this.addProductFromTextfile(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveShoppingList(int shoppingListIndex){
        try {
            FileWriter writer = new FileWriter("ShoppingList" + shoppingListIndex + ".txt");
            for (int categoryIndex = 0; categoryIndex < shoppingListSize; categoryIndex++) {
                ShoppingCategory currentCategory = categories.get(categoryIndex);
                for (int productIndex = 0; productIndex < currentCategory.getCategorySize(); productIndex++) {
                    writer.write(currentCategory.getCategoryName() + ", " + currentCategory.getProductName(productIndex) + '\n');
                }
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<String> getAllProductsFromCategory(int categoryIndex) {
        if (categoryIndex < 0 || categoryIndex >= shoppingListSize) {
            throw new IllegalArgumentException("Nie ma takiej kategorii");
        }

        ShoppingCategory currentCategory = categories.get(categoryIndex);
        String[] products = new String[currentCategory.getCategorySize()];
        for (int productIndex = 0; productIndex < currentCategory.getCategorySize(); productIndex++) {
            products[productIndex] = currentCategory.getProductName(productIndex);
        }
        return FXCollections.observableArrayList(products);
    }

    public ArrayList<ShoppingCategory> getCategories() {
        return categories;
    }


    public ObservableList<String> getAllCategoryNames() {
        String[] categoryNames = new String[shoppingListSize];
        for (int categoryIndex = 0; categoryIndex < shoppingListSize; categoryIndex++) {
            ShoppingCategory currentCategory = categories.get(categoryIndex);
            categoryNames[categoryIndex] = currentCategory.getCategoryName();
        }
        return FXCollections.observableArrayList(categoryNames);
    }
}

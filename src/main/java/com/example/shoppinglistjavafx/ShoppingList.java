package com.example.shoppinglistjavafx;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingList {
    private ArrayList<ShoppingCategory> categories;

    private int shoppingListSize;

    private int findCategoryIndex(String categoryName) {
        int categoryIndex = 0;
        for (; categoryIndex < shoppingListSize; categoryIndex++) {
            if (categoryName.equals(categories.get(categoryIndex).getCategoryName())) {
                return categoryIndex;
            }
        }
        return -1;
    }

    public ShoppingList() {
        shoppingListSize = 0;
        categories = new ArrayList<ShoppingCategory>();
    }

    public void addCategory(String categoryName, String product) {
        categories.add(new ShoppingCategory(categoryName, product));
        shoppingListSize += 1;
    }

    public void addProduct(String productEntry) {
        String category = productEntry.substring(0, productEntry.indexOf(','));
        String product = productEntry.substring(productEntry.indexOf(' ') + 1);

        int categoryIndex = findCategoryIndex(category);
        if (categoryIndex == -1) addCategory(category, product);
        else categories.get(categoryIndex).addProduct(product);
    }

    public void viewAllProducts() {
        if (shoppingListSize == 0) {
            System.out.println("Lista jest pusta");
            return;
        }
        for (int categoryIndex = 0; categoryIndex < shoppingListSize; categoryIndex++) {
            ShoppingCategory currentCategory = categories.get(categoryIndex);
            System.out.println(currentCategory.getCategoryName() + ":");
            for (int productIndex = 0; productIndex < currentCategory.getCategorySize(); productIndex++) {
                System.out.println("   " + currentCategory.getProductName(productIndex));
            }
        }
    }

    public boolean viewAllProductsFromCategory(int categoryIndex) {
        if (categoryIndex < 0 || categoryIndex >= shoppingListSize) {
            System.out.println("Nie ma takiej kategorii");
            return false;
        }

        ShoppingCategory currentCategory = categories.get(categoryIndex);
        System.out.println(currentCategory.getCategoryName() + ":");
        for (int productIndex = 0; productIndex < currentCategory.getCategorySize(); productIndex++) {
            System.out.println("   " + (productIndex + 1) + ". " + currentCategory.getProductName(productIndex));
        }
        return true;
    }

    public void viewAllCategoryNames() {
        for (int categoryIndex = 0; categoryIndex < shoppingListSize; categoryIndex++) {
            ShoppingCategory currentCategory = categories.get(categoryIndex);
            System.out.println((categoryIndex + 1) + ". " + currentCategory.getCategoryName());
        }
    }

    public void deleteAllProducts() {
        categories.clear();
        shoppingListSize = 0;
    }

    public void deleteAllProductsFromCategory(int categoryIndex) {
        if (categoryIndex < 0 || categoryIndex >= shoppingListSize) {
            System.out.println("Nie ma takiej kategorii");
            return;
        }

        ShoppingCategory currentCategory = categories.get(categoryIndex);
        currentCategory.deleteAllProducts();
        categories.remove(currentCategory);
        shoppingListSize -= 1;
    }

    public void deleteProductFromCategory(int categoryIndex, int productIndex) {
        if (categoryIndex < 0 || categoryIndex >= shoppingListSize) {
            System.out.println("Nie ma takiej kategorii");
            return;
        }
        if (productIndex < 0 || productIndex >= categories.get(categoryIndex).getCategorySize()) {
            System.out.println("Podano niewlasciwy numer produktu");
            return;
        }

        ShoppingCategory currentCategory = categories.get(categoryIndex);
        String productName = currentCategory.getProductName(productIndex);
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
                this.addProduct(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ShoppingCategory> getCategories() {
        return categories;
    }

    public void saveShoppingList(){
        try {
            FileWriter writer = new FileWriter("ShoppingList.txt");
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

    public int getShoppingListSize(){
        return shoppingListSize;
    }
}

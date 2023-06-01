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

    public void showAllOptions() {
        System.out.println("1. Dodaj produkt do listy zakupów");
        System.out.println("2. Wyświetl całą listę zakupów");
        System.out.println("3. Wyświetl wszystkie produkty z wybranej kategorii");
        System.out.println("4. Zresetuj listę zakupów");
        System.out.println("5. Usuń wszystkie produkty z wybranej kategorii");
        System.out.println("6. Usuń produkt z wybranej kategorii");
        System.out.println("7. Zapisz listę");
        System.out.println("8. Zakończ program");
        System.out.println("Wybierz opcję: ");
    }

    public boolean selectOption() {
        Scanner scanner = new Scanner(System.in);
        int option;
        try {
            option = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Podano niewlasciwy numer");
            return true;
        }
        int selectedCategoryIndex, selectedProductIndex;

        switch (option) {
            case 1 -> {
                System.out.println("\nWybierz kategorię:");
                availableProductsShoppingList.viewAllCategoryNames();
                if ((selectedCategoryIndex = selectCategory()) == -1) break;
                System.out.println("\nWybierz produkt:");
                if (availableProductsShoppingList.viewAllProductsFromCategory(selectedCategoryIndex)) {
                    if ((selectedProductIndex = selectProduct(selectedCategoryIndex)) == -1) break;
                    shoppingList.addProductFromTextfile(getSelectedCategoryName(selectedCategoryIndex) + ", " + getSelectedProductName(selectedCategoryIndex, selectedProductIndex));
                }
            }
            case 2 -> {
                System.out.println("\nTwoja lista zakupów:");
                shoppingList.viewAllProducts();
            }
            case 3 -> {
                System.out.println("\nWybierz kategorię:");
                if ((selectedCategoryIndex = selectCategory()) == -1) break;
                System.out.println("\nTwoja lista zakupów:");
                shoppingList.viewAllProductsFromCategory(selectedCategoryIndex);
            }
            case 4 -> {
                if (shoppingList.getShoppingListSize() == 0) {
                    System.out.println("Twoja lista już jest pusta");
                    break;
                }
                shoppingList.deleteAllProducts();
            }
            case 5 -> {
                if (shoppingList.getShoppingListSize() == 0) {
                    System.out.println("Twoja lista już jest pusta");
                    break;
                }
                System.out.println("\nWybierz kategorię:");
                shoppingList.viewAllCategoryNames();
                if ((selectedCategoryIndex = selectCategory()) == -1) break;
                shoppingList.deleteAllProductsFromCategory(selectedCategoryIndex);
            }
            case 6 -> {
                if (shoppingList.getShoppingListSize() == 0) {
                    System.out.println("Twoja lista już jest pusta");
                    break;
                }
                System.out.println("\nWybierz kategorię:");
                shoppingList.viewAllCategoryNames();
                if ((selectedCategoryIndex = selectCategory()) == -1) break;
                System.out.println("\nWybierz produkt:");
                if (shoppingList.viewAllProductsFromCategory(selectedCategoryIndex)) {
                    if ((selectedProductIndex = selectProduct(selectedCategoryIndex)) == -1) break;
                    shoppingList.deleteProductFromCategory(selectedCategoryIndex, selectedProductIndex);
                }
            }
            case 7 -> shoppingList.saveShoppingList();
            case 8 -> {
                return false;
            }
            default -> System.out.println("\nWybrano niewlasciwa opcje");
        }
        System.out.println();
        return true;
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

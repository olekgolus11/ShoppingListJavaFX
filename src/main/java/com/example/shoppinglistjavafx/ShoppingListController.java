package com.example.shoppinglistjavafx;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import utilities.ListAction;

public class ShoppingListController extends UserManager {

    private boolean isCategorySelected;
    private int selectedCategoryIndex;
    private String selectedCategoryName;
    private ListAction selectedAction;
    public ListView productsList;

    @FXML
    private Label selectedOptionText;

    private void displayAllAvailableProducts() {
        selectedCategoryName = (String) productsList.getSelectionModel().getSelectedItem();
        selectedCategoryIndex = this.getAvailableProductsShoppingList().findCategoryIndex(selectedCategoryName);
        ObservableList<String> items = this.getAvailableProductsShoppingList().getAllProductsFromCategory(selectedCategoryIndex);
        productsList.setItems(items);
    }

    private void displayAllProducts() {
        selectedCategoryName = (String) productsList.getSelectionModel().getSelectedItem();
        selectedCategoryIndex = this.getShoppingList().findCategoryIndex(selectedCategoryName);
        ObservableList<String> items = this.getShoppingList().getAllProductsFromCategory(selectedCategoryIndex);
        productsList.setItems(items);
    }

    private void addProductToList() {
        String selectedProduct = (String) productsList.getSelectionModel().getSelectedItem();
        this.getShoppingList().addProduct(selectedProduct, selectedCategoryName);
        productsList.setItems(null);
    }

    private void resetList() {
        this.getShoppingList().deleteAllProducts();
        productsList.setItems(null);
    }

    private void deleteAllItemsFromCategory() {
        this.getShoppingList().deleteAllProductsFromCategory(selectedCategoryIndex);
        productsList.setItems(null);
    }

    private void deleteItemFromCategory() {
        String selectedProduct = (String) productsList.getSelectionModel().getSelectedItem();
        this.getShoppingList().deleteProductFromCategory(selectedCategoryIndex, selectedProduct);
        productsList.setItems(null);
    }


    @FXML
    protected void handleSelectedListItem(MouseEvent event) {
        switch (selectedAction) {
            case ADD_PRODUCT -> {
                if (!isCategorySelected) {
                    displayAllAvailableProducts();
                } else {
                    addProductToList();
                }
            }
            case DISPLAY_ALL_PRODUCTS -> {
                if (!isCategorySelected) displayAllProducts();
            }
            case DISPLAY_ALL_AVAILABLE_PRODUCTS -> {
                if (!isCategorySelected) displayAllAvailableProducts();
            }
            case DELETE_ALL_PRODUCTS -> {
                if (!isCategorySelected) deleteAllItemsFromCategory();
            }
            case DELETE_PRODUCT -> {
                if (!isCategorySelected){
                    displayAllProducts();
                } else {
                    deleteItemFromCategory();
                }
            }
            default -> {
            }
        }

        isCategorySelected = true;
    }

    @FXML
    protected void onAddProductButtonClick() {
        selectedAction = ListAction.ADD_PRODUCT;
        selectedOptionText.setText("Add product");
        ObservableList<String> items = this.getAvailableProductsShoppingList().getAllCategoryNames();
        productsList.setItems(items);
        isCategorySelected = false;
    }

    @FXML
    protected void onDisplayAllProductsButtonClick() {
        selectedAction = ListAction.DISPLAY_ALL_PRODUCTS;
        selectedOptionText.setText("Display all added products from selected category");
        ObservableList<String> items = this.getShoppingList().getAllCategoryNames();
        productsList.setItems(items);
        isCategorySelected = false;
    }

    @FXML
    protected void onDisplayAllAvailableProductsButtonClick() {
        selectedAction = ListAction.DISPLAY_ALL_AVAILABLE_PRODUCTS;
        selectedOptionText.setText("Display all products from selected category");
        ObservableList<String> items = this.getAvailableProductsShoppingList().getAllCategoryNames();
        productsList.setItems(items);
        isCategorySelected = false;
    }

    @FXML
    protected void onResetShoppingListButtonClick() {
        selectedAction = ListAction.RESET_LIST;
        resetList();
        selectedOptionText.setText("Reset shopping list");
    }

    @FXML
    protected void onDeleteAllProductsFromCategoryButtonClick() {
        selectedAction = ListAction.DELETE_ALL_PRODUCTS;
        ObservableList<String> items = this.getShoppingList().getAllCategoryNames();
        productsList.setItems(items);
        selectedOptionText.setText("Delete all products from category");
        isCategorySelected = false;
    }

    @FXML
    protected void onDeleteProductFromCategoryButtonClick() {
        selectedAction = ListAction.DELETE_PRODUCT;
        ObservableList<String> items = this.getShoppingList().getAllCategoryNames();
        productsList.setItems(items);
        selectedOptionText.setText("Delete product from category");
        isCategorySelected = false;
    }

    @FXML
    protected void onSaveListButtonClick() {
        selectedAction = ListAction.SAVE_LIST;
        this.getShoppingList().saveShoppingList();
        selectedOptionText.setText("Save list");
    }

    @FXML
    protected void onExitButtonClick() {
        selectedAction = ListAction.EXIT;
        selectedOptionText.setText("Exit");
        System.exit(0);
    }
}
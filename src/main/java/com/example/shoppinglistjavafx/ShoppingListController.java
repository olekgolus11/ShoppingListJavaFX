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
        selectedOptionText.setText("Reset shopping list");
    }

    @FXML
    protected void onDeleteAllProductsFromCategoryButtonClick() {
        selectedAction = ListAction.DELETE_ALL_PRODUCTS;
        selectedOptionText.setText("Delete all products from category");
    }

    @FXML
    protected void onDeleteProductFromCategoryButtonClick() {
        selectedAction = ListAction.DELETE_PRODUCT;
        selectedOptionText.setText("Delete product from category");
    }

    @FXML
    protected void onSaveListButtonClick() {
        selectedAction = ListAction.SAVE_LIST;
        selectedOptionText.setText("Save list");
    }

    @FXML
    protected void onExitButtonClick() {
        selectedAction = ListAction.EXIT;
        selectedOptionText.setText("Exit");
    }
}
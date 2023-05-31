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
    private ListAction selectedAction;
    public ListView productsList;

    @FXML
    private Label selectedOptionText;

    private void displayAllAvailableProducts() {
        String selectedCategory = (String) productsList.getSelectionModel().getSelectedItem();
        int selectedCategoryIndex = this.getAvailableProductsShoppingList().findCategoryIndex(selectedCategory);
        ObservableList<String> items = this.getAvailableProductsShoppingList().getAllProductsFromCategory(selectedCategoryIndex);
        productsList.setItems(items);
    }

    private void displayAllProducts() {
        String selectedCategory = (String) productsList.getSelectionModel().getSelectedItem();
        int selectedCategoryIndex = this.getShoppingList().findCategoryIndex(selectedCategory);
        ObservableList<String> items = this.getShoppingList().getAllProductsFromCategory(selectedCategoryIndex);
        productsList.setItems(items);
    }


    @FXML
    protected void handleSelectedListItem(MouseEvent event) {
        if (isCategorySelected) return;

        switch (selectedAction) {
            case DISPLAY_ALL_PRODUCTS -> displayAllProducts();
            case DISPLAY_ALL_AVAILABLE_PRODUCTS -> displayAllAvailableProducts();
            default -> {
            }
        }

        isCategorySelected = true;
    }

    @FXML
    protected void onAddProductButtonClick() {
        selectedAction = ListAction.ADD_PRODUCT;
        selectedOptionText.setText("Add product");
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
        ObservableList<String> items = this.getShoppingList().getAllCategoryNames();
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
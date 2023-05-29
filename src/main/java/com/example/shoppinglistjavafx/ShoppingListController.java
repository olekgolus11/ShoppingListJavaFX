package com.example.shoppinglistjavafx;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ShoppingListController extends UserManager {
    public ListView productsList;

    @FXML
    private Label selectedOptionText;


    @FXML
    protected void onAddProductButtonClick() {
        selectedOptionText.setText("Add product");
    }

    @FXML
    protected void onDisplayAllProductsButtonClick() {
        selectedOptionText.setText("Display all added products");
    }

    @FXML
    protected void onDisplayAllProductsFromCategoryButtonClick() {
        selectedOptionText.setText("Display all products from category");
        ObservableList<String> items = this.getShoppingList().getAllProductsFromCategory(0);
        productsList.setItems(items);
    }

    @FXML
    protected void onResetShoppingListButtonClick() {
        selectedOptionText.setText("Reset shopping list");
    }

    @FXML
    protected void onDeleteAllProductsFromCategoryButtonClick() {
        selectedOptionText.setText("Delete all products from category");
    }

    @FXML
    protected void onDeleteProductFromCategoryButtonClick() {
        selectedOptionText.setText("Delete product from category");
    }

    @FXML
    protected void onSaveListButtonClick() {
        selectedOptionText.setText("Save list");
    }

    @FXML
    protected void onExitButtonClick() {
        selectedOptionText.setText("Exit");
    }
}
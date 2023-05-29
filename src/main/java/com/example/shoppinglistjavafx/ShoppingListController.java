package com.example.shoppinglistjavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ShoppingListController {
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
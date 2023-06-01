package com.example.shoppinglistjavafx;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import utilities.ListAction;

public class ShoppingListController extends ShoppingListsManager {

    private boolean isCategorySelected;
    private int selectedCategoryIndex;
    private String selectedCategoryName;
    private ListAction selectedAction;
    public ListView productsList;
    public ListView shoppingListsList;

    public ShoppingListController() {
        super();
        productsList = new ListView();
        shoppingListsList = new ListView();
        isCategorySelected = false;
    }

    @FXML
    private Label selectedOptionText;

    @FXML
    private Label selectedShoppingListText;

    private void displayShoppingLists() {
        ObservableList<String> items = this.getShoppingListsNumberArray();
        shoppingListsList.setItems(items);
    }

    private String getSelectedItem() {
        return (String) productsList.getSelectionModel().getSelectedItem();
    }

    private void displayAllAvailableProducts() {
        selectedCategoryName = getSelectedItem();
        selectedCategoryIndex = this.getAvailableProductsShoppingList().findCategoryIndex(selectedCategoryName);
        ObservableList<String> items = this.getAvailableProductsShoppingList().getAllProductsFromCategory(selectedCategoryIndex);
        productsList.setItems(items);
    }

    private void displayAllProducts() {
        selectedCategoryName = getSelectedItem();
        selectedCategoryIndex = this.getShoppingList(this.getSelectedShoppingListIndex()).findCategoryIndex(selectedCategoryName);
        ObservableList<String> items = this.getShoppingList(this.getSelectedShoppingListIndex()).getAllProductsFromCategory(selectedCategoryIndex);
        productsList.setItems(items);
    }

    private void addProductToList() {
        String selectedProduct = getSelectedItem();
        this.getShoppingList(this.getSelectedShoppingListIndex()).addProduct(selectedProduct, selectedCategoryName);
        productsList.setItems(null);
    }

    private void resetList() {
        this.getShoppingList(this.getSelectedShoppingListIndex()).deleteAllProducts();
        productsList.setItems(null);
    }

    private void deleteAllItemsFromCategory() {
        this.getShoppingList(this.getSelectedShoppingListIndex()).deleteAllProductsFromCategory(selectedCategoryIndex);
        productsList.setItems(null);
    }

    private void deleteItemFromCategory() {
        String selectedProduct = getSelectedItem();
        this.getShoppingList(this.getSelectedShoppingListIndex()).deleteProductFromCategory(selectedCategoryIndex, selectedProduct);
        productsList.setItems(null);
    }

    private void createNewShoppingList(){
        this.addShoppingList();
    }

    @FXML
    protected void handleSelectedListItem() {
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
        }
        isCategorySelected = true;
    }

    @FXML
    protected void handleSelectedShoppingList() {
        this.setSelectedShoppingListIndex(shoppingListsList.getSelectionModel().getSelectedIndex());
        shoppingListsList.setItems(null);
    }

    @FXML
    protected void onAddProductButtonClick() {
        selectedAction = ListAction.ADD_PRODUCT;
        selectedOptionText.setText("Add product");
        productsList.setItems(this.getAvailableProductsShoppingList().getAllCategoryNames());
        isCategorySelected = false;
        displayShoppingLists();
    }

    @FXML
    protected void onDisplayAllProductsButtonClick() {
        selectedAction = ListAction.DISPLAY_ALL_PRODUCTS;
        selectedOptionText.setText("Display all added products from selected category");
        productsList.setItems(this.getShoppingList(this.getSelectedShoppingListIndex()).getAllCategoryNames());
        isCategorySelected = false;
    }

    @FXML
    protected void onDisplayAllAvailableProductsButtonClick() {
        selectedAction = ListAction.DISPLAY_ALL_AVAILABLE_PRODUCTS;
        selectedOptionText.setText("Display all products from selected category");
        productsList.setItems(this.getAvailableProductsShoppingList().getAllCategoryNames());
        isCategorySelected = false;
    }

    @FXML
    protected void onResetShoppingListButtonClick() {
        selectedAction = ListAction.RESET_LIST;
        selectedOptionText.setText("Reset shopping list");
        resetList();
    }

    @FXML
    protected void onDeleteAllProductsFromCategoryButtonClick() {
        selectedAction = ListAction.DELETE_ALL_PRODUCTS;
        selectedOptionText.setText("Delete all products from category");
        productsList.setItems(this.getShoppingList(this.getSelectedShoppingListIndex()).getAllCategoryNames());
        isCategorySelected = false;
    }

    @FXML
    protected void onDeleteProductFromCategoryButtonClick() {
        selectedAction = ListAction.DELETE_PRODUCT;
        selectedOptionText.setText("Delete product from category");
        productsList.setItems(this.getShoppingList(this.getSelectedShoppingListIndex()).getAllCategoryNames());
        isCategorySelected = false;
    }

    @FXML
    protected void onSaveListButtonClick() {
        selectedAction = ListAction.SAVE_LIST;
        selectedOptionText.setText("Save list");
        this.getShoppingList(this.getSelectedShoppingListIndex()).saveShoppingList(this.getSelectedShoppingListIndex());
    }

    @FXML
    protected void onExitButtonClick() {
        selectedOptionText.setText("Exit");
        selectedAction = ListAction.EXIT;
        System.exit(this.getSelectedShoppingListIndex());
    }

    @FXML
    protected void onCreateNewShoppingListButtonClick() {
        selectedShoppingListText.setText("New shopping list");
        this.createNewShoppingList();
        shoppingListsList.setItems(null);
    }

    @FXML
    protected void onSelectShoppingListButtonClick() {
        selectedShoppingListText.setText("Selected shopping list");
        displayShoppingLists();
    }
}
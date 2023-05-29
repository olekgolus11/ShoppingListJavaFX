package com.example.shoppinglistjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader shoppingListView = new FXMLLoader(Main.class.getResource("shopping-list-view.fxml"));
        Scene shoppingListScene = new Scene(shoppingListView.load(), 800, 600);
        stage.setTitle("Shopping list");
        stage.setScene(shoppingListScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

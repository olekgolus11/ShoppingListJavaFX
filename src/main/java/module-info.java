module com.example.shoppinglistjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.shoppinglistjavafx to javafx.fxml;
    exports com.example.shoppinglistjavafx;
}
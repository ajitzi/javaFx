module com.example.color {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens ajitzi.color to javafx.fxml;
    exports ajitzi.color;
    exports ajitzi.color.controller;
    opens ajitzi.color.controller to javafx.fxml;
}
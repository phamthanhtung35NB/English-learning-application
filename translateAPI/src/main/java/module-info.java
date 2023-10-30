module com.example.translateapi {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.translateapi to javafx.fxml;
    exports com.example.translateapi;
}
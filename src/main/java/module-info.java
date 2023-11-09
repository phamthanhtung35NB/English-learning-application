module com.example.baitaplon {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.mediaEmpty;
    requires javafx.web;
    requires java.sql;

    opens com.example.baitaplon to javafx.fxml;
    exports com.example.baitaplon;
}
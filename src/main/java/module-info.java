module com.example.baitaplon {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.mediaEmpty;

    opens com.example.baitaplon to javafx.fxml;
    exports com.example.baitaplon;
}
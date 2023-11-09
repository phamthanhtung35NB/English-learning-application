module com.example.baitaplon {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.mediaEmpty;
    requires javafx.web;
//<<<<<<< HEAD
//    requires java.sql;
//=======
    requires com.google.auth.oauth2; // Add this line to declare the dependency
    requires firebase.admin;
    requires com.google.auth;
//    requires com.google.api.core;
    requires com.google.api.apicommon;
    requires com.google.common;
    requires java.desktop;
    requires java.sql;
    requires mysql.connector.java;
//
//>>>>>>> 4582dbf7e639c9c3d37872950377ecbb316c536a

    opens com.example.baitaplon to javafx.fxml;
    exports com.example.baitaplon;
//    exports com.example.baitaplon.testLogin;
//    opens com.example.baitaplon.testLogin to javafx.fxml;
}
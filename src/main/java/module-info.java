module com.example.baitaplon {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.mediaEmpty;
    requires javafx.web;

    requires com.google.auth.oauth2;
    requires firebase.admin;
    requires com.google.auth;

    requires com.google.api.apicommon;
    requires com.google.common;
    requires java.desktop;
    requires java.sql;
    requires mysql.connector.java;
    requires java.google.speech.api;
    requires jlayer;
    requires json;
    requires java.flac.encoder;
    requires org.mongodb.driver.core;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires javafx.media;
//    requires mongodb.driver;


    opens com.example.baitaplon to javafx.fxml;
    exports com.example.baitaplon;
//    exports com.example.baitaplon.Game;
//    opens com.example.baitaplon.Game to javafx.fxml;
//    exports com.example.baitaplon.DataBase;
//    opens com.example.baitaplon.DataBase to javafx.fxml;
//    exports com.example.baitaplon.Login;
//    opens com.example.baitaplon.Login to javafx.fxml;
//    opens com.example.baitaplon.Search to javafx.fxml;
//    exports com.example.baitaplon.Search;
//    exports com.example.baitaplon.NoteBook;
//    opens com.example.baitaplon.NoteBook to javafx.fxml;

}
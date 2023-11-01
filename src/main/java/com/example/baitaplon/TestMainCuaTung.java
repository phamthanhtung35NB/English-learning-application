package com.example.baitaplon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TestMainCuaTung extends Application {
    @Override
//    public void start(Stage stage) throws IOException {
//        Parent fxml = FXMLLoader.load(getClass().getResource("Login.fxml"));
//        Scene scene = new Scene(fxml);
//        scene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
//    }
    public void start(Stage stage) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene scene = new Scene(fxml);
        scene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}

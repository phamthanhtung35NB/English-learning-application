package com.example.baitaplon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
//        Parent fxml2 = FXMLLoader.load(getClass().getResource("Login.fxml"));
//        Scene scene2 = new Scene(fxml2);
//        scene2.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
//        stage.setTitle("login!");


        Parent fxml = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene scene = new Scene(fxml);
        stage.setTitle("Home!");

        scene.getStylesheets().add(getClass().getResource("Home.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

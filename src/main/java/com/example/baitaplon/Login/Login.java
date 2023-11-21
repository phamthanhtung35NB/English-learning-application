package com.example.baitaplon.Login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Login extends Application {
    protected Scene scene = null;
    protected Stage stage;
    Parent root = null;



    public void start(Stage stage) throws IOException {
        System.out.println('3');
        Parent fxml = FXMLLoader.load(getClass().getResource("Login.fxml"));
        scene = new Scene(fxml);
        scene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
        stage.setTitle("login!");
        System.out.println('4');

        stage.setScene(scene);
        stage.show();
        System.out.println('5');
    }
    public static void main(String[] args) {
        System.out.println('1');
        launch();
        System.out.println('2');
    }
}
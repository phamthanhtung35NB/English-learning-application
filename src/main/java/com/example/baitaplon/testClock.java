package com.example.baitaplon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class testClock extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TabHome.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Clock");
        scene.getStylesheets().add(getClass().getResource("TabHome.css").toExternalForm());
//        stage.setScene(new Scene(root, 800, 600));
        stage.setScene(scene);
//        ClockController controller = loader.getController();
//        controller.initialize();
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
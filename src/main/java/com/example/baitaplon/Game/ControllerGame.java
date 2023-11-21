package com.example.baitaplon.Game;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class ControllerGame extends Application {

    @FXML
    SplitPane setupGame;
    @FXML
    public Slider difficultySlider;
    public int difficultyHangman = 3;

    //ham khoir tao
    @FXML
    public void initialize() {
        // Khoi tao cac gia tri ban dau


    }

    @FXML
    private void letGo() {
        // Mở mic hoặc thực hiện các thao tác cần thiết khi bắt đầu thu âm
        System.out.println("letGo started");
        difficultyHangman = (int) difficultySlider.getValue();
        System.out.println(difficultyHangman);
    }

    @FXML
    private void hangman() throws IOException {
        System.out.println("buttonButton");
        System.out.println("1");
        AnchorPane view = FXMLLoader.load(getClass().getResource("TabGameHangman.fxml"));
        view.getStylesheets().add(getClass().getResource("Hangman.css").toExternalForm());
        System.out.println("2");
        setupGame.getItems().set(0, view);
        System.out.println("3");
        System.out.println("hangman started");
        difficultySlider.setMin(2);
        difficultySlider.setMax(5);
        difficultySlider.setValue(3);
        difficultySlider.setShowTickMarks(true);
        difficultySlider.setMajorTickUnit(1);
    }

    @FXML
    private void pronounce() {
        // Mở mic hoặc thực hiện các thao tác cần thiết khi bắt đầu thu âm
        System.out.println("pronounce started");
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent fxml = FXMLLoader.load(getClass().getResource("TabGame.fxml"));
        Scene scene = new Scene(fxml);
        stage.setTitle("Home!");

        scene.getStylesheets().add(getClass().getResource("Hangman.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }


}

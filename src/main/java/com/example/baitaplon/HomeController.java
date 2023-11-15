package com.example.baitaplon;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class HomeController  {


//    @FXML
//    private Field tesst;

    @FXML
    private BorderPane centerBorderPane;
    @FXML
    private TextField textName;
    @FXML
    private PasswordField textPass;

    @FXML
    protected void buttonHome() {
        System.out.println("Home");
    }
    @FXML
    protected void buttonSearch() throws IOException{
        System.out.println("Search");
<<<<<<< HEAD
        System.out.println("1");
        AnchorPane view = FXMLLoader.load(getClass().getResource("FileTxt.fxml"));
        System.out.println("2");
        centerBorderPane.setCenter(view);
        System.out.println("3");
//        new ControllerSearchByFile().autoLoad();
=======
        AnchorPane view = FXMLLoader.load(getClass().getResource("FileTxt.fxml"));
        centerBorderPane.setCenter(view);
>>>>>>> b953160e984bee9caeee7297ae341bdd0ae7a07f
    }
    @FXML
    protected void buttonOnlineSearch() throws IOException{
        System.out.println("O Search");
        Pane view = FXMLLoader.load(getClass().getResource("GoogleApi.fxml"));
        centerBorderPane.setCenter(view);
    }
    @FXML
    protected void buttonGame(){
        System.out.println("Game");
    }
    @FXML
<<<<<<< HEAD
    protected void buttonButton() throws IOException {
        System.out.println("buttonButton");
        System.out.println("1");
        AnchorPane view = FXMLLoader.load(getClass().getResource("SoTayCaNhan.fxml"));
        System.out.println("2");
        centerBorderPane.setCenter(view);
        System.out.println("3");

    }
    @FXML
    protected void buttonFeedback(){
        System.out.println("Feed Back");
    }
=======
    protected void buttonFeedback(){
        System.out.println("Feed Back");
    }
    @FXML
    protected void buttonButton() throws IOException {
        System.out.println("buttonButton");

    }
>>>>>>> b953160e984bee9caeee7297ae341bdd0ae7a07f
}
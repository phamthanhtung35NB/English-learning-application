package com.example.baitaplon;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

import java.io.IOException;

public class HomeController  {


//    @FXML
//    private Field tesst;

    // centerBorderPane laf view chinh cua BorderPane
    @FXML
    protected BorderPane centerBorderPane;
    @FXML
    private TextField textName;
    @FXML
    private PasswordField textPass;

    @FXML
    private VBox vbox;
    @FXML
    public void abc123() {
        if (centerBorderPane.getLeft() != null) {
            centerBorderPane.setLeft(null);
            System.out.println("Thanh Top Ẩn");
        } else {
            centerBorderPane.setLeft(vbox);
            System.out.println("Thanh Top");
        }
    }
    @FXML
    public void initialize() throws IOException {
        buttonHome();
        System.out.println("Home");
        centerBorderPane.setOnMouseMoved(event -> {
            int x = (int)event.getX();
            int y = (int) event.getY();
            if ((x>=0&&x < 100)&&(y>=0&&y<750)) {
                if (centerBorderPane.getLeft() == null) {
                    centerBorderPane.setLeft(vbox);
                    System.out.println("show");
                }
            }else if ((x > 240&&x<1320)&&(y>=0&&y<750)){
                if (centerBorderPane.getLeft() != null) {
                    centerBorderPane.setLeft(null);
                    System.out.println("hide");
                }
            }
            System.out.println("Mouse Entered - X: " + x+ " Y: " + y );

        });
    }

    @FXML
    protected void buttonHome() throws IOException {
        System.out.println("Home");
        AnchorPane view = FXMLLoader.load(getClass().getResource("TabHome.fxml"));
        view.getStylesheets().add(getClass().getResource("TabHome.css").toExternalForm());
        System.out.println("2");
        centerBorderPane.setCenter(view);
        System.out.println("3");
    }
    @FXML
    protected void buttonSearch() throws IOException{
        System.out.println("Search");
        System.out.println("1");
        AnchorPane view = FXMLLoader.load(getClass().getResource("FileTxt.fxml"));
        System.out.println("2");
        centerBorderPane.setCenter(view);
        System.out.println("3");
//        new ControllerSearchByFile().autoLoad();

    }
    @FXML
    protected void buttonOnlineSearch() throws IOException{
        System.out.println("O Search");
        Pane view = FXMLLoader.load(getClass().getResource("GoogleApi.fxml"));
        centerBorderPane.setCenter(view);
    }
    @FXML
    protected void buttonGame() throws IOException {
        System.out.println("Game");
        GridPane view = FXMLLoader.load(getClass().getResource("Hangman.fxml"));
        centerBorderPane.setCenter(view);
    }
    @FXML
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

//    @FXML
//    protected void buttonButton() throws IOException {
//        System.out.println("buttonButton");
//
//    }

}
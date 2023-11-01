package com.example.baitaplon;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;

public class ControllerTestCuaTung {
    @FXML
    private TextField textName;
    @FXML
    private PasswordField textPass;

    @FXML
    protected void buttonOkLogin() {
        String name = textName.getText();
        String pass = textPass.getText();
        System.out.println(name + " " + pass);
        if (!("test".equals(name) && "12345".equals(pass))) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("WARNING");
            alert.setContentText("Account information or password is incorrect");
            alert.show();
        }
    }
    @FXML
    protected void buttonHome() {
        System.out.println("Home");
    }
    @FXML
    protected void buttonSearch() {
        System.out.println("Search");
    }
    @FXML
    protected void buttonOnlineSearch(){
        System.out.println("O Search");
    }
    @FXML
    protected void buttonGame(){
        System.out.println("Game");
    }
    @FXML
    protected void buttonFeedback(){
        System.out.println("Feed Back");
    }
    @FXML
    protected void buttonButton(){
        System.out.println("Feed Back");
    }


//    @FXML
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }
}
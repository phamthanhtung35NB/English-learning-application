package com.example.baitaplon;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ControllerHangman {
    @FXML
    private WebView webView;
    //tu bi mat
    @FXML
    private HBox hBoxButtonsSecret;
    //cac chu cai
    @FXML
    private HBox hBoxAlphabet;

    //thong bao
    @FXML
    private Label labelNotification;

    private static String wordToGuess = "hangmana";

    //tu bi mat chua duoc hien thi
    private StringBuilder wordHasNotGuessed = new StringBuilder("--------");
    // list cac nut bam
    private static List<Button> ListButtonsSecret;
    private static List<Button> ListAlphabetButtons;

    //so lan doan sai
    private static int countIncorrectGuesses = 0;


    /**
     * khoi tao game
     */
    @FXML
    public void initialize() {
        ListButtonsSecret = new ArrayList<>();
        // load nut bam vao hBoxButtonsSecret (tu bi mat)
        for (int i = 1; i < wordToGuess.length() + 1; i++) {
            Button button = new Button("-");
            // khi bam vao nut bam thi goi ham useHints
            button.setOnAction(event -> useHints(button));
            button.getStyleClass().add("button_");
            // them nut bam vao ListButtonsSecret
            ListButtonsSecret.add(button);
            // them nut bam vao hBoxButtonsSecret
            hBoxButtonsSecret.getChildren().add(button);
        }

        ListAlphabetButtons = new ArrayList<>();

        // Tạo nút cho mỗi chữ cái trong wordToGuess
        for (int i = 0; i < wordToGuess.length(); i++) {
            String letter = Character.toString(wordToGuess.charAt(i));
            Button button = new Button(letter);

            // Thiết lập sự kiện onAction
            button.setOnAction(event -> chooseLetters(button));
            button.getStyleClass().add("buttonChuCai");
            // Thêm nút vào ListAlphabetButtons
            ListAlphabetButtons.add(button);
            // Thêm nút vào hBoxAlphabet
            hBoxAlphabet.getChildren().add(button);
        }
        //random 5 chu cai
        for (int i = 0; i < 5; i++) {
            String letter = RandomKitu();
            Button button = new Button(letter);
            // Thiết lập sự kiện onAction
            button.setOnAction(event -> chooseLetters(button));
            button.getStyleClass().add("buttonChuCai");
            // Thêm nút vào ListAlphabetButtons
            ListAlphabetButtons.add(button);
            // Thêm nút vào hBoxAlphabet
            hBoxAlphabet.getChildren().add(button);
        }

        System.out.println("ListButtonsSecret: " + ListButtonsSecret);
        System.out.println("ListAlphabetButtons (shuffled): " + ListAlphabetButtons);
        // Xáo trộn ListAlphabetButtons
        Collections.shuffle(ListAlphabetButtons);
        System.out.println("after shuffle");
        System.out.println("ListButtonsSecret: " + ListButtonsSecret);
        System.out.println("ListAlphabetButtons (shuffled): " + ListAlphabetButtons);
        updateAlphabetButtons();

    }

    //update lai cac nut bam sau khi xao tron
    private void updateAlphabetButtons() {
        // Xóa sạch hBoxAlphabet
        hBoxAlphabet.getChildren().clear();
        // Thêm từng nút chữ cái từ ListAlphabetButtons vào hBoxAlphabet
        for (Button button : ListAlphabetButtons) {
            hBoxAlphabet.getChildren().add(button);
        }
    }

    //khi su dung goi y
    private void useHints(Button sourceButton) {
        // lay vi tri cua nut bam
        int buttonIndex = hBoxButtonsSecret.getChildren().indexOf(sourceButton);
        wordHasNotGuessed.setCharAt(buttonIndex, wordToGuess.charAt(buttonIndex));
        updatehBoxButtonsSecret();
        //neu doan dung thi thong bao
        if (wordHasNotGuessed.toString().equals(wordToGuess)) {
            labelNotification.setText("Congratulations! You guessed it!\n" +
                    "\tClick submit to continue");
            for (Button button : ListAlphabetButtons) {
                button.setDisable(false);
                button.getStyleClass().remove("button_");
                button.getStyleClass().add("button_Done");
            }
        }
    }

    /**
     * khi bam vao cac nut bam chua cac chu cai input
     *
     * @param sourceButton
     */
    private void chooseLetters(Button sourceButton) {
        String selectedLetter = sourceButton.getText();
        sourceButton.setDisable(true);
        if (wordToGuess.contains(selectedLetter)) {
            for (int i = 0; i < wordToGuess.length(); i++) {
                if (wordToGuess.charAt(i) == selectedLetter.charAt(0)) {
                    wordHasNotGuessed.setCharAt(i, selectedLetter.charAt(0));
                }
            }
            updatehBoxButtonsSecret();
            if (wordHasNotGuessed.toString().equals(wordToGuess)) {
                labelNotification.setText("Congratulations! You guessed the word!");
            }
        } else {
            labelNotification.setText("Incorrect guess. Try again.");
            countIncorrectGuesses++;
            System.out.println(countIncorrectGuesses);
        }
    }

    private void updatehBoxButtonsSecret() {
        // clear hBoxButtonsSecret
        hBoxButtonsSecret.getChildren().clear();
        //them tung nut bam laf cacs chu cai trong wordHasNotGuessed vao hBoxButtonsSecret
        for (int i = 0; i < wordHasNotGuessed.length(); i++) {
            Button button = new Button(Character.toString(wordHasNotGuessed.charAt(i)));
            if (wordHasNotGuessed.charAt(i) == '-') {
                button.getStyleClass().add("button_");
            } else {
                button.setDisable(true);
                button.getStyleClass().remove("button_");
                button.getStyleClass().add("button_Done");
            }
            hBoxButtonsSecret.getChildren().add(button);
        }
    }

    public static String RandomKitu() {
        Random random = new Random();
        // 'a' là 97 và 'z' là 122
        int randomAscii = random.nextInt(26) + 97;
        char randomChar = (char) randomAscii;
        return String.valueOf(randomChar);
    }

    //nut bam submit chuyen man
    @FXML
    public void clickSubmit() {
        System.out.println("submit");
    }

    @FXML
    public void cancelGame() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("hangman2.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Hangman Game");
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
            // Dong cua so hien tai
            hBoxAlphabet.getScene().getWindow().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("hangman.fxml"));
//        primaryStage.setTitle("Hangman Game");
//        primaryStage.setScene(new Scene(root, 800, 600));
//        primaryStage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
}
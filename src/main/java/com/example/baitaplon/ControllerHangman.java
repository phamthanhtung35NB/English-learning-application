package com.example.baitaplon;

import animatefx.animation.BounceInDown;
import animatefx.animation.BounceInLeft;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.*;

public class ControllerHangman extends Application {
    @FXML
    private Canvas hangmanCanvas;
    @FXML
    private TextArea webView;
    //tu bi mat
    @FXML
    private HBox hBoxButtonsSecret;
    //cac chu cai
    @FXML
    private HBox hBoxAlphabet;

    //thong bao
    @FXML
    private Label labelNotification;

    private static String wordToGuess = "null";
    private static String meaningOfTheWordToGuess;

    //tu bi mat chua duoc hien thi
    private StringBuilder wordHasNotGuessed = new StringBuilder("");
    // list cac nut bam
    private static List<Button> ListButtonsSecret;
    private static List<Button> ListAlphabetButtons;

    //so lan doan sai
    private static int countIncorrectGuesses = 0;
    private static boolean isRandom = false;

    /**
     * khoi tao game
     */
    @FXML
    public void initialize() {
        if (HomeController.isLoadDataOfSoTuCaNhan == false) {
            DataBase.loadDataSqlOfSoTuCaNhan();
            HomeController.isLoadDataOfSoTuCaNhan = true;
        }
        if (isRandom == false) {
            isRandom = true;
            randomWord();

            }
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
            wordHasNotGuessed.append("-");
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
            hBoxAlphabet.getChildren().add(button);
        }
        //random 5 chu cai
        for (int i = 0; i < 5; i++) {
            String letter = RandomKitu();
            Button button = new Button(letter);
            button.setOnAction(event -> chooseLetters(button));
            button.getStyleClass().add("buttonChuCai");
            // Thêm nút vào ListAlphabetButtons
            ListAlphabetButtons.add(button);
            hBoxAlphabet.getChildren().add(button);

            Collections.shuffle(ListAlphabetButtons);
        }

//        System.out.println("ListButtonsSecret: " + ListButtonsSecret);
//        System.out.println("ListAlphabetButtons (shuffled): " + ListAlphabetButtons);
        // Xáo trộn ListAlphabetButtons
//        System.out.println("after shuffle");
//        System.out.println("ListButtonsSecret: " + ListButtonsSecret);
//        System.out.println("ListAlphabetButtons (shuffled): " + ListAlphabetButtons);
        updateAlphabetButtons();
        updateHangmanCanvas();
        webView.setText("\n\n\t" + meaningOfTheWordToGuess);
    }

    //update lai cac nut bam sau khi xao tron
    private void updateAlphabetButtons() {
        // Xóa sạch hBoxAlphabet
        hBoxAlphabet.getChildren().clear();
        // Thêm từng nút chữ cái từ ListAlphabetButtons vào hBoxAlphabet
        for (Button button : ListAlphabetButtons) {
//            new BounceInDown(button).play();
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
        if (checkWin() == true) {
            labelNotification.setText("Congratulations! You guessed it!\n" +
                    "\tClick submit to continue");
            for (Button button : ListAlphabetButtons) {
                button.setDisable(true);
                button.getStyleClass().remove("button_");
                button.getStyleClass().add("button_Done");
            }
        }
    }

    //check xem thang chua
    private boolean checkWin() {
        if (wordHasNotGuessed.toString().equals(wordToGuess)) {
            labelNotification.setText("Congratulations! You guessed it!\n" +
                    "\tClick submit to continue");

            for (Button button : ListAlphabetButtons) {
                button.setDisable(true);
                button.getStyleClass().remove("button_");
                button.getStyleClass().add("button_Done");
            }
            return true;
        }
        return false;
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
            labelNotification.setText("Correct guess. Keep going!");
            if (checkWin() == true) {
                labelNotification.setText("Congratulations! You guessed it!\n" +
                        "\tClick submit to continue");
                new TextToSpeech(wordToGuess);
                for (Button button : ListAlphabetButtons) {
                    button.setDisable(true);
                    button.getStyleClass().remove("button_");
                    button.getStyleClass().add("button_Done");
                }
            }
        } else {
            labelNotification.setText("Incorrect guess. Try again.");
            countIncorrectGuesses++;
            updateHangmanCanvas();
            if (countIncorrectGuesses >= 4) {
                labelNotification.setText("Game over. You lost. Click submit to continue");
                for (Button button : ListAlphabetButtons) {
                    button.setDisable(true);
                    button.getStyleClass().remove("button_");
                    button.getStyleClass().add("button_Done");
                }
                webView.setText("\n\n\t" + meaningOfTheWordToGuess + " \n\n\tThe answer is>" + wordToGuess);
                new TextToSpeech(wordToGuess);
            }
        }
    }

    //update lai cac nut bam chua cac chu cai bi mat
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
            new BounceInDown(button).play();
            hBoxButtonsSecret.getChildren().add(button);
        }
    }

    public static String RandomKitu() {
        Random random = new Random();
        // 'a'  97  'z'  122
        int randomAscii = random.nextInt(26) + 97;
        char randomChar = (char) randomAscii;
        return String.valueOf(randomChar);
    }

    //randum tu bi mat in so tay ca nhan
    public String randomWord() {
        countIncorrectGuesses = 0;
        wordHasNotGuessed = new StringBuilder("");
        ListAlphabetButtons = new ArrayList<>();
        ListButtonsSecret = new ArrayList<>();
        hBoxButtonsSecret.getChildren().clear();
        hBoxAlphabet.getChildren().clear();
        updateHangmanCanvas();
        labelNotification.setText("");

        if (ControllerSoTayCaNhan.dataSoTu.isEmpty()) {
            return "null";
        }

        int randomIndex = new Random().nextInt(ControllerSoTayCaNhan.dataSoTu.size());
        int currentIndex = 0;

        for (Map.Entry<String, WordSQL> entry : ControllerSoTayCaNhan.dataSoTu.entrySet()) {
            if (currentIndex == randomIndex) {
                wordToGuess = entry.getKey();
                meaningOfTheWordToGuess = entry.getValue().getWord_explain();
                System.out.println("wordToGuess: " + wordToGuess);
                System.out.println("meaningOfTheWordToGuess: " + meaningOfTheWordToGuess);
                return "true";
            }
            currentIndex++;
        }

        return "false";
    }

    //nut bam submit chuyen man
    @FXML
    public void clickSubmit() {
        System.out.println("submit");
        if (checkWin() == true) {
            isRandom = false;
            initialize();
            new TextToSpeech("Good job");
        } else {
            isRandom = false;
            new TextToSpeech("Try again");
            initialize();
        }
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    private void updateHangmanCanvas() {
        GraphicsContext gc = hangmanCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, hangmanCanvas.getWidth(), hangmanCanvas.getHeight());

                new BounceInLeft(hangmanCanvas).play();
        switch (countIncorrectGuesses) {
            case 0:
                drawHangman(gc);
                break;
            case 1:
                drawHangman(gc);
                drawHead(gc);
                break;
            case 2:
                drawHangman(gc);
                drawHead(gc);
                drawBody(gc);
                break;
            case 3:
                drawHangman(gc);
                drawHead(gc);
                drawBody(gc);
                drawLeftArm(gc);
                drawRightArm(gc);
                break;
            //ngoai le
            default:
                drawHangman(gc);
                drawHead(gc);
                drawBody(gc);
                drawLeftArm(gc);
                drawRightArm(gc);
                drawLeftLeg(gc);
                drawRightLeg(gc);
                break;
        }
    }

    private void drawHangman(GraphicsContext gc) {
        // khung treo
        gc.setStroke(Color.BROWN);
        gc.setLineWidth(10);
        gc.strokeLine(175, 10, 175, 20); // Dây treo
        gc.strokeLine(175, 20, 100, 20); // Cột trái
        gc.strokeLine(175, 20, 250, 20); // Cột phải

        gc.setLineWidth(5);
        gc.strokeLine(175, 90, 100, 20);
        gc.strokeLine(175, 90, 250, 20);
    }

    private void drawHead(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillOval(160, 55, 30, 30);
    }

    private void drawBody(GraphicsContext gc) {
        gc.setStroke(Color.BLACK);
        gc.fillRect(172, 85, 6, 60);
    }

    private void drawLeftArm(GraphicsContext gc) {

        gc.strokeLine(175, 90, 150, 110);
    }

    private void drawRightArm(GraphicsContext gc) {

        gc.strokeLine(175, 90, 200, 110);
    }

    private void drawLeftLeg(GraphicsContext gc) {

        gc.strokeLine(175, 145, 150, 165);
    }

    private void drawRightLeg(GraphicsContext gc) {

        gc.strokeLine(175, 145, 200, 165);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Hangman.fxml"));
        primaryStage.setTitle("Hangman Game");
        primaryStage.setScene(new Scene(root, 800, 600));

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
package com.example.baitaplon;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
//import java.awt.TextField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.HashMap;

public class ControllerGamePron extends Application {


    public static String wordDeBai = "";

    public static String game(String word) {
        return wordDeBai = word;
    }

    @FXML
    private TextField inData;
    @FXML
    private Label thongbao;
    @FXML
    private Label goiytu;

    @FXML
    public void buttonOk() {
        String tuNapvao = inData.getText();
        game("hello");
        if (checkAnsewer(wordDeBai, tuNapvao)) {
            thongbao.setText("Chính xác! Bạn đã đoán đúng từ.");

        } else {
            thongbao.setText("Rất tiếc, câu trả lời không chính xác. \n Xin mời nhập tiếp");
            goiytu.setText("Số chữ đã có đúng là " + GoiYsochudung(wordDeBai, tuNapvao));
        }
//        }
    }

    @FXML
    public void buttonLoa() {
        //gọi hàm loa
        new TextToSpeech("hello");
    }

    private boolean checkAnsewer(String textToSpeech, String usser) {
        return usser.equals(textToSpeech.toLowerCase().trim());
    }

    private int GoiYsochudung(String nhapvao, String tucantim) {
        int count = 0;
        int chieuDai = Math.min(nhapvao.length(), tucantim.length());
        HashMap<Character, Boolean> daDem = new HashMap<>();

        for (int i = 0; i < chieuDai; i++) {
            if (nhapvao.charAt(i) == tucantim.charAt(i) && !daDem.containsKey(nhapvao.charAt(i))) {
                count++;
                daDem.put(nhapvao.charAt(i), true);
            }
        }
        return count;
    }


    @Override
    public void start(Stage stage) throws IOException {

        Parent fxml = FXMLLoader.load(getClass().getResource("GamePron.fxml"));
        Scene scene = new Scene(fxml);
        stage.setTitle("Game!");
        thongbao = new Label("Nội dung thông báo");

//        scene.getStylesheets().add(getClass().getResource("Home.css").toExternalForm());
        stage.setScene(scene);
        stage.show();


    }


    public static void main(String[] args) {
        launch();
    }
}

//đc roi đó

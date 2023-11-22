package com.example.baitaplon;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
//import java.awt.TextField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ControllerGamePron extends Application {


    public static String wordDeBai = "";
    public static String wordToGuess = "";
    public static String wordNghia = "";
    public static boolean doiTu = false;

    public static int k = 0;
    private int cnt = 0;
    private int heart = 10;
    @FXML
    private Label Heart;


    public static String game(String word) {
        return wordDeBai = word;
    }

    @FXML
    private TextField inData;
    @FXML
    private Label thongbao;
    @FXML
    private Label thongbaochieudai;
    @FXML
    private Label goiytu;
    @FXML
    private Label goiYNghia;
    @FXML
    private Button replay;
    @FXML
    private ImageView tim;


    @FXML
    public void buttonOk() {
        String tuNapvao = inData.getText();
        game(wordToGuess);
        if (checkAnsewer(wordDeBai, tuNapvao)) {
            goiYNghia.setText("");
            thongbao.setText("Chính xác! Bạn đã đoán đúng từ.");
            k = 0;
            cnt = 0;
            heart = heart + 1;
        } else {
            if (heart >= 1) {
                heart = heart - 1;
            }
            cnt = cnt + 1;
            thongbao.setText("Rất tiếc, câu trả lời không chính xác. \n Xin mời nhập tiếp");
            if (cnt >= 2) {
                thongbaochieudai.setText("Chiều dài của từ cần tìm là " + wordDeBai.length());
            }
            if (cnt >= 3) {
                goiYNghia.setText(wordNghia);
            }
            if (cnt >= 4) {
                goiytu.setText("Số chữ đã có đúng là " + GoiYsochudung(tuNapvao, wordDeBai));
            }
        }
        if (heart >= 1) {
            Heart.setText(Integer.toString(heart));
        } else if(heart == 0){
            thongbao.setText("GAME OVER!");
            thongbao.setStyle("-fx-text-fill: red");
            replay.setVisible(true);
        }
    }
    @FXML
    public void Replay(){
        heart = 10;
        Heart.setText(Integer.toString(heart));
        thongbaochieudai.setText("");
        thongbao.setText("");
        thongbao.setStyle("-fx-text-fill: black");
        goiytu.setText("");
        goiYNghia.setText("");
        inData.clear();
        replay.setVisible(false);
    }

    @FXML
    public void buttonLoa() {
        //gọi hàm loa
        if (doiTu == false && k == 0) {
            randomWord();
            k++;
        }
        new TextToSpeech(wordToGuess);
    }

    public String randomWord() {
        thongbao.setText("");
        goiytu.setText("");
        inData.setText("");
        if (ControllerSoTayCaNhan.dataSoTu.isEmpty()) {
            return "null";
        }
        int randomIndex = new Random().nextInt(ControllerSoTayCaNhan.dataSoTu.size());
        int currentIndex = 0;
        for (Map.Entry<String, WordSQL> entry : ControllerSoTayCaNhan.dataSoTu.entrySet()) {
            if (currentIndex == randomIndex) {
                wordToGuess = entry.getKey().toLowerCase();
                wordNghia = entry.getValue().getWord_explain();
                System.out.printf(wordNghia);
                return "true";
            }
            currentIndex++;
        }
        return "false";
    }

    private boolean checkAnsewer(String textToSpeech, String usser) {
        return usser.equals(textToSpeech.toLowerCase().trim());
    }

    private int GoiYsochudung(String nhapvao, String tucantim) {
        int count = 0;
        int chieuDai = Math.min(nhapvao.length(), tucantim.length());
        HashMap<Character, Boolean> daDem = new HashMap<>();
        if (nhapvao.isEmpty()) {
            return count;
        } else {
            for (int i = 0; i < chieuDai; i++) {
                if (nhapvao.charAt(i) == tucantim.charAt(i) && !daDem.containsKey(nhapvao.charAt(i))) {
                    count++;
                    daDem.put(nhapvao.charAt(i), true);
                }
            }
            return count;
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("GamePron.fxml"));
        Scene scene = new Scene(fxml);
        stage.setTitle("Game!");
        thongbao = new Label("Nội dung thông báo");
        stage.setScene(scene);
        stage.show();

    }


    public static void main(String[] args) {
        launch();
    }
}


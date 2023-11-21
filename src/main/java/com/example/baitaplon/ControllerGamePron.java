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
import java.awt.event.KeyEvent;
import java.io.IOException;

public class ControllerGamePron extends Application {
    //        public void PlayGame(){
//            TextToSpeech tuphatam = new TextToSpeech("hello");
//            String tuphatamsangString = "hello";
//            System.out.println("Hãy đoán từ đã được phát âm:");
//            Scanner input = new Scanner(System.in);
//            while (true){
//                String userGuess = input.nextLine().trim().toLowerCase();
//                if(checkAnsewer(tuphatamsangString, userGuess)){
//                    System.out.println("Chính xác! Bạn đã đoán đúng từ.");
//                    break;
//                }else {
//                    System.out.println("Rất tiếc, câu trả lời không chính xác. Xin mời nhập tiếp: ");
//                    continue;
//                }
//            }
//        }
////    private String generateRandomWord() {
////        // Tạo logic để chọn từ ngẫu nhiên từ một danh sách từ điển (có thể sử dụng nguyên tắc trong class TextToSpeech)
////        // Đây chỉ là ví dụ đơn giản
////        return "hello"; // Thay đổi để tạo từ ngẫu nhiên từ danh sách từ điển
//    // khi lam xong database thi lam tiep
////    }
        private boolean checkAnsewer(String textToSpeech, String usser){
            return usser.equals(textToSpeech.toLowerCase().trim());
        }
//
//    public static void main(String[] args) {
//        GamePron game = new GamePron();
//        game.PlayGame();
//    }
//    /////////////////////////////////////
////    cấu trúc nó kiểu như này nè
    public static String wordDeBai = "";
    public static String game(String word) {
        //phats aam word
//        goi ham phat am
        //luu word de bai
        return wordDeBai = word;
    }
//    //button check
//    public static String buttonCheck() {
//        //lays du lieu da nhap
//        //so sanh voi wordDeBai
//        if (wordDaNhap.equals(wordDeBai)) {
//            return "true";
//        } else {
//            return "false";
//        }
//    }
//    public static void buttonSpeech(){
//
//    }
//    @FXML
//    private void initialize() {
//        promptLabel.setText("Hãy đoán từ đã được phát âm:");
//    }
//
//    @FXML
//    private void checkGuess() {
//        String userGuess = inputField.getText().trim().toLowerCase();
//        if (gamePron.checkAnsewer("hello", userGuess)) {
//            resultLabel.setText("Chính xác! Bạn đã đoán đúng từ.");
//        } else {
//            resultLabel.setText("Rất tiếc, câu trả lời không chính xác. Xin mời nhập tiếp:");
//        }
//    }
    //biến lấy dữ liệu từ bàn phím
    @FXML
    private TextField inData;
    //biến thông báo khi đoán đúng ỏ sai
    @FXML
    private Label thongbao;


    @FXML
    public void buttonOk() {
//        String doi = inData.getText();
        //kiểu tra đúng sai trong này
//        while (true){
            String a = inData.getText();
            game("hello");
            if (checkAnsewer(wordDeBai,a)){
                thongbao.setText("Chính xác! Bạn đã đoán đúng từ.");

            }else {
                System.out.println("111");
                thongbao.setText("Rất tiếc, câu trả lời không chính xác. Xin mời nhập tiếp");

            }
//        }
    }

    @FXML
    public void buttonLoa() {
        //gọi hàm loa
         new TextToSpeech("hello");
    }

    @Override
    public void start(Stage stage) throws IOException {

        Parent fxml = FXMLLoader.load(getClass().getResource("GamePron.fxml"));
        Scene scene = new Scene(fxml);
        stage.setTitle("Game!");
        thongbao = new Label("Nội dung thông báo"); // Gán một đối tượng Label mới vào trường thongbao

//        scene.getStylesheets().add(getClass().getResource("Home.css").toExternalForm());
        stage.setScene(scene);
        stage.show();


    }


    public static void main(String[] args) {
        launch();
    }
}

//đc roi đó

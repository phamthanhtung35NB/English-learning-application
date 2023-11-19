package com.example.baitaplon;

import java.util.Scanner;

public class GamePron {
        public void PlayGame(){
            TextToSpeech tuphatam = new TextToSpeech("hello");
            String tuphatamsangString = "hello";
            System.out.println("Hãy đoán từ đã được phát âm:");
            Scanner input = new Scanner(System.in);
            while (true){
                String userGuess = input.nextLine().trim().toLowerCase();
                if(checkAnsewer(tuphatamsangString, userGuess)){
                    System.out.println("Chính xác! Bạn đã đoán đúng từ.");
                    break;
                }else {
                    System.out.println("Rất tiếc, câu trả lời không chính xác. Xin mời nhập tiếp: ");
                    continue;
                }
            }
        }
//    private String generateRandomWord() {
//        // Tạo logic để chọn từ ngẫu nhiên từ một danh sách từ điển (có thể sử dụng nguyên tắc trong class TextToSpeech)
//        // Đây chỉ là ví dụ đơn giản
//        return "hello"; // Thay đổi để tạo từ ngẫu nhiên từ danh sách từ điển
    // khi lam xong database thi lam tiep
//    }
        private boolean checkAnsewer(String textToSpeech, String usser){
            return usser.equals(textToSpeech.toLowerCase());
        }

    public static void main(String[] args) {
        GamePron game = new GamePron();
        game.PlayGame();
    }
    }


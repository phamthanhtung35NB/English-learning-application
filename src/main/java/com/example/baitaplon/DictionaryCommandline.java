package com.example.baitaplon;

import java.util.Scanner;

public class DictionaryCommandline {
    DictionaryManagement dictionaryManagement = new DictionaryManagement();


    /**
     * Hàm main
     * Thực thi qua Terminal
     * Hàm in ra các lựa chọn và đọc yêu cầu của người dùng
     */
    public static void main(String[] args) {
        System.out.println("Dictionary_CommandLine");
        DictionaryCommandline dic = new DictionaryCommandline();
        Scanner scan = new Scanner(System.in);
        //menu
        System.out.println("Bam 1 de tra tu");
        System.out.println("Bam 2 de them tu");
        System.out.println("Bam 3 de xoa tu");
        int menuChoice = scan.nextInt();
        dic.dictionaryBasic(menuChoice);
    }

    /**
     * hiển thị kết quả danh sách dữ liệu từ vựng trên màn
     * hình theo thứ tự alphabet.
     */
    public void showAllWords() {

    }

    /**
     * Thực thi qua Terminal
     * Xử lý đầu vào gọi 1 trong ba hàm tìm từ, thêm từ, xóa từ
     */
    public void dictionaryBasic(int menuChoice) {
        if (menuChoice == 1) {
            dictionaryManagement.dictionarySQLiteSearch();
        }

        if (menuChoice == 2) {
            dictionaryManagement.addWordInSQLiteDB();
        }

        if (menuChoice == 3) {
            dictionaryManagement.dropWordInSQLiteDB();
        }
    }
}

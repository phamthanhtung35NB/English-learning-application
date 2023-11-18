package com.example.baitaplon;

import java.util.Scanner;

public class DictionaryCommandline {
    DictionaryManagement dictionaryManagement = new DictionaryManagement();
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
     * có chức năng gọi hàm insertFromCommandline() và
     * showAllWords().
     */
    public void dictionaryBasic(int menuChoice) {
        if (menuChoice == 1) {
//            dictionaryManagement.dictionarySearcherinDB();
            dictionaryManagement.dictionarySQLiteSearch();
        }

        if (menuChoice == 2) {
//            dictionaryManagement.addWordInDB();
            dictionaryManagement.addWordInSQLiteDB();
        }


        if (menuChoice == 3) {
            dictionaryManagement.dropWord();
        }
    }
}

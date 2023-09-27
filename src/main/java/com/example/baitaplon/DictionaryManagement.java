package com.example.baitaplon;

public class DictionaryManagement {
    /**
     * Nhập vào bàn phím số lượng từ vựng (Word).
     * Định dạng nhập dữ liệu từ điển Anh – Việt:
     * +Dòng 1: Nhập từ tiếng Anh.
     * +Dòng 2: Nhập giải thích bằng tiếng Việt.
     */
    public void insertFromCommandline() {


    }

    /**
     * nhập dữ liệu từ điển từ tệp dictionaries.txt.
     * Quy định tệp dictionaries.txt: Mỗi dòng chứa từ tiếng anh và giải thích tiếng việt
     * (ngăn cách nhau bằng dấu tab).
     */
    public void insertFromFile() {

    }


    /**
     * chức năng tra cứu từ điển.
     */
    public void dictionaryLookup(String word) {

    }

    /**
     * chuc nang them tu.
     *
     * @param word_target  _ tu tieng anh
     * @param word_explain _ nghia  tieng viet
     * @return _ true khi them thanh cong nguoc lai
     */
    public boolean dictionaryAdd(String word_target, String word_explain) {

        return true;
    }

    /**
     * chuc nang sua tu.
     */
    public void dictionaryEdit(String word_target, String word_explain) {
        Dictionary.set(word_target, word_explain);
    }

    /**
     * chuc nang xoa tu.
     *
     * @return true khi xoa thanh cong
     */
    public boolean dictionaryErase(String word) {
        if (Dictionary.delete(word) == true) {
            System.out.println("Done");
            return true;
        }
        System.out.println("Error");
        return false;
    }
}

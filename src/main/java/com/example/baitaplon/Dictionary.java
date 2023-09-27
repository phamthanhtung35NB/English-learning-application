package com.example.baitaplon;

import java.util.*;
//import java.util.HashMap;
import java.util.Map;
//import java.util.Set;

public class Dictionary {
    private static TreeMap<String, Word> wordMap = new TreeMap<>();

//    public void setWordMap(TreeMap<String, Word> wordMap) {
//        this.wordMap = wordMap;
//    }

    public static void add(String word_target, String word_explain) {
        Word words = new Word(word_target, word_explain);
        wordMap.put(words.getWord_target(), words);
    }

    public static void show() {
        for (Map.Entry<String, Word> tam : wordMap.entrySet()) {
            String key = tam.getKey();
            Word value = tam.getValue();
            System.out.println("English: " + key + ", Vietnamese: " + value.getWord_explain());
        }
    }

    public static void set(String word_target, String word_explain) {
        Word updatedWord = new Word(word_target, word_explain);
        wordMap.put(word_target, updatedWord);

    }

    public static boolean delete(String word_target) {
        if (wordMap.containsKey(word_target)) {
            wordMap.remove(word_target);
            return true;
        } else {
            return false;
        }
    }

    public static String search(String words) {
        Word searchWord = wordMap.get(words);
        if (searchWord == null) {
            return "";
        }
        return searchWord.getWord_explain();// nghia
    }
}

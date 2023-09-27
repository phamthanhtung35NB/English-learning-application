package com.example.baitaplon;

import java.util.*;
//import java.util.HashMap;
import java.util.Map;
//import java.util.Set;

public class Dictionary {
    private TreeMap<String, Word> wordMap = new TreeMap<>();

    public void add(String word_target, String word_explain) {
        Word words = new Word(word_target, word_explain);
        wordMap.put(words.getWord_target(), words);
    }

    public void show() {
        for (Map.Entry<String, Word> tam : wordMap.entrySet()) {
            String key = tam.getKey();
            Word value = tam.getValue();
            System.out.println("English: " + key + ", Vietnamese: " + value.getWord_explain());
        }
    }

    public String search(String words) {
        Word searchWord = wordMap.get(words);
        if (searchWord == null) {
            return "";
        }
        return searchWord.getWord_explain();// nghia
    }
}

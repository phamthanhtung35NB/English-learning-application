package com.example.baitaplon;

//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.*;
////import java.util.HashMap;
//import java.util.Map;
////import java.util.Set;


import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Tab;
import javafx.scene.control.Label;

import javafx.scene.web.WebView;
import javafx.stage.Stage;

import javafx.scene.Parent;


import java.io.*;
import java.util.*;

public class Dictionary extends Application {

//    private Map<String, Word> data = new HashMap<>();

//    @FXML
//    private ListView<String> listView;
//    @FXML
//    private WebView explainView;
//    public void setWordMap(TreeMap<String, Word> wordMap) {
//        this.wordMap = wordMap;
//    }


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        AnchorPane root = fxmlLoader.load(getClass().getResourceAsStream("mainView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Dictionary Demonstration");
        stage.show();

        // init components
        // read word list from E_V.txt
        // load word list to the ListView
        Controller controller = new Controller();
        controller.initComponents(scene);
        controller.readData();
    }


    public static void main(String[] args) {
        launch();
    }
//
//    public static void add(String word_target, String word_explain) {
//        Word words = new Word(word_target, word_explain);
//        wordMap.put(words.getWord_target(), words);
//    }
//
//    public static void show() {
//        for (Map.Entry<String, Word> tam : wordMap.entrySet()) {
//            String key = tam.getKey();
//            Word value = tam.getValue();
//            System.out.println("English: " + key + ", Vietnamese: " + value.getWord_explain());
//        }
//    }
//
//    public static void set(String word_target, String word_explain) {
//        Word updatedWord = new Word(word_target, word_explain);
//        wordMap.put(word_target, updatedWord);
//
//    }
//
//    public static boolean delete(String word_target) {
//        if (wordMap.containsKey(word_target)) {
//            wordMap.remove(word_target);
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public static String search(String words) {
//        Word searchWord = wordMap.get(words);
//        if (searchWord == null) {
//            return "";
//        }
//        return searchWord.getWord_explain();// nghia
//    }
}

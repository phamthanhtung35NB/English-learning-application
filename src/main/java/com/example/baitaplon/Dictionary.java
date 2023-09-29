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
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

public class Dictionary extends Application {
    private static TreeMap<String, Word> data = new TreeMap<>();
    private static final String DATA_FILE_PATH = "data/E_V.txt";
    //private static final String FXML_FILE_PATH = "./src/main/resources/com/example/dictionary/dictionary-view.fxml";
    private static final String SPLITTING_CHARACTERS = "<html>";
//    private Map<String, Word> data = new HashMap<>();

    @FXML
    private ListView<String> listView;
    @FXML
    private WebView definitionView;
//    public void setWordMap(TreeMap<String, Word> wordMap) {
//        this.wordMap = wordMap;
//    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        //FileInputStream fis = new FileInputStream(FXML_FILE_PATH);
        //AnchorPane root = fxmlLoader.load(fis);
        AnchorPane root = fxmlLoader.load(getClass().getResourceAsStream("main-view.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Dictionary Demonstration");
        stage.show();

        // init components
        initComponents(scene);

        // read word list from E_V.txt
        readData();

        // load word list to the ListView
        loadWordList();
    }

    public void initComponents(Scene scene) {
        this.definitionView = (WebView) scene.lookup("#definitionView");
        this.listView = (ListView<String>) scene.lookup("#listView");
        Dictionary context = this;
        this.listView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    Word selectedWord = data.get(newValue.trim());
                    String definition = selectedWord.getDef();
                    context.definitionView.getEngine().loadContent(definition, "text/html");
                }
        );
    }

    public void loadWordList() {
        this.listView.getItems().addAll(data.keySet());
    }

    public void readData() throws IOException {
        FileReader fis = new FileReader(DATA_FILE_PATH);
        BufferedReader br = new BufferedReader(fis);
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(SPLITTING_CHARACTERS);
            String word = parts[0];
            String definition = SPLITTING_CHARACTERS + parts[1];
            Word wordObj = new Word(word, definition);
            data.put(word, wordObj);
        }
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

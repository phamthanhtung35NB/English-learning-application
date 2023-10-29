package com.example.baitaplon;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.TreeMap;

public class Controller {
    @FXML
    private Label welcomeText;

    @FXML
    private ListView<String> listView;

    @FXML
    private WebView explainView;


    private static TreeMap<String, Word> data = new TreeMap<>();
    private static final String DATA_FILE_PATH = "data/E_V.txt";
    private static final String SPLITTING_CHARACTERS = "<html>";


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
        loadWordList();
    }

    public void loadWordList() {
        this.listView.getItems().addAll(data.keySet());

    }

    public void initComponents(Scene scene) {
        this.explainView = (WebView) scene.lookup("#eexplainView");
        this.listView = (ListView<String>) scene.lookup("#listView");
//        Controller controller = new Controller();
//        Dictionary context = this;
        Controller context = this;
        this.listView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    Word selectedWord = data.get(newValue.trim());
                    String definition = selectedWord.getWord_explain();
                    context.explainView.getEngine().loadContent(definition, "text/html");
                }
        );
    }

    @FXML
    protected void clickCheck() {
        welcomeText.setText("Welcome to JavaFX");
    }

    public static void add(String word_target, String word_explain) {
        Word words = new Word(word_target, word_explain);
        data.put(words.getWord_target(), words);
    }

    public static boolean delete(String word_target) {
        if (data.containsKey(word_target)) {
            data.remove(word_target);
            return true;
        } else {
            return false;
        }
    }

    public static void set(String word_target, String word_explain) {
        Word updatedWord = new Word(word_target, word_explain);
        data.put(word_target, updatedWord);

    }
}
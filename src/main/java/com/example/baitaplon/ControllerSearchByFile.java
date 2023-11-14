package com.example.baitaplon;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

public class ControllerSearchByFile {
    @FXML
    private ListView<String> listView;

    @FXML
    private WebView explainView;

    @FXML
    private TextField search;

    //    private static Map<String, Word> data = new HashMap<>();
    private static TreeMap<String, Word> data = new TreeMap<>();

    private static final String DATA_FILE_PATH = "data/E_V.txt";
    private static final String SPLITTING_CHARACTERS = "<html>";


    //text la tu can doc
    private String text;
    //khoi tao cac thanh phan
    public void initComponents() {
//        ControllerSearchByFile context = this;
        this.listView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    text = newValue.trim();
                    Word selectedWord = data.get(text);
                    String definition = selectedWord.getWord_explain();
                    this.explainView.getEngine().loadContent(definition, "text/html");
                }
        );
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

    @FXML
    protected void clickCheck() {
        try {
            text = search.getText().toLowerCase();
            Word selectedWord = data.get(text);
            String definition = selectedWord.getWord_explain();
            System.out.println(text);
            this.explainView.getEngine().loadContent(definition, "text/html");
        } catch (Exception e) {
            System.out.println("WARNING");
            this.explainView.getEngine().loadContent("\n\n Not in the dictionary", "text/html");
        }
    }
    @FXML
    protected void clickSpeech() {
        new TextToSpeech(text);
    }

    @FXML
    protected void load() throws IOException {
        System.out.println("tesss");
        readData();
        loadWordList();
        initComponents();
    }
    //neu mo cmt dong nay thi se bi loi
//    ControllerSearchByFile() throws IOException {
//        System.out.println("tesss");
//        readData();
//        loadWordList();
//        initComponents();
//    }
    public void autoLoad() throws IOException {
        readData();
        loadWordList();
        initComponents();
    }
    public void loadWordList() {
        this.listView.getItems().addAll(data.keySet());
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
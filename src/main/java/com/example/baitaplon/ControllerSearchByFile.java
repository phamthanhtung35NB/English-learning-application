package com.example.baitaplon;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.web.WebView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerSearchByFile {
    @FXML
    private ListView<String> listView;

    private static Map<String, Word> data = new HashMap<>();
    @FXML
    private WebView explainView;

//    private static TreeMap<String, Word> data = new TreeMap<>();

    private static final String DATA_FILE_PATH = "data/E_V.txt";
    private static final String SPLITTING_CHARACTERS = "<html>";


    public void initComponents() {
//        this.explainView = (WebView) scene.lookup("#definitionView");
//        this.listView = (ListView<String>) scene.lookup("#listView");
        ControllerSearchByFile context = this;
        this.listView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    Word selectedWord = data.get(newValue.trim());
                    String definition = selectedWord.getWord_explain();
                    context.explainView.getEngine().loadContent(definition, "text/html");
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
    protected void clickCheck() throws IOException{
//            welcomeText.setText("Welcome to JavaFX");
        System.out.println("tesss");
        initComponents();
        readData();
        loadWordList();
    }
    @FXML
    protected void load() throws IOException{
//            welcomeText.setText("Welcome to JavaFX");
        System.out.println("tesss");
        initComponents();
        readData();
        loadWordList();
    }
        public void loadWordList() {
        this.listView.getItems().addAll(data.keySet());
    }





    //    public void start(Stage s) throws Exception {
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        AnchorPane root = fxmlLoader.load(getClass().getResourceAsStream("mainView.fxml"));
//        Scene scene = new Scene(root);
//        s.setScene(scene);
//        s.setTitle("Dictionary Demonstration");
//        s.show();
//
//
//        initComponents(scene);
//        readData();
//    }

//    public void initComponents(){
////        this.explainView = (WebView) scene.lookup("#explainView");
////        this.listView = (ListView<String>) scene.lookup("#listView");
////        ControllerSearchByFile context = this;
////        this.
//                listView.getSelectionModel().selectedItemProperty().addListener(
//                (observable, oldValue, newValue) -> {
//                    Word selectedWord = data.get(newValue.trim());
//                    String definition = selectedWord.getWord_explain();
//
//                    WebEngine webEngine = explainView.getEngine();
//                    webEngine.loadContent(definition);
////                    explainView.getEngine().loadContent(definition, "text/html");
//                }
//        );
////        readData();
//    }
//    public void initComponents(WebView explainView, ListView<String> listView)  {
//        this.explainView = explainView;
//        this.listView = listView;
//        ControllerSearchByFile context = this;
//        this.listView.getSelectionModel().selectedItemProperty().addListener(
//                (observable, oldValue, newValue) -> {
//                    Word selectedWord = data.get(newValue.trim());
//                    String definition = selectedWord.getWord_explain();
//                    context.explainView.getEngine().loadContent(definition, "text/html");
//                }
//        );
//    }
//    public void initComponents(FXMLLoader fxmlLoader) {
//        AnchorPane root = fxmlLoader.getRoot();
//        WebView definitionView = (WebView) root.lookup("#definitionView");
//        ListView<String> listView = (ListView<String>) root.lookup("#listView");
//
//        listView.getSelectionModel().selectedItemProperty().addListener(
//                (observable, oldValue, newValue) -> {
//                    Word selectedWord = data.get(newValue.trim());
//                    if (selectedWord != null) {
//                        String definition = selectedWord.getWord_explain();
//                        definitionView.getEngine().loadContent(definition, "text/html");
//                    }
//                }
//        );
//    }

//    public void initComponents() {
//        WebView explainViewLocal = explainView;
//        ListView<String> listViewLocal = listView;
//
//        listViewLocal.getSelectionModel().selectedItemProperty().addListener(
//                (observable, oldValue, newValue) -> {
//                    Word selectedWord = data.get(newValue.trim());
//                    String definition = selectedWord.getWord_explain();
//                    explainViewLocal.getEngine().loadContent(definition, "text/html");
//                }
//        );
////
////        readData();
//    }






////////////////////////////////////////////////////


//
//    public static void add(String word_target, String word_explain) {
//        Word words = new Word(word_target, word_explain);
//        data.put(words.getWord_target(), words);
//    }
//
//    public static boolean delete(String word_target) {
//        if (data.containsKey(word_target)) {
//            data.remove(word_target);
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public static void set(String word_target, String word_explain) {
//        Word updatedWord = new Word(word_target, word_explain);
//        data.put(word_target, updatedWord);
//
//    }
}
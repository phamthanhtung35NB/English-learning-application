package com.example.baitaplon;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.TreeMap;



public class ControllerSoTayCaNhan {

    @FXML
    private ListView<String> listViewA;

    @FXML
    private WebView explainViewNghia;

    @FXML
    private TextField searchTra;

    protected static TreeMap<String, WordSQL> dataSoTu = new TreeMap<>();

    //text la tu can doc/xoa
    private String text;
    private int idCanXoa;

    public void initComponents() {
        this.listViewA.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
//                    text =
                    WordSQL selectedWord = dataSoTu.get(newValue.trim());
                    //lay nghia cua tu
                    String definition = selectedWord.getWord_explain();
                    //lay id cua tu can xoa neu muon xoa
                    idCanXoa = selectedWord.getID();
                    this.explainViewNghia.getEngine().loadContent(definition, "text/html");
                }
        );
    }

    @FXML
    protected void clickCheck() {
        try {
            text = searchTra.getText().toLowerCase();
            WordSQL selectedWord = dataSoTu.get(text);
            //lay nghia cua tu
            String definition = selectedWord.getWord_explain();
            //lay id cua tu can xoa neu muon xoa
            idCanXoa = selectedWord.getID();
            System.out.println(text);
            this.explainViewNghia.getEngine().loadContent(definition, "text/html");
        } catch (Exception e) {
            System.out.println("WARNING");
            this.explainViewNghia.getEngine().loadContent("\n\n Not in the dictionary", "text/html");
        }
    }

    @FXML
    protected void clickSpeech() {
        new TextToSpeech(text);
    }

    @FXML
    protected void load() throws IOException, SQLException {
        DataBase.loadDataSqlOfSoTuCaNhan();
        System.out.println("test");
        initComponents();
        loadWordList();
    }

    //ddang loi cho nay
    @FXML
    protected void clickDelete() throws IOException, SQLException {
        //xoa trong SQL
        if (DataBase.deleteSQLiteStuding_array(idCanXoa)) {
            DataBase.loadDataSqlOfSoTuCaNhan();
            ObservableList<String> emptyList = FXCollections.observableArrayList();
            listViewA.setItems(emptyList);
            initComponents();
            loadWordList();
            System.out.println("xoa thanh cong");
        }
    }
    public void loadWordList() {
        this.listViewA.getItems().addAll(dataSoTu.keySet());
    }

//    public static void add(String word_target, String word_explain) {
//        Word words = new Word(word_target, word_explain);
//        dataSoTu.put(words.getWord_target(), words);
//    }
//
//    public static boolean delete(String word_target) {
//        if (dataSoTu.containsKey(word_target)) {
//            dataSoTu.remove(word_target);
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public static void set(String word_target, String word_explain) {
//        Word updatedWord = new Word(word_target, word_explain);
//        dataSoTu.put(word_target, updatedWord);
//    }
//    //main
//    public static void main(String[] args) {
//
//    }
}
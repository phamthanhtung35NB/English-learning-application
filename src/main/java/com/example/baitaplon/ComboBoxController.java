package com.example.baitaplon;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ComboBoxController implements Initializable {

    @FXML
    public ComboBox<String> comboBoxInPut;
    @FXML
    public ComboBox<String> comboBoxOutPut;
    @FXML
    private Label outputLabelApiGoogle;
    @FXML
    private TextField inputTextApiGoogle;
    String selectedInPut = "";
    String selectedOutPut = "";

    ObservableList<String> listInPut = FXCollections.observableArrayList("Anh", "Viet", "Trung");

    //khi chon
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // load gia tri
        comboBoxInPut.getItems().addAll(listInPut);
        comboBoxOutPut.getItems().addAll(listInPut);
        // gia tri mac dinh
        comboBoxInPut.setValue("Select Language");
        comboBoxOutPut.setValue("Select Language");

        comboBoxInPut.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedInPut = comboBoxInPut.getValue();
        });
        comboBoxOutPut.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedOutPut = comboBoxOutPut.getValue();
        });
    }
    public String maNgonNgu(String string) {
        if (string.equals("Anh")) {
            return "en";
        } else if (string.equals("Viet")) {
            return "vi";
        }
        return "";
    }

    public void translateText() {
        String input = inputTextApiGoogle.getText();
        try {
            String translatedText = APIgoogle.translate(maNgonNgu(selectedInPut), maNgonNgu(selectedOutPut), input);
            outputLabelApiGoogle.setText("  " + translatedText);
        } catch (Exception e) {
            outputLabelApiGoogle.setText("Translation failed. Please try again.");
            e.printStackTrace();
        }
    }
}


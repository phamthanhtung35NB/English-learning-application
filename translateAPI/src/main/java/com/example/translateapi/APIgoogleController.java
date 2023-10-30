package com.example.translateapi;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class APIgoogleController {
    @FXML
    private TextField inputText;
    @FXML
    private Label outputLabel;
    public void translateText() {
        String input = inputText.getText();
        try {
            String translatedText = APIgoogle.translate("en", "vi", input);
            outputLabel.setText("  " + translatedText);
        } catch (Exception e) {
            outputLabel.setText("Translation failed. Please try again.");
            e.printStackTrace();
        }
    }
}
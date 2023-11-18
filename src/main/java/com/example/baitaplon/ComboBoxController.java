package com.example.baitaplon;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

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
    @FXML
    private ToggleButton isRecordSpeechIn;
    String selectedInPut = "auto";
    String selectedOutPut = "en";


    //khi chon ngon ngu trong combobox
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // load gia tri
        comboBoxInPut.getItems().addAll(listInPut);
        comboBoxOutPut.getItems().addAll(listOutPut);
        // gia tri mac dinh
        comboBoxInPut.setValue("Select Language");
        comboBoxOutPut.setValue("Select Language");

        comboBoxInPut.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedInPut = comboBoxInPut.getValue();

        });
        comboBoxOutPut.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedOutPut = comboBoxOutPut.getValue();
        });
        System.out.println("selectedInPut: " + selectedInPut);
        System.out.println("selectedOutPut: " + selectedOutPut);
    }

    @FXML
    public void speechIn() {
        String input = inputTextApiGoogle.getText();
        new TextToSpeech(input);
    }

    @FXML
    public void speechOut() {
        String input = outputLabelApiGoogle.getText();
        new TextToSpeech(input);
    }

    @FXML
    public void buttonRecordSpeechIn() {
        if (isRecordSpeechIn.isSelected()) {
            isRecordSpeechIn.setText("Stop");
            try {
//                System.out.println(new speechToText().speech(convertToLanguageCode(selectedInPut)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            isRecordSpeechIn.setText("Record");
        }
    }


    /**
     * dich tu
     * input: tu can dich lay tu textfield
     */
    public void translateText() {
        String input = inputTextApiGoogle.getText();
        try {
//            String translatedText = APIgoogle.translate(convertToLanguageCode(selectedInPut), convertToLanguageCode(selectedOutPut), input);
//            System.out.println(convertToLanguageCode(selectedInPut) + " "+ selectedInPut);
            if (!convertToLanguageCode(selectedInPut).equals("auto")) {
                String[] translatedText = GoogleTranslate.translate(convertToLanguageCode(selectedInPut), convertToLanguageCode(selectedOutPut), input);
                outputLabelApiGoogle.setText("  \n" + translatedText[0]);
            } else {
                String[] translatedText = GoogleTranslate.translate(convertToLanguageCode(selectedInPut), convertToLanguageCode(selectedOutPut), input);
                outputLabelApiGoogle.setText("  \n-Translate from " + convertToLanguageName(translatedText[1]) +
                        " to \n->" + convertToLanguageName(convertToLanguageCode(selectedOutPut)) + " :  \n\t" + translatedText[0]);
                System.out.println(translatedText[0]);
                System.out.println(convertToLanguageName(translatedText[1]));
            }

        } catch (Exception e) {
            outputLabelApiGoogle.setText("Translation failed. Please try again.");
            e.printStackTrace();
        }
    }

    /**
     * list cac ngon ngu cua input
     */
    ObservableList<String> listInPut = FXCollections.observableArrayList("Auto",
            "Tiếng Việt (Việt Nam)",
            "Tiếng Anh (English)",
            "Tiếng Ả Rập (العربية)",
            "Tiếng Albania (Shqip)",
            "Tiếng Ba Lan (Polski)",
            "Tiếng Bồ Đào Nha (Português)",
            "Tiếng Bulgaria (Български)",
            "Tiếng Croatia (Hrvatski)",
            "Tiếng Cộng hòa Macedonia (Македонски)",
            "Tiếng Czech (Čeština)",
            "Tiếng Đan Mạch (Dansk)",
            "Tiếng Đức (Deutsch)",
            "Tiếng Estonia (Eesti)",
            "Tiếng Filipino (Filipino)",
            "Tiếng Pháp (Français)",
            "Tiếng Phần Lan (Suomi)",
            "Tiếng Hy Lạp (Ελληνικά)",
            "Tiếng Hin-ddi (हिन्दी)",
            "Tiếng Hungary (Magyar)",
            "Tiếng Iceland (Íslenska)",
            "Tiếng Indonesia (Bahasa Indonesia)",
            "Tiếng Ý (Italiano)",
            "Tiếng Latvia (Latviešu)",
            "Tiếng Lithuania (Lietuvių)",
            "Tiếng Malay (Bahasa Melayu)",
            "Tiếng Na Uy (Norsk)",
            "Tiếng Nga (Русский)",
            "Tiếng Romania (Română)",
            "Tiếng Serbia (Српски)",
            "Tiếng Slovakia (Slovenčina)",
            "Tiếng Slovenia (Slovenščina)",
            "Tiếng Swahili",
            "Tiếng Séc (Čeština)",
            "Tiếng Thái (ไทย)",
            "Tiếng Thổ Nhĩ Kỳ (Türkçe)",
            "Tiếng Tây Ban Nha (Español)",
            "Tiếng Trung (中文)",
            "Tiếng Ý (Italiano)",
            "Tiếng Nhật (日本語)",
            "Tiếng Hàn (한국어)"
    );
    /**
     * list cac ngon ngu cua output
     */
    ObservableList<String> listOutPut = FXCollections.observableArrayList("Tiếng Việt (Việt Nam)",
            "Tiếng Anh (English)",
            "Tiếng Ả Rập (العربية)",
            "Tiếng Albania (Shqip)",
            "Tiếng Ba Lan (Polski)",
            "Tiếng Bồ Đào Nha (Português)",
            "Tiếng Bulgaria (Български)",
            "Tiếng Croatia (Hrvatski)",
            "Tiếng Cộng hòa Macedonia (Македонски)",
            "Tiếng Czech (Čeština)",
            "Tiếng Đan Mạch (Dansk)",
            "Tiếng Đức (Deutsch)",
            "Tiếng Estonia (Eesti)",
            "Tiếng Filipino (Filipino)",
            "Tiếng Pháp (Français)",
            "Tiếng Phần Lan (Suomi)",
            "Tiếng Hy Lạp (Ελληνικά)",
            "Tiếng Hin-ddi (हिन्दी)",
            "Tiếng Hungary (Magyar)",
            "Tiếng Iceland (Íslenska)",
            "Tiếng Indonesia (Bahasa Indonesia)",
            "Tiếng Ý (Italiano)",
            "Tiếng Latvia (Latviešu)",
            "Tiếng Lithuania (Lietuvių)",
            "Tiếng Malay (Bahasa Melayu)",
            "Tiếng Na Uy (Norsk)",
            "Tiếng Nga (Русский)",
            "Tiếng Romania (Română)",
            "Tiếng Serbia (Српски)",
            "Tiếng Slovakia (Slovenčina)",
            "Tiếng Slovenia (Slovenščina)",
            "Tiếng Swahili",
            "Tiếng Séc (Čeština)",
            "Tiếng Thái (ไทย)",
            "Tiếng Thổ Nhĩ Kỳ (Türkçe)",
            "Tiếng Tây Ban Nha (Español)",
            "Tiếng Trung (中文)",
            "Tiếng Ý (Italiano)",
            "Tiếng Nhật (日本語)",
            "Tiếng Hàn (한국어)"
    );

    /**
     * chuyen tu ten ngon ngu sang ma ngon ngu
     *
     * @param string
     * @return ma ngon ngu
     */
    public String convertToLanguageCode(String string) {
        if (selectedInPut.equals("Select Language")) {

            System.out.println("selectedInPut: " + selectedInPut);
        }
        switch (string) {
            case "Select Language":
                return "auto";
            case "Auto":
                return "auto";
            case "Tiếng Anh (English)":
                return "en";
            case "Tiếng Việt (Việt Nam)":
                return "vi";
            case "Tiếng Trung (中文)":
                return "zh";
            case "Tiếng Tây Ban Nha (Español)":
                return "es";
            case "Tiếng Pháp (Français)":
                return "fr";
            case "Tiếng Đức (Deutsch)":
                return "de";
            case "Tiếng Nhật (日本語)":
                return "ja";
            case "Tiếng Hàn (한국어)":
                return "ko";
            case "Tiếng Nga (Русский)":
                return "ru";
            case "Tiếng Ả Rập (العربية)":
                return "ar";
            case "Tiếng Bồ Đào Nha (Português)":
                return "pt";
            case "Tiếng Hin-ddi (हिन्दी)":
                return "hi";
            case "Tiếng Ý (Italiano)":
                return "it";
            case "Tiếng Hà Lan (Nederlands)":
                return "nl";
            case "Tiếng Thái (ไทย)":
                return "th";
            case "Tiếng Thổ Nhĩ Kỳ (Türkçe)":
                return "tr";
            case "Tiếng Ba Lan (Polski)":
                return "pl";
            case "Tiếng Thụy Điển (Svenska)":
                return "sv";
            case "Tiếng Đan Mạch (Dansk)":
                return "da";
            case "Tiếng Phần Lan (Suomi)":
                return "fi";
            case "Tiếng Na Uy (Norsk)":
                return "no";
            case "Tiếng Hy Lạp (Ελληνικά)":
                return "el";
            case "Tiếng Séc (Čeština)":
                return "cs";
            case "Tiếng Hungary (Magyar)":
                return "hu";
            case "Tiếng Romania (Română)":
                return "ro";
            case "Tiếng Slovakia (Slovenčina)":
                return "sk";
            case "Tiếng Croatia (Hrvatski)":
                return "hr";
            case "Tiếng Indonesia (Bahasa Indonesia)":
                return "id";
            case "Tiếng Malay (Bahasa Melayu)":
                return "ms";
            case "Tiếng Bulgaria (Български)":
                return "bg";
            case "Tiếng Hebrew (עברית)":
                return "he";
            case "Tiếng Serbia (Српски)":
                return "sr";
            case "Tiếng Slovenia (Slovenščina)":
                return "sl";
            case "Tiếng Estonia (Eesti)":
                return "et";
            case "Tiếng Latvia (Latviešu)":
                return "lv";
            case "Tiếng Lithuania (Lietuvių)":
                return "lt";
            case "Tiếng Albania (Shqip)":
                return "sq";
            case "Tiếng Macedonia (Македонски)":
                return "mk";
            case "Tiếng Iceland (Íslenska)":
                return "is";
            case "Tiếng Swahili":
                return "sw";
            case "Tiếng Filipino (Filipino)":
                return "fil";

        }

        return "auto";
    }

    /**
     * chuyen tu ma ngon ngu sang ten ngon ngu
     *
     * @param string
     * @return ma ngon ngu
     */
    public static String convertToLanguageName(String string) {
        switch (string) {
            case "en":
                return "Tiếng Anh (English)";
            case "vi":
                return "Tiếng Việt (Việt Nam)";
            case "zh":
                return "Tiếng Trung (中文)";
            case "es":
                return "Tiếng Tây Ban Nha (Español)";
            case "fr":
                return "Tiếng Pháp (Français)";
            case "de":
                return "Tiếng Đức (Deutsch)";
            case "ja":
                return "Tiếng Nhật (日本語)";
            case "ko":
                return "Tiếng Hàn (한국어)";
            case "ru":
                return "Tiếng Nga (Русский)";
            case "ar":
                return "Tiếng Ả Rập (العربية)";
            case "pt":
                return "Tiếng Bồ Đào Nha (Português)";
            case "hi":
                return "Tiếng Hin-ddi (हिन्दी)";
            case "it":
                return "Tiếng Ý (Italiano)";
            case "nl":
                return "Tiếng Hà Lan (Nederlands)";
            case "th":
                return "Tiếng Thái (ไทย)";
            case "tr":
                return "Tiếng Thổ Nhĩ Kỳ (Türkçe)";
            case "pl":
                return "Tiếng Ba Lan (Polski)";
            case "sv":
                return "Tiếng Thụy Điển (Svenska)";
            case "da":
                return "Tiếng Đan Mạch (Dansk)";
            case "fi":
                return "Tiếng Phần Lan (Suomi)";
            case "no":
                return "Tiếng Na Uy (Norsk)";
            case "el":
                return "Tiếng Hy Lạp (Ελληνικά)";
            case "cs":
                return "Tiếng Séc (Čeština)";
            case "hu":
                return "Tiếng Hungary (Magyar)";
            case "ro":
                return "Tiếng Romania (Română)";
            case "sk":
                return "Tiếng Slovakia (Slovenčina)";
            case "hr":
                return "Tiếng Croatia (Hrvatski)";
            case "id":
                return "Tiếng Indonesia (Bahasa Indonesia)";
            case "ms":
                return "Tiếng Malay (Bahasa Melayu)";
            case "bg":
                return "Tiếng Bulgaria (Български)";
            case "he":
                return "Tiếng Hebrew (עברית)";
            case "sr":
                return "Tiếng Serbia (Српски)";
            case "sl":
                return "Tiếng Slovenia (Slovenščina)";
            case "et":
                return "Tiếng Estonia (Eesti)";
            case "lv":
                return "Tiếng Latvia (Latviešu)";
            case "lt":
                return "Tiếng Lithuania (Lietuvių)";
            case "sq":
                return "Tiếng Albania (Shqip)";
            case "mk":
                return "Tiếng Macedonia (Македонски)";
            case "is":
                return "Tiếng Iceland (Íslenska)";
            case "sw":
                return "Tiếng Swahili";
            case "fil":
                return "Tiếng Filipino (Filipino)";
            default:
                return "Mysterious language";
        }
    }
}


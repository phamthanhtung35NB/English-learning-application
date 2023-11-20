package com.example.baitaplon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

public class HomeController  {


//    @FXML
//    private Field tesst;

    // centerBorderPane laf view chinh cua BorderPane
    @FXML
    protected BorderPane centerBorderPane;
    @FXML
    private TextField textName;
    @FXML
    private PasswordField textPass;

    @FXML
    private VBox vbox;
    @FXML
    private VBox vboxRight;
    @FXML
    private ToggleButton isLockUnlockSetLeft;
    @FXML
    private ToggleButton isSettingLockUnlockSetLeft;
    @FXML
    private Label title;
    private boolean isLock = false;
    private boolean isSettingLock = false;
    private boolean isSetting= false;
    @FXML
    public void buttonSetting() {
        if (isSetting==false) {
            centerBorderPane.setRight(vboxRight);
            isSetting = true;
            System.out.println("Setting");
        } else {
            centerBorderPane.setRight(null);
            isSetting = false;
            System.out.println("Setting");
        }
////            centerBorderPane.setLeft(vbox);
//            System.out.println("Cửa sổ Left đã mở");
//
//            isSettingLock = true;
//            isLock = true;
//
//        } else {
////            centerBorderPane.setLeft(null);
//            System.out.println("Cửa sổ Left đã đóng");
//            isSettingLock = false;
//            isLock = false;
//        }
    }
    @FXML
    public void initialize() throws IOException {
        buttonHome();
        System.out.println("Home");
        centerBorderPane.setRight(null);
        centerBorderPane.setOnMouseMoved(event -> {
            int x = (int)event.getX();
            int y = (int) event.getY();
            if ((x>=0&&x < 100)&&(y>=0&&y<750)) {
                if (centerBorderPane.getLeft() == null) {
                    centerBorderPane.setLeft(vbox);
                    System.out.println("show");
                }
            }else if ((x > 240&&x<1320)&&(y>=0&&y<750)&&(isLock==false||isSettingLock==false)){
                if (centerBorderPane.getLeft() != null) {
                    centerBorderPane.setLeft(null);
                    System.out.println("hide");
                }
            }
//            System.out.println("Mouse Entered - X: " + x+ " Y: " + y );

        });
    }

    @FXML
    protected void buttonHome() throws IOException {
//        System.out.println("Home");
        AnchorPane view = FXMLLoader.load(getClass().getResource("TabHome.fxml"));
        view.getStylesheets().add(getClass().getResource("TabHome.css").toExternalForm());
//        System.out.println("2");
        centerBorderPane.setCenter(view);
//        System.out.println("3");
        title.setText("Home");
    }
    @FXML
    protected void buttonSearch() throws IOException{
//        System.out.println("Search");
//        System.out.println("1");
        AnchorPane view = FXMLLoader.load(getClass().getResource("FileSql.fxml"));
//        System.out.println("2");
        view.getStylesheets().add(getClass().getResource("FileSql.css").toExternalForm());
        centerBorderPane.setCenter(view);
//        System.out.println("3");
//        new ControllerSearchByFile().autoLoad();
        title.setText("Search");
    }
    @FXML
    protected void buttonOnlineSearch() throws IOException{
//        System.out.println("O Search");
        Pane view = FXMLLoader.load(getClass().getResource("GoogleApi.fxml"));
        centerBorderPane.setCenter(view);
        title.setText("Online Search");
    }
    @FXML
    protected void buttonGame() throws IOException {
        System.out.println("Game");
        GridPane view = FXMLLoader.load(getClass().getResource("Hangman.fxml"));
        centerBorderPane.setCenter(view);
        title.setText("Game");
    }
    @FXML
    protected void buttonButton() throws IOException {
        System.out.println("buttonButton");
        System.out.println("1");
        AnchorPane view = FXMLLoader.load(getClass().getResource("SoTayCaNhan.fxml"));
        System.out.println("2");
        centerBorderPane.setCenter(view);
        System.out.println("3");
        title.setText("Notebook");

    }
    @FXML
    protected void buttonFeedback(){
        System.out.println("Feed Back");
        String url = "https://www.google.com";
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void buttonLoOut(ActionEvent event) {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage HomeStage = new Stage();
                HomeStage.setTitle("Login");
                HomeStage.setScene(scene);

                /**
                 * Lấy Node từ sự kiện
                 * Đối tượng event là sự kiện mà chúng ta đã gán cho nút đăng nhập
                 * Đối tượng source là Node gốc mà sự kiện được phát ra
                 * Trong trường hợp này, Node gốc là nút đăng nhập
                 * Sau khi có Node gốc, chúng ta có thể lấy Stage từ Node gốc
                 * Cuối cùng, chúng ta có thể đóng Stage
                 */
                Node source = (Node) event.getSource();
                Stage currentStage = (Stage) source.getScene().getWindow();
                currentStage.close();

                HomeStage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }

    }
    @FXML
    protected void exit() throws IOException {
        System.exit(0);
    }
    @FXML
    protected void buttonSettingLockUnlockSetLeft() throws IOException {
        if (isSettingLockUnlockSetLeft.isSelected()) {
//            centerBorderPane.setLeft(vbox);
            System.out.println("Cửa sổ Left đã mở");

            isSettingLock = true;
            isLock = true;

        } else {
//            centerBorderPane.setLeft(null);
            System.out.println("Cửa sổ Left đã đóng");
            isSettingLock = false;
            isLock = false;
        }
    }

//    @FXML
//    protected void buttonButton() throws IOException {
//        System.out.println("buttonButton");
//
//    }

}
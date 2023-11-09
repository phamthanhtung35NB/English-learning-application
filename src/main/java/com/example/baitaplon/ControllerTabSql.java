package com.example.baitaplon;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;

public class ControllerTabSql {

    /**
     * tab add.
     */
    @FXML
    private TextField textWordTargetAdd;
    @FXML
    private TextField textWordExplainAdd;
    @FXML
    protected void clickAddAdd(){

    }
    @FXML
    protected void clickDeleteAdd(){

    }

    /**
     * tab set.
     */
    @FXML
    private TextField textIdSet;
    @FXML
    private TextField textWordTargetSet;
    @FXML
    private TextField textWordExplainSet;

    @FXML
    protected void clickSetSet(){

    }
    @FXML
    protected void clickDeleteSet(){

    }

    /**
     * tab delete.
     */
    @FXML
    private TextField textIdDelete;
    @FXML
    private TextField textWordTargetDelete;
    @FXML
    private TextField textWordExplainDelete;

    @FXML
    protected void clickDeleteDelete(){

    }
    @FXML
    protected void clickCancelDelete(){

    }



    @FXML
    private WebView webView;

    public void initialize() {
        // Đặt nội dung HTML của WebView
        String htmlContent = "<I><Q>@zootomic /,zouə'tɔmik/<br />*  tính từ<br />- (thuộc) giải phẫu động vật</Q></I>";
        webView.getEngine().loadContent(htmlContent);
    }
    @FXML
    protected void clickCheck(){

    }
}

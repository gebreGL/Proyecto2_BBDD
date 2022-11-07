package com.example.buscarip.controller;

import com.example.buscarip.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private Stage stage ;
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void irLogin(ActionEvent actionEvent) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("mainview.fxml"));
        Scene scene2 = new Scene(fxmlLoader.load(), 645, 490);
        stage.setScene(scene2);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

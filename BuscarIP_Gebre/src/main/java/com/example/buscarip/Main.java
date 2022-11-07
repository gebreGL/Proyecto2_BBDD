package com.example.buscarip;

import com.example.buscarip.controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;

import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("loginview.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 700);
        ((LoginController) fxmlLoader.getController()).setStage(primaryStage);
        primaryStage.setTitle("Log In");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) throws Exception{launch();}

}
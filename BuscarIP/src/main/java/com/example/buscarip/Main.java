package com.example.buscarip;

import com.example.buscarip.controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;

import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Pablo & Gebre
 */
public class Main extends Application {

    /**
     *
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     * @throws IOException
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("loginview.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        ((LoginController) fxmlLoader.getController()).setStage(primaryStage);
        primaryStage.setTitle("Log In");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Clase main que ejecuta el código, en este caso el launcher que hemos definido.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        launch();
    }

}
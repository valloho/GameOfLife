package com.theanimalfarm.gameoflife;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class GameOfLifeApp extends Application{



    public static void main(String[] args) {
    launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(this.getClass().getResource("/startscreen.fxml"));
        Scene scene = new Scene(root);
        primaryStage.initStyle(StageStyle.UNDECORATED); //hides default title bar
        primaryStage.setTitle("Game of Life Animal Farm Edition");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

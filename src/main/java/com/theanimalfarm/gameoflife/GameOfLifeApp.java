package com.theanimalfarm.gameoflife;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.awt.*;

public class GameOfLifeApp extends Application{


    public static void main(String[] args) {
    launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(this.getClass().getResource("/startscreen.fxml"));
        /*
        Rectangle rec = new Rectangle(0,0,200,50);
        Button myButton = new Button();
        layout.getChildren().add(rec);
        layout.getChildren().add(myButton);
         */

        primaryStage.setTitle("Game of Life");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

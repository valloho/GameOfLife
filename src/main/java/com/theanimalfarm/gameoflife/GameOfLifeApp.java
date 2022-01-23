package com.theanimalfarm.gameoflife;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class GameOfLifeApp extends Application{

    //Variables required for window drag
    private double xOffset = 0;
    private double yOffset = 0;

    public static void main(String[] args) {
    launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(this.getClass().getResource("/startscreen.fxml"));
        Scene scene = new Scene(root);
        primaryStage.initStyle(StageStyle.UNDECORATED); //hides default title bar
        primaryStage.setTitle("Game of Life");
        primaryStage.setScene(scene);
        primaryStage.show();

        /**
         * Amos Chepchieng | https://medium.com/@keeptoo/making-a-borderless-javafx-window-movable-f7855eb33a51 | 23.01.2022
         */
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //primaryStage.setX(event.getScreenX() - xOffset);
                //primaryStage.setY(event.getScreenY() - yOffset);
            }
        });

    }
}

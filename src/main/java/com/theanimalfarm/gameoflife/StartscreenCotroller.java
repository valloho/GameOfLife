package com.theanimalfarm.gameoflife;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class StartscreenCotroller implements Initializable {

    @FXML
    public GameManager gameManager = new GameManager();
    @FXML
    Group group = new Group();

    @FXML
    private Button playButton;

    @FXML
    public void playGame(ActionEvent event) {
        gameManager.initializeGame(group);
        Scene scene = new Scene(group);
        Stage gamewindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
        gamewindow.setScene(scene);
        gamewindow.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}

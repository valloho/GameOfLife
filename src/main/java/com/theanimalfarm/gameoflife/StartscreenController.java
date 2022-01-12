package com.theanimalfarm.gameoflife;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class StartscreenController implements Initializable
{
    @FXML
    private Group group;
    @FXML
    private Button playButton;
    @FXML
    private Button nextButton;
    @FXML
    private Button backButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button loadButton;
    @FXML
    private ImageView pause;
    @FXML
    private ImageView play;
    @FXML
    private ImageView exit;
    @FXML
    private ImageView min;
    @FXML
    private VBox slider;
    @FXML
    private Label menu;
    @FXML
    private Label menuClose;
    @FXML
    private MenuItem grid50_30;
    @FXML
    private MenuItem grid25_15;
    @FXML
    private MenuItem speed1;
    @FXML
    private MenuItem speed0_5;
    @FXML
    private MenuItem speed0_1;
    @FXML
    private MenuItem colorBlack;
    @FXML
    private MenuItem colorBlue;
    @FXML
    private MenuItem colorGreen;
    @FXML
    private MenuItem colorRed;

    /**
     * Manager Variables
     */
    SaveLoad saveLoadManager = new SaveLoad();
    CellManager cellManager = new CellManager();
    private boolean playing;

    private void createLevel(int gridSizeY, int gridSizeX)
    {
        int[] arr = new int[gridSizeY*gridSizeX];
//        for(int i = 0 ;i < arr.length; i++){
//            arr[i] = 0;
//        }
        SaveLoad.createBoard("arie",arr);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        cellManager.InitializeGame(group);

        //Exit Icon
        exit.setOnMouseClicked(event -> {
            System.exit(0);
        });

        //Minimize Icon
        min.setOnMouseClicked(event -> {
            Stage stage = (Stage) min.getScene().getWindow();
            stage.setIconified(true);
        });

        //Menu - Slider
        slider.setTranslateX(-250);
        menu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.5));
            slide.setNode(slider);

            slide.setToX(0);
            slide.play();

            slider.setTranslateX(-250);

            slide.setOnFinished(event1 -> {
                menu.setVisible(false);
                menuClose.setVisible(true);
            });
        });

        menuClose.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.5));
            slide.setNode(slider);

            slide.setToX(-250);
            slide.play();

            slider.setTranslateX(0);

            slide.setOnFinished(event1 -> {
                menu.setVisible(true);
            });
        });

        //Gridsize Menu
        grid50_30.setOnAction(event -> {
            cellManager.ResetGrid(group, 50,30, 10);
        });
        grid25_15.setOnAction(event -> {
            cellManager.ResetGrid(group, 25,15, 20);
        });

        //Speed Menu
        speed1.setOnAction(event -> {
            cellManager.SetSpeed(1000);
        });
        speed0_5.setOnAction(event -> {
            cellManager.SetSpeed(500);
        });
        speed0_1.setOnAction(event -> {
            cellManager.SetSpeed(100);
        });

        //Color Menu
        colorBlack.setOnAction(event -> {
            cellManager.SetColor(Color.BLACK);
        });
        colorBlue.setOnAction(event -> {
            cellManager.SetColor(Color.BLUE);
        });
        colorGreen.setOnAction(event -> {
            cellManager.SetColor(Color.GREEN);
        });
        colorRed.setOnAction(event -> {
            cellManager.SetColor(Color.RED);
        });

        //Save
        saveButton.setOnMouseClicked(event -> {
            //saveLoadManager.saveGrid("arie",Cell.CellToArray(cellManager.GetCellGrid()));
        });

        //Load
        loadButton.setOnMouseClicked(event -> {
            //loadGrid();
        });

        //Play|Pause Button
        playButton.setGraphic(play);
        playButton.setOnMouseClicked(event ->
        {
            if (playing)
            {
                cellManager.OnPause();
                playButton.setGraphic(pause);
                this.playing = false;
            }
            else
            {
                cellManager.OnPlay();
                playButton.setGraphic(play);
                this.playing = true;
            }
        });

        //Next Generation
        nextButton.setOnMouseClicked(event -> {
            if (playing)
            {
                return;
            }
            cellManager.OnNextFrame();
        });

        //Previous Generation
        backButton.setOnMouseClicked(event -> {
            if (playing)
            {
                return;
            }
            cellManager.OnPreviousFrame();
        });
    }
}

package com.theanimalfarm.gameoflife;

import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;

public class StartscreenController implements Initializable {

    @FXML
    private Group group;
    @FXML
    private Pane titleBar;
    @FXML
    private Button playButton;
    @FXML
    private Button nextButton;
    @FXML
    private Button backButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button clearButton;
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
    private MenuItem speed0_05;
    @FXML
    private MenuItem colorBlack;
    @FXML
    private MenuItem colorBlue;
    @FXML
    private MenuItem colorGreen;
    @FXML
    private MenuItem colorRed;
    @FXML
    private MenuItem load50x30;
    @FXML
    private MenuItem load25x15;
    @FXML
    private MenuItem loadRockets;
    @FXML
    private MenuItem loadFlower;
    @FXML
    private MenuItem loadSpider;
    @FXML
    private MenuItem loadFun;

    /**
     * Manager Variables
     */
    SaveLoadManager saveLoadManager = new SaveLoadManager();
    CellManager cellManager = new CellManager();
    private boolean playing;
    //Variables required for window drag
    private double xOffset, yOffset;
    private String file;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Initialize CellManager
        cellManager.InitializeGame(group);

        //Initialize SaveLoadManager
        int[] arr = new int[cellManager.GetGridSizeX()* cellManager.GetGridSizeY()];
        saveLoadManager.createBoard("saveGame 50x30",arr);
        saveLoadManager.createBoard("saveGame 25x15",arr);

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
        /**
         *KEITHAYA | https://www.youtube.com/watch?v=LMl_OZHJYC8 | 07.01.2022
         */
        slider.setTranslateX(-252);
        menu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.5));
            slide.setNode(slider);

            slide.setToX(0);
            slide.play();

            slider.setTranslateX(-252);

            slide.setOnFinished(event1 -> {
                menu.setVisible(false);
                menuClose.setVisible(true);
            });
        });

        menuClose.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.5));
            slide.setNode(slider);

            slide.setToX(-252);
            slide.play();

            slider.setTranslateX(0);

            slide.setOnFinished(event1 -> {
                menu.setVisible(true);
            });
        });

        //Gridsize Menu Items
        grid50_30.setOnAction(event -> {
            cellManager.ResetGrid(group, 50,30, 10);
        });
        grid25_15.setOnAction(event -> {
            cellManager.ResetGrid(group, 25,15, 20);
        });

        //Speed Menu Items
        speed1.setOnAction(event -> {
            cellManager.SetSpeed(1000);
        });
        speed0_5.setOnAction(event -> {
            cellManager.SetSpeed(500);
        });
        speed0_1.setOnAction(event -> {
            cellManager.SetSpeed(100);
        });
        speed0_05.setOnAction(event -> {
            cellManager.SetSpeed(50);
        });

        //Color Menu Items
        colorBlack.setOnAction(event -> {
            cellManager.SetColor(Color.BLACK);
        });
        colorBlue.setOnAction(event -> {
            cellManager.SetColor(Color.web("#177f97"));
        });
        colorGreen.setOnAction(event -> {
            cellManager.SetColor(Color.web("#348c3b"));
        });
        colorRed.setOnAction(event -> {
            cellManager.SetColor(Color.web("#631c13"));
        });

        //Save Game
        saveButton.setOnMouseClicked(event -> {
            saveLoadManager.SaveGame(this.cellManager.GetCellGrid());
        });

        //Load Game
        load50x30.setOnAction(event -> {
            if(cellManager.GetGridSizeX() == 50){
                file = "saveGame 50x30";
                cellManager.SetCellState(saveLoadManager.LoadGame(file,cellManager.GetGridSizeX(), cellManager.GetGridSizeY()));
            }else{
                System.out.println("Not able to print a 50x30 grid on a 25x15 grid!");
            }
        });
        load25x15.setOnAction(event -> {
            file ="saveGame 25x15";
            if(cellManager.GetGridSizeX() == 25){
                cellManager.SetCellState(saveLoadManager.LoadGame(file,cellManager.GetGridSizeX(), cellManager.GetGridSizeY()));
            }else{
                System.out.println("Not able to print a 25x15 grid on a 50x30 grid!");
            }
        });
        loadRockets.setOnAction(event -> {
            file = "rockets_preset";
            if(cellManager.GetGridSizeX() == 50){
                cellManager.SetCellState(saveLoadManager.LoadGame(file,cellManager.GetGridSizeX(), cellManager.GetGridSizeY()));
            }else{
                System.out.println("Not able to print a 50x30 grid on a 25x15 grid!");
            }
        });
        loadSpider.setOnAction(event -> {
            file = "spider_preset";
            if(cellManager.GetGridSizeX() == 50){
                cellManager.SetCellState(saveLoadManager.LoadGame(file,cellManager.GetGridSizeX(), cellManager.GetGridSizeY()));
            }else{
                System.out.println("Not able to print a 50x30 grid on a 25x15 grid!");
            }
        });
        loadFlower.setOnAction(event -> {
            file = "flower_preset";
            if(cellManager.GetGridSizeX() == 25){
                cellManager.SetCellState(saveLoadManager.LoadGame(file,cellManager.GetGridSizeX(), cellManager.GetGridSizeY()));
            }else{
                System.out.println("Not able to print a 25x15 grid on a 50x30 grid!");
            }
        });
        loadFun.setOnAction(event -> {
            file = "fun_preset";
            if(cellManager.GetGridSizeX() == 25){
                cellManager.SetCellState(saveLoadManager.LoadGame(file,cellManager.GetGridSizeX(), cellManager.GetGridSizeY()));
            }else{
                System.out.println("Not able to print a 25x15 grid on a 50x30 grid!");
            }
        });

        //Play|Pause Button
        playButton.setGraphic(play);
        playButton.setOnMouseClicked(event ->
        {
            if (playing)
            {
                cellManager.OnPause();
                playButton.setGraphic(play);
                this.playing = false;
            }
            else
            {
                cellManager.OnPlay();
                playButton.setGraphic(pause);
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

        //Clear Cell Grid
        clearButton.setOnMouseClicked(event -> {
            cellManager.ClearCells();
        });

        /**
         * Amos Chepchieng | https://medium.com/@keeptoo/making-a-borderless-javafx-window-movable-f7855eb33a51 | 23.01.2022
         */
        titleBar.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        titleBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage stage = (Stage) titleBar.getScene().getWindow();
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
    }
}

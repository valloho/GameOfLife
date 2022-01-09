package com.theanimalfarm.gameoflife;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class StartscreenCotroller implements Initializable {

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

    // Grid variables
    private int gridSizeX = 50;
    private int gridSizeY = 30;
    private Cell[][] cellGrid;

    // Cell variables
    private float cellSize = 10;

    // Game management variables
    private boolean playing;
    Timer timer;

    //Initialize the game by creating a grid containing all the cells
    @FXML
    public void InitializeGame(Group group)
    {
        // Create a cell grid by looping through the 2 dimensional array and creating new cells
        cellGrid = new Cell[this.gridSizeX][this.gridSizeY];

        for (int x = 0; x < gridSizeX; x++) // Loop through x-axis
        {
            for (int y = 0; y < gridSizeY; y++)  // Loop through y-axis
            {
                // Create and initialize new cell
                cellGrid[x][y] = new Cell(cellSize, x * cellSize, y * cellSize, x, y);
                cellGrid[x][y].setFill(Color.rgb(255, 255, 255));
                cellGrid[x][y].setStroke(Color.rgb(0, 0, 0));

                // Set an event to the cell so the state can be changed by clicking on it
                cellGrid[x][y].setOnMouseClicked(new EventHandler<MouseEvent>()
                {
                    @Override
                    public void handle(MouseEvent event)
                    {
                        ((Cell) event.getSource()).ChangeState();
                    }
                });

                // Add cell to the main group
                group.getChildren().add(cellGrid[x][y]);
            }
        }
        createLevel();
    }
    public void saveGrid(){
        SaveLoad.saveGrid("arie",Cell.CellToArray(cellGrid));
    }

    private void createLevel(){
        int[] arr = new int[1500];
//        for(int i = 0 ;i < arr.length; i++){
//            arr[i] = 0;
//        }
        SaveLoad.createBoard("arie",arr);
    }

    public void OnPlay()
    {
        if (playing)
        {
            //Stop playing
            this.playing = false;
            playButton.setGraphic(play);
            System.out.println("Not Playing");
            timer.cancel();
            return;
        }

        //Start playing
        this.playing = true;
        playButton.setGraphic(pause);
        System.out.println("Playing");
        timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                System.out.println("Hello World");
                OnNextFrame();
            }
        },1000, 1000);
    }

    // Jump to the next frame by calculating and setting the cells next state
    public void OnNextFrame()
    {
        //Calculate New Cell States
        for (Cell[] cellX : cellGrid)
        {
            for (Cell cell : cellX)
            {
                cell.CalculateNewState(cellGrid, gridSizeX, gridSizeY);
            }
        }

        //Set New Cell States
        for (Cell[] cellX : cellGrid)
        {
            for (Cell cell : cellX)
            {
                cell.SetNewState();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        InitializeGame(group);

        //exit button
        exit.setOnMouseClicked(event -> {
            System.exit(0);
        });
        //minimize button
        min.setOnMouseClicked(event -> {
            Stage stage = (Stage) min.getScene().getWindow();
            stage.setIconified(true);
        });

        playButton.setGraphic(play);

        playButton.setOnMouseClicked(event -> {
            OnPlay();
        });

        nextButton.setOnMouseClicked(event -> {
            if (playing)
            {
                return;
            }
            System.out.println("Next Step");
            OnNextFrame();
        });

        //menu slide
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

        //saveButton
        saveButton.setOnMouseClicked(event -> {
            saveGrid();
        });
    }


}

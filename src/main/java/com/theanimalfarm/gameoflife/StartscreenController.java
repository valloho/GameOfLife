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
import java.util.Timer;
import java.util.TimerTask;

public class StartscreenController implements Initializable {

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


    // Grid variables
    private int temp_gridSizeX = 50;
    private int temp_gridSizeY = 30;
    private Cell[][] cellGrid;

    // Cell variables
    private float temp_cellSize = 10;

    // Game management variables
    private boolean playing;
    private Timer timer;
    private int gameSpeed = 1000;

    //Initialize the game by creating a grid containing all the cells
    @FXML
    public void InitializeGame(Group group, int gridSizeX, int gridSizeY, float cellSize)
    {
        // Create a cell grid by looping through the 2 dimensional array and creating new cells
        cellGrid = new Cell[gridSizeX][gridSizeY];

        for (int x = 0; x < gridSizeX; x++) // Loop through x-axis
        {
            for (int y = 0; y < gridSizeY; y++)  // Loop through y-axis
            {
                // Create and initialize new cell
                cellGrid[x][y] = new Cell(cellSize, x * cellSize, y * cellSize, x, y);
                cellGrid[x][y].setFill(Color.rgb(255, 255, 255));
                cellGrid[x][y].setStroke(Color.rgb(0, 0, 0));

                // Set an event to the cell so the state can be changed by clicking on it
                cellGrid[x][y].setOnMouseClicked(event -> {
                    ((Cell) event.getSource()).ChangeState();
                });

                // Add cell to the main group
                group.getChildren().add(cellGrid[x][y]);
            }
        }

        //createLevel(gridSizeX, gridSizeY);
    }

    public void SetSpeed(int delayInMS)
    {
        gameSpeed = delayInMS;
    }

    public void SetColor(Color color)
    {
        for (Cell[] cells:cellGrid)
        {
            for (Cell cell:cells)
            {
                cell.SetCellColor(color);
            }
        }
    }

    public void SetGridSize(int gridSizeX, int gridSizeY, float cellSize)
    {
        for (Cell[] cellX : cellGrid)
        {
            for (Cell cell : cellX)
            {
                group.getChildren().remove(cell);
            }
        }

        this.InitializeGame(group, gridSizeX, gridSizeY, cellSize);
    }

    public void saveGrid(){
        SaveLoad.saveGrid("arie",Cell.CellToArray(cellGrid));
    }
    public void loadGrid(){
        cellGrid=Cell.fileToGrid();
        //maybe run initializeGame game again to draw loaded board
    }

    private void createLevel(int gridSizeY, int gridSizeX)
    {
        int[] arr = new int[gridSizeY*gridSizeX];
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
            timer.cancel();
            return;
        }

        //Start playing
        this.playing = true;
        playButton.setGraphic(pause);
        timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                OnNextFrame();
            }
        },gameSpeed, gameSpeed);
    }

    // Jump to the next frame by calculating and setting the cells next state
    public void OnNextFrame()
    {
        //Calculate New Cell States
        for (Cell[] cellX : cellGrid)
        {
            for (Cell cell : cellX)
            {
                cell.CalculateNewState(cellGrid, temp_gridSizeX, temp_gridSizeY);
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

    public void OnPreviousFrame()
    {
        //Calculate New Cell States
        for (Cell[] cellX : cellGrid)
        {
            for (Cell cell : cellX)
            {
                cell.SetLastState();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        InitializeGame(group, temp_gridSizeX, temp_gridSizeY, temp_cellSize);

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
            SetGridSize(50,30, 10);
        });
        grid25_15.setOnAction(event -> {
            SetGridSize(25,15, 20);
        });

        //Speed Menu
        speed1.setOnAction(event -> {
            SetSpeed(1000);
        });
        speed0_5.setOnAction(event -> {
            SetSpeed(500);
        });
        speed0_1.setOnAction(event -> {
            SetSpeed(100);
        });

        //Color Menu
        colorBlack.setOnAction(event -> {
            SetColor(Color.BLACK);
        });
        colorBlue.setOnAction(event -> {
            SetColor(Color.BLUE);
        });
        colorGreen.setOnAction(event -> {
            SetColor(Color.GREEN);
        });
        colorRed.setOnAction(event -> {
            SetColor(Color.RED);
        });

        //Save
        saveButton.setOnMouseClicked(event -> {
            saveGrid();
        });

        //Load
        loadButton.setOnMouseClicked(event -> {
            loadGrid();
        });

        //Play|Pause Button
        playButton.setGraphic(play);
        playButton.setOnMouseClicked(event -> {
            OnPlay();
        });

        //Next Generation
        nextButton.setOnMouseClicked(event -> {
            if (playing)
            {
                return;
            }
            OnNextFrame();
        });

        //Previous Generation
        backButton.setOnMouseClicked(event -> {
            if (playing)
            {
                return;
            }
            OnPreviousFrame();
        });
    }
}

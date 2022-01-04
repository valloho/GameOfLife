package com.theanimalfarm.gameoflife;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
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
    }

    @FXML
    public void playGame()
    {
        playButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                OnPlay();
            }
        });
    }

    @FXML
    public void nextFrame() {
        nextButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                if (playing)
                {
                    return;
                }
                System.out.println("Next Step");
                OnNextFrame();
            }
        });
    }

    private void OnPlay()
    {
        if (playing)
        {
            //Stop playing
            this.playing = false;
            System.out.println("Not Playing");
            timer.cancel();
            return;
        }

        //Start playing
        this.playing = true;
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
    private void OnNextFrame()
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
    }


}

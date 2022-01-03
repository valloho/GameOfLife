package com.theanimalfarm.gameoflife;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.util.TimerTask;
import java.util.Timer;

import java.awt.*;

public class GameManager
{
    // Grid Variables
    private int gridSizeX = 50;
    private int gridSizeY = 30;
    private Cell[][] grid;

    // Cell Variables
    private float cellSize = 10;

    //Button Variables
    private boolean playing;
    Button playButton;
    Button nextFrameButton;

    //Gameplay
    Timer timer;

    public void InitializeGame(Group root)
    {
        //Create Grid
        grid = new Cell[this.gridSizeX][this.gridSizeY];

        for (int x = 0; x < gridSizeX; x++)
        {
            for (int y = 0; y < gridSizeY; y++)
            {
                grid[x][y] = new Cell(cellSize, x * cellSize, y * cellSize, x, y);
                grid[x][y].setFill(Color.rgb(255,255,255));
                grid[x][y].setStroke(Color.rgb(0,0,0));

                grid[x][y].setOnMouseClicked(new EventHandler<MouseEvent>()
                {
                    @Override
                    public void handle(MouseEvent event)
                    {
                        ((Cell) event.getSource()).ChangeState();
                    }
                });

                root.getChildren().add(grid[x][y]);
            }
        }

        //Create Play Button
        playButton = new Button(">");
        playButton.setTranslateY(gridSizeY * cellSize + cellSize * 2);

        playButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                OnPlay();
            }
        });
        root.getChildren().add(playButton);

        //Create Next Frame Button
        nextFrameButton = new Button("=>");
        nextFrameButton.setTranslateX(40);
        nextFrameButton.setTranslateY(gridSizeY * cellSize + cellSize * 2);
        nextFrameButton.setOnAction(new EventHandler<ActionEvent>() {
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
        root.getChildren().add(nextFrameButton);

    }

    private void OnPlay()
    {
        if (playing)
        {
            //Stop playing
            this.playing = false;
            playButton.setText(">");
            System.out.println("Not Playing");
            timer.cancel();
            return;
        }

        //Start playing
        this.playing = true;
        playButton.setText("#");
        System.out.println("Playing");
        timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                System.out.println("Hello World");
                OnNextFrame();
            }
        },200, 200);
    }

    private void OnNextFrame()
    {
        //Calculate New Cell States
        for (Cell[] cellX : grid)
        {
            for (Cell cell : cellX)
            {
                cell.CalculateNewState(grid, gridSizeX, gridSizeY);
            }
        }

        //Set New Cell States
        for (Cell[] cellX : grid)
        {
            for (Cell cell : cellX)
            {
                cell.SetNewState();
            }
        }
    }
}

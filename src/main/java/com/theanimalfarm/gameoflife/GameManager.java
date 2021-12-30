package com.theanimalfarm.gameoflife;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GameManager
{
    // Grid Variables
    private int gridSizeX = 50;
    private int gridSizeY = 30;
    private Cell[][] grid = new Cell[this.gridSizeX][this.gridSizeY];

    private int tempX;
    private int tempY;
    // Cell Variables
    private float cellSize = 10;

    public void InitializeGame(Group root)
    {
        for (int x = 0; x < gridSizeX; x++)
        {
            for (int y = 0; y < gridSizeY; y++)
            {
                grid[x][y] = new Cell(new Rectangle(x * cellSize, y * cellSize, cellSize, cellSize), x, y);
                grid[x][y].getGraphic().setFill(Color.rgb(255,255,255));
                grid[x][y].getGraphic().setStroke(Color.rgb(0,0,0));

                tempX = x;
                tempY = y;

                grid[x][y].getGraphic().setOnMouseClicked(new EventHandler<MouseEvent>()
                {
                    @Override
                    public void handle(MouseEvent event)
                    {
                        grid[tempX][tempY].ChangeState();
                    }
                });

                root.getChildren().add(grid[x][y].getGraphic());
            }
        }
    }
}

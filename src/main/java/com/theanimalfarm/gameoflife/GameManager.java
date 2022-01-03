package com.theanimalfarm.gameoflife;


import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


public class GameManager
{
    // Grid Variables
    private int gridSizeX = 50;
    private int gridSizeY = 30;
    private Cell[][] grid = new Cell[this.gridSizeX][this.gridSizeY];

    // Cell Variables
    private float cellSize = 10;



    public void initializeGame(Group group)
    {
        for (int x = 0; x < gridSizeX; x++)
        {
            for (int y = 0; y < gridSizeY; y++)
            {
                grid[x][y] = new Cell(cellSize, x * cellSize, y * cellSize);
                grid[x][y].setFill(Color.rgb(255,255,255));
                grid[x][y].setStroke(Color.rgb(0,0,0));

                grid[x][y].setOnMouseClicked(new EventHandler<MouseEvent>()
                {
                    @Override
                    public void handle(MouseEvent event)
                    {
                        ((Cell) event.getSource()).ChangeState();
                        //grid[tempX][tempY].ChangeState();
                    }
                });

                group.getChildren().add(grid[x][y]);
            }
        }
    }


}

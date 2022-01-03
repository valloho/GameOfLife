package com.theanimalfarm.gameoflife;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle
{
    private boolean alive;
    private boolean newState;

    private int cellIndexX;
    private int cellIndexY;

    public Cell(double cellSize, double positionX, double positionY, int cellIndexX, int cellIndexY)
    {
        super(positionX, positionY, cellSize, cellSize);
        this.cellIndexX = cellIndexX;
        this.cellIndexY = cellIndexY;
        this.alive = false;
    }

    public boolean GetState()
    {
        return alive;
    }

    public void ChangeState()
    {
        if (alive)
        {
            alive = false;
            super.setFill(Color.WHITE);
            return;
        }

        alive = true;
        super.setFill(Color.BLACK);
    }

    public void ChangeGraphic(boolean alive)
    {
        if (alive)
        {
            super.setFill(Color.BLACK);
            return;
        }

        super.setFill(Color.WHITE);
    }

    public void CalculateNewState(Cell[][] cells, int gridSizeX, int gridSizeY)
    {
        int aliveNeighbourCells = 0;

        for (int x = Math.max(0, cellIndexX - 1); x <= Math.min(cellIndexX + 1, gridSizeX - 1); x++)
        {
            for (int y = Math.max(0, cellIndexY - 1); y <= Math.min(cellIndexY + 1, gridSizeY - 1); y++)
            {
                if (cells[x][y] != this)
                {
                    if (cells[x][y].GetState())
                    {
                        aliveNeighbourCells++;
                    }
                }
            }
        }

        if (alive)
        {
            this.newState = aliveNeighbourCells >= 2 && aliveNeighbourCells <= 3;
            return;
        }

        this.newState = aliveNeighbourCells == 3;
    }

    public void SetNewState()
    {
        alive = newState;
        ChangeGraphic(alive);
    }
}

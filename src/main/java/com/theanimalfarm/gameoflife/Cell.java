package com.theanimalfarm.gameoflife;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle
{
    private boolean alive;

    public Cell(double cellSize, double positionX, double positionY)
    {
        super(positionX, positionY, cellSize, cellSize);
        this.alive = false;
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
}

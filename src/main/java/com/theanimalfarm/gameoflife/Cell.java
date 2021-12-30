package com.theanimalfarm.gameoflife;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell
{
    private boolean alive;
    private Rectangle graphic;

    private float positionX;
    private float positionY;

    public Cell(Rectangle graphic, float positionX, float positionY)
    {
        this.positionX = positionX;
        this.positionY = positionY;
        this.graphic = graphic;
        this.alive = false;
    }

    public void ChangeState()
    {
        if (alive)
        {
            alive = false;
            graphic.setFill(Color.WHITE);
            return;
        }

        alive = true;
        graphic.setFill(Color.BLACK);
    }

    public Rectangle getGraphic()
    {
        return this.graphic;
    }
}

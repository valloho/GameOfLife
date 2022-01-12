package com.theanimalfarm.gameoflife;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.Stack;

/**
 * A cell object is one tiny rectangle that can either be alive or dead.
 * Cell extends from rectangle, so it has a visual representation and can have an "onMouseClick" event.
 */
public class Cell extends Rectangle
{
    //region VARIABLES -------------------------------------------------------------------------------------------------
    /**
     * Cell state variables
     */
    private boolean alive;
    private boolean newState;

    /**
     * Cell index variables
     */
    private int cellIndexX;
    private int cellIndexY;
    private Color cellColor = Color.BLACK;

    /**
     * Last cell states
     */
    private Stack<Boolean> lastCellStates = new Stack<>();
    private static int maxCellStack = 20;
    //endregion

    //region CONSTRUCTOR -----------------------------------------------------------------------------------------------

    /**
     * Constructor to initialize a cell
     * @param cellSize
     * @param positionX
     * @param positionY
     * @param cellIndexX
     * @param cellIndexY
     */
    public Cell(double cellSize, double positionX, double positionY, int cellIndexX, int cellIndexY)
    {
        super(positionX, positionY, cellSize, cellSize);
        this.cellIndexX = cellIndexX;
        this.cellIndexY = cellIndexY;
        this.alive = false;
    }
    //endregion

    //region GETTER ----------------------------------------------------------------------------------------------------

    /**
     * Get the current state of a cell (dead/alive)
     * @return boolean
     */
    public boolean GetState()
    {
        return alive;
    }
    //endregion

    //region SETTER ----------------------------------------------------------------------------------------------------

    /**
     * Set the state of the cell to the state of the previous frame
     */
    public void SetLastState()
    {
        if (lastCellStates.stream().count() < 1)
        {
            System.out.println("Not more steps available!");
            return;
        }

        newState = lastCellStates.pop();

        alive = newState;
        ChangeGraphic(alive);
    }

    /**
     * Resets the color of the cell if it's alive
     * @param cellColor
     */
    public void SetCellColor(Color cellColor)
    {
        this.cellColor = cellColor;

        if (alive)
        {
            this.setFill(cellColor);
        }
    }

    /**
     * Set the new state and change the color
     */
    public void SetNewState()
    {
        lastCellStates.push(alive);
        if (lastCellStates.stream().count() > maxCellStack)
        {
            lastCellStates.remove(0);
        }

        alive = newState;
        ChangeGraphic(alive);
    }
    //endregion

    //region PUBLIC METHODS --------------------------------------------------------------------------------------------

    /**
     * Change the current state of the cell to the opposite state
     */
    public void ChangeState()
    {
        if (alive)
        {
            // Set cell to dead if cell was alive
            alive = false;
            ChangeGraphic(false);
            return;
        }

        // Set cell to alive if cell was dead
        alive = true;
        ChangeGraphic(true);
    }

    /**
     * Change cell graphic to current cell state
     * @param cellState
     */
    public void ChangeGraphic(boolean cellState)
    {
        if (cellState)
        {
            // Change color to black if cell is alive
            super.setFill(cellColor);
            return;
        }

        // Change color to white if cell is dead
        super.setFill(Color.WHITE);
    }

    /**
     * Calculate new cell state (state of next frame)
     * @param cells
     * @param gridSizeX
     * @param gridSizeY
     */
    public void CalculateNewState(Cell[][] cells, int gridSizeX, int gridSizeY)
    {
        int aliveNeighbourCells = 0;

        // Find the amount of neighbor cells, by looping through all the cells around the current cell
        for (int x = Math.max(0, cellIndexX - 1); x <= Math.min(cellIndexX + 1, gridSizeX - 1); x++) // Loop from cell[x - 1][y] to cell[x + 1][y]
        {
            for (int y = Math.max(0, cellIndexY - 1); y <= Math.min(cellIndexY + 1, gridSizeY - 1); y++) //Loop from cell[x][y - 1] to cell[x][y + 1]
            {
                if (cells[x][y] != this) // Ignore itself
                {
                    if (cells[x][y].GetState()) // If the cell is alive add 1 to aliveNeighborCells
                    {
                        aliveNeighbourCells++;
                    }
                }
            }
        }

        // Calculate the new state by looking at the amount of living neighbor cells
        if (alive)
        {
            // A living cell with 1 neighbor cell dies due to loneliness
            // A living cell with 2 or 3 neighbor cells stays alive
            // A living cell with more than 3 neighbor cells dies due to overpopulation
            this.newState = aliveNeighbourCells >= 2 && aliveNeighbourCells <= 3;
            return;
        }

        this.newState = aliveNeighbourCells == 3; // A dead cell with exactly 3 neighbors is reborn
    }
    //endregion
}

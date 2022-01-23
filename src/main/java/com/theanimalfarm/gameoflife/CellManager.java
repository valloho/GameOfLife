package com.theanimalfarm.gameoflife;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import java.util.Timer;
import java.util.TimerTask;

/**
 * CellManager manages all cells within a 2 dimensional grid
 */
public class CellManager {
    //region Variables -------------------------------------------------------------------------------------------------
    /**
     * Grid variables
     */
    private int temp_gridSizeX = 50;
    private int temp_gridSizeY = 30;
    private Cell[][] cellGrid;

    /**
     * Cell variables
     */
    private float temp_cellSize = 10;
    private Color currentColor = Color.BLACK;

    /**
     * Updating of the cells variables
     */
    private Timer timer;
    private int gameSpeed = 500;
    private boolean playing;
    //endregion

    //region GETTER ----------------------------------------------------------------------------------------------------

    /**
     * Returns the cellGrid in a 2 dimensional array
     *
     * @return
     */
    public Cell[][] GetCellGrid()
    {
        return cellGrid;
    }

    public int GetGridSizeX()
    {
        return temp_gridSizeX;
    }

    public int GetGridSizeY()
    {
        return temp_gridSizeY;
    }
    //endregion

    //region SETTER ----------------------------------------------------------------------------------------------------

    /**
     * Setting the speed/delay, the cells are updated
     * (in milliseconds)
     *
     * @param delayInMS
     */
    public void SetSpeed(int delayInMS)
    {
        System.out.println("Hello");
        gameSpeed = delayInMS;

        if (playing)
        {
            OnPause();
            OnPlay();
        }
    }

    /**
     * Setting the color of all alive cells
     *
     * @param color
     */
    public void SetColor(Color color)
    {
        for (Cell[] cells : cellGrid)
        {
            for (Cell cell : cells)
            {
                cell.SetCellColor(color);
            }
        }
    }

    /**
     * Setting a new grid and cell size
     *
     * @param group
     * @param gridSizeX
     * @param gridSizeY
     * @param cellSize
     */
    public void ResetGrid(Group group, int gridSizeX, int gridSizeY, float cellSize)
    {
        for (Cell[] cellX : cellGrid)
        {
            for (Cell cell : cellX)
            {
                group.getChildren().remove(cell);
            }
        }

        this.temp_gridSizeX = gridSizeX;
        this.temp_gridSizeY = gridSizeY;
        this.temp_cellSize = cellSize;

        this.InitializeGame(group);
        SetColor(currentColor);
    }

    /**
     * Set the state of the cells to a specific boolean[][] array
     *
     * @param cellGridStates
     */
    public void SetCellState(boolean[][] cellGridStates)
    {
        if (cellGridStates.length != cellGrid.length || cellGridStates.length == 0)
        {
            System.out.println("The size x of the parameter is either 0 or not identical to the current grid size x");
            return;
        }

        if (cellGridStates[0].length != cellGrid[0].length)
        {
            System.out.println("The size y of the parameter is not identical to the current grid size y");
            return;
        }

        for (int x = 0; x < cellGridStates.length; x++)
        {
            for (int y = 0; y < cellGridStates[x].length; y++)
            {
                cellGrid[x][y].SetSpecificState(cellGridStates[x][y]);
            }
        }
    }
    //endregion

    //region PUBLIC METHODS --------------------------------------------------------------------------------------------

    /**
     * Initialize the game by creating a grid containing all the cells
     *
     * @param group
     */
    @FXML
    public void InitializeGame(Group group)
    {
        // Create a cell grid by looping through the 2 dimensional array and creating new cells
        cellGrid = new Cell[temp_gridSizeX][temp_gridSizeY];

        for (int x = 0; x < temp_gridSizeX; x++) // Loop through x-axis
        {
            for (int y = 0; y < temp_gridSizeY; y++)  // Loop through y-axis
            {
                // Create and initialize new cell
                cellGrid[x][y] = new Cell(temp_cellSize, x * temp_cellSize, y * temp_cellSize, x, y);
                cellGrid[x][y].setFill(Color.rgb(255, 255, 255));
                cellGrid[x][y].setStroke(Color.rgb(0, 0, 0));

                // Set an event to the cell so the state can be changed by clicking on it
                cellGrid[x][y].setOnMouseClicked(event -> {
                    ((Cell) event.getSource()).ChangeState();
                });

                // Set event to change the cell state when the mouse is dragged over it
                cellGrid[x][y].setOnMouseDragEntered( event -> {

                    if (event.getSource() instanceof Cell)
                    {
                        Cell cell = (Cell) (event.getSource());
                        cell.ChangeState();
                    }
                });

                // Add the cell to the group
                group.getChildren().add(cellGrid[x][y]);
            }
        }

        // Start full drag of the group so the cell states can be changed
        group.setOnDragDetected(e -> group.startFullDrag());
    }

    /**
     * Start playing by creating a loop that calls OnNextFrame every few seconds
     */
    public void OnPlay()
    {
        this.playing = true;
        timer = new Timer();

        timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                OnNextFrame();
            }
        }, gameSpeed, gameSpeed);
    }

    /**
     * Stop playing by pausing the timer
     */
    public void OnPause()
    {
        this.playing = false;
        timer.cancel();
    }

    /**
     * Jump to the next frame by calculating and setting the cells next state
     */
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

    /**
     * Jump to the previous frame by setting the last saved cell state
     */
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

    /**
     * Kill all cells (set all cells to "dead")
     */
    public void ClearCells()
    {
        boolean[][] deadCells = new boolean[this.temp_gridSizeX][this.temp_gridSizeY];
        SetCellState(deadCells);
    }
    //endregion
}

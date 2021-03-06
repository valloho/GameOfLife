package com.theanimalfarm.gameoflife;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveLoadManager
{
    //region SAVE GAME -------------------------------------------------------------------------------------------------

    /**
     * writes a one dimensional integer array to a txt file
     * @param file
     * @param idArray
     */
    private void writeToFile(File file, int[] idArray)
    {
        try
        {
            PrintWriter print = new PrintWriter(file);
            for (int i = 0 ;i < idArray.length; i++)
            {
                print.println(idArray[i]);
            }
            print.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * change Cell[][] to int[][]
     * @param cell
     * @return
     */
    private int[][] CellToInt(Cell[][] cell)
    {
        int[][] array = new int[cell.length][cell[0].length];

        for (int i = 0; i < cell.length; i++)
        {
            for (int j = 0; j < cell[i].length; j++)
            {
                if (cell[i][j].GetState())
                {
                    array[i][j] = 1;
                } else
                {
                    array[i][j] = 0;
                }
            }
        }
        return array;
    }

    /**
     * checks if file exists and calls a method that changes a two-dimensional integer array to a one dimensional integer array
     * @param name
     * @param idArr
     */
    private void saveGrid(String name,int[][] idArr)
    {
        File gridFile = new File("src/main/resources/" + name + ".txt");

        if (gridFile.exists())
        {
            writeToFile(gridFile, ArrayConverter.TwoDTo1D(idArr));
        }
        else
        {
            System.out.println("File " + name + "does not exist!");
        }
    }
    //endregion

    //region LOAD GAME -------------------------------------------------------------------------------------------------

    /**
     * Reads information of a file and writes it to an arraylist
     * @param emil
     * @return arraylist
     */
    private ArrayList<Integer> readFromFile(File emil)
    {
        ArrayList<Integer> list = new ArrayList<>();

        try
        {
            Scanner scan = new Scanner(emil);
            while (scan.hasNextLine())
            {
                list.add(Integer.parseInt(scan.nextLine()));
            }
            scan.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Change int[][] to Cell[][]
     * @param intGrid
     * @return
     */
    private boolean[][] IntToCell(int[][] intGrid)
    {
        if (intGrid.length < 1)
        {
            return new boolean[0][0];
        }

        boolean[][] cellStates = new boolean[intGrid.length][intGrid[0].length];

        for (int i = 0; i < intGrid.length; i++)
        {
            for (int j = 0 ; j < intGrid[i].length;j++)
            {
                if(intGrid[i][j] == 1)
                {
                    cellStates[i][j] = true;
                }
                else
                {
                    cellStates[i][j] = false;
                }
            }
        }
        return cellStates;
    }

    /**
     * Gets arraylist from readFromFile and changes it to a two-dimensional integer array
     * @param fileName
     * @return two-dimensional integer array
     */
    public int[][] GetGridFromFile(String fileName, int gridSizeX, int gridSizeY)
    {
        File gridFile = new File("src/main/resources/" + fileName + ".txt");

        if (gridFile.exists())
        {
            ArrayList<Integer> list = readFromFile(gridFile);
            return ArrayConverter.listTo2D(list,gridSizeX,gridSizeY);
        }
        else
        {
            System.out.println("File " + fileName + "does not exist!");
            return null;
        }
    }

    //endregion

    //creates a txt file at start of program
    public  void createBoard(String name, int[] idArray)
    {
        File kaine = new File("src/main/resources/" + name + ".txt");
        if (kaine.exists())
        {
            System.out.println("File " + name + " already exists!");
        }
        else
        {
            try
            {
                kaine.createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            writeToFile(kaine, idArray);
        }
    }

    /**
     * Save the current cell states
     */
    public void SaveGame(Cell[][] cellGrid)
    {
        int[][] intGrid = CellToInt(cellGrid);
        if(intGrid.length == 50){
            saveGrid("saveGame 50x30", intGrid);
        }
        else if(intGrid.length == 25){
            saveGrid("saveGame 25x15", intGrid);
        }
    }

    /**
     * Load the last saved cell states
     */
    public boolean[][] LoadGame(String file,int gridSizeX, int gridSizeY) {
        int[][] intGrid = new int[gridSizeX][gridSizeY];
        if ((gridSizeX * gridSizeY) == 1500) {
            intGrid = GetGridFromFile(file, gridSizeX, gridSizeY);
        } else if ((gridSizeX * gridSizeY) == 375) {
            intGrid = GetGridFromFile(file, gridSizeX, gridSizeY);
        }
        boolean[][] cellStates = IntToCell(intGrid);
        return cellStates;
    }
}

package com.theanimalfarm.gameoflife;

import java.util.ArrayList;

public class ArrayConverter
{
    /**
     *
     * changes an ArrayList to a 2D int Array and returns that Array
     */
    public static int[][] listTo2D(ArrayList<Integer> list, int xSize, int ySize)
    {
        int[][] twoDArray = new int[xSize][ySize];

        for (int i = 0; i < twoDArray.length; i++)
        {
            for (int j = 0; j < twoDArray[i].length; j++)
            {
                int index = j * xSize + i;
                if (index <xSize*ySize)
                {
                    twoDArray[i][j] = list.get(index);
                }
            }
        }
        return twoDArray;
    }

    /**
     * takes a 2D Array and changes it to a 1D Array
     * returns a 1D Array
     */
    public static int[] TwoDTo1D(int[][] arr) {
        int[] OneDArray = new int[arr.length * arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                int index = j * arr.length + i;
                if (index < arr.length*arr[0].length) {
                    OneDArray[index] = arr[i][j];
                }
            }
        }
        return OneDArray;
    }
}
package com.theanimalfarm.gameoflife;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayConverter {
    public static int[][] listTo2D(ArrayList<Integer> list, int xSize, int ySize) {
        int[][] twoDArray = new int[xSize][ySize];

        for (int i = 0; i < twoDArray.length; i++) {
            for (int j = 0; j < twoDArray[i].length; j++) {
                int index = i * xSize + j;
                twoDArray[i][j] = list.get(index);
            }
        }
        return twoDArray;
    }

    public static int[] TwoDTo1D(int[][] arr) {
        int[] OneDArray = new int[arr.length * arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                int index = j * arr.length + i;
                if (index < arr.length*arr[0].length) {
                    OneDArray[index] = arr[i][j];
                    //System.out.println(OneDArray[index]);
                }
            }
        }
        //System.out.println(Arrays.toString(OneDArray));
        return OneDArray;
    }
}
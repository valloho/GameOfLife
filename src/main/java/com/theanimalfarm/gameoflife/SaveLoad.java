package com.theanimalfarm.gameoflife;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveLoad {
    //creates a txt file at start of program
    public static void createBoard(String name, int[] idArray) {
        File kaine = new File("src/main/resources/" + name + ".txt");
        if (kaine.exists()) {
            System.out.println("File " + name + "already exists!");
        } else {
            try {
                kaine.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            writeToFile(kaine, idArray);
        }
    }

    //writes a one dimensional integer array to a txt file
    private static void writeToFile(File file, int[] idArray) {
        try {
            PrintWriter print = new PrintWriter(file);
            for (int i = 0 ;i < idArray.length; i++) {
                print.println(idArray[i]);
            }
            print.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // checks if file exists and calls a method that changes a two-dimensional integer array to a one dimensional integer array
    public static void saveGrid(String name,int[][] idArr){
        File gridFile = new File("src/main/resources/" + name + ".txt");
        if (gridFile.exists()){
            writeToFile(gridFile,ArrayConverter.TwoDTo1D(idArr));
        }else{
            System.out.println("File " + name + "does not exist!");
        }
    }

    //reads information of a file and writes it to an arraylist
    //returns arraylist
    private static ArrayList<Integer> readFromFile(File emil) {
        ArrayList<Integer> list = new ArrayList<>();
        try {
            Scanner scan = new Scanner(emil);
            while (scan.hasNextLine()) {
                list.add(Integer.parseInt(scan.nextLine()));
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    // gets arraylist from readFromFile and changes it to a two-dimensional integer array
    //returns two-dimensional integer array
    public static int[][] GetGrid(String name) {
        File gridFile = new File("src/main/resources/" + name + ".txt");
        if (gridFile.exists()) {
            ArrayList<Integer> list = readFromFile(gridFile);
            return ArrayConverter.listTo2D(list,50,30);
        } else {
            System.out.println("File " + name + "does not exist!");
            return null;
        }

    }
}


package com.theanimalfarm.gameoflife;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveLoad {
    public static void createFile() {
        File file = new File("src/main/resources/testMe.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    private static void writeToFile(File file, int[] idArray) {
        try {
            PrintWriter print = new PrintWriter(file);
            for (int i : idArray) {
                print.println(idArray[i]);
            }
            print.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void saveGrid(String name,int[][] idArr){
        File gridFile = new File("src/main/resources/" + name + ".txt");
        if (gridFile.exists()){
            writeToFile(gridFile,ArrayConverter.TwoDTo1D(idArr));
        }else{
            System.out.println("File " + name + "does not exist!");
        }
    }

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


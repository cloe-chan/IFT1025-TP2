package com.example.devoir2;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Scores {
    private ArrayList<Integer> scoresInt; // scoresInt
    private ArrayList<String> scoresString; // scores
    private ArrayList<String> displayString; // scoresNames
    private File file = new File("scores.txt");

    public ArrayList<Integer> getScoresInt() {return scoresInt;}
    public void setScoresInt(ArrayList<Integer> scoresInt) {this.scoresInt = scoresInt;}

    public ArrayList<String> getScoresString() {return scoresString;}
    public void setScoresString(ArrayList<String> scoresString) {this.scoresString = scoresString;}

    public ArrayList<String> getDisplayString() {return displayString;}
    public void setDisplayString(ArrayList<String> displayString) {this.displayString = displayString;}


    public Scores(){
        scoresInt = new ArrayList<>();
        scoresString = new ArrayList<>();
        displayString = new ArrayList<>();
    }

    // ajouter score aux ARRAYLISTs
    public void addScore(int inputScore, String nom){
        int indexCounter = 0 ;

        // if arrayList is empty
        if (scoresInt.size() == 0){
            scoresInt.add(inputScore);
            scoresString.add(nom + "-" + inputScore);
            displayString.add("#" + 1 + "-" + scoresString.get(0));
        }
        else {

            // trouver ou mettre le nouveau score
            for (int score : scoresInt) {
                if (inputScore > score && scoresInt.size() < 10) {
                    indexCounter++;
                    continue;
                } else {
                    break;
                }
            }

            // ajouter au bon endroit s'il reste de l'espace
            if (indexCounter < 10) {
                scoresInt.add(indexCounter, inputScore);
                scoresString.add(indexCounter, nom + "-" + inputScore);
            }
            // ajouter les chiffres des positions dans l'arraylist qui sera utiliser pour faire le fichier
            for(int i=0; i< scoresInt.size(); i++ ){
                displayString.set(i, "#" + i+1 + "-" + scoresString.get(i));
            }
        }

        writeScoresFile(String.join("\n",displayString));

    }

    public void writeScoresFile(String scoresString){
        boolean result;
        for(int i=0; i<displayString.size(); i++){
            scoresString += displayString.get(i) + "\n";
        }
        // creating and or writing in the file /
        try{
            result = file.createNewFile();
            FileWriter scoresWriter = new FileWriter("scores.txt");
            // file does not already exist
            if(result){
                scoresWriter.write(scoresString);
                System.out.println("file created");
                scoresWriter.close();
            }
            // file already exists
            else{
                try {
                    getScores();
                    // clear file avant d'ecrire
                    new FileOutputStream("scores.txt").close();
                    scoresWriter.write(scoresString);
                    scoresWriter.close();
                    //System.out.println("Successfully wrote to the file.");
                } catch (IOException error) {
                    //System.out.println("An error occurred.");
                    error.printStackTrace();
                }
            }
        }catch(IOException error){
            error.printStackTrace();
        }

    }

    // get contents of txt file if exists already
    public String getScores(){
        String scoreString = "";
        // Reads data from the file
        try {
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                scoreString += fileReader.nextLine();

            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        String[] fileLines = scoreString.split("\n");

        // adds scores to score arraylist and displaystrings to display strings arraylist
        for(int i = 0; i<fileLines.length; i++) {
            //fileLines[i] = fileLines[i].substring(fileLines[i].indexOf("-") + 1);
            //scoresInt.add(i, Integer.parseInt(fileLines[i]));
            //displayString.add(i, names[i]);

            String[] lineContents = fileLines[i].split("-");
            System.out.println("this line contains" + lineContents);
            scoresInt.add(Integer.parseInt(lineContents[2]));
            scoresString.add(lineContents[1] + "-" + lineContents[2]);
            displayString.add(fileLines[i]);

        }
        return scoreString;

    }
}

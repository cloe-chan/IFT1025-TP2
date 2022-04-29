import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static <Int> void main(String args[]){
        ArrayList<String> scores = new ArrayList<>();
        ArrayList<Integer> scoresInt = new ArrayList<>();
        ArrayList<String> scoresNames = new ArrayList<>();
        String scoresString = "";
        File file = new File("scores.txt");
        boolean result;
        
        //for testing
        scores.add("Charles-1234");
        scores.add("Cloe-34");
        scores.add("CA-24");
        scores.add("qwerty-12");
        scores.add("AAA-11");
        scores.add("asdfg-9");
        scores.add("me-5");

        for(int i=0; i<scores.size(); i++){
            scoresString = scoresString + scores.get(i) + "\n";
        }

        // creating and or writing in the file // might need some fixes in if cause we need to add scroeString once in there
        try{
            result = file.createNewFile();
            if(result){
                System.out.println("file created");
            }
            else{
                try {
                    FileWriter myWriter = new FileWriter("scores.txt");
                    myWriter.write(scoresString);
                    myWriter.close();
                    System.out.println("Successfully wrote to the file.");
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }


        // Reads data from the file
        try {
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        String[] str = scoresString.split("\n");
        int[] intArr = new int [str.length];
        //display string
        String[] names = str;

        // adds scores to score arraylist and displaystrings to display strings arraylist
        for(int i = 0; i<str.length; i++) {
            str[i] = str[i].substring(str[i].indexOf("-") + 1);
            intArr[i] = Integer.parseInt(str[i]);
            scoresInt.add(i, intArr[i]);
            scoresNames.add(i, names[i]);
            System.out.println(scoresNames.get(i));

        }
    }
}

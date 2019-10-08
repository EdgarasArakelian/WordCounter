package Solution;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BuildResults {
    ArrayList<String> words = new ArrayList<>();

    void buildListFromHash(Map<String, Integer> countedWords){
        for (Map.Entry m : countedWords.entrySet()) {
            words.add(m.getKey().toString());
        }
        Collections.sort(words);
    }

    void printResultsToMultipleFiles(Map<String, Integer> countedWords, ArrayList<String> excludedWords) throws IOException {
        ArrayList<String> wordsToPrint = new ArrayList<>();
        char fLetter; // current file first letter
        char pLetter = ' '; // previous file first letter

        for (String word : words){
            if (!excludedWords.contains(word)){ //Checking if the word we want to print in a file doesn't belong to excluded words.
                fLetter = word.charAt(0);

                // If this is first time executed.
                if (pLetter == ' '){
                    wordsToPrint.add(word);
                    pLetter = fLetter;
                }
                else {
                    if (fLetter == pLetter){ // Checking if current word still begins with the same letter like previous word.
                        wordsToPrint.add(word);
                    }
                    else { // if current word has different letter, we print to file
                        printToFile(wordsToPrint, String.valueOf(pLetter), countedWords);
                        wordsToPrint = new ArrayList<>(); // Cleaning up the list
                        pLetter = fLetter; // Setting new letter
                        wordsToPrint.add(word); // Adding current word to the new list
                    }
                }
            }
        }
        // Edge case (last letter):
        if (wordsToPrint.size() != 0)
            printToFile(wordsToPrint, String.valueOf(pLetter), countedWords);

    }

    // Writing to a single file.
    private void printToFile(ArrayList<String> wordsToPrint, String printFileName, Map<String, Integer> countedWords) throws IOException {
        String fileName = printFileName+".txt";
        FileWriter fileWriter = new FileWriter(fileName);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        for (String word : wordsToPrint){
            printWriter.println(word + " " + countedWords.get(word));
        }

        printWriter.close();
        fileWriter.close();
    }

    public void buildResults(Map<String, Integer> countedWords, ArrayList<String> excludedWords) throws IOException {
        buildListFromHash(countedWords); // Taking all words from HashMap and storing them in the List.
        printResultsToMultipleFiles(countedWords, excludedWords);
        printToFile(excludedWords, "results", countedWords); // Printing excluded words.
    }
}

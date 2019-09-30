package Solution;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class WordCounter {
    HashMap<String, Integer> countWords(ArrayList<String> filePaths, boolean keepNumbers){
        HashMap<String, Integer> wordCounter = new HashMap<>();

        for (String path : filePaths){
            File file = new File(path);

            try {
                Scanner sc = new Scanner(file);

                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    String words [] = AdditionalFunctions.splitLine(line, keepNumbers);

                    for (String word : words){
                        word = word.trim();

                        if (wordCounter.containsKey(word)){
                            Integer count = wordCounter.get(word);
                            count++;
                            wordCounter.put(word,count);
                        }
                        else {
                            wordCounter.put(word, 1);
                        }
                    }
                }
                sc.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("File was not found at this path: " + path);
            } catch (Exception e){
                System.out.println("Error has occurred while dealing with this file: " + path);
            }
        }

        return wordCounter;
    }
}
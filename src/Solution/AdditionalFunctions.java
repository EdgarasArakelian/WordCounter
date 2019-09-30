package Solution;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AdditionalFunctions {

    // Function, which splits line into words and remove all symbols(numbers).
    public static String[] splitLine(String line, boolean keepNumbers){
        String words[];

        if (keepNumbers)
            words = line.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase().split("\\s");
        else
            words = line.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");

        return words;
    }

    // Finds all files in root folder.
    public static void buildFilePaths(final File folder) throws IOException {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) { // If file is directory go inside and check if there are more files to read.
                buildFilePaths(fileEntry);
            } else {
                // Checking if file is exclude.txt
                if (fileEntry.getName().equals(Runnable.EXCLUDE_FILE_NAME)) {
                    Runnable.excludeFilePath = fileEntry.getAbsolutePath(); //Storing it's path separately
                } else {
                    Runnable.filePaths.add(fileEntry.getAbsolutePath());
                }
            }
        }
    }

    // Retrieve words that should be excluded and displayed in the results file.
    static public ArrayList<String> readExcludedWords(String excludeFilePath, boolean keepNumbers) throws FileNotFoundException {
        ArrayList<String> excludedWords = new ArrayList<>();

        if (!excludeFilePath.equals("")) {
            try {
                File file = new File(excludeFilePath);
                Scanner sc = new Scanner(file);

                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    String words[] = AdditionalFunctions.splitLine(line, keepNumbers);

                    for (String word : words) {
                        word = word.trim();
                        excludedWords.add(word);
                    }
                }

                sc.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("Exclude file not found, file path: " + excludeFilePath);
            }

            return excludedWords;
        } else {
            System.err.println("No \"exclude.txt\" was provided!");
            return new ArrayList<>();
        }
    }
}
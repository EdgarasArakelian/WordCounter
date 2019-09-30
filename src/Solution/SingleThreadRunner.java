package Solution;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SingleThreadRunner {

    public void runSingleThread() throws IOException {
        BuildResults buildResults = new BuildResults();
        WordCounter wordCounter = new WordCounter();

        System.out.println("Counting word occurrence in all files.");
        HashMap<String, Integer> wordsCounted = wordCounter.countWords(Runnable.filePaths, Runnable.keepNumbers);

        System.out.println("Reading words to exclude.");
        ArrayList<String> excludedWords = AdditionalFunctions.readExcludedWords(Runnable.excludeFilePath, Runnable.keepNumbers);

        System.out.println("Building & Displaying results.");
        buildResults.buildResults(wordsCounted, excludedWords);
    }
}

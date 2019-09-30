package Extra;

import Solution.AdditionalFunctions;
import Solution.BuildResults;
import Solution.Runnable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.locks.ReentrantLock;

public class MultiThreadRunner {
    static Hashtable<String, Integer> countedWords;
    static ReentrantLock lock = new ReentrantLock();

    public void runMT() throws IOException, InterruptedException {
        countedWords = new Hashtable<>();

        BuildResults buildResults = new BuildResults();

        System.out.println("Creating " + Runnable.filePaths.size() + " threads");
        WordCounterMT threads[] = new WordCounterMT[Runnable.filePaths.size()]; // Creating new thread for each input file found.

        System.out.println("Launching all threads");
        for (int i = 0; i < Runnable.filePaths.size(); i++) {
            threads[i] = new WordCounterMT(Runnable.filePaths.get(i), Runnable.keepNumbers);
            threads[i].t.start();
        }

        System.out.println("Waiting all threads to finish");
        // Waiting while all threads are done.
        for (WordCounterMT thread : threads) {
            thread.t.join();
        }
        System.out.println("All threads finished working");

        // Converting back to HM so previously written function would work. Might have issues if there is more than 1 null value.
        System.out.println("Converting HashTable to HashMap");
        HashMap<String, Integer> HMConverted = new HashMap<>();
        HMConverted.putAll(countedWords);

        System.out.println("Reading words to exclude");
        ArrayList<String> excludedWords = AdditionalFunctions.readExcludedWords(Runnable.excludeFilePath, Runnable.keepNumbers);

        System.out.println("Building & Displaying results.");
        buildResults.buildResults(HMConverted, excludedWords);
    }
}
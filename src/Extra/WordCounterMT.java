package Extra;

import Solution.AdditionalFunctions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordCounterMT implements Runnable {
    String filePath;
    boolean keepNumbers;
    Thread t;

    public WordCounterMT(String fileP, boolean keepNumbers) {
        this.filePath = fileP;
        this.keepNumbers = keepNumbers;
        t = new Thread(this, "threadName");
    }

    public void run() {
        try {
            countWordsInFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void countWordsInFile() throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String words[] = AdditionalFunctions.splitLine(line, keepNumbers);

            for (String word : words) {
                word = word.trim();

                // Using shared clock to access data.
                MultiThreadRunner.lock.lock();

                try {
                    if (MultiThreadRunner.countedWords.containsKey(word)) {
                        Integer count = MultiThreadRunner.countedWords.get(word);
                        count = count + 1;
                        MultiThreadRunner.countedWords.put(word, count);
                    }
                    else {
                        MultiThreadRunner.countedWords.put(word, 1);
                    }
                }
                finally {
                    // Letting rest threads to run
                    MultiThreadRunner.lock.unlock();
                }
            }
        }
    }
}

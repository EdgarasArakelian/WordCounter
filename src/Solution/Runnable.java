package Solution;

import Extra.MultiThreadRunner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Runnable {
    final static String EXCLUDE_FILE_NAME = "exclude.txt";

    static String rootFolderPath;
    static boolean multiThread;

    public static boolean keepNumbers;
    public static String excludeFilePath;
    public static ArrayList<String> filePaths;

    public static void main(String[] args) throws IOException, InterruptedException {
        setFlags(args);
        filePaths = new ArrayList<>();
        final File folder = new File(rootFolderPath);

        System.out.println("Retrieving all files from root folder");
        AdditionalFunctions.buildFilePaths(folder);

        if (multiThread){
            MultiThreadRunner wCMT = new MultiThreadRunner();
            wCMT.runMT();
        } else {
            SingleThreadRunner sTR = new SingleThreadRunner();
            sTR.runSingleThread();
        }
    }

    private static void setFlags(String flags[]) {
        String flag1 = "";
        String flag2 = "";

        try {
            rootFolderPath = flags[0];
            flag1 = flags[1];
            flag2 = flags[2];
        } catch (Exception e){
            System.err.println("Error while trying to launch application!");
            System.out.println();
            System.out.println("Running application (multithreading and keeping numbers): java Runnable \"root_folder\" -MT -T");
            System.out.println("Running application (multithreading and  not keeping numbers): java Runnable \"root_folder\" -MT -F");
            System.out.println("Running application (single thread and keeping numbers): java Runnable \"root_folder\" -ST -T");
            System.out.println("Running application (single thread and  not keeping numbers): java Runnable \"root_folder\" -ST -F");
            System.out.println();
            System.out.println("After root folder dummy flags can be used. In this case it will launch single thread and numbers will be removed.");
            System.exit(20);
        }

        if (flag1.equals("-MT")){
            multiThread = true;
            System.out.println("Using multiple threads");
        } else {
            multiThread = false;
            System.out.println("Using single tread");
        }

        if (flag2.equals("-T")){
            keepNumbers = true;
            System.out.println("Keeping the numbers");
        } else {
            keepNumbers = false;
            System.out.println("Removing numbers");
        }
    }
}

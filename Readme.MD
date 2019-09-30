# Word Counter v.0.1

### Description:
This JAVA application counts occurrence of the words in all files from given root directory.
It can be run in two ways:
 <br/>1) Using Multithreading <br/>2) Using Single thread.<br/>
  
  This application creates a file for each first unique letter of word that was found in the texts and exports the results for each word and the number of occurrences. It also creates “results.txt” with excluded words and how many of them were excluded. Additionally, if numbers are not ignored result files for them will be created too.
Words to exclude has to be in file named "exclude.txt" and inside the root directory.
### Usage:
- Navigate to src directory;

- Compile:  
``javac ./Extra/MultiThreadRunner.java``  
``javac ./Solution/Runnable.java``
	
- Run the application:  
 ``java Solution/Runnable <rootFolder> <F1> <F2>``

rootFolder – This parameter can be name or full/relative path of the directory. 

F1 – first flag must be “-MT” to run multithreaded version of application. Any other value will result in single thread version.
    
F2 – second flag must be “-T” to keep numbers in files. Otherwise they will be  ignored.   
	
Examples:  

        # Data files are in folder "./folderName"; Multithreaded version; Numbers will be kept;  
	    java Solution/Runnable “folderName” -MT -T
		
	    # Data files are in folder "~/folderName"; Multithreaded version; Numbers will not be kept;
	    java Solution/Runnable "~/rootFolder” -MT -F
	    
	    # Data files are in folder "./rootFolder"; Single thread version; Numbers will be kept;
	    java Solution/Runnable “rootFolder” -ST -T
		
	    # Data files are in folder "./folderName"; Single thread version; Numbers will not be kept;
	    java Solution/Runnable “rootFolder” -ST -F

   As mentioned before any string can be used instead of “ST” and “F” values. Rule is simple: You always must provide exactly 3 arguments for the application to run.

### Tested on:
OS and JAVA version:  
Windows 10 – 12.0.2;  
macOS 10.14.6 – 12.0.2;

### Should work on:
Any Windows, Linux or MAC OS with JAVA versions 11 and newer.

### Authors:
Edgar Arakelian
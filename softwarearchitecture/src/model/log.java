package model;
// importing libraries
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class log {
    private static log instance; // Singleton instance
    private BufferedWriter logWriter;

    // Private constructor to initialise object for log
    private log() {
        try {
            logWriter = new BufferedWriter(new FileWriter("src/data/ProcessedData.txt", true));
        } catch (IOException e) {
            System.err.println("Error initializing log: " + e.getMessage());
        }
    }

    // Method for singleton design pattern creating only 1 instance of this class
    public static log getInstance() {
        if (instance == null) {
            instance = new log();
        }
        return instance;
    }

    // Method to write to the log message
    public void writeLog(String message) {
        try {
            logWriter.write(message + "\n");
            logWriter.flush();
        } catch (IOException e) {
            System.err.println("Error writing to log: " + e.getMessage());
        }
    }

    // Close the log file
    public void closeLog() {
        try {
            if (logWriter != null) {
                logWriter.close();
            }
        } catch (IOException e) {
            System.err.println("Error closing log: " + e.getMessage());
        }
    }
}


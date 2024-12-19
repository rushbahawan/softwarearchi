package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class log {
    private static log instance; // Singleton instance
    private BufferedWriter logWriter;

    // Private constructor
    private log() {
        try {
            logWriter = new BufferedWriter(new FileWriter("src/data/ProcessedData.txt", true));
        } catch (IOException e) {
            System.err.println("Error initializing log: " + e.getMessage());
        }
    }

    // Method to get the single instance of the class
    public static log getInstance() {
        if (instance == null) {
            instance = new log();
        }
        return instance;
    }

    // Method to write to the log file
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

//package model;
//
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//
//public class log {
//    private static log instance = null;
//    private StringBuilder log = new StringBuilder();
//
//    private log() {}
//
//    public static log getInstance() {
//        if (instance == null) {
//            instance = new log();
//        }
//        return instance;
//    }
//
//    public void addEvent(String event) {
//        log.append(event).append("\n");
//    }
//
//    public void writeLogToFile(String fileName) {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
//            writer.write(log.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}


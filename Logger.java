package jansaida.project;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    public static void main(String[] args) {
        // Example usage of the Logger
        String s = "abc";
        System.out.println(s.getClass()); // Print the class of the string object
        Logger.getInstance().log(s, 2, "abc"); // Log a message with priority
    }

    // Priority levels for log messages
    private static final int CONFIGURED = 2; // Only log messages with priority >= 2 (Medium and High)
    private static final int HIGH = 3;       // High-priority level
    private static final int MEDIUM = 2;     // Medium-priority level
    private static final int LOW = 1;        // Low-priority level

    private static Logger instance; // Singleton instance of the Logger
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Private constructor to prevent instantiation from other classes
    private Logger() {
    }

    /**
     * Get the singleton instance of the Logger class.
     * @return the Logger instance
     */
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    /**
     * Log a message to a file asynchronously.
     * This version logs without any priority filter.
     * 
     * @param message The message to log
     */
    public void log(String message) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Format the current timestamp
                String timestamp = LocalDateTime.now().format(dateFormatter);
                String logMessage = timestamp + " - " + message;

                // Write the log message to the file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\Logger\\taskmanager.log", true))) {
                    writer.write(logMessage);
                    writer.newLine();
                } catch (IOException e) {
                    // Print error message if logging fails
                    System.err.println("Failed to write log: " + e.getMessage());
                }
            }
        }).start(); // Start the logging thread
    }

    /**
     * Log a message with priority and class information.
     * Messages with priority < CONFIGURED will not be logged.
     * 
     * @param message  The message to log
     * @param priority The priority level of the message
     * @param cls      The class from where the log is being generated
     */
    public void log(String message, int priority, Object cls) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Only log messages with priority >= CONFIGURED
                if (priority >= CONFIGURED) {
                    // Format the current timestamp
                    String timestamp = LocalDateTime.now().format(dateFormatter);
                    String logMessage = timestamp + " - " + message;

                    // Write the log message with extra details to the file
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\Logger\\task.log", true))) {
                        writer.write(logMessage + " - Priority: " + priority + " - Thread: " 
                            + Thread.currentThread().getName() + " - Class: " + cls.getClass() 
                            + " - Method: " + getMethodName());
                        writer.newLine();
                    } catch (IOException e) {
                        // Print error message if logging fails
                        System.err.println("Failed to write log: " + e.getMessage());
                    }
                }
            }
        }).start(); // Start the logging thread
    }

    /**
     * Get the name of the method that called this logger.
     * @return the name of the method
     */
    private static String getMethodName() {
        // Use the stack trace to retrieve the caller method's name
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }
}

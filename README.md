# Java-Singleton-Logger

# Java Logging Utility with Multithreading and Priority Levels

This is a Java-based logging utility designed to provide asynchronous logging with configurable priority levels. 
It uses the Singleton Design Pattern to ensure only one instance of the logger, making it efficient for multi-threaded applications. 
The utility supports logging with timestamps, priority levels (High, Medium, Low), and thread-safe log writing to a file.

# Key Features:
1. Singleton Design Pattern: Ensures a single instance of the logger throughout the application.
2. Multithreaded Logging: Logs messages asynchronously using Java's Thread and Runnable for improved performance.
3. Priority-based Logging: Supports configurable logging levels (High, Medium, Low) to filter messages.
4. Timestamped Logs: Every log entry is timestamped for accurate tracking.
5. File-based Logging: Logs are written to a file (taskmanager.log) with each entry on a new line.

# How to Use:
 1. Clone the repository.
 2. Import the Logger class into your Java project.
 3. Call Logger.getInstance().log(message) to log messages
 4. Optionally, use priority-based logging by specifying a priority level: Logger.getInstance().log(message, priority, this).

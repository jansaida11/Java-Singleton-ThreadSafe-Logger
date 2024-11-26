package jansaida.project;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
	
	
	public static void main(String[] args) {
		String s="abc";
		System.out.println(s.getClass());
		Logger.getInstance().log(s, 2,"abc");
	}
	
	
	private static final int CONFIGURED = 2;// so the logging will be done to only with priority 2 or above i.e.,3 only
	private static final int HIGH = 3;
	private static final int MEDIUM = 2;
	private static final int LOW = 1;
	
    private static Logger instance;
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private Logger() {
    	//private constructor.
    }
    
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
       new Thread(new Runnable() {
    	   @Override
    	public void run() {
    		   String timestamp = LocalDateTime.now().format(dateFormatter);
               String logMessage = timestamp + " - " + message;

               try (BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\Logger\\taskmanager.log", true))) {
                   writer.write(logMessage);
                   writer.newLine();
                  // System.out.println(logMessage); // Also print to console for convenience
               } catch (IOException e) {
                   System.err.println("Failed to write log: " + e.getMessage());
               }
    		
    	}
       }).start();
    }
    public void log(String message,int priority,Object cls) {
    	//Anonymous inner class.
    	new Thread(new Runnable() {
			
			@Override
			public void run() {
				//Only logging those which are only Medium and High priority.
		    	if(priority>=CONFIGURED) {
		            String timestamp = LocalDateTime.now().format(dateFormatter);
		            String logMessage = timestamp + " - " + message;

		            try (BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\Logger\\task.log", true))) {
		                writer.write(logMessage+" - "+priority+" - "+Thread.currentThread().getName()+" - "+cls.getClass()+" - "+getMethodName());
		                writer.newLine();
		               // System.out.println(logMessage); // Also print to console for convenience
		            } catch (IOException e) {
		                System.err.println("Failed to write log: " + e.getMessage());
		            
		            }
		    	}
				
			}
		}).start();
   }
    private static String getMethodName() {
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }
}


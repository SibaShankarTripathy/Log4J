package com.st.basiclog4j;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

/*Make sure set build path to log4j jar*/
public class BasicLog4J {
	//Step-1 Declare Logger object.
	private static Logger logger = Logger.getLogger(BasicLog4J.class);
	
	static {
		//Step-2 Create Layout object
		SimpleLayout layout = new SimpleLayout();
		//Step-3 Create Appender Object with layout object
		ConsoleAppender appender = new ConsoleAppender(layout);
		//Step-4 Add appender in logger object
		logger.addAppender(appender);
		//Step-5 Set logger level to display log message
		logger.setLevel(Level.DEBUG);//If we will not write this line then it will take DEBUG by default.
		//* Applying logger here
		logger.info("All logger setup ready...");
	}
	public static void main(String[] args) {
		System.out.println("====Order of log message====");
		logger.debug("debug is default as well as 1st log level. Uses: Using where we need to debug");
		logger.info("info is 2nd log level. Uses: Using where need to give information e.g DB connection");
		logger.warn("warn is 3rd log level. Uses: Using unexpected area entered by controller in program");
		logger.error("error is 4th log level. Uses: Using in Catch block");
		logger.fatal("fatal is 5th or final log level. Uses: Using in unknown error condition");
	}
}

package de.parsing;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class LoggerExample {
	final static Logger logger = Logger.getLogger(LoggerExample.class);

	public static void main(String[] args) {
		System.out.println("he");
		LoggerExample obj = new LoggerExample();
		obj.runMe("Rene");
		
	}

	private void runMe(String parameter) {
	//	Logger.getRootLogger().setLevel(Level.DEBUG);
		if(logger.isDebugEnabled()){
			logger.debug("This is debug : " + parameter);
		}
		
		if(logger.isInfoEnabled()){
			logger.info("This is info : " + parameter);
		}
		
		logger.warn("This is warn : " + parameter);
		logger.error("This is error : " + parameter);
		logger.fatal("This is fatal : " + parameter);
	}
}

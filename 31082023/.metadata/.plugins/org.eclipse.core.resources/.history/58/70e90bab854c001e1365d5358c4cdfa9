package com.anil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingLambda {
	private static final Logger log=LoggerFactory.getLogger(LoggingLambda.class);
	
	
	public void handler(Object[] input) {
		String message= String.format("Hello, %s!", input);
		System.out.println("SOUT : "+message);
		log.info("SLF4J : "+message);
//		return message;
	}
}

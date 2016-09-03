package com.bootcamp.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyLogger {
	
	Logger log = LogManager.getLogger();	
	
	//@Before("execution(* com.bootcamp.app.persistence.daos.interfaces.IGenericDAO.*(..))")
	@Before("execution(* com.bootcamp.app.persistence.daos.interfaces.IGenericDAO.save(..))")
	public void logBeginTransaction() {
		log.info("Begin transaction");
	}
	
	public void logTransactionMade() {
		log.info("Transaction made successfully");		
	}
	
	public void logTransactionError() {
		log.error("An error occurred with the transaction");
	}
}

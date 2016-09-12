package com.bootcamp.app.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyLogger {

	Logger log = LogManager.getLogger();

	@Before("execution(* com.bootcamp.app.controllers.*.*(..))")
	public void logRequest() {
		log.info("Request received");
	}

	@After("execution(* com.bootcamp.app.controllers.*.*(..))")
	public void logResponse() {
		log.info("Response sent");
	}
}

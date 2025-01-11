package com.psps.gcPOC;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GcPocApplication {

	private static final Logger logger = LoggerFactory.getLogger(GcPocApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(GcPocApplication.class, args);
		logger.info(":::  APP STARTED :::");
	}

}

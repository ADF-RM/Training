package com.naveen.project.adf_bank;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.naveen.project.adf_bank.controllers.BankAccountController;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.logging.java.SimpleFormatter;

@SpringBootApplication
@SuppressWarnings("PMD")
public class AdfBankApplication {
	public static Logger logger = Logger.getLogger(BankAccountController.class.getName());
	public static void main(String[] args) {
		SpringApplication.run(AdfBankApplication.class, args);
		System.out.println("\nWelcome...\n");
		
		FileHandler fHandler;

		try {
			fHandler = new FileHandler("C:/Users/HP/ADF/Training/07-2021/9/adf_bank/src/main/java/com/naveen/project/Logs.txt");
			SimpleFormatter sFormatter = new SimpleFormatter();
			fHandler.setFormatter(sFormatter);
			logger.addHandler(fHandler);
			logger.info("Sucess Initializing Log...");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.toString());
			logger.log(Level.WARNING, e.toString());
		}
	}
}

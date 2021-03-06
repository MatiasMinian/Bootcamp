package com.bootcamp.app;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Mailer {
	
	/* *** CONSTRUCTORS *** */
	
	public Mailer(){}
	
	/* *** METHODS *** */
	
	public boolean sendEmail(String title, String body, String email) {
		String filename = email + ".txt";
		try {
			FileWriter fw = new FileWriter(filename, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(title);
			bw.newLine();
			bw.write(body);
			bw.newLine();
			bw.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}
}

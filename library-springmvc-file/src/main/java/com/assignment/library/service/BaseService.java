package com.assignment.library.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class BaseService {

	protected static boolean isFileEmpty(String fileName) {
		BufferedReader br;
		boolean isEmpty = false;
		try {
			br = new BufferedReader(new FileReader(fileName));
			if (br.readLine() == null) {
				isEmpty = true;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}     
		
		return isEmpty;		
	}

	protected static int getId() {
		Random rand = new Random();
		return rand.nextInt(50) + 1;		
	}

	public BaseService() {
		super();
	}

}
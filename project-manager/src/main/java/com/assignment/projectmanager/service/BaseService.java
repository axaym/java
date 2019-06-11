package com.assignment.projectmanager.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import org.springframework.util.ResourceUtils;

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

	protected File getFile(String fileName) {
		File file = null;
		try {
			file = ResourceUtils.getFile("classpath:" + fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return file;
	}

}
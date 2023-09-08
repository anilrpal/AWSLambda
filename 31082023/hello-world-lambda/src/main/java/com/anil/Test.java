package com.anil;

import java.io.IOException;
import java.util.concurrent.Callable;

public class Test implements Callable<String> {

	public String call() throws Exception {

		return null;
	}

	public static void main(String[] args) {

		try {

			throw new IOException();
		} catch (IOException exception) {

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

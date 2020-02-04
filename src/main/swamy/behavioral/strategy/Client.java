package main.swamy.behavioral.strategy;

import java.util.ArrayList;
/*
 * Defines a set of encapsulated algorithms that can be swapped to carry out a specific behaviour
 * 
 */

public class Client {

	public static void main(String[] args) {
		
		CompressionContext context  = new CompressionContext();
		context.setCompressionStrategy(new ZipCompressionStrategy());
		
		ArrayList filesList = new ArrayList();
		context.createArchive(filesList);
	}

}

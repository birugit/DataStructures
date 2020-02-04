package main.swamy.behavioral.strategy;

import java.io.File;
import java.util.ArrayList;
/*
 * Concrete Strategy
 */

public class ZipCompressionStrategy implements CompressionStrategy<File> {

	@Override
	public void compressFiles(ArrayList<File> files) {
		
		System.out.println("Using ZIP approach to compress files");
	}

}

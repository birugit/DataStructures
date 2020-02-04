package main.swamy.behavioral.strategy;

import java.io.File;
import java.util.ArrayList;
/**
 * Concrete Strategy
 * @author swamy
 *
 */

public class RarCompressionStrategy implements CompressionStrategy<File> {

	@Override
	public void compressFiles(ArrayList<File> files) {
		System.out.println("Using RAR approach to compress Files");
		
	}

}

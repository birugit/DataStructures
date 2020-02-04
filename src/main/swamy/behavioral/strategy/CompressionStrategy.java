package main.swamy.behavioral.strategy;

import java.util.ArrayList;
/**
 * Strategy Intefaces
 * @author swamy
 *
 * @param <File>
 */
public interface CompressionStrategy<File> {
	
		public void compressFiles(ArrayList<File> files);
}

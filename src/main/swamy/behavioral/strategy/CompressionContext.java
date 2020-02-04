package main.swamy.behavioral.strategy;

import java.util.ArrayList;

/*
 * Context provide the way the strategy to be executed
 * 
 */
public class CompressionContext<File> {
	CompressionStrategy strategy;
	
	public void setCompressionStrategy(CompressionStrategy strategy) {
		this.strategy = strategy;
	}
	
	public void createArchive(ArrayList<File> files) {
		strategy.compressFiles(files);
	}
	
}

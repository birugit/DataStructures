package main.swamy.bag.diagraph;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeMap;

public class SymbolDiagraph {
	
	TreeMap<String, Integer> st;
	private Diagraph dg;
	private String[] keys;
	
	public SymbolDiagraph(String fileName, String delimeter) throws IOException {
		st =  new TreeMap<String, Integer>();
		URL url = new URL(fileName);
	//	Scanner sc = new Scanner( url.openStream(), "UTF-8" );
	//	String sc = new Scanner( url.openStream(), "UTF-8" ).useDelimiter(" ").next();
	//	System.out.println(sc);
	/*	for(int i = 0; i < sc.length(); i++) {
			if(!st.contains(sc[i])) {
				st.put(sc[i], st.size());
			}
		}*/
//		String[] a = new String[50];
	//	int i= 0;
	/*	while( sc.hasNext() ) {
			a[i] = sc.useDelimiter(" ").next();
			i++;
		}
		sc.close();*/
		
		Scanner sc = new Scanner( url.openStream(), "UTF-8" );	
		//Scanner sc = new Scanner( new FileInputStream(fileName), "UTF-8");
	//	i = 0;
		while( sc.hasNextLine() )
		{
			String[] tokens = sc.nextLine().split(delimeter);
			if(tokens.length > 1)
			{
				for (int i = 0; i < tokens.length; i++) {
				//	a[i] = tokens[i];
					if(!st.containsKey(tokens[i])) {
						st.put(tokens[i], st.size());
					}
				}
				
			}
		
			  
			}
		System.out.println("***Tree Map***");
		System.out.println(st);
		
		System.out.println("**Values**");
		System.out.println(st.values());
		System.out.println("**Keys**");
		System.out.println(st.keySet());
		sc.close();
		
		//inverted index to get string keys in an array
		keys = new String[st.size()];
		for(String name: st.keySet()) {
			keys[st.get(name)] = name;
		}
		
		Scanner sc1 = new Scanner( url.openStream(), "UTF-8" );	
		//Scanner sc1 = new Scanner( new FileInputStream(fileName), "UTF-8" );
		dg = new Diagraph(st.size());
		
		while( sc1.hasNextLine() )
		{
			String[] tokens = sc1.nextLine().split(delimeter);
			if(tokens.length > 1)
			{
				int v = st.get(tokens[0]);
				for (int i = 1; i < tokens.length; i++) {
					int w = st.get(tokens[i]);
					dg.addEdge(v, w);
					
				}
				
			}
		
			  
			}
		sc1.close();
		
		}
	
	public String name(int v) {
		return keys[v];
	}
	
	public int index(String s) {
		return st.get(s);
	}
		
	

	public static void main(String[] args) throws IOException {
	//	String fileName = "https://algs4.cs.princeton.edu/42digraph/routes.txt";
		String fileName= "https://algs4.cs.princeton.edu/42digraph/jobs.txt";
	//	String fileName = "/Users/swamy/Downloads/job.txt";
		String delimeter = "/";
		SymbolDiagraph sd = new SymbolDiagraph(fileName, delimeter);
		Diagraph graph = sd.diagraph();
		System.out.println("**Graph**");
		System.out.println(graph);
	/*	String t;
		while(sd.st != null) {
		for(int v: sd.diagraph()) {
			System.out.println(" "+sd.name(v));
		}
		}
		*/
	}



	public Diagraph diagraph() {
		
		return dg;
	}

}

package main.swamy.test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Scanner;

public class Spellchecker {


		public static void main(String[] args) throws IOException
		{
			URL url = new URL("http://andrew.cmu.edu/course/15-121/dictionary.txt");
			Scanner sc = new Scanner( url.openStream() );

			HashSet<String> dict =  new HashSet<String>();

			while( sc.hasNext() ) dict.add(sc.next());
			
			
			sc.close();
			sc = new Scanner(System.in);
			System.out.println("Enter word to search in Dictionary:");
			String word = sc.nextLine();
			//while(word!= null) {
				if(dict.contains(word))
					System.out.println("word found in dictionary:"+word);
		//	}
			sc.close();
//scum
			String st = "scum";
			sc = new Scanner( new File("/Users/swamy/eclipse-workspace/TestDS/DS/src/main/swamy/test/Spellchecker.java") );
			while( sc.hasNextLine() )
			{
				String[] tokens = sc.nextLine().split("\\W");
				for(String token : tokens)
				   if (token.length() > 1 && dict.contains(token.toLowerCase()))
				      System.out.println(token);
			}
			sc.close();
		}

	

}

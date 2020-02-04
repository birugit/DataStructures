package main.google.test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Mohr {

	public static void main(String[] args) {
		System.out.println("Yash");
		System.out.println("Joe");
		System.out.println("Jonathan");
		System.out.println("2+2="+(23232325-23232332));
		System.out.println("Yash"+(1-1000));
		
	
		    URL myURL;
			try {
				myURL = new URL("https://www.youtube.com/watch?v=aGjL7YXI31Q&list=PLEbnTDJUr_IeHYw_sfBOJ6gk5pie0yP-0");
		
		    URLConnection myURLConnection = myURL.openConnection();
		    myURLConnection.connect();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		System.out.println("****Maths Tables******");
		for(int i=1;i<=10;i++) {
			for(int j=0;j<=10;j++) {
				System.out.println(i+"+"+j+"="+(i+j));
		//drone in the world
				/*Drone has  4  wings
				 * Drone asapple
				 */
				 
			}
			System.out.println();
		}
	}

}

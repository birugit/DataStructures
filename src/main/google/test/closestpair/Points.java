package main.google.test.closestpair;

import java.util.Comparator;

public class Points {
	
	public static Comparator<Points> sortX = new compareX();
	public static Comparator<Points> sortY = new compareY();
	
	public static  class compareY implements Comparator<Points> {

		//comparator for comparing y-coordinates
		@Override
		public int compare(Points o1, Points o2) {
			if(o1.y < o2.y) return -1;
			if(o1.y > o2.y) return +1;
			return 0;
		}

	}
	public static class compareX implements Comparator<Points> {
		//comparator for comparing x-coordinates
		@Override
		public int compare(Points o1, Points o2) {
			if(o1.x < o2.x) return -1;
			if(o1.x > o2.x) return +1;
			return 0;
		}

	}
	
	int x;
	int y;
	public Points(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

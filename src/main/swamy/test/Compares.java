package main.swamy.test;

import java.util.Arrays;
import java.util.Comparator;

public class Compares {

	public static void main(String[] args) {

	String[] cities = {"Bangalore","Pune","San Francisco","New York City"};
    MySort ms = new MySort();
    Arrays.sort(cities, ms);
    System.out.println(Arrays.binarySearch(cities, "New York City"));
}
static class MySort implements Comparator
	{
	
    public int compare( Object a, Object b)
    {
        return ((String) b).compareTo((String) a);
    }

/*	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		return 0;
	}
*/
	}
}

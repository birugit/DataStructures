package main.swamy.structural.adapter.easy;

import java.util.ArrayList;
import java.util.List;

/*
 * Target --> Sorter : Expects int[] array type
 * Adaptee --> NumberSorter : Returns the List, not array after sorting
 * Adapter --> SortListAdapter : Gets the sorted List from Adaptee and Converts it to a array, which is compatible with Target.
 * 
 */

public class SortListAdapter implements Sorter{   
	@Override   
	public int[] sort(int[] numbers)   {  
		//convert the array to a List     
		List<Integer> numberList = new ArrayList<Integer>(); 
		//call the adapter       
		NumberSorter sorter = new NumberSorter(); 
		numberList = sorter.sort(numberList);   
		
		//convert the list back to an array and return 
		int[] array = new int[numberList.size()];
		//array =(int[]) numberList.toArray();
		for(int i=0; i<numberList.size();i++) {
			array[i] = numberList.get(i);
		}
		return  array;   
		}  
}


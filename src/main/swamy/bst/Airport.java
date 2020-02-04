package main.swamy.bst;

import java.util.HashMap;
import java.util.Map;



/*

Write a method that can take in an unordered list of airport pairs visited during a trip, and return the list in order:
Unordered: ("ITO", "KOA"), ("ANC", "SEA"), ("LGA", "CDG"), ("KOA", "LGA"), ("PDX", "ITO"), ("SEA", "PDX")
Ordered: ("ANC", "SEA"), ("SEA", "PDX"), ("PDX", "ITO"), ("ITO", "KOA"), ("KOA", "LGA"), ("LGA", "CDG")

 */
/**
 * 
 * @author swamy
 *You can also using graph with topological sort 
 *hashmap and topological sort give result n O(n)
 */
public class Airport {

	   // Driver function 
    public static void main(String[] args) 
    { 
        Map<String, String> dataSet = new HashMap<String, String>(); 
       /* dataSet.put("ITO", "KOA");
        dataSet.put("ANC", "SEA"); 
        dataSet.put("LGA", "CDG");
        dataSet.put("KOA", "LGA");
        dataSet.put("PDX", "ITO");
        dataSet.put("SEA", "PDX");*/
        dataSet.put("SFO", "NYK"); 
        dataSet.put("ZTL", "LAX"); 
        dataSet.put("DFW", "SFO"); 
        dataSet.put("LAX", "DFW"); 
  
        printResult(dataSet); 
    } 
  
    // This function populates 'result' for given input 'dataset' 
    private static void printResult(Map<String, String> dataSet) 
    { 
        // To store reverse of given map 
        Map<String, String> reverseMap = new HashMap<String, String>(); 
  
        // To fill reverse map, iterate through the given map 
        for (Map.Entry<String,String> entry: dataSet.entrySet()) 
            reverseMap.put(entry.getValue(), entry.getKey()); 
  
        // Find the starting point of itinerary 
        String start = null; 
        for (Map.Entry<String,String> entry: dataSet.entrySet()) 
        { 
              if (!reverseMap.containsKey(entry.getKey())) 
              { 
                   start = entry.getKey(); 
                   break; 
              } 
        } 
  
        // If we could not find a starting point, then something wrong 
        // with input 
        if (start == null) 
        { 
           System.out.println("Invalid Input"); 
           return; 
        } 
  
        // Once we have starting point, we simple need to go next, next 
        // of next using given hash map 
        String to = dataSet.get(start); 
        while (to != null) 
        { 
            System.out.print(start +  "->" + to + ", "); 
            start = to; 
            to = dataSet.get(to); 
        } 

}
}

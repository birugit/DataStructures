package main.google.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Given an array of positive and negative integers {-1,6,9,-4,-10,-9,8,8,4}
 *  (repetition allowed) sort the array in a way such that the starting from a positive number,
 *  the elements should be arranged as one positive and one negative element maintaining insertion order. 
 * First element should be starting from positive integer and the resultant array should look like {6,-1,9,-4,8,-10,8,-9,4}
 * 
 * Given a binary array {0,1,1,0,0,1,0,0,1} , sort the array in a way that all zeros come to the left and all the one's come to the right side of the array.
 * 
 * @author swamy
 *
 */
public class AlternativeNumbers {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>(  );
        list.add( -1 );list.add( 6 );
        list.add( 9 );list.add( -4 );
        list.add( -10 );list.add( -9 );
        list.add( 8 );list.add( 8 );list.add( 4 );
//[-1, 6, 9, -4, -10, -9, 8, 8, 4]
        List<Integer> tempList = new ArrayList<>( list );
        List<Integer> result = new ArrayList<>( );
        Collections.sort( tempList , (o1, o2) -> (o1 > 0)? -1:1 );
       //tempList = [4, 8, 8, 9, 6, -1, -4, -10, -9] 
        int firstNegativeElementIndex = 0;

        for(int i=0; i < tempList.size(); i++){
            if(tempList.get( i ) < 0){
                firstNegativeElementIndex = i;
                break;
            }
        }

        for(int i=0;firstNegativeElementIndex -i>0;i++){
                result.add( tempList.get( firstNegativeElementIndex-1 -i ) );
                if(firstNegativeElementIndex +i < tempList.size())
                		result.add( tempList.get( firstNegativeElementIndex +i ) );
        }
        System.out.println(result);

	}

}

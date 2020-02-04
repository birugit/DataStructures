package main.google.test;
/**
 * Divide without / operator
 * Multiply wihtoyt * operator
 * 
 *  Example:
 *  Shift operator division
 *   10>>1 = 5
 *   -10>>1 = -5
 *   multiplication
 *   5<<1 = 10
 *   5<<2 = 20
 * @author swamy
 *
 */
public class DivideMultiply {

	public static void main(String[] args) {
		
		int a= 10, b = 3;
		//int a=43, b= -8;
		System.out.println(divideShift(a, b));
		System.out.println(divide(a, b));
		//divide 10/2
		System.out.println(10>>1);
		//Multiply 5 *2
		System.out.println(5<<1);
	}
private static int divide(int dividend, int divisor) {
		int sign = ((dividend < 0) ^ divisor < 0) ?-1 :1;
		
		dividend = Math.abs(dividend);
		divisor = Math.abs(divisor);
		
		int quotient =0;
		while(dividend > divisor) {
			dividend -= divisor;
			++quotient;
		}
		return sign * quotient;
	}
//time Complexity O(log(a))
	//space O(1)
	private static long divideShift(long dividend, long divisor) {
		//Calculate sign of divisor
		//i.e sign will be negative
		//only if either one of them is negative otherwise it
		// will be positive
		 
		long sign = ((dividend < 0) ^ (divisor < 0)) ? -1: 1;
		
		//remove the sign of operands
		dividend = Math.abs(dividend);
		divisor =Math.abs(divisor);
		
		//Initialize the quotient
		long quotient =0,  temp=0;
		
		//test down from highest bit and accumulate the 
		//tentative value for
		//valid bit
		//1<<31 behaves incorreclty and gives Integer
		//Min value which should not be the case instead
		//1L <<31 works correctly
		
		for(int i = 31; i>=0; --i) {
			if(temp + (divisor<<i) <= dividend) {
				temp += divisor << i;
				quotient |= 1L<<i;
			}
		}
		return (sign * quotient);
	}

}

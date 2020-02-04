package main.swamy.probability.addition;
/*
 * Write methods to implement the multiply, subtract, and divide operations for integers. 
 * Use only the add operator.
 */
public class Add {

	public static void main(String[] args) {
		Add add = new Add();
		
		//flips positive to negative and negative to positive
		int n = negate(5);
		System.out.println("Negative:"+ n);
		
		//substract two numbers by negating b and adding them
		int sub = minus(3, 2);
		System.out.println("Substraction:"+sub);
		
		//multiply
		int m = multiply(2, 3);
		System.out.println("Multiply:"+ m);
		
		//division
		//x = a / b
		//a = xb
		int d = divide(2, 4);
		System.out.println("Divide:"+ d);
	}

	private static int divide(int a, int b) throws ArithmeticException {
		if(b == 0) {
			throw new java.lang.ArithmeticException();
		}
		int absa = abs(a);
		int absb = abs(b);
		
		int product = 0;
		int x = 0;
		
		while(product + absb <= absa) {
			product += absb;
			x++;
		}
		
		if((a < 0 && b < 0) || (a > 0 && b > 0)) {
			return x;
		}else {
			return negate(x);
		}
		
	}

	private static int multiply(int a, int b) {
		if(a < b) {
			return multiply(b, a);
		}
		int sum = 0;
		for(int i = abs(b); i > 0; i--) {
			sum += a;
		}
		if(b < 0) {
		sum = negate(sum);
		}
		return sum;
		
	}

	private static int abs(int a) {
		if(a < 0) {
			return negate(0);
		}else
		return 0;
	}

	private static int minus(int a, int b) {
		
		return a + negate(b);
	}

	private static int negate(int a) {
		
		int neg = 0;
		int d = a < 0 ? 1 : -1;
		while(a != 0) {
			neg += d;
			a += d;
		}
		return neg;
	}

}

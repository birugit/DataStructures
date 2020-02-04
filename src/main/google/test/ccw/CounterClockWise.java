package main.google.test.ccw;

/**
 * Find Orientation?
 * three points a,b and c, is a-b-c a counterclockwise turn?
 * Idea: compare slopes
 * c       b           c                  c               c              b
 *  \      |\           \                 |               |              |
 *   b      | c           b               b               a              c
 *  /        |            |               |               |              |
 * a         a            a               a               b              a
 * yes     No            Yes             ???             ???            ???
 *                      (slope)       (Collinear)    (Collinear)     (Collinear)
 *                      
 *                    |a     a    1|            
 *                    |  x    y    |
 *  2 X Area(a,b,c) = |b     b    1|  = (b  - a) (c  - a )  - (b  - a )(c   - a  )
 *                    | x     y    |      x    x   y    y        y   y   x    x
 *                    |c     c    1|
 *                    | x     y    |         
 *                    
 *   >if area > 0 then a-b-c is counterclockwise
 *   >If area < 0 then a-b-c is clockwise
 *   >if area  = 0 then a-b-c are collinear
 *   
 *                               
 *        (b , b )                       
 *          x   y
 *          /\
 *         /  \
 *        /<-- \   
 *       / >0   \
 *  (c ,  c)   (a  , a )
 *    x    y     x    y
 *    Counter Clockwise
 *    
 *        (b , b )                       
 *          x   y
 *          /\
 *         /  \
 *        /--> \   
 *       / <0   \
 *  (a ,  a)   (c  , c )
 *    x    y     x    y
 *    Clockwise
 *    
 * @author swamy
 *
 *Ref: 
 *https://www.cs.princeton.edu/~rs/AlgsDS07/10Hashing.pdf
 *http://www.dcs.gla.ac.uk/~pat/52233/slides/Geometry1x1.pdf
 */
public class CounterClockWise {

	public final int x;
	public final int y;
	
	public CounterClockWise(int x, int y) {
		this.x = x;
		this.y = y;
	}
	//returns sqrt of (x2+y2)
	public double distanceTo(CounterClockWise q) {
		return Math.hypot(this.x - q.x, this.y - q.y);
	}
	
	public static int ccw(CounterClockWise p1, CounterClockWise p2, CounterClockWise p3) {
		double area2 = (p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y)*(p3.x - p1.x);
		//if (area2 < 0) return -1;
		//else if(area2 > 0) return +1;
		//else if(area2 ==0)  return 0;
		if (area2 == 0 ) return 0;
		return (area2 >0)?1:-1;
		
	}
	
	public static void main(String[] args) {
		CounterClockWise p1 = new CounterClockWise(0,0);
		CounterClockWise p2 = new CounterClockWise(4,4);
		CounterClockWise p3 = new CounterClockWise(1,2);
		
		int o= CounterClockWise.ccw(p1, p2, p3);
		if(o == 0)
			System.out.println("Colinear");
		else if (o == 1)
			System.out.println("Counter Clockwise");
		else
			System.out.println(" Clockwise");

	}

}

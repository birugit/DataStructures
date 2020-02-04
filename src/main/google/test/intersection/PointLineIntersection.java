package main.google.test.intersection;
/**
 * Given two line segments, do they intersect?
 * Idea1: find intersection point using algebra and check.
 * Idea2: check if the endpoints of one line segment are on different "sides" of the other line segment.
 * 4 CCW computations.
 * 
 * p1                     q2
 *  .---------------------.
 *  |\                   / |
 *  |  \                /  |
 *  |    \             /   |
 *  |      \          /    |
 *  |        \       /     |
 *  |         \     /      |
 *   |          \  /       |
 *    |           \        |
 *     |        /   \     |
 *      |      /      \   |
 *       |    /         \ |
 *        | /            \|
 *         ._______________.
 *         p2               q1
 *    
 *         Intersection happens
 *         General case
 *  1) (p1, q1, p2) and (p1,q1,q2) have different orientations 
 *  and
 *  2)(p2,q2,p1) and (p2,q2,q1) have different orientations
 *      
 *      Special Cases
 *      
 *   $ (p1,q1,p1),(p1,q1,q2),(p2,q2,p1) and (p2,q2,q1) are all collinear and
 *   $ the x-projections of (p1,q1) and (p2,q2) intersect
 *   $ the y-projections of (p1,q1) and (p2,q2) intersect
 *  
 * @author swamy
 *
 */

public class PointLineIntersection {
	
	static class Point{
		int x;
		int y;
			public Point(int x, int y) {
				this.x = x;
				this.y = y;
				
			}
	};
	
	//Given three collinear points p,q,r the function check if 
	//point q lies on line segment 'pr'
	static boolean onSegment(Point p, Point q, Point r) {
		if(q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) &&
				q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y))
			return true;
		return false;
	}

	//orientation with p,q,r
	//returns 
	//0 for collinear
	//-1 for clockwise
	//1 for counter clockwise
	
	/*
	 *         q
	 *         /\
	 *        /  \
	 *       /____\
	 *      r      p
	 *  
	 * 
	 */
	static int orientation(Point p, Point q, Point r) {
		
		int area = (q.x - p.x) * (r.y - p.y)  -  (q.y-p.y)*(r.x-p.x);
		
		if(area == 0) return 0;
		
		
		return (area > 0)? 1: -1;
	}
	
	//returns true of line segment 'p1q1' and 
	//'p2q2' intersect
	static boolean doIntersect(Point p1, Point q1, Point p2, Point q2) {
		//Find the four orientations needed for general and 
		//special cases
		int o1 = orientation(p1, q1, p2);
		int o2 = orientation(p1, q1, q2);
		int o3 = orientation(p2, q2, p1);
		int o4 = orientation(p2, q2, q1);
		
		//General case
		if(o1 != o2 && o3 != o4)
			return true;
		
		//Special cases
		//p1, q1, and p2 are collinear and p2 lies on the segment p1q1
		if(o2 == 0 && onSegment(p1, p2, q1)) return true;
		
		//p1, q1, and q2 are collinear and q2 lies on segment p1q1
		if(o2 == 0 && onSegment(p1, q2, q1)) return true;
		
		//p2, q2, and p1 are collinear and p1 lies on the segment p2q2
		if(o3 == 0 && onSegment(p2, p1, q2)) return true;
		
		//p2, q2, and q1 are collinear and q1 lies on the segment p2q2
		if(o4 == 0 && onSegment(p1, p2, q1)) return true;
		
		return false;
	}
	public static void main(String[] args) {
		
		Point p1 = new Point(1, 1);
		Point q1 = new Point(10, 1);
		Point p2 = new Point(1, 2);
		Point q2 = new Point(10, 2);
		
		if(doIntersect(p1, q1, p2, q2))
			System.out.println("Yes");
		else
			System.out.println("No");
		
		
		p1 = new Point(10, 1); q1 = new Point(0, 10); 
	    p2 = new Point(0, 0); q2 = new Point(10, 10); 
	    if(doIntersect(p1, q1, p2, q2)) 
	            System.out.println("Yes"); 
	    else
	        System.out.println("No"); 

	    
	    p1 = new Point(-5, -5); q1 = new Point(0, 0); 
	    p2 = new Point(1, 1); q2 = new Point(10, 10);; 
	    if(doIntersect(p1, q1, p2, q2)) 
	        System.out.println("Yes"); 
	    else
	        System.out.println("No"); 
	}

}

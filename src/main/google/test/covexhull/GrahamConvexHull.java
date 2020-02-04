package main.google.test.covexhull;
/**
 * https://iq.opengenus.org/graham-scan-convex-hull/
 * 
 * Let points[0..n-1] be the input array.

Find the bottom-most point by comparing y coordinate of all points. If there are two points with same y value, then the point with smaller x coordinate value is considered. Let the bottom-most point be P0. Put P0 at first position in output hull.

Consider the remaining n-1 points and sort them by polor angle in counterclockwise order around points[0]. If polor angle of two points is same, then put the nearest point first.

3 After sorting, check if two or more points have same angle. If two more points have same angle, then remove all same angle points except the point farthest from P0. Let the size of new array be m.

If m is less than 3, return (Convex Hull not possible)

Create an empty stack ‘S’ and push points[0], points[1] and points[2] to S.

Process remaining m-3 points one by one. Do following for every point ‘points[i]’
4.1) Keep removing points from stack while orientation of following 3 points is not counterclockwise (or they don’t make a left turn).
a) Point next to top in stack
b) Point at the top of stack
c) points[i]
4.2) Push points[i] to S

Print contents of S
 * @author swamy
 *
 */
public class GrahamConvexHull {
	static Point p0 ;
	static class Point{
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	};
	
	static class PolarAnglePoints{
		Point point;
		int polarAngle;
		public PolarAnglePoints(Point points, int polarAngle) {
			this.point = points;
			this.polarAngle = polarAngle;
		}
	}

	public static void main(String[] args) {
	/*	 Point p1 = new Point(0,0);
		 Point p2 = new Point(0,4);
		 Point p3 = new Point(-4,0);
		 Point p4 = new Point(5,0);
		 Point p5 = new Point(0,-6);
		 Point p6 = new Point(1,0);*/
		 
		 Point points[] = {
				 new Point(0,0),
				 new Point(0,4),
				 new Point(-4,0),
				 new Point(5,0),
				 new Point(0,-6),
				 new Point(1,0)
		 };
		 
		 int n = points.length;
		 System.out.println(n);
		 convexHull(points, n);
		 //find the point with smalled y coordinte
		 

		 //find the polar angle// CCW
	}

	private static void convexHull(Point[] points, int n) {
		//Find the bottom most point
		int ymin = points[0].y, min = 0;
		
		//Pick the bottom-most or chose left most point in case of tie
		for(int i=0; i<n; i++) {
			int y = points[i].y;
			if((y < ymin) || 
					(ymin == y && points[i].x < points[min].x))
			{
				ymin = points[i].y;
				min = i;
			}
		}
		
		System.out.println("ymin:"+ymin+"min:"+min);
		p0 = points[min];
		//place the bottom most point at fist position
		swap(points[0], points[min]);
			
		System.out.println("First Point:"+p0.x +","+p0.y);
		
		//sort by polar angle p1 to n-1
		sortByPolarAngle(points, n-1);
		
		
	}

	private static void sortByPolarAngle(Point[] points, int n) {
		int angle ;
		for(int i=1; i< n; i++)
		angle = ccw(p0,points[i],points[i+1]);
		
	}

	private static int ccw(Point p0, Point p1, Point p2) {
		int angle = (p1.x - p0.x)*(p2.y-p0.y) - (p1.y - p0.y)*(p2.x-p0.x);
		System.out.println("Angle:"+angle);
		//collinear
		if(angle == 0) return 0;
		
		
		return 0;
	}

	private static void swap(Point point1, Point point2) {
		Point temp = point1;
		point1 = point2;
		point2 = temp;
		
	}

}

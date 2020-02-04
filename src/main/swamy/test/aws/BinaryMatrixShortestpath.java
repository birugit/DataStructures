package main.swamy.test.aws;

import java.util.LinkedList;
import java.util.Queue;
/**
 * 
 * Given a binary matrix of 0 and 1 where we can move in 4 directions left, right, top, down 
 * and we can only pass through 1's. 
 * Find the shortest path from given source coordinate (a,b) to destination (m,n) given we can flip any one of the zero to one.
 * 
 * @author swamy
 *
 */
public class BinaryMatrixShortestpath {

	static int ROW = 9;
	static int COL = 10;
	
	//to store matrix cell coordinates
	static class Point{
			int x;
			int y;
			
			public Point(int x, int y) {
				this.x = x;
				this.y = y;
			}
	};
	//a Data Structure for queue used in BFS
	static class queueNode{
		Point pt;//The coordinates of a cell
		int dist;//Cell's distance of from the source
		
		public queueNode(Point pt, int dist) {
			this.pt = pt;
			this.dist = dist;
		}
	};
	
	//check whether given cell (row, col)
	//is valid cell or not
	static boolean isValid(int row, int col) {
		//return true if row number and column number is in range
		return (row >= 0) && (row < ROW) &&
				(col >= 0) && (col < COL);
	}
	
	//These arrays are used to get row and column
	//numbers of 4 neighbours of a given cell
	static int rowNum[] = {-1, 0, 0, 1};
	static int colNum[] = {0, -1, 1, 0};
	
	//function to find the shortest path between
	//a given source cell to a destination cell.
	static int BFS(int mat[][], Point src, Point dest) {
		//check source and destination cell
		//of the matrix have value 1
		
		if(mat[src.x][src.y] != 1 ||
				mat[dest.x][dest.y] != 1)
			return -1;
		
		boolean [][]visited = new boolean[ROW][COL];
		
		//mark the source cell as visited
		visited[src.x][src.y] = true;
		
		//Create a queue for BFS
		Queue<queueNode> q = new LinkedList<>();
		
		
		//distance of source cell is 0
		queueNode s = new queueNode(src, 0);
		q.add(s);//Enqueue
		
		//Do BFS
		while(!q.isEmpty()) {
			queueNode curr = q.peek();
			Point pt = curr.pt;
			
			//if we have reached the destination
			//we are done
			if(pt.x == dest.x && pt.y == dest.y)
				return curr.dist;
			
			//otherwise deque the front cell
			//in the queue and enqueue
			//its adjacent cell
			q.remove();
			
			//check all 4 possible moves from current cell
			for(int i=0; i<4; i++) {
				int row = pt.x + rowNum[i];
				int col = pt.y + colNum[i];
				
				//if adjacnet cell is valid, has path
				//and not visited yet, enqueue
				if(isValid(row, col) &&
						mat[row][col] ==1 &&
						!visited[row][col]
						) {
					//mark the cell as visited and enque
					visited[row][col] = true;
					queueNode adjCell = new queueNode(new Point(row, col), curr.dist + 1);
					q.add(adjCell);
				}
			}
		}
		//return -1 if destination not reached
		return -1;
	}
	public static void main(String[] args) {
		int mat[][] = {{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 }, 
                { 1, 0, 1, 0, 1, 1, 1, 0, 1, 1 }, 
                { 1, 1, 1, 0, 1, 1, 0, 1, 0, 1 }, 
                { 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 }, 
                { 1, 1, 1, 0, 1, 1, 1, 0, 1, 0 }, 
                { 1, 0, 1, 1, 1, 1, 0, 1, 0, 0 }, 
                { 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, 
                { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 }, 
                { 1, 1, 0, 0, 0, 0, 1, 0, 0, 1 }}; 

 Point source = new Point(0, 0); 
 Point dest = new Point(3, 4); 

 int dist = BFS(mat, source, dest); 

 if (dist != Integer.MAX_VALUE) 
     System.out.println("Shortest Path is " + dist); 
 else
     System.out.println("Shortest Path doesn't exist"); 
 } 
	

}

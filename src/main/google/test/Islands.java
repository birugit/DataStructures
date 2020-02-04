package main.google.test;

/**
 * Islands issue is finding number of connected components in Graph . A
 * connected component of an undirected graph is a subgraph in which every two
 * vertices are connected to each other by a path(s), and which is connected to
 * no other vertices outside the subgraph. Use DFS or BFS and count
 */
public class Islands {
	// number of rows n columns
	static final int ROW = 5, COL = 5;

	// A fucntion to check given cell to be included in DFS
	boolean isSafe(int M[][], int row, int col, boolean visited[][]) {
		// row number is in range, column number is in range
		// and value is 1 and not yet visited
		return (row >= 0) && (row < ROW) && (col >= 0) && (col < COL) && (M[row][col] == 1 && !visited[row][col]);
	}

	// A utility function to do DFS for a 2D boolean matrix.
	// It only considers the 8 neighbors as adjacent vertices
	void DFS(int M[][], int row, int col, boolean visited[][]) {

		// if you want to check neighbours of i, j, do following iteration,
		// initialized in rowNbr n colNbr
		/*
		 * [i-1,j-1][i-1,j ][i-1,j+1] [i ,j-1][i ,j ][i ,j+1] [i+1,j-1][i+1,j
		 * ][i+1,j+1]
		 */
		int rowNbr[] = new int[] { -1, -1, -1, 0, 0, 1, 1, 1 };
		int colNbr[] = new int[] { -1, 0, 1, -1, 1, -1, 0, 1 };

		// Mark this cell as visited
		visited[row][col] = true;

		// Recur for all connected neighbours
		for (int k = 0; k < 8; ++k)
			if (isSafe(M, row + rowNbr[k], col + colNbr[k], visited))
				DFS(M, row + rowNbr[k], col + colNbr[k], visited);
	}

	// The main function that returns count of islands in a given
	// boolean 2D matrix
	int countIslands(int M[][]) {
		// Make a bool array to mark visited cells.
		// Initially all cells are unvisited
		boolean visited[][] = new boolean[ROW][COL];

		// Initialize count as 0 and traverse through the all cells
		// of given matrix
		int count = 0;
		for (int i = 0; i < ROW; ++i)
			for (int j = 0; j < COL; ++j)
				if (M[i][j] == 1 && !visited[i][j]) // If a cell with
				{ // value 1 is not
					// visited yet, then new island found, Visit all
					// cells in this island and increment island count
					DFS(M, i, j, visited);
					++count;
				}

		return count;
	}

	public static void main(String[] args) {

		int M[][] = new int[][] { { 0, 0, 0, 0, 0 }, 
								  { 0, 0, 0, 0, 0 }, 
								  { 0, 0, 0, 0, 0 }, 
								  { 0, 0, 0, 1, 1 },
								  { 0, 0, 0, 1, 0 } };
		Islands I = new Islands();
		System.out.println("Number of islands is: " + I.countIslands(M));
	}

}

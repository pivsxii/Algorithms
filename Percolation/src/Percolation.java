public class Percolation 
{
	private boolean[][] schemaOpen = null;
	private int dimension = 0;
	private WeightedQuickUnionUF test = null;
	
	// create N-by-N grid, with all sites blocked
	public Percolation(int N)
	{
		dimension = N;
		schemaOpen = new boolean[N][N];
		test = new WeightedQuickUnionUF(N * N);
		
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				schemaOpen[i][j] = false;
	}
	
	// open site (row i, column j) if it is not already
	public void open(int i, int j)
	{
		checkIndex(i,j);
		int x = i - 1;
		int y = j - 1;
		int elem = x * dimension + y;
		
		if (!isOpen(i, j))
		{
			schemaOpen[x][y] = true;
			
			// Check the left cell
			if (y != 0)
				if (isOpen(i, j - 1))
					test.union(elem,elem - 1);
			
			// Check the right cell
			if (y != dimension - 1)
				if (isOpen(i, j + 1))
					test.union(elem,elem + 1);

			// Check the upper cell
			if (x != 0)
				if (isOpen(i - 1, j))
					test.union(elem,elem - dimension);
			
			// Check the lower cell
			if (x != dimension - 1)
				if (isOpen(i + 1, j))
					test.union(elem,elem + dimension);
		}
	}
	
    // is site (row i, column j) open?
	public boolean isOpen(int i, int j)
	{
		return schemaOpen[i-1][j-1];
	}
	
    // is site (row i, column j) full?
	public boolean isFull(int i, int j)
	{
		for (int x = 0; x < dimension; x++)
		{
			int p = i * dimension + j;
			if (this.test.connected(x, p))
				return true;
		}
		
		return false;
	}
	
	private void checkIndex(int i, int j) 
	{
		if (i - 1 < 0 || i > dimension)
			throw new IndexOutOfBoundsException("First dimension is invalid");
		if (j - 1 < 0 || j > dimension)
			throw new IndexOutOfBoundsException("Second dimension is invalid");
	}

	// does the system percolate?
	public boolean percolates()
	{
		for (int i = 0; i < dimension ; i++)
			if (isFull(dimension - 1, i))
				return true;
		
		return false;
	}
}
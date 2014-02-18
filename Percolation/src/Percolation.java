public class Percolation
{
   private boolean[][] schemaOpen = null;
   private int dimension = 0;
   private WeightedQuickUnionUF test = null;
   private WeightedQuickUnionUF testFull = null; // To avoid backwash
   private int virtualTopPosition = 0;
   private int virtualBottomPosition = 0;

   // create N-by-N grid, with all sites blocked
   public Percolation(int N)
   {
      dimension = N;
      schemaOpen = new boolean[N][N];
      test = new WeightedQuickUnionUF(N * N + 2); // 2 for the 2 virtual sites
      testFull = new WeightedQuickUnionUF(N * N + 1); // 1 for only top Virtual site
      virtualTopPosition = test.count() - 2;
      virtualBottomPosition = test.count() - 1;

      for (int i = 0; i < N; i++)
         for (int j = 0; j < N; j++)
            schemaOpen[i][j] = false;
      
//      for (int a = 0; a < dimension; a++)
//      {
//         test.union(virtualTopPosition, a);
//         test.union(virtualBottomPosition, a + (dimension * (dimension - 1)));
//      }
   }

   // open site (row i, column j) if it is not already
   public void open(int i, int j)
   {
      int x = i - 1;
      int y = j - 1;
      int elem = x * dimension + y;

      schemaOpen[x][y] = true;

      // Check the left cell
      if (y != 0)
         if (isOpen(i, j - 1))
         {
            test.union(elem, elem - 1);
            testFull.union(elem, elem - 1);
         }

      // Check the right cell
      if (y != dimension - 1)
         if (isOpen(i, j + 1))
         {
            test.union(elem, elem + 1);
            testFull.union(elem, elem + 1);
         }

      // Check the upper cell
      if (x != 0)
         if (isOpen(i - 1, j))
         {
            test.union(elem, elem - dimension);
            testFull.union(elem, elem - dimension);
         }

      // Check the lower cell
      if (x != dimension - 1)
         if (isOpen(i + 1, j))
         {
            test.union(elem, elem + dimension);
            testFull.union(elem, elem + dimension);
         }
      
      if (x == 0)
      {
         test.union(virtualTopPosition, elem);
         testFull.union(virtualTopPosition, elem);
      }
      if (x == dimension - 1)
         test.union(virtualBottomPosition, elem);
      
      this.isFull(i, j);
   }

   // is site (row i, column j) open?
   public boolean isOpen(int i, int j)
   {
      checkIndex(i, j);

      return schemaOpen[i - 1][j - 1];
   }

   // is site (row i, column j) full?
   public boolean isFull(int i, int j)
   {
      checkIndex(i, j);
      int x = i - 1;
      int y = j - 1;
      
      if (isOpen(i, j))
      {
         int p = x * dimension + y;
         return this.testFull.connected(p, virtualTopPosition);
      }
      else
         return false;
   }

   private void checkIndex(int i, int j)
   {
      if (i < 0 || i > dimension)
         throw new IndexOutOfBoundsException("First dimension is invalid");
      if (j < 0 || j > dimension)
         throw new IndexOutOfBoundsException("Second dimension is invalid");
   }

   // does the system percolate?
   public boolean percolates()
   {
      return this.test.connected(virtualBottomPosition, virtualTopPosition);
   }
}
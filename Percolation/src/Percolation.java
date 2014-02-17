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
      checkIndex(i, j);
      int x = i - 1;
      int y = j - 1;
      int elem = x * dimension + y;

      schemaOpen[x][y] = true;

      // Check the left cell
      if (y != 0)
         if (isOpen(i, j - 1))
            test.union(elem, elem - 1);

      // Check the right cell
      if (y != dimension - 1)
         if (isOpen(i, j + 1))
            test.union(elem, elem + 1);

      // Check the upper cell
      if (x != 0)
         if (isOpen(i - 1, j))
            test.union(elem, elem - dimension);

      // Check the lower cell
      if (x != dimension - 1)
         if (isOpen(i + 1, j))
            test.union(elem, elem + dimension);
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
         //The elements in the first row are always full if thery're open 
         if (x == 0)
            return true;
         
         int p = x * dimension + y;
         int elemValue = test.find(p);
         for (int a = 0; a < dimension; a++)
         {
            if (isOpen(1, a + 1) && this.test.find(a) == elemValue)
               return true;
         }
      }

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
      for (int i = 1; i <= dimension; i++)
         if (isFull(dimension, i))
            return true;

      return false;
   }
}
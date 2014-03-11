/*************************************************************************
* Name: Paolo Re
*
* Description: The Board class
* Algorithms, Part I - Week 4
*
*************************************************************************/

public class Board
{
   private int[][] gameBoard;
   private int dimension;
   
   // construct a board from an N-by-N array of blocks
   // (where blocks[i][j] = block in row i, column j)
   public Board(int[][] blocks)
   {
      dimension = blocks.length;
      gameBoard = copyArray(blocks);
   }
   
   private int[][] copyArray(int[][] source)
   {
      int[][] copy = new int[source.length][source.length];
      
      for (int i = 0; i < dimension; i++)
         for (int j = 0; j < dimension; j++)
            copy[i][j] = source[i][j];
      
      return copy;
   }
   
   // board dimension N
   public int dimension()
   {
      return dimension;
   }
   
   // number of blocks out of place
   public int hamming()
   {
      int hammingValue = 0;
      
      for (int i = 0; i < dimension; i++)
         for (int j = 0; j < dimension; j++)
         {
            if (gameBoard[i][j] == 0) // Empty space
               continue;
            else
               if (gameBoard[i][j] != cellValue(i, j))
                  hammingValue++;
         }
      
      return hammingValue;
   }
   
   private int cellValue(int i, int j)
   {
      return i * dimension + j + 1;
   }
   
   // sum of Manhattan distances between blocks and goal
   public int manhattan()
   {
      int manhattanDistanceSum = 0;
      int value;
      int targetX;
      int targetY;
      int dx;
      int dy;
      
      for (int x = 0; x < dimension; x++)
          for (int y = 0; y < dimension; y++) 
          {
              value = gameBoard[x][y];
              if (value != 0) 
              {
                  targetX = (value - 1) / dimension; // expected x-coordinate (row)
                  targetY = (value - 1) % dimension; // expected y-coordinate (col)
                  dx = x - targetX;
                  dy = y - targetY;
                  manhattanDistanceSum += Math.abs(dx) + Math.abs(dy); 
              } 
          }
      return manhattanDistanceSum;
   }
   
   // is this board the goal board?
   public boolean isGoal()
   {
      return hamming() == 0;
   }
   
   // a board obtained by exchanging two adjacent blocks in the same row
   public Board twin() 
   {
      int[][] twin = copyBoard();
      
      for (int i = 0; i < dimension; i++)
      {
         boolean check = true;
         for (int j = 0; j < dimension; j++)
         {
            if (twin[i][j] == 0)
            {
               check = false;
               break;
            }
            
         }
         
         if (check)
         {
            int temp = twin[i][0];
            twin[i][0] = twin[i][1];
            twin[i][1] = temp;
            break;
         }
      }
      
      return new Board(twin);
   }
 
   // make a copy of the gameboard
   private int[][] copyBoard() 
   {
      int[][] b = new int[dimension][dimension];
      for (int i = 0; i < dimension; i++)
         for (int j = 0; j < dimension; j++)
            b[i][j] = gameBoard[i][j];
      return b;
   }
   
   // does this board equal y?
   public boolean equals(Object y)
   {
      if (y == this)
         return true;
      if (y == null)
         return false;
      if (!(y instanceof Board))
         return false;
      
      Board objectToCheck = (Board) y;
      
      if (objectToCheck.dimension != dimension)
         return false;
      
      for (int i = 0; i < dimension; i++)
         for (int j = 0; j < dimension; j++)
         {
            if (gameBoard[i][j] != objectToCheck.gameBoard[i][j])
               return false;
         }
      
      return true;
   }
   
   
   // all neighboring boards
   public Iterable<Board> neighbors() 
   {
      Queue<Board> queue = new Queue<Board>();
      
      for (int i = 0; i < dimension; i++)
         for (int j = 0; j < dimension; j++)
         {
            if (gameBoard[i][j] == 0) 
            {
               if (i != 0) 
               {
                  // Exchange with upper row
                  int[][] nb = copyBoard();
                  nb[i][j] = nb[i - 1][j];
                  nb[i - 1][j] = 0;
                  queue.enqueue(new Board(nb));
               }
               if (i != dimension - 1) 
               {
                  // Exchange with lower row
                  int[][] nb = copyBoard();
                  nb[i][j] = nb[i + 1][j];
                  nb[i + 1][j] = 0;
                  queue.enqueue(new Board(nb));
               }
               
               if (j != 0) 
               {
                  // Exchange with left column
                  int[][] nb = copyBoard();
                  nb[i][j] = nb[i][j - 1];
                  nb[i][j - 1] = 0;
                  queue.enqueue(new Board(nb));
               }
               
               if (j != dimension - 1) 
               {
                  // Exchange with right column
                  int[][] nb = copyBoard();
                  nb[i][j] = nb[i][j + 1];
                  nb[i][j + 1] = 0;
                  queue.enqueue(new Board(nb));
               }
               
               return queue;
            }
         }
      
      return null;
   }
   
   // string representation of the board (in the output format specified below)
   public String toString() 
   {
      StringBuilder s = new StringBuilder();
      s.append(dimension + "\n");
      for (int i = 0; i < dimension; i++) 
      {
          for (int j = 0; j < dimension; j++) 
          {
              s.append(String.format("%2d ", gameBoard[i][j]));
          }
          s.append("\n");
      }
      return s.toString();
  }
}
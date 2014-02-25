/*************************************************************************
* Name: Paolo Re
*
* Description: The Subset class
* Algorithms, Part I - Week 2
*
*************************************************************************/

public class Subset 
{
   public static void main(String[] args)
   {
      int numberOfInput = Integer.parseInt(args[0]);
      RandomizedQueue<String> test = new RandomizedQueue<String>();

      while (!StdIn.isEmpty())
      {
         test.enqueue(StdIn.readString());
      }
      
      for (int i = 0; i < numberOfInput; i++) 
      {
         StdOut.println(test.dequeue()); 
      }
   }
}

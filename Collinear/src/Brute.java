import java.util.Arrays;

/*************************************************************************
* Name: Paolo Re
*
* Description: The Brute class
* Algorithms, Part I - Week 3
*
*************************************************************************/

public class Brute 
{
   public static void main(String[] args)
   {
      int numberOfPoints = 0;

      if (args != null)
      {
         StdDraw.setXscale(0, 32768);
         StdDraw.setYscale(0, 32768);
         
         In inputFile = new In(args[0]);
         numberOfPoints = inputFile.readInt();
         Point[] array = new Point[numberOfPoints];
         
         for (int i = 0; i < numberOfPoints; i++)
         {
            Point point = new Point(inputFile.readInt(), inputFile.readInt());
            point.draw();
            array[i] = point;
         }
         
         Arrays.sort(array);
         
         for (int i = 0; i < numberOfPoints; i++)
         {
            for (int j = i + 1; j < numberOfPoints; j++)
            {
               for (int k = j + 1; k < numberOfPoints; k++)
               {
                  for (int n = k + 1; n < numberOfPoints; n++)
                  {
                     if (array[i].slopeTo(array[j]) == array[i].slopeTo(array[k]) &&  array[i].slopeTo(array[j]) == array[i].slopeTo(array[n]))
                     {
                        StdOut.println(array[i].toString() + " -> " + array[j].toString() + " -> " + array[k].toString() + " -> "  + array[n].toString());
                        array[i].drawTo(array[n]);
                     }
                  }
               }
            }
         }
      }
   }
}

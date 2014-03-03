import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*************************************************************************
* Name: Paolo Re
*
* Description: The Fast class
* Algorithms, Part I - Week 3
*
*************************************************************************/

public class Fast 
{
   public static void main(String[] args)
   {
      int numberOfPoints = 0;
      final int CUTOFF_ELEMENT = 4;

      if (args != null)
      {
         StdDraw.setXscale(0, 32768);
         StdDraw.setYscale(0, 32768);
         
         In inputFile = new In(args[0]);
         numberOfPoints = inputFile.readInt();
         Point[] array = new Point[numberOfPoints];
         Point[] originalArray = new Point[numberOfPoints];
         
         for (int i = 0; i < numberOfPoints; i++)
         {
            Point point = new Point(inputFile.readInt(), inputFile.readInt());
            point.draw();
            array[i] = point;
         }
         
         originalArray = Arrays.copyOf(array, array.length);
         
         for (int i = 0; i < numberOfPoints; i++)
         {
            Arrays.sort(array, originalArray[i].SLOPE_ORDER);
            List<Point> sameSlopeArray = new ArrayList<Point>();
            boolean newSlopeToCheck = false;
            
            for (int j = 0; j < numberOfPoints; j++)
            {
               if (j == 0)
               {
                  sameSlopeArray.clear();
                  sameSlopeArray.add(array[j]);
                  newSlopeToCheck = true;
                  continue;
               }
               
               if (newSlopeToCheck || array[j].slopeTo(originalArray[i]) == array[j - 1].slopeTo(originalArray[i]))
               {
                  sameSlopeArray.add(array[j]);
                  newSlopeToCheck = false;
                  continue;
               }
               
               if (sameSlopeArray.size() >= CUTOFF_ELEMENT)
               {
                  // I have 4+ Points with the same slope
                  Collections.sort(sameSlopeArray);
                  
                  // Print result only one time for tuple
                  if (sameSlopeArray.get(0).compareTo(originalArray[i]) == 0)
                  {
                     for (int k = 0; k < sameSlopeArray.size(); k ++)
                     {
                        if (k < sameSlopeArray.size() - 1)
                           StdOut.print(sameSlopeArray.get(k).toString() + " -> ");
                        else
                           StdOut.println(sameSlopeArray.get(k).toString());
                     }
                     sameSlopeArray.get(0).drawTo(sameSlopeArray.get(sameSlopeArray.size() - 1));
                  }
                  
                  //Reset variable
                  sameSlopeArray.clear();
                  newSlopeToCheck = true;
               }
            }
         }
      }
   }
}
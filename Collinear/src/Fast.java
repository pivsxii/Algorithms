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
      final int CUTOFF_ELEMENT = 3;
      List<Point> sameSlopeArray = new ArrayList<Point>();

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
         double slopeToCheck;
         
         for (int i = 0; i < numberOfPoints; i++)
         {
            Arrays.sort(array, originalArray[i].SLOPE_ORDER);
            
            sameSlopeArray.clear();
            slopeToCheck = Double.MAX_VALUE;
            
            for (int j = 1; j < numberOfPoints; j++)
            {
               if (slopeToCheck == Double.MAX_VALUE)
               {
                  slopeToCheck = originalArray[i].slopeTo(array[j]);
                  sameSlopeArray.add(array[j]);
                  continue;
               }
                  
               if (originalArray[i].slopeTo(array[j]) == slopeToCheck)
               {
                  sameSlopeArray.add(array[j]);
                  continue;
               }
               
               if (sameSlopeArray.size() >= CUTOFF_ELEMENT)
                  printResult(array[0], sameSlopeArray, originalArray[i]);
               
               //Reset variable
               sameSlopeArray.clear();
               sameSlopeArray.add(array[j]);
               slopeToCheck = originalArray[i].slopeTo(array[j]);
            }
            
            if (sameSlopeArray.size() >= CUTOFF_ELEMENT)
               printResult(array[0], sameSlopeArray, originalArray[i]);
         }
      }
   }

   private static void printResult(Point point, List<Point> sameSlopeArray, Point originaPoint)
   {
      sameSlopeArray.add(point);
      // I have 4+ Points with the same slope
      Collections.sort(sameSlopeArray);
      
      // Print result only one time for tuple
      if (sameSlopeArray.get(0).compareTo(originaPoint) == 0)
      {
         for (int k = 0; k < sameSlopeArray.size(); k++)
         {
            if (k < sameSlopeArray.size() - 1)
               StdOut.print(sameSlopeArray.get(k).toString() + " -> ");
            else
               StdOut.println(sameSlopeArray.get(k).toString());
         }
         sameSlopeArray.get(0).drawTo(sameSlopeArray.get(sameSlopeArray.size() - 1));
      }
   }
}
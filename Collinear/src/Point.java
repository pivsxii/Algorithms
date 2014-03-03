/*************************************************************************
* Name: Paolo Re
*
* Description: The Point class
* Algorithms, Part I - Week 3
*
*************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> 
{
    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new OrderBySlope();
    
    private class OrderBySlope implements Comparator<Point>
    {
      @Override
      public int compare(Point p1, Point p2)
      {
         if (Point.this.slopeTo(p1) == Point.this.slopeTo(p2))
            return 0;
         else if (Point.this.slopeTo(p1) > Point.this.slopeTo(p2))
            return 1;
         else
            return -1;
      }
    }

    private final int x;
    private final int y;

    // create the point (x, y)
    public Point(int x, int y) 
    {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() 
    {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) 
    {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) 
    {
       if (this.compareTo(that) == 0)
          return Double.NEGATIVE_INFINITY;
       
       // Check horizontal line segment
       if (this.y == that.y)
          return (double) 0.0/1.0;

       // Check vertical line segment
       if (this.x == that.x)
          return Double.POSITIVE_INFINITY;
       
       return ((double) (that.y - this.y)) / (that.x - this.x); 
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) 
    {
        if (this.y < that.y)
           return -1;
        else if (this.y > that.y)
           return 1;
        else if (this.x < that.x)
           return -1;
        else if (this.x > that.x)
           return 1;
        else
           return 0;
    }

    // return string representation of this point
    public String toString() 
    {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) 
    {
        Point p1 = new Point(126, 238);
        Point p2 = new Point(53, 92);
        Point p3 = new Point(54, 94);
        
        p1.SLOPE_ORDER.compare(p2, p3);
    }
}
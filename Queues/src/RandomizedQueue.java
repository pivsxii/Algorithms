/*************************************************************************
* Name: Paolo Re
*
* Description: The RandomizedQueue class
* Algorithms, Part I - Week 2
*
*************************************************************************/
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> 
{
   private Item[] array = null;
   private int numElem = 0;
   
   private class RandomizedQueueIterator implements Iterator<Item>
   {
      private Item[] randomElemArray = (Item[]) new Object[numElem];
      private int countElement = 0;
      
      public RandomizedQueueIterator()
      {
         for (int i = 0; i < array.length; i++)
         {
            if (array[i] == null)
               continue;

            int randomNumber = StdRandom.uniform(numElem);
            
            while (randomElemArray[randomNumber] != null)
               randomNumber = StdRandom.uniform(numElem);
            
            randomElemArray[randomNumber] = array[i];
         }
      }
      
      @Override
      public boolean hasNext()
      {
         return countElement < randomElemArray.length;
      }

      @Override
      public Item next()
      {
         if (!hasNext())
            throw new NoSuchElementException();
         
         return randomElemArray[countElement++];
      }

      @Override
      public void remove()
      {
         throw new UnsupportedOperationException();
      }
   }
   
   // construct an empty randomized queue
   public RandomizedQueue()
   {
      this.array = (Item[]) new Object[10];
   }
   
   // is the queue empty?
   public boolean isEmpty()
   {
      return numElem == 0;
   }
   
   // return the number of items on the queue
   public int size()
   {
      return numElem;
   }
   
   // add the item
   public void enqueue(Item item)
   {
      if (item == null)
         throw new NullPointerException();
      
      if (numElem == array.length)
         resize(numElem * 2);
      
      array[numElem] = item;
      numElem++;
   }
   
   private void resize(int newDimension)
   {
      Item[] newArray = (Item[]) new Object[newDimension];
      int n = 0;
      
      for (int i = 0; i < array.length; i++)
      {
         if (array[i] == null)
            continue;
         newArray[n++] = array[i];
      }
      
      array = newArray;
   }

   // delete and return a random item   
   public Item dequeue()
   {
      int elemPosition = getRandomElement();
      Item elem = array[elemPosition];
      array[elemPosition] = null;
      
      numElem--;
      
      if (numElem > 0 && numElem == array.length / 4)
         resize(array.length / 2);
      
      return elem;
   }
   
   private int getRandomElement()
   {
      if (isEmpty())
         throw new NoSuchElementException();
      
      int randomNumber = StdRandom.uniform(array.length);
      
      while (array[randomNumber] == null)
         randomNumber = StdRandom.uniform(array.length);
      
      return randomNumber;
   }
   
   // return (but do not delete) a random item
   public Item sample()
   {
      if (isEmpty())
         throw new NoSuchElementException();
      
      return array[getRandomElement()];
   }
   
   // return an independent iterator over items in random order
   public Iterator<Item> iterator()
   {
      return new RandomizedQueueIterator();
   }
   
   // unit testing
   public static void main(String[] args)
   {
      RandomizedQueue<String> prova = new RandomizedQueue<String>();
      prova.enqueue("AA");
      prova.enqueue("BB");
      prova.enqueue("BB");
      prova.enqueue("BB");
      prova.enqueue("BB");
      prova.enqueue("BB");
      prova.enqueue("CC");
      prova.enqueue("CC");
      
      StdOut.println(prova.dequeue());
      StdOut.println(prova.dequeue());
      StdOut.println(prova.dequeue());
      StdOut.println(prova.dequeue());
      StdOut.println(prova.dequeue());
      StdOut.println(prova.dequeue());
      StdOut.println(prova.dequeue());
      StdOut.println(prova.dequeue());
      StdOut.println(prova.dequeue());
      StdOut.println(prova.dequeue());
      StdOut.println(prova.dequeue());
      StdOut.println(prova.dequeue());
      StdOut.println(prova.dequeue());
//      
//      StdOut.println("Prova con Iteratori");
//      Iterator<String> iter = prova.iterator();
//      while (iter.hasNext())
//         StdOut.println(iter.next());
   }
}
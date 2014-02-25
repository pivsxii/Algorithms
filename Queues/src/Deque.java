/*************************************************************************
* Name: Paolo Re
*
* Description: The Deque class
* Algorithms, Part I - Week 2
*
*************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> 
{
   private Node<Item> first = null;
   private Node<Item> last = null;
   private int size = 0;
   
   @SuppressWarnings("hiding")
   private class Node<Item>
   {
      private Node<Item> next = null;
      private Node<Item> previous = null;
      private Item item = null;
      
      public Node(Node<Item> previous, Node<Item> next, Item item)
      {
         this.previous = previous;
         this.next = next;
         this.item = item;
      }
   }
   
   private class DequeIterator implements Iterator<Item> 
   {
      private Node<Item> pointer = first;
      
      @Override
      public boolean hasNext()
      {
         return pointer != null;
      }

      @Override
      public Item next()
      {
         if (!hasNext())
            throw new java.util.NoSuchElementException();
         
         Item it = pointer.item;
         pointer = pointer.next;
         
         return it;
      }

      @Override
      public void remove()
      {
         throw new UnsupportedOperationException();
      }
   }
   
   // construct an empty deque
   public Deque()
   {
      this.first = null;
      this.last = null;
      this.size = 0;
   }
   
   // is the deque empty?
   public boolean isEmpty()
   {
      return size == 0;
   }
   
   // return the number of items on the deque
   public int size() 
   {
      return size;
   }
   
   // insert the item at the front
   public void addFirst(Item item)
   {
      if (item == null)
         throw new NullPointerException();
      
      Node<Item> elem = new Node<Item>(null, first, item);
      
      if (first != null)
         first.previous = elem;
      
      first = elem;
      
      if (isEmpty())
         last = first;
      
      size++;
   }
   
   // insert the item at the end
   public void addLast(Item item)
   {
      if (item == null)
         throw new NullPointerException();
      
      Node<Item> elem = new Node<Item>(last, null, item);
      
      if (last != null)
         last.next = elem;
         
      last = elem;
      
      if (size == 0)
         first = last;
      
      size++;
   }
   
   // delete and return the item at the front
   public Item removeFirst()
   {
      if (isEmpty())
         throw new NoSuchElementException();
      
      Node<Item> temp = first;
      first = temp.next;
      
      size--;
      
      if (first != null)
         first.previous = null;
      else
         last = null;
      
      return temp.item;
   }
   
   // delete and return the item at the end
   public Item removeLast()
   {
      if (isEmpty())
         throw new NoSuchElementException();
      
      Node<Item> temp = last;
      last = temp.previous;
      
      size--;
      
      if (last != null)
         last.next = null;
      else
         first = null;
      
      return temp.item;
   }
   
   // return an iterator over items in order from front to end
   public Iterator<Item> iterator()
   {
      return new DequeIterator();
   }
   
   // unit testing
   public static void main(String[] args)
   {
      Deque<String> prova = new Deque<String>();
      prova.addFirst("pippo");
      prova.addFirst("pluto");
      prova.addFirst("Minni");
      prova.removeLast();
      prova.removeLast();
      prova.removeLast();
      
      for (String string : prova)
      {
         StdOut.println(string);
      }
   }
}
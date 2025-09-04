//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P08 JukeBox
// Course: CS 300 Fall 2024
//
// Author: Angelina Chou
// Email: apchou@wisc.edu
// Lecturer: Blerina Gkotse
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: (identify each by name and describe how they helped)
// Online Sources: (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;

/**
 * A generic singly-linked queue implementation.
 */
public class LinkedQueue<T> implements QueueADT<T> {
  private LinkedNode<T> front;
  private LinkedNode<T> back;
  private int size;

  /**
   * Add a new element to the back of the queue, assumed to be non-null.
   * 
   * @param value the value to add
   */
  public void enqueue(T value) {
    LinkedNode<T> node = new LinkedNode<>(value);
    if (front == null) {
      front = node;
      back = node;
    } else {
      back.setNext(node);
      back = node;
    }
    size++;
  }

  /**
   * Removes and returns the value added to this queue least recently
   * 
   * @return the least recently-added value, or null if the queue is empty
   */
  public T dequeue() {
    if (front == null) {
      return null;
    }

    LinkedNode<T> node = front;
    front = front.getNext();

    if (front == null) {
      back = null;
    }

    size--;
    return node.getData();
  }

  /**
   * Accesses the value added to this queue least recently, without modifying the queue
   * 
   * @return the least recently-added value, or null if the queue is empty
   */
  public T peek() {
    if (front == null) {
      return null;
    }
    return front.getData();
  }

  /**
   * Returns true if this queue contains no elements.
   * 
   * @return true if the queue contains no elements, false otherwise
   */
  public boolean isEmpty() {
    if (front == null) {
      return true;
    }
    return false;
  }

  /**
   * Returns the number of elements in the queue.
   * 
   * @return the number of elements in the queue
   */
  public int size() {
    return size;
  }

  /**
   * Removes all of the elements from the queue. The queue will be empty after this call returns.
   */
  public void clear() {
    front = null;
    back = null;
    size = 0;
  }

  /**
   * Returns true if this queue contains the element, false otherwise
   * 
   * @param value the value to check
   * @return true if the queue contains the element, false otherwise
   */
  public boolean contains(T value) {
    LinkedNode<T> current = front;
    while (current != null) {
      if (current.getData().equals(value)) {
        return true;
      }
      current = current.getNext();
    }
    return false;
  }

  /**
   * Creates a copy of the current contents of this queue in the order they are present here, in
   * ArrayList form. This method should traverse the queue without removing any elements, and add
   * the values (not the nodes!) to an ArrayList in the order they appear in the queue, with the
   * front of the queue at index 0.
   * 
   * @return an ArrayList representation of the current state of this queue
   */
  public ArrayList<T> getList() {
    ArrayList<T> a = new ArrayList<>();
    LinkedNode<T> node = front;

    while (node != null) {
      a.add(node.getData());
      node = node.getNext();
    }
    return a;
  }
}

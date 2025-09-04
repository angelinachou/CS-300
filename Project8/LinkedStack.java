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
 * A generic singly-linked stack implementation.
 */
public class LinkedStack<T> implements StackADT<T> {
  private LinkedNode<T> top;

  /**
   * Add a new element to the top of this stack, assumed to be non-null.
   * 
   * @param value - the value to add
   */
  public void push(T value) {
    LinkedNode<T> node = new LinkedNode<>(value);
    node.setNext(top);
    top = node;
  }

  /**
   * Removes and returns the value added to this stack most recently
   * 
   * @return the most recently-added value, or null if the stack is empty
   */
  public T pop() {
    if (top == null) {
      return null;
    }
    LinkedNode<T> node = new LinkedNode<>(top.getData());
    top = top.getNext();
    return node.getData();
  }

  /**
   * Accesses the value added to this stack most recently, without modifying the stack
   * 
   * @return the most recently-added value, or null if the stack is empty
   */
  public T peek() {
    if (top == null) {
      return null;
    }
    return top.getData();
  }

  /**
   * Returns true if this stack contains no elements.
   * 
   * @return true if the stack contains no elements, false otherwise
   */
  public boolean isEmpty() {
    if (top == null) {
      return true;
    }
    return false;
  }

  /**
   * Returns true if this stack contains the element, false otherwise
   * 
   * @param value the value to check
   * @return true if the stack contains the element, false otherwise
   */
  public boolean contains(T value) {
    if (top == null) {
      return false;
    }

    LinkedNode<T> node = top;
    while (node != null) {
      if (node.getData().equals(value)) {
        return true;
      }
      node = node.getNext();
    }
    return false;
  }

  /**
   * Creates a copy of the current contents of this stack in the order they are present here, in
   * ArrayList form. This method should traverse the stack without removing any elements, and add
   * the values (not the nodes!) to an ArrayList in the order they appear in the stack, with the top
   * of the stack at index 0.
   * 
   * @return an ArrayList representation of the current state of this stack
   */
  public ArrayList<T> getList() {
    ArrayList<T> a = new ArrayList<>();
    LinkedNode<T> node = top;

    while (node != null) {
      a.add(node.getData());
      node = node.getNext();
    }
    return a;
  }
}

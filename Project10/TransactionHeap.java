//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P10 Bank Transaction Manager
// Course: CS 300 Fall 2024
//
// Author: Angelina Chou
// Email: apchou@wisc.edu
// Lecturer: Blerina Gkotse
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Online Sources: https://www.w3schools.com/java/java_switch.asp
// https://stackoverflow.com/questions/4649423/should-switch-statements-always-contain-a-default-
// clause
// https://docs.oracle.com/javase/8/docs/api/java/lang/Enum.html
//
///////////////////////////////////////////////////////////////////////////////

import java.util.NoSuchElementException;

/**
 * Class for implementing a heap/priority queue on Transactions
 */
public class TransactionHeap {
  private Transaction[] transactions;
  private int size;

  /**
   * Initializes transactions array with size capacity
   * 
   * @param capacity the length of the transactions heap array
   */
  public TransactionHeap(int capacity) {
    transactions = new Transaction[capacity];
    size = 0;
  }

  /**
   * This method adds a transaction to the heap if space allows.
   * 
   * @param transaction the transaction to add to the heap
   * @throws IllegalStateException if the TransactionHeap is full.
   */
  public void addTransaction(Transaction transaction) {
    if (size == (transactions.length)) {
      throw new IllegalStateException("Transaction heap is full.");
    }
    transactions[size] = transaction;
    size++;
    heapifyUp(size - 1);
  }

  /**
   * Reinforces the heap rules after adding a Transaction to the end
   * 
   * @param index the index of the new Transaction
   */
  public void heapifyUp(int index) {
    while (index > 0) {
      int i = (index - 1) / 2;

      if (transactions[index].compareTo(transactions[i]) > 0) {
        Transaction temp = transactions[index];
        transactions[index] = transactions[i];
        transactions[i] = temp;
        index = i;
      } else {
        break;
      }
    }
  }

  /**
   * Removes the next transaction from the priority queue
   * 
   * @return the next transaction in the priority queue
   * @throws NoSuchElementException if there are no transactions in the heap
   */
  public Transaction getNextTransaction() {
    if (isEmpty()) {
      throw new NoSuchElementException("No transactions in the heap.");
    }

    Transaction t = transactions[0];
    transactions[0] = transactions[size - 1];
    transactions[size - 1] = null;
    size--;
    heapifyDown(0);
    return t;
  }

  /**
   * Enforces the heap conditions after removing a Transaction from the heap
   * 
   * @param index the index whose subtree needs to be heapified
   */
  public void heapifyDown(int index) {
    while (true) {
      int left = (2 * index) + 1;
      int right = (2 * index) + 2;
      int max = index;

      if ((left < size) && (transactions[left].compareTo(transactions[max]) > 0)) {
        max = left;
      }

      if ((right < size) && (transactions[right].compareTo(transactions[max]) > 0)) {
        max = right;
      }

      if (max == index) {
        break;
      }

      Transaction temp = transactions[index];
      transactions[index] = transactions[max];
      transactions[max] = temp;

      index = max;
    }
  }

  /**
   * Returns the highest priority transaction without removing it from the heap.
   * 
   * @return the highest priority transaction without removing it from the heap.
   * @throws NoSuchElementException if there are no transactions in the heap
   */
  public Transaction peek() {
    if (isEmpty()) {
      throw new NoSuchElementException("No transactions in the heap.");
    }

    return transactions[0];
  }

  /**
   * Getter method for the heap size
   * 
   * @return the size
   */
  public int getSize() {
    return size;
  }

  /**
   * Tells if the heap has any elements in it
   * 
   * @return whether or not the heap is empty
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * PROVIDED Creates and returns a deep copy of the heap's array of data.
   * 
   * @return the deep copy of the array holding the heap's data
   */
  public Transaction[] getHeapData() {
    Transaction[] list = new Transaction[this.transactions.length];
    for (int i = 0; i < list.length; i++)
      list[i] = this.transactions[i];
    return list;
  }

}

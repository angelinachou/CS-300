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

public class BankManager {
  protected TransactionHeap low;
  protected TransactionHeap medium;
  protected TransactionHeap high;

  public BankManager(int capacity) {
    low = new TransactionHeap(capacity);
    medium = new TransactionHeap(capacity);
    high = new TransactionHeap(capacity);
  }

  /**
   * Gets and removes the next transaction from the priority queues. Take the transaction from the
   * highest available priority queue (high -> medium -> low).
   * 
   * @return The next transaction to process, null if there are no transactions.
   */
  public Transaction getNextTransaction() {
    if (!high.isEmpty()) {
      return high.getNextTransaction();
    } else if (!medium.isEmpty()) {
      return medium.getNextTransaction();
    } else if (!low.isEmpty()) {
      return low.getNextTransaction();
    }
    return null;
  }

  /**
   * Gets the highest priority transaction from the priority queues without removing it. Take the
   * transaction from the highest available priority queue (high -> medium -> low).
   * 
   * @return the transaction with highest priority from all heaps and null if there are no
   *         transactions.
   */
  public Transaction peekHighestPriorityTransaction() {
    if (!high.isEmpty()) {
      return high.peek();
    } else if (!medium.isEmpty()) {
      return medium.peek();
    } else if (!low.isEmpty()) {
      return low.peek();
    }
    return null;
  }

  /**
   * Adds a transaction to the BankManager according to the amount that the transaction is for low:
   * < 1000 medium: 1000 <= t < 1000000 high: >= 1000000
   * 
   * @param transaction the transaction to add to the BankManager
   */
  public void queueTransaction(Transaction transaction) {
    if (transaction.getAmount() >= 1000000) {
      high.addTransaction(transaction);
    } else if (transaction.getAmount() >= 1000) {
      medium.addTransaction(transaction);
    } else if (transaction.getAmount() < 1000) {
      low.addTransaction(transaction);
    }
  }

  /**
   * Removes and processes the next transaction in the priority queue. Withdrawals should remove the
   * amount from the balance, deposits should add the amount to the balance, and loan applications
   * add the amount to the balance only if the loan amount is less than ten (10) times the account's
   * current balance.
   * 
   * @throws NoSuchElementException if there are no transactions to process
   * @throws IllegalStateException  if the account would overdraft
   */
  public void performTransaction() {
    Transaction t = getNextTransaction();
    if (t == null) {
      throw new NoSuchElementException("No transactions to process");
    }

    if (t.getType() == Transaction.Type.WITHDRAWAL) {
      if (t.getAmount() > t.getUser().getBalance()) {
        throw new IllegalStateException("Account would overdraft");
      }
      t.getUser().withdraw(t.getAmount());
    } else if (t.getType() == Transaction.Type.DEPOSIT) {
      t.getUser().deposit(t.getAmount());
    } else if (t.getType() == Transaction.Type.LOAN_APPLICATION) {
      if (t.getAmount() < (10 * t.getUser().getBalance())) {
        t.getUser().deposit(t.getAmount());
      }
    }
  }

}

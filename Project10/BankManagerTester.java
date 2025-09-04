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

public class BankManagerTester {

  /**
   * Tests the constructor for the Transaction class.
   * 
   * @return true if the test passes
   */
  public static boolean testTransactionConstructor() {
    // (1)Verify if the transaction was created correctly and assigned the
    // correct priority.
    // (2)Verify the only correct amounts can be used, otherwise verify that an
    // exception is thrown.
    Account user = new Account(1L, 10);
    try {
      Transaction t_bad = new Transaction(user, 0, Transaction.Type.DEPOSIT);
      return false;
    } catch (IllegalArgumentException e) {
      // should get here
    }

    Transaction t1 = new Transaction(user, 500, Transaction.Type.DEPOSIT);
    if ((t1.getUser() == null) || (t1.getAmount() != 500)
        || (t1.getType() != Transaction.Type.DEPOSIT)
        || (t1.getPriority() != Transaction.Priority.HIGH)) {
      return false;
    }

    Transaction t2 = new Transaction(user, 2000, Transaction.Type.LOAN_APPLICATION);
    if ((t2.getUser() == null) || (t2.getAmount() != 2000)
        || (t2.getType() != Transaction.Type.LOAN_APPLICATION)
        || (t2.getPriority() != Transaction.Priority.LOW)) {
      return false;
    }

    Transaction t3 = new Transaction(user, 1, Transaction.Type.LOAN_APPLICATION);
    if ((t3.getUser() == null) || (t3.getAmount() != 1)
        || (t3.getType() != Transaction.Type.LOAN_APPLICATION)
        || (t3.getPriority() != Transaction.Priority.URGENT)) {
      return false;
    }

    Transaction t4 = new Transaction(user, 10, Transaction.Type.WITHDRAWAL);
    if ((t4.getUser() == null) || (t4.getAmount() != 10)
        || (t4.getType() != Transaction.Type.WITHDRAWAL)
        || (t4.getPriority() != Transaction.Priority.NORMAL)) {
      return false;
    }
    return true;
  }

  /**
   * tests the Transaction.compareTo when the priorities are different
   * 
   * @return true if the test passes
   */
  public static boolean testTransactionCompareToPriority() {
    // verify that the transaction with the higher priority is greater than the
    // one with the lower.
    Account user1 = new Account(2L, 100);
    Account user2 = new Account(3L, 50);

    Transaction positive1 = new Transaction(user1, 5, Transaction.Type.LOAN_APPLICATION);
    Transaction positive2 = new Transaction(user2, 200, Transaction.Type.LOAN_APPLICATION);
    if (positive1.compareTo(positive2) <= 0) {
      return false;
    }

    Transaction negative1 = new Transaction(user1, 5, Transaction.Type.WITHDRAWAL);
    Transaction negative2 = new Transaction(user2, 5, Transaction.Type.DEPOSIT);
    if (negative1.compareTo(negative2) >= 0) {
      return false;
    }

    return true;
  }

  /**
   * tests the Transaction.compareTo when the priorities are the same
   * 
   * @return true if the test passes
   */
  public static boolean testTransactionCompareToAccountBalance() {
    // verify that when the transaction has the same priority, the account
    // balance is used.
    Account user1 = new Account(2L, 100);
    Account user2 = new Account(3L, 50);
    Transaction equal1 = new Transaction(user1, 5, Transaction.Type.DEPOSIT);
    Transaction equal2 = new Transaction(user2, 5, Transaction.Type.DEPOSIT);
    if (equal1.compareTo(equal2) <= 0) {
      return false;
    }

    user2.deposit(50);
    Transaction equal3 = new Transaction(user1, 5, Transaction.Type.DEPOSIT);
    Transaction equal4 = new Transaction(user2, 5, Transaction.Type.DEPOSIT);
    if (equal3.compareTo(equal4) != 0) {
      return false;
    }

    user2.deposit(50);
    Transaction equal5 = new Transaction(user1, 5, Transaction.Type.DEPOSIT);
    Transaction equal6 = new Transaction(user2, 5, Transaction.Type.DEPOSIT);
    if (equal5.compareTo(equal6) >= 0) {
      return false;
    }
    return true;
  }

  /**
   * Tests the TransactionHeap.addTransaction() method
   * 
   * @return true if the test passes
   */
  public static boolean testAddTransactionToHeap() {
    // Verify that the transaction is added to the transaction heap
    TransactionHeap t = new TransactionHeap(2);
    Account user = new Account(2L, 100);
    Transaction t1 = new Transaction(user, 10, Transaction.Type.LOAN_APPLICATION);
    Transaction t2 = new Transaction(user, 2000, Transaction.Type.WITHDRAWAL);
    Transaction t3 = new Transaction(user, 20000, Transaction.Type.DEPOSIT);

    t.addTransaction(t1);
    if (t.getSize() != 1) {
      return false;
    }

    t.addTransaction(t2);
    if (t.getSize() != 2) {
      return false;
    }

    try {
      t.addTransaction(t3);
      return false;
    } catch (IllegalStateException e) {
      // should reach this
    }

    return true;
  }

  /**
   * Tests the TransactionHeap.heapifyUp() and TransactionHeap.heapifyDown() methods
   * 
   * @return true if the test passes
   */
  public static boolean testHeapify() {
    // (1) verify that the heap property is restored after adding a transaction
    // to the heap
    // (2)verify that the heap property is restored after removing a transaction
    // from the heap
    TransactionHeap t = new TransactionHeap(5);
    Account user = new Account(1L, 100);

    Transaction t1 = new Transaction(user, 10, Transaction.Type.LOAN_APPLICATION); // URGENT
    Transaction t2 = new Transaction(user, 10, Transaction.Type.WITHDRAWAL); // NORMAL
    Transaction t3 = new Transaction(user, 100, Transaction.Type.DEPOSIT); // HIGH

    t.addTransaction(t1);
    if (t.peek() != t1) {
      return false;
    }

    t.addTransaction(t2);
    if (t.peek() != t1) {
      return false;
    }

    t.addTransaction(t3);
    Transaction[] expected = {t1, t2, t3};
    for (int i = 0; i < expected.length; i++) {
      if (t.getHeapData()[i] != expected[i]) {
        return false;
      }
    }

    Transaction removed1 = t.getNextTransaction();
    if ((removed1 != t1) || t.peek() != t3) {
      return false;
    }

    Transaction removed2 = t.getNextTransaction();
    if ((removed2 != t3) || t.peek() != t2) {
      return false;
    }

    Transaction removed3 = t.getNextTransaction();
    if ((removed3 != t2) || !t.isEmpty()) {
      return false;
    }

    return true;
  }

  /**
   * Tests the TransactionHeap.getNextTransaction() method
   * 
   * @return true if the test passes
   */
  public static boolean testGetNextTransactionFromHeap() {
    // TODO (1) verify that the correct transaction is removed
    // (2) verify that an exception is thrown if the heap is empty
    TransactionHeap t = new TransactionHeap(4);
    Account user = new Account(5L, 100);
    Transaction t1 = new Transaction(user, 50, Transaction.Type.DEPOSIT); // HIGH
    Transaction t2 = new Transaction(user, 20, Transaction.Type.WITHDRAWAL); // NORMAL
    Transaction t3 = new Transaction(user, 1000, Transaction.Type.LOAN_APPLICATION); // LOW
    Transaction t4 = new Transaction(user, 40, Transaction.Type.LOAN_APPLICATION); // URGENT

    t.addTransaction(t1);
    t.addTransaction(t2);
    t.addTransaction(t3);
    t.addTransaction(t4);

    if (!validHeap(t.getHeapData(), t.getSize())) {
      return false;
    }

    Transaction removed1 = t.getNextTransaction();
    if ((removed1 != t4) || (t.getSize() != 3) || (!validHeap(t.getHeapData(), t.getSize()))) {
      return false;
    }

    Transaction removed2 = t.getNextTransaction();
    if ((removed2 != t1) || (t.getSize() != 2) || (!validHeap(t.getHeapData(), t.getSize()))) {
      return false;
    }

    Transaction removed3 = t.getNextTransaction();
    if ((removed3 != t2) || (t.getSize() != 1) || (!validHeap(t.getHeapData(), t.getSize()))) {
      return false;
    }

    Transaction removed4 = t.getNextTransaction();
    if ((removed4 != t3) || (t.getSize() != 0) || (!validHeap(t.getHeapData(), t.getSize()))) {
      return false;
    }

    try {
      t.getNextTransaction();
      return false;
    } catch (NoSuchElementException e) {
      // should get here
    }

    return true;
  }

  private static boolean validHeap(Transaction[] data, int size) {
    for (int i = 0; i < size; i++) {
      int l = 2 * i + 1;
      int r = 2 * i + 2;

      if (l < size && (data[i].compareTo(data[l]) < 0)) {
        return false;
      }
      if (r < size && (data[i].compareTo(data[r]) < 0)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Tests the BankManager.queueTransaction() method
   * 
   * @return true if the test passes
   */
  public static boolean testQueueTransaction() {
    // Verify that a transaction is added in the correct queue:
    // (1) Verify that a transaction with amount <1000 is added to the "low" heap
    // (2) Verify that a transaction with amount greater or equal to 1000 but less
    // than 1000000 is added to the "medium" heap
    // (3) Verify that a transaction with amount greater or equal to 1000000 is
    // added to the "high" heap
    Account user = new Account(5L, 3000);
    BankManager b = new BankManager(10);

    Transaction t1 = new Transaction(user, 10, Transaction.Type.LOAN_APPLICATION);
    b.queueTransaction(t1);
    if ((b.low.getSize() != 1) || (b.medium.getSize() != 0) || (b.high.getSize() != 0)) {
      return false;
    }
    if (!b.peekHighestPriorityTransaction().equals(t1)) {
      return false;
    }
    b.getNextTransaction();

    Transaction t2 = new Transaction(user, 2000, Transaction.Type.WITHDRAWAL);
    b.queueTransaction(t2);
    if ((b.low.getSize() != 0) || (b.medium.getSize() != 1) || (b.high.getSize() != 0)) {
      return false;
    }
    if (!b.peekHighestPriorityTransaction().equals(t2)) {
      return false;
    }
    b.getNextTransaction();

    Transaction t3 = new Transaction(user, 2000000, Transaction.Type.DEPOSIT);
    b.queueTransaction(t3);
    if ((b.low.getSize() != 0) || (b.medium.getSize() != 0) || (b.high.getSize() != 1)) {
      return false;
    }
    if (!b.peekHighestPriorityTransaction().equals(t3)) {
      return false;
    }

    return true;
  }

  /**
   * Tests the BankManager.performTransaction() method
   * 
   * @return true if the test passes
   */
  public static boolean testPerformTransaction() {
    // (1) Verify the transaction is removed from the correct heap
    // (2) Verify the transaction is processed correctly:
    // (2a) Verify that the transaction is done when that is possible based on the
    // rules
    // (2b) Verify that an exception is thrown when necessary
    Account user = new Account(5L, 3000);
    BankManager b = new BankManager(10);
    try {
      b.performTransaction();
      return false;
    } catch (NoSuchElementException e) {
      // should reach this
    }

    Transaction t1 = new Transaction(user, 10, Transaction.Type.LOAN_APPLICATION);
    Transaction t2 = new Transaction(user, 2000, Transaction.Type.WITHDRAWAL);
    Transaction t3 = new Transaction(user, 1000000, Transaction.Type.WITHDRAWAL);
    Transaction t4 = new Transaction(user, 20000, Transaction.Type.DEPOSIT);
    b.queueTransaction(t1);
    b.queueTransaction(t2);
    b.queueTransaction(t3);
    b.queueTransaction(t4);

    if ((b.high.getSize() != 1) || (b.medium.getSize() != 2) || (b.low.getSize() != 1)) {
      return false;
    }

    // transaction t3
    try {
      b.performTransaction();
    } catch (IllegalStateException e) {
      // should reach this
    }
    if ((b.high.getSize() != 0) || (b.medium.getSize() != 2) || (b.low.getSize() != 1)
        || (user.getBalance() != 3000)) {
      return false;
    }

    // transaction t4
    b.performTransaction();
    if ((b.high.getSize() != 0) || (b.medium.getSize() != 1) || (b.low.getSize() != 1)
        || (user.getBalance() != 23000)) {
      return false;
    }

    // transaction t2
    b.performTransaction();
    if ((b.high.getSize() != 0) || (b.medium.getSize() != 0) || (b.low.getSize() != 1)
        || (user.getBalance() != 21000)) {
      return false;
    }

    // transaction t1
    b.performTransaction();
    if ((b.high.getSize() != 0) || (b.medium.getSize() != 0) || (b.low.getSize() != 0)
        || (user.getBalance() != 21010)) {
      return false;
    }

    Transaction t5 = new Transaction(user, 500000000, Transaction.Type.LOAN_APPLICATION);
    b.queueTransaction(t5);
    b.performTransaction();

    if ((b.high.getSize() != 0) || (b.medium.getSize() != 0) || (b.low.getSize() != 0)
        || (user.getBalance() != 21010)) {
      return false;
    }

    return true;
  }

  /**
   * Tests the BankManager.peekHighestPriorityTransaction() method
   * 
   * @return true if the test passes
   */
  public static boolean testPeekHighestPriorityTransaction() {
    // verify that the transaction with the highest priority is returned
    // without being removed
    Account user = new Account(5L, 30000000);
    BankManager b = new BankManager(10);

    Transaction t1 = new Transaction(user, 10, Transaction.Type.LOAN_APPLICATION);
    Transaction t2 = new Transaction(user, 2000, Transaction.Type.WITHDRAWAL);
    Transaction t3 = new Transaction(user, 1000000, Transaction.Type.WITHDRAWAL);
    Transaction t4 = new Transaction(user, 20000, Transaction.Type.DEPOSIT);
    b.queueTransaction(t1);
    b.queueTransaction(t2);
    b.queueTransaction(t3);
    b.queueTransaction(t4);

    Transaction peek1 = b.peekHighestPriorityTransaction();
    if ((!peek1.equals(t3)) || (b.high.getSize() != 1) || (b.medium.getSize() != 2)
        || (b.low.getSize() != 1)) {
      return false;
    }
    b.performTransaction();

    Transaction peek2 = b.peekHighestPriorityTransaction();
    if ((!peek2.equals(t4)) || (b.high.getSize() != 0) || (b.medium.getSize() != 2)
        || (b.low.getSize() != 1)) {
      return false;
    }
    b.performTransaction();

    Transaction peek3 = b.peekHighestPriorityTransaction();
    if ((!peek3.equals(t2)) || (b.high.getSize() != 0) || (b.medium.getSize() != 1)
        || (b.low.getSize() != 1)) {
      return false;
    }
    b.performTransaction();

    Transaction peek4 = b.peekHighestPriorityTransaction();
    if ((!peek4.equals(t1)) || (b.high.getSize() != 0) || (b.medium.getSize() != 0)
        || (b.low.getSize() != 1)) {
      return false;
    }

    return true;
  }

  public static void main(String[] args) {
    System.out.println(
        "Transaction Constructor Tests: " + (testTransactionConstructor() ? "PASS" : "FAIL"));
    System.out.println(
        "CompareTo Tests for Priority: " + (testTransactionCompareToPriority() ? "PASS" : "FAIL"));
    System.out.println("CompareTo Tests for Account Balance: "
        + (testTransactionCompareToAccountBalance() ? "PASS" : "FAIL"));
    System.out.println(
        "Testing Add Transaction to Heap: " + (testAddTransactionToHeap() ? "PASS" : "FAIL"));
    System.out.println("Testing Heapify: " + (testHeapify() ? "PASS" : "FAIL"));
    System.out.println(
        "Testing Get Next Transaction: " + (testGetNextTransactionFromHeap() ? "PASS" : "FAIL"));
    System.out.println("Testing Queue Transaction: " + (testQueueTransaction() ? "PASS" : "FAIL"));
    System.out
        .println("Testing Perform Transaction: " + (testPerformTransaction() ? "PASS" : "FAIL"));
    System.out.println("Testing Peek Highest Priority Transaction: "
        + (testPeekHighestPriorityTransaction() ? "PASS" : "FAIL"));
  }
}

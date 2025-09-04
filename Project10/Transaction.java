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

public class Transaction implements Comparable<Transaction> {

  /**
   * ENUM are values you can access directly. For example, if you wanted to set the priority of a
   * transaction to HIGH, you could do this.priority = Priority.HIGH; If you want to access the
   * priorities from a different class, the call becomes Transaction.Priority.HIGH and so on.
   */
  public enum Priority {
    LOW, NORMAL, HIGH, URGENT
  }

  public enum Type {
    LOAN_APPLICATION, WITHDRAWAL, DEPOSIT
  }

  private final Priority priority;
  private final Account user;
  private final double amount;
  private final Type type;

  /**
   * compareTo implementation. This compares the Priority value, and the higher the value, the more
   * important. If the priorities are equal, then compare the account balances of the two accounts,
   * with a higher account balance being more important. Refer to
   * https://docs.oracle.com/javase/8/docs/api/java/lang/Enum.html for help on how to work with
   * enums, there are a few ways to do this!
   * 
   * @param other the other transaction
   * @return a positive value if this transaction is MORE important, a negative number if it less
   *         important, and 0 if they are equal.
   */
  @Override
  public int compareTo(Transaction other) {
    // CITE: oracle.com to understand what ordinal does
    int ordinalP = priority.ordinal();
    int ordinalO = other.getPriority().ordinal();
    if (ordinalP > ordinalO) {
      return 1;
    } else if (ordinalP < ordinalO) {
      return -1;
    } else {
      if (user.getBalance() > other.user.getBalance()) {
        return 1;
      } else if (user.getBalance() < other.user.getBalance()) {
        return -1;
      }
    }
    return 0;
  }

  /**
   * Constructor for the Transaction class
   *
   * Rules for the priority are: If it is a deposit, the priority is HIGH. If it is a withdrawal,
   * the priority is NORMAL. If it is a loan application which exceeds 3x the account balance, it is
   * LOW If it is a loan application which does not exceed the 3x threshold, it is URGENT
   *
   * One tip: enums can be used in switch statements!
   *
   * @param user   The account associated with the transaction
   * @param amount the amount related to this transaction
   * @param type   the transaction type
   * @throws IllegalArgumentException if the amount is 0 or less
   */
  public Transaction(Account user, double amount, Type type) {
    if (amount <= 0) {
      throw new IllegalArgumentException("Amount is 0 or less");
    }
    this.user = user;
    this.amount = amount;
    this.type = type;

    // CITE: w3schools.com to learn switch statements
    switch (type) {
      case DEPOSIT:
        this.priority = Priority.HIGH;
        break;
      case WITHDRAWAL:
        this.priority = Priority.NORMAL;
        break;
      case LOAN_APPLICATION:
        if (amount <= (user.getBalance() * 3)) {
          this.priority = Priority.URGENT;
        } else {
          this.priority = Priority.LOW;
        }
        break;
      // CITE: Stack overflow to understand default
      default:
        // not really specified what the default should be but I just put it as this
        // it should not ever reach this line
        this.priority = Priority.LOW;
        System.out.println("Should not have reached the default line.");
    }
  }

  /**
   * Getter for the account
   * 
   * @return the account for this transaction
   */
  public Account getUser() {
    return user;
  }

  /**
   * Getter for the amount
   * 
   * @return the amount for this transaction
   */
  public double getAmount() {
    return amount;
  }

  /**
   * Getter for the type
   * 
   * @return the type for this transaction
   */
  public Type getType() {
    return type;
  }

  /**
   * Getter for the priority
   * 
   * @return the priority for this transaction
   */
  public Priority getPriority() {
    return priority;
  }

}

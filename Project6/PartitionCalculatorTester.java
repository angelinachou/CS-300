//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P06 Calculating Partitions
// Course: CS 300 Fall 2024
//
// Author: Angelina Chou
// Email: apchou@wisc.edu
// Lecturer: Blerina Gkotse
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: (identify each by name and describe how they helped)
// Online Sources: https://www.geeksforgeeks.org/generating-random-numbers-in-java/
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Random;

/**
 * A tester class for the PartitionCalculator class.
 *
 */
public class PartitionCalculatorTester {

  /**
   * Tests the correctness of PartitionCalculator.numOfPartitions method for base cases n = 1 and n
   * = 2. This should also account for unexpected exceptions that MAY occur.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testNumOfPartitionsBase() {
    return PartitionCalculator.numOfPartitions(1) == TesterUtility.getPartitionCount(1)
        && PartitionCalculator.numOfPartitions(2) == TesterUtility.getPartitionCount(2);
  }

  /**
   * Tests the correctness of PartitionCalculator.numOfPartitions method for recursive cases with n
   * >= 3. There must be a total of 3 test cases. This should also account for unexpected exceptions
   * that MAY occur.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testNumOfPartitionsRecursive() {
    boolean t1 = PartitionCalculator.numOfPartitions(5) == TesterUtility.getPartitionCount(5);
    boolean t2 = PartitionCalculator.numOfPartitions(6) == TesterUtility.getPartitionCount(6);
    boolean t3 = PartitionCalculator.numOfPartitions(7) == TesterUtility.getPartitionCount(7);
    return t1 && t2 && t3;
  }

  /**
   * Tests the correctness of PartitionCalculator.numOfPartitions method for multiple random cases
   * of N. (See write-up for more details). This should also account for unexpected exceptions that
   * MAY occur.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testNumOfPartitionsFuzz() {
    Random random = new Random();
    // CITE: generating random number within range - geeksforgeeks
    int rand = random.nextInt(100) + 100;
    for (int i = 0; i < rand; i++) {
      int N = random.nextInt(50) + 1;
      if (TesterUtility.getPartitionCount(N) != PartitionCalculator.numOfPartitions(N)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Tests the correctness of PartitionCalculator.calculatePartitions method for base cases n = 1
   * and n = 2. This should also account for unexpected exceptions that MAY occur.
   * 
   * @return true if it passes all test cases, false otherwise
   */

  public static boolean testCalcPartitionsBase() {
    boolean t1 = TesterUtility.comparePartitionLists(PartitionCalculator.calculatePartitions(1),
        TesterUtility.getPartitions(1, false), false);
    boolean t2 = TesterUtility.comparePartitionLists(PartitionCalculator.calculatePartitions(2),
        TesterUtility.getPartitions(2, false), false);
    return t1 && t2;
  }


  /**
   * Tests the correctness of PartitionCalculator.calculatePartitions method for recursive cases
   * with n >= 3. There must be a total of 3 test cases. This should also account for unexpected
   * exceptions that MAY occur.
   * 
   * @return true if it passes all test cases, false otherwise
   */

  public static boolean testCalcPartitionsRecursive() {
    boolean t1 = TesterUtility.comparePartitionLists(PartitionCalculator.calculatePartitions(5),
        TesterUtility.getPartitions(5, false), false);
    boolean t2 = TesterUtility.comparePartitionLists(PartitionCalculator.calculatePartitions(6),
        TesterUtility.getPartitions(6, false), false);
    boolean t3 = TesterUtility.comparePartitionLists(PartitionCalculator.calculatePartitions(7),
        TesterUtility.getPartitions(7, false), false);
    return t1 && t2 && t3;
  }

  /**
   * Tests the correctness of PartitionCalculator.calculatePartitions method for multiple random
   * cases of N. (See write-up for more details). This should also account for unexpected exceptions
   * that MAY occur.
   * 
   * @return true if it passes all test cases, false otherwise
   */

  public static boolean testCalcPartitionsFuzz() {
    Random random = new Random();
    int rand = random.nextInt(20) + 1;
    for (int i = 0; i < rand; i++) {
      int N = random.nextInt(35) + 1;
      if (!TesterUtility.comparePartitionLists(PartitionCalculator.calculatePartitions(N),
          TesterUtility.getPartitions(N, false), false)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Tests the correctness of PartitionCalculator.calculateAllPermuations method for base cases n =
   * 1 and n = 2. This should also account for unexpected exceptions that MAY occur.
   * 
   * @return true if it passes all test cases, false otherwise
   */

  public static boolean testCalculateAllPermutationsBase() {
    boolean t1 = TesterUtility.comparePartitionLists(
        PartitionCalculator.calculateAllPermutations(PartitionCalculator.calculatePartitions(1)),
        TesterUtility.getPartitions(1, true), true);
    boolean t2 = TesterUtility.comparePartitionLists(
        PartitionCalculator.calculateAllPermutations(PartitionCalculator.calculatePartitions(2)),
        TesterUtility.getPartitions(2, true), true);
    return t1 && t2;
  }

  /**
   * Tests the correctness of PartitionCalculator.calculateAllPermutations method for recursive
   * cases with n >= 3. There must be a total of 3 test cases. This should also account for
   * unexpected exceptions that MAY occur.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testCalculateAllPermutationsRecursive() {
    boolean t1 = TesterUtility.comparePartitionLists(
        PartitionCalculator.calculateAllPermutations(PartitionCalculator.calculatePartitions(3)),
        TesterUtility.getPartitions(3, true), true);
    boolean t2 = TesterUtility.comparePartitionLists(
        PartitionCalculator.calculateAllPermutations(PartitionCalculator.calculatePartitions(4)),
        TesterUtility.getPartitions(4, true), true);
    boolean t3 = TesterUtility.comparePartitionLists(
        PartitionCalculator.calculateAllPermutations(PartitionCalculator.calculatePartitions(5)),
        TesterUtility.getPartitions(5, true), true);
    return t1 && t2 && t3;
  }


  /**
   * Runs and outputs the results of all tester methods.
   * 
   * @return true if all tests return true, false otherwise
   * @author Michelle Jensen
   */
  public static boolean runAllTests() {
    boolean test1 = testNumOfPartitionsBase();
    System.out.println("testNumOfPartitionsBase(): " + (test1 ? "PASS" : "FAIL"));

    boolean test2 = testNumOfPartitionsRecursive();
    System.out.println("testNumOfPartitionsRecursive(): " + (test2 ? "PASS" : "FAIL"));


    boolean test3 = testCalcPartitionsBase();
    System.out.println("testUniquePartitionsBase(): " + (test3 ? "PASS" : "FAIL"));

    boolean test4 = testCalcPartitionsRecursive();
    System.out.println("testUniquePartitionsRecursive(): " + (test4 ? "PASS" : "FAIL"));

    boolean test5 = testCalculateAllPermutationsBase();
    System.out.println("testCalculateAllPermutationsBase(): " + (test5 ? "PASS" : "FAIL"));

    boolean test6 = testCalculateAllPermutationsRecursive();
    System.out.println("testCalculateAllPermutationsRecursive(): " + (test6 ? "PASS" : "FAIL"));

    boolean test7 = testNumOfPartitionsFuzz();
    System.out.println("testNumOfPartitionsFuzz(): " + (test7 ? "PASS" : "FAIL"));

    boolean test8 = testCalcPartitionsFuzz();
    System.out.println("testUniquePartitionsFuzz(): " + (test8 ? "PASS" : "FAIL"));

    return test1 && test2 && test3 && test4 && test5 && test6 && test7 && test8;
  }

  public static void main(String[] args) {
    System.out.println("runAllTest(): " + (runAllTests() ? "PASS" : "FAIL"));
  }

}

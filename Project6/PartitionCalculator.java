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
// Online Sources:
// - https://stackoverflow.com/questions/14053885/integer-partition-algorithm-and-recursion
// - https://stackoverflow.com/questions/26496016/adding-elements-of-another-arraylist-by-
// recursion-java
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;

/**
 * A class to calculate different scenarios of partitions, like how many partitions are possible,
 * and the different partitions that could exist.
 *
 */
public class PartitionCalculator {

  /**
   * Given a value, return the total number of partitions for that value N that could be created
   * 
   * @param N - the number that we are checking for the number of partitions
   * @return number of unique partitions possible
   */
  public static int numOfPartitions(int N) {
    return count(N, N);
  }

  /**
   * Given a value, return the total number of partitions for that value N that could be created,
   * helper method
   * 
   * @param num - the number that we are checking for the number of partitions
   * @param max - what is the biggest number we want in our combination - so if we have count (4,
   *            3), each value added together to find 4 must be less than or equal to 3
   * @return number of unique partitions possible
   */
  private static int count(int num, int max) {
    // If the max value is equal to 0, you can't add 0 to anything to change the answer, so we would
    // stop trying there. If the number we're checking for the number of partitions is less than
    // zero, then we don't want to find the sum anymore because that's not a goal.
    if (num < 0 || max == 0) {
      return 0;
    }

    if (num == 1) {
      return 1;
    }

    // CITE: Used implementation from stack overflow to answer this question - stack overflow
    // first source
    if (num == max) {
      return 1 + count(num, max - 1);
    }

    return count(num - max, max) + count(num, max - 1);
  }

  /**
   * Given a value, return all the unique partitions for the value N.
   * 
   * @param N - the number that is the sum for all possible partitions
   * @return ArrayList of unique possible partitions
   */
  public static ArrayList<Partition> calculatePartitions(int N) {
    // CITE: I was creating the arraylist in the helper method and it was not working, but this told
    // me that I should make it in the original method and then edit in the helper method - stack
    // overflow second source
    ArrayList<Partition> p = new ArrayList<>();
    calcPart(N, N, new Partition(N), p);
    return p;
  }


  /**
   * Given a value, return all the unique partitions for the value N, helper method
   * 
   * @param N         - sum value that we need partitions to add up to
   * @param max       - what is the biggest number we want in our combination - so if we have count
   *                  (4, 3), each value added together to find 4 must be less than or equal to 3
   * @param partition - current partition to be added to the arrayList
   * @param p         - ArrayList of partitions that each add up to N
   */
  private static void calcPart(int N, int max, Partition partition, ArrayList<Partition> p) {
    if (N == 0) {
      p.add(partition.copyOf());
      return;
    }

    if (N < 0 || max == 0) {
      return;
    }

    // starts with the max value - so for the first time if N=4, gives 4.
    partition.addNumber(max);

    // if N=max, then we're done here. if not, then we continue the partition to be able to get the
    // smaller values. for N = 4, if max is 2, then this will add the next 2. Breaking down the
    // partition to smaller pieces after max.
    calcPart(N - max, max, partition, p);

    // After the previous line, every time it would run I would get way too many values in the
    // partition. I realized that was because when I did N-max and for example it would be N=4
    // max = 3, on the second round over it would add 3 to it, so I have to remove it.
    partition.removeLast();

    // Lowering the max value so that we can go through each case with lower values. So for N = 4
    // and we've covered (4) in the last call, we want max = 3 to get 3 + 1 and so on. Basically
    // the same implementation as the numOfPartitions method but with the added complexity of the
    // arrayList.
    calcPart(N, max - 1, partition, p);
  }

  /**
   * Given a value, return all permutations of the partitions for the value N.
   * 
   * @param partitions - the partitions without order mattering
   * @return ArrayList of all possible partitions
   */
  public static ArrayList<Partition> calculateAllPermutations(ArrayList<Partition> partitions) {
    Partition.orderMatters = true;
    ArrayList<Partition> allPartitions = new ArrayList<>();
    for (int i = 0; i < partitions.size(); i++) {
      calcPerm(partitions.get(i).copyOf(), 0, allPartitions);
    }
    return allPartitions;
  }


  /**
   * Given a single partition, return all permutations of that partition for the value N.
   * 
   * @param N - the number that is the sum for all possible partitions
   * @return ArrayList of all possible partitions
   */
  private static void calcPerm(Partition partition, int pointer,
      ArrayList<Partition> allPartitions) {

    // base case - adds a partition if it doesn't already exist
    if (pointer >= partition.length() - 1) {
      if (exists(allPartitions, partition) == false) {
        allPartitions.add(partition.copyOf());
      }
      return;
    }

    for (int i = pointer; i < partition.length(); i++) {
      // swaps the pointer value with the i value
      partition.swapNumbers(pointer, i);

      // looks at smaller subset of the partition and shrinks it until there are only 2 values left
      // to possibly swap
      calcPerm(partition, pointer + 1, allPartitions);

      // starts over so that the value at index 0 is a different number now and then shrinks the
      // partition again with a new set of values for the ones after index 0
      partition.swapNumbers(pointer, i);
    }

  }

  /**
   * Given an ArrayList of partitions and a single partition, determine if the single partition is
   * in the ArrayList.
   * 
   * @param partitions - the ArrayList of partitions
   * @param partition  - the single partition to compare to the ArrayList
   * @return true if the partition is in the ArrayList and false if it is not
   */
  private static boolean exists(ArrayList<Partition> partitions, Partition partition) {
    Partition.orderMatters = true;

    boolean exists = false;
    for (int i = 0; i < partitions.size(); i++) {
      if (partitions.get(i).equals(partition)) {
        exists = true;
      }
    }

    return exists;
  }


}

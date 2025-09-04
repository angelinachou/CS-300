//////////////// P03 Exceptional Elections //////////////////////////
//
// Title: Exceptional Elections
// Course: CS 300 Fall 2024
//
// Author: Angelina Chou
// Email: apchou@wisc.edu
// Lecturer: Blerina Gkotse
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Online Sources: https://www.tutorialspoint.com/java/util/arraylist_clear.
// htm#:~:text=The%20Java%20ArrayList%20clear(),empty%20after%20this%20call%20returns.
// This showed me how to clear a java ArrayList for the clearElections method
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This class creates a Ballot object with functionalities like voting, adding elections, and
 * tracking votes.
 */
public class Ballot extends Object {
  private static boolean ballotsCreated = false;
  private static ArrayList<Election> elections = new ArrayList<Election>();
  private boolean[] hasVoted;

  /**
   * Constructor that initializes boolean array hasVoted and creates the ballot
   * 
   * @throws IllegalStateException if ballot is created with no elections created yet
   */
  public Ballot() {
    if (elections == null || elections.size() == 0) {
      throw new IllegalStateException("No elections to vote in yet.");
    } else {
      hasVoted = new boolean[elections.size()];
      ballotsCreated = true;
    }
  }

  /**
   * Adds an election to the elections ArrayList
   * 
   * @param Election election which is the new election added to the ArrayList, elections
   * @throws IllegalStateException    if ballot is created before election is attempted to be added
   * @throws IllegalArgumentException if the election has already been added to the ArrayList
   */
  public static void addElection(Election election) {
    if (ballotsCreated)
      throw new IllegalStateException("Ballot already created.");
    for (int i = 0; i < elections.size(); i++) {
      if (elections.get(i).toString().equals(election.toString()))
        throw new IllegalArgumentException("Election already present in list.");
    }
    elections.add(election);
  }

  /**
   * Checks if this ballot has voted in the specific seat name/election
   * 
   * @param String seatName is the seatName of the election we're checking if it's been voted in
   * @return true if the election has been voted in and false if it hasn't been
   */
  public boolean hasVoted(String seatName) {
    int index = electionIndex(seatName);
    if (hasVoted[index]) {
      return true;
    }
    return false;
  }

  /**
   * Creates a string that shows the seat name of each election and whether the ballot has voted in
   * it or not
   * 
   * @Override toString function to create a custom String that shows information on the current
   *           ballot
   */
  public String toString() {
    String s = "";
    for (int i = 0; i < elections.size(); i++) {
      s = s + elections.get(i).SEAT_NAME + ": " + String.valueOf(hasVoted[i]);
      if (i < elections.size() - 1) {
        s = s + "\n";
      }
    }
    return s;
  }

  /**
   * Finds the index for the election that is specified by the seat name
   * 
   * @param String seatName that is the name of the election
   * @return index of the election of the given seat name
   * @throws NoSuchElementException if the index of the seat doesn't exist
   */
  private int electionIndex(String seatName) {
    for (int i = 0; i < elections.size(); i++) {
      if (elections.get(i).SEAT_NAME.equals(seatName)) {
        return i;
      }
    }

    throw new NoSuchElementException("Can't find the index with the seat name");
  }

  /**
   * Gives one vote to the candidate of whatever election the seatName is at and counts that this
   * ballot has already voted in that specific election
   * 
   * @param String    seatName that is the name of the election that we're trying to vote in
   * @param Candidate candidate is the name of the candidate that we're trying to vote for
   * @throws IllegalStateException if the ballot has already voted for the election
   */
  public void vote(String seatName, Candidate candidate) {
    int index = electionIndex(seatName);
    if (hasVoted[index]) {
      throw new IllegalStateException("This ballot has already voted in the given election");
    }

    Election election = elections.get(index);
    election.vote(candidate);
    hasVoted[index] = true;
  }

  /**
   * empties the elections arrayList and resets ballotsCreated, for testing purposes only
   */
  public static void clearElections() {
    ballotsCreated = false;
    elections.clear();
  }
}

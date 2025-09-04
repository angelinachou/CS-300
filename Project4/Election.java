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
// Online Sources: https://www.naukri.com/code360/library/methods-to-print-new-line-in-java
// Reminded me how the new line syntax worked
// https://www.javatpoint.com/string-concatenation-in-java
// Helped me when I was struggling with making the toString for loop
//
///////////////////////////////////////////////////////////////////////////////

import java.util.NoSuchElementException;

/**
 * This class creates the Election object that has functionalities like creating an election, adding
 * and removing candidates to the election, voting, and finding the winner of the election.
 */
public class Election extends Object {
  private Candidate[] candidates;
  private int numCandidates;
  public final String SEAT_NAME;

  /**
   * Initializes the oversize array for this election's candidates and sets the name of the position
   * for which this election is being held.
   * 
   * @param String seatName is the name of this election's position
   * @param int    maxCandidates is the maximum number of candidates permitted to run in this
   *               election
   * @throws IllegalArgumentException if the maximum number of candidates is not strictly greater
   *                                  than 0
   */
  public Election(String seatName, int maxCandidates) {
    SEAT_NAME = seatName;
    if (maxCandidates <= 0) {
      throw new IllegalArgumentException("Max candidates must be greater than zero");
    } else {
      candidates = new Candidate[maxCandidates];
    }
  }

  /**
   * Adds a candidate to the END of this election's list. Do NOT maintain the candidate list in
   * alphabetical order this time.
   * 
   * @param Candidate candidate is the Candidate to add to this election
   * @throws IllegalArgumentException if this candidate is already present in the candidates list
   *                                  for this election
   */
  public void addCandidate(Candidate candidate) {
    for (int i = 0; i < candidates.length; i++) {
      if (candidates[i] == null) {
        candidates[i] = candidate;
        numCandidates++;
        break;
      }
      if (candidates[i].equals(candidate)) {
        throw new IllegalArgumentException("Candidate already added!");
      }
    }
  }

  /**
   * Accessor method for the maximum number of candidates in this Election. This can be calculated
   * without adding additional instance fields!
   * 
   * @return the maximum number of candidates permitted to run in this election
   */
  public int capacity() {
    return candidates.length;
  }

  /**
   * Determines whether a provided object is equivalent to this Election. If anObject is not an
   * Election at all, they are not equal.
   * 
   * @Override equals from the class object
   * @param Object anObject is the object to compare this Election against
   * @return true if the given object represents a Election equivalent to this election, false
   *         otherwise.
   */
  public boolean equals(Object anObject) {
    if (anObject instanceof Election) {
      Election e = (Election) anObject;
      if (e.SEAT_NAME.equalsIgnoreCase(this.SEAT_NAME)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns a reference to the Candidate receiving more than 50% of the votes in this election
   * 
   * @return the Candidate with >50% of the votes across this election's candidates
   * @throws IllegalStateException  if the candidates list is empty
   * @throws NoSuchElementException if no one candidate has more than 50% of the votes (P01's
   *                                "contingent" election)
   */
  public Candidate findWinner() {
    if (numCandidates == 0) {
      throw new IllegalStateException("Candidate list is empty");
    }
    int totalVotes = 0;
    int maxVotes = candidates[0].getNumVotes();
    int index = 0;
    for (int i = 0; i < candidates.length; i++) {
      totalVotes += candidates[i].getNumVotes();

      if (candidates[i].getNumVotes() > maxVotes) {
        maxVotes = candidates[i].getNumVotes();
        index = i;
      }
    }

    double winPercent =
        Double.valueOf(candidates[index].getNumVotes()) / Double.valueOf(totalVotes);
    // not sure about this one
    if (winPercent > 0.5) {
      return candidates[index];
    }
    throw new NoSuchElementException("No one candidate has more than 50% of the votes.");
  }

  /**
   * Accessor method for the current number of candidates in this Election
   * 
   * @return the number of candidates currently running in this election
   */
  public int getNumCandidates() {
    return numCandidates;
  }

  /**
   * Removes the candidate matching the provided candidate exactly from the election's list of
   * candidates
   * 
   * @param the candidate to remove
   * @throws IllegalStateException  if you try to remove from an empty candidates list
   * @throws NoSuchElementException if the given Candidate is not present in this election's list of
   *                                candidates
   */
  public void removeCandidate(Candidate candidate) {
    if (numCandidates == 0) {
      throw new IllegalStateException("Array is empty");
    }

    boolean found = false;

    for (int i = 0; i < candidates.length; i++) {
      if (candidate.equals(candidates[i])) {
        found = true;
        for (int k = i; k < candidates.length - 1; k++) {
          candidates[k] = candidates[k + 1];
        }
        candidates[numCandidates - 1] = null;
        numCandidates--;
        break;
      }
    }
    if (!found) {
      throw new NoSuchElementException("Element not found within array");
    }
  }

  /**
   * Creates and returns a String representation of this Election object, with each Candidate's
   * string representation on a separate line.
   * 
   * @return the String representation of the current state of this Election
   */
  public String toString() {
    String s = SEAT_NAME;
    for (int i = 0; i < numCandidates; i++) {
      s = s + "\n" + candidates[i].toString();
    }
    return s;
  }

  /**
   * Increases the vote count of the given candidate by one
   * 
   * @param Candidate candidate to vote for
   * @throws NoSuchElementException if the given candidate is not present in this election
   */
  public void vote(Candidate candidate) {
    boolean found = false;
    for (int i = 0; i < candidates.length; i++) {
      if (candidate.equals(candidates[i])) {
        found = true;
        candidates[i].addVote();
      }
    }

    if (!found) {
      throw new NoSuchElementException("Given candidate is not present in this election.");
    }
  }
}

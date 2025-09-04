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
// Online Sources: https://www.educative.io/answers/what-is-the-printstacktrace-method-in-java
// Helped me understand what the printStackTrace() method was doing
//
///////////////////////////////////////////////////////////////////////////////

import java.util.NoSuchElementException;

/**
 * A tester class for the Election Manager project. It contains various tests to check the
 * correctness of the Candidate, Election, and Ballot classes.
 *
 */
public class ElectionManagerTester {

  /**
   * Tests the Candidate constructor, toString(), and getter method for correctness. This test
   * accounts for the fact that a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testCandidateConstructorAndGetters() {

    // in case we get an unexpected exception from a broken implementation
    // we handle it with a try-catch to avoid our tester from crashing
    try {
      // test the 2-argument constructor
      Candidate c = new Candidate("lebron james", "basketball");

      // check that the instance data fields have been initialized correctly
      // using the toString to do this we are also checking its correctness!
      // in a bad implementation either:
      // 1) the constructor didn't intialize a data field correctly OR
      // 2) toString() doesn't return the correct value
      if (!c.toString().equals("lebron james (basketball): 0"))
        return false;

      // let's also verify the one getter method agrees with the toString() output:
      if (c.getNumVotes() != 0)
        return false;

    } catch (Exception e) {
      // we encountered an exception when we should not have, it is a bad implementation!
      e.printStackTrace();
      return false;
    }

    // all tests pass:
    return true;
  }

  /**
   * Verifies that the Candidate constructor throws the correct type of exception(s) where an
   * exception is expected. See the Candidate documentation for details.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testCandidateConstructorExceptions() {
    // testing when name is null
    try {
      new Candidate(null, "Water");
      return false; // this line only runs if NO exception is thrown from the previous line
    } catch (IllegalArgumentException e) {
      // this is correct
    } catch (Exception e) {
      // this only runs if an exception other than IllegalArgumentException was thrown,
      // which is wrong!
      e.printStackTrace();
      return false;
    }

    // testing when name is empty
    try {
      new Candidate("", "Water");
      return false; // this line only runs if NO exception is thrown from the previous line
    } catch (IllegalArgumentException e) {
      // this is correct
    } catch (Exception e) {
      // this only runs if an exception other than IllegalArgumentException was thrown,
      // which is wrong!
      e.printStackTrace();
      return false;
    }

    // testing when party is null
    try {
      new Candidate("Wooper", null);
      return false; // this line only runs if NO exception is thrown from the previous line
    } catch (IllegalArgumentException e) {
      // this is correct
    } catch (Exception e) {
      // this only runs if an exception other than IllegalArgumentException was thrown,
      // which is wrong!
      e.printStackTrace();
      return false;
    }

    // testing when party is empty
    try {
      new Candidate("Wooper", "");
      return false; // this line only runs if NO exception is thrown from the previous line
    } catch (IllegalArgumentException e) {
      // this is correct
    } catch (Exception e) {
      // this only runs if an exception other than IllegalArgumentException was thrown,
      // which is wrong!
      e.printStackTrace();
      return false;
    }

    // all tests pass;
    return true;
  }

  /**
   * Tests the Election constructor and associated getter methods for correctness. (Note that
   * SEAT_NAME is a publicly-accessible constant and can be verified directly.) This test accounts
   * for the fact a bad implementation may throw an Exception.
   * 
   * @return true if all tests pass, false otherwise
   */

  public static boolean testElectionConstructorAndGetters() {
    // in case we get an unexpected exception from a broken implementation
    // we handle it with a try-catch to avoid our tester from crashing
    try {
      // test the 2-argument constructor
      Election e = new Election("Sportsball", 10);

      // check that the instance data fields have been initialized correctly
      // using the toString to do this we are also checking its correctness!
      // in a bad implementation either:
      // 1) the constructor didn't intialize a data field correctly OR
      // 2) toString() doesn't return the correct value
      if (!e.toString().equals("Sportsball"))
        return false;

      // let's also verify the getNumCandidates() method agrees with the toString() output:
      if (e.getNumCandidates() != 0)
        return false;

      // let's also verify that SEAT_NAME is correct
      if (!e.SEAT_NAME.equals("Sportsball"))
        return false;

      // let's also verify that capacity() method is correct
      if (e.capacity() != 10)
        return false;

    } catch (Exception e) {
      // we encountered an exception when we should not have, it is a bad implementation!
      e.printStackTrace();
      return false;
    }

    // all tests pass:
    return true;
  }

  /**
   * Verifies that the Election constructor throws the correct type of exception(s) in situations
   * where an exception is expected. See the Election documentation for details.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testElectionConstructorExceptions() {
    try {
      new Election("Sportsball", -1);
      return false; // this line only runs if NO exception is thrown from the previous line
    } catch (IllegalArgumentException e) {
      // this is correct
    } catch (Exception e) {
      // this only runs if an exception other than NoSuchElementException was thrown,
      // which is wrong!
      e.printStackTrace();
      return false;
    }

    // all tests pass:
    return true;
  }

  /**
   * Tests the Election's addCandidate() method for correctness in non-Exception situations. This
   * test accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testAddCandidate() {
    Election election = null;
    try {
      election = new Election("Sportsball", 10);
      election.addCandidate(new Candidate("Lebron James", "basketball"));
      election.addCandidate(new Candidate("Messi", "soccer"));
      String s = "Sportsball\nLebron James (basketball): 0\nMessi (soccer): 0";
      if (!election.toString().equals(s)) {
        return false;
      }
    } catch (Exception e) {
      System.out.println("Unable to continue with this test for unrelated reasons!!");
      e.printStackTrace();
      return false;
    }
    // worked correctly
    return true;
  }

  /**
   * Verifies that the Election's addCandidate() method throws the correct type of exception(s) in
   * situations where an exception is expected. See the Election documentation for details.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testAddCandidateExceptions() {
    // setup
    Election election = null; // declare outside of the try block for scope reasons
    try {
      election = new Election("Sportsball", 10);
      Candidate c = new Candidate("lebron james", "basketball");
      election.addCandidate(c);
    } catch (Exception e) {
      System.out.println("Unable to continue with this test for unrelated reasons!!");
      e.printStackTrace();
      return false;
    }

    // actual test
    try {
      election.addCandidate(new Candidate("lebron james", "basketball"));
      return false; // this line only runs if NO exception is thrown from the previous line
    } catch (IllegalArgumentException e) {
      // this is correct
    } catch (Exception e) {
      // this only runs if an exception other than NoSuchElementException was thrown,
      // which is wrong!
      e.printStackTrace();
      return false;
    }

    // all tests pass:
    return true;
  }

  /**
   * Tests the Election's vote() method for correctness in non-Exception situations. This test
   * accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testVote() {
    Election election = null; // declare outside of the try block for scope reasons
    try {
      election = new Election("Sportsball", 10);
      Candidate c1 = new Candidate("lebron james", "basketball");
      Candidate c2 = new Candidate("messi", "soccer");
      election.addCandidate(c1);
      election.addCandidate(c2);
      election.vote(c1);
      if (c1.getNumVotes() != 1)
        return false;
    } catch (Exception e) {
      System.out.println("Unable to continue with this test for unrelated reasons!!");
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * Verifies that the Election's vote() method throws the correct type of exception(s) in
   * situations where an exception is expected. See the Election documentation for details.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testVoteExceptions() {
    ////////////////////////////////////////////////////////////////////////////////////////
    // we're doing the setup separately, so we can isolate the actual test later.
    // if anything fails HERE, that's a different problem than the one we're trying to test,
    // and the test should fail.

    Election election = null; // declare outside of the try block for scope reasons
    try {
      election = new Election("Sportsball", 10);
      Candidate c1 = new Candidate("lebron james", "basketball");
      Candidate c2 = new Candidate("messi", "soccer");
      election.addCandidate(c1);
      election.addCandidate(c2);
    } catch (Exception e) {
      System.out.println("Unable to continue with this test for unrelated reasons!!");
      e.printStackTrace();
      return false;
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    // THIS part is what we are actually testing:

    try {
      election.vote(new Candidate("usain bolt", "athletics"));
      return false; // this line only runs if NO exception is thrown from the previous line
    } catch (NoSuchElementException e) {
      // this is correct
    } catch (Exception e) {
      // this only runs if an exception other than NoSuchElementException was thrown,
      // which is wrong!
      e.printStackTrace();
      return false;
    }

    // all tests pass:
    return true;

  }


  /**
   * Tests the Election's removeCandidate() method for correctness. This test accounts for the fact
   * a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testRemoveCandidate() {
    Election election = null; // declare outside of the try block for scope reasons
    try {
      election = new Election("Sportsball", 10);
      Candidate c1 = new Candidate("lebron james", "basketball");
      Candidate c2 = new Candidate("messi", "soccer");
      Candidate c3 = new Candidate("simone biles", "gymnastics");
      election.addCandidate(c1);
      election.addCandidate(c2);
      election.addCandidate(c3);

      election.removeCandidate(c2);

      String s = "Sportsball\nlebron james (basketball): 0\nsimone biles (gymnastics): 0";

      if (!election.toString().equals(s))
        return false;
    } catch (Exception e) {
      System.out.println("Unable to continue with this test for unrelated reasons!!");
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * Verifies that the Election's removeCandidate() method throws the correct type of exception(s)
   * in situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testRemoveCandidateExceptions() {

    Election election = null; // declare outside of the try block for scope reasons

    try {
      election = new Election("sportsball", 10);
      election.removeCandidate(new Candidate("usain bolt", "athletics"));
      return false;
    } catch (IllegalStateException e) {
      // this is correct
    } catch (Exception e) {
      // this only runs if an exception other than IllegalStateException was thrown,
      // which is wrong!
      e.printStackTrace();
      return false;
    }

    Election election2 = null;
    try {
      election2 = new Election("Sportsball", 10);
      Candidate c1 = new Candidate("lebron james", "basketball");
      Candidate c2 = new Candidate("messi", "soccer");
      election2.addCandidate(c1);
      election2.addCandidate(c2);
    } catch (Exception e) {
      System.out.println("Unable to continue with this test for unrelated reasons!!");
      e.printStackTrace();
      return false;
    }

    try {
      election2.removeCandidate(new Candidate("usain bolt", "athletics"));
      return false; // this line only runs if NO exception is thrown from the previous line
    } catch (NoSuchElementException e) {
      // this is correct
    } catch (Exception e) {
      // this only runs if an exception other than NoSuchElementException was thrown,
      // which is wrong!
      e.printStackTrace();
      return false;
    }

    // all tests pass:
    return true;
  }

  /**
   * Tests the Ballot two-phase setup process in non-Exception situations. This test accounts for
   * the fact that a bad implementation may throw an Exception.
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testBallotSetup() {
    // Phase 1: add elections to the Ballot class
    // Phase 2: create a Ballot and verify that it has the correct number of elections
    // (hint: use toString())
    Ballot.clearElections();

    try {
      Election e1 = new Election("Best Pokemon", 10);
      Election e2 = new Election("Basketball MVP", 3);
      Ballot.addElection(e1);
      Ballot.addElection(e2);

      Ballot ballot = new Ballot();

      String expected = "Best Pokemon: false\nBasketball MVP: false";

      if (!expected.equals(ballot.toString())) {
        return false;
      }
    } catch (Exception e) {
      System.out.println("Unable to continue with this test for unrelated reasons!!");
      e.printStackTrace();
      return false; // unexpected error
    }

    return true;
  }

  /**
   * Verifies that the Ballot two-phase setup process throws the correct type of exception(s) in
   * situations where an exception is expected. See the Ballot documentation for details.
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testBallotSetupExceptions() {
    Ballot.clearElections();

    try {
      Ballot ballot = new Ballot();
      return false; // shouldn't be reaching this point
    } catch (IllegalStateException e) {
      // this is correct
    } catch (Exception e) {
      // this only runs if an exception other than NoSuchElementException was thrown,
      // which is wrong!
      e.printStackTrace();
      return false;
    }

    try {
      Ballot.clearElections();
      Election e1 = new Election("Best Pokemon", 10);
      Ballot.addElection(e1);
      Ballot ballot2 = new Ballot();

      Election e2 = new Election("Basketball MVP", 3);
      Ballot.addElection(e2);
      return false; // should not reach this point
    } catch (IllegalStateException e) {
      // this is correct
    } catch (Exception e) {
      // this only runs if an exception other than NoSuchElementException was thrown,
      // which is wrong!
      e.printStackTrace();
      return false;
    }

    try {
      Ballot.clearElections();
      Election e1 = new Election("Best Pokemon", 10);
      Election e2 = new Election("Best Pokemon", 10);
      Ballot.addElection(e1);
      Ballot.addElection(e2);
      return false; // should not reach this point
    } catch (IllegalArgumentException e) {
      // this is correct
    } catch (Exception e) {
      // this only runs if an exception other than IllegalArgumentException was thrown,
      // which is wrong!
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * Tests the Ballot vote() and hasVoted() methods in non-Exception situations. This test accounts
   * for the fact that a bad implementation may throw an Exception.
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testBallotVote() {
    Ballot.clearElections();
    try {
      Election e1 = new Election("Basketball MVP", 3);
      Candidate c1 = new Candidate("Lebron James", "Basketball");
      Candidate c2 = new Candidate("Steph Curry", "Basketball");
      Candidate c3 = new Candidate("Kevin Durant", "Basketball");
      e1.addCandidate(c1);
      e1.addCandidate(c2);
      e1.addCandidate(c3);

      Election e2 = new Election("Best Pokemon", 10);
      Ballot.addElection(e1);
      Ballot.addElection(e2);

      Ballot ballot = new Ballot();
      ballot.vote(e1.SEAT_NAME, c2);

      if (!ballot.hasVoted("Basketball MVP")) {
        return false;
      }

      String actual = "Basketball MVP: true\nBest Pokemon: false";
      return actual.equals(ballot.toString());

    } catch (Exception e) {
      // this only runs if an exception was thrown, which is wrong!
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Verifies that the Ballot vote() and hasVoted() methods throw the correct type of exception(s)
   * in situations where an exception is expected. See the Ballot documentation for details.
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testBallotVoteExceptions() {
    Ballot.clearElections();
    try {
      // catching if ballot has already voted in the given election
      Election e1 = new Election("Basketball MVP", 3);
      Candidate c1 = new Candidate("Lebron James", "Basketball");
      Candidate c2 = new Candidate("Steph Curry", "Basketball");
      Candidate c3 = new Candidate("Kevin Durant", "Basketball");
      e1.addCandidate(c1);
      e1.addCandidate(c2);

      Election e2 = new Election("Best Pokemon", 10);
      Ballot.addElection(e1);
      Ballot.addElection(e2);

      Ballot ballot = new Ballot();
      ballot.vote(e1.SEAT_NAME, c2);
      ballot.vote(e1.SEAT_NAME, c1);

    } catch (IllegalStateException e) {
      // this is correct
    } catch (Exception e) {
      // this only runs if an exception was thrown, which is wrong!
      e.printStackTrace();
      return false;
    }

    // catching if ballot vote doesn't correspond to any election on ballot
    Ballot.clearElections();
    try {
      // catching if ballot has already voted in the given election
      Election e1 = new Election("Basketball MVP", 3);
      Candidate c1 = new Candidate("Lebron James", "Basketball");
      Candidate c2 = new Candidate("Steph Curry", "Basketball");
      Candidate c3 = new Candidate("Kevin Durant", "Basketball");
      e1.addCandidate(c1);
      e1.addCandidate(c2);

      Election e2 = new Election("Best Pokemon", 10);
      Ballot.addElection(e1);
      Ballot.addElection(e2);

      Ballot ballot = new Ballot();
      ballot.vote(e1.SEAT_NAME, c3);

    } catch (NoSuchElementException e) {
      // this is correct
    } catch (Exception e) {
      // this only runs if an exception was thrown, which is wrong!
      e.printStackTrace();
      return false;
    }

    // catching if given seat name doesn't correspond to any election on ballot
    Ballot.clearElections();
    try {
      // catching if ballot has already voted in the given election
      Election e1 = new Election("Basketball MVP", 3);
      Candidate c1 = new Candidate("Lebron James", "Basketball");
      Candidate c2 = new Candidate("Steph Curry", "Basketball");
      Candidate c3 = new Candidate("Kevin Durant", "Basketball");
      e1.addCandidate(c1);
      e1.addCandidate(c2);

      Election e2 = new Election("Best Pokemon", 10);
      Ballot.addElection(e1);

      Ballot ballot = new Ballot();
      ballot.hasVoted(e2.SEAT_NAME);

    } catch (NoSuchElementException e) {
      // this is correct
    } catch (Exception e) {
      // this only runs if an exception was thrown, which is wrong!
      e.printStackTrace();
      return false;
    }


    return true;
  }

  /**
   * Runs all testing methods and prints out their results.
   * 
   * @return true if and only if all the tests return true, false otherwise
   */
  public static boolean runAllRequiredTests() {

    boolean test1 = testCandidateConstructorAndGetters();
    System.out.println("testCandidateConstructorAndGetters(): " + (test1 ? "PASS" : "FAIL"));

    boolean test2 = testCandidateConstructorExceptions();
    System.out.println("testCandidateConstructorExceptions(): " + (test2 ? "PASS" : "FAIL"));

    boolean test3 = testElectionConstructorAndGetters();
    System.out.println("testElectionConstructorAndGetters(): " + (test3 ? "PASS" : "FAIL"));

    boolean test4 = testElectionConstructorExceptions();
    System.out.println("testElectionConstructorExceptions(): " + (test4 ? "PASS" : "FAIL"));

    boolean test5 = testAddCandidate();
    System.out.println("testAddCandidate(): " + (test5 ? "PASS" : "FAIL"));

    boolean test6 = testAddCandidateExceptions();
    System.out.println("testAddCandidateExceptions(): " + (test6 ? "PASS" : "FAIL"));

    boolean test7 = testVote();
    System.out.println("testVote(): " + (test7 ? "PASS" : "FAIL"));

    boolean test8 = testVoteExceptions();
    System.out.println("testVoteExceptions(): " + (test8 ? "PASS" : "FAIL"));

    boolean test9 = testRemoveCandidate();
    System.out.println("testRemoveCandidate(): " + (test9 ? "PASS" : "FAIL"));

    boolean test10 = testRemoveCandidateExceptions();
    System.out.println("testRemoveCandidateExceptions(): " + (test10 ? "PASS" : "FAIL"));

    boolean test11 = testBallotSetup();
    System.out.println("testBallotSetup(): " + (test11 ? "PASS" : "FAIL"));

    boolean test12 = testBallotSetupExceptions();
    System.out.println("testBallotSetupExceptions(): " + (test12 ? "PASS" : "FAIL"));

    boolean test13 = testBallotVote();
    System.out.println("testBallotVote(): " + (test13 ? "PASS" : "FAIL"));

    boolean test14 = testBallotVoteExceptions();
    System.out.println("testBallotVoteExceptions(): " + (test14 ? "PASS" : "FAIL"));

    return test1 && test2 && test3 && test4 && test5 && test6 && test7 && test8 && test9 && test10
        && test11 && test12 && test13 && test14;
  }

  /**
   * Calls runAllRequiredTests and displays the output. If you add additional private testers, call
   * them directly in the main method rather than adding them to the previous method.
   * 
   * @param args unused
   */
  public static void main(String[] args) {
    System.out.println("runAllRequiredTests(): " + runAllRequiredTests());
  }


}

//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Election Manager Tester
// Course:   CS 300 Fall 2024
//
// Author:   Angelina Chou
// Email:    apchou@wisc.edu
// Lecturer: Blerina Gkotse
//
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Online Sources: These two sources reminded me how to convert int into string and vise versa: 
//                 https://www.javatpoint.com/java-int-to-string
//                 https://www.javatpoint.com/java-string-to-int
//
//                 Helped me realize why I kept getting Null Pointer Exception:
//                 https://www.upwork.com/resources/what-is-null-in-java
//                 https://bito.ai/resources/java-initialize-empty-array-java-explained/#:
//                 ~:text=In%20Java%2C%20you%20can%20use,store%20integers%20that%20holds%20nothing.
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Arrays;

/**
 * This class is used to test five different methods from the ElectionManager class through 
 * various test cases.
 */
public class ElectionManagerTester {
    
  /**
   * Tests if containsCandidate() works properly when there are no candidates in an array
   * <p>
   * @return true if the method works correctly and containsCandidate() returns false when trying 
   * to find a candidate in an empty array
   */
  public static boolean testContainsEmpty() {
    
    String[][] candidateList = {null, null, null, null, null, null};
    String[][] candidateCopy = Arrays.copyOf(candidateList, candidateList.length);
    int size = 0;
    String targetName = "Wooper";
    String targetParty = "Water";
    boolean expected = false;
    
    boolean actual = ElectionManager.containsCandidate(candidateList, size, 
        targetName, targetParty);
    
    if (expected != actual) return false;
    
    if (!Arrays.deepEquals(candidateList, candidateCopy)) return false;
    
    return true;
    
  }
  
  /**
   * Tests if containsCandidate() works properly when a candidate name and party that doesn't 
   * exist is passed through the method
   * <p>
   * @return true if the method works correctly and containsCandidate() returns false when trying 
   * to find a candidate in the array that doesn't contain those values
   */
  public static boolean testDoesNotContain() {
    
    String[][] candidateList = {
        {"Slowpoke", "Water", "3"},
        {"Squirtle", "Water", "127"},
        {"Wooper", "Water", "300"},
        null, null, null};
    
    String[][] candidateCopy = Arrays.copyOf(candidateList, candidateList.length);
    int size = 3;
    String targetName = "Wooper";
    String targetParty = "Fire";
    boolean expected = false;
    
    boolean actual = 
        ElectionManager.containsCandidate(candidateList, size, targetName, targetParty);
    
    if (expected != actual) return false;
    
    if (!Arrays.deepEquals(candidateList, candidateCopy)) return false;
    
    return true;
    
  }
  
  /**
   * PROVIDED TESTER METHOD: example test method for verifying whether a candidate has
   * already been added to the race.
   * 
   * NOTE: This method ONLY tests scenarios where the candidate IS PRESENT in the list;
   * situations where the candidate is not present or the list is empty should be
   * tested in the other contains tester methods.
   * 
   * @return false if any of the scenarios we test have results other than what we expect;
   * true ONLY if all of our expectations are met by the method we are testing
   */
  public static boolean testDoesContain() { 
    
    // (1a) set up the test variables
    String[][] candidateList = {
        {"Slowpoke", "Water", "3"},
        {"Squirtle", "Water", "127"},
        {"Wooper", "Water", "300"},
        null, null, null};
    String[][] candidateCopy = Arrays.copyOf(candidateList, candidateList.length);
    int size = 3;
    String targetName = "Wooper";
    String targetParty = "Water";
    boolean expected = true;
    
    // (1b) call the method we are testing
    boolean actual = 
        ElectionManager.containsCandidate(candidateList, size, targetName, targetParty);
    
    // (2) verify that the expected method return value and the actual return value match
    if (expected != actual) return false;
    
    // (3) since THIS method should not modify the array, check it against a copy we made
    if (!Arrays.deepEquals(candidateList, candidateCopy)) return false;
    
    // (4) if we have not yet returned false, we can now return true as all tests have passed!
    return true;
    
  }
    
  /**
   * Tests if addCandidate() works properly when there are no candidates in the array yet
   * <p>
   * @return true if the method works correctly and adds the candidate's name, party, and votes
   * to the empty array. Returns false if any of the scenarios don't work as expected. 
   */
  public static boolean testAddToEmpty() {
    
    String[][] candidateList = {null, null, null};
    String newName = "Goldeen";
    String newParty = "Water";
    int newVotes = 5;
    
    String[][] expectedList = {
        {"Goldeen", "Water", "5"},
        null, null};  
    int size = 0;
    int expected = 1;
    
    int actual = 
        ElectionManager.addCandidate(candidateList, size, newName, newParty, newVotes);
    
    if (expected != actual) return false;
    
    if (!Arrays.deepEquals(candidateList, expectedList)) return false;
    
    return true;
    
  }
  
  /**
   * PROVIDED TESTER METHOD: example test method for verifying whether a new candidate has
   * been added correctly to the race.
   * 
   * @return false if any of the scenarios we test have results other than what we expect;
   * true ONLY if all of our expectations are met by the method we are testing
   */
  public static boolean testAddToNonEmpty() {
    
    // (1a) set up the test variables
    String[][] candidateList = {
        {"Slowpoke", "Water", "3"},
        {"Squirtle", "Water", "127"},
        {"Wooper", "Water", "300"},
        null, null, null};
    String newName = "Goldeen";
    String newParty = "Water";
    int newVotes = 5;
    
    String[][] expectedList = {
        {"Goldeen", "Water", "5"}, // alphabetically first, new candidate will be added here
        {"Slowpoke", "Water", "3"},
        {"Squirtle", "Water", "127"},
        {"Wooper", "Water", "300"},
        null, null};  // now only TWO null values in this length-6 array!
    int size = 3;
    int expected = 4;
    
    // (1b) call the method we are testing
    int actual = 
        ElectionManager.addCandidate(candidateList, size, newName, newParty, newVotes);
    
    // (2) verify that the expected method return value and the actual return value match
    if (expected != actual) return false;
    
    // (3) this method modifies the input array; verify that it was modified correctly
    if (!Arrays.deepEquals(candidateList, expectedList)) return false;
    
    // (4) if we have not yet returned false, we can now return true as all tests have passed!
    return true;
    
  }
  
  /**
   * Tests whether addCandidate() will run correctly and not add a candidate that already previously
   * existed within the candidateList array
   * <p>
   * @return false if any of the scenarios we test have results other than what we expect;
   * true ONLY if all of our expectations are met by the method we are testing
   */
  public static boolean testAddCandidateErrors() {
    
    String[][] candidateList = {
        {"Slowpoke", "Water", "3"},
        {"Squirtle", "Water", "127"},
        {"Wooper", "Water", "300"},
        null, null, null};
    String newName = "Slowpoke";
    String newParty = "Water";
    int newVotes = 3;
    
    String[][] expectedList = { 
        {"Slowpoke", "Water", "3"},
        {"Squirtle", "Water", "127"},
        {"Wooper", "Water", "300"},
        null, null, null};  
    int size = 3;
    int expected = 3;
    
    int actual = 
        ElectionManager.addCandidate(candidateList, size, newName, newParty, newVotes);
    
    if (expected != actual) return false;
    
    if (!Arrays.deepEquals(candidateList, expectedList)) return false;
    
    return true;
    
  }
  
  /**
   * Tests if addCandidate() works properly when program tries to add a new candidate in an 
   * already full array that cannot take any new values
   * <p>
   * @return true if the method works correctly and doesn't throw an error/try to add a new 
   * candidate to the full array. Returns false if any of the scenarios don't work as expected. 
   */
  public static boolean testAddToFull() {
    
    String[][] candidateList = {
        {"Slowpoke", "Water", "3"},
        {"Squirtle", "Water", "127"},
        {"Wooper", "Water", "300"}};
    String newName = "Goldeen";
    String newParty = "Water";
    int newVotes = 5;
    
    String[][] expectedList = { 
        {"Slowpoke", "Water", "3"},
        {"Squirtle", "Water", "127"},
        {"Wooper", "Water", "300"}};  
    int size = 3;
    int expected = 3;
    
    int actual = 
        ElectionManager.addCandidate(candidateList, size, newName, newParty, newVotes);
    
    if (expected != actual) return false;
    
    if (!Arrays.deepEquals(candidateList, expectedList)) return false;
    
    return true;
    
  }
  
  /**
   * Tests if dropCandidate() works properly when program tries to remove a candidate when there 
   * is only one candidate in the entire candidateList array
   * <p>
   * @return true if the method works correctly and properly removes the candidate from the array.
   * Returns false if any of the scenarios don't work as expected. 
   */
  public static boolean testDropOnlyCandidate() {
    
    String[][] candidateList = {
        {"Slowpoke", "Water", "3"}, null};
    String[][] candidateCopy = {null, null};
    String name = "Slowpoke";
    String party = "Water";
    int size = 1;
    int expected = 0;
    
    int actual =
        ElectionManager.dropCandidate(candidateList, size, name, party);
    
    if (expected != actual) return false;
    
    if (!Arrays.deepEquals(candidateList, candidateCopy)) return false;
    
    return true;
    
  }
  
  /**
   * Tests if dropCandidate() works properly when program tries to remove the first candidate 
   * from an array of candidates, candidateList
   * <p>
   * @return true if the method works correctly and properly removes the candidate from the array.
   * Returns false if any of the scenarios don't work as expected. 
   */
  public static boolean testDropFirstCandidate() {
    
    String[][] candidateList = {
        {"Slowpoke", "Water", "3"},
        {"Squirtle", "Water", "127"},
        {"Wooper", "Water", "300"},
        null, null, null};
    String[][] candidateCopy = {
        {"Squirtle", "Water", "127"},
        {"Wooper", "Water", "300"},
        null, null, null, null};
    String name = "Slowpoke";
    String party = "Water";
    int size = 3;
    int expected = 2;
    
    int actual =
        ElectionManager.dropCandidate(candidateList, size, name, party);
    
    if (expected != actual) return false;
    
    if (!Arrays.deepEquals(candidateList, candidateCopy)) return false;
    
    return true;
    
  }
  
  /**
   * PROVIDED TESTER METHOD: example test method for verifying whether trying to drop a
   * candidate who is not running in the race correctly has NO effect on the candidate list.
   * 
   * @return false if any of the scenarios we test have results other than what we expect;
   * true ONLY if all of our expectations are met by the method we are testing
   */
  public static boolean testDropCandidateNotRunning() {
    
    // (1a) set up the test variables
    String[][] candidateList = {
        {"Slowpoke", "Water", "3"},
        {"Squirtle", "Water", "127"},
        {"Wooper", "Water", "300"},
        null, null, null};
    String[][] candidateCopy = Arrays.copyOf(candidateList, candidateList.length);
    String name = "Goldeen";
    String party = "Water";
    int size = 3;
    int expected = 3;
    
    // (1b) call the method we are testing
    int actual =
        ElectionManager.dropCandidate(candidateList, size, name, party);
    
    // (2) verify that the expected method return value and the actual return value match
    if (expected != actual) return false;
    
    // (2a) sometimes you may want to REPEAT the process with slightly different variables:
    name = "Slowpoke";
    party = "Fire"; // try with a name that's present but a different PARTY; should still not drop
    actual = ElectionManager.dropCandidate(candidateList, size, name, party);
    if (expected != actual) return false;
    
    // (3) this scenario should NOT modify the input array; check it against a copy we made
    if (!Arrays.deepEquals(candidateList, candidateCopy)) return false;
    
    // (4) if we have not yet returned false, we can now return true as all tests have passed!
    return true;
    
  }
  
  /**
   * Tests if findWinner() works properly when candidateList only has one candidate, and therefore
   * only one choice for winner
   * <p>
   * @return true if the method works correctly and properly chooses the only candidate that can win
   * Returns false if any of the scenarios don't work as expected. 
   */
  public static boolean testUncontestedWinner() {
    
    String[][] candidateList = {
        {"Slowpoke", "Water", "3"},
        null, null, null};
    String[][] candidateCopy = Arrays.copyOf(candidateList, candidateList.length);
    String expectedName = "Slowpoke";
    String expectedParty = "(Water)";
    Double expectedVotePct = 100.0;
    int size = 1;
    
    String result = ElectionManager.findWinner(candidateList, size);
    
    String[] resultPieces = result.split(" "); // get the space-separated pieces of the string
    if (resultPieces.length != 4) return false; // incorrect formatting
    if (!resultPieces[3].endsWith("%")) return false; // no % at the end
    
    if (!resultPieces[0].equals(expectedName) || !resultPieces[1].equals(expectedParty))
      return false; 
    
    if (!resultPieces[2].equals("-")) return false; // forgot the "-" between party and %
    
    // vote percentage should be 100 as the only candidate, so confirm that
    double actualVotePct = Double.valueOf(resultPieces[3].substring(0,resultPieces[3].length()-1));
    if (expectedVotePct != actualVotePct) return false;
    
    // this scenario should NOT modify the input array; check it against a copy we made
    if (!Arrays.deepEquals(candidateList, candidateCopy)) return false;
    
    // if we have not yet returned false, we can now return true as all tests have passed!
    return true;
    
  }
  
  /**
   * Tests if findWinner() works properly when candidateList has a dominant candidate that is 
   * clearly the winner of the election.
   * <p>
   * @return true if the method works correctly and properly chooses the dominant candidate to win.
   * Returns false if any of the scenarios don't work as expected. 
   */
  public static boolean testClearWinner() {
    
    String[][] candidateList = {
        {"Slowpoke", "Water", "3"},
        {"Squirtle", "Water", "97"},
        {"Wooper", "Water", "300"},
        null, null, null};
    String[][] candidateCopy = Arrays.copyOf(candidateList, candidateList.length);
    String expectedName = "Wooper";
    String expectedParty = "(Water)";
    double expectedVotePct = 300.0/(300+97+3)*100;
    int size = 3;
    
    String result = ElectionManager.findWinner(candidateList, size);
    
    String[] resultPieces = result.split(" "); // get the space-separated pieces of the string
    if (resultPieces.length != 4) return false; // incorrect formatting
    if (!resultPieces[3].endsWith("%")) return false; // no % at the end
    
    if (!resultPieces[0].equals(expectedName) || !resultPieces[1].equals(expectedParty))
      return false; // wrong name or wrong party
    
    if (!resultPieces[2].equals("-")) return false; // forgot the "-" between party and %
    
    // do a range check on the calculated vote percentage, since it's not always going to come out
    // exactly the same:
    double actualVotePct = Double.valueOf(resultPieces[3].substring(0,resultPieces[3].length()-1));
    if (Math.abs(actualVotePct-expectedVotePct) > 0.01) return false;
    
    if (!Arrays.deepEquals(candidateList, candidateCopy)) return false;
    
    return true;
    
  }
  
  /**
   * Tests if findWinner() works properly when candidateList doesn't have a candidate with over 
   * 50% if the vote.
   * <p>
   * @return true if the method works correctly and returns CONTINGENT, showing that no candidate
   * had over 50% of the vote. Returns false if any of the scenarios don't work as expected. 
   */
  public static boolean testContingentElection() {
    
    String[][] candidateList = {
        {"Slowpoke", "Water", "100"},
        {"Squirtle", "Water", "250"},
        {"Wooper", "Water", "200"},
        null, null, null};
    String[][] candidateCopy = Arrays.copyOf(candidateList, candidateList.length);
    int size = 3;
    String expected = "CONTINGENT";
    
    String result = ElectionManager.findWinner(candidateList, size);
        
    if (expected != result) return false;
    if (!Arrays.deepEquals(candidateList, candidateCopy)) return false;
    
    return true;
    
  }
  
  /**
   * Tests if findLowestPollingCandidate() works properly when candidateList has only one candidate
   * and therefore the method should return UNCONTESTED rather than try to find the loser.
   * <p>
   * @return true if the method works correctly and identifies that there is only one candidate, and
   * returns UNCONTESTED. Returns false if any of the scenarios don't work as expected. 
   */
  public static boolean testUncontestedLowestPolling() {
    
    String[][] candidateList = {
        {"Slowpoke", "Water", "100"},
        null, null, null};
    String[][] candidateCopy = Arrays.copyOf(candidateList, candidateList.length);
    int size = 1;
    String expected = "UNCONTESTED";
    
    String result = ElectionManager.findLowestPollingCandidate(candidateList, size);
        
    if (expected != result) return false;
    if (!Arrays.deepEquals(candidateList, candidateCopy)) return false;
    
    return true;
    
  }
  
  /**
   * Tests if findLowestPollingCandidate() works properly when there is a clear lowest polling 
   * candidate.
   * <p>
   * @return true if the method works correctly and identifies the true lowest polling candidate. 
   * Returns false if any of the scenarios don't work as expected. 
   */
  public static boolean testLowestUniqueVoteCount() {
    
    String[][] candidateList = {
        {"Slowpoke", "Water", "97"},
        {"Squirtle", "Water", "5"},
        {"Wooper", "Water", "300"},
        null, null, null};
    String[][] candidateCopy = Arrays.copyOf(candidateList, candidateList.length);
    String expectedName = "Squirtle";
    String expectedParty = "(Water)";
    String expectedVote = "5";
    int size = 3;
    
    String result = ElectionManager.findLowestPollingCandidate(candidateList, size);
    String[] resultPieces = result.split(" "); // get the space-separated pieces of the string
    if (resultPieces.length != 4) return false;
    if (!resultPieces[0].equals(expectedName) || !resultPieces[1].equals(expectedParty))
      return false; // wrong name or wrong party
    
    if (!resultPieces[2].equals("-")) return false; 
    if (!resultPieces[3].equals(expectedVote)) return false;
    
    if (!Arrays.deepEquals(candidateList, candidateCopy)) return false;
    
    return true;
    
  }
  
  /**
   * Tests if findLowestPollingCandidate() works properly when there are two different candidates
   * with the same number of votes.
   * <p>
   * @return true if the method works correctly and identifies the lowest polling candidate who  
   * comes first alphabetically. Returns false if any of the scenarios don't work as expected. 
   */
  public static boolean testLowestVoteCountTied() {
    
    String[][] candidateList = {
        {"Slowpoke", "Water", "200"},
        {"Squirtle", "Water", "100"},
        {"Wooper", "Water", "100"},
        null, null, null};
    String[][] candidateCopy = Arrays.copyOf(candidateList, candidateList.length);
    String expectedName = "Squirtle";
    String expectedParty = "(Water)";
    String expectedVote = "100";
    int size = 3;
    
    String result = ElectionManager.findLowestPollingCandidate(candidateList, size);
    String[] resultPieces = result.split(" "); // get the space-separated pieces of the string
    if (resultPieces.length != 4) return false;
    if (!resultPieces[0].equals(expectedName) || !resultPieces[1].equals(expectedParty))
      return false; // wrong name or wrong party
    
    if (!resultPieces[2].equals("-")) return false; 
    if (!resultPieces[3].equals(expectedVote)) return false;
    
    if (!Arrays.deepEquals(candidateList, candidateCopy)) return false;
    
    return true;
    
  }
     
  /**
   * PROVIDED MAIN METHOD to manage the tester methods above.
   * 
   * We're getting a little esoteric here to take advantage of loops to keep the code short;
   * each pass through the loop could also be written as follows:
   * 
   *   boolean singleTest = testMethodCall();
   *   allPass &= singleTest;
   *   System.out.println("testMethodCall : " + singleTest);
   * 
   * @throws NoSuchMethodException if you spell a method name incorrectly
   * 
   * And a couple of other "checked" exceptions that should never happen with our usage here:
   * @throws IllegalAccessException
   * @throws java.lang.reflect.InvocationTargetException
   */
  public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, 
    java.lang.reflect.InvocationTargetException {
    
    boolean allPass = true, singlePass = true;
    String printFormat = "%-29s: %s\n";
    
    // NOTE TO STUDENTS: If you create any additional tests for any of the methods in
    // ElectionManager, add their names to the appropriate array below!
    String[] containsTests = {"testContainsEmpty", "testDoesNotContain", "testDoesContain"};
    String[] addTests = {"testAddToEmpty", "testAddToNonEmpty", "testAddCandidateErrors",
        "testAddToFull"};
    String[] dropTests = {"testDropOnlyCandidate", "testDropFirstCandidate", 
        "testDropCandidateNotRunning"};
    String[] winTests = {"testUncontestedWinner", "testClearWinner", "testContingentElection"};
    String[] lowTests = {"testUncontestedLowestPolling", "testLowestUniqueVoteCount", 
        "testLowestVoteCountTied"};
    
    String[][] testNames = {containsTests, addTests, dropTests, winTests, lowTests};
    
    // NOTE TO STUDENTS: this for-loop is moving through the method names we've added to the 2D 
    // array testNames and attempting to call methods with those names from this tester
    // (specifically line 286 here). See Java's reflection framework for more details!
    for (String[] testSet : testNames) {
      for (String name : testSet) {
        singlePass = (boolean) ElectionManagerTester.class.getDeclaredMethod(name).invoke(null);
        allPass &= singlePass;
        System.out.printf(printFormat, name, singlePass);
      }
      System.out.println();
    }
 
    System.out.println("ALL TESTS: "+allPass);

  }

}

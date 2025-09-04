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
// Online Sources: https://www.w3schools.com/java/ref_string_isempty.asp#:~:text=The%20isEmpty()
// %20method%20checks,)%2C%20and%20false%20if%20not.
// Helped me figure out how to check if a string had anything in it for the party or name
// https://rollbar.com/blog/how-to-throw-illegalargumentexception-in-java/
// Reminded me how to do exceptions
// https://howtodoinjava.com/java11/check-blank-string/#:~:text=isBlank()%20method%20to%20determine,
// been%20added%20in%20Java%2011.&text=Java%20String-,Learn%20to%20use%20String.,or%20contains%20
// only%20white%20spaces.
// Reminded me how to check if there's a blank string with just empty white space inside it.
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class creates the Candidate object that can add votes and figure out the number of votes for
 * each candidate
 */
public class Candidate extends Object {
  private String name;
  private int numVotes;
  private String party;

  /**
   * Constructor that assigns the name and party of the object and throws exceptions if the name or
   * party are blank or null
   * 
   * @param String name that gives the name of the Candidate
   * @param String party that gives the party of the Candidate
   * @throws IllegalArgumentException if the name is null, empty, or blank
   * @throws IllegalArgumentException if the party is null, empty, or blank
   */
  public Candidate(String name, String party) {
    if (name == null || name.isEmpty() || name.isBlank()) {
      throw new IllegalArgumentException("Name must exist.");
    } else {
      this.name = name;
    }

    if (party == null || party.isEmpty() || party.isBlank()) {
      throw new IllegalArgumentException("Party must exist.");
    } else {
      this.party = party;
    }
  }

  /**
   * Adds one vote for the candidate
   */
  public void addVote() {
    numVotes += 1;
  }

  /**
   * Checks if the parameter object is equal to the current Candidate object
   * 
   * @Override equals() from the Object class to now compare Candidate objects
   * @param Object anObject that will be compared to Candidate object
   * @return boolean that is true if the objects are equal and false if they are different
   */
  public boolean equals(Object anObject) {
    // Makes sure anObject is a Candidate object
    if (anObject instanceof Candidate) {
      // Checks if they are shallow copies of each other
      if (this == anObject) {
        return true;
      }

      Candidate a = (Candidate) anObject;
      // Checks if they are deep copies of each other
      return (this.name.equals(a.name)) && (this.numVotes == a.numVotes)
          && (this.party.equals(a.party));
    }
    return false;
  }

  /**
   * Constructor that finds the number of votes that the current candidate has
   * 
   * @return number of votes that the candidate has
   */
  public int getNumVotes() {
    return numVotes;
  }

  /**
   * Creates a string that shows the name, party, and number of votes of candidate
   * 
   * @Override toString function to create a custom toString that gives the candidate's name, party,
   *           and number of votes
   */
  public String toString() {
    return name + " (" + party + "): " + String.valueOf(numVotes);
  }
}

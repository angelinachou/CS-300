//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Election Manager
// Course:   CS 300 Fall 2024
//
// Author:   Angelina Chou
// Email:    apchou@wisc.edu
// Lecturer: Blerina Gkotse
//
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Online Sources: Reminded me how to iterate through 2D arrays for the containsCandidate class:
//                 https://www.codingrooms.com/blog/iterating-through-2d-array-java#:~:text=
//                 The%20first%20for%20loop%20loops,on%20to%20the%20next%20row.
//
//                 Taught me how to alphabetically sort strings:
//                 https://www.geeksforgeeks.org/
//                 java-program-to-sort-names-in-an-alphabetical-order/
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class contains five methods that are utilized to organize an election by adding, dropping, 
 * and confirming candidates while also being able to check who the highest polling and lowest 
 * polling candidates are 
 */
public class ElectionManager {
  
  /**
   * Checks whether the exact given candidate exists within the String candidates
   * <p>
   * @param candidates is the array that is being parsed through to see if the candidate exists
   * @param numCandidates shows the number of non-null candidates in the oversize array, candidates
   * @param name is the name of the candidate being looked for in the array candidates
   * @param party is the party of the candidate being looked for in the array candidates
   * @return true if the candidate exists in the array and false if the candidate doesn't exist
   */
  public static boolean containsCandidate(String[][] candidates, int numCandidates, String name, 
      String party) {
    
    int count = 0;
    for (int i = 0; i < numCandidates; i++) {
      if (candidates[i][0] == name) {
        // candidate's name matches with a name in the candidates array
        count++;
        if (candidates[i][1] == party) {
       // candidate's name and party matches with a name + party combo in the candidates array
          count ++;
        } else {
          // party does not match with one in candidates array, so the name doesn't matter
          count = 0;
        }
      }
    }
    
    return count == 2;
    
  }
  
  /**
   * Adds a candidate in sorted alphabetical order into array under these conditions: 1. the 
   * candidate doesn't already exist in the array 2. the candidate has zero or more votes 3. the 
   * oversize array has enough space left to add an additional candidate
   * <p>
   * @param candidates is the array that is being parsed through to see if a new candidate can be
   * feasibly added
   * @param numCandidates shows the number of non-null candidates in the oversize array, candidates
   * @param name is the name of the candidate that is potentially being added
   * @param party is the party of the candidate that is potentially being added
   * @param numVotes is the number of votes of the candidate that is potentially being added
   * @return the number of non-null candidates within the array candidates
   */
  public static int addCandidate(String[][] candidates, int numCandidates, String name, 
      String party, int numVotes) {
    
    // checks to make sure candidate isn't already in the array
    if (containsCandidate(candidates, numCandidates, name, party)) {
      return numCandidates;
    }
    
    // candidate must have a rational number of votes
    if (numVotes < 0) {
      return numCandidates;
    }
    
    // must be space to add an additional candidate
    if (numCandidates >= candidates.length) {
      return numCandidates;
    }
    
    int index = numCandidates;
    // gets rid of the null value that could cause null pointer exception
    candidates[numCandidates] = new String[] {"", "", ""};
    // looking for the alphabetically correct spot to insert the new candidate
    for (int i = 0; i < numCandidates; i++) {
      if (name.compareTo(candidates[i][0]) < 0) {
        index = i;
        // ends the search for the index of the new candidate 
        break;
      }
    }
    
    for (int i = numCandidates; i > index; i--) {
      // moves the rest of the candidates after the new candidate one spot over
      candidates[i][0] = candidates[i-1][0];
      candidates[i][1] = candidates[i-1][1];
      candidates[i][2] = candidates[i-1][2];
      
    }
    
    // adds the new candidate to the opened up spot
    candidates[index][0] = name;
    candidates[index][1] = party;
    candidates[index][2] = String.valueOf(numVotes);
    
    return numCandidates + 1;
    
  }
  
  /**
   * Removes a candidate if the candidate's name and party matches with one that is in 
   * the array candidates. Does not modify candidates if the given candidate does not exist in the 
   * array
   * <p>
   * @param candidates is the array that is being parsed through to drop a specified candidate
   * @param numCandidates shows the number of non-null candidates in the oversize array candidates
   * @param name is the name of the candidate that is potentially being removed
   * @param party is the party of the candidate that is potentially being removed
   * @return the number of non-null candidates within the array candidates
   */
  public static int dropCandidate(String[][] candidates, int numCandidates, String name, 
      String party) {
    
    // confirms that candidate actually exists in array
    if (!containsCandidate(candidates, numCandidates, name, party)) {
      return numCandidates;
    }
    
    // shifts back all the candidates after the dropped one over one spot
    for (int i = 0; i < numCandidates; i++) {
      if (candidates[i][0].equals(name) && candidates[i][1].equals(party)) {
        for (int j = i; j < numCandidates - 1; j++) {
          candidates[j][0] = candidates[j+1][0];
          candidates[j][1] = candidates[j+1][1];
          candidates[j][2] = candidates[j+1][2];
        }
        
        // makes the last candidate spot at the end null because there should be nobody occupying 
        // that space
        candidates[numCandidates-1] = null;
        return numCandidates-1;
      }
    }
    
    return numCandidates;
    
  }
  
  /**
   * Finds the candidate with the highest number of votes. If no candidate has over 50% of the 
   * votes, then the method returns "CONTINGENT"
   * <p>
   * @param candidates is the array that is being parsed through to find the winner
   * @param numCandidates shows the number of non-null candidates in the oversize array candidates
   * @return the name and party of the candidate that won, as well as the percentage with which 
   * they won. Returns "CONTINGENT" if nobody got more than 50% of the vote. 
   */
  public static String findWinner(String[][] candidates, int numCandidates) {
    
    double totalVotes = 0.0;
    double percent;
    String name = candidates[0][0];
    String party = candidates[0][1];
    
    // assigns the person with the most votes to the first person in the list until altered
    int mostVotes = Integer.valueOf(candidates[0][2]);
    // if a candidate has more votes, assigns most votes to that person
    for (int i = 0; i < numCandidates; i++) {
      totalVotes += Integer.valueOf(candidates[i][2]);
      if (Integer.valueOf(candidates[i][2]) > mostVotes) {
        name = candidates[i][0];
        party = candidates[i][1];
        mostVotes = Integer.valueOf(candidates[i][2]);
      }
    }
    
    // calculates the percentage of votes that the winning candidate has
    percent = (mostVotes/totalVotes)*100;
    if (percent <= 50) {
      return "CONTINGENT";
    }
    
    return name + " (" + party + ") - " + String.valueOf(percent) + "%";
    
  }
  
  /**
   * Finds the candidate with the lowest number of votes. If two given candidates have the same 
   * number of votes, whoever is higher alphabetically is the winner. If there is only 1 or 0 
   * candidates, the program automatically returns "UNCONTESTED"
   * <p>
   * @param candidates is the array that is being parsed through to find the loser
   * @param numCandidates shows the number of non-null candidates in the oversize array candidates
   * @return the name and party of the candidate that lost, as well as the number of votes that 
   * candidate received. Returns "UNCONTESTED" if there is 0 or 1 candidate 
   */
  public static String findLowestPollingCandidate(String[][] candidates, int numCandidates) {
    
    // makes sure that we're looking for a candidate in an election with more than one candidate
    if (numCandidates < 2 && numCandidates >= 0) {
      return "UNCONTESTED";
    }
    
    // identifies what candidate has the lowest number of votes and saves them in name, party
    // and least votes 
    String name = candidates[0][0];
    String party = candidates[0][1];
    int leastVotes = Integer.valueOf(candidates[0][2]);
    for (int i = 0; i < numCandidates; i++) {
      if (Integer.valueOf(candidates[i][2]) < leastVotes) {
        name = candidates[i][0];
        party = candidates[i][1];
        leastVotes = Integer.valueOf(candidates[i][2]);
      }
    }
    
    return name + " (" + party + ") - " + leastVotes;
    
  }

}


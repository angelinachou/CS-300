//////////////// P05 CABO //////////////////////////
//
// Title: P05 Cabo
// Course: CS 300 Fall 2024
//
// Author: Angelina Chou
// Email: apchou@wisc.edu
// Lecturer: Blerina Gkotse
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: (identify each by name and describe how they helped)
// Online Sources:
// - https://www.javacodegeeks.com/2022/01/java-arraylist-insert-replace-at-index.html
// #:~:text=Use%20the%20ArrayList.,index%20of%20ArrayList%20in%20java.
//
///////////////////////////////////////////////////////////////////////////////

/**
 * The ActionCard class extends BaseCard, and assigns the action type of a card to the BaseCard
 */
public class ActionCard extends BaseCard {
  private String actionType;

  /**
   * Constructs an ActionCard with the specified rank, suit, and action type.
   * 
   * @param rank       - the rank of the card (e.g., 1 for Ace, 13 for King).
   * @param suit       - the suit of the card (e.g., "Hearts", "Diamonds").
   * @param actionType - the type of action associated with this card: "peek", "spy", or "switch"
   */
  public ActionCard(int rank, String suit, String actionType) {
    super(rank, suit);
    this.actionType = actionType;
  }

  /**
   * Gets the type of action associated with this card.
   * 
   * @return the action type as a String: "peek", "spy", or "switch".
   */
  public String getActionType() {
    return actionType;
  }

}

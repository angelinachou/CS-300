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

import java.util.ArrayList;

/**
 * This class models a mini-Deck that each player holds - their "hand" of cards.
 */
public class Hand extends Deck {
  private final int HAND_SIZE = 4;

  /**
   * Creates a new empty deck for this hand
   */
  public Hand() {
    // initialize empty deck
    super(new ArrayList<>());
  }

  /**
   * Overrides Deck's addCard() method to prevent this player being dealt more than HAND_SIZE cards
   * 
   * @param card - the card to add to this hand
   * @throws IllegalStateException - if the player is already holding the maximum number of cards
   */
  @Override
  public void addCard(BaseCard card) {
    if (cardList.size() >= HAND_SIZE) {
      throw new IllegalStateException("Player is already holding the maximum number of cards.");
    }
    super.addCard(card);
  }

  /**
   * Replaces the card at the given index (assumed to be between 0 and (HAND_SIZE-1)) with the
   * provided card, and returns the card that was previously at that index.
   * 
   * @param newCard - the card to swap into this hand
   * @param index   - the index to place the new card at
   * @return the card that was previously at that index
   */
  public BaseCard swap(BaseCard newCard, int index) {
    BaseCard oldCard = cardList.get(index);
    // CITE: how to replace value in arrayList - java code geeks
    cardList.set(index, newCard);
    return oldCard;
  }

  /**
   * Switches a card in this hand with a card in the other hand.
   * 
   * @param myIndex    - the index of the card in this hand to switch
   * @param otherHand  - the other hand to switch cards with
   * @param otherIndex - the index of the card in the other hand to switch
   */
  public void switchCards(int myIndex, Hand otherHand, int otherIndex) {
    // replaces the card from my deck with the card from otherHand and saves the card from my deck
    // in newOtherHandCard
    BaseCard newOtherHandCard = swap(otherHand.cardList.get(otherIndex), myIndex);

    // replaces card from other deck with my card at the other card's index
    otherHand.swap(newOtherHandCard, otherIndex);
  }

  /**
   * Changes the face-up value of the card at the given index to the provided value
   * 
   * @param index  - the index of the card to change
   * @param faceUp - true if this card should be face-up, false if it should be face-down
   */
  public void setFaceUp(int index, boolean faceUp) {
    cardList.get(index).setFaceUp(faceUp);
  }

  /**
   * Draws the entire hand at the given y-coordinate. To calculate the x-coordinate of each card,
   * use (50 + 60*index).
   * 
   * @param y - the y-coordinate of the upper-left corner of all cards in this hand
   */
  public void draw(int y) {
    for (int i = 0; i < cardList.size(); i++) {
      // draws using the baseCard draw method
      cardList.get(i).draw(50 + (60 * i), y);
    }
  }

  /**
   * Checks if the mouse is currently over any of the cards in this hand, and returns the index of
   * any card which the mouse is over, or -1 if the mouse is not currently over any card in this
   * hand.
   * 
   * @return the index of a card in this hand which the mouse is over, or -1 if the mouse is not
   *         over any cards in this hand
   */
  public int indexOfMouseOver() {
    for (int i = 0; i < cardList.size(); i++) {
      if (cardList.get(i).isMouseOver()) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Accesses the rank of a card at a given index
   * 
   * @param index - the index of the card to access
   * @return the rank of the card at that index
   */
  public int getRankAtIndex(int index) {
    return cardList.get(index).getRank();
  }

  /**
   * Determines the total value of the cards in this hand, as a sum of the ranks of each of the
   * cards
   * 
   * @return the total value of this Player's hand
   */
  public int calcHand() {
    int value = 0;
    for (int i = 0; i < cardList.size(); i++) {
      value += getRankAtIndex(i);
    }
    return value;
  }

}

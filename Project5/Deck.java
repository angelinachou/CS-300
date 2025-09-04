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
// - https://www.geeksforgeeks.org/find-first-and-last-element-of-arraylist-in-java/
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.Collections;
import processing.core.PApplet;

/**
 * The Deck class represents a deck of playing cards for the game Cabo. It manages a collection of
 * cards, including shuffling, drawing, and adding cards.
 */
public class Deck {

  protected ArrayList<BaseCard> cardList;
  protected static processing.core.PApplet processing;

  /**
   * Constructs a new Deck based on the provided parameter. To create a full deck, pass in the
   * output of createDeck().
   * 
   * @param deck - starting list of cards for deck; should be either a full deck or an empty list.
   * @throws IllegalStateException if the Processing environment is not set before creating a deck.
   */
  public Deck(ArrayList<BaseCard> deck) {
    if (processing == null) {
      throw new IllegalStateException("Processing environment is not set before creating a deck");
    }

    cardList = deck;
  }

  /**
   * Sets the Processing environment to be used by the Deck class. This must be called before
   * creating a deck.
   * 
   * @param processing - the Processing environment to be used for drawing and interaction.
   */
  public static void setProcessing(processing.core.PApplet processing) {
    Deck.processing = processing;
  }

  /**
   * Draws a card from the top (end) of the deck.
   * 
   * @return the top card from the deck, or null if the deck is empty.
   */
  public BaseCard drawCard() {
    if (cardList.isEmpty()) {
      return null;
    }

    // CITE: how to draw the last card in arrayList - geeks for geeks
    BaseCard topCard = cardList.get(cardList.size() - 1);
    cardList.remove(cardList.size() - 1);

    return topCard;
  }

  /**
   * Adds a card to the top (end) of the deck.
   * 
   * @param card - the card to add to the deck.
   */
  public void addCard(BaseCard card) {
    cardList.add(card);
  }

  /**
   * Gets the current number of cards in the Deck.
   * 
   * @return the size of the deck.
   */
  public int size() {
    return cardList.size();
  }

  /**
   * Checks if the deck is empty.
   * 
   * @return true if the deck is empty, false otherwise.
   */
  public boolean isEmpty() {
    if (cardList.isEmpty()) {
      return true;
    }
    return false;
  }

  /**
   * Sets up the deck with CABO cards, including action cards. Initializes the deck with all
   * necessary cards and shuffles them.
   *
   * @return the completed ArrayList of CABO cards
   */
  public static ArrayList<BaseCard> createDeck() {
    ArrayList<BaseCard> cardList = new ArrayList<>();

    // Define the suits
    String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};

    // Cards from 1 (Ace) to 13 (King)
    for (int rank = 1; rank <= 13; ++rank) {
      // Loop through each suit
      for (String suit : suits) {
        if (rank >= 7 && rank <= 12) {
          // Special action cards
          String actionType = "";
          if (rank == 7 || rank == 8) {
            actionType = "peek";
          } else if (rank == 9 || rank == 10) {
            actionType = "spy";
          } else {
            actionType = "switch";
          }
          cardList.add(new ActionCard(rank, suit, actionType)); // Add ActionCard to deck
        } else {
          cardList.add(new BaseCard(rank, suit)); // Add NumberCard to deck
        }
      }
    }
    Collections.shuffle(cardList);
    return cardList;
  }

  /**
   * Draws the top card of the deck onto the Processing canvas at the specified position. If the
   * deck is empty, draws a placeholder indicating the deck is empty.
   *
   * @param x         - the x-coordinate to draw the card.
   * @param y         - the y-coordinate to draw the card.
   * @param isDiscard - whether the deck is a discard pile, in which case the top card should be
   *                  drawn face-up. Otherwise, the top card should be face-down.
   */
  public void draw(int x, int y, boolean isDiscard) {
    if (isEmpty()) {
      // Draw a black rectangle if the discard pile is empty
      processing.stroke(0);
      processing.fill(0);
      processing.rect(x, y, 50, 70, 7);
      processing.fill(255);
      processing.textSize(12);
      processing.textAlign(PApplet.CENTER, PApplet.CENTER);
      processing.text("Empty", x + 25, y + 35);
      // won't be making the baseCard and stuff like that
      return;
    }

    // creates BaseCard object based off the top card in deck
    BaseCard b = cardList.get(cardList.size() - 1);

    // sets BaseCard b to whatever face it is facing
    b.setFaceUp(isDiscard);

    // draws BaseCard b - doesn't need to do an if else statement on whether it is faceup or down
    // because it does it in BaseCard class
    b.draw(x, y);
  }

}

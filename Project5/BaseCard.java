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
// - https://stackoverflow.com/questions/22787032/java-static-variable-and-parameter-with-same-name
//
///////////////////////////////////////////////////////////////////////////////


import java.io.File;

/**
 * The BaseCard class represents a singular playing card for the game Cabo. It manages the creation
 * of the card, draws the card, and sees if the mouse is over the card.
 */
public class BaseCard {
  protected int rank;
  protected String suit;
  protected boolean faceUp;
  private int x;
  private int y;
  private final int WIDTH = 50;
  private final int HEIGHT = 70;
  protected static processing.core.PApplet processing;
  private processing.core.PImage cardImage;
  private static processing.core.PImage cardBack;

  /**
   * Constructs a new BaseCard with the specified rank and suit. The card is initialized to be face
   * down by default. You may assume that the provided rank and suit are valid. This method should
   * also initialize the cardImage, and initialize cardBack if that has not yet been done by any
   * other constructor call.
   * 
   * @param rank - the rank of the card (e.g., 1 for Ace, 13 for King).
   * @param suit - the suit of the card (e.g., "Hearts", "Diamonds").
   * @throws IllegalStateException if the Processing environment is not set before creating a deck.
   */
  public BaseCard(int rank, String suit) {
    if (processing == null) {
      throw new IllegalStateException("Processing environment has not been set.");
    }
    this.rank = rank;
    this.suit = suit;
    faceUp = false;
    cardImage = processing
        .loadImage("images" + File.separator + rank + "_of_" + suit.toLowerCase() + ".png");
    if (cardBack == null) {
      cardBack = processing.loadImage("images" + File.separator + "back.png");
    }
  }

  /**
   * Sets the Processing environment to be used for drawing and interacting with cards. This method
   * must be called before creating any BaseCard objects.
   * 
   * @param processing - the Processing PApplet environment.
   */
  public static void setProcessing(processing.core.PApplet processing) {
    // CITE: How to assign parameter to field variable when static: Stack Overflow
    BaseCard.processing = processing;
  }

  /**
   * Returns the rank of the card directly, or -1 if the card is the King of Diamonds
   * 
   * @return the rank of the card, or -1 for the King of Diamonds
   */
  public int getRank() {
    if ((rank == 13) && suit.equals("Diamonds")) {
      return -1;
    }
    return rank;
  }

  /**
   * Sets the face-up status of the card.
   * 
   * @param faceUp - if true, set the card face-up; if false, set it face-down.
   */
  public void setFaceUp(boolean faceUp) {
    this.faceUp = faceUp;
  }

  /**
   * Returns a string representation of the card, showing its suit and rank.
   * 
   * @return a string in the format "Suit Rank" (e.g., "Hearts 10").
   */
  @Override
  public String toString() {
    return rank + " " + String.valueOf(suit);
  }

  /**
   * Draws the card on the PApplet at the specified position.
   * 
   * @param xPosition - the x-coordinate to draw the card.
   * @param yPosition - the y-coordinate to draw the card.
   */
  public void draw(int xPosition, int yPosition) {
    x = xPosition;
    y = yPosition;

    processing.fill(255);
    processing.rect(xPosition, yPosition, WIDTH, HEIGHT);

    if (faceUp) {
      processing.image(cardImage, x, y, WIDTH, HEIGHT);
    } else {
      processing.image(cardBack, x, y, WIDTH, HEIGHT);
    }
  }

  /**
   * Checks if the mouse is currently over this card. Use PApplet's mouseX and mouseY fields to
   * determine where the mouse is; the (x,y) coordinates of this card's upper left corner were set
   * when it was last drawn.
   * 
   * @return true if the card is under the mouse's current position, false otherwise.
   */
  public boolean isMouseOver() {
    int min_x = x;
    int max_x = x + WIDTH;
    int min_y = y;
    int max_y = y + HEIGHT;

    int mx = processing.mouseX;
    int my = processing.mouseY;

    return (mx >= min_x && mx <= max_x && my >= min_y && my <= max_y);
  }
}

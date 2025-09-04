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
import processing.core.PApplet;

/**
 * The CaboGame class implements the main game logic for the card game CABO. It manages the deck,
 * discard pile, players, game state, and user interactions.
 */
public class CaboGame extends processing.core.PApplet {

  /**
   * Enum representing the different action states in the game (e.g., swapping cards, peeking,
   * spying, switching).
   * 
   * This allows us to easily restrict the possible values of a variable.
   */
  private enum ActionState {
    NONE, SWAPPING, PEEKING, SPYING, SWITCHING
  }

  private ActionState actionState = ActionState.NONE;
  private Deck deck;
  private Deck discard;
  private BaseCard drawnCard;
  private Player[] players;
  private int currentPlayer;
  private int caboPlayer;
  private Button[] buttons;
  private int selectedCardFromCurrentPlayer;
  private boolean gameOver;

  // provided data fields for tracking the players' moves through the game
  private ArrayList<String> gameMessages = new ArrayList<>();

  /**
   * Launch the game window; PROVIDED. Note: the argument to PApplet.main() must match the name of
   * this class, or it won't run!
   * 
   * @param args unused
   */
  public static void main(String[] args) {
    PApplet.main("CaboGame");
  }

  /**
   * Sets up the initial window size for the game; PROVIDED.
   */
  @Override
  public void settings() {
    size(1000, 800);
  }

  /**
   * Sets up the game environment, including the font, game state, and game elements.
   */
  @Override
  public void setup() {
    textFont(createFont("Arial", 16));
    // setProcessing for the classes which require it
    BaseCard.setProcessing(this);
    Deck.setProcessing(this);
    Button.setProcessing(this);
    deckCheck();

    // set up deck and discard pile
    deck = new Deck(Deck.createDeck());
    discard = new Deck(new ArrayList<>());
    drawnCard = null;

    // set up players array and deal their cards
    players = new AIPlayer[4];
    players[0] = new AIPlayer("Cyntra", 0, false);
    players[1] = new AIPlayer("Avalon", 1, true);
    players[2] = new AIPlayer("Balthor", 2, true);
    players[3] = new AIPlayer("Ophira", 3, true);
    currentPlayer = 0;
    caboPlayer = -1;
    selectedCardFromCurrentPlayer = -1;

    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        BaseCard b = deck.drawCard();
        players[j].addCardToHand(b);
      }
    }

    players[0].getHand().cardList.get(0).setFaceUp(true);
    players[0].getHand().cardList.get(1).setFaceUp(true);

    // set up buttons and update their states for the beginning of the game
    buttons = new Button[5];
    buttons[0] = new Button("Draw from Deck", 50, 700, 150, 40);
    buttons[1] = new Button("Swap a Card", 220, 700, 150, 40);
    buttons[2] = new Button("Declare Cabo", 390, 700, 150, 40);
    buttons[3] = new Button("Use Action", 390 + 170, 700, 150, 40);
    buttons[4] = new Button("End Turn", 390 + 170 + 170, 700, 150, 40);
    updateButtonStates();

    // update the gameMessages log: "Turn for "+currentPlayer.name
    setGameStatus("Turn for " + players[currentPlayer].getName());
  }

  /**
   * Console-only output for verifying the setup of the card objects and the deck containing them
   */
  public void deckCheck() {
    Deck testDeck = new Deck(Deck.createDeck());

    // verify that there are 52 cards in the deck
    System.out.println("Deck size is 52: " + String.valueOf(testDeck.size() == 52));

    // verify that there are 8 of each type of ActionCard
    int peek = 0;
    int spy = 0;
    int switchCount = 0;
    for (int i = 0; i < testDeck.size(); i++) {
      if (testDeck.cardList.get(i) instanceof ActionCard) {
        ActionCard a = (ActionCard) testDeck.cardList.get(i);
        String action = a.getActionType();
        if (action.equals("peek"))
          peek += 1;
        else if (action.equals("spy"))
          spy += 1;
        else if (action.equals("switch"))
          switchCount += 1;
      }
    }
    System.out.println(
        "Found correct number of action cards: " + (peek == 8 && spy == 8 && switchCount == 8));

    // verify that there are 13 of each suit
    int club = 0;
    int diamond = 0;
    int heart = 0;
    int spade = 0;
    for (int i = 0; i < testDeck.size(); i++) {
      String suit = testDeck.cardList.get(i).suit;
      if (suit.equals("Clubs"))
        club += 1;
      else if (suit.equals("Diamonds"))
        diamond += 1;
      else if (suit.equals("Hearts"))
        heart += 1;
      else if (suit.equals("Spades"))
        spade += 1;
    }
    System.out.println("Found correct number of each suit: "
        + (club == 13 && diamond == 13 && heart == 13 && spade == 13));

    // verify that the king of diamonds' getRank() returns -1
    for (int i = 0; i < testDeck.size(); i++) {
      if (testDeck.cardList.get(i).suit.equals("Diamonds") && testDeck.cardList.get(i).rank == 13) {
        if (testDeck.cardList.get(i).getRank() == -1) {
          System.out.println("King of Diamonds found!");
          break;
        }
      }
    }
  }

  /**
   * Updates the state of the action buttons based on the current game state. Activates or
   * deactivates buttons depending on whether it's the start of a player's turn, a card has been
   * drawn, or the player is an AI.
   */
  public void updateButtonStates() {
    // if the current player is a computer, deactivate all buttons
    if (players[currentPlayer].isComputer()) {
      for (int i = 0; i < buttons.length; i++) {
        buttons[i].setActive(false);
      }
    }
    // otherwise, if no card has been drawn, activate accordingly (see writeup)
    else if (drawnCard == null) {
      buttons[0].setActive(true);
      buttons[1].setActive(false);
      if (caboPlayer == -1)
        buttons[2].setActive(true);
      else
        buttons[2].setActive(false);
      buttons[3].setActive(false);
      buttons[4].setActive(false);
    }
    // otherwise, if a card has been drawn, activate accordingly (see writeup)
    else {
      buttons[0].setActive(false);
      buttons[1].setActive(true);
      buttons[2].setActive(false);
      if (drawnCard instanceof ActionCard) {
        buttons[3].setActive(true);
        String newLabel = ((ActionCard) drawnCard).getActionType();
        buttons[3].setLabel(newLabel.toUpperCase());
      } else
        buttons[3].setActive(false);
      buttons[4].setActive(true);
    }
  }

  /**
   * Renders the graphical user interface; also handles some game logic for the computer players.
   */
  @Override
  public void draw() {
    background(0, 128, 0);

    // draw the deck and discard pile
    textSize(16);
    fill(255);
    text("Deck:", 520, 60);;
    text("Discard Pile:", 644, 60);
    deck.draw(500, 80, false);
    discard.draw(600, 80, true);

    // draw the players' hands
    for (int i = 0; i < 4; i++) {
      drawHands(i);
    }

    // draw the buttons
    for (int i = 0; i < 5; i++) {
      if (!players[currentPlayer].isComputer()) {
        buttons[i].draw();
      }

      // show the drawn card, if there is one
      if (drawnCard != null) {
        drawnCard.setFaceUp(true);
        drawnCard.draw(500, 500);
      }

      // if the game is over, display the game over status
      if (gameOver) {
        displayGameOver();
        return;
      }

      // handle the computer players' turns
      if (players[currentPlayer].isComputer()) {
        for (int j = 0; j < 5; j++) {
          updateButtonStates();
          buttons[j].draw();
        }
      }
    }

    // Display game messages with different colors based on the content
    int y = 200; // Starting y-position for messages
    for (String message : gameMessages) {
      textSize(16);
      if (message.contains("CABO")) {
        fill(255, 128, 0);
      } else if (message.contains("switched")) {
        fill(255, 204, 153);
      } else if (message.contains("spied")) {
        fill(255, 229, 204);
      } else {
        fill(255);
      }
      text(message, width - 300, y); // Adjust x-position as needed
      y += 20; // Spacing between messages
    }

    if (players[currentPlayer].isComputer()) {
      performAITurn();
    }
  }

  private void drawHands(int index) {
    textSize(16);
    fill(255);
    text(players[index].getName(), 50, 45 + 150 * index);

    players[index].getHand().draw(60 + 150 * index);
  }

  /**
   * Handles mouse press events during the game. It manages user interactions with buttons (that is,
   * drawing a card, declaring CABO, swapping cards, using action cards) and updates the game state
   * accordingly.
   */
  @Override
  public void mousePressed() {
    // if game is over or it's the computer's turn, do nothing
    if (players[currentPlayer].isComputer() || gameOver == true) {
      return;
    }

    // handle button clicks
    int index;
    for (int i = 0; i < 5; i++) {
      if (buttons[i].isMouseOver() && buttons[i].isActive()) {
        if (i == 0)
          drawFromDeck();
        else if (i == 1) {
          actionState = ActionState.SWAPPING;
          setGameStatus("Click a card in your hand to swap it with the drawn card.");
        } else if (i == 2)
          declareCabo();
        else if (i == 3) {
          buttons[i].setLabel("Use Action");
          ActionCard a = (ActionCard) drawnCard;
          String action_type = a.getActionType();
          if (action_type.equals("peek")) {
            setGameStatus("Click a card in your hand to peek at it.");
            actionState = ActionState.PEEKING;
          } else if (action_type.equals("spy")) {
            setGameStatus("Click a card in another player's hand to spy on it.");
            actionState = ActionState.SPYING;
          } else if (action_type.equals("switch")) {
            setGameStatus(
                "Click a card from your hand, then a card from another Kingdom's hand to switch.");
            actionState = ActionState.SWITCHING;
          }
        } else if (i == 4) {
          nextTurn();
        }
      }
    }


    // handle additional action states (complete these methods)
    switch (actionState) {
      case SWAPPING -> handleCardSwap();
      case PEEKING -> handlePeek();
      case SPYING -> handleSpy();
      case SWITCHING -> handleSwitch();
      default -> {
        /* No action to be taken */ }
    }
  }

  ///////////////////////////////////// BUTTON CLICK HANDLERS /////////////////////////////////////

  /**
   * Handles the action of drawing a card from the deck. If the deck is empty, the game ends.
   * Otherwise, the drawn card is displayed in the middle of the table. The game status and button
   * states are updated accordingly.
   */
  public void drawFromDeck() {
    // if the deck is empty, game over
    if (deck.isEmpty()) {
      displayGameOver();
    }

    // otherwise, draw the next card from the deck
    else {
      drawnCard = deck.drawCard();
    }

    // update the gameMessages log: player.name+" drew a card."
    setGameStatus(players[currentPlayer].getName() + " drew a card.");

    // update the button states
    updateButtonStates();
  }

  /**
   * Handles the action of declaring CABO. Updates the game status to show that the player has
   * declared CABO.
   */
  public void declareCabo() {
    // update the gameMessages log: player.name+" declares CABO!"
    setGameStatus(players[currentPlayer].getName() + " declares CABO!");

    // set the caboPlayer to the current player's index
    caboPlayer = currentPlayer;

    // end this player's turn
    nextTurn();
  }

  ///////////////////////////////////// ACTION STATE HANDLERS /////////////////////////////////////

  /**
   * This method runs when the human player has chosen to SWAP the drawn card with one from their
   * hand. Detect if the mouse is over a card from the currentPlayer's hand and, if it is, swap the
   * drawn card with that card.
   * 
   * If the mouse is not currently over a card from the currentPlayer's hand, this method does
   * nothing.
   */
  public void handleCardSwap() {
    // find a card from the current player's hand that the mouse is currently over
    BaseCard b;
    int i;
    for (i = 0; i < 4; i++) {
      if (players[currentPlayer].getHand().cardList.get(i).isMouseOver()) {
        b = players[currentPlayer].getHand().cardList.get(i);
        break;
      }
    }
    if (i == 4) {
      return;
    }
    // swap that card with the drawnCard
    b = players[currentPlayer].getHand().swap(drawnCard, i);

    // add the swapped-out card from the player's hand to the discard pile
    discard.addCard(b);

    // update the gameMessages log: "Swapped the drawn card with card "+(index+1)+" in the
    // hand."
    setGameStatus("Swapped the drawn card with card " + (i + 1) + " in the hand.");

    // set the drawnCard to null and the actionState to NONE
    drawnCard = null;
    actionState = ActionState.NONE;

    // set all buttons except End Turn to inactive
    setInactive();

    // uncomment this code to erase all knowledge of the card at that index from the AI
    // (you may need to adjust its indentation and/or change some variables)
    AIPlayer AI;
    for (int j = 1; j < players.length; ++j) {
      AI = (AIPlayer) players[j];
      AI.setCardKnowledge(0, i, false);
    }

  }

  // private helper method to set everything except end turn to inactive
  private void setInactive() {
    for (int j = 0; j < 4; j++) {
      buttons[j].setActive(false);
    }
    buttons[4].setActive(true);
  }

  /**
   * Handles the action of peeking at one of your cards. The player selects a card from their own
   * hand, which is then revealed (set face-up).
   * 
   * If the mouse is not currently over a card from the currentPlayer's hand, this method does
   * nothing.
   */
  public void handlePeek() {
    // find a card from the current player's hand that the mouse is currently over
    BaseCard b;
    int i;
    for (i = 0; i < 4; i++) {
      if (players[currentPlayer].getHand().cardList.get(i).isMouseOver()) {
        b = players[currentPlayer].getHand().cardList.get(i);
        break;
      }
    }
    if (i == 4) {
      return;
    }

    // set that card to be face-up
    players[currentPlayer].getHand().cardList.get(i).setFaceUp(true);

    // update the gameMessages log: "Revealed card "+(index+1)+" in the hand."
    setGameStatus("Revealed card " + (i + 1) + " in the hand.");

    // add the drawnCard to the discard, set drawnCard to null and actionState to NONE
    discard.addCard(drawnCard);
    drawnCard = null;
    actionState = ActionState.NONE;

    // set all buttons except End Turn to inactive
    setInactive();
  }

  /**
   * Handles the spy action, allowing the current player to reveal one of another player's cards.
   * The current player selects a card from another player's hand, which is temporarily revealed.
   * 
   * If the mouse is not currently over a card from another player's hand, this method does nothing.
   */
  public void handleSpy() {
    // find a card from any player's hand that the mouse is currently over
    BaseCard b;
    int p;
    int i = 4;
    for (p = 0; p < 4; p++) {
      // skips current player
      if (p == currentPlayer) {
        continue;
      }
      for (i = 0; i < 4; i++) {
        if (players[p].getHand().cardList.get(i).isMouseOver()) {
          b = players[p].getHand().cardList.get(i);
          break;
        }
      }
    }
    if (i == 4) {
      return;
    }

    // if it is not one of their own cards, set it to be face-up
    players[p].getHand().cardList.get(i).setFaceUp(true);

    // update the gameMessages log: "Spied on "+player.name+"'s card.";
    setGameStatus("Spied on " + players[p].getName() + "'s card.");

    // add the drawnCard to the discard, set drawnCard to null and actionState to NONE
    discard.addCard(drawnCard);
    drawnCard = null;
    actionState = ActionState.NONE;

    // set all buttons except End Turn to inactive
    setInactive();
  }

  /**
   * Handles the switch action, allowing the current player to switch one of their cards with a card
   * from another player's hand.
   * 
   * This action is performed in 2 steps, in this order: (1) select a card from the current player's
   * hand (2) select a card from another player's hand
   * 
   * If the mouse is not currently over a card, this method does nothing.
   */
  public void handleSwitch() {
    // add CaboGame instance variable to store the index of the card from the currentPlayer's
    // hand
    // selectedCardFromCurrentPlayer added in class
    // check if the player has selected a card from their own hand yet
    // if they haven't: determine which card in their own hand the mouse is over & store it
    // and do nothing else
    if (selectedCardFromCurrentPlayer == -1) {
      selectedCardFromCurrentPlayer = players[currentPlayer].getHand().indexOfMouseOver();
      return;
    }
    int otherindex = -1;

    // if they have selected a card from their own hand already:
    // find a card from any OTHER player's hand that the mouse is currently over
    // swap the selected card with the card from the currentPlayer's hand
    // update the gameMessages log: "Switched a card with "+player.name
    // add the drawnCard to the discard, set drawnCard to null and actionState to NONE
    // set all buttons except End Turn to inactive
    int p = 0;
    for (p = 0; p < 4; p++) {
      if (p != currentPlayer) {
        otherindex = players[p].getHand().indexOfMouseOver();
        if (otherindex != -1) {
          break;
        }
      }
    }
    if (otherindex == -1)
      return;
    players[currentPlayer].getHand().switchCards(selectedCardFromCurrentPlayer,
        players[p].getHand(), otherindex);
    setGameStatus("Switched a card with " + players[p].getName());
    // add the drawnCard to the discard, set drawnCard to null and actionState to NONE
    discard.addCard(drawnCard);
    drawnCard = null;
    actionState = ActionState.NONE;

    // set all buttons except End Turn to inactive
    setInactive();

    // uncomment this code to update the knowledge of the swapped card for the other player
    // (you may need to adjust its indentation and variables)
    boolean knowledge = ((AIPlayer) players[p]).getCardKnowledge(p, otherindex);
    ((AIPlayer) players[p]).setCardKnowledge(p, otherindex,
        ((AIPlayer) players[p]).getCardKnowledge(currentPlayer, selectedCardFromCurrentPlayer));
    ((AIPlayer) players[p]).setCardKnowledge(currentPlayer, selectedCardFromCurrentPlayer,
        knowledge);

    // reset the selected card instance variable to -1
    selectedCardFromCurrentPlayer = -1;
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////

  /**
   * Advances the game to the next player's turn. Hides all players' cards, updates the current
   * player, checks for game-over conditions, resets action states, and updates the UI button states
   * for the new player's turn.
   */
  public void nextTurn() {
    // hide all players' cards
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        players[i].getHand().cardList.get(j).setFaceUp(false);
      }
    }

    // if there is still an active drawnCard, discard it and set drawnCard to null
    if (drawnCard != null) {
      discard.addCard(drawnCard);
      drawnCard = null;
    }

    // advance the current player to the next one in the list
    currentPlayer += 1;
    if (currentPlayer >= 4) {
      currentPlayer = 0;
    }

    // check if the new player is the one who declared CABO (and end the game if so)
    if (caboPlayer == currentPlayer) {
      displayGameOver();
    }

    // update the gameMessages log: "Turn for "+player.name
    setGameStatus("Turn for " + players[currentPlayer].getName());

    // reset the action state to NONE
    actionState = ActionState.NONE;

    // update the button states
    updateButtonStates();
  }

  /**
   * Displays the game-over screen and reveals all players' cards. The method calculates each
   * player's score, identifies the winner, and displays a message about the game's result,
   * including cases where there is no winner.
   * 
   * We've provided the code for the GUI parts, but the logic behind this method is still todo
   */
  public void displayGameOver() {
    // Create a dimmed background overlay
    fill(0, 0, 0, 200);
    rect(0, 0, width, height);
    fill(255);
    textSize(32);
    textAlign(CENTER, CENTER);
    text("Game Over!", (float) width / 2, (float) height / 2 - 150);

    int yPosition = 0;
    // reveal all players' cards
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        players[i].getHand().cardList.get(j).setFaceUp(true);
      }
      // calculate and display each player's score
      yPosition = height / 2 - 100;
      textSize(24);
      // uncomment to Display a player's score
      text(players[i].getName() + "'s score: " + players[i].getHand().calcHand(), (float) width / 2,
          yPosition);
      yPosition += 30;
    }



    // check if there is a tie or a specific CABO winner (lowest score wins)
    String winner = players[0].getName();
    int total = players[0].getHand().calcHand();
    int numwinners = 1;
    for (int p = 1; p < 4; p++) {
      int newtotal = players[p].getHand().calcHand();
      if (newtotal < total) {
        total = newtotal;
        winner = players[p].getName();
      } else if (newtotal == total) {
        numwinners++;
      }
    }


    // display this message if there is no winner
    if (numwinners > 1) {
      text("No Winner. The war starts.", (float) width / 2, yPosition + 30);
      return;
    }

    // display this message if there is a winner
    text("Winner: " + winner, (float) width / 2, yPosition + 30);
  }


  /**
   * PROVIDED: Sets the current game status message and updates the message log. If the message log
   * exceeds a maximum number of messages, the oldest message is removed.
   *
   * @param message the message to set as the current game status.
   */
  private void setGameStatus(String message) {
    gameMessages.add(message);
    int MAX_MESSAGES = 15;
    if (gameMessages.size() > MAX_MESSAGES) {
      gameMessages.remove(0); // Remove the oldest message
    }
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////
  // The 2 methods below this line are PROVIDED in their entirety to run the AIPlayer interactions
  // with the CABO game. Uncomment them once you are ready to add AIPlayer actions to your game!
  /////////////////////////////////////////////////////////////////////////////////////////////////

  /**
   * Performs the AI player's turn by drawing a card and deciding whether to swap, discard, or use
   * an action card. If the AI player draws a card that is better than their highest card, they swap
   * it; otherwise, they discard it. If the drawn card is an action card, the AI player performs the
   * corresponding action. If the AI player's hand value is low enough, they may declare CABO.
   */
  private void performAITurn() {
    AIPlayer aiPlayer = (AIPlayer) players[currentPlayer];
    String gameStatus = aiPlayer.getName() + " is taking their turn.";
    setGameStatus(gameStatus);

    // Draw a card from the deck
    drawnCard = deck.drawCard();
    if (drawnCard == null) {
      gameOver = true;
      return;
    }


    gameStatus = aiPlayer.getName() + " drew a card.";
    setGameStatus(gameStatus);

    // Determine if AI should swap or discard
    int drawnCardValue = drawnCard.getRank();
    int highestCardIndex = aiPlayer.getHighestIndex();
    if (highestCardIndex == -1) {
      highestCardIndex = 0;
    }
    int highestCardValue = aiPlayer.getHand().getRankAtIndex(highestCardIndex);

    // Swap if the drawn card has a lower value than the highest card in hand
    if (drawnCardValue < highestCardValue) {
      BaseCard cardInHand = aiPlayer.getHand().swap(drawnCard, highestCardIndex);
      aiPlayer.setCardKnowledge(aiPlayer.getLabel(), highestCardIndex, true);
      discard.addCard(cardInHand);
      gameStatus = aiPlayer.getName() + " swapped the drawn card with card "
          + (highestCardIndex + 1) + " in their hand.";
      setGameStatus(gameStatus);
    } else if (drawnCard instanceof ActionCard) { // Use the action card
      String actionType = ((ActionCard) drawnCard).getActionType();
      gameStatus = aiPlayer.getName() + " uses an action card: " + actionType;
      setGameStatus(gameStatus);
      performAIAction(aiPlayer, actionType);
      discard.addCard(drawnCard);
    } else { // Discard the drawn card
      discard.addCard(drawnCard);
      gameStatus = aiPlayer.getName() + " discarded the drawn card: " + drawnCard;
      setGameStatus(gameStatus);
    }

    // AI may declare Cabo if hand value is low enough
    int handValue = aiPlayer.calcHandBlind();
    if (handValue <= random(13, 21) && caboPlayer == -1) {
      declareCabo();
    }

    // Prepare for the next turn
    drawnCard = null;
    nextTurn();
  }//


  /**
   * Performs the specified action for the AI player based on the drawn action card. Actions include
   * peeking at their own cards, spying on another player's card, or switching cards with another
   * player.
   *
   * @param aiPlayer   the AI player performing the action.
   * @param actionType the type of action to perform ("peek", "spy", or "switch").
   */
  private void performAIAction(AIPlayer aiPlayer, String actionType) {
    Player otherPlayer = players[0]; // Assuming Player 1 is the human player
    String gameStatus = "";
    switch (actionType) {
      case "peek" -> { // AI peeks at one of its own cards
        int unknownCardIndex = aiPlayer.getUnknownCardIndex();
        if (unknownCardIndex != -1) {
          aiPlayer.setCardKnowledge(aiPlayer.getLabel(), unknownCardIndex, true);
          gameStatus = aiPlayer.getName() + " peeked at their card " + (unknownCardIndex + 1);
          setGameStatus(gameStatus);
        }
      }
      case "spy" -> { // AI spies on one of the human player's cards
        int spyIndex = aiPlayer.getSpyIndex();
        if (spyIndex != -1) {
          aiPlayer.setCardKnowledge(0, spyIndex, true);
          gameStatus = aiPlayer.getName() + " spied on Player 1's card " + (spyIndex + 1);
          setGameStatus(gameStatus);
        }
      }
      case "switch" -> { // AI switches one of its cards with one of the human player's cards
        int aiCardIndex = aiPlayer.getHighestIndex();
        if (aiCardIndex == -1) {
          aiCardIndex = (int) random(aiPlayer.getHand().size());
        }
        int otherCardIndex = aiPlayer.getLowestIndex(otherPlayer);
        if (otherCardIndex == -1)
          otherCardIndex = (int) random(otherPlayer.getHand().size());

        // Swap the cards between AI and the human player
        aiPlayer.getHand().switchCards(aiCardIndex, otherPlayer.getHand(), otherCardIndex);
        boolean preCardKnowledge = aiPlayer.getCardKnowledge(aiPlayer.getLabel(), aiCardIndex);
        aiPlayer.setCardKnowledge(aiPlayer.getLabel(), aiCardIndex,
            aiPlayer.getCardKnowledge(0, otherCardIndex));
        aiPlayer.setCardKnowledge(0, otherCardIndex, preCardKnowledge);

        gameStatus = aiPlayer.getName() + " switched card " + (aiCardIndex + 1) + " with "
            + otherPlayer.getName() + "'s " + (otherCardIndex + 1) + ".";
        setGameStatus(gameStatus);
      }
    }
  }
}

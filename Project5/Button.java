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


import processing.core.PApplet;

/**
 * The Button class represents a simple interactive button in the Processing environment. It
 * displays a label and can change its appearance when active or inactive. The button's appearance
 * and behavior are managed through the Processing library.
 */
public class Button {
  protected static processing.core.PApplet processing;
  private String label;
  private boolean active;
  private int x;
  private int y;
  private int width;
  private int height;

  /**
   * Constructs a Button with the specified label and position, which is inactive by default. Throws
   * an IllegalStateException if the Processing environment has not been initialized.
   * 
   * @param label  - the text label displayed on the button.
   * @param x      - the x-coordinate of the top-left corner of the button.
   * @param y      - the y-coordinate of the top-left corner of the button.
   * @param width  - the width of the button.
   * @param height - the height of the button.
   * 
   * @throws IllegalStateException if the Processing environment is not set before creating a
   *                               button.
   */
  public Button(String label, int x, int y, int width, int height) {
    if (processing == null) {
      throw new IllegalStateException(
          "Processing environment is not set before creating a button.");
    }

    this.label = label;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  /**
   * Sets the Processing environment to be used by the Button class. This must be called before
   * creating any buttons.
   * 
   * @param processing - the Processing environment to be used for drawing and interaction.
   */
  public static void setProcessing(processing.core.PApplet processing) {
    Button.processing = processing;
  }

  /**
   * Returns the label of this button
   * 
   * @return this button's current label
   */
  public String getLabel() {
    return label;
  }

  /**
   * Changes the label of this button
   * 
   * @param label - the new label for this button
   */
  public void setLabel(String label) {
    this.label = label;
  }

  /**
   * Returns whether the button is currently active.
   * 
   * @return true if the button is active, false otherwise.
   */
  public boolean isActive() {
    if (active) {
      return true;
    }
    return false;
  }

  /**
   * Sets the active state of the button. If true, the button will be rendered as active. If false,
   * it will be rendered as inactive.
   * 
   * @param active - the new active state of the button.
   */
  public void setActive(boolean active) {
    this.active = active;
  }

  /**
   * Renders the button on the Processing canvas. The button changes color based on its isActive
   * parameter and whether the mouse is currently over it
   */
  public void draw() {
    if (active && isMouseOver()) {
      processing.fill(150);
    } else if (active) {
      processing.fill(200);
    } else {
      processing.fill(255, 51, 51);
    }

    processing.rect(x, y, width, height, 5);
    processing.fill(0);
    processing.textSize(14);
    processing.textAlign(PApplet.CENTER, PApplet.CENTER);
    processing.text(label, x + (width / 2), y + (height / 2));
  }

  /**
   * Checks if the mouse is currently over this button. Use PApplet's mouseX and mouseY fields to
   * determine where the mouse is.
   * 
   * @return true if the button is under the mouse's current position, false otherwise.
   */
  public boolean isMouseOver() {
    int min_x = x;
    int max_x = x + width;
    int min_y = y;
    int max_y = y + height;

    int mx = processing.mouseX;
    int my = processing.mouseY;

    return (mx >= min_x && mx <= max_x && my >= min_y && my <= max_y);
  }

}

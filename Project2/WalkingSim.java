//////////////// P02 Walking Simulator //////////////////////////
//
// Title:    Walking Simulator
// Course:   CS 300 Fall 2024
//
// Author:   Angelina Chou
// Email:    apchou@wisc.edu
// Lecturer: Blerina Gkotse
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Online Sources:  
//  1. https://www.shiksha.com/online-courses/articles/generate-random-number-in-java/
//      Taught me how to generate integer values using Random in any given range
//
//  2. https://www.wikihow.com/Check-Null-in-Java
//      #:~:text=Use%20%E2%80%9C%3D%3D%E2%80%9D%20to%20check%20a%20variable's%20value.&text=
//      If%20you%20set%20a%20variable,a%20value%20is%20NOT%20equal.
//       I needed to know if I could use == to check if something is null or if I should've used 
//       .equalTo() instead. 
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Random;
import java.io.File;
import processing.core.PImage;

/**
 * This class creates an animation of a randomly generated number of people walking with a 
 * randomly generated background. Users can choose which people in the animation are walking and 
 * can stop them whenever. 
 */
public class WalkingSim {
  
  private static Random randGen;
  private static int bgColor;
  private static PImage[] frames; 
  private static Walker[] walkers;
  
  /**
   * Checks whether the mouse is hovering over a specific walker. 
   * 
   * @param walker is a Walker
   * @return true the mouse is hovering over the Walker walker
   */
  public static boolean isMouseOver(Walker walker) {
    // all frames have the same width and height, so we're just using the first one
    int w = frames[0].width;
    int h = frames[0].height;
    
    // finding the middle of the walker
    float x = walker.getPositionX();
    float y = walker.getPositionY();
    
    // finding where the mouse is right now
    int mx = Utility.mouseX();
    int my = Utility.mouseY();
    
    // need to divide by two because the index of the walker is starting from the middle
    int min_w = (int)x - (w/2); 
    int max_w = (int)x + (w/2);
    int min_h = (int)y - (h/2); 
    int max_h = (int)y + (h/2);
    
    // basically if the mouse is within this certain box, then the mouse is within the walker
    // using less/greater than and not equal to because edges don't count
    if ((mx < max_w && mx > min_w) && (my < max_h && my > min_h)) {
      return true;
    }
    return false;
  }
  
  /**
   * Gets walkers that are pressed on by the mouse to start walking
   */
  public static void mousePressed() {
    for (int i = 0; i < walkers.length; i ++) {
      // stops loop when it reaches null values
      if (walkers[i] == null) {
        break;
      }
      
      // whatever Walker is pressed on starts walking 
      if (isMouseOver(walkers[i])) {
        walkers[i].setWalking(true);
      }
    }
  }
  
  /**
   * Adds a Walker if key 'a' is pressed and stops Walkers from walking when 's' is pressed
   * 
   * @param c is a char 
   */
  public static void keyPressed(char c) {
    if (c == 'a') {
      for (int i = 0; i < walkers.length; i ++) {
        if (walkers[i] == null) {
          // randomly generates a specific (x,y) position for each walker 
          int w = randGen.nextInt(Utility.width());
          int h = randGen.nextInt(Utility.height());
          // creates new Walker
          walkers[i] = new Walker(w, h);
          break;
        }
      }
    }
    
    if (c == 's') {
      for (int i = 0; i < walkers.length; i ++) {
        if (walkers[i] == null) {
          break;
        }
        // all walkers are set to not walking 
        walkers[i].setWalking(false);
      }
    }
  }
  
  /**
   * Generates frames of walkers at random parts of the screen and generates a random background
   * color to be saved to private variables to be used
   */
  public static void setup() {
    randGen = new Random(); 
    bgColor = randGen.nextInt();
    frames = new PImage[Walker.NUM_FRAMES];
    walkers = new Walker[8];
    
    //loads all of the frames 
    for (int i = 0; i < frames.length; i++) {
      frames[i] = Utility.loadImage("images" + File.separator + "walk-" + i + ".png");
    }
    
    //loops through the randomly generated number of walkers and gives them a random (x,y) position
    int num = randGen.nextInt(walkers.length) + 1;
    for (int i = 0; i < num; i++) {
      int w = randGen.nextInt(Utility.width());
      int h = randGen.nextInt(Utility.height());
      walkers[i] = new Walker(w, h);
    }
  }
  
  /**
   * Loads all the animation parts like the background color and walkers onto the screen and 
   * gets the walkers to start/stop walking 
   */
  public static void draw() {
    Utility.background(bgColor);
    
    for (int i = 0; i < walkers.length; i++) {
      if (walkers[i] == null) {
        break;
      }
      
      // finds current frame and position of the walker
      int current = walkers[i].getCurrentFrame();
      float x = walkers[i].getPositionX();
      float y = walkers[i].getPositionY();
      
      // moves the walker to the right by three pixels but uses modulus so it never goes off screen
      if (walkers[i].isWalking()) {
        walkers[i].setPositionX((walkers[i].getPositionX() + 3)%800);
      }
      
      
      Utility.image(frames[current], x, y);
      
      if(isMouseOver(walkers[i])) {
        System.out.println("Mouse is over a walker!");
      }
      
      // if the mouse presses on a walker, then setWalking sets isWalking to true and then after 
      // that the walker is set to walking through update()
      if (walkers[i].isWalking()) {
        walkers[i].update();
      }
    }
  }
  
  /**
   * Runs the walker animation 
   */
  public static void main(String[] args) {
    Utility.runApplication();
  }
}

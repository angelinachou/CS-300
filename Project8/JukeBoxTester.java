//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P08 JukeBox
// Course: CS 300 Fall 2024
//
// Author: Angelina Chou
// Email: apchou@wisc.edu
// Lecturer: Blerina Gkotse
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: (identify each by name and describe how they helped)
// Online Sources: https://www.tutorialspoint.com/how-to-compare-two-arraylist-for-equality-in-java#
// :~:text=You%20can%20compare%20two%20array,if%20not%20it%20returns%20false.
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Tester class for testing the functionality of the LinkedQueue, LinkedStack, Album, Song, and
 * Jukebox classes.
 */
public class JukeBoxTester {

  /**
   * Test the behavior of adding an element to the stack.
   * 
   * @return true if element is correctly added to the stack, false otherwise
   */
  public static boolean testStackAdd() {
    LinkedStack<String> stack = new LinkedStack<>();
    if (stack.peek() != null) {
      return false;
    }

    stack.push("Taylor Swift");
    stack.push("Fearless");
    stack.push("Speak Now");

    if (!stack.peek().equals("Speak Now")) {
      return false;
    }

    if (!stack.pop().equals("Speak Now")) {
      if (!stack.peek().equals("Fearless")) {
        return false;
      }
    }

    if (!stack.pop().equals("Fearless")) {
      if (!stack.peek().equals("Taylor Swift")) {
        return false;
      }
    }

    if (!stack.pop().equals("Taylor Swift")) {
      return false;
    }

    return stack.peek() == null;
  }

  /**
   * Test the behavior of removing an element from the stack.
   * 
   * @return true if element is correctly removed from the stack, false otherwise
   */
  public static boolean testStackRemove() {
    LinkedStack<String> stack = new LinkedStack<>();
    stack.push("Taylor Swift");
    stack.push("Fearless");
    stack.push("Speak Now");
    String s = stack.pop();
    if (!s.equals("Speak Now")) {
      return false;
    }
    return stack.peek().equals("Fearless");
  }

  /**
   * Test the behavior of adding an element to the queue.
   * 
   * @return true if element is correctly added to the queue, false otherwise
   */
  public static boolean testQueueAdd() {
    LinkedQueue<String> queue = new LinkedQueue<>();
    if (queue.peek() != null) {
      return false;
    }

    queue.enqueue("Taylor Swift");
    if (!queue.peek().equals("Taylor Swift")) {
      return false;
    }

    queue.enqueue("Fearless");
    queue.enqueue("Speak Now");

    if (!queue.dequeue().equals("Taylor Swift")) {
      return false;
    }

    if (!queue.dequeue().equals("Fearless")) {
      return false;
    }

    if (!queue.dequeue().equals("Speak Now")) {
      return false;
    }

    return queue.peek() == null;
  }

  /**
   * Test the behavior of removing an element from the queue.
   * 
   * @return true if element is correctly removed from the queue, false otherwise
   */
  public static boolean testQueueRemove() {
    LinkedQueue<String> queue = new LinkedQueue<>();
    String s = queue.dequeue();
    if (s != null) {
      return false;
    }

    queue.enqueue("Taylor Swift");
    queue.enqueue("Fearless");
    queue.enqueue("Speak Now");
    s = queue.dequeue();
    if (!s.equals("Taylor Swift")) {
      return false;
    }
    return queue.peek().equals("Fearless");
  }

  /**
   * Test the behavior of peeking at the top element (for stack) and the front element (for queue).
   * 
   * @return true if the correct element returned for both data structures, false otherwise
   */
  public static boolean testPeek() {
    LinkedStack<String> stack = new LinkedStack<>();
    LinkedQueue<String> queue = new LinkedQueue<>();

    if ((stack.peek() != null) || (queue.peek() != null)) {
      return false;
    }

    stack.push("Taylor Swift");
    stack.push("Fearless");
    stack.push("Speak Now");
    queue.enqueue("Taylor Swift");
    queue.enqueue("Fearless");
    queue.enqueue("Speak Now");

    String s1 = stack.peek();
    String s2 = queue.peek();

    if ((!s1.equals("Speak Now")) || (!s2.equals("Taylor Swift")) || (queue.size() != 3)) {
      return false;
    }
    return true;
  }

  /**
   * This method tests whether the contains method correctly identifies whether a specific element
   * exists in a stack and a queue.
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testContains() {
    LinkedStack<String> stack = new LinkedStack<>();
    LinkedQueue<String> queue = new LinkedQueue<>();

    // testing when empty
    if (stack.contains("Fearless") || queue.contains("Fearless")) {
      return false;
    }

    stack.push("Taylor Swift");
    stack.push("Fearless");
    stack.push("Speak Now");
    queue.enqueue("Taylor Swift");
    queue.enqueue("Fearless");
    queue.enqueue("Speak Now");

    if (stack.contains("Red") || queue.contains("Red") || (!stack.contains("Fearless"))
        || (!queue.contains("Fearless"))) {
      return false;
    }
    return true;
  }

  /**
   * Test the behavior of getting the list of elements in the stack and queue.
   * 
   * @return true if method returns a correctly ordered list for both data structures, false
   *         otherwise
   */
  public static boolean testGetList() {
    ArrayList<String> stackExpected = new ArrayList<String>();
    ArrayList<String> queueExpected = new ArrayList<String>();
    LinkedStack<String> stack = new LinkedStack<>();
    LinkedQueue<String> queue = new LinkedQueue<>();

    ArrayList<String> stackActual = stack.getList();
    ArrayList<String> queueActual = queue.getList();

    // test with empty stack and queue
    if ((!stackExpected.equals(stackActual)) && (!queueExpected.equals(queueActual))) {
      return false;
    }

    stackExpected.add("Speak Now");
    stackExpected.add("Fearless");
    stackExpected.add("Taylor Swift");

    queueExpected.add("Taylor Swift");
    queueExpected.add("Fearless");
    queueExpected.add("Speak Now");


    stack.push("Taylor Swift");
    stack.push("Fearless");
    stack.push("Speak Now");

    queue.enqueue("Taylor Swift");
    queue.enqueue("Fearless");
    queue.enqueue("Speak Now");

    stackActual = stack.getList();
    queueActual = queue.getList();

    // CITE: Used tutorials point to figure out how to compare two arrayLists
    return ((stackExpected.equals(stackActual)) && (queueExpected.equals(queueActual)));
  }

  /**
   * Tests adding songs to an Album and verifies the size and content. Checks if songs are correctly
   * added in LIFO order.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testAddSongToAlbum() {
    Album a = new Album("Red");
    Song song1 = new Song("All Too Well", "Taylor Swift");
    song1.setAlbum(a);
    a.addSong(song1);

    Song song2 = new Song("I Knew You Were Trouble", "Taylor Swift");
    song2.setAlbum(a);
    a.addSong(song2);

    Song duplicate = new Song("All Too Well", "Taylor Swift");
    duplicate.setAlbum(a);

    try {
      a.addSong(duplicate);
    } catch (IllegalArgumentException e) {
      // expected
    }

    if (a.size() != 2) {
      return false;
    }

    String actual = a.toString();

    String expected = "Red" + "\nI Knew You Were Trouble: Taylor Swift (Red)"
        + "\nAll Too Well: Taylor Swift (Red)";

    return actual.equals(expected);
  }

  /**
   * Tests removing a song from an Album and verifies the size and content after removal. Checks if
   * songs are correctly removed in LIFO order.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testRemoveSongFromAlbum() {
    Album a = new Album("Red");

    try {
      a.removeSong();
      return false;
    } catch (NoSuchElementException e) {
      // expected
    }

    Song song1 = new Song("All Too Well", "Taylor Swift");
    song1.setAlbum(a);
    a.addSong(song1);

    Song song2 = new Song("I Knew You Were Trouble", "Taylor Swift");
    song2.setAlbum(a);
    a.addSong(song2);

    Song song3 = new Song("Treacherous", "Taylor Swift");
    song2.setAlbum(a);
    a.addSong(song3);

    Song song4 = new Song("Red", "Taylor Swift");
    song2.setAlbum(a);
    a.addSong(song4);

    Song song5 = new Song("State of Grace", "Taylor Swift");
    song2.setAlbum(a);
    a.addSong(song5);

    if (a.size() != 5) {
      return false;
    }

    Song s5 = a.removeSong();
    if (!s5.equals(song5)) {
      return false;
    }

    if (a.size() != 4) {
      return false;
    }

    Song s4 = a.removeSong();
    if (!s4.equals(song4)) {
      return false;
    }

    if (a.size() != 3) {
      return false;
    }

    Song s3 = a.removeSong();
    if (!s3.equals(song3)) {
      return false;
    }

    if (a.size() != 2) {
      return false;
    }

    String expected = "Red" + "\nI Knew You Were Trouble: Taylor Swift (Red)"
        + "\nAll Too Well: Taylor Swift (Red)";

    return a.toString().equals(expected);
  }

  /**
   * Tests the toString method of the Album class. Verifies that the returned string correctly
   * represents all songs in LIFO order.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testAlbumToString() {
    Album a = new Album("Red");
    Song song1 = new Song("All Too Well", "Taylor Swift");
    song1.setAlbum(a);
    a.addSong(song1);

    Song song2 = new Song("I Knew You Were Trouble", "Taylor Swift");
    song2.setAlbum(a);
    a.addSong(song2);

    String expected = "Red" + "\nI Knew You Were Trouble: Taylor Swift (Red)"
        + "\nAll Too Well: Taylor Swift (Red)";

    return a.toString().equals(expected);
  }

  /**
   * Tests adding a song to the Jukebox and verifies the queue contents and size.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testAddSongToJukebox() {
    JukeBox box = new JukeBox(2);
    Album a = new Album("TTPD");

    Song song1 = new Song("The Smallest Man Who Ever Lived", "Taylor Swift");
    song1.setAlbum(a);
    box.addSongToQueue(song1);
    if (box.size() != 1) {
      return false;
    }

    try {
      Song duplicate = new Song("The Smallest Man Who Ever Lived", "Taylor Swift");
      duplicate.setAlbum(a);
      box.addSongToQueue(duplicate);
      return false;
    } catch (IllegalArgumentException e) {
      // expected
    }

    Song song2 = new Song("Black Dog", "Taylor Swift");
    song2.setAlbum(a);
    box.addSongToQueue(song2);
    if (box.size() != 2) {
      return false;
    }

    try {
      box.addSongToQueue(new Song("Long Live", "Taylor Swift"));
      return false;
    } catch (IllegalStateException e) {
      // expected
    }

    String expected = "The Smallest Man Who Ever Lived: Taylor Swift (TTPD)" + " -> "
        + "Black Dog: Taylor Swift (TTPD)" + " -> END";

    return expected.equals(box.toString());
  }

  /**
   * Tests adding an album to the Jukebox and verifies the queue contents and size.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testAddAlbumToJukebox() {
    Album a = new Album("Evermore");
    Song s1 = new Song("Gold Rush", "Taylor Swift");
    s1.setAlbum(a);
    a.addSong(s1);

    Song s2 = new Song("Closure", "Taylor Swift");
    s2.setAlbum(a);
    a.addSong(s2);

    Song s3 = new Song("Ivy", "Taylor Swift");
    s3.setAlbum(a);
    a.addSong(s3);

    Song s4 = new Song("Cowboy Like Me", "Taylor Swift");
    s4.setAlbum(a);
    a.addSong(s4);

    Song s5 = new Song("Champagne Problems", "Taylor Swift");
    s5.setAlbum(a);
    a.addSong(s5);

    JukeBox box = new JukeBox(4);
    Song other = new Song("Cowboy Like Me", "Taylor Swift");
    other.setAlbum(a);

    box.addSongToQueue(other);
    if (box.size() != 1 || box.capacity() != 4) {
      return false;
    }

    box.addAlbumToQueue(a);
    if ((!box.isFull()) || (box.size() != 4)) {
      return false;
    }

    String expected = "Cowboy Like Me: Taylor Swift (Evermore)" + " -> "
        + "Champagne Problems: Taylor Swift (Evermore)" + " -> " + "Ivy: Taylor Swift (Evermore)"
        + " -> " + "Closure: Taylor Swift (Evermore)" + " -> END";

    return box.toString().equals(expected);
  }

  /**
   * Tests playing a song from the JukeboxQueue. Verifies that the song is removed from the queue
   * after playback.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testPlaySongFromJukebox() {
    JukeBox box = new JukeBox(2);

    Song song1 = new Song("The Smallest Man Who Ever Lived", "Taylor Swift");
    box.addSongToQueue(song1);
    if (box.size() != 1) {
      return false;
    }

    Song song2 = new Song("Black Dog", "Taylor Swift");
    box.addSongToQueue(song2);
    if (box.size() != 2) {
      return false;
    }

    Song s1 = box.playSong();
    if ((!s1.equals(song1)) || (box.size() != 1)) {
      return false;
    }

    Song s2 = box.playSong();
    if ((!s2.equals(song2)) || (box.size() != 0)) {
      return false;
    }

    try {
      box.playSong();
      return false;
    } catch (NoSuchElementException e) {
      // expected
    }

    return true;

  }

  /**
   * Tests shuffling the JukeBox queue. Verifies that the songs are reordered randomly after the
   * operation.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testJukeboxShuffle() {
    JukeBox box = new JukeBox(16);
    Song[] songs = new Song[16];
    songs[0] = new Song("The 1", "Taylor Swift");
    songs[1] = new Song("Cardigan", "Taylor Swift");
    songs[2] = new Song("The Last Great American Dynasty", "Taylor Swift");
    songs[3] = new Song("Exile", "Taylor Swift");
    songs[4] = new Song("Tolerate It", "Taylor Swift");
    songs[5] = new Song("Mirrorball", "Taylor Swift");
    songs[6] = new Song("Seven", "Taylor Swift");
    songs[7] = new Song("August", "Taylor Swift");
    songs[8] = new Song("This Is Me Trying", "Taylor Swift");
    songs[9] = new Song("Illicit Affairs", "Taylor Swift");
    songs[10] = new Song("Invisible String", "Taylor Swift");
    songs[11] = new Song("Mad Woman", "Taylor Swift");
    songs[12] = new Song("Epiphany", "Taylor Swift");
    songs[13] = new Song("Betty", "Taylor Swift");
    songs[14] = new Song("Peace", "Taylor Swift");
    songs[15] = new Song("Hoax", "Taylor Swift");

    for (int i = 0; i < 16; i++) {
      box.addSongToQueue(songs[i]);
    }

    String original = box.toString();
    box.shuffleSongQueue();
    String newQueue = box.toString();

    if (box.size() != 16) {
      return false;
    }
    return (!original.equals(newQueue));
  }

  public static void main(String[] args) {
    // Running and printing results for all the tests

    boolean test1 = testStackAdd();
    System.out.println("testStackAdd: " + (test1 ? "PASS" : "FAIL"));

    boolean test2 = testStackRemove();
    System.out.println("testStackRemove: " + (test2 ? "PASS" : "FAIL"));

    boolean test3 = testQueueAdd();
    System.out.println("testQueueAdd: " + (test3 ? "PASS" : "FAIL"));

    boolean test4 = testQueueRemove();
    System.out.println("testQueueRemove: " + (test4 ? "PASS" : "FAIL"));

    boolean test5 = testPeek();
    System.out.println("testPeek: " + (test5 ? "PASS" : "FAIL"));

    boolean test6 = testContains();
    System.out.println("testContains: " + (test6 ? "PASS" : "FAIL"));

    boolean test7 = testGetList();
    System.out.println("testGetList: " + (test7 ? "PASS" : "FAIL"));

    boolean test8 = testAddSongToAlbum();
    System.out.println("testAddSongToAlbum: " + (test8 ? "PASS" : "FAIL"));

    boolean test9 = testRemoveSongFromAlbum();
    System.out.println("testRemoveSongFromAlbum: " + (test9 ? "PASS" : "FAIL"));

    boolean test10 = testAlbumToString();
    System.out.println("testAlbumToString: " + (test10 ? "PASS" : "FAIL"));

    boolean test11 = testAddSongToJukebox();
    System.out.println("testAddSongToJukebox: " + (test11 ? "PASS" : "FAIL"));

    boolean test12 = testAddAlbumToJukebox();
    System.out.println("testAddAlbumToJukebox: " + (test12 ? "PASS" : "FAIL"));

    boolean test13 = testPlaySongFromJukebox();
    System.out.println("testPlaySongFromJukebox: " + (test13 ? "PASS" : "FAIL"));

    boolean test14 = testJukeboxShuffle();
    System.out.println("testJukeboxShuffle: " + (test14 ? "PASS" : "FAIL"));

    System.out.println("ALL TESTS: " + (test1 && test2 && test3 && test4 && test5 && test6 && test7
        && test8 && test9 && test10 && test11 && test12 && test13 && test14 ? "PASS" : "FAIL"));
  }
}

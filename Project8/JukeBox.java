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
// Online Sources: (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

/**
 * Represents an album consisting of a stack of songs. The Album class allows adding and removing
 * songs in LIFO order.
 */
public class JukeBox {

  private LinkedQueue<Song> songQueue;
  private int capacity;

  /**
   * Constructs a new JukeBox with a specified capacity.
   * 
   * @param capacity - the maximum number of songs that can be held in the jukebox queue
   * @throws IllegalArgumentException - if the provided capacity is negative
   */
  public JukeBox(int capacity) {
    songQueue = new LinkedQueue<>();

    if (capacity < 0) {
      throw new IllegalArgumentException("The provided capacity is negative");
    }
    this.capacity = capacity;
  }

  /**
   * Adds a single song to the end of the queue if space allows.
   * 
   * @param song - the Song object to be added to the queue
   * @throws IllegalStateException    - if the queue is at maximum capacity
   * @throws IllegalArgumentException - if the song already exists in the queue
   */
  public void addSongToQueue(Song song) {
    if (isFull()) {
      throw new IllegalStateException("Queue is at maximum capacity");
    }
    if (songQueue.contains(song)) {
      throw new IllegalArgumentException("Song already exists in the queue");
    }

    songQueue.enqueue(song);
  }

  /**
   * Adds an entire album to the queue if space allows. Each song in the album is added individually
   * in order. Add as many songs as possible if album has size greater than space, skip songs
   * already added to queue.
   * 
   * @param album - a list of Song objects representing an album
   */
  public void addAlbumToQueue(Album album) {
    while (album.size() > 0 && !isFull()) {
      try {
        addSongToQueue(album.firstSong());
      } catch (IllegalArgumentException e) {
        // do nothing, just move to the next object
      }
      album.removeSong();
    }
  }

  /**
   * Plays and removes the song at the front of the queue.
   * 
   * @returns the Song object that was played from the front.
   * @throws NoSuchElementException - if the queue is empty
   */
  public Song playSong() {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue is empty");
    }
    return songQueue.dequeue();
  }

  /**
   * Shuffles the songs present in the queue. Size and capacity after this operation should remain
   * the same. Randomly reorder the contents of the song queue: (1) Create an ArrayList
   * representation of all of the elements of this queue, in order (2) Use Collections.shuffle() to
   * create a new random ordering of the contents (3) REPLACE the current contents of the queue with
   * the contents in their new order from the ArrayList
   */
  public void shuffleSongQueue() {
    ArrayList<Song> songs = songQueue.getList();
    Collections.shuffle(songs);
    songQueue.clear();
    for (int i = 0; i < songs.size(); i++) {
      songQueue.enqueue(songs.get(i));
    }
  }

  /**
   * Returns the current number of songs in the queue.
   * 
   * @returns the number of songs in the queue
   */
  public int size() {
    return songQueue.size();
  }

  /**
   * Returns the capacity of the queue.
   * 
   * @returns the maximum number of songs that can be in the queue
   */
  public int capacity() {
    return capacity;
  }

  /**
   * Checks if the queue is full.
   * 
   * @returns true if the queue has reached its capacity, false otherwise
   */
  public boolean isFull() {
    return capacity == size();
  }

  /**
   * Checks if the queue is empty.
   * 
   * @returns true if the queue has no songs, false otherwise
   */
  public boolean isEmpty() {
    return (size() == 0);
  }

  /**
   * Provides a string representation of the jukebox queue for debugging and display. Song
   * representation followed by -> and finishes with "END". Song1: Artist1 (Album1) -> Song2:
   * Artist2 (Album2) -> END
   * 
   * @returns a string representing the queue, including song details in order
   */
  @Override
  public String toString() {
    String s = "";
    for (int i = 0; i < size(); i++) {
      s = s + songQueue.getList().get(i).toString() + " -> ";
    }
    return s + "END";
  }
}

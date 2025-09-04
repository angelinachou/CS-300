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
import java.util.NoSuchElementException;

/**
 * Represents an album consisting of a stack of songs. The Album class allows adding and removing
 * songs in LIFO order.
 */
public class Album {
  private LinkedStack<Song> trackList;
  private String albumName;
  private int size;

  /**
   * Constructs an empty Album with a new LinkedStack to store song and size as zero.
   * 
   * @param albumName - the name of the album
   * @throws IllegalArgumentException - if the name is null or empty
   */
  public Album(String albumName) {
    size = 0;
    trackList = new LinkedStack<Song>();

    if (albumName.isEmpty() || (albumName == null)) {
      throw new IllegalArgumentException("Album name is null or empty");
    }
    this.albumName = albumName;
  }

  /**
   * Adds a song to the top of the album's track list and adds the Album reference to the song.
   * 
   * @param s - the Song object to be added to the album
   * @throws IllegalArgumentException - if the song already exists in the album.
   */
  public void addSong(Song s) {
    if (trackList.contains(s)) {
      throw new IllegalArgumentException("Song already exists in the album");
    }
    s.setAlbum(this);
    trackList.push(s);
    size++;
  }

  /**
   * Removes the most recently added song from the album.
   * 
   * @return the Song object removed from the top of the album
   * @throws NoSuchElementException - if the album is empty
   */
  public Song removeSong() {
    if (trackList.isEmpty()) {
      throw new NoSuchElementException("Album is empty");
    }

    Song s = trackList.pop();
    size--;
    return s;
  }

  /**
   * Retrieves the song that is currently at the top of the album's track list, without removing it
   * from the stack.
   * 
   * @return the Song object at the top of the album, or null if the album is empty
   */
  public Song firstSong() {
    return trackList.peek();
  }

  /**
   * Retrieves the name of the album.
   * 
   * @return the the name of the album.
   */
  public String getAlbumName() {
    return albumName;
  }

  /**
   * Returns the number of songs currently in the album.
   * 
   * @return the number of songs in the album
   */
  public int size() {
    return size;
  }

  /**
   * Returns a string representation of the album, with the name of the album as the first line and
   * listing all songs from the top of the stack to the bottom. The output string should separate
   * Songs using a new line (\n). Top of the stack should be the first line of the string.
   * 
   * @return a string listing all songs in the album in LIFO order (top of stack comes FIRST)
   */
  @Override
  public String toString() {
    String s = albumName;
    ArrayList<Song> a = trackList.getList();
    for (int i = 0; i < a.size(); i++) {
      s += "\n" + a.get(i).toString();
    }
    return s;
  }
}

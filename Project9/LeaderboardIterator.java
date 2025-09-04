//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P09 Leaderboard
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

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LeaderboardIterator implements Iterator<Player> {

  private BSTNode<Player> current;
  private BSTNode<Player> root;

  public LeaderboardIterator(BSTNode<Player> root) {
    this.root = root;
    // minimum
    current = root;
    while (current.getLeft() != null) {
      current = current.getLeft();
    }
  }

  @Override
  public boolean hasNext() {
    return current != null;
  }

  @Override
  public Player next() {
    if (!hasNext()) {
      throw new NoSuchElementException("Has next returns false");
    }

    Player player = current.getData();
    if (current.getRight() != null) {
      current = current.getRight();
      while (current.getLeft() != null) {
        current = current.getLeft();
      }
    } else {
      BSTNode<Player> prev = null;
      while (current != null) {
        prev = current;
        current = parentNode(root, current);
      }
      current = prev;
    }
    return player;
  }

  private BSTNode<Player> parentNode(BSTNode<Player> node, BSTNode<Player> target) {
    if (node == null) {
      return null;
    }
    if ((node.getLeft() == target) || (node.getRight() == target)) {
      return node;
    }
    BSTNode<Player> left = parentNode(node.getLeft(), target);
    if (left != null) {
      return left;
    }
    return (parentNode(node.getRight(), target));
  }

}

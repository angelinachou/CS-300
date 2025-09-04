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

public class LeaderboardTester {

  /////////////////////////////////////////// COMPARE TO ///////////////////////////////////////////

  public static boolean testPlayerCompareTo() {
    boolean test1 = testCompareToDiffScore();
    boolean test2 = testCompareToSameScoreDiffName();
    boolean test3 = testCompareToEqual();
    if (!test1)
      System.out.print("diffScore FAIL ");
    if (!test2)
      System.out.print("diffName FAIL ");
    if (!test3)
      System.out.print("equals FAIL ");
    return test1 && test2 && test3;
  }

  private static boolean testCompareToDiffScore() {
    Player p1 = new Player("Angelina", 1000);
    Player p2 = new Player("Noona");

    int actual = p1.compareTo(p2);
    return actual < 0;
  }

  private static boolean testCompareToSameScoreDiffName() {
    Player p1 = new Player("Angelina");
    Player p2 = new Player("Noona");

    int actual = p1.compareTo(p2);
    return actual < 0;
  }

  private static boolean testCompareToEqual() {
    Player p1 = new Player("Noona");
    Player p2 = new Player("Noona");

    int actual = p1.compareTo(p2);
    return actual == 0;
  }

  ///////////////////////////////////////// LOOKUP: NAME /////////////////////////////////////////

  public static boolean testNameLookup() {
    boolean test1 = testLookupRoot();
    boolean test2 = testLookupLeft();
    boolean test3 = testLookupRight();
    boolean test4 = testLookupNotPresent();
    if (!test1)
      System.out.print("lookupRoot FAIL ");
    if (!test2)
      System.out.print("lookupLeft FAIL ");
    if (!test3)
      System.out.print("lookupRight FAIL ");
    if (!test4)
      System.out.print("lookupNotPresent FAIL");
    return test1 && test2 && test3 && test4;
  }

  // HINT: for these testers, add one Player at the root and then build the rest of the tree
  // manually
  // using the reference returned by getRoot(), so that you are not relying on the correctness of
  // the addPlayer() method.

  private static boolean testLookupRoot() {
    Leaderboard leaderboard = new Leaderboard();

    Player root = new Player("Root");
    leaderboard.addPlayer(root);
    Player actual = leaderboard.lookup("Root");

    return actual.equals(root);
  }

  private static boolean testLookupLeft() {
    Leaderboard leaderboard = new Leaderboard();

    Player root = new Player("Root");
    Player left = new Player("Left", 1000);

    leaderboard.addPlayer(root);
    leaderboard.getRoot().setLeft(new BSTNode<Player>(left));

    Player actual = leaderboard.lookup("Left");
    return actual.equals(left);
  }

  private static boolean testLookupRight() {
    Leaderboard leaderboard = new Leaderboard();

    Player root = new Player("Root");
    Player right = new Player("Right", 2000);

    leaderboard.addPlayer(root);
    leaderboard.getRoot().setRight(new BSTNode<Player>(right));

    Player actual = leaderboard.lookup("Right");
    return actual.equals(right);
  }

  private static boolean testLookupNotPresent() {
    Leaderboard leaderboard = new Leaderboard();

    Player root = new Player("Root");
    Player right = new Player("Right", 2000);

    leaderboard.addPlayer(root);
    leaderboard.getRoot().setRight(new BSTNode<Player>(right));

    Player actual = leaderboard.lookup("Left");
    return actual == null;
  }

  //////////////////////////////////////////// ADD ////////////////////////////////////////////

  public static boolean testAdd() {
    boolean test1 = testAddPlayerEmpty();
    boolean test2 = testAddPlayer();
    boolean test3 = testAddPlayerDuplicate();
    if (!test1)
      System.out.print("addEmpty FAIL ");
    if (!test2)
      System.out.print("addPlayer FAIL ");
    if (!test3)
      System.out.print("addDuplicate FAIL ");
    return test1 && test2 && test3;
  }

  private static boolean testAddPlayerEmpty() {
    Leaderboard leaderboard = new Leaderboard();
    if ((leaderboard.count() != 0) || (leaderboard.size() != 0)) {
      return false;
    }
    Player root = new Player("root");
    boolean added = leaderboard.addPlayer(root);
    return added && (leaderboard.count() == 1) && (leaderboard.size() == 1);
  }

  private static boolean testAddPlayer() {
    // verify that addPlayer() correctly adds a player to a non-empty BST
    // Each time you add a player, make sure that:
    // (1) the method returns true
    // (2) the size and count have increased correctly
    // (3) the output of prettyPrint() is the tree that you expect (you may do this one visually)
    Leaderboard leaderboard = new Leaderboard();
    if ((leaderboard.count() != 0) || (leaderboard.size() != 0)) {
      return false;
    }

    Player p1 = new Player("Root");
    boolean added1 = leaderboard.addPlayer(p1);
    if (!added1 || (leaderboard.count() != 1) || (leaderboard.size() != 1)) {
      return false;
    }

    Player p2 = new Player("Left", 1400);
    boolean added2 = leaderboard.addPlayer(p2);
    if (!added2 || (leaderboard.count() != 2) || (leaderboard.size() != 2)) {
      return false;
    }

    Player p3 = new Player("Right", 1600);
    boolean added3 = leaderboard.addPlayer(p3);
    if (!added3 || (leaderboard.count() != 3) || (leaderboard.size() != 3)) {
      return false;
    }

    String prettyprint = "└──Root: 1500\n" + "    ├──Left: 1400\n" + "    └──Right: 1600\n";
    return prettyprint.equals(leaderboard.prettyPrint());
  }

  private static boolean testAddPlayerDuplicate() {
    Leaderboard leaderboard = new Leaderboard();
    if ((leaderboard.count() != 0) || (leaderboard.size() != 0)) {
      return false;
    }

    Player p1 = new Player("Root");
    boolean added1 = leaderboard.addPlayer(p1);
    if (!added1 || (leaderboard.count() != 1) || (leaderboard.size() != 1)) {
      return false;
    }

    Player p2 = new Player("Left", 1400);
    boolean added2 = leaderboard.addPlayer(p2);
    if (!added2 || (leaderboard.count() != 2) || (leaderboard.size() != 2)) {
      return false;
    }

    Player dupe = new Player("Left", 1400);
    boolean addedDupe = leaderboard.addPlayer(dupe);
    if (addedDupe || (leaderboard.count() != 2) || (leaderboard.size() != 2)) {
      return false;
    }

    String prettyprint = "└──Root: 1500\n" + "    ├──Left: 1400\n";
    return prettyprint.equals(leaderboard.prettyPrint());
  }

  //////////////////////////////////////////// REMOVE ////////////////////////////////////////////

  public static boolean testRemove() {
    boolean test1 = testRemoveLeaf();
    boolean test2 = testRemoveOneChild();
    boolean test3 = testRemoveTwoChildren();
    boolean test4 = testRemoveNotInTree();
    if (!test1)
      System.out.print("remove FAIL ");
    if (!test2)
      System.out.print("removeOneChild FAIL ");
    if (!test3)
      System.out.print("removeTwoChildren FAIL ");
    if (!test4)
      System.out.print("removeNotInTree FAIL ");
    return test1 && test2 && test3 && test4;
  }

  private static boolean testRemoveLeaf() {
    // verify that removePlayer() correctly removes a leaf node from the tree
    Leaderboard leaderboard = new Leaderboard();
    Player root = new Player("Player1", 1500);
    Player left = new Player("Player2", 1400);
    Player right = new Player("Player3", 1600);

    leaderboard.addPlayer(root);
    leaderboard.addPlayer(left);
    leaderboard.addPlayer(right);

    boolean removed = leaderboard.removePlayer(left);

    if (!removed) {
      return false;
    }

    if (leaderboard.count() != 2) {
      return false;
    }

    if (leaderboard.lookup("Player2") != null) {
      return false;
    }
    return true;
  }

  private static boolean testRemoveOneChild() {
    // TODO: verify that removePlayer() correctly removes a player with ONE child
    // Each time you remove a player, make sure that:
    // (1) the method returns true
    // (2) the size and count have decreased correctly
    // (3) the output of prettyPrint() is the tree that you expect (you may do this one visually)
    Leaderboard leaderboard = new Leaderboard();
    Player root = new Player("Player1", 1500);
    Player left = new Player("Player2", 1400);
    Player right = new Player("Player3", 1600);
    Player rightLeft = new Player("Player4", 1550);

    leaderboard.addPlayer(root);
    leaderboard.addPlayer(left);
    leaderboard.addPlayer(right);
    leaderboard.addPlayer(rightLeft);

    boolean removed = leaderboard.removePlayer(right);

    if (!removed) {
      return false;
    }

    if ((leaderboard.count() != 3) || (leaderboard.size() != 3)) {
      return false;
    }

    if (leaderboard.lookup("Player3") != null) {
      return false;
    }

    if (leaderboard.lookup("Player4") == null) {
      return false;
    }

    String expected = "└──Player1: 1500\n" + "    ├──Player2: 1400\n" + "    └──Player4: 1550\n";
    return (leaderboard.prettyPrint().equals(expected));
  }

  private static boolean testRemoveTwoChildren() {
    // TODO: verify that removePlayer() correctly removes a player with TWO children
    // Each time you remove a player, make sure that:
    // (1) the method returns true
    // (2) the size and count have decreased correctly
    // (3) the output of prettyPrint() is the tree that you expect (you may do this one visually)
    Leaderboard leaderboard = new Leaderboard();
    Player root = new Player("Player1", 1500);
    Player left = new Player("Player2", 1400);
    Player right = new Player("Player3", 1600);
    Player rightLeft = new Player("Player4", 1550);
    Player rightRight = new Player("Player5", 1650);

    leaderboard.addPlayer(root);
    leaderboard.addPlayer(left);
    leaderboard.addPlayer(right);
    leaderboard.addPlayer(rightLeft);
    leaderboard.addPlayer(rightRight);

    boolean removed = leaderboard.removePlayer(right);

    if (!removed) {
      return false;
    }

    if ((leaderboard.count() != 4) || (leaderboard.size() != 4)) {
      return false;
    }

    if (leaderboard.lookup("Player3") != null) {
      return false;
    }

    if (leaderboard.lookup("Player4") == null) {
      return false;
    }

    if (leaderboard.lookup("Player5") == null) {
      return false;
    }

    if (!leaderboard.getRoot().getRight().getData().equals(rightRight)) {
      return false;
    }

    String expected = "└──Player1: 1500\n" + "    ├──Player2: 1400\n" + "    └──Player5: 1650\n"
        + "        ├──Player4: 1550\n";
    return (leaderboard.prettyPrint().equals(expected));
  }

  private static boolean testRemoveNotInTree() {
    // TODO: verify that removing a player not in the tree returns false, does not modify the BST,
    // and does not cause an exception
    Leaderboard leaderboard = new Leaderboard();
    Player root = new Player("Player1", 1500);
    Player left = new Player("Player2", 1400);
    Player right = new Player("Player3", 1600);

    leaderboard.addPlayer(root);
    leaderboard.addPlayer(left);
    leaderboard.addPlayer(right);

    Player fake = new Player("Player4", 1800);
    boolean removed = leaderboard.removePlayer(fake);

    if (removed) {
      return false;
    }

    if ((leaderboard.count() != 3) || (leaderboard.size() != 3)) {
      return false;
    }

    String expected = "└──Player1: 1500\n" + "    ├──Player2: 1400\n" + "    └──Player3: 1600\n";
    return (leaderboard.prettyPrint().equals(expected));
  }

  //////////////////////////////////////////// GET NEXT ////////////////////////////////////////////

  public static boolean testGetNext() {
    boolean test1 = testGetNextAfterRoot();
    boolean test2 = testGetNextAfterLeftSubtree();
    boolean test3 = testGetNextAfterRightSubtree();
    if (!test1)
      System.out.print("afterRoot FAIL ");
    if (!test2)
      System.out.print("afterLeft FAIL ");
    if (!test3)
      System.out.print("afterRight FAIL ");
    return test1 && test2 && test3;
  }

  private static boolean testGetNextAfterRoot() {
    // verify that next() returns the correct Player when passed the Player in the root node
    Leaderboard leaderboard = new Leaderboard();

    Player root = new Player("Root");
    leaderboard.addPlayer(root);

    Player right = new Player("Right", 1600);
    leaderboard.addPlayer(right);

    Player left = new Player("Left", 1400);
    leaderboard.addPlayer(left);

    Player player = leaderboard.next(root);

    return player != null && player.equals(right);
  }

  private static boolean testGetNextAfterLeftSubtree() {
    // verify that next() returns the correct Player when the correct value is in the left
    // subtree of the leaderboard
    Leaderboard leaderboard = new Leaderboard();

    Player root = new Player("Root");
    leaderboard.addPlayer(root);

    Player leftRight = new Player("LeftRight", 1450);
    leaderboard.addPlayer(leftRight);

    Player left = new Player("Left", 1400);
    leaderboard.addPlayer(left);

    Player p = new Player("p", 1420);
    Player player = leaderboard.next(p);

    return player != null && player.equals(leftRight);
  }

  private static boolean testGetNextAfterRightSubtree() {
    // verify that next() returns the correct Player when the correct value is in the right
    // subtree of the leaderboard
    Leaderboard leaderboard = new Leaderboard();

    Player root = new Player("Root");
    leaderboard.addPlayer(root);

    Player Right = new Player("Right", 1600);
    leaderboard.addPlayer(Right);

    Player rightLeft = new Player("RightLeft", 1550);
    leaderboard.addPlayer(rightLeft);

    Player p = new Player("p");
    Player player = leaderboard.next(p);

    return player != null && player.equals(rightLeft);
  }

  //////////////////////////////////////////// MAIN ////////////////////////////////////////////

  public static void main(String[] args) {
    System.out.print("Player compareTo(): ");
    System.out.println(testPlayerCompareTo() ? "PASS" : "");

    System.out.print("Leaderboard lookup(): ");
    System.out.println(testNameLookup() ? "PASS" : "");

    System.out.print("Leaderboard add(): ");
    System.out.println(testAdd() ? "PASS" : "");

    System.out.print("Leaderboard remove(): ");
    System.out.println(testRemove() ? "PASS" : "");

    System.out.print("Leaderboard next(): ");
    System.out.println(testGetNext() ? "PASS" : "");
  }

}

//////////////// P07 Hyperloop //////////////////////////
//
// Title: P07 Hyperloop
// Course: CS 300 Fall 2024
//
// Author: Angelina Chou
// Email: apchou@wisc.edu
// Lecturer: Blerina Gkotse
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Online Sources:
// https://stackoverflow.com/questions/70640337/if-we-check-head-null-why-dont-we-check-tail-null-in
// -java-linkedlist#:~:text=Java%20LinkedList%2C%20checking%20head%20%3D%3D,
// are%20elements%20in%20the%20list.
//
///////////////////////////////////////////////////////////////////////////////

import java.util.NoSuchElementException;

/**
 * This class tests the LoopStation class, and by extension, the Track class
 */
public class LoopStationTester {



  /**
   * Checks the correctness of the createPod() method. This method should: - create a Pod with the
   * given capxacity and podClass - add it to the correct end of the correct Track in the
   * LoopStation - return a reference (shallow copy) to that Pod Note that the tracks in LoopStation
   * are protected, so you may access them directly for testing purposes
   * 
   * @return true if createPod() is functioning correctly, false otherwise
   */
  public static boolean testCreatePod() {
    LoopStation station = new LoopStation();

    Pod pod1 = station.createPod(4, true);
    Pod pod2 = station.createPod(3, false);

    // does a functionality check before checking to waitingFirst because if it is not functional,
    // it would not have made it to waitingFirst
    if ((isFunctional(pod1) && !station.waitingFirst.contains(pod1))
        || (isFunctional(pod2) && !station.waitingEconomy.contains(pod2))) {
      return false;
    }

    // checks capacity and class of pods
    try {
      if (isFunctional(pod1)) {
        if (pod1.getPodClass() != Pod.FIRST || pod1.getCapacity() != 4) {
          return false;
        }
      }

      if (isFunctional(pod2)) {
        if (pod2.getPodClass() != Pod.ECONOMY || pod2.getCapacity() != 3) {
          return false;
        }
      }
    } catch (MalfunctioningPodException e) {
      System.out.println("Malfunctioning Pod Exception: " + e.getMessage());
      return false;
    }
    return true;
  }

  /**
   * Checks the correctness of the launchPod() method. This method should: - throw a
   * NoSuchElementException if no pods are waiting to launch - launch first class pods from the END
   * of the waitingFirst track - launch economy class pods from the BEGINNING of the waitingEconomy
   * track - launch ALL first class pods before launching ANY economy class pods Note that the
   * tracks in LoopStation are protected, so you may access them directly for testing purposes
   * 
   * @return true if launchPod() is functioning correctly, false otherwise
   */
  public static boolean testLaunchPod() {
    LoopStation station = new LoopStation();

    Pod p1 = station.createPod(1, true);
    Pod p2 = station.createPod(2, true);
    Pod p3 = station.createPod(3, false);
    Pod p4 = station.createPod(4, false);

    if (isFunctional(p1)) {
      station.launchPod();
      if (isFunctional(p1)
          && ((!station.launched.contains(p1)) || station.waitingFirst.contains(p1))) {
        return false;
      }
    }
    if (isFunctional(p2)) {
      station.launchPod();
      if (isFunctional(p2)
          && ((!station.launched.contains(p2)) || station.waitingFirst.contains(p2))) {
        return false;
      }
    }
    if (isFunctional(p3)) {
      station.launchPod();
      if (isFunctional(p3)
          && ((!station.launched.contains(p3)) || station.waitingEconomy.contains(p3))) {
        return false;
      }
    }
    if (isFunctional(p4)) {
      station.launchPod();
      if (isFunctional(p4)
          && ((!station.launched.contains(p4)) || station.waitingEconomy.contains(p4))) {
        return false;
      }
    }


    try {
      station.launchPod();
    } catch (NoSuchElementException e) {
      // should throw this
    }
    return true;
  }

  /**
   * Checks the correctness of the clearMalfunctioning() method. This method should: - repeatedly
   * check the launched track for malfunctioning pods - remove those pods correctly - report the
   * number of pods it removed once there are no longer any malfunctioning pods
   * 
   * Things to consider when you are testing:
   * 
   * - there is a protected setNonFunctional() method you may use for testing purposes to ensure
   * that at least one pod is non-functional
   * 
   * - calling isFunctional() on a Pod may cause it to malfunction! You should come up with an
   * alternate way to check whether a Pod is functional, if you have not already.
   * 
   * - verify that the difference in number of pods from before the method was called and after the
   * method was called is equal to the number that it reported
   * 
   * @return true if clearMalfunctioning() is functioning correctly, false otherwise
   */
  public static boolean testClearMalfunctioning() {
    LoopStation station = new LoopStation();
    final int total = 100;
    Pod[] p = new Pod[total];
    boolean[] isgood = new boolean[total];
    int launchsize = 0;
    int i;

    // creates all total pods in p
    for (i = 0; i < total; i++) {
      p[i] = station.createPod(i+1, false);
      isgood[i] = true;
    }

    // launches functional pods
    for (i = 0; i < total; i++) {
      if (isFunctional(p[i])) {
        station.launchPod();
        if (!isFunctional(p[i])) { // launchPod may make pod not functional
          isgood[i] = false;
        } else {
          launchsize++;
        }
      }
    }
    // at this point all items in station.launched are functional.
    for (i = (total/4); i < (total*2/3); i ++) {
      if (isgood[i]) {
        isgood[i] = false;
        p[i].setNonFunctional();
      }
    }

    int bad=0;
    for (i=0; i<total; i++) {
      if (station.launched.contains(p[i])) {
        if (!isFunctional(p[i])) {
          bad++;
        }
      }
    }
    
    int removed = station.clearMalfunctioning();

    if (bad != removed) {
      return false;
    }
    int realsize = 0;
    for (i = 0; i < total; i++) {
      if (station.launched.contains(p[i])) {
        if (!isgood[i]) {
          // left a bad one in the launched track
          return false;
        }
        realsize++;
      }
    }

    if (station.launched.size() != realsize) {
      return false;
    }
    if ((launchsize - removed) != realsize) {
      return false;
    }

    return true;
  }

  /**
   * Checks the correctness of the three getNumXXX() methods from LoopStation. This will require
   * adding Pods of various types, loading them with passengers, and launching them.
   * 
   * @return true if the getNumXXX() methods are all functioning correctly, false otherwise
   */
  public static boolean testGetNums() {
    // getNumWaiting
    LoopStation s1 = new LoopStation();
    Pod[] p = new Pod[4];
    for (int i = 0; i < 2; i++) {
      p[i] = s1.createPod(i + 1, true);
    }
    for (int i = 2; i < 4; i++) {
      p[i] = s1.createPod(i + 1, false);
    }

    // counts how many pods are waiting manually after making sure it was added to the track
    int expectedWaiting = 0;
    for (int i = 0; i < 4; i++) {
      if (isFunctional(p[i])) {
        expectedWaiting++;
      }
    }

    int actualWaiting = s1.getNumWaiting();

    if (expectedWaiting != actualWaiting) {
      return false;
    }

    // getNumLaunched
    int expectedLaunched = 0;
    try {
      s1.launchPod();
      s1.launchPod();
      s1.launchPod();
      s1.launchPod();
    } catch (NoSuchElementException e) {
    }

    // if the pod was functional, it should've launched, and if not, then it would've disappeared
    for (int i = 0; i < 4; i++) {
      if (isFunctional(p[i])) {
        expectedLaunched++;
      }
    }

    int actualLaunched = s1.getNumLaunched();
    if (expectedLaunched != actualLaunched) {
      return false;
    }

    // getNumPassengers
    int expectedPass = 0;

    // adds the number of passengers in the pod if the pod was functional
    for (int i = 0; i < 4; i++) {
      if (isFunctional(p[i])) {
        try {
          expectedPass += p[i].getNumPassengers();
        } catch (MalfunctioningPodException e) {
        }
      }
    }

    int actualPass = s1.getNumPassengers();

    if (expectedPass != actualPass) {
      return false;
    }

    return true;
  }

  /**
   * Helper method to use indirect way to see if pod is functional. Where capacity throws an
   * exception, the pod must not be functional.
   * 
   * @param p is the pod to be checked for functionality
   * @return true if p if functional and false if it is not
   */
  private static boolean isFunctional(Pod p) {
    try {
      p.getCapacity();
    }
    // if getCapacity throws a MalfunctioningPodException, then the whole pod must be malfunctioning
    catch (MalfunctioningPodException e) {
      return false;
    }
    return true;
  }

  public static void main(String[] args) {
    boolean test1 = testCreatePod();
    System.out.println("testCreatePod: " + (test1 ? "PASS" : "fail"));

    boolean test2 = testLaunchPod();
    System.out.println("testLaunchPod: " + (test2 ? "PASS" : "fail"));

    boolean test3 = testClearMalfunctioning();
    System.out.println("testClearMalfunctioning: " + (test3 ? "PASS" : "fail"));

    boolean test4 = testGetNums();
    System.out.println("testGetNums: " + (test4 ? "PASS" : "fail"));

    System.out.println("ALL TESTS: " + ((test1 && test2 && test3 && test4) ? "PASS" : "fail"));
  }

}

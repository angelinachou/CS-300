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
 * This class models a LoopStation
 */
public class LoopStation {
  protected Track waitingFirst;
  protected Track waitingEconomy;
  protected Track launched;


  /**
   * Creates a new LoopStation with empty tracks
   */
  public LoopStation() {
    waitingFirst = new Track();
    waitingEconomy = new Track();
    launched = new Track();
  }

  /**
   * Creates a new Pod of the appropriate class and loads it onto the correct waiting track. This
   * method also returns a reference to this newly-created Pod so that passengers may board
   * 
   * @param capacity     - the capacity of the new Pod to create
   * @param isFirstClass - whether the new Pod is a first class Pod
   * @return a reference to the newly-created Pod
   */
  public Pod createPod(int capacity, boolean isFirstClass) {
    int c;
    Pod pod;
    if (isFirstClass) {
      c = Pod.FIRST;
      pod = new Pod(capacity, c);
      waitingFirst.add(pod);
    } else {
      c = Pod.ECONOMY;
      pod = new Pod(capacity, c);
      waitingEconomy.add(pod);
    }

    return pod;
  }

  /**
   * Launches the highest-priority, least-recently-created Pod that is currently waiting:
   * 
   * @throws NoSuchElementException if no pods are waiting to launch
   */
  public void launchPod() {

    // first tries to launch last pod from waitingFirst
    if (waitingFirst != null && !waitingFirst.isEmpty()) {
      Pod pod = waitingFirst.get(waitingFirst.size() - 1);

      launched.add(pod);
      waitingFirst.remove(waitingFirst.size() - 1);

    }
    // then tries to launch last pod from waitingEconomy
    else if (waitingEconomy != null && !waitingEconomy.isEmpty()) {
      Pod pod = waitingEconomy.get(0);

      launched.add(pod);
      waitingEconomy.remove(0);
    } else {
      throw new NoSuchElementException("No pods are waiting to launch.");
    }
  }

  /**
   * Finds and removes any malfunctioning Pods from the launched track
   * 
   * @return the total number of pods which were removed
   */
  public int clearMalfunctioning() {
    int removed = 0;
    int index = launched.findFirstNonFunctional();
    while (index != -1) {
      launched.remove(index);
      removed++;
      index = launched.findFirstNonFunctional();
    }
    return removed;
  }

  /**
   * Reports the total number of first and economy class Pods which are waiting to be launched
   * 
   * @return the total number of unlaunched pods at this LoopStation
   */
  public int getNumWaiting() {
    int num = waitingFirst.size() + waitingEconomy.size();
    return num;
  }

  /**
   * Reports the total number of Pods, functional or non-functional, which are currently running on
   * the launched track
   * 
   * @return the total number of Pods on the launched track
   */
  public int getNumLaunched() {
    int num = launched.size();
    return num;
  }

  /**
   * Reports the total number of passengers in functional Pods across all tracks, waiting and
   * launched
   * 
   * @return the total number of passengers in functional Pods currently being served by this
   *         LoopStation
   */
  public int getNumPassengers() {
    int total = 0;

    for (int i = 0; i < waitingFirst.size(); i++) {
      try {
        Pod pod = waitingFirst.get(i);
        if (pod.isFunctional()) {
          total = total + pod.getNumPassengers();
        }
      } catch (MalfunctioningPodException e) {
        System.out.println("Malfunctioning Pod Exception: " + e.getMessage());
      }
    }

    for (int i = 0; i < waitingEconomy.size(); i++) {
      try {
        Pod pod = waitingEconomy.get(i);
        if (pod.isFunctional()) {
          total = total + pod.getNumPassengers();
        }
      } catch (MalfunctioningPodException e) {
        System.out.println("Malfunctioning Pod Exception: " + e.getMessage());
      }
    }

    for (int i = 0; i < launched.size(); i++) {
      try {
        Pod pod = launched.get(i);
        if (pod.isFunctional()) {
          total = total + pod.getNumPassengers();
        }
      } catch (MalfunctioningPodException e) {
        System.out.println("Malfunctioning Pod Exception: " + e.getMessage());
      }
    }

    return total;
  }

}

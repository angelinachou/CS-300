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

/**
 * This class models Track objects as a doubly-linked list for the Hyperloop project.
 */
public class Track implements ListADT<Pod> {
  protected LinkedNode head;
  protected LinkedNode tail;
  private int size = 0;

  /**
   * Adds a passenger to the first available seat in a Pod which matches their class designation.
   * 
   * @param name         - the name of the passenger to add
   * @param isFirstClass - whether this passenger is first class
   * @return true if they were successfully added to an available seat of their corresponding class,
   *         false if there were no seats or Pods available for their class
   */
  public boolean addPassenger(String name, boolean isFirstClass) {
    LinkedNode current = head;

    while (current != null) {
      Pod pod = current.getPod();

      // might be malfunctioning
      try {
        int classNum;

        if (isFirstClass) {
          classNum = Pod.FIRST;
        } else {
          classNum = Pod.ECONOMY;
        }

        if (pod.getPodClass() == classNum) {
          if (!pod.isFull()) {
            pod.addPassenger(name);
            return true;
          }
        }
      } catch (MalfunctioningPodException e) {
        System.out.println("Malfunctioning pod exception: " + e.getMessage());
      }

      // in case the first pod that is functional and the write class is full
      current = current.getNext();
    }
    return false;
  }

  /**
   * Searches all Pods in the track to find the given passenger
   * 
   * @param name - the name of the passenger to find
   * @return the index of the Pod this passenger was located in, or -1 if they were not found (or
   *         the Track is currently empty)
   */
  public int findPassenger(String name) {
    if (head == null) {
      return -1;
    }

    int index = 0;
    LinkedNode current = head;
    while (current != null) {
      try {
        if (current.getPod().containsPassenger(name)) {
          return index;
        }
      } catch (MalfunctioningPodException e) {
      }

      current = current.getNext();
      index++;
    }

    return -1;
  }

  /**
   * Finds the index of the first non-functional Pod on the track. If all Pods are functioning,
   * returns -1
   * 
   * @return the lowest index of a non-functional Pod on the track, or -1 if all Pods are currently
   *         functioning (or the Track is currently empty)
   */
  public int findFirstNonFunctional() {
    if (head == null) {
      return -1;
    }

    LinkedNode current = head;
    int index = 0;

    while (current != null) {
      try {
        current.getPod().getCapacity();
      } catch (MalfunctioningPodException e) {
        return index;
      }
      current = current.getNext();
      index++;
    }

    return -1;
  }

  /**
   * Reports whether the track is currently empty
   * 
   * @return true if the track is currently empty, false otherwise
   */
  public boolean isEmpty() {
    // CITE: stack overflow
    if (head == null) {
      return true;
    }
    return false;
  }

  /**
   * Reports the current number of Pods currently on this track. This number includes both
   * functional and non-functional Pods.
   * 
   * @return the number of Pods on this track
   */
  public int size() {
    return size;
  }

  /**
   * Removes ALL Pods from this track
   */
  public void clear() {
    head = null;
    tail = null;
    size = 0;
  }

  /**
   * Adds a Pod to the track in the correct location. FIRST class Pods should be added to the front
   * of the list; ECONOMY class Pods should be added to the back of the list.
   * 
   * @param newElement - the Pod to add to this track
   */
  public void add(Pod newElement) {
    if (!newElement.isFunctional()) {
      return;
    }
    LinkedNode newNode = new LinkedNode(newElement);

    try {
      // when pod is in first class, adds pod to head
      if (newElement.getPodClass() == Pod.FIRST) {
        newNode.setNext(head);
        if (head != null) {
          head.setPrev(newNode);
        }
        head = newNode;
        if (head.getNext() == null) {
          tail = newNode;
        }

      }
      // when pod is in economy class, adds pod to tail
      else if (newElement.getPodClass() == Pod.ECONOMY) {
        if (head == null) {
          head = newNode;
          tail = newNode;
        } else {
          tail.setNext(newNode);
          newNode.setPrev(tail);
          tail = newNode;
        }
      }
      size++;
    } catch (MalfunctioningPodException e) {
      System.out.println("Malfunction Pod Exception: " + e.getMessage());
    }
  }

  /**
   * Accesses the Pod at a given index
   * 
   * @param index - the index of the Pod to access
   * @return a reference to the Pod at a given index in the track
   * @throws IndexOutOfBoundsException - if the given index is invalid
   */
  public Pod get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index is invalid");
    }

    LinkedNode current = head;
    int i = 0;

    while (i < index) {
      current = current.getNext();
      i++;
    }

    return current.getPod();
  }

  /**
   * Determines whether a particular Pod is contained in the track
   * 
   * @param toFind - the Pod to search for in the track
   * @return true if the Pod is contained in the track, false otherwise
   */
  public boolean contains(Pod toFind) {
    LinkedNode current = head;

    while (current != null) {
      if (current.getPod().equals(toFind)) {
        return true;
      }
      current = current.getNext();
    }

    return false;
  }

  /**
   * Removes a Pod at a given index from the track
   * 
   * @param index - the index of the Pod to remove
   * @return a reference to the Pod removed from the track
   * @throws IndexOutOfBoundsException - if the given index is invalid
   */
  public Pod remove(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Given index is invalid");
    }

    LinkedNode l;

    // single element to remove + will empty list
    if (index == 0 && size == 1) {
      l = head;
      head = null;
      tail = null;
    }
    // removing from the beginning of the list
    else if (index == 0) {
      l = head;
      head = head.getNext();
      head.setPrev(null);
    }
    // removing from the end of the list
    else if (index == (size - 1)) {
      l = tail;
      tail = tail.getPrev();
      tail.setNext(null);

    }
    // removing from a random place in the middle of the list
    else {
      l = head;
      for (int i = 0; i < index; i++) {
        l = l.getNext();
      }

      LinkedNode prev = l.getPrev();
      LinkedNode next = l.getNext();

      prev.setNext(next);
      next.setPrev(prev);
    }

    size--;
    return l.getPod();
  }

  /**
   * Returns a String representation of the entire contents of the track (OUTPUT NOT GRADED). This
   * method traverses the entire track and includes a String representation of each Pod, which you
   * may wish to use for testing purposes.
   * 
   * @return a String representation of all Pods currently on the track
   */
  public String toString() {
    String track = "track: \n";

    LinkedNode current = head;

    while (current != null) {
      track = track + current.getPod().toString() + "\n";
      current = current.getNext();
    }

    return track;
  }

}

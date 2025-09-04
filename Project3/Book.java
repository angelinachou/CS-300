//////////////// P03 Library Management System //////////////////////////
//
// Title: Library Management System
// Course: CS 300 Fall 2024
//
// Author: Angelina Chou
// Email: apchou@wisc.edu
// Lecturer: Blerina Gkotse
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: TA William Sun helped me figure out why several of my tester methods weren't
// working. The reasoning was because the way my testers were working was not
// thorough enough, and he helped me come up with a different way in which I
// compared ArrayLists rather than booleans.
// Online Sources: https://www.programiz.com/java-programming/library/arraylist/set
// This source helped me figure out how to sort my ArrayList of books by order
// of publication year.
// https://beginnersbook.com/2022/08/add-multiple-items-to-an-arraylist-in-java/
// This source helped me figure out how to add several items in an arrayList
// into a different ArrayList all at once.
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class creates the object Book.
 */
public class Book {
  private String title;
  private String author;
  private int yearOfPublication;
  private String publisher;
  private int numberOfPages;

  /**
   * Assigns each parameter to a private variable within the Book class
   * 
   * @param t    is a String that holds the title of the Book
   * @param a    is a String that holds the author of the Book
   * @param year is an int that holds the publication year of the book
   * @param p    is a String that holds the publisher of the Book
   * @param num  is an int that holds the number of pages in the Book
   * @throws IllegalArgumentException if year of publication is an illogical value
   * @throws IllegalArgumentException if number of pages in book is negative
   */
  public Book(String t, String a, int year, String p, int num) {

    title = t;
    author = a;
    yearOfPublication = year;
    publisher = p;
    numberOfPages = num;

    // Checks for negative year of publication or publication year after 2024
    if (yearOfPublication < 0 || yearOfPublication > 2024) {
      throw new IllegalArgumentException("Year of publication is invalid");
    }

    // Checks for negative number of pages
    if (numberOfPages < 0) {
      throw new IllegalArgumentException("Number of pages cannot be negative");
    }
  }

  /**
   * Getter method that gets the title of the Book
   * 
   * @returns the title of the Book
   */
  public String getTitle() {
    return title;
  }

  /**
   * Getter method that gets the author of the Book
   * 
   * @returns the author of the Book
   */
  public String getAuthor() {
    return author;
  }

  /**
   * Getter method that gets the publication year of the Book
   * 
   * @returns the publication year of the Book
   */
  public int getYearOfPublication() {
    return yearOfPublication;
  }

  /**
   * Getter method that gets the publisher of the Book
   * 
   * @returns the publisher of the Book
   */
  public String getPublisher() {
    return publisher;
  }

  /**
   * Getter method that gets the number of pages of the Book
   * 
   * @returns the number of pages of the Book
   */
  public int getNumberOfPages() {
    return numberOfPages;
  }

  /**
   * Setter method that updates the title of the Book
   * 
   * @param newTitle is the updated title of the book that the method updates for the Book object
   */
  public void setTitle(String newTitle) {
    title = newTitle;
  }

  /**
   * Setter method that updates the author of the Book
   * 
   * @param newAuthor is the updated author of the book that the method updates for the Book object
   */
  public void setAuthor(String newAuthor) {
    author = newAuthor;
  }

  /**
   * Setter method that updates the publication year of the Book
   * 
   * @param newYear is the updated publication year of the book that the method updates for the Book
   *                object
   */
  public void setYearOfPublication(int newYear) {
    // Checks for a weird year of publication that doesn't make sense
    if (newYear < 0 || newYear > 2024) {
      throw new IllegalArgumentException("Year of publication is invalid");
    } else {
      yearOfPublication = newYear;
    }
  }

  /**
   * Setter method that updates the publisher of the Book
   * 
   * @param newPublisher is the updated publisher of the book that the method updates for the Book
   *                     object
   */
  public void setPublisher(String newPublisher) {
    publisher = newPublisher;
  }

  /**
   * Setter method that updates the number of pages of the Book
   * 
   * @param newNum is the updated number of pages of the book that the method updates for the Book
   *               object
   */
  public void setNumberOfPages(int newNum) {
    // Checks for negative number of pages
    if (newNum < 0) {
      throw new IllegalArgumentException("Number of pages cannot be negative");
    } else {
      numberOfPages = newNum;
    }
  }


}

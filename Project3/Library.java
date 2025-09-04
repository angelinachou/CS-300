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

import java.util.ArrayList;

/**
 * This class creates the object Library.
 */
public class Library {
  private ArrayList<Book> books = new ArrayList<Book>();

  /**
   * Getter method that gets the number of books in the Library
   * 
   * @returns the number of books in the Library
   */
  public int getTotalBooks() {
    return books.size();
  }

  /**
   * Getter method that gets the list of all the Books in the Library
   * 
   * @returns all the Book objects in the Library as an array list
   */
  public ArrayList<Book> getAllBooks() {
    return books;
  }

  /**
   * Adds a given book to the Library books and orders it by year of publication
   * 
   * @param book is a Book object that is then added to the Library
   */
  public void addBook(Book book) {
    int year = book.getYearOfPublication();
    // Figures out where in the arrayList the book should go based on chronological publication year
    for (int i = 0; i < books.size(); i++) {
      if (year < books.get(i).getYearOfPublication()) {
        books.add(i, book);
        break;
      }
    }
    books.add(book);
  }

  /**
   * Removes book from library based on title of the book
   * 
   * @param title is the title of the book that will be removed from the library
   * @returns true if book was successfully removed and false if it was not
   */
  public boolean removeBookByTitle(String title) {
    for (int i = 0; i < books.size(); i++) {
      if (books.get(i).getTitle().equalsIgnoreCase(title)) {
        books.remove(i);
        return true;
      }
    }
    return false;
  }

  /**
   * Finds all the books in the library of a specific author
   * 
   * @param author is the author whose books we are trying to find
   * @returns ArrayList of all books by the author
   */
  public ArrayList<Book> findBooksByAuthor(String author) {
    ArrayList<Book> authorBooks = new ArrayList<Book>();
    for (int i = 0; i < books.size(); i++) {
      if (author.equalsIgnoreCase(books.get(i).getAuthor())) {
        authorBooks.add(books.get(i));
      }
    }

    return authorBooks;
  }

  /**
   * Changes the title of a book to a new title
   * 
   * @param oldTitle is the title that we are trying to replace
   * @param newTitle is the title that we are trying to change the title of the book to
   * @returns true if book's title was successfully changed
   */
  public boolean updateBookTitle(String oldTitle, String newTitle) {
    for (int i = 0; i < books.size(); i++) {
      if (books.get(i).getTitle().equalsIgnoreCase(oldTitle)) {
        books.get(i).setTitle(newTitle);
        return true;
      }
    }
    return false;
  }

  /**
   * Updates author name of a specific Book
   * 
   * @param title     is the title of the book whose author we are trying to change
   * @param newAuthor is the author that we are trying to replace the current book author with
   * @returns true if the author was successfully changed and false if it was not
   */
  public boolean updateBookAuthor(String title, String newAuthor) {
    for (int i = 0; i < books.size(); i++) {
      if (books.get(i).getTitle().equalsIgnoreCase(title)) {
        books.get(i).setAuthor(newAuthor);
        return true;
      }
    }
    return false;
  }

  /**
   * Prints out the title and the author of every single book in the Library
   */
  public void printAllBooks() {
    for (int i = 0; i < books.size(); i++) {
      System.out
          .println("Title: " + books.get(i).getTitle() + ", Author: " + books.get(i).getAuthor());
    }
  }


}

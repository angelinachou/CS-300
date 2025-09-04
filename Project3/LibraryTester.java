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
 * Tests methods of Book and Library classes.
 */
public class LibraryTester {
  /**
   * PROVIDED TESTER METHOD: example test method for testing the getTitle method.
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testGetTitle() {
    Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
    return "1984".equals(book.getTitle());
  }

  /**
   * PROVIDED TESTER METHOD: example test method for testing the setTitle method.
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testSetTitle() {
    Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
    book.setTitle("Animal Farm");
    return "Animal Farm".equals(book.getTitle());
  }

  /**
   * Testing the getAuthor method.
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testGetAuthor() {
    Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
    return "George Orwell".equals(book.getAuthor());
  }

  /**
   * Testing the setAuthor method.
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testSetAuthor() {
    Book book = new Book("1984", "Jane Austen", 1949, "Secker & Warburg", 328);
    book.setAuthor("George Orwell");
    return "George Orwell".equals(book.getAuthor());
  }

  /**
   * Testing the getYearOfPublication method.
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testGetYearOfPublication() {
    Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
    return 1949 == book.getYearOfPublication();
  }

  /**
   * Testing the setYearOfPublication method.
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testSetYearOfPublication() {
    Book book = new Book("1984", "George Orwell", 1946, "Secker & Warburg", 328);
    book.setYearOfPublication(1949);
    return 1949 == book.getYearOfPublication();
  }

  /**
   * Testing the getPublisher method.
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testGetPublisher() {
    Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
    return "Secker & Warburg".equals(book.getPublisher());
  }

  /**
   * Testing the setPublisher method.
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testSetPublisher() {
    Book book = new Book("1984", "George Orwell", 1949, "Disney", 328);
    book.setPublisher("Secker & Warburg");
    return "Secker & Warburg".equals(book.getPublisher());
  }

  /**
   * Testing the getNumberOfPages method.
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testGetNumberOfPages() {
    Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
    int expectedPages = 328;
    return expectedPages == book.getNumberOfPages();
  }

  /**
   * Testing the setNumberOfPages method.
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testSetNumberOfPages() {
    Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 200);
    book.setNumberOfPages(328);
    int expectedPages = 328;
    return expectedPages == book.getNumberOfPages();
  }

  /**
   * PROVIDED TESTER METHOD: Retrieves the total number of books in the library.
   * 
   * @return the total number of books
   */
  public static boolean testGetTotalBooks() {
    Library library = new Library();
    library.addBook(new Book("Book 1", "Author A", 2023, "Publisher Y", 200));
    library.addBook(new Book("Book 2", "Author B", 2023, "Publisher Z", 300));

    int expected = 2;
    int result = library.getTotalBooks();

    ArrayList<Book> expectedA = new ArrayList<>();
    expectedA.add(new Book("Book 1", "Author A", 2023, "Publisher Y", 200));
    expectedA.add(new Book("Book 2", "Author B", 2023, "Publisher Z", 300));

    if (expected != result) {
      return false;
    }
    return compareBooks(expectedA, library.getAllBooks());
  }


  /**
   * PROVIDED TESTER METHOD: example test method for adding a single book to the library.
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testAddBook() {
    Library library = new Library();
    Book book = new Book("Test Book", "Test Author", 2023, "Publisher X", 100);
    library.addBook(book);

    ArrayList<Book> expected = new ArrayList<>();
    expected.add(new Book("Test Book", "Test Author", 2023, "Publisher X", 100));
    return compareBooks(expected, library.getAllBooks());
  }

  /**
   * Test method for adding several books to the library.
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testAddMultipleBooks() {
    Library library = new Library();

    Book book1 = new Book("Pride and Prejudice", "Jane Austen", 1813, "Thomas Egerton", 273);
    library.addBook(book1);

    Book book2 = new Book("Percy Jackson", "Rick Riordan", 2005, "Miramax Books", 377);
    library.addBook(book2);

    Book book3 = new Book("Test Book", "Test Author", 2023, "Publisher X", 100);
    library.addBook(book3);

    // Created array list to compare against library array list
    ArrayList<Book> expected = new ArrayList<>();
    expected.add(new Book("Pride and Prejudice", "Jane Austen", 1813, "Thomas Egerton", 273));
    expected.add(new Book("Percy Jackson", "Rick Riordan", 2005, "Miramax Books", 377));
    expected.add(new Book("Test Book", "Test Author", 2023, "Publisher X", 100));

    return compareBooks(expected, library.getAllBooks());
  }

  /**
   * PROVIDED TESTER METHOD: example test method for removing a book by title from the library.
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testRemoveBookByTitle() {
    Library library = new Library();
    library.addBook(new Book("Test Book", "Test Author", 2023, "Publisher X", 100));
    boolean result = library.removeBookByTitle("Test Book");

    // checking result from removeBookByTitle("Test Book")
    if (result != true) {
      return false;
    }
    // checking resulted number of books
    if (library.getTotalBooks() != 0) {
      return false;
    }
    ArrayList<Book> expected = new ArrayList<>();
    // checking resulted library
    if (!compareBooks(expected, library.getAllBooks())) {
      return false;
    }
    return true;
  }

  /**
   * Test method for removing several books to the library.
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testRemoveOneOfManyBooks() {
    Library library = new Library();
    library.addBook(new Book("Pride and Prejudice", "Jane Austen", 1813, "Thomas Egerton", 273));
    library.addBook(new Book("Percy Jackson", "Rick Riordan", 2005, "Miramax Books", 377));
    library.addBook(new Book("Test Book", "Test Author", 2023, "Publisher X", 100));
    boolean result = library.removeBookByTitle("Pride and Prejudice");

    // checking result from removeBookByTitle("Test Book")
    if (result != true) {
      return false;
    }
    // checking resulted number of books
    if (library.getTotalBooks() != 2) {
      return false;
    }
    ArrayList<Book> expected = new ArrayList<>();
    expected.add(new Book("Percy Jackson", "Rick Riordan", 2005, "Miramax Books", 377));
    expected.add(new Book("Test Book", "Test Author", 2023, "Publisher X", 100));
    // checking resulted library
    if (!compareBooks(expected, library.getAllBooks())) {
      return false;
    }
    return true;
  }

  /**
   * Test method for finding books in library based on an author name.
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testFindBooksByAuthor() {
    Library library = new Library();
    library.addBook(new Book("Pride and Prejudice", "Jane Austen", 1813, "Thomas Egerton", 273));
    library.addBook(new Book("Percy Jackson", "Rick Riordan", 2005, "Miramax Books", 377));
    library.addBook(new Book("Magnus Chase", "Rick Riordan", 2015, "Disney-Hyperion", 513));
    library.addBook(new Book("Test Book", "Test Author", 2023, "Publisher X", 100));

    // Created array list to compare against library array list

    ArrayList<Book> b = new ArrayList<Book>(library.findBooksByAuthor("Rick Riordan"));
    ArrayList<Book> expected = new ArrayList<Book>();
    expected.add(new Book("Percy Jackson", "Rick Riordan", 2005, "Miramax Books", 377));
    expected.add(new Book("Magnus Chase", "Rick Riordan", 2015, "Disney-Hyperion", 513));

    // checking resulted library
    if (compareBooks(b, expected)) {
      return true;
    }
    return false;
  }

  /**
   * Test method for finding books in library based on author name for several authors
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testFindBooksByMultipleAuthors() {
    Library library = new Library();
    library.addBook(new Book("Pride and Prejudice", "Jane Austen", 1813, "Thomas Egerton", 273));
    library.addBook(new Book("Emma", "Jane Austen", 1815, "John Murray", 1036));
    library.addBook(new Book("Percy Jackson", "Rick Riordan", 2005, "Miramax Books", 377));
    library.addBook(new Book("Magnus Chase", "Rick Riordan", 2015, "Disney-Hyperion", 513));
    library.addBook(new Book("Test Book", "Test Author", 2023, "Publisher X", 100));

    // Created two array lists to find two different authors
    ArrayList<Book> jane = new ArrayList<Book>(library.findBooksByAuthor("Jane Austen"));
    ArrayList<Book> rick = new ArrayList<Book>(library.findBooksByAuthor("Rick Riordan"));

    // Adding all books by multiple authors into one arrayList
    ArrayList<Book> actual = new ArrayList<Book>();
    actual.addAll(jane);
    actual.addAll(rick);

    // Created array list to compare against tester's array list
    ArrayList<Book> expected = new ArrayList<Book>();
    expected.add(new Book("Pride and Prejudice", "Jane Austen", 1813, "Thomas Egerton", 273));
    expected.add(new Book("Emma", "Jane Austen", 1815, "John Murray", 1036));
    expected.add(new Book("Percy Jackson", "Rick Riordan", 2005, "Miramax Books", 377));
    expected.add(new Book("Magnus Chase", "Rick Riordan", 2015, "Disney-Hyperion", 513));

    if (compareBooks(actual, expected)) {
      return true;
    }

    return false;
  }

  /**
   * Test method for updating the title of a book to a different title
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testUpdateBookTitle() {
    Library library = new Library();
    Book book1 = new Book("Emma", "Jane Austen", 1813, "Thomas Egerton", 273);
    library.addBook(book1);
    Book book2 = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
    library.addBook(book2);
    library.updateBookTitle("1984", "Animal Farm");

    ArrayList<Book> expectedArray = new ArrayList<Book>();
    Book expected = new Book("Animal Farm", "George Orwell", 1949, "Secker & Warburg", 328);
    expectedArray.add(expected);

    return compareBooks(expectedArray, library.findBooksByAuthor("George Orwell"));
  }

  /**
   * Test method for updating several book titles to different titles
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testUpdateMultipleBookTitles() {
    Library library = new Library();
    Book book1 = new Book("Emma", "Jane Austen", 1813, "Thomas Egerton", 273);
    library.addBook(book1);
    Book book2 = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
    library.addBook(book2);
    library.updateBookTitle("Emma", "Pride and Prejudice");
    library.updateBookTitle("1984", "Animal Farm");

    ArrayList<Book> expectedArray = new ArrayList<Book>();
    Book expected1 = new Book("Pride and Prejudice", "Jane Austen", 1813, "Thomas Egerton", 273);
    Book expected2 = new Book("Animal Farm", "George Orwell", 1949, "Secker & Warburg", 328);
    expectedArray.add(expected1);
    expectedArray.add(expected2);

    ArrayList<Book> actualArray = new ArrayList<Book>();
    actualArray.addAll(library.findBooksByAuthor("Jane Austen"));
    actualArray.addAll(library.findBooksByAuthor("George Orwell"));

    return compareBooks(expectedArray, actualArray);
  }

  /**
   * Test method for updating the name of an author based on the book title
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testUpdateBookAuthor() {
    Library library = new Library();
    Book book1 = new Book("Emma", "George Orwell", 1813, "Thomas Egerton", 273);
    library.addBook(book1);

    library.updateBookAuthor("Emma", "Jane Austen");

    ArrayList<Book> expectedArray = new ArrayList<Book>();
    Book expected = new Book("Emma", "Jane Austen", 1813, "Thomas Egerton", 273);
    expectedArray.add(expected);

    return compareBooks(expectedArray, library.findBooksByAuthor("Jane Austen"));
  }

  /**
   * Test method for updating the name of several authors based on the book title
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testUpdateMultipleBookAuthors() {
    Library library = new Library();
    Book book1 = new Book("Pride and Prejudice", "Charlotte Bronte", 1813, "Thomas Egerton", 273);
    library.addBook(book1);
    Book book2 = new Book("Animal Farm", "Ayn Rand", 1949, "Secker & Warburg", 328);
    library.addBook(book2);

    library.updateBookAuthor("Pride and Prejudice", "Jane Austen");
    library.updateBookAuthor("Animal Farm", "George Orwell");

    ArrayList<Book> expectedArray = new ArrayList<Book>();
    Book expected1 = new Book("Pride and Prejudice", "Jane Austen", 1813, "Thomas Egerton", 273);
    Book expected2 = new Book("Animal Farm", "George Orwell", 1949, "Secker & Warburg", 328);
    expectedArray.add(expected1);
    expectedArray.add(expected2);

    ArrayList<Book> actualArray = new ArrayList<Book>();
    actualArray.addAll(library.findBooksByAuthor("Jane Austen"));
    actualArray.addAll(library.findBooksByAuthor("George Orwell"));

    return compareBooks(expectedArray, actualArray);
  }

  /**
   * Test method to see if removeBookByTitle works correctly when the book doesn't exist in the
   * library
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testRemoveNonExistentBook() {
    Library library = new Library();
    Book book1 = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
    library.addBook(book1);

    library.removeBookByTitle("Count of Monte Cristo");

    ArrayList<Book> expectedArray = new ArrayList<Book>();
    Book expected = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
    expectedArray.add(expected);

    ArrayList<Book> actualArray = new ArrayList<Book>();
    actualArray.addAll(library.getAllBooks());

    return compareBooks(expectedArray, actualArray);
  }

  /**
   * Compares two lists of books for equality.
   * 
   * @param expected the expected list of books
   * @param result   the list of books to compare
   * @return true if both lists contain the same books, false otherwise
   */
  private static boolean compareBooks(ArrayList<Book> expected, ArrayList<Book> result) {
    if (expected.size() != result.size()) {
      return false;
    }
    for (int i = 0; i < expected.size(); i++) {
      Book expectedBook = expected.get(i);
      Book resultBook = result.get(i);
      if (!expectedBook.getTitle().equals(resultBook.getTitle())
          || !expectedBook.getAuthor().equals(resultBook.getAuthor())
          || !(expectedBook.getPublisher().equals(resultBook.getPublisher()))
          || !(expectedBook.getNumberOfPages() == resultBook.getNumberOfPages())
          || !(expectedBook.getYearOfPublication() == resultBook.getYearOfPublication())) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    // test two functions in book.class
    System.out.println("Test getTitle: " + testGetTitle());
    System.out.println("Test setTitle: " + testSetTitle());
    System.out.println("Test getAuthor: " + testGetAuthor());
    System.out.println("Test setAuthor: " + testSetAuthor());
    System.out.println("Test getYearOfPublication: " + testGetYearOfPublication());
    System.out.println("Test setYearOfPublication: " + testSetYearOfPublication());
    System.out.println("Test getPublisher: " + testGetPublisher());
    System.out.println("Test setPublisher: " + testSetPublisher());
    System.out.println("Test getNumberOfPages: " + testGetNumberOfPages());
    System.out.println("Test setNumberOfPages: " + testSetNumberOfPages());
    System.out.println("Test addBook: " + testAddBook());
    System.out.println("Test addMultipleBooks: " + testAddMultipleBooks());
    System.out.println("Test removeBookByTitle: " + testRemoveBookByTitle());
    System.out.println("Test removeOneOfManyBooks: " + testRemoveOneOfManyBooks());
    System.out.println("Test findBooksByAuthor: " + testFindBooksByAuthor());
    System.out.println("Test findBooksByMultipleAuthors: " + testFindBooksByMultipleAuthors());
    System.out.println("Test updateBookTitle: " + testUpdateBookTitle());
    System.out.println("Test updateMultipleBookTitles: " + testUpdateMultipleBookTitles());
    System.out.println("Test updateBookAuthor: " + testUpdateBookAuthor());
    System.out.println("Test updateMultipleBookAuthors: " + testUpdateMultipleBookAuthors());
    System.out.println("Test removeNonExistentBook: " + testRemoveNonExistentBook());

  }
}

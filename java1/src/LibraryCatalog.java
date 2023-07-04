import java.util.*;
public class LibraryCatalog {
    public static final String GREEN_COLOR = "\u001B[32m";
    public static final String RESET = "\u001B[0m";
    public static final String RED_COLOR = "\u001B[31m";

    public static void main(String[] args) {
        LibraryManager lm = new LibraryManager();
        Scanner sc = new Scanner(System.in);

        boolean showCommands = false;

        while (true) {
            if (showCommands) {
                System.out.println("Available commands:");
                System.out.println("Add book - Add a new book to the catalog.");
                System.out.println("Remove book - Remove a book from the catalog.");
                System.out.println("Search books by title - Search for books by title.");
                System.out.println("Search books by author - Search for books by author.");
                System.out.println("Update book information - Update the information of a book.");
                System.out.println("Display all books - Display all books in the catalog.");
                System.out.println("Sort books - Sort the books in the catalog.");
                System.out.println("Help - Show available commands.");
                System.out.println("Exit - Exit the application.");
                System.out.println();
                showCommands = false;
            }

            System.out.println("Enter a command (type 'help' to see available commands):");
            String command = sc.nextLine().toLowerCase();

            switch (command) {
                case "add book":
                    System.out.println("Enter title:");
                    String title = sc.nextLine();
                    System.out.println("Enter author:");
                    String author = sc.nextLine();
                    System.out.println("Enter ISBN:");
                    String isbn = sc.nextLine();

                    lm.addBook(title, author, isbn);
                    System.out.println(GREEN_COLOR + "Book added successfully." + RESET);
                    System.out.println();
                    break;
                case "remove book":
                    System.out.println("Enter the title of the book to remove:");
                    String bookTitle = sc.nextLine();
                    boolean removed = lm.removeBook(bookTitle);

                    if (removed) {
                        System.out.println(GREEN_COLOR + "Book removed successfully." + RESET);
                    } else {
                        System.out.println(RED_COLOR + "Book not found in the catalog." + RESET);
                    }
                    System.out.println();
                    break;
                case "search books by title":
                    System.out.println("Enter the title to search for:");
                    String searchTitle = sc.nextLine();
                    List<BookDTO> titleResults = lm.searchByTitle(searchTitle);

                    if (titleResults.isEmpty()) {
                        System.out.println(RED_COLOR + "No books found with the given title." + RESET);
                    } else {
                        System.out.println("Search results:");
                        for (BookDTO book : titleResults) {
                            System.out.println(book);
                        }
                    }
                    System.out.println();
                    break;
                case "search books by author":
                    System.out.println("Enter the author to search for:");
                    String searchAuthor = sc.nextLine();
                    List<BookDTO> authorResults = lm.searchByAuthor(searchAuthor);

                    if (authorResults.isEmpty()) {
                        System.out.println(RED_COLOR + "No books found by the given author." + RESET);
                    } else {
                        System.out.println("Search results:");
                        for (BookDTO book : authorResults) {
                            System.out.println(book);
                        }
                    }
                    System.out.println();
                    break;
                case "update book information":
                    System.out.println("Enter the title of the book to update:");
                    String bookTitleToUpdate = sc.nextLine();
                    BookDTO bookToUpdate = lm.searchByTitleExact(bookTitleToUpdate);

                    if (bookToUpdate == null) {
                        System.out.println(RED_COLOR + "Book not found in the catalog." + RESET);
                    } else {
                        System.out.println("Enter the new title:");
                        String newTitle = sc.nextLine();
                        System.out.println("Enter the new author:");
                        String newAuthor = sc.nextLine();
                        System.out.println("Enter the new ISBN:");
                        String newIsbn = sc.nextLine();

                        lm.updateBook(bookToUpdate, newTitle, newAuthor, newIsbn);
                        System.out.println(GREEN_COLOR + "Book updated successfully." + RESET);
                    }
                    System.out.println();
                    break;
                case "display all books":
                    System.out.println("All books in the catalog:");
                    List<BookDTO> allBooks = lm.getAllBooks();

                    if (allBooks.isEmpty()) {
                        System.out.println(RED_COLOR + "The catalog is empty." + RESET);
                    } else {
                        for (BookDTO book : allBooks) {
                            System.out.println(book);
                        }
                    }
                    System.out.println();
                    break;
                case "sort books":
                    System.out.println("Select sorting option:");
                    System.out.println("1. Sort by title");
                    System.out.println("2. Sort by ISBN");
                    System.out.println("3. Sort by author");
                    String sortOption = sc.nextLine();

                    Comparator<BookDTO> comparator;
                    switch (sortOption) {
                        case "1":
                            comparator = Comparator.comparing(BookDTO::getTitle);
                            lm.sortBooks(comparator);
                            System.out.println(GREEN_COLOR + "Books sorted by title." + RESET);
                            break;
                        case "2":
                            comparator = Comparator.comparing(BookDTO::getIsbn);
                            lm.sortBooks(comparator);
                            System.out.println(GREEN_COLOR + "Books sorted by ISBN." + RESET);
                            break;
                        case "3":
                            comparator = Comparator.comparing(BookDTO::getAuthor);
                            lm.sortBooks(comparator);
                            System.out.println(GREEN_COLOR + "Books sorted by author." + RESET);
                            break;
                        default:
                            System.out.println(RED_COLOR +"Invalid sorting option." + RESET);
                            break;
                    }
                    System.out.println();
                    break;
                case "help":
                    showCommands = true;
                    break;
                case "exit":
                    System.out.println("Exiting the application...");
                    return;
                default:
                    System.out.println(RED_COLOR + "Invalid command. Type 'help' to see available commands." + RESET);
                    System.out.println();
                    break;
            }
        }
    }
}
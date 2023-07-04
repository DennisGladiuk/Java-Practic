import java.util.*;
class LibraryManager {
    private Library library;

    public LibraryManager() {
        this.library = new Library();
    }

    public void addBook(String title, String author, String isbn) {
        BookDTO book = new BookDTO(title, author, isbn);
        library.getCatalog().add(book);
    }

    public boolean removeBook(String title) {
        List<BookDTO> catalog = library.getCatalog();

        for (BookDTO book : catalog) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                catalog.remove(book);
                return true;
            }
        }

        return false;
    }

    public List<BookDTO> searchByTitle(String title) {
        List<BookDTO> results = new ArrayList<>();

        for (BookDTO book : library.getCatalog()) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                results.add(book);
            }
        }

        return results;
    }

    public BookDTO searchByTitleExact(String title) {
        for (BookDTO book : library.getCatalog()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }

        return null;
    }

    public List<BookDTO> searchByAuthor(String author) {
        List<BookDTO> results = new ArrayList<>();

        for (BookDTO book : library.getCatalog()) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                results.add(book);
            }
        }

        return results;
    }

    public void updateBook(BookDTO bookToUpdate, String newTitle, String newAuthor, String newIsbn) {
        bookToUpdate.setTitle(newTitle);
        bookToUpdate.setAuthor(newAuthor);
        bookToUpdate.setIsbn(newIsbn);
    }

    public List<BookDTO> getAllBooks() {
        return library.getCatalog();
    }

    public void sortBooks(Comparator<BookDTO> comparator) {
        library.getCatalog().sort(comparator);
    }
}
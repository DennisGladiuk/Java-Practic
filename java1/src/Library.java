import java.util.*;
class Library {
    private List<BookDTO> catalog;

    public Library() {
        this.catalog = new ArrayList<>();
    }

    public List<BookDTO> getCatalog() {
        return catalog;
    }

    public void setCatalog(List<BookDTO> catalog) {
        this.catalog = catalog;
    }
}

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
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

    public void saveCatalog(String filename) {
        try (Writer writer = new FileWriter(filename)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(catalog, writer);
            System.out.println("Catalog saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving catalog: " + e.getMessage());
        }
    }

    public void loadCatalog(String filename) {
        try (Reader reader = new FileReader(filename)) {
            Gson gson = new Gson();
            Type catalogType = new TypeToken<List<BookDTO>>(){}.getType();
            catalog = gson.fromJson(reader, catalogType);
            System.out.println("Catalog loaded from " + filename);
        } catch (FileNotFoundException e) {
            System.out.println("Catalog file not found: " + filename);
        } catch (IOException e) {
            System.out.println("Error loading catalog: " + e.getMessage());
        }
    }
}
package edu.yonsei.csi3106.homework2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Cover following methods,
 * Library.java
 * Library(String fileName)
 * saveLibraryToFile(String fileName)
 * Set<Book> findBooks(String collection)
 */
public class LibraryTest {
    @Test
    public void testLibraryConstructorFromFile1() {
        String fileName = "library.json";
        String libraryString = "{\"collections\": []}";

        try {
            Files.write(new File(fileName).toPath(), libraryString.getBytes());
            Library library = new Library(fileName);
            assertNotNull(library);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            new File(fileName).delete();
        }

    }

    @Test
    public void testSaveLibraryToFile1() {
        // TODO implement this
        // Create a library
        String fileName = "library.txt";
        Library library = new Library();

        Collection Novel = new Collection("Novel");
        Collection HarryPotter = new Collection("HarryPotter");

        Set<String> authors1 = new HashSet<>();
        authors1.add("J.K.Rowling");
        Set<String> authors2 = new HashSet<>(Arrays.asList("히가시노 게이고"));
        authors1.add("히가시노 게이고");

        Book book1 = new Book("Harry Potter1", authors1);
        Book book2 = new Book("Harry Potter2", authors1);
        Book book3 = new Book("눈에 갇힌 외딴 산장에서", authors2);

        HarryPotter.addElement(book1);
        HarryPotter.addElement(book2);
        Novel.addElement(HarryPotter);
        Novel.addElement(book3);

        library.getCollections().add(HarryPotter);
        library.getCollections().add(Novel);

        library.saveLibraryToFile(fileName);

    }
    
    @Test
    public void testFindBooks1() {
        Library library = new Library();

        Collection Novel = new Collection("Novel");
        Collection HarryPotter = new Collection("HarryPotter");

        Set<String> authors1 = new HashSet<>(Arrays.asList("J.K.Rowling"));
        Set<String> authors2 = new HashSet<>(Arrays.asList("히가시노 게이고"));
        Book book1 = new Book("Harry Potter1", authors1);
        Book book2 = new Book("Harry Potter2", authors1);
        Book book3 = new Book("눈에 갇힌 외딴 산장에서", authors2);

        HarryPotter.addElement(book1);
        HarryPotter.addElement(book2);
        Novel.addElement(HarryPotter);
        Novel.addElement(book3);

        // Add collections to the library
        library.getCollections().add(HarryPotter);
        library.getCollections().add(Novel);

        Set<Book> books = library.findBooks("Novel");

        // Check if the books are found correctly
        assertEquals(3, books.size());
        assertTrue(books.contains(book1));
        assertTrue(books.contains(book2));
        assertTrue(books.contains(book3));

    }
}

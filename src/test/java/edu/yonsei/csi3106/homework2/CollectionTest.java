package edu.yonsei.csi3106.homework2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;
import java.util.List;
/**
 * Cover following methods,
 * Collection.java
 * static Collection restoreCollection(String stringRepresentation) // static method instead of constructor
 * String getStringRepresentation()
 * boolean addElement(Element element) // see comments in code for some conditions
 * boolean deleteElement(Element element) // see comments in code for some conditions
 * Set<Books> findTitle(String author)
 */
public class CollectionTest {
    @Test
    public void testRestoreCollection1() {
        String collectionJson = "{\"name\": \"Novel\", \"elements\": [{\"name\": \"HarryPotter\", \"elements\": [{\"title\": \"Harry Potter1\", \"authors\": [\"J.K.Rowling\"]}, {\"title\": \"Harry Potter2\", \"authors\": [\"J.K.Rowling\"]}]} , {\"title\": \"눈에 갇힌 외딴 산장에서\", \"authors\": [\"히가시노 게이고\"]}]}";
        // String collectionJson = "{\"name\":\"Novel\",\"elements\":[{\"title\":\"Harry Potter\",\"authors\":[\"J.K.Rowling\"]},{\"title\":\"눈에 갇힌 외딴 산장에서\",\"authors\":[\"히가시노 게이고\"]}]}";
        Collection restoredCollection = Collection.restoreCollection(collectionJson);

        assertNotNull(restoredCollection);
        assertEquals("Novel", restoredCollection.getName());

        List<Element> elements = restoredCollection.getElements();
        assertNotNull(elements);
        assertEquals(2, elements.size());
    }

    @Test
    public void testGetStringRepresentation1() {
        Collection collection = new Collection("Novel");

        Set<String> authors1 = new HashSet<>(Arrays.asList("J.K.Rowling"));
        Set<String> authors2 = new HashSet<>(Arrays.asList("히가시노 게이고"));
        Book book1 = new Book("Harry Potter", authors1);
        Book book2 = new Book("눈에 갇힌 외딴 산장에서", authors2);

        collection.addElement(book1);
        collection.addElement(book2);

        String expectedJson = "{\"name\":\"Novel\",\"elements\":[{\"title\":\"Harry Potter\",\"authors\":[\"J.K.Rowling\"]},{\"title\":\"눈에 갇힌 외딴 산장에서\",\"authors\":[\"히가시노 게이고\"]}]}";
        assertEquals(expectedJson, collection.getStringRepresentation());
    }

    @Test
    public void testAddElement1() {
        Collection collection = new Collection("Novel");

        Set<String> authors1 = new HashSet<>(Arrays.asList("J.K.Rowling"));
        Set<String> authors2 = new HashSet<>(Arrays.asList("히가시노 게이고"));
        Book book1 = new Book("Harry Potter", authors1);
        Book book2 = new Book("눈에 갇힌 외딴 산장에서", authors2);

        assertTrue(collection.addElement(book1));
        assertFalse(collection.addElement(book1));
        assertTrue(collection.addElement(book2));

        List<Element> elements = collection.getElements();
        assertNotNull(elements);
        assertEquals(2, elements.size());
        assertTrue(elements.contains(book1));
    }

    @Test
    public void testDeleteElement1() {
        Collection collection = new Collection("Novel");

        Set<String> authors1 = new HashSet<>(Arrays.asList("J.K.Rowling"));
        Set<String> authors2 = new HashSet<>(Arrays.asList("히가시노 게이고"));
        Book book1 = new Book("Harry Potter", authors1);
        Book book2 = new Book("눈에 갇힌 외딴 산장에서", authors2);

        assertFalse(collection.deleteElement(book1));

        collection.addElement(book1);
        collection.addElement(book2);
        assertTrue(collection.deleteElement(book1));

        List<Element> elements = collection.getElements();
        assertNotNull(elements);
        assertEquals(1, elements.size());
    }
    
    @Test
    public void testFindTitle() {
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

        Set<Book> booksByAuthor1 = Novel.findTitle("J.K.Rowling");
        assertNotNull(booksByAuthor1);
        assertEquals(2, booksByAuthor1.size());

        Set<Book> booksByAuthor2 = Novel.findTitle("홍길동");
        assertNull(booksByAuthor2);

    }
}

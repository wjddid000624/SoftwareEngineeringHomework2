package edu.yonsei.csi3106.homework2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;
import java.util.List;

/**
 * Cover following methods,
 * Book.java
 * Book(String stringRepresentation) // regular constructor
 * String getStringRepresentation()
 * List<Collection> getContainingCollections()
 */
public class BookTest {
    @Test
    public void testBookConstructor1() {
        String Title = "중증외상센터: 골든 아워";
        Set<String> authors = new HashSet<>(Arrays.asList("이낙준", "한산이가"));
        Book book = new Book(Title, authors);

        assertEquals(Title, book.getTitle());
        assertEquals(authors, book.getAuthor());
    }
    
    @Test
    public void testGetStringRepresentation1() {
        String title = "중증외상센터: 골든 아워";
        Set<String> authors = new HashSet<>(Arrays.asList("이낙준", "한산이가"));
        Book book1 = new Book(title, authors);

        String expectedRepresentation = "{\"title\":\"중증외상센터: 골든 아워\",\"authors\":[\"이낙준\",\"한산이가\"]}";
        assertEquals(expectedRepresentation, book1.getStringRepresentation());

        Book book2 = new Book(expectedRepresentation);
        assertEquals(book1.getTitle(), book2.getTitle());
    }

    @Test
    public void testGetContainingCollections1() {
        Collection collection1 = new Collection("Collection1");
        Collection collection2 = new Collection("Collection2");
        Collection collection3 = new Collection("Collection3");

        Book book = new Book("중증외상센터: 골든 아워", new HashSet<>(Arrays.asList("이낙준", "한산이가")));

        // Collection3 is parent of collection1
        // Since book is included in collection1, book will not be included in collection2
        collection1.addElement(book);
        collection2.addElement(book);
        collection3.addElement(collection1);

        // should
        List<Collection> containingCollections = book.getContainingCollections();

        assertEquals(2, containingCollections.size());
        assertTrue(containingCollections.contains(collection1));
        assertFalse(containingCollections.contains(collection2));
        assertTrue(containingCollections.contains(collection3));

    }
}

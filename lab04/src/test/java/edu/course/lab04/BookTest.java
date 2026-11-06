package edu.course.lab04;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookTest {
    @Test
    void equalBooksHaveSameHashAndWorkAsHashMapKeys() {
        Book first = new Book("978-1", "Original title");
        Book second = new Book("978-1", "Another title");
        HashMap<Book, String> shelf = new HashMap<>();
        shelf.put(first, "stored");

        assertNotSame(first, second);
        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
        assertEquals("stored", shelf.get(second));
    }

    @Test
    void booksWithDifferentIsbnAreNotEqual() {
        Book first = new Book("978-1", "Same title");
        Book second = new Book("978-2", "Same title");

        assertFalse(first.equals(second));
    }

    @Test
    void equalsReturnsFalseForNullAndAnotherType() {
        Book book = new Book("978-1", "Title");

        assertFalse(book.equals(null));
        assertFalse(book.equals("978-1"));
        assertTrue(book.equals(book));
    }
}


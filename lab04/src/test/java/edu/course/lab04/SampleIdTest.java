package edu.course.lab04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SampleIdTest {
    @Test
    void equalIdsHaveSameHashCode() {
        SampleId first = new SampleId("birds", 1);
        SampleId second = new SampleId("birds", 1);

        assertNotSame(first, second);
        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void unequalIdsCanHaveTheSameHashCode() {
        SampleId first = new SampleId("Aa", 1);
        SampleId second = new SampleId("BB", 1);

        assertFalse(first.equals(second));
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void equalsHandlesNullOtherTypesAndIdentity() {
        SampleId id = new SampleId("birds", 1);

        assertFalse(id.equals(null));
        assertFalse(id.equals("birds:1"));
        assertTrue(id.equals(id));
    }
}


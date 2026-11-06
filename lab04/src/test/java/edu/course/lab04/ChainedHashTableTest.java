package edu.course.lab04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChainedHashTableTest {
    @Test
    void storesCollidingKeysAndReplacesValues() {
        ChainedHashTable table = new ChainedHashTable();
        SampleId firstId = new SampleId("Aa", 1);
        SampleId secondId = new SampleId("BB", 1);
        DataSample first = new DataSample("first", new double[]{1.0});
        DataSample second = new DataSample("second", new double[]{2.0});
        DataSample replacement = new DataSample("replacement", new double[]{3.0});

        table.put(firstId, first);
        table.put(secondId, second);

        assertEquals(2, table.size());
        assertSame(first, table.get(firstId));
        assertSame(second, table.get(secondId));

        assertSame(first, table.put(firstId, replacement));
        assertEquals(2, table.size());
        assertSame(replacement, table.get(firstId));
    }

    @Test
    void resizesAndKeepsEntriesAccessible() {
        ChainedHashTable table = new ChainedHashTable();

        for (int i = 1; i <= 50; i++) {
            table.put(new SampleId("dataset", i),
                    new DataSample("sample-" + i, new double[]{i}));
        }

        assertEquals(50, table.size());
        for (int i = 1; i <= 50; i++) {
            assertTrue(table.containsKey(new SampleId("dataset", i)));
            assertEquals("sample-" + i,
                    table.get(new SampleId("dataset", i)).getLabel());
        }
    }

    @Test
    void removesEntriesAndReportsMissingKeys() {
        ChainedHashTable table = new ChainedHashTable();
        SampleId id = new SampleId("dataset", 1);
        DataSample sample = new DataSample("sample", new double[]{1.0});
        table.put(id, sample);

        assertSame(sample, table.remove(new SampleId("dataset", 1)));
        assertFalse(table.containsKey(id));
        assertNull(table.get(id));
        assertNull(table.remove(id));
        assertEquals(0, table.size());
    }
}


package edu.course.lab04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SampleRegistryTest {
    @Test
    void supportsAddGetReplaceRemoveAndSize() {
        SampleRegistry registry = new SampleRegistry();
        SampleId firstId = new SampleId("birds", 1);
        SampleId equalId = new SampleId("birds", 1);
        DataSample first = new DataSample("sparrow", new double[]{1.0, 2.0});
        DataSample replacement = new DataSample("eagle", new double[]{3.0});

        assertNull(registry.put(firstId, first));
        assertTrue(registry.containsKey(equalId));
        assertSame(first, registry.get(equalId));
        assertEquals(1, registry.size());

        assertSame(first, registry.put(equalId, replacement));
        assertEquals(1, registry.size());
        assertSame(replacement, registry.get(firstId));

        assertSame(replacement, registry.remove(equalId));
        assertFalse(registry.containsKey(firstId));
        assertNull(registry.get(firstId));
        assertNull(registry.remove(firstId));
        assertEquals(0, registry.size());
    }

    @Test
    void keepsDifferentKeysWithTheSameHashCode() {
        SampleRegistry registry = new SampleRegistry();
        SampleId firstId = new SampleId("Aa", 1);
        SampleId secondId = new SampleId("BB", 1);
        DataSample first = new DataSample("first", new double[]{1.0});
        DataSample second = new DataSample("second", new double[]{2.0});

        assertEquals(firstId.hashCode(), secondId.hashCode());
        registry.put(firstId, first);
        registry.put(secondId, second);

        assertEquals(2, registry.size());
        assertSame(first, registry.get(firstId));
        assertSame(second, registry.get(secondId));
    }

    @Test
    void rejectsNullKeysAndValues() {
        SampleRegistry registry = new SampleRegistry();
        SampleId id = new SampleId("birds", 1);
        DataSample sample = new DataSample("sparrow", new double[]{1.0});

        assertThrows(NullPointerException.class, () -> registry.put(null, sample));
        assertThrows(NullPointerException.class, () -> registry.put(id, null));
        assertThrows(NullPointerException.class, () -> registry.get(null));
        assertThrows(NullPointerException.class, () -> registry.remove(null));
        assertThrows(NullPointerException.class, () -> registry.containsKey(null));
    }
}


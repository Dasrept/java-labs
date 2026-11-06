package edu.course.lab04;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

class MutableKeyExperimentTest {
    @Test
    void changingAKeyAfterInsertionCanBreakLookup() {
        MutableKey key = new MutableKey(1);
        HashMap<MutableKey, String> values = new HashMap<>();
        values.put(key, "value");

        key.setNumber(2);

        assertFalse(values.containsKey(key));
        assertNull(values.get(key));
    }

    private static final class MutableKey {
        private int number;

        private MutableKey(int number) {
            this.number = number;
        }

        private void setNumber(int number) {
            this.number = number;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || getClass() != object.getClass()) {
                return false;
            }
            MutableKey other = (MutableKey) object;
            return number == other.number;
        }

        @Override
        public int hashCode() {
            return number;
        }
    }
}


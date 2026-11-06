package edu.course.lab04;

import java.util.Objects;

public final class ChainedHashTable {
    private static final int DEFAULT_CAPACITY = 16;
    private static final double MAX_LOAD_FACTOR = 0.75;

    private Node[] buckets;
    private int size;

    public ChainedHashTable() {
        buckets = new Node[DEFAULT_CAPACITY];
    }

    public DataSample put(SampleId id, DataSample sample) {
        Objects.requireNonNull(id, "id");
        Objects.requireNonNull(sample, "sample");

        Node existing = findNode(id);
        if (existing != null) {
            DataSample previous = existing.sample;
            existing.sample = sample;
            return previous;
        }

        if (size + 1 > buckets.length * MAX_LOAD_FACTOR) {
            resize();
        }

        int index = indexFor(id, buckets.length);
        buckets[index] = new Node(id, sample, buckets[index]);
        size++;
        return null;
    }

    public DataSample get(SampleId id) {
        Objects.requireNonNull(id, "id");
        Node node = findNode(id);
        return node == null ? null : node.sample;
    }

    public DataSample remove(SampleId id) {
        Objects.requireNonNull(id, "id");
        int index = indexFor(id, buckets.length);
        Node previous = null;
        Node current = buckets[index];

        while (current != null) {
            if (current.id.equals(id)) {
                if (previous == null) {
                    buckets[index] = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return current.sample;
            }
            previous = current;
            current = current.next;
        }
        return null;
    }

    public boolean containsKey(SampleId id) {
        Objects.requireNonNull(id, "id");
        return findNode(id) != null;
    }

    public int size() {
        return size;
    }

    private Node findNode(SampleId id) {
        int index = indexFor(id, buckets.length);
        Node current = buckets[index];
        while (current != null) {
            if (current.id.equals(id)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    private void resize() {
        Node[] expanded = new Node[buckets.length * 2];
        for (Node bucket : buckets) {
            Node current = bucket;
            while (current != null) {
                Node next = current.next;
                int index = indexFor(current.id, expanded.length);
                current.next = expanded[index];
                expanded[index] = current;
                current = next;
            }
        }
        buckets = expanded;
    }

    private static int indexFor(SampleId id, int capacity) {
        return (id.hashCode() & 0x7fffffff) % capacity;
    }

    private static final class Node {
        private final SampleId id;
        private DataSample sample;
        private Node next;

        private Node(SampleId id, DataSample sample, Node next) {
            this.id = id;
            this.sample = sample;
            this.next = next;
        }
    }
}


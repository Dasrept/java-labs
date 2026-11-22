package edu.course.lab04;

import java.util.HashMap;

public final class Main {
    public static void main(String[] args) {
        Book firstBook = new Book("978-1", "Original title");
        Book equalBook = new Book("978-1", "Another title");
        HashMap<Book, String> shelf = new HashMap<>();
        shelf.put(firstBook, "Programming shelf");
        System.out.println("Equal book objects: " + firstBook.equals(equalBook));
        System.out.println("Lookup by equal ISBN: " + shelf.get(equalBook));

        SampleRegistry registry = new SampleRegistry();
        SampleId sampleId = new SampleId("birds", 1);
        DataSample sample = new DataSample("sparrow", new double[]{1.0, 2.0});
        registry.put(sampleId, sample);
        System.out.println("Sample found: " + registry.containsKey(
                new SampleId("birds", 1)));
        System.out.println("Registry size: " + registry.size());
        System.out.println("Sample label: " + registry.get(sampleId).getLabel());

        ChainedHashTable table = new ChainedHashTable();
        SampleId collisionOne = new SampleId("Aa", 1);
        SampleId collisionTwo = new SampleId("BB", 1);
        table.put(collisionOne, new DataSample("first", new double[]{1.0}));
        table.put(collisionTwo, new DataSample("second", new double[]{2.0}));
        System.out.println("Collision keys have same hash: "
                + (collisionOne.hashCode() == collisionTwo.hashCode()));
        System.out.println("Both collision entries retained: " + table.size());
        System.out.println("Second value: " + table.get(collisionTwo).getLabel());
    }
}

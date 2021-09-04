package app.web.craigstroberg.synchronizedExample;

import java.util.*;

public class SynchronizedCollectionExample {
    public static void main(String[] argv) throws Exception {
        try {
            // creating object of List<String>
            List<String> vector = new ArrayList<String>();

            // populate the vector
            vector.add("A");
            vector.add("B");
            vector.add("C");
            vector.add("D");
            vector.add("E");

            // printing the Collection
            System.out.println("Collection : " + vector);

            // getting the synchronized view of Collection
            Collection<String> c = Collections.synchronizedCollection(vector);

            // printing the Collection
            System.out.println("Synchronized view of collection : " + c);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Exception thrown : " + e);
        }
    }
}

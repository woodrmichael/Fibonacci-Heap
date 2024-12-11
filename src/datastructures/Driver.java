package datastructures;

import java.util.Random;

public class Driver {
    public static void main(String[] args) {
        FibonacciHeap<Integer> fh = new FibonacciHeap<>();
        generate(fh, 5);
        System.out.println("Fibonacci Heap initialized with 5 random keys");
        System.out.println(fh.toKeyString() + "\n");

        /*
         * Insert Demo
         */
        System.out.println("Insert node with key 50");
        fh.insert(null, 50);
        System.out.println(fh.toKeyString());
        System.out.println("Insert node with key -1, will update Min pointer");
        fh.insert(null, -1);
        System.out.println(fh.toKeyString() + "\n");

        /*
         * Union Demo
         */
        FibonacciHeap<Integer> fh2 = new FibonacciHeap<>();
        generate(fh2, 3);
        System.out.println("New Fibonacci Heap initialized with 3 random keys");
        System.out.println(fh2.toKeyString());
        System.out.println("Union the first heap and the second heap");
        fh.union(fh2);
        System.out.println(fh.toKeyString() + "\n");

        /*
         * Extract Min Demo
         */
        System.out.println("Insert a new node with key 100 to for decrease key");
        FibonacciHeap.Node<Integer> node = fh.insert(null, 100);
        fh.extractMin();
        System.out.println("Extracted the Minimum Node");
        System.out.println(fh.toKeyString() + "\n");

        /*
         * Decrease Key Demo
         */
        System.out.println("A more advanced view of the nodes");
        System.out.println(fh);
        System.out.println("Decrease the key of 100 to -2");
        fh.decreaseKey(node, -2);
        System.out.println(fh);
    }

    public static void generate(FibonacciHeap<Integer> fh, int count) {
        Random generator = new Random();
        for (int i = 0; i < count; i++) {
            fh.insert(null, generator.nextInt(0, 100));
        }
    }
}

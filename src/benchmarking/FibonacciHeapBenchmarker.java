package benchmarking;

import java.util.Random;
import datastructures.FibonacciHeap;

/**
 * A class used to benchmark different kinds of Fibonacci Heap operations.
 */
public class FibonacciHeapBenchmarker {

    /**
     * Runs the benchmarks on a specific operation on the Fibonacci Heap
     * @param operation The method to be benchmarked.
     * @param size the size of the heap.
     * @param multiplier how much the size of the heap should multiply by between each test.
     * @param numberOfTests the amount of tests.
     * @return an array of longs containing the time, in nanoseconds,
     * required for each benchmark to complete.
     *
     * @throws IllegalArgumentException Thrown if operation, size, multiplier,
     * or numberOfTests aren't valid.
     */
    public static long[] runBenchmarks(String operation, int size, int multiplier,
                                       int numberOfTests) throws IllegalArgumentException {
        checkInputNumbers(size, multiplier, numberOfTests);
        long[] elapsedTimes = new long[numberOfTests];
        for(int i = 0; i < numberOfTests; i++) {
            Integer[] arr = fillList(size);
            FibonacciHeap<Integer> fh = new FibonacciHeap<>(arr);
            elapsedTimes[i] = runOperation(fh, operation);
            size *= multiplier;
        }
        return elapsedTimes;
    }

    /**
     * Fills an array of integers with a specified size with
     * a random positive integer at each index.
     * @param size the size of the array.
     * @return an array of integers with random integers from 0 to 2^31 -1.
     */
    private static Integer[] fillList(int size) {
        Random generator = new Random();
        Integer[] arr = new Integer[size];
        for(int i = 0; i < size; i++) {
            arr[i] = generator.nextInt(Integer.MAX_VALUE);
        }
        return arr;
    }

    /**
     * Runs the specified operation on the heap.
     * @param fh the Fibonacci Heap
     * @param operation the method to be called on the heap.
     * @return the amount of time in ns to run the operation.
     *
     * @throws IllegalArgumentException thrown if operation isn't a valid operation.
     * Valid options are 'insert', 'union', 'extractMin', or 'decreaseKey'.
     */
    private static long runOperation(FibonacciHeap<Integer> fh, String operation) {
        int value = new Random().nextInt(Integer.MAX_VALUE);
        long startTime;
        long endTime;
        switch (operation) {
            case "insert":
                startTime = System.nanoTime();
                fh.insert(null, value);
                endTime = System.nanoTime();
                break;
            case "union":
                Integer[] arr = fillList(fh.size());
                FibonacciHeap<Integer> fh2 = new FibonacciHeap<>(arr);
                startTime = System.nanoTime();
                fh.union(fh2);
                endTime = System.nanoTime();
                break;
            case "extractMin":
                startTime = System.nanoTime();
                fh.extractMin();
                endTime = System.nanoTime();
                break;
            case "decreaseKey":
                FibonacciHeap.Node<Integer> node = fh.insert(null, 100000);
                fh.extractMin();
                startTime = System.nanoTime();
                fh.decreaseKey(node, 0);
                endTime = System.nanoTime();
                break;
            default:
                throw new IllegalArgumentException("Ensure the operation is valid. " +
                        "Valid options are 'insert', 'union', 'extractMin', or 'decreaseKey'.\n");
        }
        return endTime - startTime;
    }

    /**
     * Checks if the input numbers taken from the command line arguments are valid
     * @param size the size of the heap.
     * @param multiplier how much the size of the heap should multiply by between each test.
     * @param numberOfTests the amount of tests.
     *
     * @throws IllegalArgumentException Thrown if either size, multiplier,
     * or numberOfTests isn't positive.
     */
    private static void checkInputNumbers(int size, int multiplier, int numberOfTests)
            throws IllegalArgumentException {
        StringBuilder message = new StringBuilder();
        if(size < 1) {
            message.append("Please ensure size is >= 1\n");
        }
        if(multiplier < 1) {
            message.append("Please ensure multiplier is >= 1\n");
        }
        if(numberOfTests < 1) {
            message.append("Please ensure numberOfTests is >= 1\n");
        }
        if(!message.isEmpty()) {
            throw new IllegalArgumentException(message.toString());
        }
    }
}

# Fibonacci Heap Implementation

This repository contains a Java implementation of a Fibonacci Heap, a data structure for priority queues that supports efficient operations such as `insert`, `extract-min`, and `decrease-key`. Fibonacci Heaps offer better amortized time complexity for several operations compared to binary heaps, particularly in scenarios involving many `decrease-key` and `extract-min` operations.

## Fibonacci Heap Operations

- **Insert**: Adds a new node to the heap in O(1) time.
- **Extract-Min**: Removes and returns the node with the smallest key in O(log n) amortized time.
- **Decrease-Key**: Decreases the key of a given node, ensuring heap properties are maintained.
- **Union**: Merges two Fibonacci Heaps in O(1) time.
- **Delete**: Deletes a node by first decreasing its key to the minimum and then extracting it.
- **Clear**: Empties the heap, removing all nodes.
- **Size**: Returns the total number of nodes in the heap.

## Example Usage

```java
FibonacciHeap<Integer> heap = new FibonacciHeap<>();
FibonacciHeap.Node<Integer> node1 = heap.insert(10);
FibonacciHeap.Node<Integer> node2 = heap.insert(20);

// Extract the minimum node
FibonacciHeap.Node<Integer> minNode = heap.extractMin();

// Decrease the key of a node
heap.decreaseKey(node2, 5);

// Union with another Fibonacci heap
FibonacciHeap<Integer> anotherHeap = new FibonacciHeap<>();
anotherHeap.insert(3);
heap.union(anotherHeap);
```

### How to Run the Benchmarking Program

The Fibonacci Heap can be benchmarked using the included JavaFX benchmarking program. To run the program, ensure that you have JavaFX set up in your environment and follow the instructions below to execute the benchmark and see the performance results:

1. **Set up JavaFX** in your environment.
2. **Compile and run** the `BenchmarkerFX.java` file to visualize and compare the performance of Fibonacci Heap operations.
3. **Command Line Arguments** `----startSize=10000 --operation=extractMin --numberOfSamples=13 --multiplier=2 --output=plots/extractMin.png`

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE.txt) file for details.
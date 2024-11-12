package datastructures;

import java.util.List;

public class FibonacciHeap<T extends Comparable<T>> {
    private Node<T> min;
    private int size;

    public static final class Node<T> {
        private int key;
        private T value;
        private Node<T> parent;
        private Node<T> child;
        private Node<T> left;
        private Node<T> right;
        private int degree;
        private boolean marked;

        public Node(T value, int key) {
            this.key = key;
            this.value = value;
            parent = null;
            child = null;
            left = null;
            right = null;
            degree = 0;
            marked = false;
        }
    }

    public FibonacciHeap() {
        this.min = null;
        this.size = 0;
    }

    public FibonacciHeap<T> heapify(List<T> list) {
        // TODO
        return this;
    }

    public Node<T> insert(T value, int key) {
        Node<T> newNode = new Node(value, key);
        // TODO
        return newNode;
    }

    public FibonacciHeap<T> union(FibonacciHeap<T> other) {
        // TODO
        return this;
    }

    public Node<T> minimum() {
        return min;
    }

    public Node<T> extractMin() {
        Node<T> oldMin = min;
        // TODO
        return oldMin;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void decreaseKey(Node<T> node, int newKey) {

    }

    public void delete(Node<T> node) {
        decreaseKey(node, Integer.MIN_VALUE);
        extractMin();
    }

    // Method to consolidate the heap after extracting the minimum node
    private void consolidate() {

    }

    // Method to link two nodes (used in consolidation)
    private void link(Node<T> y, Node<T> x) {
        // Link logic here
    }
}

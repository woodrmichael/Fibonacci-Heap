package datastructures;

public class FibonacciHeap<T extends Comparable<T>> {
    private Node<T> minimum;

    private static final class Node<T> {
        private int key;
        private T value;
        private Node<T> left;
        private Node<T> right;
        private int degree;

        public Node(T value, int key) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.degree = 0;
        }
    }


    public FibonacciHeap() {

    }

    public void heapify() {

    }

    public void insert(T value, int key) {

    }

    public void union() {

    }

    public T minimum() {
        return minimum.value;
    }

    public T extractMinimum() {
        return null;
    }

    public int size() {
        return 0;
    }

    public void decreaseKey(T value, int key) {

    }

    public void delete(T value) {

    }
}

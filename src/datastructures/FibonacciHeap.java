package datastructures;

public class FibonacciHeap<T extends Comparable<T>> {

    private static final class Node<T> {
        private int key;
        private T value;
        private Node<T> left;
        private Node<T> right;
        private int degree;

        public Node(int key, T value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.degree = 0;
        }
    }
}

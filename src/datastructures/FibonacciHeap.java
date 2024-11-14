package datastructures;

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
            left = this;
            right = this;
        }

        public T getValue() {
            return value;
        }

        public int getKey() {
            return key;
        }

        @Override
        public String toString() {
            return "Node{value=" + value + ", key=" + key
                    + ", degree=" + degree + ", marked=" + marked + "}";
        }
    }

    public FibonacciHeap() {
        this.min = null;
        this.size = 0;
    }

    /**
     * Inserts a new node in the root list to the right of the current minimum node.
     * No consolidation is performed when a new node is inserted.
     * Operation is performed in O(1) time. <br>
     *
     * Before: insert(..., 2) <br>
     * min------------| <br>
     * |-> (...) <-> (3) <-> (4) <-> (5) <-> (...) <-| <br>
     * |---------------------------------------------| <br>
     *
     * After: <br>
     * min--------------------| <br>
     * |-> (...) <-> (3) <-> (2) <-> (4) <-> (5) <-> (...) <-| <br>
     * |-----------------------------------------------------|
     *
     * @param value the value of the new node.
     * @param key the key of the new node.
     * @return the new node.
     */
    public Node<T> insert(T value, int key) {
        Node<T> newNode = new Node<>(value, key);
        if (min != null) {
            Node<T> oldRightNode = min.right;
            min.right = newNode;
            newNode.left = min;
            oldRightNode.left = newNode;
            newNode.right = oldRightNode;
            if (key < min.key) {
                min = newNode;
            }
        } else {
            min = newNode;
        }
        size++;
        return newNode;
    }

    /**
     * Merges 2 Fibonacci Heaps in O(1) time. Adds other's root list to the current root list.
     * No consolidation is performed when union is called.
     * @param other the Fibonacci Heap which is being merged with the current Fibonacci Heap.
     */
    public void union(FibonacciHeap<T> other) {
        if (other.min != null) {
            Node<T> oldThisLeftNode = this.min.left;
            Node<T> oldOtherLeftNode = other.min.left;
            oldThisLeftNode.right = other.min;
            other.min.left = oldThisLeftNode;
            oldOtherLeftNode.right = this.min;
            this.min.left = oldOtherLeftNode;
            if (other.min.key < this.min.key) {
                min = other.min;
            }
            size = this.size + other.size;
        }
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
        return min == null;
    }

    public void clear() {
        min = null;
        size = 0;
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

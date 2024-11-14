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

    // Decreases the key of a given node to a new, smaller value.
    // If the new key violates the heap-order property (is smaller than the key of the parent),
    // the node is cut from its parent, added to the root list, and may trigger cascading cuts
    public void decreaseKey(Node<T> node, int newKey) {
        // TODO
    }

    public void delete(Node<T> node) {
        decreaseKey(node, Integer.MIN_VALUE);
        extractMin();
    }

    // A helper method used in the consolidate step.
    // This method links two trees of the same degree by making one node a child of the other,
    // increasing the degree of the new parent.
    private void link(Node<T> y, Node<T> x) {
        // TODO
    }

    // Cuts the node from its parent and moves it to the root list.
    // This is used during decreaseKey when a node violates the heap property.
    private void cut(Node<T> node, Node<T> parent) {
        // TODO
    }

    // Performs cascading cuts. When a node is cut from its parent, it may trigger cuts up the tree.
    // This method ensures all necessary nodes are cut recursively and added to the root list.
    private void cascadingCut(Node<T> node) {
        // TODO
    }

    // Ensures that there are no two trees in the root list with the same degree by merging them.
    // This is called in extractMin() after the minimum node has been removed.
    private void consolidate() {
        // TODO
    }

    // Adds a node to the root list of the heap. This is useful in decreaseKey, cut, and insert.
    private void addToRootList(Node<T> node) {
        // TODO
    }

    // Removes a node from the root list, used in extractMin and possibly other cleanup operations.
    private void removeFromRootList(Node<T> node) {
        // TODO
    }

    /**
     * Tester method currently for root list
     * @return
     */
    public String toString() {
        String ret = "";
        Node<T> current = min;
        for (int i = 0; i < size; i++) {
            ret += current.key + " ";
            current = current.right;
        }
        return ret;
    }
}

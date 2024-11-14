package datastructures;

public class FibonacciHeap<T> {
    private Node<T> min;
    private int size;

    /**
     * A node in a Fibonacci Heap which has a value and a key.
     * A node has a reference to its left and right neighbors
     * as well as its parent and child if any.
     * @param <T> the type of the value.
     */
    public static final class Node<T> {
        private int key;
        private T value;
        private Node<T> parent;
        private Node<T> child;
        private Node<T> left;
        private Node<T> right;
        private int degree;
        private boolean marked;

        /**
         * Initializes a Node with a value and key.
         * Since the root list and child lists are doubly circular linked lists,
         * left and right are automatically initialized to itself.
         * @param value the data value to store.
         * @param key the priority of this node.
         */
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

    /**
     * Initializes a new Fibonacci Heap with a size of 0 and min points to null.
     */
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
        addToRootList(newNode);
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

    /**
     * Cuts the specified node from its parent and moves it to the root list.
     * This operation is used during a decrease-key operation when a node
     * violates the heap property. The cut method also handles cascading cuts
     * if necessary, ensuring that any marked ancestors of the node are also
     * cut and added to the root list.
     *
     * @param node The node to be cut from its parent and added to the root list.
     * @param parent The parent of the node being cut.
     */
    private void cut(Node<T> node, Node<T> parent) {
        if (node.right == node) {
            parent.child = null;
        } else {
            node.right.left = node.left;
            node.left.right = node.right;
            if (parent.child == node) {
                parent.child = node.right;
            }
        }

        parent.degree--;
        node.parent = null;
        node.marked = false;

        addToRootList(node);

        if (parent.parent != null) { // Ensure we aren't going to mark a node in the root list
            if (parent.marked) { // Perform a cascading cut if the parent is marked already
                cut(parent, parent.parent);
            } else {
                parent.marked = true;
            }
        }
    }

    // Ensures that there are no two trees in the root list with the same degree by merging them.
    // This is called in extractMin() after the minimum node has been removed.
    private void consolidate() {
        // TODO
    }

    // decrease key, cut, insert
    /**
     * Adds a single node to the root list and updates min.
     * This node will be added to the right of the current node.
     * @param node a node with no neighbors.
     */
    private void addToRootList(Node<T> node) {
        if (min != null) {
            node.left = min;
            node.right = min.right;
            min.right.left = node;
            min.right = node;
            if (node.key < min.key) {
                min = node;
            }
        } else {
            min = node;
        }
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

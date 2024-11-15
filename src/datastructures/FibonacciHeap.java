package datastructures;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements a Fibonacci Heap, a data structure for priority queues that supports
 * efficient operations such as insert, extract-min, and decrease-key. Fibonacci
 * heaps provide better amortized time complexity for several operations compared
 * to other heap structures like binary heaps, especially in scenarios that involve
 * many decrease-key and extract-min operations.
 *
 * @param <T> The type of the values stored in the heap. This can be any object type.
 */
public class FibonacciHeap<T> {

    /**
     * The minimum node in the Fibonacci heap. This node has the smallest key value
     * among all the nodes in the heap. The `min` node is used to efficiently perform
     * the `extract-min` operation, which removes the node with the smallest key from the heap. <br>
     *
     * The `min` node is always maintained at the root level and is updated after
     * each operation (e.g., insert, extract-min, etc.).
     */
    private Node<T> min;

    /**
     * The total number of nodes in the Fibonacci heap.
     */
    private int size;

    /**
     * A node in a Fibonacci Heap which has a value and a key.
     * A node has a reference to its left and right neighbors
     * as well as its parent and child if any.
     * @param <T> the type of the value.
     */
    public static final class Node<T> {

        /**
         * The value stored in the node. This is the data associated with the node,
         * which may be of any generic type.
         */
        private final T value;

        /**
         * The key associated with the node. Used for ordering the node in the heap.
         */
        private int key;

        /**
         * The parent node of this node. This node's parent is the node in the heap's
         * tree structure that directly points to one of its siblings in the child list.
         * If the node is in the root list, this pointer is null.
         */
        private Node<T> parent;

        /**
         * The first child node of this node. If the node has no children, this pointer
         * is null. The child pointer is used to represent the node's subtree.
         */
        private Node<T> child;

        /**
         * A pointer to the left sibling of this node in the doubly linked circular list
         * of siblings. This is used to traverse the sibling list of a node.
         */
        private Node<T> left;

        /**
         * A pointer to the right sibling of this node in the doubly linked circular list
         * of siblings. This is used to traverse the sibling list of a node.
         */
        private Node<T> right;

        /**
         * The degree of the node. Represents the number of direct children the node has.
         * The degree is incremented when a new child is added to the node, and decremented
         * when a child is removed.
         */
        private int degree;

        /**
         * A flag indicating whether the node has lost a child since it was last marked.
         * This is used in the cascading cut mechanism during decrease-key operations.
         * The node is marked after losing a child, and the mark is reset when the node
         * is moved to the root list.
         */
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

    /**
     * Links two trees of the same degree by making node y a child of node x.
     * This method is used in the consolidate step of the Fibonacci heap to
     * reduce the number of trees in the root list by combining trees with the
     * same degree.
     * <p>
     * The method assumes that the key of x is less than or equal to the key of y,
     * making x the new parent of y. Node y is removed from the root list and added
     * as a child of x, and the degree of x is incremented to reflect the addition
     * of a new child.
     *
     * @param y The node to be made a child. This node will be removed from the root list.
     * @param x The node that will become the parent of y. This node's degree will be increased.
     */
    private void link(Node<T> y, Node<T> x) {
        y.right.left = y.left;
        y.left.right = y.right;

        y.parent = x;
        x.degree++;
        y.marked = false;

        if (x.child == null) {
            x.child = y;
            y.left = y;
            y.right = y;
        } else {
            y.left = x.child;
            y.right = x.child.right;
            x.child.right.left = y;
            x.child.right = y;
        }
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

        // Ensure we aren't going to cascade cut a node in the root list
        if (parent.parent != null) {
            if (parent.marked) { // Perform a cascading cut if the parent is marked already
                cut(parent, parent.parent);
            } else {
                parent.marked = true;
            }
        }
    }

    /**
     * Consolidates the trees in the Fibonacci heap. This operation merges trees of the same degree
     * in the root list into a single tree, ensuring that there is only one tree of each degree in the
     * root list. The method iterates over all the root nodes, links trees of the same degree, and updates
     * the minimum node. After consolidation, the heap will have a set of trees with unique degrees,
     * and the root list will contain the consolidated trees.
     * <p>
     * This operation maintains the Fibonacci heap property and reduces the number of trees in the root list.
     * The time complexity of this operation is O(log n), where n is the number of nodes in the heap.
     *
     * @see #link(Node, Node)
     */
    public void consolidate() {
        final int maxDegree = (int) Math.floor(Math.log(size) / Math.log(2)) + 1;
        final Node<T>[] degreeTable = new Node[maxDegree];
        final List<Node<T>> rootList = new ArrayList<>();
        Node<T> current = min;

        // Traverse all root nodes and add them to rootList.
        do {
            rootList.add(current);
            current = current.right;
        } while (current != min);

        // Consolidate the trees in the root list
        for (Node<T> node : rootList) {
            while (degreeTable[node.degree] != null) {
                Node<T> collisionNode = degreeTable[node.degree];
                degreeTable[node.degree] = null;

                if (collisionNode.key < node.key) {
                    link(node, collisionNode);
                    // node isn't in root list anymore, so we need to update it to node's parent.
                    node = node.parent;
                } else {
                    link(collisionNode, node);
                }
            }
            degreeTable[node.degree] = node;
            if (node.key < min.key) {
                min = node;
            }
        }
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

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import datastructures.FibonacciHeap;

class FibonacciHeapTest {

    @Test
    void testInsertAndMinimum() {
        FibonacciHeap<Integer> heap = new FibonacciHeap<>();
        assertTrue(heap.isEmpty());

        heap.insert(10, 10);
        assertFalse(heap.isEmpty());
        assertEquals(10, heap.minimum().getValue());

        heap.insert(5, 5);
        assertEquals(5, heap.minimum().getValue());

        heap.insert(15, 15);
        assertEquals(5, heap.minimum().getValue());
    }

    @Test
    void testExtractMin() {
        FibonacciHeap<String> heap = new FibonacciHeap<>();

        heap.insert("A", 10);
        heap.insert("B", 5);
        heap.insert("C", 15);

        assertEquals("B", heap.extractMin().getValue());
        assertEquals("A", heap.minimum().getValue());
        assertEquals("A", heap.extractMin().getValue());
        assertEquals("C", heap.extractMin().getValue());
        assertTrue(heap.isEmpty());
    }

    @Test
    void testDecreaseKey() {
        FibonacciHeap<Integer> heap = new FibonacciHeap<>();

        FibonacciHeap.Node<Integer> node1 = heap.insert(10, 10);
        FibonacciHeap.Node<Integer> node2 = heap.insert(20, 20);
        FibonacciHeap.Node<Integer> node3 = heap.insert(15, 15);

        heap.decreaseKey(node3, 5);
        assertEquals(5, heap.minimum().getKey());
        assertEquals(node3, heap.minimum());

        heap.decreaseKey(node2, 1);
        assertEquals(1, heap.minimum().getKey());
        assertEquals(node2, heap.minimum());
    }

    @Test
    void testDelete() {
        FibonacciHeap<String> heap = new FibonacciHeap<>();

        FibonacciHeap.Node<String> node1 = heap.insert("A", 10);
        FibonacciHeap.Node<String> node2 = heap.insert("B", 5);
        FibonacciHeap.Node<String> node3 = heap.insert("C", 15);

        heap.delete(node2);
        assertEquals("A", heap.minimum().getValue());

        heap.delete(node1);
        assertEquals("C", heap.minimum().getValue());

        heap.delete(node3);
        assertTrue(heap.isEmpty());
    }

    @Test
    void testUnion() {
        FibonacciHeap<Integer> heap1 = new FibonacciHeap<>();
        FibonacciHeap<Integer> heap2 = new FibonacciHeap<>();

        heap1.insert(10, 10);
        heap1.insert(20, 20);

        heap2.insert(5, 5);
        heap2.insert(15, 15);

        heap1.union(heap2);

        assertEquals(5, heap1.minimum().getKey());
        assertEquals(4, heap1.size());
    }

    @Test
    void testSizeAndClear() {
        FibonacciHeap<Integer> heap = new FibonacciHeap<>();
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());

        heap.insert(1, 1);
        heap.insert(2, 2);
        heap.insert(3, 3);

        assertFalse(heap.isEmpty());
        assertEquals(3, heap.size());

        heap.clear();
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
    }

    @Test
    void testToString() {
        FibonacciHeap<Integer> heap = new FibonacciHeap<>();
        heap.insert(1, 1);
        heap.insert(2, 2);
        heap.insert(3, 3);

        String heapString = heap.toString();
        assertTrue(heapString.contains("size=3"));
        assertTrue(heapString.contains("MinNode{value=1, key=1, degree=0, marked=false"));
        assertTrue(heapString.contains("RootNode{value=2, key=2, degree=0, marked=false"));
    }
}

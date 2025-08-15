import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class MoreBinaryMinHeapTest {
    private BinaryMinHeap<Integer> heap;

    @BeforeEach
    public void setUp() {
        heap = new BinaryMinHeap<>();
    }

    @Test
    public void testAdd() {
        heap.add(10);
        heap.add(5);
        heap.add(3);

        assertEquals(3, heap.get(), "The root should be the smallest element.");
        assertEquals(3, heap.size(), "Heap size should be 3.");
    }

    @Test
    public void testRemove() {
        heap.add(10);
        heap.add(5);
        heap.add(3);

        assertEquals(3, heap.remove(), "The root (smallest element) should be removed.");
        assertEquals(2, heap.size(), "Heap size should be 2 after removal.");
        assertEquals(5, heap.get(), "The new root should be the next smallest element.");
    }

    @Test
    public void testGet() {
        heap.add(10);
        heap.add(5);
        heap.add(3);

        assertEquals(3, heap.get(), "The root should be the smallest element.");
        heap.remove();
        assertEquals(5, heap.get(), "After removing the smallest element, the root should be the next smallest.");
    }

    @Test
    public void testIsEmpty() {
        assertTrue(heap.isEmpty(), "Heap should be empty initially.");
        heap.add(10);
        assertFalse(heap.isEmpty(), "Heap should not be empty after adding an element.");
        heap.remove();
        assertTrue(heap.isEmpty(), "Heap should be empty after removing the only element.");
    }

    @Test
    public void testSize() {
        assertEquals(0, heap.size(), "Heap size should be 0 initially.");
        heap.add(10);
        heap.add(5);
        heap.add(3);
        assertEquals(3, heap.size(), "Heap size should be 3 after adding 3 elements.");
        heap.remove();
        assertEquals(2, heap.size(), "Heap size should be 2 after removing one element.");
    }

    @Test
    public void testClear() {
        heap.add(10);
        heap.add(5);
        heap.add(3);

        heap.clear();
        assertTrue(heap.isEmpty(), "Heap should be empty after clear.");
        assertEquals(0, heap.size(), "Heap size should be 0 after clear.");
    }

    @Test
    public void testAddNullThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> heap.add(null), "Adding null should throw IllegalArgumentException.");
    }

    @Test
    public void testRemoveFromEmptyHeapThrowsException() {
        assertThrows(NoSuchElementException.class, () -> heap.remove(), "Removing from an empty heap should throw NoSuchElementException.");
    }

    @Test
    public void testGetFromEmptyHeapThrowsException() {
        assertThrows(NoSuchElementException.class, () -> heap.get(), "Getting from an empty heap should throw NoSuchElementException.");
    }

    @Test
    public void testHeapIterator() {
        heap.add(10);
        heap.add(5);
        heap.add(3);

        Iterator<Integer> iterator = heap.iterator();
        assertTrue(iterator.hasNext(), "Iterator should have elements.");
        assertEquals(3, iterator.next(), "Iterator should return the smallest element first.");
        assertTrue(iterator.hasNext(), "Iterator should have more elements.");
        assertEquals(5, iterator.next(), "Iterator should return the next smallest element.");
        assertTrue(iterator.hasNext(), "Iterator should have one more element.");
        assertEquals(10, iterator.next(), "Iterator should return the last element.");
        assertFalse(iterator.hasNext(), "Iterator should have no more elements.");
    }
}
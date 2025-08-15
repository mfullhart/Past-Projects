import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;

public class BinaryMinHeapTest {
    private BinaryMinHeap<Integer> heap;

    @BeforeEach
    public void setUp() {
        heap = new BinaryMinHeap<>();
    }

    @Test
    public void testAddSingleElement() {
        heap.add(10);
        assertEquals(1, heap.size());
        assertEquals(10, heap.get());
    }

    @Test
    public void testAddMultipleElements() {
        heap.add(10);
        heap.add(5);
        heap.add(20);
        heap.add(3);
        heap.add(8);

        assertEquals(5, heap.size());
        assertEquals(3, heap.get());
    }

    @Test
    public void testRemoveRoot() {
        heap.add(10);
        heap.add(5);
        heap.add(20);
        heap.add(3);
        heap.add(8);

        assertEquals(3, heap.remove());
        assertEquals(5, heap.get());
    }

    @Test
    public void testRemoveUntilEmpty() {
        heap.add(10);
        heap.add(5);
        heap.add(20);

        assertEquals(5, heap.remove());
        assertEquals(10, heap.remove());
        assertEquals(20, heap.remove());
        assertTrue(heap.isEmpty());
    }

    @Test
    public void testRemoveFromEmptyHeap() {
        assertThrows(NoSuchElementException.class, () -> heap.remove());
    }

    @Test
    public void testGetFromEmptyHeap() {
        assertThrows(NoSuchElementException.class, () -> heap.get());
    }

    @Test
    public void testAddNullElement() {
        assertThrows(IllegalArgumentException.class, () -> heap.add(null));
    }

    @Test
    public void testClearHeap() {
        heap.add(10);
        heap.add(5);
        heap.add(20);
        heap.clear();
        assertTrue(heap.isEmpty());
    }

    @Test
    public void testSizeAfterClear() {
        heap.add(10);
        heap.add(5);
        heap.add(20);
        heap.clear();
        assertEquals(0, heap.size());
    }

    @Test
    public void testAddRemoveAdd() {
        heap.add(10);
        heap.add(5);
        assertEquals(5, heap.remove());
        heap.add(20);
        assertEquals(10, heap.remove());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(heap.isEmpty());
        heap.add(5);
        assertFalse(heap.isEmpty());
    }

    @Test
    public void testGetMinValue() {
        heap.add(10);
        heap.add(5);
        heap.add(20);
        assertEquals(5, heap.get());
    }

    @Test
    public void testHeapAfterMultipleRemoves() {
        heap.add(10);
        heap.add(5);
        heap.add(20);
        heap.add(3);
        heap.remove(); // Removes 3
        heap.remove(); // Removes 5
        assertEquals(10, heap.get());
    }

    @Test
    public void testHeapOrderAfterAdds() {
        heap.add(30);
        heap.add(10);
        heap.add(20);
        heap.add(5);
        heap.add(25);
        assertEquals(5, heap.get()); // Min heap property
    }

    @Test
    public void testIterator() {
        heap.add(10);
        heap.add(5);
        heap.add(20);
        heap.add(3);

        int[] expected = {3, 5, 10, 20};
        int i = 0;
        for (Integer val : heap) {
            assertEquals(expected[i], val);
            i++;
        }
    }

    @Test
    public void testIteratorEmptyHeap() {
        heap.add(10);
        heap.add(5);
        heap.clear();

        assertFalse(heap.iterator().hasNext());
    }

    @Test
    public void testMultipleAddRemove() {
        heap.add(10);
        heap.add(20);
        heap.add(5);
        assertEquals(5, heap.remove());
        heap.add(8);
        heap.add(15);
        assertEquals(8, heap.remove());
    }

    @Test
    public void testAddLargeNumber() {
        heap.add(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, heap.get());
    }

    @Test
    public void testAddNegativeNumber() {
        heap.add(-10);
        heap.add(-5);
        heap.add(-20);
        assertEquals(-20, heap.get());
    }

    @Test
    public void testClearDoesNotThrowException() {
        heap.clear(); // Should not throw exceptions if the heap is empty
    }

    @Test
    public void testMultipleElementRemoval() {
        heap.add(10);
        heap.add(5);
        heap.add(20);
        heap.add(15);
        heap.add(8);
        assertEquals(5, heap.remove());
        assertEquals(8, heap.remove());
        assertEquals(10, heap.remove());
        assertEquals(15, heap.remove());
        assertEquals(20, heap.remove());
    }

    @Test
    public void testAddDuplicateElements() {
        heap.add(10);
        heap.add(5);
        heap.add(10);
        heap.add(20);
        heap.add(5);

        assertEquals(5, heap.get());
        heap.remove();
        assertEquals(5, heap.get());
    }

    @Test
    public void testHeapStateAfterAddRemove() {
        heap.add(10);
        heap.add(5);
        heap.add(20);
        heap.remove();
        assertEquals(10, heap.get());
    }

    @Test
    public void testSizeAfterMultipleAdds() {
        heap.add(5);
        heap.add(8);
        heap.add(10);
        heap.add(3);
        assertEquals(4, heap.size());
    }

    @Test
    public void testHeapAfterMultipleAddsAndRemoves() {
        heap.add(10);
        heap.add(5);
        heap.add(20);
        heap.remove();
        heap.add(15);
        heap.add(8);
        assertEquals(8, heap.remove());
    }

    @Test
    public void testAddRemoveAddOrder() {
        heap.add(10);
        heap.add(5);
        heap.add(20);
        heap.remove();
        heap.add(15);
        heap.remove();
        assertEquals(15, heap.remove());
    }

    @Test
    public void testIteratorExpectsAscendingOrder() {
        heap.add(10);
        heap.add(5);
        heap.add(20);
        heap.add(3);
        heap.add(8);

        Integer[] expected = {3, 5, 8, 10, 20};
        int i = 0;
        for (Integer value : heap) {
            assertEquals(expected[i], value);
            i++;
        }
    }

    @Test
    public void testIteratorDoesNotModifyHeap() {
        heap.add(10);
        heap.add(5);
        heap.add(20);
        heap.add(3);

        int sizeBeforeIteration = heap.size();
        for (Integer value : heap) {
            // Iterating does not modify the heap
        }
        assertEquals(sizeBeforeIteration, heap.size());
    }

    @Test
    public void testRemoveFromEmptyHeapThrowsException() {
        assertThrows(NoSuchElementException.class, () -> heap.remove());
    }

    @Test
    public void testRemoveAndThenGet() {
        heap.add(10);
        heap.add(5);
        heap.remove();
        assertEquals(10, heap.get());
    }

    @Test
    public void testAddAllElementsInAscendingOrder() {
        heap.add(1);
        heap.add(2);
        heap.add(3);
        assertEquals(1, heap.get());
        heap.remove();
        assertEquals(2, heap.get());
        heap.remove();
        assertEquals(3, heap.get());
    }

    @Test
    public void testAddAllElementsInDescendingOrder() {
        heap.add(3);
        heap.add(2);
        heap.add(1);
        assertEquals(1, heap.get());
        heap.remove();
        assertEquals(2, heap.get());
        heap.remove();
        assertEquals(3, heap.get());
    }
}

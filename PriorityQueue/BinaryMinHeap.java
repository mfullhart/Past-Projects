import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A functioning heap.
 *
 * @author mfullhart20@georgefox.edu
 *
 * @param <E> Type of elements in heap.
 */
public class BinaryMinHeap<E extends Comparable<E>> implements PriorityQueue<E>, Iterable<E>
{
    private E[] _elements;
    private int _size;
    private static final int DEFAULT_CAPACITY = 10;
    int _currentIndex;

    /**
     * Construct a binary heap.
     */
    @SuppressWarnings("unchecked")
    public BinaryMinHeap()
    {
        _elements = (E[]) new Comparable[DEFAULT_CAPACITY];
        _size = 0;
    }

    /**
     * Set initial capacity of BMH.
     *
     * @param initialCapacity Ser initial capacity of BMH.
     * @throws IllegalArgumentException Throw IAE is initial capacity is less than or equal to 0.
     */
    @SuppressWarnings("unchecked")
    public BinaryMinHeap(int initialCapacity)
    {
        if (DEFAULT_CAPACITY <= 0)
        {
            throw new IllegalArgumentException("Initial capacity must be greater than 0.");
        }

        _elements = (E[]) new Comparable[DEFAULT_CAPACITY]; // Initialize the backing array.
        _size = 0; // Set the initial size to 0.
    }

    /**
     * Add an element to the BMH.
     *
     * @param element The element to add to the BMH.
     * @throws IllegalArgumentException Throw IAE if element to be added is null.
     */
    public void add(E element)
    {
        if (element == null)
        {
            throw new IllegalArgumentException("Element cannot be null.");
        }

        if (_size == _elements.length)
        {
            grow(); // Grow the array if needed.
        }

        _elements[_size] = element; // Place the new element at the next available position.
        _currentIndex = _size; // Set currentIndex to the position of the new element.
        siftUp(_size); // Restore heap property.
        _size++;
    }

    /**
     * Grows BMH by 2 times.
     */
    @SuppressWarnings("unchecked")
    private void grow()
    {
        E[] newArray = (E[]) new Comparable[_elements.length * 2];
        System.arraycopy(_elements, 0, newArray, 0, _elements.length);
        _elements = newArray;
    }

    /**
     * Gets the root value of BMH.
     *
     * @return The root value of BMH.
     */
    @Override
    public E get()
    {
        if (_size ==0)
        {
            throw new NoSuchElementException("Heap is empty.");
        }

        return _elements[0];
    }

    /**
     * Remove and return root value of BMH.
     * @return The root value of BMH.
     */
    @Override
    public E remove()
    {
        if (_size == 0)
        {
            throw new NoSuchElementException("Heap is empty.");
        }

        E _oldValue = _elements[0]; // Store the root element to return later.
        _elements[0] = _elements[_size - 1]; // Replace the root with the last element.
        _elements[_size - 1] = null; // Clear the last element.
        _size--; // Decrease the size.
        siftDown(0); // Restore the heap property from the root down.
        return _oldValue;
    }

    /**
     * Clear the BMH and set size to 0.
     */
    @Override
    public void clear()
    {
        for (int i = 0; i < _size; i++)
        {
            _elements[i] = null; // Nullify all elements
        }

        _size = 0; // Reset the size.
    }

    /**
     * Get the size of the BMH.
     *
     * @return The size of the BMH.
     */
    @Override
    public int size()
    {
        return _size;
    }

    /**
     * Check if the BMH is empty.
     *
     * @return True if BMH is empty.
     */
    @Override
    public boolean isEmpty()
    {
        return _size == 0;
    }

    /**
     * Return an iterator that consumes the BMH.
     *
     * @return The iterator.
     */
    @Override
    public Iterator<E> iterator()
    {
        return new BinaryMinHeapIterator();
    }

    /**
     * Sift an element up to maintain the heap property.
     *
     * @param index Index of current location.
     */
    private void siftUp(int index)
    {
        int _parentIndex = (index - 1) / 2; // Compute the parent index.

        while (index > 0 && _elements[index].compareTo(_elements[_parentIndex]) < 0)
        {
            swap(index, _parentIndex); // Swap with the parent.
            index = _parentIndex; // Move to the parent.
            _parentIndex = (index - 1) / 2; // Recalculate the parent index.
        }
    }

    /**
     * Sift an element down to maintain the heap property.
     *
     * @param index Index of current location.
     */
    private void siftDown(int index)
    {
        int _leftChildIndex = 2 * index + 1; // Left child index
        int _rightChildIndex = _leftChildIndex + 1; // Right child index
        int _smallestIndex = index; // Start by assuming the current index is the smallest

        // Compare with left child
        if (_leftChildIndex < _size &&
                _elements[_leftChildIndex].compareTo(_elements[_smallestIndex]) < 0)
        {
            _smallestIndex = _leftChildIndex; // Left child is smaller than the current element
        }

        // Compare with right child
        if (_rightChildIndex < _size &&
                _elements[_rightChildIndex].compareTo(_elements[_smallestIndex]) < 0)
        {
            _smallestIndex = _rightChildIndex; // Right child is smaller than the current smallest
        }

        // If the smallest index is not the current index, swap and continue sifting down
        if (_smallestIndex != index)
        {
            swap(index, _smallestIndex); // Swap with the smallest child
            siftDown(_smallestIndex); // Continue sifting down
        }
    }

    /**
     * Swap two elements in the heap
     *
     * @param i First element to be swapped.
     * @param j Second element to be swapped.
     */
    private void swap(int i, int j)
    {
        E temp = _elements[i];
        _elements[i] = _elements[j];
        _elements[j] = temp;
    }

    /**
     * Iterator class for traversing the elements of BMH.
     */
    public class BinaryMinHeapIterator implements Iterator<E>
    {
        private E[] _sortedElements; // The sorted elements for iteration
        private int _currentIndex; // Current index in the sorted elements array

        /**
         * Constructor for the BMH iterator.
         */
        public BinaryMinHeapIterator()
        {
            // Sort the elements in ascending order for the iterator
            _sortedElements = Arrays.copyOf(_elements, _size); // Make a copy of the heap's elements
            Arrays.sort(_sortedElements); // Sort the copy in ascending order
            _currentIndex = 0; // Initialize the iterator at the start of the array
        }

        /**
         * Determines if there is a next value.
         *
         * @return true if there is a next value in BMH.
         */
        @Override
        public boolean hasNext()
        {
            return _currentIndex < _sortedElements.length; // Check if there are more elements
        }

        /**
         * Return the next element in the iteration
         *
         * @return The next element.
         * @throws NoSuchElementException Throw NSEE if there are no more elements.
         */
        @Override
        public E next()
        {
            if (!hasNext())
            {
                throw new NoSuchElementException("No more elements in the heap.");
            }

            return _sortedElements[_currentIndex++]; // Return the next element and increment the
            // index
        }
    }
}
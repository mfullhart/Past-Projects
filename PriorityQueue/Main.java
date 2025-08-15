public class Main {
    public static void main(String[] args) {
        // Create a BinaryMinHeap instance
        BinaryMinHeap<Integer> minHeap = new BinaryMinHeap<>();

        // Add elements to the heap
        System.out.println("Adding elements to the heap:");
        minHeap.add(10);
        minHeap.add(5);
        minHeap.add(20);
        minHeap.add(3);
        minHeap.add(8);

        // Display the size and the minimum element
        System.out.println("Heap size: " + minHeap.size());
        System.out.println("Minimum element: " + minHeap.get());

        // Remove the root element and display the heap state
        System.out.println("\nRemoving the root element:");
        System.out.println("Removed element: " + minHeap.remove());
        System.out.println("New minimum element: " + minHeap.get());
        System.out.println("Heap size after removal: " + minHeap.size());

        // Iterate through the heap and consume elements
        System.out.println("\nIterating through the heap (consumes the heap):");
        for (Integer value : minHeap) {
            System.out.print(value + " ");
        }
        System.out.println();

        // Check if the heap is empty
        System.out.println("\nIs the heap empty? " + minHeap.isEmpty());

        // Clear the heap
        System.out.println("\nClearing the heap...");
        minHeap.clear();
        System.out.println("Heap size after clearing: " + minHeap.size());
    }
}

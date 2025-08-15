# Sorting
![Sorting Algorithm Performance (with insertion).png](Sorting%20Algorithm%20Performance%20%28with%20insertion%29.png)
![Sorting Algorithm Performance (without insertion).png](Sorting%20Algorithm%20Performance%20%28without%20insertion%29.png)

**Insertion Sort's Performance**

As anticipated, Insertion Sort performs the worst among the 4 tested methods. While it runs in negligible time for 
small inputs, its run time grows drastically as the input size increases. This confirms its known O(n²) 
average-case complexity, making it impractical for large datasets. The steep curve in the first graph shows its 
rapid degradation, which is probably why we excluded it from the second graph for better clarity of the 3 other
sorting algorithms.

**Comparison of QuickSort Implementations**

The recursive QuickSort and iterative QuickSort exhibit similar performance trends, both showing an approximate
O(n log n) growth pattern. The recursive version slightly outperforms the iterative approach in most cases. 
This may be due to the additional overhead introduced by manually managing the recursion stack in the iterative 
implementation.

**Go’s Built-in Sorting (sort.Ints)**

Go’s sort.Ints function performs slightly better than both QuickSort implementations. This is expected, as Go's 
built-in sort function is optimized and uses a variation of merge sort. The minor advantage it holds is 
noticeable at larger input sizes, where it outperforms both QuickSort implementations.

**Conclusion**

The measured times align well with the theoretical expectations for O(n log n) sorting algorithms like QuickSort 
and Go’s sort.Ints. InsertionSort’s O(n²) behavior is evident in its exponential growth, making it unsuitable for 
large datasets. The built-in sorting function’s efficiency confirms that optimized library implementations are 
often superior to implementations of standard algorithms.
package sorting

import (
	"fmt"
	"math/rand"
	"sort"
	"testing"
	"time"
)

const (
	numRuns       = 5
	minSize       = 100
	maxSize       = 100000
	sizeIncrement = 10000
)

func TestSortingAlgorithms(t *testing.T) {
	rand.Seed(time.Now().UnixNano())

	for size := minSize; size <= maxSize; size += sizeIncrement {
		var totalTime [4]time.Duration // [QuickSortRecursive, QuickSort, InsertionSort, sort.Ints]

		for run := 0; run < numRuns; run++ {
			arr := generateRandomArray(size)

			// Test QuickSortRecursive
			arr1 := make([]int, size)
			copy(arr1, arr)
			start := time.Now()
			QuickSortRecursive(arr1)
			totalTime[0] += time.Since(start)
			if !isSorted(arr1) {
				t.Errorf("QuickSortRecursive failed to sort array of size %d", size)
			}

			// Test QuickSort (iterative)
			arr2 := make([]int, size)
			copy(arr2, arr)
			start = time.Now()
			QuickSort(arr2)
			totalTime[1] += time.Since(start)
			if !isSorted(arr2) {
				t.Errorf("QuickSort (iterative) failed to sort array of size %d", size)
			}

			// Test InsertionSort (now for all sizes)
			arr3 := make([]int, size)
			copy(arr3, arr)
			start = time.Now()
			InsertionSort(arr3)
			totalTime[2] += time.Since(start)
			if !isSorted(arr3) {
				t.Errorf("InsertionSort failed to sort array of size %d", size)
			}

			// Test sort.Ints
			arr4 := make([]int, size)
			copy(arr4, arr)
			start = time.Now()
			sort.Ints(arr4)
			totalTime[3] += time.Since(start)
			if !isSorted(arr4) {
				t.Errorf("sort.Ints failed to sort array of size %d", size)
			}
		}

		// Log results
		avgQuickRec := totalTime[0] / numRuns
		avgQuickIter := totalTime[1] / numRuns
		avgInsertion := totalTime[2] / numRuns
		avgBuiltin := totalTime[3] / numRuns

		t.Logf("Size: %d", size)
		t.Logf("  QuickSortRecursive: %v", avgQuickRec)
		t.Logf("  QuickSort (iter):   %v", avgQuickIter)
		t.Logf("  InsertionSort:     %v", avgInsertion)
		t.Logf("  sort.Ints:         %v", avgBuiltin)
	}
}

func BenchmarkSortingAlgorithms(b *testing.B) {
	sizes := []int{100, 1000, 10000, 50000, 100000}

	for _, size := range sizes {
		b.Run(fmt.Sprintf("Size-%d", size), func(b *testing.B) {
			arr := generateRandomArray(size)
			arr1 := make([]int, size)
			arr2 := make([]int, size)
			arr3 := make([]int, size)
			arr4 := make([]int, size)

			b.Run("QuickSortRecursive", func(b *testing.B) {
				for i := 0; i < b.N; i++ {
					copy(arr1, arr)
					QuickSortRecursive(arr1)
				}
			})

			b.Run("QuickSortIterative", func(b *testing.B) {
				for i := 0; i < b.N; i++ {
					copy(arr2, arr)
					QuickSort(arr2)
				}
			})

			// InsertionSort now runs for all sizes
			b.Run("InsertionSort", func(b *testing.B) {
				for i := 0; i < b.N; i++ {
					copy(arr3, arr)
					InsertionSort(arr3)
				}
			})

			b.Run("SortInts", func(b *testing.B) {
				for i := 0; i < b.N; i++ {
					copy(arr4, arr)
					sort.Ints(arr4)
				}
			})
		})
	}
}

func generateRandomArray(size int) []int {
	arr := make([]int, size)
	for i := 0; i < size; i++ {
		arr[i] = rand.Intn(size * 10)
	}
	return arr
}

package sorting

// QuickSortRecursive implements recursive QuickSort
func QuickSortRecursive(values []int) {
	// Base case: already sorted or empty
	if len(values) <= 1 {
		return
	}

	// Partition the array and get the pivot index
	pivot := partition(values)
	// Recursively sort the left partition
	QuickSortRecursive(values[:pivot])
	// Recursively sort the right partition
	QuickSortRecursive(values[pivot+1:])
}

// QuickSort implements iterative QuickSort using a stack
func QuickSort(values []int) {
	// Initialize the stack with the full array
	stack := []int{0, len(values) - 1}

	for len(stack) > 0 {
		right, left := stack[len(stack)-1], stack[len(stack)-2]
		stack = stack[:len(stack)-2]

		if left < right {
			pivot := partition(values[left:right+1]) + left
			stack = append(stack, left, pivot-1, pivot+1, right)
		}
	}
}

// InsertionSort implements the insertion sort algorithm
func InsertionSort(values []int) {
	// Iterate through the array, inserting each element in its correct position
	for i := 1; i < len(values); i++ {
		key, j := values[i], i-1

		for j >= 0 && values[j] > key {
			// Shift elements to the right to create space for the key
			values[j+1] = values[j]
			j--
		}

		values[j+1] = key
	}
}

// Partition function selects a pivot and partitions the array around it
func partition(values []int) int {
	pivot, i := values[len(values)-1], -1

	for j := 0; j < len(values)-1; j++ {
		if values[j] < pivot {
			i++
			values[i], values[j] = values[j], values[i]
		}
	}

	// Swap the pivot to its correct position in the sorted array.
	values[i+1], values[len(values)-1] = values[len(values)-1], values[i+1]
	return i + 1
}

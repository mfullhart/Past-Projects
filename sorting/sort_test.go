package sorting

import (
	"math/rand"
	"sort"
	"testing"
)

const ARRAY_SIZE = 100
const NUM_TRIALS = 1000

func Test_Quicksort(t *testing.T) {

	defer func() {
		if r := recover(); r != nil {
			t.Errorf("During Quicksort test: panic recovered: %v", r)
		}
	}()

	//test Quicksort multiple times
	for i := 0; i < NUM_TRIALS; i++ {

		arr := makeRandomArray(ARRAY_SIZE)

		//make a copy and sort it to check the answer
		dup := make([]int, ARRAY_SIZE)
		copy(dup, arr)
		sort.Ints(dup)

		QuickSortRecursive(arr)

		if !isSorted(arr) || !arrayEquals(arr, dup) {
			t.Fatal("Quicksort failed to sort array")
		}

	}
}

func isSorted(arr []int) bool {
	for i := 0; i < len(arr)-1; i++ {
		if arr[i] > arr[i+1] {
			return false
		}
	}

	return true
}

func makeRandomArray(size int) []int {
	arr := make([]int, size)

	for i := 0; i < size; i++ {
		arr[i] = rand.Intn(size) * randomSign()
	}

	return arr
}

func randomSign() int {
	if rand.Int()%2 == 0 {
		return 1
	} else {
		return -1
	}
}

func arrayEquals(a []int, b []int) bool {
	if len(a) != len(b) {
		return false
	}
	for i := 0; i < len(a); i++ {
		if a[i] != b[i] {
			return false
		}
	}

	return true
}

package set

import (
	"testing"
)

//Note that each test case must start with "Test"
//also the file needs to end with "_test.go"

// TestAddToEmptySet tests if a single element can be added to an empty set
func TestAddToEmptySet(t *testing.T) {
	ex := NewSet[int]()

	ex.Add(0)

	if !ex.Contains(0) {
		t.Error("After adding, an empty set should now contain a single value")
	}
}

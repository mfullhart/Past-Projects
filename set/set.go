package set

type Set[T comparable] struct {
	elements map[T]bool
}

// NewSet acts as a constructor, creates new empty set
func NewSet[T comparable]() Set[T] {
	return Set[T]{elements: make(map[T]bool)}
}

// Equal determines if two sets have exactly the same elements
func (set *Set[T]) Equal(other *Set[T]) bool {
	if set.Len() != other.Len() {
		return false
	}
	for elem := range set.elements {
		if !other.Contains(elem) {
			return false
		}
	}
	return true
}

// UnEqual i.e. the negation of the equals operator
func (set *Set[T]) UnEqual(other *Set[T]) bool {
	return !set.Equal(other)
}

// Len returns the number of items in the set
func (set *Set[T]) Len() int {
	return len(set.elements)
}

// Add mutates the set by adding a new value to it
func (set *Set[T]) Add(values ...T) {
	for _, value := range values {
		set.elements[value] = true
	}
}

// Extend adds a new value a new copy of the set
func (set *Set[T]) Extend(value T) Set[T] {
	newSet := NewSet[T]()
	for elem := range set.elements {
		newSet.Add(elem)
	}
	newSet.Add(value)
	return newSet
}

// Remove creates a new set without the specified value
func (set *Set[T]) Remove(value T) Set[T] {
	newSet := NewSet[T]()
	for elem := range set.elements {
		if elem != value {
			newSet.Add(elem)
		}
	}
	return newSet
}

// Delete removes the specified value from the set (mutates the set)
func (set *Set[T]) Delete(value T) {
	delete(set.elements, value)
}

// Contains determines if a value is already in the set
func (set *Set[T]) Contains(value T) bool {
	for index := range set.elements {
		if index == value {
			return true
		}
	}
	return false
}

// Intersect returns all the values common to both sets
func (set *Set[T]) Intersect(other *Set[T]) Set[T] {
	result := NewSet[T]()
	for elem := range set.elements { // 0(n)
		if other.Contains(elem) { // 0(1)
			result.Add(elem) // 0(1)
		}
	}
	return result
}

// Union returns a set with all the elements from both sets
func (set *Set[T]) Union(other *Set[T]) Set[T] {
	result := NewSet[T]()
	for elem := range set.elements {
		result.Add(elem)
	}
	for elem := range other.elements {
		result.Add(elem)
	}
	return result
}

// Difference returns a set with elements that only exist in "this" set
func (set *Set[T]) Difference(other *Set[T]) Set[T] {
	result := NewSet[T]()
	for elem := range set.elements {
		if !other.Contains(elem) {
			result.Add(elem)
		}
	}
	return result
}

// ToArray returns the content of the set as an array (slice)
func (set *Set[T]) ToArray() []T {
	arr := make([]T, 0, len(set.elements))
	for elem := range set.elements {
		arr = append(arr, elem)
	}
	return arr
}

// Subsets returns slice that contains all the subset of a given size i.e. combinations
func (set *Set[T]) Subsets(size int) []Set[T] {
	// Convert the set to a slice of its elements
	values := set.ToArray()

	// Call the helper function to generate subsets
	return subsetsHelper(NewSet[T](), values, 0, size)
}

// subsetsHelper is a recursive function that generates all subsets of a specified size
func subsetsHelper[T comparable](currentSet Set[T], values []T, current int, size int) []Set[T] {
	// If the size of the current set is the desired size, return an empty set (base case)
	if currentSet.Len() == size {
		return []Set[T]{currentSet}
	}

	var subsets []Set[T]

	// Iterate through the values, starting from the current index
	for i := current; i < len(values); i++ {
		// Create a copy of the current set and add the current element
		newSet := NewSet[T]()
		newSet.Add(currentSet.ToArray()...) // Copy the elements from the current set
		newSet.Add(values[i])               // Add the current element to the set

		// Recursively find the remaining subsets
		recSubs := subsetsHelper(newSet, values, i+1, size)

		// Add the recursively found subsets to the result
		subsets = append(subsets, recSubs...)
	}

	// Return the list of subsets
	return subsets
}

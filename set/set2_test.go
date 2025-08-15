package set

import (
	"testing"
)

func TestSet(t *testing.T) {
	t.Run("Test NewSet", func(t *testing.T) {
		s := NewSet[int]()
		if s.Len() != 0 {
			t.Errorf("expected empty set, got length %d", s.Len())
		}
	})

	t.Run("Test Add and Contains", func(t *testing.T) {
		s := NewSet[int]()
		s.Add(1, 2, 3)
		if !s.Contains(1) || !s.Contains(2) || !s.Contains(3) {
			t.Errorf("expected set to contain 1, 2, 3")
		}
		if s.Len() != 3 {
			t.Errorf("expected length 3, got %d", s.Len())
		}
	})

	t.Run("Test Delete", func(t *testing.T) {
		s := NewSet[int]()
		s.Add(1, 2, 3)
		s.Delete(2)
		if s.Contains(2) {
			t.Errorf("expected set to not contain 2")
		}
		if s.Len() != 2 {
			t.Errorf("expected length 2, got %d", s.Len())
		}
	})

	t.Run("Test Remove", func(t *testing.T) {
		s := NewSet[int]()
		s.Add(1, 2, 3)
		newSet := s.Remove(2)
		if newSet.Contains(2) {
			t.Errorf("expected new set to not contain 2")
		}
		if newSet.Len() != 2 {
			t.Errorf("expected length 2, got %d", newSet.Len())
		}
		if !s.Contains(2) {
			t.Errorf("expected original set to still contain 2")
		}
	})

	t.Run("Test Equal and UnEqual", func(t *testing.T) {
		s1 := NewSet[int]()
		s2 := NewSet[int]()
		s1.Add(1, 2, 3)
		s2.Add(1, 2, 3)
		if !s1.Equal(&s2) {
			t.Errorf("expected sets to be equal")
		}
		s2.Add(4)
		if s1.Equal(&s2) {
			t.Errorf("expected sets to be unequal")
		}
	})

	t.Run("Test Union", func(t *testing.T) {
		s1 := NewSet[int]()
		s2 := NewSet[int]()
		s1.Add(1, 2)
		s2.Add(3, 4)
		union := s1.Union(&s2)
		expected := NewSet[int]()
		expected.Add(1, 2, 3, 4)
		if !union.Equal(&expected) {
			t.Errorf("expected %v, got %v", expected.ToArray(), union.ToArray())
		}
	})

	t.Run("Test Intersect", func(t *testing.T) {
		s1 := NewSet[int]()
		s2 := NewSet[int]()
		s1.Add(1, 2, 3)
		s2.Add(2, 3, 4)
		intersect := s1.Intersect(&s2)
		expected := NewSet[int]()
		expected.Add(2, 3)
		if !intersect.Equal(&expected) {
			t.Errorf("expected %v, got %v", expected.ToArray(), intersect.ToArray())
		}
	})

	t.Run("Test Difference", func(t *testing.T) {
		s1 := NewSet[int]()
		s2 := NewSet[int]()
		s1.Add(1, 2, 3)
		s2.Add(2, 3, 4)
		diff := s1.Difference(&s2)
		expected := NewSet[int]()
		expected.Add(1)
		if !diff.Equal(&expected) {
			t.Errorf("expected %v, got %v", expected.ToArray(), diff.ToArray())
		}
	})

	t.Run("Test Subsets", func(t *testing.T) {
		s := NewSet[int]()
		s.Add(1, 2)
		subsets := s.Subsets(1)
		expected := []Set[int]{
			func() Set[int] { set := NewSet[int](); set.Add(1); return set }(),
			func() Set[int] { set := NewSet[int](); set.Add(2); return set }(),
		}
		if len(subsets) != len(expected) {
			t.Errorf("expected %d subsets, got %d", len(expected), len(subsets))
		}
		for i, subset := range subsets {
			if !subset.Equal(&expected[i]) {
				t.Errorf("expected subset %v, got %v", expected[i].ToArray(), subset.ToArray())
			}
		}
	})

	t.Run("Test ToArray", func(t *testing.T) {
		s := NewSet[int]()
		s.Add(1, 2, 3)
		arr := s.ToArray()

		// Create a new set from the array and compare
		newSet := NewSet[int]()
		for _, v := range arr {
			newSet.Add(v)
		}

		if !s.Equal(&newSet) {
			t.Errorf("expected %v, got %v", s.ToArray(), arr)
		}
	})
}

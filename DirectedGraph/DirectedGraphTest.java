import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Iterator;

class DirectedGraphTest {
    private DirectedGraph<String, Integer> graph;

    @BeforeEach
    void setUp() {
        graph = new MatrixGraph<>();
    }

    // Adding vertices
    @Test
    void testAddVertex() {
        graph.add("A");
        assertTrue(graph.contains("A"));
    }

    @Test
    void testAddDuplicateVertexThrowsException() {
        graph.add("A");
        assertThrows(DuplicateVertexException.class, () -> graph.add("A"));
    }

    @Test
    void testAddNullVertexThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> graph.add(null));
    }

    // Removing vertices
    @Test
    void testRemoveVertex() {
        graph.add("A");
        assertEquals("A", graph.remove("A"));
        assertFalse(graph.contains("A"));
    }

    @Test
    void testRemoveNonExistentVertexThrowsException() {
        assertThrows(NoSuchVertexException.class, () -> graph.remove("A"));
    }

    // Checking contains
    @Test
    void testContainsVertex() {
        graph.add("A");
        assertTrue(graph.contains("A"));
        assertFalse(graph.contains("B"));
    }

    // Getting vertices
    @Test
    void testGetVertex() {
        graph.add("A");
        assertEquals("A", graph.get("A").getLabel());
    }

    @Test
    void testGetNonExistentVertexThrowsException() {
        assertThrows(NoSuchVertexException.class, () -> graph.get("A"));
    }

    // Adding edges
    @Test
    void testAddEdge() {
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 10);
        assertTrue(graph.containsEdge("A", "B"));
    }

    @Test
    void testAddDuplicateEdgeThrowsException() {
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 10);
        assertThrows(DuplicateEdgeException.class, () -> graph.addEdge("A", "B", 20));
    }

    @Test
    void testAddEdgeWithNonExistentVertexThrowsException() {
        graph.add("A");
        assertThrows(NoSuchVertexException.class, () -> graph.addEdge("A", "B", 10));
    }

    @Test
    void testAddEdgeWithNullLabelThrowsException() {
        graph.add("A");
        graph.add("B");
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge("A", "B", null));
    }

    // Removing edges
    @Test
    void testRemoveEdge() {
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 10);
        assertEquals(10, graph.removeEdge("A", "B"));
        assertFalse(graph.containsEdge("A", "B"));
    }

    @Test
    void testRemoveNonExistentEdgeThrowsException() {
        graph.add("A");
        graph.add("B");
        assertThrows(NoSuchEdgeException.class, () -> graph.removeEdge("A", "B"));
    }

    @Test
    void testRemoveEdgeWithNonExistentVertexThrowsException() {
        graph.add("A");
        assertThrows(NoSuchVertexException.class, () -> graph.removeEdge("A", "B"));
    }

    // Checking containsEdge
    @Test
    void testContainsEdge() {
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 10);
        assertTrue(graph.containsEdge("A", "B"));
    }

    @Test
    void testContainsEdgeWithNonExistentVerticesThrowsException() {
        assertThrows(NoSuchVertexException.class, () -> graph.containsEdge("A", "B"));
    }

    // Getting edges
    @Test
    void testGetEdge() {
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 10);
        Edge<String, Integer> edge = graph.getEdge("A", "B");
        assertEquals(10, edge.getLabel());
        assertEquals("A", edge.getU());
        assertEquals("B", edge.getV());
    }

    @Test
    void testGetNonExistentEdgeThrowsException() {
        graph.add("A");
        graph.add("B");
        assertThrows(NoSuchEdgeException.class, () -> graph.getEdge("A", "B"));
    }

    // Graph size
    @Test
    void testGraphSize() {
        assertEquals(0, graph.size());
        graph.add("A");
        graph.add("B");
        assertEquals(2, graph.size());
    }

    @Test
    void testEdgeCount() {
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 10);
        assertEquals(1, graph.edgeCount());
    }

    @Test
    void testGraphIsEmpty() {
        assertTrue(graph.isEmpty());
        graph.add("A");
        assertFalse(graph.isEmpty());
    }

    // Adjacency and iteration
    @Test
    void testAdjacentVertices() {
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 10);
        assertTrue(graph.adjacent("A").hasNext());
    }

    @Test
    void testIterateVertices() {
        graph.add("A");
        graph.add("B");
        Iterator<Vertex<String>> it = graph.vertices();
        assertTrue(it.hasNext());
        assertEquals("A", it.next().getLabel());
    }

    @Test
    void testIterateEdges() {
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 10);
        Iterator<Edge<String, Integer>> it = graph.edges();
        assertTrue(it.hasNext());
    }

    // 1. Test clearing the graph
    @Test
    void testClearGraph() {
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 10);
        graph.clear();
        assertTrue(graph.isEmpty());
        assertEquals(0, graph.size());
        assertEquals(0, graph.edgeCount());
    }

    // 2. Test adjacency after edge removal
    @Test
    void testAdjacencyAfterEdgeRemoval() {
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 10);
        graph.removeEdge("A", "B");
        assertFalse(graph.adjacent("A").hasNext());
    }

    // 3. Test iterator over an empty graph
    @Test
    void testEmptyGraphIteration() {
        assertFalse(graph.vertices().hasNext());
        assertFalse(graph.edges().hasNext());
    }

    // 4. Test adding multiple edges from one vertex
    @Test
    void testMultipleEdgesFromOneVertex() {
        graph.add("A");
        graph.add("B");
        graph.add("C");
        graph.addEdge("A", "B", 10);
        graph.addEdge("A", "C", 20);
        Iterator<Vertex<String>> adjacent = graph.adjacent("A");
        List<String> adjacentLabels = new ArrayList<>();
        while (adjacent.hasNext()) {
            adjacentLabels.add(adjacent.next().getLabel());
        }
        assertTrue(adjacentLabels.contains("B"));
        assertTrue(adjacentLabels.contains("C"));
    }

    // 5. Test edge count with multiple edges
    @Test
    void testEdgeCountWithMultipleEdges() {
        graph.add("A");
        graph.add("B");
        graph.add("C");
        graph.addEdge("A", "B", 10);
        graph.addEdge("A", "C", 20);
        assertEquals(2, graph.edgeCount());
    }

    // 6. Test degree of a vertex with multiple edges
    @Test
    void testDegreeWithMultipleEdges() {
        graph.add("A");
        graph.add("B");
        graph.add("C");
        graph.addEdge("A", "B", 10);
        graph.addEdge("A", "C", 20);
        assertEquals(2, graph.degree("A"));
    }

    // 7. Test adding reverse edges
    @Test
    void testAddingReverseEdges() {
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 10);
        graph.addEdge("B", "A", 20);
        assertTrue(graph.containsEdge("A", "B"));
        assertTrue(graph.containsEdge("B", "A"));
    }

    // 8. Test iteration order for vertices
    @Test
    void testVertexIterationOrder() {
        graph.add("A");
        graph.add("B");
        graph.add("C");
        Iterator<Vertex<String>> it = graph.vertices();
        List<String> order = new ArrayList<>();
        while (it.hasNext()) {
            order.add(it.next().getLabel());
        }
        assertEquals(List.of("A", "B", "C"), order);
    }

    // 9. Test edge equality
    @Test
    void testEdgeEquality() {
        Edge<String, Integer> edge1 = new Edge<>("A", "B", 10);
        Edge<String, Integer> edge2 = new Edge<>("A", "B", 10);
        assertEquals(edge1, edge2);
    }

    // 10. Test edge inequality with different labels
    @Test
    void testEdgeInequalityWithDifferentLabels() {
        Edge<String, Integer> edge1 = new Edge<>("A", "B", 10);
        Edge<String, Integer> edge2 = new Edge<>("A", "B", 20);
        assertNotEquals(edge1, edge2);
    }

    // 11. Test vertex equality
    @Test
    void testVertexEquality() {
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("A");
        assertEquals(vertex1, vertex2);
    }

    // 12. Test vertex inequality
    @Test
    void testVertexInequality() {
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        assertNotEquals(vertex1, vertex2);
    }

    // 13. Test adding self-loop edge
    @Test
    void testAddingSelfLoopEdge() {
        graph.add("A");
        graph.addEdge("A", "A", 10);
        assertTrue(graph.containsEdge("A", "A"));
    }

    // 14. Test removing self-loop edge
    @Test
    void testRemovingSelfLoopEdge() {
        graph.add("A");
        graph.addEdge("A", "A", 10);
        graph.removeEdge("A", "A");
        assertFalse(graph.containsEdge("A", "A"));
    }

    // 15. Test iterator over edges in reverse direction
    @Test
    void testEdgeIterationWithReverseEdges() {
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 10);
        graph.addEdge("B", "A", 20);
        Iterator<Edge<String, Integer>> edges = graph.edges();
        List<Integer> labels = new ArrayList<>();
        while (edges.hasNext()) {
            labels.add(edges.next().getLabel());
        }
        assertTrue(labels.contains(10));
        assertTrue(labels.contains(20));
    }

    // 16. Test removing all edges
    @Test
    void testRemoveAllEdges() {
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 10);
        graph.removeEdge("A", "B");
        assertEquals(0, graph.edgeCount());
    }

    // 17. Test removing a vertex removes associated edges
    @Test
    void testRemoveVertexRemovesEdges() {
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 10);
        graph.remove("A");
        assertFalse(graph.containsEdge("A", "B"));
    }

    // 18. Test removing a non-existent edge with existing vertices
    @Test
    void testRemoveNonExistentEdgeWithVertices() {
        graph.add("A");
        graph.add("B");
        assertThrows(NoSuchEdgeException.class, () -> graph.removeEdge("A", "B"));
    }

    // 19. Test adding edges with the same label
    @Test
    void testAddEdgesWithSameLabel() {
        graph.add("A");
        graph.add("B");
        graph.add("C");
        graph.addEdge("A", "B", 10);
        graph.addEdge("B", "C", 10);
        assertEquals(2, graph.edgeCount());
    }

    // 20. Test isEmpty on newly cleared graph
    @Test
    void testIsEmptyAfterClear() {
        graph.add("A");
        graph.clear();
        assertTrue(graph.isEmpty());
    }

    // 21. Test adjacency for non-existent vertex
    @Test
    void testAdjacencyForNonExistentVertexThrowsException() {
        assertThrows(NoSuchVertexException.class, () -> graph.adjacent("A"));
    }

    // 22. Test removing all vertices
    @Test
    void testRemoveAllVertices() {
        graph.add("A");
        graph.add("B");
        graph.remove("A");
        graph.remove("B");
        assertTrue(graph.isEmpty());
    }

    // 23. Test size after adding and removing vertices
    @Test
    void testSizeAfterAddingAndRemovingVertices() {
        graph.add("A");
        graph.add("B");
        graph.remove("A");
        assertEquals(1, graph.size());
    }

    // 24. Test edge count after adding and removing edges
    @Test
    void testEdgeCountAfterAddingAndRemovingEdges() {
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 10);
        graph.removeEdge("A", "B");
        assertEquals(0, graph.edgeCount());
    }

    // 25. Test vertex degree after removing edges
    @Test
    void testDegreeAfterRemovingEdges() {
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 10);
        graph.removeEdge("A", "B");
        assertEquals(0, graph.degree("A"));
    }
}

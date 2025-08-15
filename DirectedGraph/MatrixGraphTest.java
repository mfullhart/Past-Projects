import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;

class MatrixGraphTest {
    private MatrixGraph<String, Integer> graph;

    @BeforeEach
    void setUp() {
        graph = new MatrixGraph<>(); // Initialize with a matrix size of 10x10
    }

    // Test adding vertices
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

    // Test removing vertices
    @Test
    void testRemoveVertex() {
        graph.add("A");
        graph.remove("A");
        assertFalse(graph.contains("A"));
    }

    @Test
    void testRemoveNonExistentVertexThrowsException() {
        assertThrows(NoSuchVertexException.class, () -> graph.remove("A"));
    }

    // Test checking if vertex exists
    @Test
    void testContainsVertex() {
        graph.add("A");
        assertTrue(graph.contains("A"));
        assertFalse(graph.contains("B"));
    }

    // Test adding edges
    @Test
    void testAddEdge() {
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 10);
        assertTrue(graph.containsEdge("A", "B"));
    }

    @Test
    void testAddEdgeWithNullLabelThrowsException() {
        graph.add("A");
        graph.add("B");
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge("A", "B", null));
    }

    @Test
    void testAddEdgeWithNonExistentVertexThrowsException() {
        graph.add("A");
        assertThrows(NoSuchVertexException.class, () -> graph.addEdge("A", "B", 10));
    }

    @Test
    void testAddDuplicateEdgeThrowsException() {
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 10);
        assertThrows(DuplicateEdgeException.class, () -> graph.addEdge("A", "B", 10));
    }

    // Test removing edges
    @Test
    void testRemoveEdge() {
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 10);
        Integer removedLabel = graph.removeEdge("A", "B");
        assertEquals(10, removedLabel);
        assertFalse(graph.containsEdge("A", "B"));
    }

    @Test
    void testRemoveNonExistentEdgeThrowsException() {
        assertThrows(NoSuchEdgeException.class, () -> graph.removeEdge("A", "B"));
    }

    // Test getting edges
    @Test
    void testGetEdge() {
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 10);
        Edge<String, Integer> edge = graph.getEdge("A", "B");
        assertNotNull(edge);
        assertEquals("A", edge.getU());
        assertEquals("B", edge.getV());
        assertEquals(10, edge.getLabel());
    }

    @Test
    void testGetNonExistentEdgeThrowsException() {
        graph.add("A");
        graph.add("B");
        assertThrows(NoSuchEdgeException.class, () -> graph.getEdge("A", "B"));
    }

    // Test graph size
    @Test
    void testGraphSize() {
        assertEquals(0, graph.size());
        graph.add("A");
        graph.add("B");
        assertEquals(2, graph.size());
    }

    // Test degree of a vertex
    @Test
    void testDegree() {
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 10);
        assertEquals(1, graph.degree("A"));
    }

    @Test
    void testDegreeOfNonExistentVertexThrowsException() {
        assertThrows(NoSuchVertexException.class, () -> graph.degree("A"));
    }

    // Test edge count
    @Test
    void testEdgeCount() {
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 10);
        assertEquals(1, graph.edgeCount());
    }

    // Test iterating over vertices
    @Test
    void testVertexIterator() {
        graph.add("A");
        graph.add("B");
        Iterator<Vertex<String>> it = graph.vertices();
        assertTrue(it.hasNext());
        assertEquals("A", it.next().getLabel());
    }

    // Test iterating over adjacent vertices
    @Test
    void testAdjacentIterator() {
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 10);
        Iterator<Vertex<String>> it = graph.adjacent("A");
        assertTrue(it.hasNext());
        assertEquals("B", it.next().getLabel());
    }

    @Test
    void testAdjacentIteratorEmpty() {
        graph.add("A");
        Iterator<Vertex<String>> it = graph.adjacent("A");
        assertFalse(it.hasNext());
    }

    // Test iterating over edges
    @Test
    void testEdgeIterator() {
        // Add vertices and edges to the graph
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 10);

        // Create an edge iterator
        Iterator<Edge<String, Integer>> it = graph.edges();

        // Check if the iterator has any edges before calling next()
        assertTrue(it.hasNext(), "Edge iterator should have elements.");
        Edge<String, Integer> edge = it.next();

        // Ensure the edge is as expected
        assertEquals("A", edge.getU());
        assertEquals("B", edge.getV());
        assertEquals(10, edge.getLabel());
    }

    // Test clearing the graph
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

    // Test if graph is empty
    @Test
    void testIsEmpty() {
        assertTrue(graph.isEmpty());
        graph.add("A");
        assertFalse(graph.isEmpty());
    }

    // Test removing all vertices
    @Test
    void testRemoveAllVertices() {
        graph.add("A");
        graph.add("B");
        graph.remove("A");
        assertEquals(1, graph.size());
        graph.remove("B");
        assertTrue(graph.isEmpty());
    }

    // Test adding and removing edges after clearing the graph
    @Test
    void testAddRemoveEdgeAfterClear() {
        graph.clear();
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 10);
        assertTrue(graph.containsEdge("A", "B"));
        graph.removeEdge("A", "B");
        assertFalse(graph.containsEdge("A", "B"));
    }

    // Test invalid removal of edge
    @Test
    void testRemoveEdgeAfterClear() {
        graph.clear();
        assertThrows(NoSuchEdgeException.class, () -> graph.removeEdge("A", "B"));
    }

    // Test checking adjacency when there are no edges
    @Test
    void testNoAdjacency() {
        graph.add("A");
        graph.add("B");
        Iterator<Vertex<String>> it = graph.adjacent("A");
        assertFalse(it.hasNext());
    }

    // Test throwing exception for non-existent vertex when checking adjacency
    @Test
    void testAdjacentNonExistentVertexThrowsException() {
        graph.add("A");
        assertThrows(NoSuchVertexException.class, () -> graph.adjacent("B"));
    }

    // Test handling of edge count when removing edges
    @Test
    void testEdgeCountAfterRemoveEdge() {
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 10);
        graph.removeEdge("A", "B");
        assertEquals(0, graph.edgeCount());
    }

    // Test removing vertex updates edge count
    @Test
    void testEdgeCountAfterRemoveVertex() {
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 10);
        graph.remove("A");
        assertEquals(0, graph.edgeCount());
    }

    // Test removing a vertex that has no edges
    @Test
    void testRemoveVertexWithNoEdges() {
        graph.add("A");
        graph.remove("A");
        assertTrue(graph.isEmpty());
    }

    // Test that clear works properly after adding multiple edges
    @Test
    void testClearAfterMultipleEdges() {
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 10);
        graph.clear();
        assertTrue(graph.isEmpty());
    }
}

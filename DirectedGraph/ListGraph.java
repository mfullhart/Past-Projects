import java.util.*;

/**
 * A concrete implementation of the DirectedGraph class using an adjacency list
 * to represent edges. Each vertex maps to a list of its outgoing edges.
 *
 * @author mfullhart20@georgefox.edu
 * @param <V> the type of the vertex labels
 * @param <E> the type of the edge labels
 */
public class ListGraph<V, E> extends DirectedGraph<V, E>
{
    // Maps each vertex to a list of its outgoing edges
    private Map<V, List<Edge<V, E>>> _adjacencyList;
    // A set of all vertices in the graph
    private Set<V> _vertices;
    // Total number of edges in the graph
    private int _edgeCount;

    /**
     * Constructs an empty ListGraph.
     */
    public ListGraph()
    {
        _adjacencyList = new HashMap<>();
        _vertices = new HashSet<>();
        _edgeCount = 0;
    }

    /**
     * Adds a vertex to the graph.
     *
     * @param v the label of the vertex to add
     * @throws IllegalArgumentException throw IAE if the vertex is null
     * @throws DuplicateVertexException throw DVE if the vertex already exists
     */
    @Override
    public void add(V v)
    {
        if (v == null)
        {
            throw new IllegalArgumentException("Null vertex");
        }
        if (_vertices.contains(v))
        {
            throw new DuplicateVertexException("Vertex already exists");
        }

        _vertices.add(v);
        _adjacencyList.put(v, new ArrayList<>());
    }

    /**
     * Checks if a vertex exists in the graph.
     *
     * @param v the label of the vertex to check
     * @return true if the vertex exists, false otherwise
     */
    @Override
    public boolean contains(V v)
    {
        return _vertices.contains(v);
    }

    /**
     * Retrieves the vertex object corresponding to the given label.
     *
     * @param v the label of the vertex to retrieve
     * @return the vertex object
     * @throws NoSuchVertexException throw NSVE if the vertex does not exist
     */
    @Override
    public Vertex<V> get(V v)
    {
        if (!_vertices.contains(v))
        {
            throw new NoSuchVertexException("Vertex not found");
        }
        return new Vertex<>(v);
    }

    /**
     * Removes a vertex and all associated edges from the graph.
     *
     * @param v the label of the vertex to remove
     * @return the label of the removed vertex
     * @throws NoSuchVertexException throw NSVE if the vertex does not exist
     */
    @Override
    public V remove(V v)
    {
        if (!_vertices.contains(v))
        {
            throw new NoSuchVertexException("Vertex not found");
        }

        _vertices.remove(v);
        _adjacencyList.remove(v);

        for (List<Edge<V, E>> edges : _adjacencyList.values())
        {
            edges.removeIf(edge -> edge.getV().equals(v));
        }

        return v;
    }

    /**
     * Adds a directed edge between two vertices.
     *
     * @param u     the source vertex
     * @param v     the destination vertex
     * @param label the label of the edge
     * @throws IllegalArgumentException throw IAE if the edge label is null
     * @throws NoSuchVertexException throw NSVE if either vertex does not exist
     * @throws DuplicateEdgeException throw DEE if the edge already exists
     */
    @Override
    public void addEdge(V u, V v, E label)
    {
        if (label == null)
        {
            throw new IllegalArgumentException("Null edge label");
        }

        if (!_vertices.contains(u) || !_vertices.contains(v))
        {
            throw new NoSuchVertexException("Vertex not found");
        }

        List<Edge<V, E>> edges = _adjacencyList.get(u);

        for (Edge<V, E> edge : edges)
        {
            if (edge.getV().equals(v))
            {
                throw new DuplicateEdgeException("Edge already exists");
            }
        }

        edges.add(new Edge<>(u, v, label));
        _edgeCount++;
    }

    /**
     * Checks if an edge exists between two vertices.
     *
     * @param u the source vertex
     * @param v the destination vertex
     * @return true if the edge exists, false otherwise
     * @throws NoSuchVertexException throw NSVE if either vertex does not exist
     */
    @Override
    public boolean containsEdge(V u, V v)
    {
        if (!_vertices.contains(u) || !_vertices.contains(v))
        {
            throw new NoSuchVertexException("Vertex not found");
        }

        return _adjacencyList.get(u).stream().anyMatch(edge -> edge.getV().equals(v));
    }

    /**
     * Retrieves the edge object between two vertices.
     *
     * @param u the source vertex
     * @param v the destination vertex
     * @return the edge object
     * @throws NoSuchVertexException throw NSVE if either vertex does not exist
     * @throws NoSuchEdgeException throw NSEE if the edge does not exist
     */
    @Override
    public Edge<V, E> getEdge(V u, V v)
    {
        if (!_vertices.contains(u) || !_vertices.contains(v))
        {
            throw new NoSuchVertexException("Vertex not found");
        }

        for (Edge<V, E> edge : _adjacencyList.get(u))
        {
            if (edge.getV().equals(v))
            {
                return edge;
            }
        }

        throw new NoSuchEdgeException("Edge not found");
    }

    /**
     * Removes a directed edge between two vertices.
     *
     * @param u the source vertex
     * @param v the destination vertex
     * @return the label of the removed edge
     * @throws NoSuchVertexException throw NSVE if either vertex does not exist
     * @throws NoSuchEdgeException throw NSEE  if the edge does not exist
     */
    @Override
    public E removeEdge(V u, V v)
    {
        if (!_vertices.contains(u) || !_vertices.contains(v))
        {
            throw new NoSuchEdgeException("Edge not found");
        }

        List<Edge<V, E>> edges = _adjacencyList.get(u);
        Iterator<Edge<V, E>> iterator = edges.iterator();

        while (iterator.hasNext())
        {
            Edge<V, E> edge = iterator.next();
            if (edge.getV().equals(v))
            {
                iterator.remove();
                _edgeCount--;
                return edge.getLabel();
            }
        }

        throw new NoSuchEdgeException("Edge not found");
    }

    /**
     * Returns the number of vertices in the graph.
     *
     * @return the number of vertices
     */
    @Override
    public int size()
    {
        return _vertices.size();
    }

    /**
     * Returns the number of outgoing edges from a given vertex.
     *
     * @param v the vertex to check
     * @return the degree of the vertex
     * @throws NoSuchVertexException throw NSVE if the vertex does not exist
     */
    @Override
    public int degree(V v)
    {
        if (!_vertices.contains(v))
        {
            throw new NoSuchVertexException("Vertex not found");
        }

        return _adjacencyList.get(v).size();
    }

    /**
     * Returns the total number of edges in the graph.
     *
     * @return the number of edges
     */
    @Override
    public int edgeCount()
    {
        return _edgeCount;
    }

    /**
     * Returns an iterator over all vertices in the graph.
     *
     * @return an iterator over Vertex<V> objects
     */
    @Override
    public Iterator<Vertex<V>> vertices()
    {
        List<Vertex<V>> vertexList = new ArrayList<>();

        for (V v : _vertices)
        {
            vertexList.add(new Vertex<>(v));
        }
        return vertexList.iterator();
    }

    /**
     * Returns an iterator over all vertices adjacent to the given vertex.
     *
     * @param v the source vertex
     * @return an iterator over adjacent Vertex<V> objects
     * @throws NoSuchVertexException throw NSVE if the vertex does not exist
     */
    @Override
    public Iterator<Vertex<V>> adjacent(V v)
    {
        if (!_vertices.contains(v))
        {
            throw new NoSuchVertexException("Vertex not found");
        }

        List<Vertex<V>> adjList = new ArrayList<>();

        for (Edge<V, E> edge : _adjacencyList.get(v))
        {
            adjList.add(new Vertex<>(edge.getV()));
        }
        return adjList.iterator();
    }

    /**
     * Returns an iterator over all edges in the graph.
     *
     * @return an iterator over Edge<V, E> objects
     */
    @Override
    public Iterator<Edge<V, E>> edges()
    {
        List<Edge<V, E>> edgeList = new ArrayList<>();

        for (List<Edge<V, E>> edges : _adjacencyList.values())
        {
            edgeList.addAll(edges);
        }

        return edgeList.iterator();
    }

    /**
     * Clears the graph by removing all vertices and edges.
     */
    @Override
    public void clear()
    {
        _adjacencyList.clear();
        _vertices.clear();
        _edgeCount = 0;
    }

    /**
     * Checks if the graph is empty.
     *
     * @return true if the graph has no vertices, false otherwise
     */
    @Override
    public boolean isEmpty()
    {
        return _vertices.isEmpty();
    }
}

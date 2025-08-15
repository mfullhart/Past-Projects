import java.util.*;

/**
 * A concrete implementation of the DirectedGraph class using an adjacency matrix
 * to represent the edges. The vertices are stored in a list, and each vertex is mapped
 * to its index in the adjacency matrix.
 *
 * @author mfullhart20@georgefox.edu
 * @param <V> the type of the vertex labels
 * @param <E> the type of the edge labels
 */
public class MatrixGraph<V, E> extends DirectedGraph<V, E>
{
    // Maps each vertex to its index in the adjacency matrix
    private Map<V, Integer> _vertexIndex;
    // List of all vertices in the graph
    private List<V> _vertices;
    // Adjacency matrix to store edges, where _adjacencyMatrix[i][j] contains the label of the edge
    // from vertex i to vertex j
    private E[][] _adjacencyMatrix;
    // Total number of edges in the graph
    private int _edgeCount;

    /**
     * Constructs an empty MatrixGraph with an initial adjacency matrix capacity of 10.
     */
    @SuppressWarnings("unchecked")
    public MatrixGraph()
    {
        _vertexIndex = new HashMap<>();
        _vertices = new ArrayList<>();
        _adjacencyMatrix = (E[][]) new Object[10][10];
        _edgeCount = 0;
    }

    /**
     * Adds a vertex to the graph.
     *
     * @param v the label of the vertex to add
     * @throws IllegalArgumentException throw IAE if there is a null vertex
     * @throws DuplicateVertexException throw DVE if the vertex already exists
     */
    @Override
    public void add(V v)
    {
        if (v == null)
        {
            throw new IllegalArgumentException("Null vertex");
        }
        if (_vertexIndex.containsKey(v))
        {
            throw new DuplicateVertexException ("Vertex already exists");
        }

        _vertexIndex.put(v, _vertices.size());
        _vertices.add(v);

        // Resize the matrix if necessary
        if (_vertices.size() > _adjacencyMatrix.length)
        {
            resizeMatrix();
        }
    }

    /**
     * Resizes the adjacency matrix by doubling its size, copying existing edges to the new matrix.
     */
    private void resizeMatrix()
    {
        int newSize = _adjacencyMatrix.length * 2;

        @SuppressWarnings("unchecked")
        E[][] newMatrix = (E[][]) new Object[newSize][newSize];

        for (int i = 0; i < _adjacencyMatrix.length; i++)
        {
            System.arraycopy(_adjacencyMatrix[i], 0, newMatrix[i], 0,
                    _adjacencyMatrix.length);
        }

        _adjacencyMatrix = newMatrix;
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
        return _vertexIndex.containsKey(v);
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
        if (!_vertexIndex.containsKey(v))
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
        if (!_vertexIndex.containsKey(v))
        {
            throw new NoSuchVertexException("Vertex not found");
        }

        int index = _vertexIndex.remove(v);
        V removedVertex = _vertices.get(index);
        _vertices.remove(index);

        // Update adjacency matrix
        for (int i = 0; i < _adjacencyMatrix.length; i++)
        {
            _adjacencyMatrix[index][i] = null;
            _adjacencyMatrix[i][index] = null;
        }

        return removedVertex;
    }

    /**
     * Adds a directed edge between two vertices.
     *
     * @param u     the source vertex
     * @param v     the destination vertex
     * @param label the label of the edge
     * @throws IllegalArgumentException throw IAE if the label is null
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
        if (!_vertexIndex.containsKey(u) || !_vertexIndex.containsKey(v))
        {
            throw new NoSuchVertexException("Vertex not found");
        }

        int uIndex = _vertexIndex.get(u);
        int vIndex = _vertexIndex.get(v);

        if (_adjacencyMatrix[uIndex][vIndex] != null)
        {
            throw new DuplicateEdgeException("Edge already exists");
        }

        _adjacencyMatrix[uIndex][vIndex] = label;
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
        if (!_vertexIndex.containsKey(u) || !_vertexIndex.containsKey(v))
        {
            throw new NoSuchVertexException("Vertex not found");
        }

        int uIndex = _vertexIndex.get(u);
        int vIndex = _vertexIndex.get(v);
        return _adjacencyMatrix[uIndex][vIndex] != null;
    }

    /**
     * Retrieves the edge between two specified vertices in the graph.
     *
     * @param u the source vertex
     * @param v the destination vertex
     * @return the edge object connecting the source vertex (u) to the destination vertex (v)
     * @throws NoSuchVertexException throw NSVE if either the source or destination vertex does not
     * exist in the graph
     * @throws NoSuchEdgeException throw NSEEif no edge exists between the specified vertices
     */
    @Override
    public Edge<V, E> getEdge(V u, V v)
    {
        if (!_vertexIndex.containsKey(u) || !_vertexIndex.containsKey(v))
        {
            throw new NoSuchVertexException("Vertex not found");
        }

        int uIndex = _vertexIndex.get(u);
        int vIndex = _vertexIndex.get(v);
        E label = _adjacencyMatrix[uIndex][vIndex];

        if (label == null)
        {
            throw new NoSuchEdgeException("Edge not found");
        }

        return new Edge<>(u, v, label);
    }

    /**
     * Removes a directed edge between two vertices.
     *
     * @param u the source vertex
     * @param v the destination vertex
     * @return the label of the removed edge
     * @throws NoSuchVertexException throw NSVE if either vertex does not exist
     * @throws NoSuchEdgeException throw NSEE if the edge does not exist
     */
    @Override
    public E removeEdge(V u, V v)
    {
        if (!_vertexIndex.containsKey(u) || !_vertexIndex.containsKey(v))
        {
            throw new NoSuchEdgeException("Edge not found");
        }

        int uIndex = _vertexIndex.get(u);
        int vIndex = _vertexIndex.get(v);
        E label = _adjacencyMatrix[uIndex][vIndex];

        if(label == null)
        {
            throw new NoSuchEdgeException("Edge not found");
        }

        _adjacencyMatrix[uIndex][vIndex] = null;
        _edgeCount--;
        return label;
    }

    /**
     * Gets the number of vertices in the graph.
     *
     * @return the number of vertices
     */
    @Override
    public int size()
    {
        return _vertices.size();
    }

    /**
     * Gets the degree (number of outgoing edges) of a vertex.
     *
     * @param v the label of the vertex
     * @return the degree of the vertex
     */
    @Override
    public int degree(V v)
    {
        if (!_vertexIndex.containsKey(v))
        {
            throw new NoSuchVertexException("Vertex not found");
        }

        int index = _vertexIndex.get(v);
        int degree = 0;

        for (E edge : _adjacencyMatrix[index])
        {
            if (edge != null) degree++;
        }

        return degree;
    }

    /**
     * Gets the total number of edges in the graph.
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
     * Each vertex is represented as a Vertex<V> object.
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
     * Adjacent vertices are connected by outgoing edges from the specified vertex.
     *
     * @param v the source vertex
     * @return an iterator over adjacent Vertex<V> objects
     * @throws NoSuchVertexException throw NSVE if the vertex does not exist in the graph
     */
    @Override
    public Iterator<Vertex<V>> adjacent(V v)
    {
        if (!_vertexIndex.containsKey(v))
        {
            throw new NoSuchVertexException("Vertex not found");
        }

        int index = _vertexIndex.get(v);
        List<Vertex<V>> adjList = new ArrayList<>();

        for (int i = 0; i < _adjacencyMatrix[index].length; i++)
        {
            if (_adjacencyMatrix[index][i] != null)
            {
                adjList.add(new Vertex<>(_vertices.get(i)));
            }
        }

        return adjList.iterator();
    }

    /**
     * Returns an iterator over all edges in the graph.
     * Each edge is represented as an Edge<V, E> object.
     *
     * @return an iterator over Edge<V, E> objects
     */
    @Override
    public Iterator<Edge<V, E>> edges()
    {
        List<Edge<V, E>> edgeList = new ArrayList<>();

        for (int i = 0; i < _vertices.size(); i++)
        {
            for (int j = 0; j < _vertices.size(); j++)
            {
                E label = _adjacencyMatrix[i][j];
                if (label != null)
                {
                    edgeList.add(new Edge<>(_vertices.get(i), _vertices.get(j), label));
                }
            }
        }

        return edgeList.iterator();
    }

    /**
     * Clears the graph by removing all vertices and edges.
     * Resets the adjacency matrix, vertex list, and edge count.
     */
    @Override
    public void clear()
    {
        _vertexIndex.clear();
        _vertices.clear();
        _adjacencyMatrix = (E[][]) new Object[10][10];
        _edgeCount = 0;
    }

    /**
     * Checks if the graph is empty, meaning it contains no vertices.
     *
     * @return true if the graph is empty, false otherwise
     */
    @Override
    public boolean isEmpty()
    {
        return _vertices.isEmpty();
    }
}

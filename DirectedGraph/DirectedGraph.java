import java.util.Iterator;

/**
 * Represents a generic directed graph structure where vertices are labeled
 * using type <V>, and edges are labeled using type <E>.
 *
 * @author mfullhart20@georgefox.edu
 * @param <V> the type of vertex labels
 * @param <E> the type of edge labels
 */
public abstract class DirectedGraph<V, E>
{
    /**
     * Adds a vertex to the graph.
     *
     * @param v the label of the vertex to add
     */
    public abstract void add(V v);

    /**
     * Checks if a vertex with the given label exists in the graph.
     *
     * @param v the label of the vertex to check
     * @return true if the vertex exists, false otherwise
     */
    public abstract boolean contains(V v);

    /**
     * Retrieves the vertex object corresponding to the given label.
     *
     * @param v the label of the vertex to retrieve
     * @return the vertex object
     */
    public abstract Vertex<V> get(V v);

    /**
     * Removes a vertex and all its associated edges from the graph.
     *
     * @param v the label of the vertex to remove
     * @return the label of the removed vertex, or null if it does not exist
     */
    public abstract V remove(V v);

    /**
     * Adds a directed edge from vertex u to vertex v with a given label.
     *
     * @param u the source vertex label
     * @param v the destination vertex label
     * @param label the label of the edge
     */
    public abstract void addEdge(V u, V v, E label);

    /**
     * Checks if an edge exists between the given source and destination vertices.
     *
     * @param u the source vertex label
     * @param v the destination vertex label
     * @return true if the edge exists, false otherwise
     */
    public abstract boolean containsEdge(V u, V v);

    /**
     * Retrieves the edge object between the given source and destination vertices.
     *
     * @param u the source vertex label
     * @param v the destination vertex label
     * @return the edge object
     */
    public abstract Edge<V, E> getEdge(V u, V v);

    /**
     * Removes the edge between the given source and destination vertices.
     *
     * @param u the source vertex label
     * @param v the destination vertex label
     * @return the label of the removed edge, or null if the edge does not exist
     */
    public abstract E removeEdge(V u, V v);

    /**
     * Gets the number of vertices in the graph.
     *
     * @return the number of vertices
     */
    public abstract int size();

    /**
     * Gets the degree (number of outgoing edges) of a vertex.
     *
     * @param v the label of the vertex
     * @return the degree of the vertex
     */
    public abstract int degree(V v);

    /**
     * Gets the total number of edges in the graph.
     *
     * @return the number of edges
     */
    public abstract int edgeCount();

    /**
     * Gets an iterator over all vertices in the graph.
     *
     * @return an iterator over vertex objects
     */
    public abstract Iterator<Vertex<V>> vertices();

    /**
     * Gets an iterator over all vertices adjacent to the given vertex.
     *
     * @param v the label of the source vertex
     * @return an iterator over adjacent vertex objects
     */
    public abstract Iterator<Vertex<V>> adjacent(V v);

    /**
     * Gets an iterator over all edges in the graph.
     *
     * @return an iterator over edge objects
     */
    public abstract Iterator<Edge<V, E>> edges();

    /**
     * Removes all vertices and edges from the graph, making it empty.
     */
    public abstract void clear();

    /**
     * Checks if the graph is empty (contains no vertices).
     *
     * @return true if the graph is empty, false otherwise
     */
    public abstract boolean isEmpty();

}


/**
 * Represents a directed edge in a graph with a label. An edge connects a source vertex (u)
 * to a destination vertex (v) and is optionally labeled with a value of type <E>.
 *
 * @author mfullhart20@georgefox.edu
 * @param <V> the type of the vertices (source and destination labels)
 * @param <E> the type of the edge label
 */
public class Edge<V, E>
{
    // The source vertex of the edge
    private final V u;
    // The destination vertex of the edge
    private final V v;
    // The label of the edge
    private E _label;

    /**
     * Constructs an edge with a source vertex, a destination vertex, and a label.
     *
     * @param u the source vertex
     * @param v the destination vertex
     * @param label the label of the edge
     * @throws IllegalArgumentException throw IAE if any of the parameters (u, v, or label) are null
     */
    public Edge(V u, V v, E label)
    {
        if (u == null || v == null || label == null)
        {
            throw new IllegalArgumentException("Null value in Edge");
        }

        this.u = u;
        this.v = v;
        _label = label;
    }

    /**
     * Returns the source vertex of the edge.
     *
     * @return the source vertex
     */
    public V getU()
    {
        return u;
    }

    /**
     * Returns the destination vertex of the edge.
     *
     * @return the destination vertex
     */
    public V getV()
    {
        return v;
    }

    /**
     * Returns the label of the edge.
     *
     * @return the edge label
     */
    public E getLabel()
    {
        return _label;
    }

    /**
     * Updates the label of the edge.
     *
     * @param label the new label for the edge
     * @throws IllegalArgumentException throw IAE if the new label is null
     */
    public void setLabel(E label)
    {
        if (label == null)
        {
            throw new IllegalArgumentException("Null label");
        }
        _label = label;
    }

    /**
     * Compares this edge to another object for equality. Two edges are considered
     * equal if they have the same source vertex and the same destination vertex,
     * regardless of their labels.
     *
     * @param o the object to compare with
     * @return true if the object is an edge with the same source and destination vertices, false otherwise
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        Edge<?, ?> edge = (Edge<?, ?>) o;
        return u.equals(edge.u) && v.equals(edge.v);
    }

    /**
     * Computes a hash code for the edge based on its source and destination vertices.
     *
     * @return the hash code of the edge
     */
    @Override
    public int hashCode()
    {
        return u.hashCode() + v.hashCode();
    }
}

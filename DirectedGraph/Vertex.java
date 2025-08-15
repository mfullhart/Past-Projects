/**
 * Represents a vertex in a graph. A vertex is identified by a label of type <V>.
 *
 * @author mfullhart20@georgefox.edu
 * @param <V> the type of the label used to identify the vertex
 */
public class Vertex<V>
{
    // The label of the vertex, used to uniquely identify it
    private final V _label;

    /**
     * Constructs a vertex with the specified label.
     *
     * @param label the label of the vertex
     * @throws IllegalArgumentException throw IAE if the label is null
     */
    public Vertex(V label)
    {
        if (label == null)
        {
            throw new IllegalArgumentException("Null label");
        }

        _label = label;
    }

    /**
     * Returns the label of the vertex.
     *
     * @return the label of the vertex
     */
    public V getLabel()
    {
        return _label;
    }

    /**
     * Compares this vertex to another object for equality. Two vertices are considered
     * equal if their labels are the same.
     *
     * @param o the object to compare with
     * @return true if the object is a vertex with the same label, false otherwise
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

        Vertex<?> vertex = (Vertex<?>) o;
        return _label.equals(vertex._label);
    }

    /**
     * Computes a hash code for the vertex based on its label.
     *
     * @return the hash code of the vertex
     */
    @Override
    public int hashCode()
    {
        return _label.hashCode();
    }
}

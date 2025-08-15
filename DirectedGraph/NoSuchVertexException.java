import java.util.NoSuchElementException;

/**
 * Thrown by various accessor methods to indicate that the vertex being requested does not exist.
 *
 * @author mfullhart20@georgefox.edu
 */
public class NoSuchVertexException extends NoSuchElementException
{
    /**
     * Constructs a NoSuchVertexException with null as its error message string.
     */
    public NoSuchVertexException()
    {
        super();
    }

    /**
     * Constructs a NoSuchVertexException, saving a reference to the error message string s for
     * later retrieval by the getMessage method.
     *
     * @param s String message
     */
    public NoSuchVertexException(String s)
    {
        super(s);
    }
}

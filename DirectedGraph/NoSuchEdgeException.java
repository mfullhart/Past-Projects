import java.util.NoSuchElementException;

/**
 * Thrown by various accessor methods to indicate that the edge being requested does not exist.
 *
 * @author mfullhart20@georgefox.edu
 */
public class NoSuchEdgeException extends NoSuchElementException
{
    /**
     * Constructs a NoSuchEdgeException with null as its error message string.
     */
    public NoSuchEdgeException()
    {
        super();
    }

    /**
     * Constructs a NoSuchEdgeException, saving a reference to the error message string s for
     * later retrieval by the getMessage method.
     *
     * @param s String message
     */
    public NoSuchEdgeException(String s)
    {
        super(s);
    }
}

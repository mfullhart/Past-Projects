/**
 * Thrown by various accessor methods to indicate that the edge being requested already exists.
 *
 * @author mfullhart20@georgefox.edu
 */
public class DuplicateEdgeException extends RuntimeException
{
    /**
     * Constructs a DuplicateEdgeException.
     */
    public DuplicateEdgeException()
    {
        super();
    }

    /**
     * Constructs a DuplicateEdgeException, saving a reference to the error message string s for
     * later retrieval.
     *
     * @param s String message
     */
    public DuplicateEdgeException(String s)
    {
        super(s);
    }
}

/**
 * Thrown by various accessor methods to indicate that the vertex being requested already exists.
 *
 * @author mfullhart20@georgefox.edu
 */
public class DuplicateVertexException extends RuntimeException
{
    /**
     * Constructs a DuplicateVertexException.
     */
    public DuplicateVertexException()
    {
        super();
    }

    /**
     * Constructs a DuplicateVertexException, saving a reference to the error message string s for
     * later retrieval.
     *
     * @param s String message
     */
    public DuplicateVertexException(String s)
    {
        super(s);
    }
}
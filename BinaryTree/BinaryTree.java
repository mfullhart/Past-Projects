import java.util.*;

/**
 * A functioning binary tree class.
 *
 * @author mfullhart20@georgefox.edu.
 *
 * @param <E> Type of elements in binary tree.
 */
public class BinaryTree<E>
{
    // Elements in binary tree.
    private E _element;
    private BinaryTree<E> _leftChild;
    private BinaryTree<E> _rightChild;
    private BinaryTree<E> _parent;

    private int _size;
    private int _leftHeight;
    private int _rightHeight;
    private int _level;
    private int _degree;

    /**
     * Construct a binary tree.
     */
    public BinaryTree(E element)
    {
        _element = element;
        _leftChild = null;
        _rightChild = null;
        _parent = null;
        _size = 1;
        _leftHeight = 0;
        _rightHeight = 0;
        _level = 0;
        _degree = 0;
    }

    /**
     * Retrieves the element in binary tree.
     *
     * @return element.
     */
    public E getElement()
    {
        return _element;
    }

    /**
     * Set element in binary tree.
     *
     * @param element to be set.
     * @return element.
     */
    public E setElement(E element)
    {
        return this._element = element;
    }

    /**
     * Check if there is a left child.
     *
     * @return True if there is a left child.
     */
    public boolean hasLeftChild()
    {
        return _leftChild != null;
    }

    /**
     * Gets left child.
     *
     * @return left child.
     */
    public BinaryTree<E> getLeftChild()
    {
        return _leftChild;
    }

    /**
     * Set a new left child.
     *
     * @param child new left child to be set.
     * @return previous left child.
     */
    public BinaryTree<E> setLeftChild(BinaryTree<E> child)
    {
        validateChild(child);
        BinaryTree<E> oldLeftChild = _leftChild;
        if (_leftChild != null)
        {
            _leftChild._parent = null;
        }
        _leftChild = child;
        if (child != null)
        {
            child._parent = this;
        }
        return oldLeftChild;
    }

    /**
     * Check if there is a right child.
     *
     * @return True if there is a right child.
     */
    public boolean hasRightChild()
    {
        return _rightChild != null;
    }

    /**
     * Gets right child.
     *
     * @return right child.
     */
    public BinaryTree<E> getRightChild()
    {
        return _rightChild;
    }

    /**
     * Set a new right child.
     *
     * @param child new right child to be set.
     * @return previous right child.
     */
    public BinaryTree<E> setRightChild(BinaryTree<E> child)
    {
        validateChild(child);
        BinaryTree<E> oldRightChild = _rightChild;
        if (_rightChild != null)
        {
            _rightChild._parent = null;
        }
        _rightChild = child;
        if (child != null)
        {
            child._parent = this;
        }
        return oldRightChild; // Attach new child
    }

    /**
     * Gets root of the binary tree.
     *
     * @return root node of the binary tree.
     */
    public BinaryTree<E> getRoot()
    {
        if (_parent != null)
        {
            return _parent.getRoot();
        }
        else
        {
            return this;
        }
    }

    /**
     * Get parent of the node.
     *
     * @return parent of the node.
     */
    public BinaryTree<E> getParent()
    {
        return _parent;
    }

    /**
     * Calculate size of binary tree.
     *
     * @return total number of nodes in tree.
     */
     public int size()
    {
        if (hasRightChild())
        {
            _size += getRightChild().size();
        }
        if (hasLeftChild())
        {
            _size += getLeftChild().size();
        }

        return _size;
    }

    /**
     * Calculate height of binary tree.
     *
     * @return height of tree.
     */
    public int height()
    {
        if (!hasLeftChild() && !hasRightChild())
        {
            return 0;
        }
        if (hasLeftChild())
        {
            _leftHeight = getLeftChild().height();
        }
        if (hasRightChild())
        {
            _rightHeight = getRightChild().height();
        }

        if (_leftHeight > _rightHeight)
        {
            return 1 + _leftHeight;
        }
        else
        {
            return 1 + _rightHeight;
        }
    }

    /**
     * Calculate level of binary tree.
     *
     * @return level of binary tree.
     */
    public int level()
    {
        if (_parent == null)
        {
            return 0;
        }
        else
        {
            return 1 + _parent.level();
        }
    }

    /**
     * Calculate degrees in binary tree.
     *
     * @return degrees in binary tree.
     */
    public int degree()
    {
        if (hasLeftChild())
        {
            _degree ++;
        }
        if (hasRightChild())
        {
            _degree ++;
        }

        return _degree;
    }

    /**
     * Determines if current node is root node.
     *
     * @return true if root node.
     */
    public boolean isRoot()
    {
        return getParent() == null;
    }

    /**
     * Determines if current node is parent node.
     *
     * @return true if parent node.
     */
    public boolean isParent()
    {
        return hasLeftChild() || hasRightChild();
    }

    /**
     * Determines if current node is child node.
     *
     * @return true if child node.
     */
    public boolean isChild()
    {
        return _parent != null;
    }

    /**
     * Determines if current node is a leaf.
     *
     * @return true if leaf.
     */
    public boolean isLeaf()
    {
        return !hasLeftChild() && !hasRightChild();
    }

    /**
     * Determines if binary tree is full.
     *
     * @return true if full.
     */
    public boolean isFull()
    {
        if (isLeaf())
        {
            return true;
        }
        if (hasLeftChild() && hasRightChild())
        {
            return _leftChild.isFull() && _rightChild.isFull();
        }
        return false;
    }

    /**
     *Determines if the binary tree is complete.
     *
     * @return true if complete.
     */
    public boolean isComplete()
    {
        Queue<BinaryTree<E>> queue = new LinkedList<>();
        queue.add(this);
        boolean gapDetected = false;

        while (!queue.isEmpty())
        {
            BinaryTree<E> node = queue.poll();

            if (node == null)
            {
                gapDetected = true; // A null node indicates a gap
            }
            else
            {
                if (gapDetected)
                {
                    return false; // Non-null node after a gap violates completeness
                }
                queue.add(node.getLeftChild());
                queue.add(node.getRightChild());
            }
        }
        return true;
    }

    /**
     * Determines if tree is degenerate, meaning every parent has at most one child,
     * resembling a linked list.
     *
     * @return true if degenerate.
     */
    public boolean isDegenerate()
    {
        return (_leftChild == null && _rightChild != null)
        || (_leftChild != null && _rightChild == null);
    }

    /**
     * Determines if the current node is an ancestor of the specified descendant node.
     *
     * @param descendant the node to check against.
     * @return true if the current node is an ancestor of the descendant node.
     * @throws IllegalArgumentException if the descendant is null.
     */
    public boolean isAncestorOf(BinaryTree<E> descendant)
    {

        if (descendant == null)
        {
            throw new IllegalArgumentException("NOPE");
        }
        if (descendant == _leftChild || descendant == _rightChild)
        {
            return true;
        }

        return (_leftChild != null && _leftChild.isAncestorOf(descendant))
                || (_rightChild != null && _rightChild.isAncestorOf(descendant));
    }

    /**
     * Determines if the current node is the parent of the specified child node.
     *
     * @param child the node to check against.
     * @return true if the current node is the parent of the child node.
     * @throws IllegalArgumentException if the child is null.
     */
    public boolean isParentOf(BinaryTree<E> child)
    {
        if (child == null)
        {
            throw new IllegalArgumentException("NO");
        }

        return _leftChild == child || _rightChild == child;
    }

    /**
     * Determines if the current node is a sibling of the specified sibling node.
     *
     * @param sibling the node to check against.
     * @return true if the current node is a sibling of the sibling node.
     * @throws IllegalArgumentException if the sibling is null.
     */
    public boolean isSiblingOf(BinaryTree<E> sibling)
    {
        if (sibling == null)
        {
            throw new IllegalArgumentException("NO");
        }
        return this._parent != null && this._parent == sibling._parent && this != sibling;
    }

    /**
     * Determines if the current node is a child of the specified parent node.
     *
     * @param parent the node to check against.
     * @return true if the current node is a child of the parent node.
     * @throws IllegalArgumentException if the parent is null.
     */
    public boolean isChildOf(BinaryTree<E> parent)
    {
        if (parent == null)
        {
            throw new IllegalArgumentException("NO");
        }

        return this._parent == parent;
    }

    /**
     * Determines if the current node is a descendant of the specified ancestor node.
     *
     * @param ancestor the node to check against.
     * @return true if the current node is a descendant of the ancestor node.
     * @throws IllegalArgumentException if the ancestor is null.
     */
    public boolean isDescendantOf(BinaryTree<E> ancestor)
    {
        if (ancestor == null)
        {
            throw new IllegalArgumentException("NO");
        }

        return this == ancestor || this.isChildOf(ancestor) || _parent != null && _parent.isDescendantOf(ancestor);
    }

    /**
     * Retrieves an iterator for traversing the binary tree in in-order traversal.
     *
     * @return an iterator over the elements of the binary tree.
     */
    public Iterator<E> iterator()
    {
        return inOrderIterator();
    }

    /**
     * Creates an in-order iterator for the binary tree.
     *
     * @return an iterator over the elements of the binary tree in in-order traversal.
     */
    public Iterator<E> inOrderIterator()
    {
        List<E> elements = new ArrayList<>();
        inOrderTraverse(this, elements);
        return elements.iterator();
    }

    /**
     * Helper method for in-order traversal.
     * Adds the elements of the binary tree to a list in in-order sequence.
     *
     * @param node the current node being traversed.
     * @param elements the list to store the elements.
     */
    private void inOrderTraverse(BinaryTree<E> node, List<E> elements)
    {
        if (node == null)
        {
            return;
        }

        inOrderTraverse(node._leftChild, elements);
        elements.add(node._element);
        inOrderTraverse(node._rightChild, elements);
    }

    /**
     * Creates a pre-order iterator for the binary tree.
     *
     * @return an iterator over the elements of the binary tree in pre-order traversal.
     */
    public Iterator<E> preOrderIterator()
    {
        List<E> elements = new ArrayList<>();
        preOrderTraverse(this, elements);
        return elements.iterator();
    }

    /**
     * Helper method for pre-order traversal.
     * Adds the elements of the binary tree to a list in pre-order sequence.
     *
     * @param node the current node being traversed.
     * @param elements the list to store the elements.
     */
    private void preOrderTraverse(BinaryTree<E> node, List<E> elements)
    {
        if (node != null)
        {
            elements.add(node._element);
            preOrderTraverse(node._leftChild, elements);
            preOrderTraverse(node._rightChild, elements);
        }
    }

    /**
     * Creates a post-order iterator for the binary tree.
     *
     * @return an iterator over the elements of the binary tree in post-order traversal.
     */
    public Iterator<E> postOrderIterator()
    {
        List<E> elements = new ArrayList<>();
        postOrderTraverse(this, elements);
        return elements.iterator();
    }

    /**
     * Helper method for post-order traversal.
     * Adds the elements of the binary tree to a list in post-order sequence.
     *
     * @param node the current node being traversed.
     * @param elements the list to store the elements.
     */
    private void postOrderTraverse(BinaryTree<E> node, List<E> elements)
    {
        if (node == null)
        {
            return;
        }
        postOrderTraverse(node._leftChild, elements);
        postOrderTraverse(node._rightChild, elements);
        elements.add(node._element);
    }

    /**
     * Creates a level-order iterator for the binary tree.
     *
     * @return an iterator over the elements of the binary tree in level-order traversal.
     */
    public Iterator<E> levelOrderIterator()
    {
        List<E> elements = new ArrayList<>();
        Queue<BinaryTree<E>> queue = new LinkedList<>();
        queue.add(this);
        while (!queue.isEmpty())
        {
            BinaryTree<E> current = queue.poll();
            if (current != null)
            {
                elements.add(current._element);
                queue.add(current._leftChild);
                queue.add(current._rightChild);
            }
        }
        return elements.iterator();
    }

    /**
     * Helper method to collect all nodes in a list for specific use cases like completeness checks.
     *
     * @param node  the current node being traversed.
     * @param nodes the list to store the nodes.
     */
    private void collectNodes(BinaryTree<E> node, List<BinaryTree<E>> nodes)
    {
        if (node != null)
        {
            nodes.add(node);
            collectNodes(node._leftChild, nodes);
            collectNodes(node._rightChild, nodes);
        }
    }

    /**
     * Validates if the specified child can be added to the tree.
     * Throws an exception if the child is self or an ancestor.
     *
     * @param child the child node to validate.
     * @throws IllegalArgumentException if the child is invalid.
     */
    private void validateChild(BinaryTree<E> child)
    {
        if (child == this || (child != null && child.isAncestorOf(this)))
        {
            throw new IllegalArgumentException("Cannot set an ancestor or self as a child.");
        }
    }

    /**
     * Generates a string representation of the binary tree in in-order traversal.
     *
     * @return a string representation of the binary tree.
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Iterator<E> iterator = inOrderIterator();
        while (iterator.hasNext())
        {
            sb.append(iterator.next());
            if (iterator.hasNext()) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}


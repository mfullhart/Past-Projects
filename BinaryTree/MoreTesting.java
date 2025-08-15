import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MoreTesting
{
    private BinaryTree<String> root;
    private BinaryTree<String> leftChild;
    private BinaryTree<String> rightChild;
    private BinaryTree<String> leftGrandChild;
    private BinaryTree<String> rightGrandChild;

    @BeforeEach
    void setup() {
        root = new BinaryTree<>("A");
        leftChild = new BinaryTree<>("B");
        rightChild = new BinaryTree<>("C");
        leftGrandChild = new BinaryTree<>("D");
        rightGrandChild = new BinaryTree<>("E");

        root.setLeftChild(leftChild);
        root.setRightChild(rightChild);
        leftChild.setLeftChild(leftGrandChild);
        leftChild.setRightChild(rightGrandChild);
    }

    // 1. Test root element
    @Test
    void testGetRootElement() {
        assertEquals("A", root.getElement());
    }

    // 2. Test set element
    @Test
    void testSetElement() {
        root.setElement("Z");
        assertEquals("Z", root.getElement());
    }

    // 3. Test left child
    @Test
    void testGetLeftChild() {
        assertEquals(leftChild, root.getLeftChild());
    }

    // 4. Test right child
    @Test
    void testGetRightChild() {
        assertEquals(rightChild, root.getRightChild());
    }

    // 5. Test setting left child
    @Test
    void testSetLeftChild() {
        BinaryTree<String> newChild = new BinaryTree<>("F");
        BinaryTree<String> detached = root.setLeftChild(newChild);
        assertEquals(leftChild, detached);
        assertEquals(newChild, root.getLeftChild());
    }

    @Test
    void testSetRightChild() {
        BinaryTree<String> newChild = new BinaryTree<>("F");
        BinaryTree<String> detached = root.setRightChild(newChild);
        assertEquals(rightChild, detached);
        assertEquals(newChild, root.getRightChild());
    }

    // 6. Test size of tree
    @Test
    void testTreeSize() {
        assertEquals(5, root.size());
    }

    // 7. Test height of tree
    @Test
    void testTreeHeight() {
        assertEquals(2, root.height());
    }

    // 8. Test tree degree of root
    @Test
    void testDegreeOfRoot() {
        assertEquals(2, root.degree());
    }

    // 9. Test tree degree of leaf
    @Test
    void testDegreeOfLeaf() {
        assertEquals(0, leftGrandChild.degree());
    }

    // 10. Test if node is leaf
    @Test
    void testIsLeaf() {
        assertTrue(leftGrandChild.isLeaf());
        assertFalse(root.isLeaf());
    }

    // 11. Test is root
    @Test
    void testIsRoot() {
        assertTrue(root.isRoot());
        assertFalse(leftChild.isRoot());
    }

    // 12. Test is full
    @Test
    void testIsFull() {
        assertTrue(root.isFull());
    }

    // 13. Test is complete
    @Test
    void testIsComplete() {
        assertTrue(root.isComplete());
    }

    // 14. Test is degenerate
    @Test
    void testIsDegenerate() {
        assertFalse(root.isDegenerate());
    }

    // 15. Test ancestor relationship
    @Test
    void testIsAncestor() {
        assertTrue(root.isAncestorOf(leftGrandChild));
        assertFalse(leftGrandChild.isAncestorOf(root));
    }

    // 16. Test parent relationship
    @Test
    void testIsParent() {
        assertTrue(root.isParentOf(leftChild));
        assertFalse(leftGrandChild.isParentOf(root));
    }

    // 17. Test sibling relationship
    @Test
    void testIsSibling() {
        assertTrue(leftChild.isSiblingOf(rightChild));
        assertFalse(leftChild.isSiblingOf(leftGrandChild));
    }

    // 18. Test descendant relationship
    @Test
    void testIsDescendant() {
        assertTrue(leftGrandChild.isDescendantOf(root));
        assertFalse(root.isDescendantOf(leftGrandChild));
    }

    // 19. Test setting child to null
    @Test
    void testSetChildToNull() {
        BinaryTree<String> detached = root.setLeftChild(null);
        assertEquals(leftChild, detached);
        assertNull(root.getLeftChild());
    }

    @Test
    void testInOrderTraversal() {
        Iterable<String> actual = () -> root.inOrderIterator();
        assertIterableEquals(List.of("D", "B", "E", "A", "C"), actual);
    }

    // 21. Test pre-order traversal
    @Test
    void testPreOrderTraversal() {
        Iterable<String> actual = () -> root.preOrderIterator();
        assertIterableEquals(List.of("A", "B", "D", "E", "C"), actual);
    }


    // 22. Test post-order traversal
    @Test
    void testPostOrderTraversal() {
        Iterable<String> actual = () -> root.postOrderIterator();
        assertIterableEquals(List.of("D", "E", "B", "C", "A"), actual);
    }


    // 23. Test level-order traversal
    @Test
    void testLevelOrderTraversal() {
        Iterable<String> actual = () -> root.levelOrderIterator();
        assertIterableEquals(List.of("A", "B", "C", "D", "E"), actual);
    }


    // 24. Test toString
    @Test
    void testToString() {
        assertEquals("[D, B, E, A, C]", root.toString());
    }

    // 25. Test setting subtree as root
    @Test
    void testSetSubtreeAsRoot() {
        assertThrows(IllegalArgumentException.class, () -> leftChild.setLeftChild(root));
    }

    // 26. Test setting null as element
    @Test
    void testSetNullElement() {
        root.setElement(null);
        assertNull(root.getElement());
    }

    // 27. Test empty tree creation
    @Test
    void testEmptyTree() {
        BinaryTree<String> empty = new BinaryTree<>(null);
        assertEquals(1, empty.size());
        assertTrue(empty.isLeaf());
    }

}
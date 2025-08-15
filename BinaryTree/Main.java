import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        // Step 1: Create the root node
        System.out.println("Creating a Binary Tree...");
        BinaryTree<Integer> root = new BinaryTree<>(10);
        System.out.println("Root created with value: " + root.getElement());

        // Step 2: Add left and right children to the root
        System.out.println("\nAdding children to the root...");
        BinaryTree<Integer> leftChild = new BinaryTree<>(5);
        BinaryTree<Integer> rightChild = new BinaryTree<>(15);
        root.setLeftChild(leftChild);
        root.setRightChild(rightChild);
        System.out.println("Left child set with value: " + leftChild.getElement());
        System.out.println("Right child set with value: " + rightChild.getElement());

        // Step 3: Add more nodes to expand the tree
        System.out.println("\nAdding grandchildren...");
        BinaryTree<Integer> leftLeftChild = new BinaryTree<>(3);
        BinaryTree<Integer> leftRightChild = new BinaryTree<>(7);
        leftChild.setLeftChild(leftLeftChild);
        leftChild.setRightChild(leftRightChild);
        System.out.println("Left grandchild (left of left child) added with value: " + leftLeftChild.getElement());
        System.out.println("Right grandchild (right of left child) added with value: " + leftRightChild.getElement());

        BinaryTree<Integer> rightLeftChild = new BinaryTree<>(12);
        BinaryTree<Integer> rightRightChild = new BinaryTree<>(20);
        rightChild.setLeftChild(rightLeftChild);
        rightChild.setRightChild(rightRightChild);
        System.out.println("Left grandchild (left of right child) added with value: " + rightLeftChild.getElement());
        System.out.println("Right grandchild (right of right child) added with value: " + rightRightChild.getElement());

        // Step 4: Display the tree structure using in-order traversal
        System.out.println("\nTree structure (in-order traversal): " + root);

        // Step 5: Test tree properties
        System.out.println("\nTesting tree properties...");
        System.out.println("Tree Height: " + root.height());
        System.out.println("Tree Size: " + root.size());
        System.out.println("Is the tree full? " + root.isFull());
        System.out.println("Is the tree complete? " + root.isComplete());
        System.out.println("Is the tree degenerate? " + root.isDegenerate());

        // Step 6: Test individual node properties
        System.out.println("\nTesting individual node properties...");
        System.out.println("Root Level: " + root.level());
        System.out.println("Left Child Level: " + leftChild.level());
        System.out.println("Right Grandchild Level: " + rightRightChild.level());
        System.out.println("Root Degree: " + root.degree());
        System.out.println("Left Child Degree: " + leftChild.degree());

        // Step 7: Test relationships
        System.out.println("\nTesting relationships...");
        System.out.println("Is the root the parent of the left child? " + root.isParentOf(leftChild));
        System.out.println("Is the left child a sibling of the right child? " + leftChild.isSiblingOf(rightChild));
        System.out.println("Is the left grandchild a descendant of the root? " + leftLeftChild.isDescendantOf(root));

        // Step 8: Traverse the tree using different iterators
        System.out.println("\nTraversing the tree using different iterators...");

        System.out.print("In-Order Traversal: ");
        printIterator(root.inOrderIterator());

        System.out.print("Pre-Order Traversal: ");
        printIterator(root.preOrderIterator());

        System.out.print("Post-Order Traversal: ");
        printIterator(root.postOrderIterator());

        System.out.print("Level-Order Traversal: ");
        printIterator(root.levelOrderIterator());

        System.out.println("\nTesting completed.");
    }

    // Helper method to print elements from an iterator
    private static <E> void printIterator(Iterator<E> iterator) {
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }
}

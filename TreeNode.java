package trees;

// Declarations for a typical tree-node class
// For a binary search tree, data in nodes must have a comesBefore method
// as well as an equal method.  This node therefore uses the Entry class 
// for data storage.

import java.lang.Comparable;

public class TreeNode <E extends Comparable <E>> {

    protected E data;      // the information to be stored in the node
    protected TreeNode<E> left;   // the pointer to the node's left subtree
    protected TreeNode<E> right;  // the pointer to the node's right subtree

    // Constructors
    public TreeNode (E startingData) {
        data = startingData;
        left = null;
        right = null;
    }

    public TreeNode  (E initData, TreeNode <E> leftNode, TreeNode <E> rightNode) {
        data = initData;
        left = leftNode;
        right = rightNode;
    }

    // extractors
    public E getData () {
        return data;
    }

    public TreeNode <E>getLeft () {
        return left;
    }

    public TreeNode<E> getRight () {
        return right;
    }

    // modifiers
    public void setData (E newData) {
        data = newData;
    }

    public void setLeft (TreeNode<E> newLeft) {
        left = newLeft;
    }
    public void setRight (TreeNode<E> newRight) {
        right = newRight;
    }
} // TreeNode
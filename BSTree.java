package trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BSTree <E extends Comparable<E>> {

    private TreeNode<E> root;

    BSTree () {
        root = null;
    }

    public void insert (E newEntry) {
        if (root == null)
            root = new TreeNode<E>(newEntry);
        else {
            TreeNode<E> ptr = root;  // pointer to node in search for leaf
            while (ptr != null) {// search for leaf
                if (newEntry.compareTo(ptr.getData()) < 0) {
                    // insert on left of given node
                    if (ptr.getLeft() == null) {
                        // when at end of tree, insert
                        ptr.setLeft(new TreeNode<E>(newEntry));
                        return; 
                    } else 
                        // move left in tree and continue search
                        ptr = ptr.getLeft();
                } else {
                    // insert on right of given node
                    if (ptr.getRight() == null) {
                        // when at end of tree, insert
                        ptr.setRight(new TreeNode<E>(newEntry));
                        return;
                    } else
                        // move right in tree and continue search
                        ptr = ptr.getRight();
                }
            }

            // insertion should be done by now!!
            // exception could be thrown or error message printed here!!
        }
    }

    public void print () {
        System.out.println ("Directory Listing");
        printKernel (root);
        System.out.println ();
        System.out.println ("End of Listing");
    }

    private void printKernel (TreeNode<E> base) {
        // to print elements in a tree (using an in-order traversal),
        //     print the left subtree
        //     print the elements in a node
        //     print the right subtree
        if (base != null) {
            printKernel (base.getLeft());
            System.out.print(base.getData());
            printKernel (base.getRight());
        }
    }

    public E lookup (E desiredItem) {
        return lookupKernel (root, desiredItem);
    }

    public E lookupKernel (TreeNode<E> base, E desiredItem) {
        if (base == null)
            return null;
        else if (base.getData().compareTo(desiredItem) == 0)
            return base.getData();
        else if (base.getData().compareTo(desiredItem) < 0)
            return lookupKernel (base.getRight(), desiredItem);
        else return lookupKernel (base.getLeft(), desiredItem);
    }
    
    public int nodeLevel(E itemData) {
    	return nodeLevelKernel(root, itemData, 0);
    }
    
    public int nodeLevelKernel (TreeNode<E> base, E itemData, int level) {
    	if (base == null) 
            return -1; 
   
        if (base.data == itemData) 
            return level; 
   
        int nextLevel = nodeLevelKernel(base.left, itemData, level + 1); 
        if (nextLevel != -1) 
            return nextLevel; 
   
        nextLevel = nodeLevelKernel(base.right, itemData, level + 1); 
        return nextLevel; 
    
    }  
    
    public int height() {
    	return heightHelper(root);
    }
    
    public int heightHelper(TreeNode<E> base)  
    { 
        if (base == null) 
            return -1;
        else 
        { 
            int leftHeight = heightHelper(base.left); 
            int rightHeight = heightHelper(base.right); 
   
            if (leftHeight > rightHeight) 
                return (leftHeight + 1); 
             else 
                return (rightHeight + 1); 
        } 
    } 
    
    public boolean isBalanced() {
    	ArrayList<Integer> leafLevelArray = new ArrayList<Integer>();
    	isBalancedHelper(leafLevelArray, 0, root);

    	System.out.println(leafLevelArray.toString());
    	
    	int minLevel = Collections.min(leafLevelArray);
    	int maxLevel = Collections.max(leafLevelArray);
    	if(Math.abs(maxLevel - minLevel) <= 1) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public void isBalancedHelper(ArrayList<Integer> leafLevelArray, int level, TreeNode<E> base) {
    	if(base.left == null & base.right == null) {
    		leafLevelArray.add(level);
    	}
    	else if (base.left == null) {
    		isBalancedHelper(leafLevelArray, level+1, base.right);
    	}
    	else if(base.right == null) {
    		isBalancedHelper(leafLevelArray, level+1, base.left);
    	} 
    	else {
    		isBalancedHelper(leafLevelArray, level+1, base.left);
    		isBalancedHelper(leafLevelArray, level+1, base.right);
    	}
    } 
    
    
    public boolean isHeightBalanced() {
    	if(root == null) {
    		return true;
    	}
    	
    	ArrayList<Integer> leafLevelArray = new ArrayList<Integer>();
    	isBalancedHelper(leafLevelArray, 0, root);

    	System.out.println(leafLevelArray.toString());
    	
    	int minLevel = Collections.min(leafLevelArray);
    	int maxLevel = Collections.max(leafLevelArray);
    	if(Math.abs(maxLevel - minLevel) <= 1) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public void isHeightBalancedHelper(ArrayList<Integer> leafLevelArray, int level, TreeNode<E> base) {
    	if(base.left == null & base.right == null) {
    		leafLevelArray.add(level);
    	}
    	else if (base.left == null) {
    		isBalancedHelper(leafLevelArray, level+1, base.right);
    	}
    	else if(base.right == null) {
    		isBalancedHelper(leafLevelArray, level+1, base.left);
    	} 
    	else {
    		isBalancedHelper(leafLevelArray, level+1, base.left);
    		isBalancedHelper(leafLevelArray, level+1, base.right);
    	}
    } 
    
}
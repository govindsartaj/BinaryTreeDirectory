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

	
	/**
	 * 
	 * @param itemData of datatype E
	 * @return the node level at which node exists that contains the itemData
	 */
	public int nodeLevel(E itemData) {
		// If tree empty
		if (root == null) {
			return -1;
		} else {
			return nodeLevelKernel(root, itemData, 0);
		}
	}

	/**
	 * 
	 * @param base is tree node passed in
	 * @param itemData desired data we are looking for
	 * @param level of current node
	 * @return
	 */
	public int nodeLevelKernel (TreeNode<E> base, E itemData, int level) {
		//leaf reached
		if (base.left == null && base.right == null) {
			if(base.data.equals(itemData)) {
				return level;
			} else {
				return -1;
			}
		}
		//node with only right child node
		else if (base.left == null) {
			if(base.data.equals(itemData)) {
				return level;
			} else {
				return nodeLevelKernel(base.right, itemData, level+1);
			}
		}
		//node with only left child node
		else if (base.right == null) {
			if(base.data.equals(itemData)) {
				return level;
			} else {
				return nodeLevelKernel(base.left, itemData, level+1);
			}
		}
		//node with both left and right child nodes
		else {
			if(base.data.equals(itemData)) {
				return level;
			} else {
				return Math.max(nodeLevelKernel(base.left, itemData, level+1), 
						nodeLevelKernel(base.right, itemData, level+1));		
			}
		}
	}  

	/**
	 * 
	 * @return the height of the tree. The node with greatest level, out of all nodes in the tree.
	 */
	public int height() {
		if (root == null) {
			return -1;
		}
		return heightHelper(0, root);
	}

	/* When leaf is reached, return level of leaf. Then at each node above, compare bottom paths
	 *  upto node with only right child node.
	 */
	/**
	 * 
	 * @param level of base node
	 * @param base is tree node passed in
	 * @return height of tree of base tree node.
	 */
	public int heightHelper(int level, TreeNode<E> base)  
	{ 
		if (base.left == null && base.right == null) {
			return level;
		}
		else if (base.left == null) {
			return heightHelper(level+1, base.right);
		}
		else if (base.right == null) {
			return heightHelper(level+1, base.left);
		}
		else {
			int leftHeight = heightHelper(level+1, base.left); 
			int rightHeight = heightHelper(level+1, base.right);
			return Math.max(leftHeight, rightHeight);
		}
	} 
	
	/**
	 * A tree is balanced when the node with greatest level and node with least level out of the tree differs by no more than 1 level.
	 * @return if tree is balanced.
	 */
	public boolean isBalanced() {
		if (root == null){
			return true;
		} else{
			ArrayList<Integer> leafLevelArray = new ArrayList<Integer>();
			isBalancedHelper(leafLevelArray, 0, root);

			int minLevel = Collections.min(leafLevelArray);
			int maxLevel = Collections.max(leafLevelArray);
			if(Math.abs(maxLevel - minLevel) <= 1) {
				return true;
			} else {
				return false;
			}
		}
	}

	/* leafLevelArray is way we keep track of the levels of all the leaves in the array. It is an 
	 * object so it is a shared instance. Then after run through entire tree, then we see if 
	 * difference between greatest and smallest level was greater than 1 to see if unbalanced.
	 */
	/**
	 * 
	 * @param leafLevelArray array which carries levels of leaves in tree.
	 * @param level of base tree node 
	 * @param base is tree node passed in
	 */
	public void isBalancedHelper(ArrayList<Integer> leafLevelArray, int level, TreeNode<E> base) {

		if(base.left == null && base.right == null) {
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

	/**
	 * A tree is height balanced when all the nodes in the tree are balanced.
	 * @return ig a tree is height balanced
	 */
	public boolean isHeightBalanced() {
		if (root == null) {
			return true;
		} else {
			int[] result = isHeightBalancedHelper(0, root);
			return (result[0] == 1);
		}
	}

	
	/**
	 * 
	 * @param level of base tree node
	 * @param base is tree node passed in
	 * @return an array, which we use to pass data up the tree
	 */
	/*
	 * int[] array passed up the tree has two values. boolean isBalancedBelow(0-false, 1-true)
	 *  at index 0, int maxHeightBelow at index 1. isBalancedBelow says if tree is heightBalanced
	 *  below the current base tree node. maxHeightBelow signifies the height of the base tree node.
	 */
	public int[] isHeightBalancedHelper(int level, TreeNode<E> base) {
		if(base.left == null && base.right == null) {
			int[] x = {1,level};
			return x;
		}
		else if (base.left == null) {
			int[] rightSubTree = isHeightBalancedHelper(level+1, base.right);
			if(rightSubTree[0] == 0 || rightSubTree[1] > level+1) {
				int[] x = {0, 808};
				return x;
			} else return rightSubTree;
		}
		else if(base.right == null) {
			int[] leftSubTree = isHeightBalancedHelper(level+1, base.left);
			if(leftSubTree[0] == 0 || leftSubTree[1] > level+1) {
				int[] x = {0, 808};
				return x;
			} else {
				return leftSubTree;
			}
		} 
		else {
			int[] leftSubTree = isHeightBalancedHelper(level+1, base.left);
			int[] rightSubTree = isHeightBalancedHelper(level+1, base.right);
			if(leftSubTree[0] == 0 || rightSubTree[0] == 0 || rightSubTree[1] > level+1 || leftSubTree[1] > level+1) {
				int[] x = {0, 808};
				return x;
			} else {
				int[]x = {1, Math.max(rightSubTree[1], leftSubTree[1])};
				return x;
			}
		}
	}

	/**
	 * 
	 * @param tree a tree of type Entry nodes passed in.
	 * @return the Entry that has longest first name out of a node from the entire tree
	 */
	public Entry findLongest(BSTree<Entry> tree) {
		if(tree.root == null) {
			return null;
		} else {
			return findLongestHelper(tree.root);
		}

	}
	
	/**
	 * 
	 * @param base is tree node passed in
	 * @return Entry that has longest firstName out of all the Entry nodes attached to base node.
	 */
	public Entry findLongestHelper(TreeNode<Entry> base) {

		if(base.left == null && base.right == null) {
			return base.data;
		}
		else if(base.left == null) {

			Entry right = findLongestHelper(base.right);
			Entry middle = base.data;

			return whichEntryLongestName(right, middle);
		}
		else if(base.right == null) {
			Entry left = findLongestHelper(base.left);
			Entry middle = base.data;

			return whichEntryLongestName(left, middle);
		}
		else {
			Entry right = findLongestHelper(base.right);
			Entry left = findLongestHelper(base.left);
			Entry middle = base.data;
			Entry rightOrLeft = whichEntryLongestName(right, left);
			
			return whichEntryLongestName(rightOrLeft, middle);
		}
	}

	/**
	 * @param e1 an Entry object
	 * @param e2 an Entry object
	 * @return the Entry which has the longest firstName field.
	 */
	public Entry whichEntryLongestName(Entry e1, Entry e2) {
		int e1Length = e1.firstName.length();
		int e2Length = e2.firstName.length();

		if(e1Length > e2Length) {
			return e1;
		}
		else if(e1Length < e2Length) {
			return e2;
		}
		else {
			if(e1.firstName.compareTo(e2.firstName) > 0) {
				return e1;
			} else {
				return e2;
			}
		}
	}

	/**
	 * Remove an item from the tree.
	 * @param item of type E
	 * pre: the tree carries the item that is desired to be removed.
	 * pre: if multiple items exist in tree, all items are removed.
	 * post: tree maintains its ordered format that defines itself as a tree
	 */
	public void remove(E item) {

		removeHelper(item, root);
	}

	/**
	 * 
	 * @param item is the item desired to be removed
	 * @param thisNode is node above the node desired to be removed.
	 */
	public void removeHelper(E item, TreeNode<E> thisNode) {

		// for when parent node(called for when root is desired to be removed) is item to be removed.
		if(thisNode != null) {
			boolean isFound = (thisNode.getData().compareTo(item) == 0);
			if(isFound) {
				if(thisNode.right == null && thisNode.left == null) {
					root = null;
				}
				else if(thisNode.right == null) {
					root = thisNode.left;
				}
				else if(thisNode.left == null) {
					root = thisNode.right;
				}
				else if(thisNode.right != null && thisNode.left != null) {
					TreeNode<E> lowestRightNodeParent = findLowestHelperParent(thisNode, thisNode.right);
					TreeNode<E> lowestRightNode = lowestRightNodeParent.left;
					TreeNode<E> lowestRightNodeChild = lowestRightNodeParent.left.right;
					thisNode.setData(lowestRightNode.data);
					lowestRightNodeParent.left = lowestRightNodeChild;
				}
			}
		}
		
		// If parent node has a right node.
		if(thisNode.right != null) {
			TreeNode<E> currentNode = thisNode.right;
			boolean isFound = (currentNode.getData().compareTo(item) == 0);

			// If right child node of parent contains item.
			if(isFound) {

				if(currentNode.right == null && currentNode.left == null) {
					if(isFound) {
						thisNode.right = null;
						System.out.println("CALLED");
					}
				}
				else if(currentNode.right == null) {
					if(isFound) {
						thisNode.right = currentNode.left;
					} 
					else {
						removeHelper(item, currentNode.left);
					}
				}
				else if(currentNode.left == null) {
					if(isFound) {
						thisNode.right = currentNode.right;
					} 
					else {
						removeHelper(item, currentNode.right);
					}
				}
				//else has both right & left subtree
				else {
					if(isFound) {
						TreeNode<E> lowestRightNodeParent = findLowestHelperParent(currentNode, currentNode.right);
						TreeNode<E> lowestRightNode = lowestRightNodeParent.left;
						TreeNode<E> lowestRightNodeChild = lowestRightNodeParent.left.right;
						thisNode.right.setData(lowestRightNode.data);
						lowestRightNodeParent.left = lowestRightNodeChild;

					} else {
						removeHelper(item, currentNode.right);
						removeHelper(item, currentNode.left);
					}
				}
			}
			else {
				removeHelper(item, currentNode);
			}
		}


		//If parent node has a left node.
		if(thisNode.left != null) {
			TreeNode<E> currentNode = thisNode.left;
			boolean isFound = (currentNode.getData().compareTo(item) == 0);

			// If left child node of parent contains item.
			if(isFound) {

				if(currentNode.right == null && currentNode.left == null) {
					if(isFound) {
						thisNode.left = null;
						System.out.println("CALLED");
					}
				}
				else if(currentNode.right == null) {
					if(isFound) {
						thisNode.left = currentNode.left;
					} 
					else {
						removeHelper(item, currentNode.left);
					}
				}
				else if(currentNode.left == null) {
					if(isFound) {
						thisNode.left = currentNode.right;
					} 
					else {
						removeHelper(item, currentNode.right);
					}
				}
				//else has both right & left subtree
				else {
					if(isFound) {
						TreeNode<E> lowestRightNodeParent = findLowestHelperParent(currentNode, currentNode.right);
						TreeNode<E> lowestRightNode = lowestRightNodeParent.left;
						TreeNode<E> lowestRightNodeChild = lowestRightNodeParent.left.right;
						thisNode.left.setData(lowestRightNode.data);
						lowestRightNodeParent.left = lowestRightNodeChild;
					} else {
						removeHelper(item, currentNode.right);
						removeHelper(item, currentNode.left);
					}
				}
			}
			else {
				removeHelper(item, currentNode);
			}
		}


	}

	/**
	 * 
	 * @param prebase is tree node that is parent of base node
	 * @param base is tree node
	 * @return parent of node that is the left-most node.
	 */
	public TreeNode<E> findLowestHelperParent(TreeNode<E> prebase, TreeNode<E> base) {
		while(base.left != null) {
			prebase = base;
			base = base.left;
		}
		return prebase;
	}


}
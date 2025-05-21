/**
 *	Binary Tree of Comparable values.
 *	The tree only has unique values. It does not add duplicate values.
 *	
 *	@author	Aarav Prakash
 *	@since	May 20th, 2024
 */
 
import java.util.List;
import java.util.ArrayList;

public class BinaryTree<E extends Comparable<E>>
{
	private TreeNode<E> root;		// the root of the tree
	
	private final int PRINT_SPACES = 3;	// print spaces between tree levels
										// used by printTree()
	
	/**	constructor for BinaryTree */
	public BinaryTree()
	{
		root = null;
	}
	
	/**	Field accessors and modifiers */
	
	/**
	 * Adds a node with the specified value to the binary tree.
	 * If the tree is empty, the new node becomes the root.
	 * Otherwise, the value is added using a recursive approach by default.
	 * Uncomment the iterative approach to use it instead.
	 *
	 * @param value 		the value to be added to the tree
	 */
	public void add(E value)
	{
		// Check if the tree is currently empty
		if(root == null)
		{
			// Create a new node with the given value
			TreeNode<E> node = new TreeNode<E>(value);
			
			// Set this new node as the root of the tree
			root = node;
		}
		
		else
		{
			// Start the recursive addition process from the root
			addRecursive(value, root); // Recursive adding
			
			//~ addIterative(value); // Uncomment to use iterative adding instead
		}
	}
	
	/**
	 * Recursively adds a value to the binary tree. The method places the new value
	 * in the correct position based on the comparison result: values less than
	 * the current node go to the left, greater values go to the right.
	 *
	 * @param value 	the value to add to the tree
	 * @param node 		the current node being examined, starting from the root
	 */
	public void addRecursive(E value, TreeNode<E> node)
	{
		// Compare the new value with the value of the current node
		if (value.compareTo(node.getValue()) > 0)
		{
			// If new value is greater, explore the right subtree
			if (node.getRight() != null)
			{
				// If right child exists, recurse on the right child
				addRecursive(value, node.getRight());
			}
			
			else
			{
				// If no right child, insert new node here
				TreeNode<E> temp = new TreeNode<E>(value);
				node.setRight(temp);
			}
		}
		
		else
		{
			// If new value is less or equal, explore the left subtree
			if (node.getLeft() != null)
			{
				// If left child exists, recurse on the left child
				addRecursive(value, node.getLeft());
			}
			
			else
			{
				// If no left child, insert new node here
				TreeNode<E> temp = new TreeNode<E>(value);
				node.setLeft(temp);
			}
		}
	}
	
	/**
	 * Iteratively adds a value to the binary tree. The method finds the correct
	 * position for the new node by traversing the tree from the root, moving left
	 * or right depending on the comparison of the value with the current node.
	 * The process continues until the correct leaf position is found.
	 *
	 * @param value 	the value to add to the tree
	 */
	public void addIterative(E value)
	{
		// Create a new node for the value
		TreeNode<E> node = new TreeNode<E>(value);
		
		// Start at the root of the tree
		TreeNode<E> current = root;
		
		// Flag to indicate completion of the insertion process
		boolean hasFinished = false;
		
		while(!hasFinished)
		{
			// Compare the new value with the value of the current node
			if (value.compareTo(current.getValue()) > 0)
			{
				// If new value is greater, move to the right subtree
				if (current.getRight() != null)
				{
					// If right child exists, move to the right child
					current = current.getRight();
				}
				
				else
				{
					// If no right child, insert new node here
					current.setRight(node);
					hasFinished = true; // Mark insertion as complete
				}
			}
			
			else
			{
				// If new value is less or equal, move to the left subtree
				if (current.getLeft() != null)
				{
					// If left child exists, move to the left child
					current = current.getLeft();
				}
				
				else
				{
					// If no left child, insert new node here
					current.setLeft(node);
					hasFinished = true; // Mark insertion as complete
				}
			}
		}
	}
	
	/**
	 * Prints the elements of the binary tree in inorder traversal.
	 * Inorder traversal visits the left subtree, the root, and then 
	 * the right subtree.
	 */
	public void printInorder()
	{   
		// Start the recursive inorder traversal from the root
		printInorderRecurs(root);
		
		// Print a newline character after all elements have been printed
		System.out.println();
	}

	/**
	 * Recursive helper method to print the tree in inorder.
	 * This method visits nodes in the following order: left child, 
	 * current node, right child.
	 * 
	 * @param node 			the current node being visited
	 */
	public void printInorderRecurs(TreeNode<E> node)
	{
		// Check if the current node is not null
		if (node != null)
		{
			// Recursively print the left subtree
			printInorderRecurs(node.getLeft());
			
			// Print the value of the current node followed by a space
			System.out.print(node.getValue() + " ");
			
			// Recursively print the right subtree
			printInorderRecurs(node.getRight());
		}
	}
	
	/**
	 * Prints  the binary tree in preorder traversal
	 */
	public void printPreorder()
	{
		// Start the recursive preorder printing from the root
		printPreorderRecurs(root);
		// Print a newline at the end for better output formatting
		System.out.println();
	}

	/**
	 * Recursive helper method to print the tree in preorder traversal.
	 * Preorder traversal involves visiting the root before its children.
	 *
	 * @param node 		the current node being visited
	 */
	private void printPreorderRecurs(TreeNode<E> node)
	{
		// Check if the current node is not null
		if (node != null)
		{
			// Print the value of the current node followed by a space
			System.out.print(node.getValue() + " ");
			
			// Recursively print the left subtree
			printPreorderRecurs(node.getLeft());
			
			// Recursively print the right subtree
			printPreorderRecurs(node.getRight());
		}
	}
	
	/**
	 * Print the binary tree using postorder traversal.
	 */
	public void printPostorder()
	{
		// Start postorder traversal from the root
		printPostorderRecurs(root);
		// Print a newline after completing the traversal
		System.out.println();
	}

	/**
	 * Recursively prints the tree in postorder.
	 * In postorder traversal, the nodes are visited in the following order:
	 * left subtree, right subtree, and then the node itself.
	 * 
	 * @param node 			the current node being visited
	 */
	private void printPostorderRecurs(TreeNode<E> node)
	{
		// Check if the current node is not null
		if (node != null) 
		{
			// Recursively visit the left child
			printPostorderRecurs(node.getLeft());
			
			// Recursively visit the right child
			printPostorderRecurs(node.getRight());
			
			// Process the current node (print its value)
			System.out.print(node.getValue() + " ");
		}
	}
    
	/**
	 * Returns a balanced version of this binary tree.
	 * A balanced tree is one where the depth of two subtrees of every node 
	 * never differ by more than one.
	 *
	 * @return 			the balanced binary tree
	 */
	public BinaryTree<E> makeBalancedTree()
	{
		// Create a new binary tree to hold the balanced version
		BinaryTree<E> balancedTree = new BinaryTree<>();
		
		// Create a list to store the elements of the tree in inorder traversal
		List<E> values = new ArrayList<>();
		
		// Fill the list with elements from the tree in inorder fashion
		storeInorder(root, values);
		
		// Build a balanced tree from the sorted list of values and assign its root to the new tree
		balancedTree.root = buildBalancedTree(values, 0, values.size() - 1);

		// Return the newly created balanced tree
		return balancedTree;
	}

	/**
	 * Recursively stores the values of the tree in inorder traversal.
	 * Inorder traversal visits the left subtree, the root, and then the 
	 * right subtree.
	 *
	 * @param node 			the current node being visited
	 * @param values 		the list to store the values
	 */
	public void storeInorder(TreeNode<E> node, List<E> values)
	{
		// Check if the current node is not null
		if (node != null)
		{
			// Recursively store values from the left subtree
			storeInorder(node.getLeft(), values);
			
			// Add the current node's value to the list
			values.add(node.getValue());
			
			// Recursively store values from the right subtree
			storeInorder(node.getRight(), values);
		}
	}

	/**
	 * Recursively builds a balanced binary tree from a sorted list of 
	 * values. This method uses the divide and conquer technique by choosing 
	 * the middle element as the root.
	 *
	 * @param values 		the sorted list of values
	 * @param start 		the start index of the current sublist
	 * @param end 			the end index of the current sublist
	 * @return 				the root node of the balanced subtree
	 */
	public TreeNode<E> buildBalancedTree(List<E> values, int start, int end)
	{
		if (start <= end)
		{
			// Calculate the middle index of the current sublist
			int mid = (start + end) / 2;
			
			// Create a new node with the middle value
			TreeNode<E> node = new TreeNode<>(values.get(mid));

			// Recursively build the left subtree using the left half of the current sublist
			node.setLeft(buildBalancedTree(values, start, mid - 1));
			
			// Recursively build the right subtree using the right half of the current sublist
			node.setRight(buildBalancedTree(values, mid + 1, end));

			// Return the node now connected to its subtrees
			return node;
		}
		
		// If the start index is greater than the end index, return null (base case)
		return null;
	}
	
	/**
	 * Removes a value from the binary tree. Starts removal from the root 
	 * and updates the root if necessary. Assumes value exists in the tree.
	 * @param value 		the value to remove from the tree
	 */
	public void remove(E value)
	{
		root = remove(root, value);
	}

	/**
	 * Recursively removes a value from the binary tree starting from the 
	 * given node. Handles different cases of removal based on the node's 
	 * children.
	 * 
	 * @param node 		the root of the subtree where the value should be removed
	 * @param value 	the value to remove from the subtree
	 * @return 			TreeNode that reconnects the subtree correctly after removal
	 */
	public TreeNode<E> remove(TreeNode<E> node, E value)
	{
		// Base case: if the subtree is empty, nothing to remove
		if (node == null) 
		{
			return null;
		}

		// Compare the value to be removed with the current node's value
		int compare = value.compareTo(node.getValue());

		// Value is less than current node's value; continue in the left subtree
		if (compare < 0) 
		{
			
			node.setLeft(remove(node.getLeft(), value));
		} 
		
		// Value is greater than current node's value; continue in the right subtree
		else if (compare > 0) 
		{
			
			node.setRight(remove(node.getRight(), value));
		} 
		
		// Found the node to remove (value matches the node's value)
		else 
		{
			// Node is a leaf node, remove it by returning null
			if (node.getLeft() == null && node.getRight() == null) 
			{
				node = null;
			}
			 
			// Node has only a left child, replace node with its left child
			else if (node.getRight() == null) 
			{
				node = node.getLeft();
			} 
			
			// Node has only a right child, replace node with its right child
			else if (node.getLeft() == null) 
			{
				node = node.getRight();
			} 
			
			// Node has both left and right children
			else 
			{
				// Find the minimum node in the right subtree to replace the current node
				TreeNode<E> minNode = findMin(node.getRight());
				
				// Replace the current node's value with the found minimum value
				node.setValue(minNode.getValue());
				
				// Remove the minimum node from the right subtree
				node.setRight(remove(node.getRight(), minNode.getValue()));
			}
		}

		// Return the node to reconnect with its parent, if any
		return node;
	}

	/**
	 * Finds the minimum node starting from the given node. Assumes that
	 * the smallest value is located in the leftmost node.
	 * 
	 * @param node 			the starting node to find the minimum
	 * @return 				the node with the minimum value in the subtree
	 */
	private TreeNode<E> findMin(TreeNode<E> node)
	{
		// The minimum value in a Binary Tree is the leftmost node
		if(node.getLeft() == null) 
		{
			// No more left child, this node is the minimum
			return node;
		}

		// Recursively find the minimum in the left subtree
		return findMin(node.getLeft());
	}

	/*******************************************************************************/	
	/********************************* Utilities ***********************************/	
	/*******************************************************************************/	
	/**
	 *	Print binary tree
	 *	@param root		root node of binary tree
	 *
	 *	Prints in vertical order, top of output is right-side of tree,
	 *			bottom is left side of tree,
	 *			left side of output is root, right side is deepest leaf
	 *	Example Integer tree:
	 *			  11
	 *			/	 \
	 *		  /		   \
	 *		5			20
	 *				  /	  \
	 *				14	   32
	 *
	 *	would be output as:
	 *
	 *				 32
	 *			20
	 *				 14
	 *		11
	 *			5
	 ***********************************************************************/
	public void printTree()
	{
		printLevel(root, 0);
	}
	
	/**
	 *	Recursive node printing method
	 *	Prints reverse order: right subtree, node, left subtree
	 *	Prints the node spaced to the right by level number
	 *	@param node		root of subtree
	 *	@param level	level down from root (root level = 0)
	 */
	private void printLevel(TreeNode<E> node, int level)
	{
		if (node == null) return;
		// print right subtree
		printLevel(node.getRight(), level + 1);
		// print node: print spaces for level, then print value in node
		for (int a = 0; a < PRINT_SPACES * level; a++) System.out.print(" ");
		System.out.println(node.getValue());
		// print left subtree
		printLevel(node.getLeft(), level + 1);
	}
}


public class LinkedTree {

	private Node root;
	int count=0;
	
	public void add(Integer data) {
		
		// If its the first node handle it.
		if (root == null) {
			Node newNode = new Node();
			newNode.setData(data);
			root = newNode;
			count = 1;
			return;
		}
		
		// Otherwise look to see if it already exists
		Node tmp = internalSearch(data);
		
		if (tmp != null) {
			// It is already in the tree
			return;
		}
		
		// Add it to the tree
		internalAdd(root, data);
		
	}
	
	private void internalAdd(Node root, Integer data) {
		if (root == null) {
			System.out.println("OUCH: Root is null, and that shouldn't happen (internalAdd)");
			return;
		}
		
		if (data < root.getData()) {
			// recurse down the left side
			if (root.getlChild() == null) {
				// This is where we add the data
				Node newNode = new Node();
				newNode.setData(data);
				root.setlChild(newNode);
				count++;
				return;
			} else {
				internalAdd(root.getlChild(), data);
			}
		} else {
			// recurse down the right side
			if (root.getrChild() == null) {
				// Add the data here
				Node newNode = new Node();
				newNode.setData(data);
				root.setrChild(newNode);
				count++;
				return;
			} else {
				internalAdd(root.getrChild(), data);
			}
		}
	}
	
	public boolean remove(Integer data) {
		return false;
	}
	
	private Node internalSearch(Integer data) {
		return null;
	}
	
	public boolean search(Integer data) {
		return false;
	}
	
	public int countOccurrences(Node root) {
		return -1;
	}
	
	public void preOrderTraversal() {
		internalPreOrderTraversal(root);
		System.out.println();
	}
	
	public void inOrderTraversal() {
		internalInOrderTraversal(root);
		System.out.println();
	}
	
	public void postOrderTraversal() {
		internalPostOrderTraversal(root);
		System.out.println();
	}
	
	private void internalPreOrderTraversal(Node root) {
		// Base Case
		if (root == null) {
			return;
		}
		System.out.print(root.getData() + " ");
		
		internalPreOrderTraversal(root.getlChild());
		internalPreOrderTraversal(root.getrChild());
		
	}

	private void internalInOrderTraversal(Node root) {
		// Base Case
		if (root == null) {
			return;
		}				
		
		internalInOrderTraversal(root.getlChild());
		System.out.print(root.getData() + " ");
		internalInOrderTraversal(root.getrChild());
	}

	private void internalPostOrderTraversal(Node root) {
		// Base Case
		if (root == null) {
			return;
		}
		
		internalPostOrderTraversal(root.getlChild());
		internalPostOrderTraversal(root.getrChild());
		System.out.print(root.getData() + " ");
	}
	
	
	private String toStringInOrderTraversal(Node root) {
		// Base Case
		if (root == null) {
			return "";
		}				
		
		String rtn = "";
		rtn += toStringInOrderTraversal(root.getlChild());
		rtn +=  root.getData() + " ";
		rtn += toStringInOrderTraversal(root.getrChild());
		
		return rtn;
	}
	
	public int size() {
		return count;
	}
	
	public int maxDepth() {
		int depth = 0;
		return maxDepth(root, depth);
	}

	private int maxDepth(Node root, int depth) {
		if (root == null) {
			return depth-1;
		}
		
		//depth++;
		int lcd = maxDepth(root.getlChild(), depth+1);
		int rcd = maxDepth(root.getrChild(), depth+1);
		if (lcd > rcd) {
			return lcd;
		} else {
			return rcd;			
		}
		
	}
	
	private int delCount = 0;
	public void delete(Integer data) {
		delCount = 0;
		int size = size();
		root = recDelete(root, data);
		
		// If the size of the tree changed, we deleted something
		if (size != size()) {
			count = size-1;
		}
		
		System.out.println("Delete recursed " + delCount + " times");
	}
	
	private Node recDelete(Node root, Integer data) {
		
		delCount++;
		// Base Case
		if (root == null) {
			return root;
		}
		
		// Recurse down the tree
		if (data < root.getData()) {
			root.setlChild(recDelete(root.getlChild(), data));
		} else if (data > root.getData()) {
			root.setrChild(recDelete(root.getrChild(), data));
		} else {
			// If the data is the same, delete this node
			count--;
			// see if node has one child or no children
			if (root.getlChild() == null) {
				return root.getrChild();
			} else if (root.getrChild() == null) {
				return root.getlChild();
			}
			
			// The Node has two children, get the smallest in the right subtree.
			root.setData(getMinValue(root.getrChild()));
			
			// Delete the in-Order successor
			root.setrChild(recDelete(root.getrChild(), root.getData()));
			
		}
		
		return root;
	}

	private Integer getMinValue(Node root) {
		int minv = root.getData();
		
		while(root.getlChild() != null) {
			minv = root.getlChild().getData();
			root = root.getlChild();
		}
		
		return minv;
	}
	
	public String toString() {
		return toStringInOrderTraversal(root);
	}
}
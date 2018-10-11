/*
Name: Daniel Villa
Class: CS 2302
Instructor: Dr. Olac Fuentes
Lab: 04
Purpose: The purpose of this lab is to create Binary Tree methods. This helps familiarize us with BTrees and how to traverse them.
TA: Zakia Al Kadri
Last Modified: October 18, 2017
*/

public class BTree{
	public BTreeNode root;
	private int t; //2t is the maximum number of children a node can have
	private int height;
	private int numberOfKeys; //is the number of keys in the BTree
	private int numberOfNodes; //is the number of nodes in the BTree
	
	public BTree(int t){
		root = new BTreeNode(t);
		this.t = t;
		height = 0;
		numberOfKeys = 0;
		numberOfNodes = 0;
	}
	
	public void printHeight(){
		System.out.println("Tree height is "+height);
	}
	
	public void insert(int newKey){
		if(isKInBTree(root, newKey) != true) {
			if (root.isFull()){//Split root;
				split();
				height++;
				//numberOfNodes+= 2;
			}
			root.insert(newKey);
			numberOfKeys++;
			numberOfNodes = countNodes(root);
		}
	}
	
	public void print(){
	// Wrapper for node print method
		root.print();
	}
	
	public void printNodes(){
	// Wrapper for node print method
		root.printNodes();
	}

	public void split(){
	// Splits the root into three nodes.
	// The median element becomes the only element in the root
	// The left subtree contains the elements that are less than the median
	// The right subtree contains the elements that are larger than the median
	// The height of the tree is increased by one

		// System.out.println("Before splitting root");
		// root.printNodes(); // Code used for debugging
		BTreeNode leftChild = new BTreeNode(t);
		BTreeNode rightChild = new BTreeNode(t);
		leftChild.isLeaf = root.isLeaf;
 		rightChild.isLeaf = root.isLeaf;
		leftChild.n = t-1;
		rightChild.n = t-1;
		int median = t-1;
		for (int i = 0;i<t-1;i++){
			leftChild.c[i] = root.c[i];
			leftChild.key[i] = root.key[i];
		}
		leftChild.c[median]= root.c[median];
		for (int i = median+1;i<root.n;i++){
			rightChild.c[i-median-1] = root.c[i];
			rightChild.key[i-median-1] = root.key[i];
		}
		rightChild.c[median]=root.c[root.n];
		root.key[0]=root.key[median];
		root.n = 1;
		root.c[0]=leftChild;
		root.c[1]=rightChild;
		root.isLeaf = false;
		// System.out.println("After splitting root");
		// root.printNodes();
	}
//*********************************************************************************//
	//5h)Returns the number of nodes in the BTree
	public int numberOfNodesInTree(){
		return numberOfNodes;
	}
	
//*********************************************************************************//
	//5i)Returns the number of keys in the BTree
	public int numberOfKeysInTree(){
		return numberOfKeys;
	}

//*********************************************************************************//
	//1.
	public boolean isKInBTree(BTreeNode T, int k){
		if(!T.isLeaf) { //if we are not in a leaf
			for(int i = 0; i<T.n; i++){ //compare all keys in the current node to k
				if(k == T.key[i]) //if we find k, return true
					return true;
				if(k < T.key[i]) //if k is smaller then the key, move to the left child
					return isKInBTree(T.c[i], k);
			}
			return isKInBTree(T.c[T.n], k); //k is bigger then all keys in the node, so move to the rightmost child
		}
		for(int i = 0; i<T.n; i++){ //when we are in a leaf
			if(k == T.key[i])
				return true;
		}
		return false;
	}
	
//*********************************************************************************//
	//Method counts the number of nodes in the tree then return that number
		public int countNodes(BTreeNode T) {
			int sum = 0;
			if(T.isLeaf)
				return 1;
			for(int i = 0; i<=T.n; i++) 
				sum += countNodes(T.c[i]);
			return sum+1;
		}
}
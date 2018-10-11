/*
Name: Daniel Villa
Class: CS 2302
Instructor: Dr. Olac Fuentes
Lab: 04
Purpose: The purpose of this lab is to create Binary Tree methods. This helps familiarize us with BTrees and how to traverse them.
TA: Zakia Al Kadri
Last Modified: October 18, 2017
*/

import java.util.Random;

public class BTreeTest{
	
//*********************************************************************************//
	//2. This method searches the BTree by comparing keys to k and returns the node it is in
	public static boolean searchBTree(BTreeNode T, int k){
		int i = 0;
		int j = T.n;
		while((i<j) && (k>T.key[i])){
			i+= (j-i)/2; //make i half of the current j to see if k is bigger or smaller then key[i]
			if((j-i)/2 == 0 && (k>T.key[i])) //if i is at the very end it will return 0 when divided. if k is still bigger we increase i by 1 so it is equal to T.n
				i++;
			if((k < T.key[i]) && (k<T.key[i-1]) && (i!=0)){ //if k is still smaller we set j to i and reset i, then search again
				j = i;
				i = 0;
			}
		}
		if((i == T.n) || (k<T.key[i])) { //k is not in current node
			if(T.isLeaf)
				return false;
			else
				return searchBTree(T.c[i], k);
		}
		else
			return true; //k is in the node
	}
	
	//This method searches the BTree by comparing keys to k and returns the node it is in (Orginal)
	public static BTreeNode searchBTree1(BTreeNode T, int k){
		int i = 0;
		while((i<T.n) && (k>T.key[i]))
			i++;
		if((i == T.n) || (k<T.key[i])) { //k is not in current node
			if(T.isLeaf)
				return null;
			else
				return searchBTree1(T.c[i], k);
		}
		else
			return T; //k is in the node
	}
		
//*********************************************************************************//
	//5a)Print keys in ascending order	
	public static void printAscending(BTreeNode T){
		//Prints all keys in the tree in ascending order
		if (T.isLeaf){
			for(int i =0; i<T.n;i++)
				System.out.print(T.key[i]+" ");
		}
		else{
			for(int i =0; i<T.n;i++){
				printAscending(T.c[i]);
				System.out.print(T.key[i]+" ");
			}
			printAscending(T.c[T.n]);

		}
   }
  
//*********************************************************************************//
  //5b)Print keys in descending order at depth d
  public static void printDescendingAtDepth(BTreeNode T, int d) {
	  if(d < 0) { //if d is smaller then 0, invalid depth
		  System.out.println("No keys at given depth");
		  return;
	  }
	  if(T.isLeaf && d > 0) { //If we are at a leaf and d is not 0, the depth does not exist
		  System.out.println("No keys at given depth");
		  return;
	  }
	  if(d == 0) { //if we find the right depth print from largest to smallest
		  for(int i=T.n-1; i>=0; i--) {
			  System.out.print(T.key[i] + " ");
		  }
		  return;
	  }
	  for(int i=T.n; i>=0; i--) { // continue until one of the base cases is true
		  printDescendingAtDepth(T.c[i], (d-1));
	  }
  }
  
//*********************************************************************************//
  //5c)Returns true if k is in the BTree
  public static boolean isKInBTree(BTreeNode T, int k){
	  int i = 0;
	  int j = T.n;
	  while((i<j) && (k>T.key[i])){
		  i+= (j-i)/2; //make i half of the current j to see if k is bigger or smaller then key[i]
		  if((j-i)/2 == 0 && (k>T.key[i])) //if i is at the very end it will return 0 when divided. if k is still bigger we increase i by 1 so it is equal to T.n
			  i++;
		  if((k < T.key[i]) && (k<T.key[i-1]) && (i!=0)){ //if k is still smaller we set j to i and reset i, then search again
			  j = i;
			  i = 0;
		  }
	  }
	  if((i == T.n) || (k<T.key[i])) { //k is not in current node
		  if(T.isLeaf)
			  return false;
		  else
			  return isKInBTree(T.c[i], k);
	  }
	  else
		  return true; //k is in the node
  }
  
//*********************************************************************************//
  //5d)Returns the minimum element in the BTree
  public static int giveSmallestElement(BTreeNode T) {
	  if(T.isLeaf) //if we are in a leaf, print the smallest element
		  return T.key[0];
	  return giveSmallestElement(T.c[0]);
  }
  
//*********************************************************************************//
  //5e)Returns the minimum element in the BTree at depth d
  public static int giveSmallestElementAtDepth(BTreeNode T, int d) {
	  if(d < 0) { //if d is smaller then 0, then it is an invalid depth, return -1
		  System.out.println("Error, depth given is invalid.");
		  return -1;
	  }
	  if(T.isLeaf && d > 0) { //if we are in a leaf and d never reached 0, then the given depth is bigger then the tree, return -1
		  System.out.println("Error, depth given exceeds height of the tree.");
		  return -1;
	  }
	  if(d == 0) //if d is 0, then return smallest element in T
		  return T.key[0];
	  return giveSmallestElementAtDepth(T.c[0], d-1);
  }
  
//*********************************************************************************//
  //5f)Returns the maximum element in the BTree
  public static int giveBiggestElement(BTreeNode T) {
	  if(T.isLeaf) //if we are in a leaf, return biggest element
		  return T.key[T.n-1];
	  return giveBiggestElement(T.c[T.n]);
  }
  
//*********************************************************************************//
  //5g)Returns the maximum element in the BTree at depth d
  public static int giveBiggestElementAtDepth(BTreeNode T, int d) {
	  if(d < 0) { //if d is smaller then 0, then it is an invalid depth, return -1
		  System.out.println("Error, depth given is invalid.");
		  return -1;
	  }
	  if(T.isLeaf && d > 0) { //if we are in a leaf and d never reached 0, then the given depth is bigger then the tree, return -1
		  System.out.println("Error, depth given exceeds height of the tree.");
		  return -1;
	  }
	  if(d == 0) //if d is equal to 0, return biggest element
		  return T.key[T.n-1];
	  return giveBiggestElementAtDepth(T.c[T.n], d-1);
  }
  
//*********************************************************************************//
  //5j)Returns the number of keys at depth d
  public static int numberOfKeysAtDepth(BTreeNode T, int d) {
	  if(d < 0) { //if d is smaller then 0, then it is an invalid depth, return -1
		  System.out.println("Error, depth given is invalid.");
		  return -1;
	  }
	  if(T.isLeaf && d > 0) { //if we are in a leaf and d never reached 0, then the given depth is bigger then the tree, return -1
		  System.out.println("Error, depth given exceeds height of the tree.");
		  return -1;
	  }
	  if(d == 0) //if d is equal to 0, return number of keys
		  return T.n;
	  int sum = 0;
	  for(int i = 0; i<=T.n; i++) { //add the number of keys from the children nodes
		  sum += numberOfKeysAtDepth(T.c[i], d-1);
	  }
	  return sum;
  }

//*********************************************************************************//
  //5k)Returns the sum of all keys in the BTree
  public static int sumOfAllKeys(BTreeNode T) {
	  int sum = 0;
	  if(T.isLeaf) { //if we are in a leaf node
		  for(int i = 0; i<T.n; i++) { //add all the keys together
			  sum += T.key[i];
		  }
		  return sum; //return the sum of the leaf node
	  }
	  for(int i = 0; i<T.n; i++) { //add all the keys together in T
		  sum += T.key[i];
	  }
	  for(int i = 0; i<=T.n; i++) { //add the sum of the T to the sum of all its child nodes
		  sum += sumOfAllKeys(T.c[i]);
	  }
	  return sum; //return resulting sum
  }
  
//*********************************************************************************//
  //5l)Returns the sum of the all keys in the tree at depth d
  public static int sumOfAllKeysInDepth(BTreeNode T, int d) {
	  int sum = 0;
	  if(d < 0) { //if d is smaller then 0, then it is an invalid depth, return -1
		  System.out.println("Error, depth given is invalid.");
		  return -1;
	  }
	  if(T.isLeaf && d > 0) { //if we are in a leaf and d never reached 0, then the given depth is bigger then the tree, return -1
		  System.out.println("Error, depth given exceeds height of the tree.");
		  return -1;
	  }
	  if(d == 0) { //if d is equal to 0, return sum of the keys
		  for(int i = 0; i<T.n; i++) {
			  sum += T.key[i];
		  }
		  return sum;
	  }
	  for(int i = 0; i<=T.n; i++) { //go into the child nodes looking for one of the base cases, then return that sum;
		  sum += sumOfAllKeysInDepth(T.c[i], d-1);
		  if(sum == -1) //if sum is equal to -1, the depth is larger then the tree's height and we break
			  break;
	  }
	  return sum;
  }
  
//*********************************************************************************//
  //5m)Returns the number of leaves in the BTree
  public static int numberOfLeaves(BTreeNode T) {
	  int sum = 0;
	  if(T.isLeaf) { //if T is a leaf, return 1
		  return 1;
	  }
	  for(int i = 0; i<=T.n; i++) //continue into the child nodes to look for leaves, then return sum of all the ones coming into the T from leaves
		  sum += numberOfLeaves(T.c[i]);
	  return sum;
  }
  
//*********************************************************************************//
  //5n)Returns the number of nodes in the BTree at depth d
  public static int numberOfNodesAtDepth(BTreeNode T, int d) {
	  int sum = 0;
	  if(d < 0) { //if d is smaller then 0, then it is an invalid depth, return -1
		  System.out.println("Error, depth given is invalid.");
		  return -1;
	  }
	  if(T.isLeaf && d > 0) { //if we are in a leaf and d never reached 0, then the given depth is bigger then the tree, return -1
		  System.out.println("Error, depth given exceeds height of the tree.");
		  return -1;
	  }
	  if(d == 0) //if d is equal to 0, return a 1 as you are in a node at depth d
		  return 1;
	  for(int i = 0; i<=T.n; i++) { //continue looking for the nodes at depth d, adding any nodes you find.
		  sum += numberOfNodesAtDepth(T.c[i], d-1);
		  if(sum == -1) //if sum is equal to negative one, then the depth is greater then the height of the tree. So break
			  break;
	  }
	  return sum;
  }
  
//*********************************************************************************//
  //5o)Returns the number of full nodes in the BTree at depth d
  public static int numberOfFullNodes(BTreeNode T) {
	  int sum = 0;
	  if(T.isLeaf && T.isFull() == true) //if T is a leaf and it is full, return 1
		  return 1;
	  if(T.isLeaf) //if T is a leaf and is not full, return 0
		  return 0;
	  if(T.isFull() == true){ //if the T is full and has children, check the children for full nodes and add 1 to the total sum
		  sum++;
		  for(int i = 0; i<=T.n; i++) {
			  sum += numberOfFullNodes(T.c[i]);
		  }
		  return sum;
	  }
	  for(int i = 0; i<=T.n; i++) { //if the T is not full and has children, check the children for full nodes
		  sum += numberOfFullNodes(T.c[i]);
	  }
	  return sum;
  }
  
//*********************************************************************************//
  //5p)Given k, return the depth at which k was found in the tree, return -1 if not found in the tree
  public static int findDepthAtK(BTreeNode T, int k) {
	  int sum = 0;
	  if(!T.isLeaf) { //if we are not in a leaf
		  for(int i = 0; i<T.n; i++){ //compare all keys in the current node to k
			  if(k == T.key[i]) //if we find k, return 0
				  return 0;
			  if(k < T.key[i]) { //if k is smaller then the key, move to the left child
				  sum = findDepthAtK(T.c[i], k);
				  if(sum != -1)
					  return sum + 1;
				  return sum;
			  }
		  }
		  sum = findDepthAtK(T.c[T.n], k); //k is bigger then all keys in the node, so move to the rightmost child
		  if(sum != -1) //if sum is not -1 (k was not found) add 1 to sum to increase the depth count then return sum
			  return sum + 1;
		  return sum;
			
	  }
	  for(int i = 0; i<T.n; i++){
		  if(k == T.key[i]) { //if we are in a node and we found k, return 0
			  return  0;
		  }
	  }
	  return -1; //if k is not found, return negative one
  }
  
//*********************************************************************************//
  //5q)Given k, print all the keys in the same node as k
  public static void printKeysInKNode(BTreeNode T, int k) {
	  if(!T.isLeaf) { //if we are not in a leaf
			for(int i = 0; i<T.n; i++){ //compare all keys in the current node to k
				if(k == T.key[i]) { //if we find k, print the keys
					for(int j = 0; j<T.n; j++)
						System.out.print(T.key[j] + " ");
					return;
				}
				if(k < T.key[i]) //if k is smaller then the key, move to the left child
					printKeysInKNode(T.c[i], k);
			}
			printKeysInKNode(T.c[T.n], k); //k is bigger then all keys in the node, so move to the rightmost child
		}
		for(int i = 0; i<T.n; i++){  //we are in a leaf, check all the keys in the node for k
			if(k == T.key[i]) { //if we find k, print out all the keys in the same node k was in
				for(int j = 0; j<T.n; j++)
					System.out.print(T.key[j] + " ");
				return;
			}
		}
		return;
  }
  
//*********************************************************************************//
   public static void main(String[] args)   {
	  int [] S ={6, 3, 16, 11, 7, 17, 14, 8, 5, 19, 15, 1, 2, 4, 18, 13, 9, 20, 10, 12, 21, 22}; 

	  BTree B = new BTree(3);
      for (int i=0;i<S.length;i++){
  			B.insert(S[i]);
		   B.printNodes();
         System.out.println("*********************");
      }
      //Test Methods
      int numberK = 8;
      int depth = 1;
      BTreeNode T = B.root;
      System.out.println("5a) Print list in ascending order: ");
      printAscending(T);
      System.out.println("\n\n5b)Print the list descending at depth " + depth);
      printDescendingAtDepth(T, depth);
      System.out.println("\n\n5c) Is " + numberK + " in the binary tree: " + isKInBTree(T, numberK));
      System.out.println("5d) The smallest element in the binary tree: "+ giveSmallestElement(T));
      System.out.println("5e) The smallest element at depth " + depth + ": "+ giveSmallestElementAtDepth(T, depth));
      System.out.println("5f) The biggest element in the binary tree: " + giveBiggestElement(T));
      System.out.println("5g) Biggest element at depth " + depth + " is: " + giveBiggestElementAtDepth(T, depth));
      System.out.println("5h) Number of nodes in the binary tree: " + B.numberOfNodesInTree());
      System.out.println("5i) Number of keys in the binary tree: " + B.numberOfKeysInTree());
      System.out.println("5j) Number of keys at depth " + depth + " is: " + numberOfKeysAtDepth(T, depth));
      System.out.println("5k) Sum of all the keys in the binary tree are: " + sumOfAllKeys(T));
      System.out.println("5l) Sum of all keys at depth " + depth + " is: " + sumOfAllKeysInDepth(T, depth));
      System.out.println("5m) Total number of leaves in the binary tree are: " + numberOfLeaves(T));
      System.out.println("5n) Number of nodes at depth " + depth + " is: " + numberOfNodesAtDepth(T, depth));
      System.out.println("5o) Amount of full nodes in the binary tree are: " + numberOfFullNodes(T));
      System.out.println("5p) " + numberK + " is at depth: " + findDepthAtK(T, depth));
      System.out.println("5q) Printing the keys in the same node as " + numberK + ": ");
      printKeysInKNode(T, numberK);
      System.out.println("\n*********************");
      
      //Build B-tree with random elements
      Random rn = new Random();
      BTree R = new BTree(4);
      for (int i=0;i<30;i++){
  			R.insert(rn.nextInt(100));
  			R.printNodes();
  			System.out.println("*********************");
      }
      T = R.root;
      System.out.println("5a) Print list in ascending order: ");
      printAscending(T);
      System.out.println("\n\n5b)Print the list decending at depth " + depth);
      printDescendingAtDepth(T, depth);
      System.out.println("\n\n5c) Is " + numberK + " in the binary tree: " + isKInBTree(T, numberK));
      System.out.println("5d) The smallest element in the binary tree: "+ giveSmallestElement(T));
      System.out.println("5e) The smallest element at depth " + depth + ": "+ giveSmallestElementAtDepth(T, depth));
      System.out.println("5f) The biggest element in the binary tree: " + giveBiggestElement(T));
      System.out.println("5g) Biggest element at depth " + depth + " is: " + giveBiggestElementAtDepth(T, depth));
      System.out.println("5h) Number of nodes in the binary tree: " + R.numberOfNodesInTree());
      System.out.println("5i) Number of keys in the binary tree: " + R.numberOfKeysInTree());
      System.out.println("5j) Number of keys at depth " + depth + " is: " + numberOfKeysAtDepth(T, depth));
      System.out.println("5k) Sum of all the keys in the binary tree are: " + sumOfAllKeys(T));
      System.out.println("5l) Sum of all keys at depth " + depth + " is: " + sumOfAllKeysInDepth(T, depth));
      System.out.println("5m) Total number of leaves in the binary tree are: " + numberOfLeaves(T));
      System.out.println("5n) Number of nodes at depth " + depth + " is: " + numberOfNodesAtDepth(T, depth));
      System.out.println("5o) Amount of full nodes in the binary tree are: " + numberOfFullNodes(T));
      System.out.println("5p) " + numberK + " is at depth: " + findDepthAtK(T, depth));
      System.out.println("5q) Printing the keys in the same node as " + numberK + ": ");
      printKeysInKNode(T, numberK);
      
      //Get the time of the two searchBTree methods
      //printAscending(T);
      /*Random rn = new Random();
      BTree R = new BTree(4);
      for (int i=0;i<10000;i++){
  			R.insert(rn.nextInt(100000));
  			//R.printNodes();
  			//System.out.println("*********************");
      }
      BTreeNode T = R.root;
      
      
      long startTime = System.nanoTime();
      searchBTree(T, 9362);
      long endTime = System.nanoTime();
      System.out.println("\nTime for Binary Search: " + (endTime - startTime));
      
      startTime = System.nanoTime();
      searchBTree1(T, 9362);
      endTime = System.nanoTime();
      System.out.println("Time for Orginal Search: " + (endTime - startTime));*/
	}
}
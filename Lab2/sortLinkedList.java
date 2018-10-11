/*
Name: Daniel Villa
Class: CS 2302
Instructor: Dr. Olac Fuentes
Lab: 02
Purpose: In this lab we learn how to use link list and compare them with bubble sort and merge sort
TA: Zakia Al Kadri
Last Modified: September 15 2017
*/

import java.util.*;
import java.lang.System;

public class sortLinkedList{
	
	/*
	*A method that recieves two integers, n and m, and returns the head iNode
	*n is the length of the list, m is the max num
	*/
	public static iNode buildLinkList(int n, int m){
		//Create list to import objects
		iNode newList = new iNode();
		//Random number generator
		Random randGenerator = new Random();
		//For loop to add random numbers to the list
		for (int i=0; i<n;i++){
			int randomNumber = randGenerator.nextInt(m) + 0;
			newList =new iNode(randomNumber, newList); 
		}
		return newList;
		
	}
	
	//This method prints out linked list
	public static void printList(iNode headNode){
		iNode printLinkedList = headNode;
        while (printLinkedList != null) {
            System.out.print(printLinkedList.item + " ");
			printLinkedList = printLinkedList.next;
        }
	}
	
	//This method clones the list given and returns the clone
	public static iNode cloneList(iNode headNode){
		iNode cloneNode = new iNode(headNode.item);
		iNode cloneMoveThroughList = cloneNode;
		
		//temp node to go through the lsit and give the nodes value to the clone
		iNode tempNode = headNode.next;
		
		//loop to move through the list, temp gives the current nodes value to the clone list
		while(tempNode != null){
			cloneMoveThroughList.next = new iNode(tempNode.item);
			cloneMoveThroughList = cloneMoveThroughList.next;
			tempNode = tempNode.next;
		}
		return cloneNode;
	}
//****************************************************************************************************************//
	//This method compares every element to every other element in the linked list
	public static void compareElementsInList(iNode headNode, int m){
		//boolean to show if a number is already taken
		boolean[] isNumberHere = new boolean[m];
		//counter to count how many duplicates the array has
		int duplicateCounter = 0;
		//Create Nodes to compare the each number to every other number in the list
		iNode numberToCompare = headNode; //current node we need to compare
		iNode compareToNumber = null;
		
		//Loop to traverse the list and find duplicates
		while(numberToCompare.next != null){
			compareToNumber = numberToCompare.next;
			while(compareToNumber != null){
				if(compareToNumber.item == numberToCompare.item && isNumberHere[compareToNumber.item] !=true)
					duplicateCounter++;
				compareToNumber = compareToNumber.next;
			}
			isNumberHere[numberToCompare.item] = true;
			numberToCompare = numberToCompare.next;
		}
		/*while(numberToCompare.next != null){
			if(isNumberHere[numberToCompare.item] != true)
				isNumberHere[numberToCompare.item] == true;
			else
				duplicateCounter++;
			numberToCompare = numberToCompare.next
		}*/
		System.out.println("The 1st method found: " + duplicateCounter + " duplicates");
	}
	
//****************************************************************************************************************//
	//This method sorts the list using bublesort then determines how many duplicates are in the list.
	public static iNode bubbleSortList(iNode headNode, int n, int m){
		//boolean to show if a number is already taken
		boolean[] isNumberHere = new boolean[m];
		//counter to count how many duplicates the array has
		int duplicateCounter = 0;
		//Variables to go through the list and swap
		iNode prevNode = null;
		iNode nextNode = null;
		iNode tempNode = new iNode();
		
		//Loop to arrange the list with bubble sort
		for(int i=0; i<n; i++){
			prevNode = headNode;
			for(int j=0; j<(n-i); j++){
				nextNode = prevNode.next;
				if(prevNode.item > nextNode.item){
					tempNode.item = prevNode.item;
					prevNode.item = nextNode.item;
					nextNode.item = tempNode.item;
				}
				prevNode = prevNode.next;
			}
		}
		
		return headNode;
	}

//****************************************************************************************************************//
	//this method splites the list recursively and merges the results together
	public static iNode mergeSortList(iNode headNode, int n, int m){
		//Base case to stop the recursion
		if(headNode == null || headNode.next == null)
			return headNode;
		
		//find the middle of the list
		iNode middleOfList = divideList(headNode, n);
		//split the list in 2 parts
		iNode rightList = middleOfList.next;
		middleOfList.next = null; //Removes the right half of the list for headNode
		
		//recursively continue to divide the list in half and return it when it is complete
		return mergeList(mergeSortList(headNode, n, m), mergeSortList(rightList, n, m));
	}
	
	//method to get to the middle point of the list given to this method
	public static iNode divideList(iNode headNode, int n){
		if(headNode == null)
			return headNode;
		
		//Create 2 variables, one to reach the middle, and the other to quickly reach the end
		iNode middleOfList = headNode;
		iNode runToEnd = headNode;
		
		/*Loop to get the middle of the current list
		*runToEnd will keep moving forward by an index of 2 so it will reach the end when middleOfList
		*reaches the middle of the list. */
		while(runToEnd.next != null && runToEnd.next.next != null){
			middleOfList = middleOfList.next;
			runToEnd = runToEnd.next.next;
		}
		return middleOfList;
	}
	
	//Method sorts the list and puts it together
	public static iNode mergeList(iNode leftList, iNode rightList){
		//Two vaiables tempHeadNode to hold the complete list and currentNode to build the list
		iNode tempHeadNode = new iNode();
		iNode currentNode = tempHeadNode;
		
		//while loop to add the values from smallest to biggest in current node.
		while(leftList != null && rightList != null){
			if(leftList.item <= rightList.item){
				currentNode.next = leftList;
				leftList = leftList.next;
			}else{
				currentNode.next = rightList;
				rightList = rightList.next;
			}
			currentNode = currentNode.next;
		}
		//Conditional to get the last value if the left list is null or if the right is null
		if(leftList == null)
			currentNode.next = rightList;
		else
			currentNode.next = leftList;
		
		return tempHeadNode.next;
	}
	
//****************************************************************************************************************//
	public static void findDuplicatesWithBoolean(iNode headNode, int m){
		//boolean to show if a number is already taken
		boolean[] isNumberHere = new boolean[m];
		//counter to count how many duplicates the array has
		int duplicateCounter = 0;
		//Create Nodes to compare the each number to every other number in the list
		iNode numberToCompare = headNode; //current node we need to compare
		
		//Loop to traverse the list and find duplicates
		while(numberToCompare != null){
			if(isNumberHere[numberToCompare.item] != true)
				isNumberHere[numberToCompare.item] = true;
			else
				duplicateCounter++;
			numberToCompare = numberToCompare.next;
		}
		
		System.out.println("The 4th method found: " + duplicateCounter + " duplicates");
	}
	
//****************************************************************************************************************//
	//This method runs all of the methods and gets their times
	public static void runMethod(int sizeOfList, int biggestNumber){
		//Generate list
		iNode intergerList = buildLinkList(sizeOfList, biggestNumber);
		System.out.println("Orignal List: ");
		printList(intergerList);
		System.out.println("\n\n");
		
		//Clone the list 4 times for each method
		iNode intergerList2 = cloneList(intergerList);
		iNode intergerList3 = cloneList(intergerList);		
		
		//Compare Elements to each other
		printList(intergerList);
		System.out.println();
		long startTime = System.nanoTime();
		compareElementsInList(intergerList, biggestNumber);
		long endTime = System.nanoTime();
		System.out.println("Time for 1st method: " + (endTime - startTime));
		System.out.println("\n\n");
		
		
		//Find duplicates using bubble sort
		startTime = System.nanoTime();
		intergerList2 = bubbleSortList(intergerList2, sizeOfList, biggestNumber);
		endTime = System.nanoTime();
		iNode tempNode = intergerList2;
		int duplicateCounter = 0;
		//do a single pass through the list to fins duplicates
		while(tempNode.next != null){
			if(tempNode.item == tempNode.next.item)
				duplicateCounter++;
			tempNode = tempNode.next;
		}
		printList(intergerList2);
		System.out.println();
		System.out.println("The 2th method found: " + duplicateCounter + " duplicates");
		System.out.println("Time for 2st method: " + (endTime - startTime));
		System.out.println("\n\n");
		
		
		
		//Finds duplicates by organizing the list using merge sort
		startTime = System.nanoTime();
		intergerList3 = mergeSortList(intergerList3, sizeOfList, biggestNumber);
		endTime = System.nanoTime();
		tempNode = intergerList3;
		duplicateCounter = 0;
		while(tempNode.next != null){
			if(tempNode.item == tempNode.next.item)
				duplicateCounter++;
			tempNode = tempNode.next;
		}
		printList(intergerList3);
		System.out.println();
		System.out.println("The 3th method found: " + duplicateCounter + " duplicates");
		System.out.println("Time for 3st method: " + (endTime - startTime));
		System.out.println("\n\n");
		
		
		//Finds dupicates with one pass and booleans
		
printList(intergerList);
		System.out.println();
		startTime = System.nanoTime();
		findDuplicatesWithBoolean(intergerList, biggestNumber);
		endTime = System.nanoTime();
		System.out.println("Time for 4st method: " + (endTime - startTime));
		System.out.println("\n\n");
	}

//****************************************************************************************************************//
	public static void main(String[]args){
		Scanner input = new Scanner(System.in);
		//Ask user what size they want the linked list to be
		System.out.println("What size do you want the linked list to be?");
		int sizeOfList = (input.nextInt()-1);
		System.out.println();
		//Ask the userfor the biggest number in the list
		System.out.println("What is the biggest number in the list?");
		int biggestNumber = input.nextInt();
		System.out.println();
		
		runMethod(sizeOfList, biggestNumber);
	}
}
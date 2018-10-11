/*
Name: Daniel Villa
Class: CS 2302
Instructor: Dr. Olac Fuentes
Lab: 06
Purpose: The purpose of this lab is to apply what we learned with our hash function, and add functionality for a heap and dsf methods.
TA: Zakia Al Kadri
Last Modified: 11/16/17
*/

public class Heap {
	/*
	In class, we used H[0] to store the size of the heap.
	In this lab, we will ’waste’ H[0], and use the field ’size’ to store the
	current size of the heap.
 	*/
	public HeapNode[] H;
	public int size;
	
	public Heap(int capacity) {
		H = new HeapNode[capacity + 1]; //We need to add 1 since we’re ’wasting’ H[0]
		this.size = 0;
	}
	
	//Use node.similarity * -1 when inserting ’node’ into the heap.
	//We have to do this because our heap puts at the top the node with the
	//smallest value, and we actually want to achieve the opposite.
	//We want the words with the greatest similarity value to be at the top.
	//Because of this, you must use node.similarity * -1
	//as the node’s value when inserting it into the heap.
	//...
	public void insert(HeapNode node){
		if(size >= H.length-1) {
			System.out.println("Heap is Full.");
			return;
		}
		size++;
		int i = size;
		while((i>1) && ((node.similarity*-1) < (H[i/2].similarity*-1))) {
			H[i] = H[i/2];
			i = i/2;
		}
		H[i] = node;
	}

	//Use node.similarity * -1 in this method as well.
	//...
	public HeapNode extractMin() {
		if(size <= 0)
			return null;
		HeapNode minVal = H[1];
		H[1] = H[size];
		size--;
		int i = 1;
		int min = 1;
		HeapNode temp;
		while(true) {
			if((2*i <= size) && (H[min].similarity*-1 > H[2*i].similarity*-1))
				min = 2*i;
			if((2*i+1 <= size) && (H[min].similarity*-1 > H[2*i+1].similarity*-1))
				min = 2*i+1;
			if(min == i)
				break;
			temp = H[i];
			H[i] = H[min];
			H[min] = temp;
			i = min;
		}
		return minVal;
	} 
}
/*
Name: Daniel Villa
Class: CS 2302
Instructor: Dr. Olac Fuentes
Lab: 07
Purpose: In this lab we have to solve the river problem using an adjacency list. Then we have to create an MST using the words and their similarities.
TA: Zakia Al Kadri
Last Modified: 11/30/17
*/

public class Heap {
	/*
	In class, we used H[0] to store the size of the heap.
	In this lab, we will ’waste’ H[0], and use the field ’size’ to store the
	current size of the heap.
 	*/
	public edgeNode[] H;
	public int size;
	
	public Heap(int capacity) {
		H = new edgeNode[capacity + 1]; //We need to add 1 since we’re ’wasting’ H[0]
		this.size = 0;
	}
	
	public void insert(edgeNode node){
		if(size >= H.length-1) {
			System.out.println("Heap is Full.");
			return;
		}
		size++;
		int i = size;
		while((i>1) && ((node.cost) < (H[i/2].cost))) {
			H[i] = H[i/2];
			i = i/2;
		}
		H[i] = node;
	}

	public edgeNode extractMin() {
		if(size <= 0)
			return null;
		edgeNode minVal = H[1];
		H[1] = H[size];
		size--;
		int i = 1;
		int min = 1;
		edgeNode temp;
		while(true) {
			if((2*i <= size) && (H[min].cost > H[2*i].cost))
				min = 2*i;
			if((2*i+1 <= size) && (H[min].cost > H[2*i+1].cost))
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
	
	public boolean isEmpty() {
		return size == 0;
	}
}
/*
Name: Daniel Villa
Class: CS 2302
Instructor: Dr. Olac Fuentes
Lab: 07
Purpose: In this lab we have to solve the river problem using an adjacency list. Then we have to create an MST using the words and their similarities.
TA: Zakia Al Kadri
Last Modified: 11/30/17
*/

public class Queue{
	
	private int QUEUE_SIZE = 5;
	private int[] items;
	private int front, back, count;
	
	public Queue() {
		items = new int[QUEUE_SIZE];
		front = 0;
		back = QUEUE_SIZE -1;
		count =0;
	}
	
	public boolean isEmpty(){
		return count ==0;
	}
	
	public boolean isFull(){
		return count == QUEUE_SIZE;
	}
	
	public void enqueue(int newItem){
		if (!isFull()){
			back = (back+1) % QUEUE_SIZE;
			items[back] = newItem;
			count++;
			return;
		} else {
			int countTemp = count; //count temp variable
			System.out.println("Queue is full. Doubling the size.");
			int[] newintArray = new int[QUEUE_SIZE*2];
			for(int i=0; i<QUEUE_SIZE; i++){
				newintArray[i] = this.dequeue();
			}
			
			//Double Queue Size and make items equal to the new array
			back = QUEUE_SIZE-1; //set back to the queue size before the doubling the queue size
			QUEUE_SIZE = QUEUE_SIZE*2;
			front = 0; //reset front
			count = countTemp; //count resets so set it equals to the temp value to not loose count
			items = newintArray;
			System.out.println("New max size is: " + QUEUE_SIZE);
			
			//Add new int
			back = (back+1) % QUEUE_SIZE;
			items[back] = newItem;
			count++;
			System.out.println("Entered the new item.");
			return;
		}
	}
		
	public int dequeue(){
		if (!isEmpty()){
			int queueFront = items[front];
			front = (front+1) % QUEUE_SIZE;
			count--;
			return queueFront;
		}else
			System.out.println("Trying to dequeue from empty queue");
			return 0;
	}
	
	public void dequeueAll(){
		items = new int[QUEUE_SIZE];
		front = 0;
		back = QUEUE_SIZE -1;
		count =0;
	}
	
	public int peek(){
		if (!isEmpty()) {
			return items[front];
		}
		else
			System.out.println("Trying to peek with empty queue");
			return 0;
	}

	public int size(){
		return count;
	}

}// End of class Queue
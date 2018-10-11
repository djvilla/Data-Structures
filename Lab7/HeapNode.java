/*
Name: Daniel Villa
Class: CS 2302
Instructor: Dr. Olac Fuentes
Lab: 07
Purpose: In this lab we have to solve the river problem using an adjacency list. Then we have to create an MST using the words and their similarities.
TA: Zakia Al Kadri
Last Modified: 11/30/17
*/

public class HeapNode {
	public String word0;
	public String word1;
	public double similarity;
	
	public HeapNode(String word0, String word1, double similarity) {
		super();
		this.word0 = word0;
		this.word1 = word1;
		this.similarity = similarity;
	}
	
	//prints the words plus the similarity of the node
	public void print() {
		System.out.println(word0 + " " + word1 + " " + similarity);
	}
	
}
/*
Name: Daniel Villa
Class: CS 2302
Instructor: Dr. Olac Fuentes
Lab: 06
Purpose: The purpose of this lab is to apply what we learned with our hash function, and add functionality for a heap and dsf methods.
TA: Zakia Al Kadri
Last Modified: 11/16/17
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
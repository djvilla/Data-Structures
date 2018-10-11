/*
Name: Daniel Villa
Class: CS 2302
Instructor: Dr. Olac Fuentes
Lab: 07
Purpose: In this lab we have to solve the river problem using an adjacency list. Then we have to create an MST using the words and their similarities.
TA: Zakia Al Kadri
Last Modified: 11/30/17
*/

public class sNode{
	public String word;
	public float[] embedding;
	public sNode next;
   
	public sNode(String S, float[] E, sNode N){
		word = S;
		embedding = new float[50];
		for (int i=0;i<50;i++)
			embedding[i] = E[i];
		next = N;
	}
}
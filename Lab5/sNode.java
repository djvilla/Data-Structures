/*
Name: Daniel Villa
Class: CS 2302
Instructor: Dr. Olac Fuentes
Lab: 05
Purpose: The purpose of this lab is to learn the operations of a hash table. This involves inputting elements, navigating through it,
and inputting information from a file into the hash table.
TA: Zakia Al Kadri
Last Modified: 11/03/17
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
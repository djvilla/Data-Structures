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

import java.lang.Math;

public class hashTableStrings{
	private sNode [] H;
	public hashTableStrings(int n){ // Initialize all lists to null
		H = new sNode[n];
		for(int i=0;i<n;i++)
			H[i] = null;
	}
	
	//gets the position of the list where k would be. K meaning the value of the word
	private int h(int k) {
		return k%H.length;
	}
	
	//returns the size of the list where H[i] is.
	public int sizeOfList(int i) {
		int count = 0;
		for(sNode t=H[i]; t!=null; t=t.next)
			count++;
		return count;
	}
	
	//inserts the word and embedding into the hash table
	public void insert(sNode k) {
		int pos = h(valueOfAWord(k.word));
		H[pos] = new sNode(k.word, k.embedding, H[pos]);
	}
	
	//searches for the word given in the table and returns the node it is located in.
	public sNode search(String newWord) {
		int pos = h(valueOfAWord(newWord));
		for(sNode t = H[pos]; t!=null; t=t.next)
			if(t.word.equals(newWord))
				return t;
		return null;
	}
	
	//returns a boolean indicating if the word is in the table or not
	public boolean inTable(String newWord) {
		return search(newWord) != null;
	}
	
	//helper method for print(), prints from the node given and on to the rest of the list
	private void printList(sNode x) {
		for(; x !=null; x = x.next)
			System.out.print(x.word + " ");
		System.out.println();
	}
	
	//prints the whole hash table
	public void print() {
		for(int i=0; i<H.length; i++) {
			System.out.print("H[" + i + "]: ");
			printList(H[i]);
		}
		System.out.println();
	}
	
	//This method finds a strings integer value by converting base 26 to base 10
	//For example 'a' integer value is 0 and 'z' integer value is 25
	//Based on the location of the word, like abc, the value is converted from left to right
	//('a')0*26^2 + ('b')1*26^1 + ('c')2*26^0
	public int valueOfAWord(String word) {
		char[] areLetters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		int value = 0;
		for(int i=0; i<word.length(); i++) {
			for(int j=0; j<areLetters.length; j++) {
				if(word.charAt(i) == areLetters[j])
					value += j * Math.pow(26, ((word.length()-1)-i));
			}
		}
		return value;
	}
	
	//this method calculates the load factor of the hash table
	public float load_factor() {
		int count = 0;
		for(int i=0; i<H.length; i++) {
			for(sNode t=H[i]; t!=null; t=t.next)
				count++;
		}
		return (float)count/H.length;
	}
	
	//This Method returns the number of empty list in the hash table
	public int emptyList() {
		int count = 0;
		for(int i=0; i<H.length; i++)
			if(H[i]==null)
				count++;
		return count;
	}
	
	//If the load factor exceeds the current predefined threshold(determined when it is called), this method increases the size of the
	//hash table by 2 times plus 1
	public hashTableStrings increaseHashSize() {
		hashTableStrings hash = new hashTableStrings(H.length*2+1);
		for(int i=0; i<H.length; i++) {
			for(sNode t=H[i]; t!=null; t=t.next) {
				hash.insert(t);
			}
		}
		return hash;
	}
	
	//This method gets the standard deviation of the length of the hash table
	public float standardDeviation() {
		int sum = 0;
		float mean = 0;
		for(int i=0; i<H.length; i++)
			sum += this.sizeOfList(i);
		mean = sum/(float)H.length;
		float standardDeviation = 0;
		for(int i=0; i<H.length; i++)
			standardDeviation+= Math.pow((this.sizeOfList(i)-mean), 2);
		return (float)Math.sqrt(standardDeviation/(double)H.length);
	}
	
	public int tableSize() {
		return H.length;
	}
}
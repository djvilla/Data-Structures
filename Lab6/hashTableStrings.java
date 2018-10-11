/*
Name: Daniel Villa
Class: CS 2302
Instructor: Dr. Olac Fuentes
Lab: 06
Purpose: The purpose of this lab is to apply what we learned with our hash function, and add functionality for a heap and dsf methods.
TA: Zakia Al Kadri
Last Modified: 11/16/17
*/

public class hashTableStrings{
	private sNode [] H;
	public hashTableStrings(int n){ // Initialize all lists to null
		H = new sNode[n];
		for(int i=0;i<n;i++)
			H[i] = null;
	}
	
	//gets the position of the list where k would be. K meaning the value of the word
	private int h(String S){
        int h = 0;
        for(int i=0;i<S.length();i++)
          h = (h*27+S.charAt(i))%H.length;
        return h;
	}
	
	//inserts the word and embedding into the hash table
	public void insert(sNode k) {
		int pos = h(k.word);
		H[pos] = new sNode(k.word, k.embedding, H[pos]);
	}
	
	public sNode search(String newWord) {
		int pos = h(newWord);
		for(sNode t = H[pos]; t!=null; t=t.next)
			if(t.word.equals(newWord))
				return t;
		return null;
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
	
	//returns a boolean indicating if the word is in the table or not
	public boolean inTable(String newWord) {
		return search(newWord) != null;
	}
}
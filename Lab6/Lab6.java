/*
Name: Daniel Villa
Class: CS 2302
Instructor: Dr. Olac Fuentes
Lab: 06
Purpose: The purpose of this lab is to apply what we learned with our hash function, and add functionality for a heap and dsf methods.
TA: Zakia Al Kadri
Last Modified: 11/16/17
*/

import java.io.FileInputStream;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.lang.Math;

public class Lab6 {
	
	//Check to see if the word starts with a letter. If it does it is a word. If it starts with a character it is not a word.
	public static boolean isAWord(String word) {
		char[] areLetters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		
		for(int i=0; i<areLetters.length; i++) {
			if(word.charAt(0) == areLetters[i])
				return true;
		}
		return false;
	}
	
	//This method reads the text file and creates a hash table
	public static hashTableStrings readTheFile() throws FileNotFoundException {
		//Variables
		String fileName = "/Users/danielvilla/Downloads/glove/glove.6B.50d.txt";
		String currentRow = "";
		hashTableStrings hash = new hashTableStrings(49157); //Start with a prime number
		
		//Create FileInputStream
		FileInputStream filescanner = new FileInputStream(fileName);
		Scanner in = new Scanner (filescanner);
		
		//Read the file and add the word with its embedding into the hash table
		while(in.hasNextLine()) {
			currentRow = in.nextLine();
			String [] currentRowArray = currentRow.split(" "); //split the line into an array
			if(isAWord(currentRowArray[0]) == true) {
				float[] lineFloats = new float[currentRowArray.length-1];
				for(int i=1; i<currentRowArray.length; i++) {
					lineFloats[i-1] = Float.parseFloat(currentRowArray[i]);
					//System.out.println(currentRowArray.length);
				}
				sNode temp = new sNode(currentRowArray[0], lineFloats, null);
				hash.insert(temp);
			}
		}
		in.close();
		return hash;
	}
	
	//Returns the length of the file for the array
	public static int lengthOfFile() throws FileNotFoundException{
		//Variables
		int counter = 0;
		String fileName = "/Users/danielvilla/Downloads/Lab6Words.txt";
		
		//Create FileInputStream
		FileInputStream filescanner = new FileInputStream(fileName);
		Scanner in = new Scanner (filescanner);
		
		//while loop to count the lines in the file
		while(in.hasNextLine()) {
			counter++;
			in.nextLine();
		}
		in.close();
		return counter;
	}

	//Creates a string of the words provided in Lab6Words.txt
	public static String[] getWordsFromTxtFile() throws FileNotFoundException{
		//Variables
		String[] words = new String[lengthOfFile()];
		String fileName = "/Users/danielvilla/Downloads/Lab6Words.txt";
		String currentRow = "";
		
		//Create FileInputStream
		FileInputStream filescanner = new FileInputStream(fileName);
		Scanner in = new Scanner (filescanner);
		
		int i = 0;
		while(in.hasNextLine()) {
			currentRow = in.nextLine();
			words[i] = currentRow;
			i++;
		}
		in.close();
		return words;
	}
	
	//This function gets the dot product between two float arrays
	public static float dotProduct(float[] e0, float[] e1) {
		float result = 0;
		for(int i=0; i<50; i++)
			result += e0[i] * e1[i];
		return result;
	}
	
	//This method compares 2 words by reading a text file and putting the similarity into a heap
	public static Heap compareWords(hashTableStrings h, String[] words) throws FileNotFoundException{
		//Create a new HeapNode to fill
		Heap heapList = new Heap(57000);
		
		//compare the words using the dot product
		for(int i=0; i< words.length; i++){
			for(int j=i+1; j<words.length; j++){
				sNode word1 = h.search(words[i]);
				sNode word2 = h.search(words[j]);
				float wordsDotProduct = dotProduct(word1.embedding, word2.embedding);
				float wordProduct1 = (float)Math.sqrt(dotProduct(word1.embedding, word1.embedding));
				float wordProduct2 = (float)Math.sqrt(dotProduct(word2.embedding, word2.embedding));
				HeapNode newHeap = new HeapNode(words[i], words[j], ((double)(wordsDotProduct/(wordProduct1*wordProduct2))));
				heapList.insert(newHeap);
			}
		}
		return heapList;
	}
	
	//Helper method for unionOnSimilarWords, finds the position of the word in the String array given
	public static int givePostionInArray(String[] wordArray, String word) {
		for(int i=0; i<wordArray.length; i++) {
			if(wordArray[i] == word)
				return i;
		}
		return -1; //word not in the array
	}
	
	//This method extracts the min n times and performs the union operation on the two words it gets, making it part of the same set in a DSF variable.
	public static DSF unionOnSimilarWords(String[] wordArray, Heap heapList) {
		DSF dsfVariable = new DSF(wordArray.length);
		
		int n = 5; //number of times the loop runs for
		HeapNode temp;
		
		for(int i=0; i<n; i++) {
			//System.out.println(i);
			temp = heapList.extractMin();
			//temp.print();
			int postionWord1 = givePostionInArray(wordArray, temp.word0);
			int postionWord2 = givePostionInArray(wordArray, temp.word1);
			dsfVariable.union_by_size(postionWord1, postionWord2);
		
		}
		
		return dsfVariable;
	}
	
	//Prints the m largest clusters in the dsf
	public static void printMLargestCluster(DSF dsfVariable, String[] wordArray) {
		int m = 2; //Number of largest clusters we have to print
		int smallestRootIndex = 0;
		int[] indexVisited = new int[m];
		for(int i=0; i<m; i++)
			indexVisited[i] = -1;
		for(int i=0; i<m; i++) {
			smallestRootIndex = dsfVariable.smallestRoot(smallestRootIndex, indexVisited); //finds the smallest root, ignore the previous index visited
			dsfVariable.printSet(smallestRootIndex, wordArray);
			indexVisited[i] = smallestRootIndex;
		}
	}
	
	public static void main(String[] args) {
		try {
			hashTableStrings hash = readTheFile();
			String[] word = getWordsFromTxtFile();
			Heap heapList = compareWords(hash, word);
			DSF dsfVariable = unionOnSimilarWords(word, heapList);
			dsfVariable.print();
			System.out.println();
			
			//long start = System.currentTimeMillis();
			printMLargestCluster(dsfVariable, word);
			//long end = System.currentTimeMillis();
			//System.out.println("\n" + (end - start));
		}
		catch(FileNotFoundException e) {
			System.out.println("Flie not Found.");
		}

	}

}

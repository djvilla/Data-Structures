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

import java.io.FileInputStream;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.lang.Math;

public class Lab5{
	
	//This method checks a string given to see if it is a word.
	//A word he is one that starts with a letter. If it
	public static boolean isAWord(String word) {
		char[] areLetters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		
		for(int i=0; i<areLetters.length; i++) {
			if(word.charAt(0) == areLetters[i])
				return true;
		}
		return false;
	}
	
	//This function gets the dot product between two float arrays
	public static float dotProduct(float[] e0, float[] e1) {
		float result = 0;
		for(int i=0; i<50; i++)
			result += e0[i] * e1[i];
		return result;
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
		//If the load factor exceeds the current predefined threshold, increase the size of the
		//hash table by 2 times plus 1
		while(hash.load_factor() > 20.00) { //The threshold of load factor. Lower the number, the lower the load factor will be.
			//System.out.println("Length and Load Factor: " + hash.tableSize() + " " + hash.load_factor());
			hash = hash.increaseHashSize();
		}
		in.close();
		return hash;
	}
	
	//This method compares 2 words by reading a text file
	public static void compareWords(hashTableStrings h) throws FileNotFoundException{
		//Variables
				String fileName = "/Users/danielvilla/Downloads/compare.txt";
				String currentRow = "";
				
				//Create FileInputStream
				FileInputStream filescanner = new FileInputStream(fileName);
				Scanner in = new Scanner (filescanner);
				
				//compare the words using the dot product
				while(in.hasNextLine()) {
					currentRow = in.nextLine();
					String [] currentRowArray = currentRow.split(" "); //split the line into an array
					System.out.print(currentRowArray[0] + " " + currentRowArray[1] + " ");
					sNode word1 = h.search(currentRowArray[0]);
					sNode word2 = h.search(currentRowArray[1]);
					float wordsDotProduct = dotProduct(word1.embedding, word2.embedding);
					float wordProduct1 = (float)Math.sqrt(dotProduct(word1.embedding, word1.embedding));
					float wordProduct2 = (float)Math.sqrt(dotProduct(word2.embedding, word2.embedding));
					System.out.println(wordsDotProduct/(wordProduct1*wordProduct2));
				}
				in.close();
	}
	
	//test standard deviation for debugging
	/*public static void testDeviation() {
		String[] areLetters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
		hashTableStrings hash = new hashTableStrings(6);
		float[] temp = new float[50];
		for(int i=0; i<50; i++)
			temp[i] = 1;
		for(int i=0; i<6; i++) {
			for(int j=0; j<i; j++) {
				sNode example = new sNode(areLetters[i], temp, null);
				hash.insert(example);
			}
		}
		hash.print();
		System.out.println("Standard deviation of the lengths of the lists: " + hash.standardDeviation());
	}*/
	
	//test dot product for debugging
	/*public static void testDotProduct() {
		float[] test1 = {-1, -2, 3};
		float[] test2 = {4, 0, -8};
		System.out.println("Dot product: " + dotProduct(test1, test2));
	}*/
	
	
	public static void main(String[]args) {
		try {
			hashTableStrings hash = readTheFile();
			//System.out.println("Length and Load Factor: " + hash.tableSize() + " " + hash.load_factor());
			//hash.print();
			System.out.println("Word Similarities:");
			compareWords(hash);
			System.out.println("\nTable Stats:");
			System.out.println("Load Factor: " + hash.load_factor());
			System.out.println("Number of empty lists: " + hash.emptyList());
			System.out.println("Standard deviation of the lengths of the lists: " + hash.standardDeviation());
		}
		catch(FileNotFoundException e) {
			System.out.println("Flie not Found.");
		}
	}
}
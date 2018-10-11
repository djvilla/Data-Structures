/*
Name: Daniel Villa
Class: CS 2302
Instructor: Dr. Olac Fuentes
Lab: 08
Purpose: The purpose of this lab is to work with dynamic programming. We have to know how the method edit distance functions, and how to modify it to suit
this lab's task
TA: Zakia Al Kadri
Last Modified: 12/7/17
*/

import java.io.FileInputStream;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.Math;

public class lab8 {
//////////***************************************************  [Edit Distance]  ******************************************************************************//////////
	//This method is a greatly modified version of edit distance. Returns the score if the two strings given
	public static int editDistance(String s1, String s2, int[][] blousumMatrix, String[] letters) {
		int sum = 0;
		if(s1.length()>s2.length()) { //If the length of s1 is greater than s2, we have to insert the missing letters, which cost -3 each
			sum += (s1.length()-s2.length()) * -3; //Add the amount of insertions to the total
			String tempS1 = s1.substring(s1.length()-s2.length()); //Since s1 has the greater length, we remove the extra strings to make s1 and s2 the same size
			String tempS2 = s2;
			for(int i=0; i< s2.length(); i++) {
				//System.out.println(tempS1.substring(0,1) + " " + tempS2.substring(0,1)); //For debugging
				sum += blousumMatrix[givePostionInArray(letters, tempS1.substring(0,1))][givePostionInArray(letters, tempS2.substring(0,1))]; //
				tempS1 = tempS1.substring(1);
				tempS2 = tempS2.substring(1);
			}
		}
		else if(s1.length()<s2.length()) { //If the length of s2 is greater than s1, we have to delete the extra letters, which cost -3 each
			sum += (s2.length()-s1.length()) * -3; //Add the amount of deletions to the total
			String tempS1 = s1;
			String tempS2 = s2.substring(s2.length()-s1.length()); //Since s2 has the greater length, we remove the extra strings to make s1 and s2 the same size
			for(int i=0; i< s1.length(); i++) {
				//System.out.println(tempS1.substring(0,1) + " " + tempS2.substring(0,1)); //For debugging
				sum += blousumMatrix[givePostionInArray(letters, tempS1.substring(0,1))][givePostionInArray(letters, tempS2.substring(0,1))];
				tempS1 = tempS1.substring(1);
				tempS2 = tempS2.substring(1);
			}
		}
		else { //The strings are the same size so no insertions or deletions are necessary
			String tempS1 = s1;
			String tempS2 = s2.substring(s2.length()-s1.length());
			for(int i=0; i< s1.length(); i++) {
				//System.out.println(tempS1.substring(0,1) + " " + tempS2.substring(0,1)); //For debugging
				sum += blousumMatrix[givePostionInArray(letters, tempS1.substring(0,1))][givePostionInArray(letters, tempS2.substring(0,1))];
				tempS1 = tempS1.substring(1);
				tempS2 = tempS2.substring(1);
			}
		}
		return sum;
	}
	
	
	/*public static int editDistance(String s1, String s2) {
		int[][] d = new int[s1.length()+1][s2.length()+1];
		for(int i=0; i<=s1.length(); i++)
			d[i][0] = i; //deletion
		for(int j=0; j<=s2.length(); j++)
			d[0][j] = j; //insertion
		for(int j=0; j<s2.length(); j++)
			for(int i=0; i<s1.length(); i++)
				if(s1.charAt(i) == s2.charAt(j)) //Match
					d[i+1][j+1] = d[i][j];
				else
					d[i+1][j+1] = 1 + Math.max(Math.max(d[i][j+1], d[i+1][j]), d[i][j]);
		return d[s1.length()][s2.length()];
	}*/
	
//////////***************************************************  [Helper Methods]  ******************************************************************************//////////
	//Helper method for editDistance, finds the position of the word in the String array given
	public static int givePostionInArray(String[] wordArray, String word) {
		for(int i=0; i<wordArray.length; i++) {
			if(wordArray[i].charAt(0) == word.charAt(0))
				return i;
		}
		System.out.println("Position not in array");
		return -1; //word not in the array
	}
	
	//Returns the length of the file for the array
	public static int lengthOfFile() throws FileNotFoundException{
		//Variables
		int counter = 0;
		String fileName = "/Users/danielvilla/Downloads/blosum62.txt";
		
		//Create FileInputStream
		FileInputStream filescanner = new FileInputStream(fileName);
		Scanner in = new Scanner (filescanner);
		
		//while loop to count the lines in the file
		while(in.hasNextLine()) {
			counter++;
			in.nextLine();
		}
		in.close();
		return counter-1; //Need to subtract one to exclude the first row, which contains the letters that represent each index of the array
	}
	
	//For debugging, prints a int 2d array
	public static void print2DArray(int[][] blousumMatrix) {
		for(int i=0; i<blousumMatrix.length; i++) {
			for(int j=0; j<blousumMatrix[i].length; j++)
				System.out.print(blousumMatrix[i][j] + " ");
			System.out.println();
		}
	}
	
	
//////////***************************************************  [Create Matrix & Letter Array]  ******************************************************************************//////////
	//This method gets the blousum 64 matrix and stores it in a 2d srting array
	public static int[][] createBlousumMatrix() throws FileNotFoundException{
		String fileName = "/Users/danielvilla/Downloads/blosum62.txt";
		
		//Create FileInputStream
		FileInputStream filescanner = new FileInputStream(fileName);
		Scanner in = new Scanner (filescanner);
		
		//Splits the first line into letters so we can obtain the numbers in the matrix
		String[] letters = in.nextLine().split(" ");
		int[][] blousumMatrix = new int[lengthOfFile()][lengthOfFile()];
		
		for(int i=0; i<blousumMatrix.length; i++) {
			String[] currentRow = in.nextLine().split(" ");
			//printString(currentRow);
			//System.out.println();
			for(int j=0; j<blousumMatrix[i].length; j++)
				blousumMatrix[i][j] = Integer.parseInt(currentRow[j]);
		}
		in.close();
		return blousumMatrix;
	}
	
	//This methods grabs the letters from the first line of the text for the matrix representation
	public static String[] createLettersArray() throws FileNotFoundException{
		String fileName = "/Users/danielvilla/Downloads/blosum62.txt";
		
		//Create FileInputStream
		FileInputStream filescanner = new FileInputStream(fileName);
		Scanner in = new Scanner (filescanner);
		
		//Gets the letters on the first line of the text file and makes them into a string array
		String[] letters = in.nextLine().split(" ");
		
		in.close();
		return letters;
	}
	
//////////***************************************************  [Find Matching Sequences]  ******************************************************************************//////////
	//This method reads a file, finds a sequence best match, and writes the best match with its score to a text file
	public static String[] matchingSequence(int[][] blousumMatrix, String[] letters) throws FileNotFoundException{
		String fileName = "/Users/danielvilla/Downloads/proteinSequences.txt";
		String fileName1 = "/Users/danielvilla/Documents/Eclipse/workspace/Lab8/src/bestMatchSequences.txt";
		
		//Create the string array to hold the sequences
		String[] sequences = new String[5348];
		
		//Create FileInputStream and PrintWriter to read then write to a file
		FileInputStream filescanner = new FileInputStream(fileName);
		Scanner in = new Scanner (filescanner);
		PrintWriter writer = new PrintWriter(fileName1);
		
		//Collect all sequences and store them in a array to compare
		int index = 0;
		while(in.hasNextLine()) {
			sequences[index] = in.nextLine();
			index++;
		}
		
		//Compares all sequences to each other
		for(int i=0; i< sequences.length; i++) {
			int score = Integer.MIN_VALUE; //score for the sequences
			int bestMatchingSequence = 0;
			for(int j=0; j< sequences.length; j++) {
				if(editDistance(sequences[i], sequences[j], blousumMatrix, letters) > score && i != j) {
					score = editDistance(sequences[i], sequences[j],blousumMatrix ,letters);
					bestMatchingSequence = j;
				}
			}
			writer.print(i+ ": " + sequences[i] + "  " + sequences[bestMatchingSequence] + "  " + score + "\n");
		}
		writer.close();
		in.close();
		return sequences;
	}
	
	
	//This method returns the sequences without having to generate a new file and compare all the sequences to each other.
	public static String[] returnSequence(int[][] blousumMatrix, String[] letters) throws FileNotFoundException{
		String fileName = "/Users/danielvilla/Downloads/proteinSequences.txt";
		
		//Create the string array to hold the sequences
		String[] sequences = new String[5348];
		
		//Create the FileInputStream to read the file
		FileInputStream filescanner = new FileInputStream(fileName);
		Scanner in = new Scanner (filescanner);
		
		//Collect all sequences and store them in a array to compare
		int index = 0;
		while(in.hasNextLine()) {
			sequences[index] = in.nextLine();
			index++;
		}
		
		in.close();
		return sequences;
	}
	
//////////***************************************************  [User Prompt]  ******************************************************************************//////////
	//Wrapper method for userTypeSequence, checks to see if the sequence given is valid. Returns whether it is valid or not
	public static boolean isASequence(String word) {
		char[] areLetters = {'C', 'S', 'T', 'P', 'A', 'G', 'N', 'D', 'E', 'Q', 'H', 'R', 'K', 'M', 'I', 'L', 'V', 'F', 'Y', 'W'};
		int numOfValidLetters = 0; //number of valid letters found in the given sequence
		
		for(int i=0; i<word.length(); i++) {
			for(int j=0; j<areLetters.length; j++) {
				if(word.charAt(i) == areLetters[j]) //If the current letter matches one of the valid letters
					numOfValidLetters++;
			}
		}
		return word.length() == numOfValidLetters; //if the valid total matches the given sequence length, then the whole sequence is valid
	}
	
	//This method prompts the user and asks to type in a valid sequence.
	public static void userTypeSequence(String[] sequences, int[][] blousumMatrix, String[] letters) {
		//Variables
		Scanner input = new Scanner(System.in);
		String userSequence = "";
		
		boolean stayInLoop = true; //boolean exits the loop, when user inputs a valid sequence
		
		//We stay in the while loop until the user enters a correct sequence
		while(stayInLoop) {
			//Ask the User to type in a sequence
			System.out.println("Type in a sequence that you want to compare to the data base.\nValid words are: \"C, S, T, P, A, G, N, D, E, Q, H, R, K, M, I, L, V, F, Y, W\"");
			userSequence = input.next();
			//check if the users input is valid, if not, inform then repeat. If it is valid, leave the loop
			if(isASequence(userSequence))
				stayInLoop = false;
			else
				System.out.println("Not a valid sequence");
		}
		
		//compare the users sequence to all the sequences in the data base
		int score = Integer.MIN_VALUE; //score for the sequences
		int bestMatchingSequence = 0;
		for(int i=0; i< sequences.length; i++) {
			if(editDistance(userSequence, sequences[i], blousumMatrix, letters) > score) {
				score = editDistance(userSequence, sequences[i],blousumMatrix ,letters);
				bestMatchingSequence = i;
			}
		}
		//Print out the sequence that closely matches the user, along with the score
		System.out.println("The sequence that best matches your input is the following: ");
		System.out.println("Your Sequence: " + userSequence + "\nMatching Sequence: " + sequences[bestMatchingSequence] + "\nScore: " + score);
		input.close();
	}
	
	
	
	
//////////***************************************************  [Main]  ******************************************************************************//////////
	public static void main(String[] args) {
		try {
			int[][] blousumMatrix = createBlousumMatrix();
			String[] letters = createLettersArray();
			
			//Tests:
			//print2DArray(blousumMatrix);
			//System.out.println(editDistance("APST", "PAT", blousumMatrix, letters)); // 9
			//System.out.println(editDistance("CPD", "ACAT", blousumMatrix, letters)); // 6
			//System.out.println(editDistance("CAT", "CAT", blousumMatrix, letters)); //17
			//System.out.println(editDistance("PAG", "CST", blousumMatrix, letters)); //-1
			//System.out.println(editDistance("NKNFFWAVKPKAVRQ", "NNQNFFWAVKPKVVR", blousumMatrix, letters)); //3
			//System.out.println(editDistance("NKNFFWAVKPKAVRQ", "QTLIAIHTLAIRYAN", blousumMatrix, letters)); //21
			
			//String[] sequences = matchingSequence(blousumMatrix, letters);
			
			//Time of getting the sequences
			String[] sequences = returnSequence(blousumMatrix, letters);
			long startTime = System.currentTimeMillis();
			userTypeSequence(sequences, blousumMatrix, letters);
			long endTime = System.currentTimeMillis();
			System.out.println(endTime-startTime);
			
			System.out.println("\n\nDone");
		}
		catch(FileNotFoundException e) {
			System.out.println("File not Found.");
		}
	}
}

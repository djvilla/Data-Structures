/*
Name: Daniel Villa
Class: CS 2302
Instructor: Dr. Olac Fuentes
Lab: 07
Purpose: In this lab we have to solve the river problem using an adjacency list. Then we have to create an MST using the words and their similarities.
TA: Zakia Al Kadri
Last Modified: 11/30/17
*/

import java.io.FileInputStream;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.lang.Math;

public class minimumSpanningTree {
	
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
		
		//This method builds an adjacency matrix with the words from Lab6Words.txt
		public static float[][] createAdjacencyMatrix(hashTableStrings h, String[] words){
			//Create adjacency matrix for words
			float[][] wordsGraph = new float[words.length][words.length];
			
			//Compare the words using dot product and place their similarities into the matrix
			for(int i=0; i<wordsGraph.length; i++) {
				for(int j=0; j<wordsGraph[i].length; j++) {
					//Get similarity
					sNode word1 = h.search(words[i]);
					sNode word2 = h.search(words[j]);
					float wordsDotProduct = dotProduct(word1.embedding, word2.embedding);
					float wordProduct1 = (float)Math.sqrt(dotProduct(word1.embedding, word1.embedding));
					float wordProduct2 = (float)Math.sqrt(dotProduct(word2.embedding, word2.embedding));
					wordsGraph[i][j] = ((float)(wordsDotProduct/(wordProduct1*wordProduct2)));
				}
			}
			return wordsGraph;
		}
		
		//Helper method for Prim_MST, finds the position of the word in the String array given
		public static int givePostionInArray(String[] wordArray, String word) {
			for(int i=0; i<wordArray.length; i++) {
				if(wordArray[i] == word)
					return i;
			}
			return -1; //word not in the array
		}
		
		//uses the prim algorithm to search for the Minimum Spanning Tree (MST) of the graph
		public static edgeNode Prim_MST(float[][] G, String[] words) {
			int v0 = 0;
			Heap H = new Heap(G.length*G.length);
			DSF S = new DSF(G.length);
			edgeNode MST = null;
			edgeNode m;
			for(int i=0; i<G[v0].length; i++)
				H.insert(new edgeNode(words[i], words[v0], G[i][v0]));
			while(!H.isEmpty()) {
				m = H.extractMin();
				if(S.union_c(givePostionInArray(words, m.source), givePostionInArray(words, m.dest)) == 1) {
					//System.out.println("HI");
					MST = new edgeNode(m.source, m.dest, m.cost, MST);
					for(int i=0; i<G.length; i++) {
						if(!S.inSameSet(v0, i)) {
							//System.out.println("HI");
							H.insert(new edgeNode(m.dest, words[i], G[givePostionInArray(words, m.dest)][i]));
						}
					}
				}
			}
			//S.print();
			return MST;
		}
		
		//This method prints an edgeNode List
		public static void printEdgeNode(edgeNode MST){
			int i=1;
			for(edgeNode T= MST; T!=null; T=T.next) {
				System.out.print(i + ": ");
				T.print();
				i++;
			}
		}
	
	
	
	public static void main(String[] args) {
		try {
			hashTableStrings hash = readTheFile();
			String[] word = getWordsFromTxtFile();
			float[][] graph = createAdjacencyMatrix(hash, word);
			
			/*for(int i=0; i<graph.length; i++) {
				for(int j=0; j<graph.length; j++) {
					System.out.print(graph[i][j] + " ");
				}
				System.out.println();
			}*/
			
			edgeNode MST = Prim_MST(graph, word);
			printEdgeNode(MST);
			
		}
		catch(FileNotFoundException e) {
			System.out.println("Flie not Found.");
		}
	}

}

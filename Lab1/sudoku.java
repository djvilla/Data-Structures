/*
Name: Daniel Villa
Class: CS 2302
Instructor: Dr. Olac Fuentes
Lab: 01
Purpose: To learn how to caluclate calculation time and how loops effect runtime
TA: Zakia Al Kadri
Last Modified: September 08 2017
*/

import java.lang.Math;
import java.lang.System;
import java.util.*;

public class sudoku{
	
/***************************************************************/
//Generates sudoku board to use for the algorithm
	public static int[][] generateBoard(int n){ //n is the size of the board
		int startNum;
		//The board with n width and n height
		int[][] sudokuBoard = new int[n][n];

		//Loop to generate a solved sudoku puzzle
		for(int i=0; i<n; i++){
			startNum = (int)(Math.sqrt(n)*(i%Math.sqrt(n))+(i/Math.sqrt(n)));
			for(int j=0; j<n; j++){
				sudokuBoard[i][j] = ((startNum + j)%n)+1;
			}
		}
		
		//Method to clear a random square per row
		sudokuBoard = randomBlankSpace(sudokuBoard);
		//sudokuBoard = diagonalBlankSpace(sudokuBoard);
		
		return sudokuBoard;
	}
	
//This method generates a blank space diagonally across the puzzle
	public static int[][] diagonalBlankSpace(int[][] sudokuBoard){
		for(int i = 0; i<sudokuBoard.length; i++){
			sudokuBoard[i][i] = -100;
		}
		return sudokuBoard;
	}
	
//This method generates a random black space per row that is not already in a column
	public static int[][] randomBlankSpace(int[][] sudokuBoard){
		//Boolean array that shows if there is a
		boolean[] numberIsHere = new boolean[sudokuBoard.length];
		for(int i=0; i<numberIsHere.length; i++)
			numberIsHere[i] = true;
		
		//Random object to choose a random position per row
		Random randGen = new Random();
		
		//Loop to clear a random square per row
		for(int i = 0; i<sudokuBoard.length; i++){
			//postion to delete, -100 is a blank space
			int deletePostion = randGen.nextInt(9) + 0;
			if(numberIsHere[deletePostion] == true){
				numberIsHere[deletePostion] = false;
				sudokuBoard[i][deletePostion] = -100;
			}
			else{
				//While the random number gives a postion in a row with a blank space, generate a new postion
				while(numberIsHere[deletePostion] == false){
					deletePostion = randGen.nextInt(9) + 0;
				}
				//Once the number is found set the postion in the boolean array to false and make it blank with a -100
				numberIsHere[deletePostion] = false;
				sudokuBoard[i][deletePostion] = -100;
			}
		}
		return sudokuBoard;
	}

//Prints a 2d array, the board
	public static void print2dArray(int[][] board){
		for(int i = 0; i<board.length; i++){
			for(int j = 0; j<board.length; j++){
				System.out.print(board[i][j] + "	");
			}
			System.out.println();
		}
	}
/***************************************************************/
//Does the Naive algorithm
	public static void naiveAlgorithm(int[][] sudokuBoard){
		boolean isNumPresent = false;
		for(int i=0; i<sudokuBoard.length; i++){ //for rows
			for(int j=1; j<=sudokuBoard.length; j++){ //number missing in the "blank" space
				isNumPresent = false;
				for(int k=0; k<sudokuBoard.length; k++){ //for columns
					if(sudokuBoard[i][k] == j){ //if the number in position i and k is equal to j, isNumPresent is true
						isNumPresent = true;
						break;
					}
				}
				if(isNumPresent != true) //if isNumPresent is false, we found our missing number
					System.out.println("The missing number in row: " + (i+1) + " is " + j);
			}
		}
	}
	
/***************************************************************/
//Runs the not so naive algorithm
	public static void notSoNaiveAlgorithm(int[][] sudokuBoard){
		boolean[] isNumPresent = new boolean[sudokuBoard.length+1];
		for(int i=0; i<sudokuBoard.length; i++){ //for rows
			for(int j=1; j<=sudokuBoard.length; j++){ //finds missing number
				isNumPresent[j] = false;
			}
			for(int j=0; j<sudokuBoard.length; j++){ //for columns
				if(sudokuBoard[i][j] > 0) //if the value of position i and j are known numbers, than isNumPresent is true
					isNumPresent[sudokuBoard[i][j]] = true;
			}
			for(int j=1; j<=sudokuBoard.length; j++){
				if(isNumPresent[j] != true) //if isNumPresent at j is false, then we found our missing number
					System.out.println("The missing number in row: " + (i+1) + " is " + j);
			}
		}
	}
/***************************************************************/
//Runs the not naive algorithm
	public static void notNaive(int[][] sudokuBoard){
		int sum = sudokuBoard.length * (sudokuBoard.length+1)/2;
		for(int i=0; i<sudokuBoard.length; i++){
			int missingNum = sum;
			for(int j=0; j<sudokuBoard.length; j++){
				if(sudokuBoard[i][j] > 0) //if the value at i and j is a known number, than missing sum is subtracted from the value at i and j
					missingNum-= sudokuBoard[i][j];
			}
			System.out.println("The missing number in row: " + (i+1) + " is " + missingNum);
		}
	}
/***************************************************************/
	//This method gets the time it takes to calculate the naive algorithm in nanoseconds
	public static long timeOfNaiveAlgorithm(int[][] sudokuBoard){
		long startTime = System.nanoTime();
		naiveAlgorithm(sudokuBoard);
		long endTime = System.nanoTime();
		System.out.println("\n");
		return endTime - startTime;
	}

	//This method gets the time it takes to calculate the Not-So-Naive algorithm in nanoseconds
	public static long timeOfNotSoNaiveAlgorithm(int[][] sudokuBoard){
		long startTime = System.nanoTime();
		notSoNaiveAlgorithm(sudokuBoard);
		long endTime = System.nanoTime();
		System.out.println("\n");
		return endTime - startTime;
	}
	
	//This method gets the time it takes to calculate the Not-So-Naive algorithm in nanoseconds
	public static long timeOfNotNaiveAlgorithm(int[][] sudokuBoard){
		long startTime = System.nanoTime();
		notNaive(sudokuBoard);
		long endTime = System.nanoTime();
		System.out.println("\n");
		return endTime - startTime;
	}
/***************************************************************/
	public static void main(String[]args){
		Scanner input = new Scanner(System.in);
		
		//Create the 4 sudoku grids and print it for the user
		int[][] sudokuBoard1 = generateBoard(9);
		System.out.println("9x9 done");
		/*int[][] sudokuBoard2 = generateBoard(25);
		System.out.println("25x25 done");
		int[][] sudokuBoard3 = generateBoard(2500);
		System.out.println("2500x2500 done");
		int[][] sudokuBoard4 = generateBoard(10000);
		System.out.println("10000x10000 done");*/
		
		print2dArray(sudokuBoard1);
		System.out.println("\n\n");
		
		//ask the user how they want to solve the puzzle
		System.out.println("Do you want to solve this puzzle with a Naive Algorithm, press \"1\",\na Not-so-Naive Algorithm, press \"2\", or a Not-Naive Algorithm, press \"3\".");
		int userAnswer = input.nextInt();
		System.out.println("\n\n");
		
		//the users choices, 1 for Naïve Algorithm, 2 for Not-so-Naïve Algorithm, 3 for Not-Naïve Algorithm
		if(userAnswer == 1){ //Naïve Algorithm
			long timeBoard1 = timeOfNaiveAlgorithm(sudokuBoard1); //Gets time for 9x9 grid
			/*long timeBoard2 = timeOfNaiveAlgorithm(sudokuBoard2); //Gets time for 25x25 grid
			long timeBoard3 = timeOfNaiveAlgorithm(sudokuBoard3); //Gets time for 2500x2500 grid
			long timeBoard4 = timeOfNaiveAlgorithm(sudokuBoard4); //Gets time for 10000x10000 grid
			System.out.println("Time to calculate Naive Algorithm: \n" + "9x9: " + timeBoard1 + "\n" + "25x25: " + timeBoard2 + "\n" + "2500x2500: " + timeBoard3 + "\n" + "10000x10000: " + timeBoard4);
		*/
		}else if(userAnswer == 2){ //Not-so-Naïve Algorithm
			long timeBoard1 = timeOfNotSoNaiveAlgorithm(sudokuBoard1); //Gets time for 9x9 grid
			/*long timeBoard2 = timeOfNotSoNaiveAlgorithm(sudokuBoard2); //Gets time for 25x25 grid
			long timeBoard3 = timeOfNotSoNaiveAlgorithm(sudokuBoard3); //Gets time for 2500x2500 grid
			long timeBoard4 = timeOfNotSoNaiveAlgorithm(sudokuBoard4); //Gets time for 10000x10000 grid
			System.out.println("Time to calculate Not-So-Naive Algorithm: \n" + "9x9: " + timeBoard1 + "\n" + "25x25: " + timeBoard2 + "\n" + "2500x2500: " + timeBoard3 + "\n" + "10000x10000: " + timeBoard4);
		*/
		}else if(userAnswer == 3){ //Not-Naïve Algorithm
			long timeBoard1 = timeOfNotNaiveAlgorithm(sudokuBoard1); //Gets time for 9x9 grid
			//long timeBoard2 = timeOfNotNaiveAlgorithm(sudokuBoard2); //Gets time for 25x25 grid
			//long timeBoard3 = timeOfNotNaiveAlgorithm(sudokuBoard3); //Gets time for 2500x2500 grid
			//long timeBoard4 = timeOfNotNaiveAlgorithm(sudokuBoard4); //Gets time for 10000x10000 grid
			//System.out.println("Time to calculate Not-Naive Algorithm: \n" + "9x9: " + timeBoard1 + "\n" + "25x25: " + timeBoard2 + "\n" + "2500x2500: " + timeBoard3 + "\n" + "10000x10000: " + timeBoard4);
		}else
			System.out.println("Answer was not recognized");
		
		
		//naiveAlgorithm(sudokuBoard);
		//notSoNaiveAlgorithm(sudokuBoard);
		//notNaive(sudokuBoard);
	}
}
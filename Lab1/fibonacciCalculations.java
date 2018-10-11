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
import java.util.Scanner;
import java.lang.System;

public class fibonacciCalculations{
	
	
	public static void main(String[]args){
		//Vairables
		Scanner input = new Scanner(System.in);
		long startTime;
        long endTime;
        long totalTime;
		
		//Get the number to put through the fibonacci process
		//System.out.println("What number do you want to use?");
		int userNumber = 5;
		
		//Ask the user which process they want to do
		System.out.println("Do you want to use Fibonacci Recursion, \"1\", or Fibonacci Iteration, \"2\", or Fibonacci Formula, \"3\".");
		System.out.println("If you want to quit enter \"0\"");
		int userAnswer =  input.nextInt();
		System.out.println();
		
		//Matches user answer to the process they selected
		if(userAnswer == 1){
			for(int i = 0; i<6; i++){
				startTime = System.nanoTime();
				fibbRecursion(userNumber);
				endTime = System.nanoTime();
				System.out.println("Time to calculate " + userNumber + ": " + (endTime - startTime));
				System.out.println();
				if(userNumber<20)
					userNumber+= 5;
				else
					userNumber+= 10;
			}
		}
		else if(userAnswer == 2){
			for(int i = 0; i<6; i++){
				startTime = System.nanoTime();
				fibbIteration(userNumber);
				endTime = System.nanoTime();
				System.out.println("Time to calculate " + userNumber + ": " + (endTime - startTime));
				System.out.println();
				if(userNumber<20)
					userNumber+= 5;
				else
					userNumber+= 10;
			}
		}
		else if(userAnswer == 3){
			for(int i = 0; i<6; i++){
				startTime = System.nanoTime();
				fibbFormula(userNumber);
				endTime = System.nanoTime();
				System.out.println("Time to calculate " + userNumber + ": " + (endTime - startTime));
				System.out.println();
				if(userNumber<20)
					userNumber+= 5;
				else
					userNumber+= 10;
			}
		}
		else if(userAnswer == 0)
			System.out.println("Exiting....");
		else
			System.out.println("Your input was not recognized.");
	}
	//Fibonacci sequence with recursion
	public static int fibbRecursion(int n){
		if (n==0) return 0;
        if (n==1) return 1;
        return fibbRecursion(n-1) + fibbRecursion(n-2);
	}
	//Fibonacci sequence with iteration
	public static long fibbIteration(int n){
		long[] fibonacci = new long[n+1];
        fibonacci[0] = 0;
        fibonacci[1] = 1;
        for (int i=2; i<=n; i++) {
            fibonacci[i] = fibonacci[i-2] + fibonacci[i-1]; 
        }
        return fibonacci[n];
	}
	//Fibonacci sequence with a formula
	public static long fibbFormula(int n){
		return (long)((Math.pow((Math.sqrt(5)+1)/2, n) - Math.pow((1-Math.sqrt(5))/2, n))/ Math.sqrt(5));
	}
}
/*
Name: Daniel Villa
Class: CS 2302
Instructor: Dr. Olac Fuentes
Lab: 07
Purpose: In this lab we have to solve the river problem using an adjacency list. Then we have to create an MST using the words and their similarities.
TA: Zakia Al Kadri
Last Modified: 11/30/17
*/

public class graphNode{
   int  dest;
   int cost; //Set to 1 if graph is unweighted
   graphNode next;
    
   public graphNode(int dest, int cost, graphNode next){
      this.dest = dest;
      this.cost = cost;      
      this.next = next;
   }

   public graphNode(int dest,  graphNode next){
      this.dest = dest;
      this.cost = 1;        
      this.next = next;
   }
}
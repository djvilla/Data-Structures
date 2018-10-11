/*
Name: Daniel Villa
Class: CS 2302
Instructor: Dr. Olac Fuentes
Lab: 07
Purpose: In this lab we have to solve the river problem using an adjacency list. Then we have to create an MST using the words and their similarities.
TA: Zakia Al Kadri
Last Modified: 11/30/17
*/

public class edgeNode{
   String  source;
   String  dest;
   float cost; //Set to 1 if graph is unweighted
   edgeNode next;

   public edgeNode(String source, String dest, float cost){
	      this.source = source;
	      this.dest = dest;
	      this.cost = cost;
	   }
   
   public edgeNode(String source, String dest, float cost, edgeNode next){
      this.source = source;
      this.dest = dest;
      this.cost = cost;
      this.next = next;
   }

    public edgeNode(String source, String dest, edgeNode next){
      this.source = source;
      this.dest = dest;
      this.cost = 1;
      this.next = next;
   }
    
  //prints the words plus the similarity of the node
  	public void print() {
  		System.out.println(source + " " + dest + " " + cost);
  	}
}
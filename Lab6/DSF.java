/*
Name: Daniel Villa
Class: CS 2302
Instructor: Dr. Olac Fuentes
Lab: 06
Purpose: The purpose of this lab is to apply what we learned with our hash function, and add functionality for a heap and dsf methods.
TA: Zakia Al Kadri
Last Modified: 11/16/17
*/

public class DSF{

	private int[] S;
	public DSF(int n){ /* Initialize disjoint set forest with n elements.
		Each element is a root */
        S = new int[n];
        for(int i=0;i<n;i++)
        	S[i] = -1;
	}
    public int find_c(int i){ // Find with path compression
        if(S[i]<0)
        		return i;
        return S[i] = find_c(S[i]);
    }
    public int union_c(int i, int j){ // Union with path compression
        int ri = find_c(i);
        int rj = find_c(j);
        if(ri == rj)
           return -1;
        S[rj] = ri;
        return 1;
    }
     public int union_by_size(int i, int j){
        int ri = find_c(i);
        int rj = find_c(j);
        if(ri==rj)return -1;
        if(S[ri]>S[rj]){
           S[rj]+=S[ri];
           S[ri]=rj; 
        }
        else{
           S[ri]+=S[rj];
           S[rj]=ri;
        }
        return 1; 
     }
     public void print(){
        for(int i=0;i<S.length;i++)
           System.out.print(S[i]+" ");
        System.out.println();
     }
     
     //Finds the smallest root and its index in the dsf to determine the sizes of the cluster. The smaller the root the more values it contains in a cluster
     //index 0 is the cluster size and index 1 is the the location where the root is located
     public int smallestRoot(int smallestValue, int[] indexsAlreadyVisited) {
    	 	int root = -1;
    	 	int clusterSize = 0;
    	 	for(int i=0; i<S.length; i++) {
    	 		if(S[i] < clusterSize && didNotVistBefore(indexsAlreadyVisited, i)) {
    	 			root = i;
    	 			clusterSize = S[i];
    	 		}
    	 	}
    	 	return root;
     }
     
     //Wrapper for smallestRoot, returns a boolean if the index has already been visited
     public boolean didNotVistBefore(int[] indexsAlreadyVisited, int index) {
    	 	for(int i=0; i<indexsAlreadyVisited.length; i++)
    	 		if(indexsAlreadyVisited[i] == index)
    	 			return false;
    	 	return true;
     }
     
     //prints the set of the index passed
     public void printSet(int index, String[] word) {
    	 	for(int i=0; i<S.length; i++)
    	 		if(find_c(i) == index)
    	 			System.out.print(word[i] + " ");
    	 	System.out.println();
     }
}
/*
Name: Daniel Villa
Class: CS 2302
Instructor: Dr. Olac Fuentes
Lab: 07
Purpose: In this lab we have to solve the river problem using an adjacency list. Then we have to create an MST using the words and their similarities.
TA: Zakia Al Kadri
Last Modified: 11/30/17
*/

public class RiverProblem {
	
	//takes a prev array and the number whose path the user wants to find. In the river problem,
	//the goal is to reach <1111> or 15.
	public static void printPath(int[] prev, int v) {
		if(prev[v] != -1) {
			printPath(prev,prev[v]);
			System.out.print("-");
		}
		System.out.print(v);
	}
	
	//creates the adjacency list we use for the breadth search and depth first search
	public static graphNode[] createGraph() {
		graphNode[] graph = new graphNode[16];
		
		graph[0] = new graphNode(1, new graphNode(5, null));
		graph[1] = new graphNode(0, null);
		graph[2] = new graphNode(7, new graphNode(11, null));
		graph[3] = null; //illegal, fox eats chicken
		graph[4] = new graphNode(5, new graphNode(7, new graphNode(13, null)));
		graph[5] = new graphNode(0, new graphNode(4, null));
		graph[6] = null; //illegal, chicken eats grain
		graph[7] = new graphNode(2, new graphNode(4, null));
		graph[8] = new graphNode(11, new graphNode(13, null));
		graph[9] = null; //illegal, chicken eats grain
		graph[10] = new graphNode(11, new graphNode(15, null));
		graph[11] = new graphNode(2, new graphNode(8, new graphNode(10, null)));
		graph[12] = null; //illegal, fox eats chicken
		graph[13] = new graphNode(4, new graphNode(8, null));
		graph[14] = new graphNode(15, null);
		graph[15] = new graphNode(10, new graphNode(14, null));
		
		return graph;
	}
	
	//This method is the breadth search function. Returns the prev array once all the paths are found
	//Source is <0000>, or zero as it is our starting point
	public static int[] breadth_first_search(graphNode[] graph) {
		boolean[] visited = new boolean[graph.length];
		int[] prev = new int[graph.length];
		for(int i=0; i<graph.length; i++) {
			visited[i] = false;
			prev[i] = -1;
		}
		Queue Q = new Queue();
		Q.enqueue(0);
		visited[0] = true;
		while(!Q.isEmpty()) {
			int u = Q.dequeue();
			for(graphNode i=graph[u]; i!=null; i=i.next) {
				if(!visited[i.dest]) {
					visited[i.dest] = true;
					prev[i.dest] = u;
					Q.enqueue(i.dest);
				}
			}
		}
		return prev;
	}
	
	
	public static int[] depth_first_search(graphNode[] graph, int source, boolean[] visited, int[] prev) {
		visited[source] = true;
		for(graphNode i=graph[source]; i!=null; i=i.next) {
			if(!visited[i.dest]) {
				prev[i.dest] = source;
				depth_first_search(graph, i.dest, visited, prev);
			}
		}
		return prev;
	}
	
	
	public static void main(String[] args) {
		graphNode[] g = createGraph();
		int[] prev0 = breadth_first_search(g);
		
		//Variables for the depth first search
		boolean[] visited = new boolean[g.length];
		int[] prev1 = new int[g.length];
		for(int i=0; i<g.length; i++) {
			visited[i] = false;
			prev1[i] = -1;
		}
		prev1 = depth_first_search(g, 0, visited, prev1);
		
		//Print the paths for both searches
		System.out.println("Breadth First Search: ");
		printPath(prev0, 15);
		System.out.println("\n\nDepth First Search: ");
		printPath(prev1, 15);
	}
}

import java.util.LinkedList;
import java.util.Random; 


public class main {

	public static Graph primMST(Graph g) {
		// Creating default heap with all weights infinity, O(n)
		Heap h = new Heap(g.getNumOfVerticals());
		
		// The parent of the vertex
		int[] parent = new int[g.getNumOfVerticals()];
		// The weight to get to the vertex
		int[] key = new int[g.getNumOfVerticals()];
		// Is the vertex was listed in MST 
		boolean[] isInMST= new boolean[g.getNumOfVerticals()];
		
		// initiating the arrays with default values
		for (Vertex v : g.getVerticals()) {
			key[v.getID()] = Integer.MAX_VALUE;
			parent[v.getID()] = -1;
			isInMST[v.getID()] = false;
		}
		
		// The first vertex is the one we start from
		h.updateWeight(0, 0);
		key[0] = 0;
		
		// going over all the verticals
		while (!h.isEmpty()) {
			// finding vertex and marking it as in the mst
			int[] u = h.extractMin();
			isInMST[u[0]] = true;
			// going over all it's neighbors 
			for (Neighbor v : g.getVertex(u[0]).getNeighbors()) {
				// if we found a way to a node that is not it the MST yet and it's weight is sorter then other ways to this node we may found
				if (!isInMST[v.getDestination().getID()] && v.getWeight() < key[v.getDestination().getID()]) {
					// updating the heap, the parent and the key of the node we just found
					parent[v.getDestination().getID()] = u[0]; 
					key[v.getDestination().getID()] = v.getWeight(); 
					h.updateWeight(v.getDestination().getID(), v.getWeight()); 
				}
			}
		}
		
		// creating the MST graph by the parameters we found
		Graph mst = new Graph(g.getNumOfVerticals());
		
		for (int i = 0; i < g.getNumOfVerticals(); i++) {
			if (parent[i] != -1) {
				mst.addEdge(parent[i], i, key[i]);
			}
		}
		return mst;
	}
	
	public static Graph addEdgeToMST(Graph mst, Vertex v, Vertex u, int weight) {
		
		// finding the path between v and u using BFS
		int[][] bfsResult = BFS(mst, v);
		int[] parent = bfsResult[1];
		int[] key = bfsResult[0];

		
		// Finding the edge with the max weight in the path from v to u.
		int currVertex = u.getID();		
		int maxVertex1 = -1;
		int maxVertex2 = -1;
		int maxWeight = -1; 
		while (key[currVertex] != 0) {
			int parentVertex = parent[currVertex];
			for (Neighbor n : mst.getVertex(parentVertex).getNeighbors()) {
				if (n.getDestination().getID() == currVertex) {
					if (n.getWeight() > maxWeight) {
						maxWeight = n.getWeight();
						maxVertex1 = currVertex;
						maxVertex2 = parentVertex;
					}
				}
			}
			currVertex = parentVertex;
		}
		
		// if the new weight is smaller, we need to change the mst
		if (weight < maxWeight) {
			mst.removeEdge(maxVertex1, maxVertex2);
			mst.addEdge(v.getID(), u.getID(), weight);
		}

		return mst;
	}
	
	public static int[][] BFS(Graph g, Vertex start) {
		// The parent of the vertex
		int[] parent = new int[g.getNumOfVerticals()];
		// The distance to get to this vertex
		int[] key = new int[g.getNumOfVerticals()];
		// marking if a vertex was visited
		boolean[] visited = new boolean[g.getNumOfVerticals()];
		
		// initiating the arrays
		for (Vertex u : g.getVerticals()) {
			visited[u.getID()] = false;
			key[u.getID()] = Integer.MAX_VALUE;
			parent[u.getID()] = -1;
		}
		LinkedList<Integer> q = new LinkedList<Integer>();
		
		// starting from start vertex
		key[start.getID()] = 0;
		visited[start.getID()] = true;
		q.add(start.getID());
		
		while (!q.isEmpty()) {
			Vertex u = g.getVertex(q.poll());
			for (Neighbor v : u.getNeighbors()) {
				if (!visited[v.getDestination().getID()]) {
					visited[v.getDestination().getID()] = true;
					key[v.getDestination().getID()] = key[u.getID()] + 1;
					parent[v.getDestination().getID()] = u.getID();
					q.add(v.getDestination().getID());
				}
			}
		}
		int[][] result = new int[2][];
		result[0] = key;
		result[1] = parent;
		
		return result;
	}
	
	public static void main(String[] args) { 
        Random rand = new Random();

		Graph g = new Graph(20);
		
		// This is for making sure we don't create more then one edge to the same vertex
		boolean[][] isThereEdge = new boolean[g.getNumOfVerticals()][g.getNumOfVerticals()];
		for (int i = 0; i < g.getNumOfVerticals(); i++) {
			for (int j = 0; j < g.getNumOfVerticals(); j++) {				
				isThereEdge[i][j] = false;
			}
		}
		
		// creating the edges
		int minNumOfEdges = 50;
		while (minNumOfEdges > 0) {
			int v1 = rand.nextInt(g.getNumOfVerticals());
			int v2 = rand.nextInt(g.getNumOfVerticals());
			if (!isThereEdge[v1][v2] && v1 != v2) {
				g.addEdge(v1, v2, rand.nextInt(1000) + 1);
				isThereEdge[v1][v2] = true;
				isThereEdge[v2][v1] = true;
				minNumOfEdges--;
			}
		}
		
		// making sure our graph is linked
		for (Vertex v : g.getVerticals()) {
			if (v.getNeighbors().isEmpty()) {
				int u = rand.nextInt(g.getNumOfVerticals());
				while (u == v.getID()) {
					u = rand.nextInt(g.getNumOfVerticals());
				}
				g.addEdge(u, v.getID(), rand.nextInt(1000) + 1);
				isThereEdge[u][v.getID()] = true;
				isThereEdge[v.getID()][u] = true;
			}
		}
		
		System.out.println("==========================================================");
		System.out.println("original graph:");
		System.out.println("---------------");
		g.printMe();
		
		System.out.println("==========================================================");
		
		// Creating mst graph of our random graph
		Graph mst = primMST(g);
		
		System.out.println("prim MST graph:");
		System.out.println("---------------");
		mst.printMe();
		
		System.out.println("==========================================================");
		
		// Creating one more edge that will not change the mst
		int v1 = rand.nextInt(g.getNumOfVerticals());
		int v2 = rand.nextInt(g.getNumOfVerticals());
		while (isThereEdge[v1][v2] || v1 == v2) {
			v1 = rand.nextInt(g.getNumOfVerticals());
			v2 = rand.nextInt(g.getNumOfVerticals());
		}
		int weight = 10000;
		g.addEdge(v1, v2, weight);
		isThereEdge[v1][v2] = true;
		isThereEdge[v2][v1] = true;
		
		System.out.println("adding edge that will not change the mst");
		System.out.printf("adding edge between %s, %s with weight %s%n", v1, v2, weight);

		Graph sameMST = addEdgeToMST(mst, g.getVertex(v1), g.getVertex(v2), weight);
		
		System.out.println("new prim MST graph (same):");
		System.out.println("--------------------------");
		sameMST.printMe();
		
		System.out.println("==========================================================");
		
		// Creating one more edge that will change the mst
		v1 = rand.nextInt(g.getNumOfVerticals());
		v2 = rand.nextInt(g.getNumOfVerticals());
		while (isThereEdge[v1][v2] || v1 == v2) {
			v1 = rand.nextInt(g.getNumOfVerticals());
			v2 = rand.nextInt(g.getNumOfVerticals());
		}
		weight = 0;
		g.addEdge(v1, v2, weight);
		isThereEdge[v1][v2] = true;
		isThereEdge[v2][v1] = true;
		
		System.out.println("adding edge that will change the mst");
		System.out.printf("adding edge between %s, %s with weight %s%n", v1, v2, weight);
		
		Graph newMST = addEdgeToMST(mst, g.getVertex(v1), g.getVertex(v2), weight);
		
		System.out.println("new prim MST graph:");
		System.out.println("-------------------");
		newMST.printMe();
	}
}

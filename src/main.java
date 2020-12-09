
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
		
		// creating the MST graph by the parameters we got
		Graph mst = new Graph(g.getNumOfVerticals());
		
		for (int i = 0; i < g.getNumOfVerticals(); i++) {
			if (parent[i] != -1) {
				mst.addEdge(parent[i], i, key[i]);
			}
		}
		return mst;
	}
	
	public static void main(String[] args) {
		Graph g = new Graph(12);
		
		g.addEdge(0, 2, 23);
		g.addEdge(0, 3, 5);
		g.addEdge(0, 1, 12);
		g.addEdge(1, 5, 7);
		g.addEdge(2, 3, 18);
		g.addEdge(2, 4, 17);
		g.addEdge(3, 5, 10);
		g.addEdge(3, 6, 9);
		g.addEdge(4, 8, 16);
		g.addEdge(4, 9, 14);
		g.addEdge(5, 11, 20);
		g.addEdge(6, 7, 4);
		g.addEdge(6, 9, 3);
		g.addEdge(7, 11, 8);
		g.addEdge(8, 10, 7);
		g.addEdge(10, 11, 12);
		
		System.out.println("original graph:");
		for (Vertex v : g.getVerticals()) {
			System.out.println(v.toString());
		}
		
		Graph mst = primMST(g);
		System.out.println("prim MST graph:");
		for (Vertex v : mst.getVerticals()) {
			System.out.println(v.toString());
		}
	}
}

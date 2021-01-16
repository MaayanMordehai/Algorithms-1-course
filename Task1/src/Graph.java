
public class Graph {
	
	// The verticals in the graph
	private Vertex[] verticals;
	
	// number of verticals
	private int numOfVerticals;
	
	/**
	 * Constructor, building graph with the given number of verticals
	 * @param numVerticals
	 */
	public Graph(int numVerticals) {
		numOfVerticals = numVerticals;
		verticals = new Vertex[numVerticals];
		for (int i = 0; i<numOfVerticals; i++) {
			verticals[i] = new Vertex(i);
		}
	}
	
	/**
	 * Getter of the verticals in the graph
	 * @return the verticals in the graph
	 */
	public Vertex[] getVerticals() {
		return verticals;
	}
	
	/**
	 * Getter of the vertex
	 * @param id the id of the vertex
	 * @return the vertex
	 */
	public Vertex getVertex(int id) {
		if (id >= numOfVerticals) {
			return null;
		}
		return verticals[id];
	}
	
	/**
	 * Adding edge between 2 verticals in the graph.
	 * @param vId id of first vertex
	 * @param uId id of second vertex
	 * @param weight the weight of the edge between them
	 */
	public void addEdge(int vId, int uId, int weight) {
		Vertex v = getVertex(vId);
		Vertex u = getVertex(uId);
		if (v != null && u != null) {
			v.addNeighbor(u, weight);
			u.addNeighbor(v, weight);
		}
	}
	
	/**
	 * Removing edge between 2 verticals in the graph
	 * @param vId id of first vertex
	 * @param uId id of second vertex
	 */
	public void removeEdge(int vId, int uId) {
		Vertex v = getVertex(vId);
		Vertex u = getVertex(uId);
		if (v != null && u != null) {
			v.removeNeighbor(u);
			u.removeNeighbor(v);
		}
	}
	
	/**
	 * Getter of number of verticals
	 * @return number of verticals in the graph
	 */
	public int getNumOfVerticals() {
		return numOfVerticals;
	}
	
	/**
	 * Printing the graph
	 */
	public void printMe() {
		for (Vertex v : getVerticals()) {
			System.out.println(v);
		}
	}
}

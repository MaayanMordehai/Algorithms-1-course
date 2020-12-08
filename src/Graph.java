
public class Graph {
	private Vertex[] verticals;
	private int numOfVerticals;
	
	public Graph(int numVerticals) {
		numOfVerticals = numVerticals;
		verticals = new Vertex[numVerticals];
		for (int i = 0; i<numOfVerticals; i++) {
			verticals[i] = new Vertex(i);
		}
	}
	
	public Vertex[] getVerticals() {
		return verticals;
	}
	
	public Vertex getVertex(int id) {
		if (id >= numOfVerticals) {
			return null;
		}
		return verticals[id];
	}
	
	public void addEdge(int vId, int uId, int weight) {
		Vertex v = getVertex(vId);
		Vertex u = getVertex(uId);
		if (v != null && u != null) {
			v.addNeighbor(u, weight);
			u.addNeighbor(v, weight);
		}
	}
	
	public int getNumOfVerticals() {
		return numOfVerticals;
	}
}

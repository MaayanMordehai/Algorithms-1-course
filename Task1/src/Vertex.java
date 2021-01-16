import java.util.LinkedList;

public class Vertex {
	
	// vertex id
	private int id;
	// all the vertex neighbors in linked list
	private LinkedList<Neighbor> neighbors;
	
	/**
	 * Constructor
	 * @param idInput the vertex id
	 */
	public Vertex(int idInput) {
		id = idInput;
		neighbors = new LinkedList<Neighbor>();
	}
	
	/**
	 * Getter for the vertex id
	 * @return the vertex id
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * Add a neighbor to a vertex
	 * @param neighbor the neighbor to add
	 * @param weight the weight of the edge to the neighbor
	 */
	public void addNeighbor(Vertex neighbor, Integer weight) {
		neighbors.add(new Neighbor(neighbor, weight));
	}
	
	/**
	 * Remove a neighbor 
	 * @param neighbor the neighbor to remove
	 */
	public void removeNeighbor(Vertex neighbor) {
		neighbors.removeIf(n -> n.getDestination().getID() == neighbor.getID());
	}
	
	/**
	 * Getter for the vertex neighbors
	 * @return the vertex neighbors
	 */
	public LinkedList<Neighbor> getNeighbors(){
		return neighbors;
	}
	
	/**
	 * Setting the vertex string to be readable, with details on the neighbors 
	 */
	public String toString() {
		String printMe = String.format("id %s, adjacent: [", id);
		for (Neighbor n : neighbors) {
				printMe = String.format("%s id: %s weight: %s,", printMe, n.getDestination().getID(), n.getWeight());
		}
		printMe = printMe.substring(0, printMe.length()-1);
		printMe = String.format("%s]", printMe);
		return printMe;
	}
}

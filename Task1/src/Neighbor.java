
public class Neighbor {
	
	// weight of the edge to this neighbor
	private int weight;
	
	// the destination vertex
	private Vertex destination;
	
	/**
	 * Constructor
	 * @param dest the destination vertex
	 * @param weightInput the weight of the edge to the dest vertex
	 */
	public Neighbor(Vertex dest, int weightInput) {
		weight = weightInput;
		destination = dest;
	}

	/**
	 * Getter for weight
	 * @return weight
	 */
	public int getWeight() {
		return weight;
	}
	
	/**
	 * Getter for destination
	 * @return the destination vertex
	 */
	public Vertex getDestination() {
		return destination;
	}
}

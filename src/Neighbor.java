
public class Neighbor {
	private int weight;
	private Vertex destination;
	
	public Neighbor(Vertex dest, int weightInput) {
		weight = weightInput;
		destination = dest;
	}

	public int getWeight() {
		return weight;
	}
	
	public Vertex getDestination() {
		return destination;
	}
}

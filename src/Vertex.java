import java.util.LinkedList;

public class Vertex {
	
	private int id;
	private LinkedList<Neighbor> adjacent;
	
	public Vertex(int idInput) {
		id = idInput;
		adjacent = new LinkedList<Neighbor>();
	}
	
	public int getID() {
		return id;
	}
	
	public void addNeighbor(Vertex neighbor, Integer weight) {
		adjacent.add(new Neighbor(neighbor, weight));
	}
	
	public LinkedList<Neighbor> getNeighbors(){
		return adjacent;
	}
	
	public String toString() {
		String printMe = String.format("id %s, adjacent: [", id);
		
		for (Neighbor n : adjacent) {
				printMe = String.format("%s id: %s weight: %s,", printMe, n.getDestination().getID(), n.getWeight());
		}
		printMe = printMe.substring(0, printMe.length()-1);
		printMe = String.format("%s]", printMe);
		return printMe;
	}
}

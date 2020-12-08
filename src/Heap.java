
public class Heap {
	
	// The weights to get to the vertex mapped by verticals id
	private int[][] weights;
	
	// The positions to keep track of which vertex is on which position in weight
	private int[] positions;
	
	// The heap size
	private int size;
	
	/**
	 * Constructor - building a default heap 
	 */
	public Heap(int numVertex) {
		size = numVertex;
		weights = new int[size][2];
		positions = new int[size];
		for (int i = 0; i < size; i++) {
			weights[i][0] = i;
			weights[i][1] = Integer.MAX_VALUE;
			positions[i] = i;
		}
	}
	
	
	public int[] minimum(){
		return weights[0];
	}
	
	/**
	 * This is O(log(n))
	 * @return the minimum we just removed
	 */
	public int[] extractMin(){
		int[] minWeight = minimum();
		int[] lastWeight = weights[size - 1];
		// replace root weight with last weight
		weights[0] = lastWeight;
		weights[size - 1] = minWeight;
		// replace positions
		positions[minWeight[0]] = size-1;
		positions[lastWeight[0]] =  0;
		// update size
		size--;
		// reorder heap
		heapify(0);
		return minWeight;
	}
	
	/**
	 * This function is updating weight for vertex
	 * @param vertexId the vertex to update the weight of
	 * @param weight the weight to update to
	 */
	public void updateWeight(int vertexId, int weight) {
		// Getting the position of the weight to update
		int index = positions[vertexId];
		// Setting the vertex to the new weight
		weights[index][1] = weight;
		
		int parent = (index-1)/2;
		// This loop is O(log(n)) because it goes up to the father each time
		while (index > 0 && weights[index][1] < weights[parent][1]) {
			int[] currentWeight = weights[index];
			int[] parentWeight = weights[parent];
			
			// Swapping the weights
			weights[index] = parentWeight;
			weights[parent] = currentWeight;
						
			// Swapping the positions
			positions[currentWeight[0]] = parent;
			positions[parentWeight[0]] = index;
			
			// moving to the father index
			index = parent;
			parent = (parent - 1) / 2;
		}
	}
	
	private void heapify(int index) {

		int indexLeft = index*2 + 1;
		int indexRight = index*2 + 2;
		
		int minIndex = index;
		
		if (indexLeft < size && weights[minIndex][1] > weights[indexLeft][1]) {
			minIndex = indexLeft;
		}
		
		if (indexRight < size && weights[minIndex][1] > weights[indexRight][1]) {
			minIndex = indexRight;
		}

		if (minIndex != index) {
			int[] minWeight = weights[minIndex];
			int[] otherWeight = weights[index];
			
			// swapping the weights to be minWeight -> index, otherWeight -> minIndex
			weights[minIndex] = otherWeight;
			weights[index] = minWeight;
			
			// swapping the positions of the indexes in the positions vector 
			positions[minWeight[0]] = index;
			positions[otherWeight[0]] = minIndex;
			
			heapify(minIndex);
		}
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
}

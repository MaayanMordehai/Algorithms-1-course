
import java.util.Arrays;
import java.util.Random;

public class DynamicPrograming {
	
    public static int BoxTower(int[] length, int[] width, int[] height) {
    	Box[] boxes = new Box[length.length];
	    for (int i=0; i<length.length; i++) {
		    boxes[i] = new Box(height[i], width[i], length[i]);
    	}
	
	    // sorting according to base from higher base to lower
	    // because if base a > base b
	    // box a can't be on top of b (but if b can or can't be on a is unknown)
	    // so after sort we only need to check if the right can be on top of the left				
	    Arrays.sort(boxes, (Box a, Box b) -> b.getWidth()*b.getLength() - a.getWidth()*a.getLength());
	
	    // maxHeight[i] -> the max height when i is in the top
	    int[] maxHeight = new int[boxes.length];
	    // tower[i] -> the index of the box that the i is above
	    int[] tower = new int[boxes.length];
	    // initiating
	    for (int i=0; i<boxes.length; i++) {
	    	// initiate with it's own height because if noting can be below it its his height
	    	maxHeight[i] = boxes[i].getHeight();
	    	tower[i] = -1;
	    }
	    
	    for (int i = 0; i < boxes.length; i++) {
	    	for (int j = 0; j < boxes.length; j++) {
	    		if (boxes[i].getWidth() < boxes[j].getWidth() && 
	    				boxes[i].getLength() < boxes[j].getLength() &&
	    				maxHeight[i] < maxHeight[j] + boxes[i].getHeight() ) {
	    			maxHeight[i] = maxHeight[j] + boxes[i].getHeight();			
	    			tower[i] = j;
	    		}
	    	}
	    }
	    // get the top box index and the maximum height
	    int max = -1;
	    int index = -1;
	    for (int i = 0; i < maxHeight.length; i++) {
	    	if (max < maxHeight[i]) {
	    		max = maxHeight[i];
	    		index = i;
	    	}
	    }
    
	    // print the tower from top to bottom
	    int next = index;
	    while (next != -1) {
	    	System.out.println(boxes[next]);
	    	next = tower[next];
	    }
	
	    return max;
    }	

    public static void main(String[] args) { 
    	Random rand = new Random();

    	int[] length = new int[20];
    	int[] width = new int[20];
    	int[] height = new int[20];
   
    	for (int i = 0; i<20; i++) {
    		length[i] = rand.nextInt(200);
    		width[i] = rand.nextInt(200);
    		height[i] = rand.nextInt(200);
    	}
    	
    	System.out.printf("max tower height %s%n", BoxTower(length, width, height));
    	
    	length = new int[30];
    	width = new int[30];
    	height = new int[30];
   
    	for (int i = 0; i<30; i++) {
    		length[i] = rand.nextInt(200);
    		width[i] = rand.nextInt(200);
    		height[i] = rand.nextInt(200);
    	}
    	
    	System.out.printf("max tower height %s%n", BoxTower(length, width, height));
    }
}

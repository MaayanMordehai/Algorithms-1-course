

public class Box {
	
	private int length;
	private int width;
	private int height;
		
	public Box (int h, int w, int l){
		length = l;
		height = h;
		width = w;
	}
	
	public int getLength() {
		return this.length;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public String toString() {
		return String.format("box length: %s, box width: %s, box height: %s", length, width, height);
	}
}

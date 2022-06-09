package Number_Maze;

public class Path_Finding {
	private int x;
	private int y;
	private Path_Finding [] prev;
	
	public Path_Finding(int x, int y, Path_Finding[] prev) {
		super();
		this.x = x;
		this.y = y;
		this.prev = prev;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Path_Finding[] getPrev() {
		return prev;
	}

	public void setPrev(Path_Finding[] prev) {
		this.prev = prev;
	}
	
	
	

}

    package Number_Maze;

public class Number {	
	    private int number;
	    private int x;
	    private int y;
	    private String way = "unassigned";
	    private boolean deleted = false;
	    public Number(int number, int x, int y) {
	        this.number = number;
	        this.x = x;
	        this.y = y;
	    }

	    public int getNumber() {
	        return number;
	    }
	    public void setNumber(int number) {
	        this.number = number;
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

		public String getWay() {
			return way;
		}

		public void setWay(String way) {
			this.way = way;
		}

		public boolean isDeleted() {
			return deleted;
		}

		public void setDeleted(boolean deleted) {
			this.deleted = deleted;
		}


}

    

package assignment2;


public class Position {
	private int xCoord;
	private int yCoord;

	public Position(int x, int y) {
		this.xCoord = x;
		this.yCoord = y;
	}

	public Position(Position p) {
		this.xCoord = p.xCoord;
		this.yCoord = p.yCoord;
	}

	public static int getDistance(Position p1, Position p2) {
		return Math.abs(p1.xCoord - p2.xCoord) + Math.abs(p1.yCoord - p2.yCoord);
	}
	
	public int getX() {
		return this.xCoord;
	}
	public int getY() {
		return this.yCoord;
	}

	public boolean equals(Object obj) {
		if (! (obj instanceof Position))
			return false;
		
		Position p = (Position) obj;
		return this.xCoord == p.xCoord && this.yCoord == p.yCoord;
	}

	public String toString() {
		return "("+this.xCoord+","+this.yCoord+")";
	}
}

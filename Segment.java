package assignment2;
import java.awt.Color;

public class Segment {
	
	private Position position;
	private Color color;

	public Segment(Position p, Color c) {
		this.position = p;
		this.color = c;
	}

	public Position getPosition() {
		return this.position ;
	}
	
	public Color getColor() {
		return this.color ;
	}

	public void setPosition(Position p ) {
		this.position = p ;
	}

	public void setColor (Color c) {
		this.color = c ;
	}

	public String toString() {
		String coloredPosition = GameColors.colorToANSIColor(this.color)
					+ this.position.toString()
					+ GameColors.colorToANSIColor(Color.WHITE) ;
		return coloredPosition;
	}
}

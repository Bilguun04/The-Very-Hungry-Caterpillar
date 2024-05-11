package assignment2;

import java.awt.Color;

public class GameColors {	
	
	public final static Color GREEN = new Color(76, 153, 0);
	public final static Color RED = new Color(204, 0, 0);
	public final static Color BLUE = new Color(0, 102, 204);
	public final static Color YELLOW = new Color(252, 209, 42); 
	public final static Color ORANGE = new Color(255,159,0); // or (242, 140, 40) or (255,140,0);
	
	/* colorblind friendly mode
	public final static Color GREEN = new Color(0, 77, 64);
	public final static Color RED = new Color(216, 27, 96);
	public final static Color BLUE = new Color(30, 136, 229);
	public final static Color YELLOW = new Color(255, 193, 7);
	*/
	
	public final static Color[] SEGMENT_COLORS = {RED, GREEN, BLUE, YELLOW, ORANGE}; 
	
	public static final String ANSI_RESET = "\u001B[0m";

	public static String colorToString(Color c) {
		if (c == BLUE)
			return "BLUE";
		else if (c == GREEN)
			return "GREEN";
		else if (c == YELLOW)
			return "YELLOW";
		else if (c == RED)
			return "RED";	
		else if (c == ORANGE)
			return "ORANGE";

		return "";
	}

	public static String colorToANSIColor(Color c) {
		switch(colorToString(c)) {
		case "BLUE":
			return "\u001B[34m";
		case "GREEN":
			return "\u001B[32m";
		case "YELLOW":
			return "\u001B[33m";
		case "RED":
			return "\u001B[31m";
		case "ORANGE":
			return "\u001B[38;5;208m";
		default:
			return ANSI_RESET;
		}
	}

}

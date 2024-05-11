package assignment2;

import java.util.Iterator;
import java.util.Random;
import java.awt.Color;

import assignment2.food.*;


public class Caterpillar extends MySinglyLinkedList<Segment> {

	public EvolutionStage stage;
	public MyStack<Position> positionsPreviouslyOccupied;
	public int goal;
	public int turnsNeededToDigest;

	public static Random randNumGenerator = new Random(1);

	// Creates a Caterpillar with one Segment.  It is up to students to decide how to implement this. 
	public Caterpillar(Position p, Color c, int goal) {
		/*
		 * ADD CODE HERE
		 */
	}

	public EvolutionStage getEvolutionStage() {
		return this.stage;
	}

	public Position getHeadPosition() {
		return ((Segment)this.head.element).getPosition() ;
	}

	// returns the color of the segment in position p. Returns null if such segment does not exist
	public Color getSegmentColor(Position p) {
		/*
		 * ADD CODE HERE
		 */
	}



	// shift all segments to the previous position while maintaining the old color
	public void move(Position p) {
		/*
		 * ADD CODE HERE
		 */
	}
	
	// a segment of the fruit's color is added at the end
	public void eat(Fruit f) {
		/*
		 * ADD CODE HERE
		 */
	}

 
	// the caterpillar moves one step backwards because of sourness
	public void eat(Pickle p) {
		/*
		 * ADD CODE HERE
		 */
	}

	// all the caterpillar's colors are shuffled around
	public void eat(Lollipop lolly) {
		/*
		 * ADD CODE HERE
		 */
	}

	// brain freeze!!
	// It reverses and its (new) head turns blue
	public void eat(IceCream gelato) {
		/*
		 * ADD CODE HERE
		 */		
	}
 
	// the caterpillar embodies a slide of Swiss cheese loosing half of its segments. 
	public void eat(SwissCheese cheese) {
		/*		 
		 * ADD CODE HERE
		 */
	}

	// A big growing stage begins
	public void eat(Cake cake) {
		/*
		 * ADD CODE HERE
		 */
	}

 	public String toString() {

 		String gus = "Gus: " ;
 		Iterator i = this.iterator() ;

 		while ( i.hasNext() ) {
			Segment s = (Segment) i.next() ;
			gus = s.toString() + gus ;
 		}
		return gus;
 	}
}

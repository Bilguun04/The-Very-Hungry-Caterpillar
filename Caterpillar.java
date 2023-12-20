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
		stage = EvolutionStage.FEEDING_STAGE;
		positionsPreviouslyOccupied = new MyStack<>();
		this.goal = goal;
		turnsNeededToDigest = 0;

		Segment newsegment = new Segment(p, c);
		this.addFirst(newsegment);
		positionsPreviouslyOccupied.push(p);
	}

	public EvolutionStage getEvolutionStage() {
		return stage;
	}

	public Position getHeadPosition() {
		return ((Segment)this.head.element).getPosition() ;
	}

	// returns the color of the segment in position p. Returns null if such segment does not exist
	public Color getSegmentColor(Position p) {
		/*
		 * ADD CODE HERE
		 */
		for (Segment segment : this) {
			Position segmentPosition = segment.getPosition();
			if (segmentPosition != null && segmentPosition.equals(p)) {
				return segment.getColor();
			}
		}
		return null;
	}


	// shift all segments to the previous position while maintaining the old color
	public void move(Position p) {
		if (this.turnsNeededToDigest > 0) {
			// Add a segment with a random color at the tail of the caterpillar
			Color[] segmentColors = GameColors.SEGMENT_COLORS;
			Random rand = Caterpillar.randNumGenerator;
			Color randomColor = segmentColors[rand.nextInt(segmentColors.length)];
			this.addLast(new Segment(this.getHeadPosition(), randomColor));

			// Decrease the number of turns left for digestion
			this.turnsNeededToDigest--;

			// Check if the digestion is fully completed
			if (this.turnsNeededToDigest == 0) {
				// Check if the caterpillar is now a butterfly
				if (this.length >= this.goal) {
					this.stage = EvolutionStage.BUTTERFLY;
				} else {
					// Resume the FEEDING STAGE
					this.stage = EvolutionStage.FEEDING_STAGE;
				}
			}
		}

		// Check if the caterpillar is in the GROWING STAGE
		if (this.stage == EvolutionStage.GROWING_STAGE) {
			// Check if the caterpillar has completed its growth
			if (this.length >= this.goal) {
				// Update the stage to BUTTERFLY
				this.stage = EvolutionStage.BUTTERFLY;
			} else {
				// Resume the FEEDING STAGE
				this.stage = EvolutionStage.FEEDING_STAGE;
			}
		}

		// Check if the input position is orthogonally connected to the head's position
		Position headPosition = this.getHeadPosition();
		if (!this.isOrthogonallyConnected(p)) {
			throw new IllegalArgumentException("The input position is out of reach.");
		}

		// Check if the input position is already occupied by the caterpillar's body
		if (this.getSegmentColor(p) != null) {
			// The caterpillar is in an ENTANGLED stage
			throw new IllegalArgumentException("Moving to an occupied position causes collision. The caterpillar is entangled!");
		}

		// Update the stack of previously occupied positions
		this.positionsPreviouslyOccupied.push(headPosition);

		// Update the body of the caterpillar accordingly
		Position currentPosition = headPosition;
		Iterator<Segment> iterator = this.iterator();

		while (iterator.hasNext()) {
			Segment segment = iterator.next();
			Position nextPosition = segment.getPosition();
			segment.setPosition(currentPosition);
			currentPosition = nextPosition;
		}

		// Update the head position to the input position
		this.head.element = new Segment(p, this.getSegmentColor(this.getHeadPosition()));
	}

	private boolean isOrthogonallyConnected(Position other) {
		// Check if two positions are orthogonally connected (adjacent)
		int dx = Math.abs(this.getHeadPosition().getX() - other.getX());
		int dy = Math.abs(this.getHeadPosition().getY() - other.getY());

		// Two positions are orthogonally connected if the difference in either x or y is 1 and the other is 0
		return (dx == 1 && dy == 0) || (dx == 0 && dy == 1);
	}


	// a segment of the fruit's color is added at the end
	public void eat(Fruit f) {
		/*
		 * ADD CODE HERE
		 */
		Color fruitColor = f.getColor();
		Position newSegmentPosition = positionsPreviouslyOccupied.peek();
		Segment newSegment = new Segment(newSegmentPosition, fruitColor);

		this.addLast(newSegment);
		positionsPreviouslyOccupied.push(newSegmentPosition);
	}


	// the caterpillar moves one step backwards because of sourness
	public void eat(Pickle p) {
		/*
		 * ADD CODE HERE
		 */
		Iterator<Segment> iterator = iterator();
		Position previousPosition = positionsPreviouslyOccupied.pop();

		while (iterator.hasNext()) {
			Segment segment = iterator.next();
			Position currentSegmentPosition = segment.getPosition();
			segment.setPosition(previousPosition);
			previousPosition = currentSegmentPosition;
		}
	}

	// all the caterpillar's colors are shuffled around
	public void eat(Lollipop lolly) {
		/*
		 * ADD CODE HERE
		 */
		Color[] colorsArray = new Color[length];
		int index = 0;
		for (Segment segment : this) {
			colorsArray[index] = segment.getColor();
            index++;
        }
		for (int i = colorsArray.length - 1; 0 < i; i--) {
			int j = randNumGenerator.nextInt(i + 1);
			Color temp = colorsArray[j];
			colorsArray[j] = colorsArray[i];
			colorsArray[i] = temp;
		}

		// Update colors in all segments
		index = 0;
		for (Segment segment : this) {
			segment.setColor(colorsArray[index]);
            index++;
        }
	}

	// brain freeze!!
	// It reverses and its (new) head turns blue
	public void eat(IceCream gelato) {
		/*
		 * ADD CODE HERE
		 */
		this.reverse();
		Position headposition = new Position(positionsPreviouslyOccupied.peek());
		Segment headSegment = new Segment(headposition, GameColors.BLUE);
		this.addFirst(headSegment);
		positionsPreviouslyOccupied.clear();
	}

	private void reverse() {
		SNode current = this.head;
		SNode prev = null;
		while (current != null){
			SNode nextnode = current.next;
			current.next = prev;
			prev = current;
			current = nextnode;
		}
		this.head = prev;
	}

	// the caterpillar embodies a slide of Swiss cheese loosing half of its segments.
	public void eat(SwissCheese cheese) {
		/*
		 * ADD CODE HERE
		 */
		if (this.length == 1){
			return;
		}
		else if (this.length == 2){
				this.removeLast();
				return;
			}

		MySinglyLinkedList<Color> colors = new MySinglyLinkedList<>();
		boolean keep = true;
		for (Segment segment : this) {
			if (keep) {
				colors.addLast(segment.getColor());
			}
			keep = !keep;
		}
		MySinglyLinkedList<Segment> remaining = new MySinglyLinkedList<>();
		for (int item = 0; item <= (this.length+1)/2; item++){
			Color curColor = colors.peekFirst();
			Position curPosition = this.head.element.getPosition();
			remaining.addLast(new Segment(curPosition, curColor));
			this.removeFirst();
			colors.removeFirst();
		}
		this.clear();
		for (Segment segment : remaining){
			this.addLast(segment);
		}
	}

	// A big growing stage begins
	public void eat(Cake cake) {
		/*
		 * ADD CODE HERE
		 */
		int energy = cake.getEnergyProvided();

		int segmentsToAdd = Math.min(energy, positionsPreviouslyOccupied.getSize());

		Color[] segmentColors = GameColors.SEGMENT_COLORS;

		for (int i = 0; i < segmentsToAdd; i++) {
			Color randomColor = segmentColors[randNumGenerator.nextInt(segmentColors.length)];
			if (this.checkinCaterpillar(positionsPreviouslyOccupied.peek())){
				turnsNeededToDigest = energy - segmentsToAdd;
				return;
			}
			this.addLast(new Segment(positionsPreviouslyOccupied.pop(), randomColor));
			if (this.getSize() == goal){
				stage = EvolutionStage.BUTTERFLY;
				return;
			}
		}

		if (segmentsToAdd == energy) {
			if (EvolutionStage.BUTTERFLY != this.stage) {
				this.stage = EvolutionStage.FEEDING_STAGE;
			}
		}
		turnsNeededToDigest = energy - segmentsToAdd;
	}

	private boolean checkinCaterpillar(Position position){
		for (Segment segment : this){
			if (position.equals(segment.getPosition())){
				return true;
			}
		}
		return false;
	}

	public String toString() {

		String gus = "Gus: ";
		Iterator i = iterator();

		while (i.hasNext()) {
			Segment s = (Segment) i.next();
			gus = s.toString() + gus;
		}
		return gus;
	}
}

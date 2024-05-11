package assignment2;
import assignment2.food.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.NoSuchElementException;

class Part1Test {

    // ==================== MY DOUBLY LINKED LIST TEST =================== //
    @Test
    @Tag("score:3")
    @DisplayName("MySinglyLinkedList add() test1")
    public void shouldAdd() {
        MySinglyLinkedList<Integer> list = new MySinglyLinkedList<>();

        list.addFirst(2);
        list.addFirst(5);
        list.addFirst(9);
        list.addFirst(0);  // {0, 9, 5, 2}

        assertEquals(4, list.getSize());
        assertEquals(0, list.peekFirst());
        assertEquals(2, list.peekLast());

        list = new MySinglyLinkedList<>();
        list.addFirst(2);
        list.addFirst(5);
        list.addLast(9);
        list.addFirst(0);  // {0, 2, 5, 9}

        assertEquals(4, list.getSize());
        assertEquals(0, list.peekFirst());
        assertEquals(9, list.peekLast());
    }

    @Test
    @Tag("score:3")
    @DisplayName("MySinglyLinkedList remove() test1")
    public void shouldRemove() {
        MySinglyLinkedList<Number> list = new MySinglyLinkedList<>();

        list.addLast(1);
        list.addLast(2);
        list.addLast(3);

        Number removedItem = list.removeFirst();  // {2, 3}

        assertEquals(1, removedItem);
        assertEquals(2, list.peekFirst());
        assertEquals(2, list.getSize());

        list = new MySinglyLinkedList<>();
        list.addFirst(1);
        list.addLast(2);
        list.addLast(3);
        list.addFirst(4);
        list.addFirst(5);  // {5, 4, 1, 2, 3}

        removedItem = list.removeLast();

        assertEquals(3, removedItem);
        assertEquals(2, list.peekLast());
        assertEquals(4, list.getSize());
    }

    @Test
    @Tag("score:2")
    @DisplayName("MySinglyLinkedList exception handling test1")
    public void shouldThrowExceptionOnEmptyList() {
        MySinglyLinkedList<Number> list = new MySinglyLinkedList<>();
        assertThrows(NoSuchElementException.class, () -> list.peekLast());
        assertThrows(NoSuchElementException.class, () -> list.peekFirst());
    }

    @Test
    @Tag("score:1")
    @DisplayName("MySinglyLinkedList peek() test1")
    public void shouldPeek() {
        MySinglyLinkedList<Number> list = new MySinglyLinkedList<>();

        list.addLast(1);
        list.addLast(2);
        list.addFirst(3);

        assertEquals(3, list.peekFirst());
        assertEquals(2, list.peekLast());
    }

    @Test
    @Tag("score:1")
    @DisplayName("MySinglyLinkedList clear() test1")
    public void shouldClear() {
        MySinglyLinkedList<Number> list = new MySinglyLinkedList<>();

        list.addLast(1);
        list.addFirst(2);
        list.addFirst(3);

        list.clear();

        Iterator i = list.iterator() ;
        assertEquals(0, list.getSize());
        assertFalse(i.hasNext());
    }

    
    // ==================== MYSTACK TEST =================== //
    @Test
    @Tag("score:1")
    @DisplayName("MyStack push() and pop() test1")
    public void shouldPush() {
        MyStack<Number> stack = new MyStack<Number>();

        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(stack.getSize(), 3);
        assertEquals(stack.peek(), 3);

        Number popped = stack.pop();

        assertEquals(3, popped);
        assertEquals(2, stack.getSize());
        assertEquals(2, stack.peek());
    }

    @Test
    @Tag("score:1")
    @DisplayName("MyStack isEmpty() test1")
    public void shouldReturnIsEmpty() {
        MyStack<Number> stack = new MyStack<Number>();
        assertTrue(stack.isEmpty());
    }

    @Test
    @Tag("score:1")
    @DisplayName("MyStack clear() test1")
    public void shouldReturnClear() {
        MyStack<Number> stack = new MyStack<Number>();

        stack.push(1);
        stack.push(2);
        stack.push(3);

        stack.clear();

        assertTrue(stack.isEmpty());
        assertEquals(0, stack.getSize());
    }
}

class Tester {
    
	private Caterpillar caterpillar;
    private Position initialPosition;
    private Color initialColor;
    private int goalLength; 

    @Test
    @Tag("score:1")
    @DisplayName("Caterpillar: testConstructorAndFeedingStage")
    void testConstructorAndFeedingStage() {
        setUp(); 
        assertEquals(new Position(1, 1), caterpillar.getHeadPosition(), "Caterpillar should start at the given position");
        assertEquals(GameColors.GREEN, caterpillar.getSegmentColor(new Position(1, 1)), "Head segment should be green");
        assertEquals(goalLength, caterpillar.goal, "Goal should match");
        assertEquals(EvolutionStage.FEEDING_STAGE, caterpillar.getEvolutionStage(), "Caterpillar should start in the feeding stage");
        assertEquals(1, caterpillar.getSize(), "Caterpillar should start with one segment");
    }

    @Test
    @Tag("score:2")
    @DisplayName("Caterpillar: testGetSegmentColorAtHead")    
    void testGetSegmentColorAtHead() {
        assertEquals(GameColors.GREEN, caterpillar.getSegmentColor(new Position(1, 1)), "Should get the color of the head segment");
    }

    @Test
    @Tag("score:2")
    @DisplayName("Caterpillar: testGetSegmentColorNoSegment")    
    void testGetSegmentColorNoSegment() {
        assertNull(caterpillar.getSegmentColor(new Position(2, 2)), "Should return null for no segment at position");
    }

    @Test
    @Tag("score:3")
    @DisplayName("Caterpillar: testEatFruit")    
    void testEatFruit() {
        caterpillar.move(new Position(1, 2));
        Fruit fruit = new Fruit(GameColors.RED);
        caterpillar.eat(fruit);
        
        assertEquals(GameColors.RED, caterpillar.getSegmentColor(new Position(1, 1)), "The color of the new segment should match the fruit");
    }
    
    @Test
    @Tag("score:2")
    @DisplayName("Caterpillar: testEatPickleRetract")    
    public void testEatPickleRetract() {
        Pickle pickle = new Pickle();
        
        caterpillar.move(new Position(2, 1)); 
        caterpillar.eat(new Fruit(GameColors.RED));
        caterpillar.move(new Position(3, 1));
        caterpillar.eat(pickle);
        
        assertEquals(new Position(2, 1), caterpillar.getHeadPosition(), "Caterpillar head is not at the given position");
        
    }

    @Test
    @Tag("score:3")
    @DisplayName("Caterpillar: testEatPickleWithOneSegment")    
    void testEatPickleWithOneSegment() {
        caterpillar.move(new Position(1, 2));
        Pickle pickle = new Pickle();
        caterpillar.eat(pickle);
        assertEquals(1, caterpillar.getSize(), "Caterpillar should not retract when it has only one segment");
    }
   
    @Test
    @Tag("score:2")
    @DisplayName("Caterpillar: testEatLollipopWithOneSegment")    
    public void testEatLollipopWithOneSegment() {
        
        // Save the initial state
        Color headColorBefore = caterpillar.getSegmentColor(initialPosition);

        // Eat a lollipop
        Lollipop lolly = new Lollipop();
        caterpillar.eat(lolly);

        // Get the state after eating the lollipop
        Color headColorAfter = caterpillar.getSegmentColor(initialPosition);

        // Assert that the caterpillar's color did not change
        assertEquals(headColorBefore, headColorAfter, "Caterpillar color should not change after eating a lollipop with one segment");
    }

    @Test
    @Tag("score:2")
    @DisplayName("Caterpillar: testEatIceCreamAndReverse")    
    void testEatIceCreamAndReverse() {
        caterpillar.move(new Position(1, 2));
        caterpillar.eat(new Fruit(Color.RED)); 
        caterpillar.move(new Position(1, 3));
        caterpillar.eat(new IceCream());
        Position headPosition = caterpillar.getHeadPosition();
        assertEquals(GameColors.BLUE, caterpillar.getSegmentColor(headPosition), "The new head segment should turn blue after eating ice cream");
        assertEquals(new Position(1, 2), caterpillar.getHeadPosition(), "Caterpillar head is not at the given position");
    }

    // Eat Swiss cheese and verify every other segment is removed, and there is no gap in between.
    @Test
    @Tag("score:3")
    @DisplayName("Caterpillar: testSwissCheese")    
  
    void testSwissCheese() {
        Position p2 = new Position(1, 2);
        caterpillar.move(p2);
        caterpillar.eat(new Fruit(GameColors.ORANGE));
        Position p3 = new Position(1, 3);
        caterpillar.move(p3);
        caterpillar.eat(new Fruit(GameColors.BLUE));
        Position p4 = new Position(1, 4);
        caterpillar.move(p4);
        caterpillar.eat(new Fruit(GameColors.RED));
        Position p5 = new Position(1, 5);
        caterpillar.move(p5);
        caterpillar.eat(new Fruit(GameColors.YELLOW));
        caterpillar.eat(new SwissCheese());
        assertEquals(GameColors.GREEN, caterpillar.getSegmentColor(p5), "head segment should be at x=1, y=5 and be green");
        assertEquals(GameColors.BLUE, caterpillar.getSegmentColor(p4), "middle segment should be at x=1, y=4 and be blue");
        assertEquals(GameColors.YELLOW, caterpillar.getSegmentColor(p3), "tail segment should be at x=1, y=3 and be yellow");
        assertEquals(null, caterpillar.getSegmentColor(p2), "there should be no segment at x=1, y=2");
        assertEquals(null, caterpillar.getSegmentColor(initialPosition), "there should be no segment at x=1, y=1");
    }    
    
    @Test
    @Tag("score:2")
    @DisplayName("Caterpillar: testEatCakeWithSufficientEnergy")    
  
    public void testEatCakeWithSufficientEnergy() {

        caterpillar.move(new Position(1, 2));
        caterpillar.move(new Position(1, 3));
        caterpillar.move(new Position(1, 4));

        
        Cake cake = new Cake(1); 
        caterpillar.eat(cake);

        
        int expectedLength = 2; 
        assertEquals(expectedLength, caterpillar.getSize(), "Caterpillar should have grown by one segment after eating cake");

    }




    @Test
    @Tag("score:3")
    @DisplayName("Caterpillar: testMoveAndGrow")    
    void testMoveAndGrow() {
        caterpillar.move(new Position(1, 2)); // Caterpillar moves to a new position
        assertEquals(new Position(1, 2), caterpillar.getHeadPosition(), "Caterpillar head should be at the new position after moving");
    }
   


    // When eating cake, verify that the colors of the newly added segments are random
    // and vary across different calls.
    @Test
    @Tag("score:2")
    @DisplayName("Caterpillar: testEatCakeSegmentColors")    
    void testEatCakeSegmentColors() {
        Position p2 = new Position(1, 2);
        caterpillar.move(p2);
        int randomNum = ThreadLocalRandom.current().nextInt(0, GameColors.SEGMENT_COLORS.length);
        caterpillar.eat(new Fruit(GameColors.SEGMENT_COLORS[randomNum]));
        Position p3 = new Position(1, 3);
        caterpillar.move(p3);
        Position p4 = new Position(1, 4);
        caterpillar.move(p4);
        Position p5 = new Position(1, 5);
        caterpillar.move(p5);
        caterpillar.eat(new Cake(3));
        assertEquals(GameColors.BLUE, caterpillar.getSegmentColor(initialPosition), "tail segment should be blue");
        assertEquals(GameColors.YELLOW, caterpillar.getSegmentColor(p2), "4th segment should be yellow");
        assertEquals(GameColors.RED, caterpillar.getSegmentColor(p3), "3rd segment should be red");
        assertEquals(GameColors.GREEN, caterpillar.getSegmentColor(p5), "head segment should be green");
    }

    @BeforeEach
    public void setUp() {
        initialPosition = new Position(1,1); 
        initialColor = GameColors.GREEN;
        goalLength = 10; 
        caterpillar = new Caterpillar(initialPosition, initialColor, goalLength);
    } 
}

package assignment2;

public class MyStack<E> {

	private MySinglyLinkedList<E> list ;

	/* ADD YOUR CODE HERE */
	
	
	
	
	
	/* ADD YOUR CODE HERE */

	public String toString() {
		String msg = "" ;
		for ( E e : list) {
			msg = e.toString() + ","  + msg ;
		}
		return msg ;
	}
}

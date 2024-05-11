package assignment2;

import java.util.Iterator;
import java.util.NoSuchElementException ;

public class MySinglyLinkedList<E> implements Iterable<E> {

	public int length ;
	public SNode head ;
	public SNode tail ;

	public class SNode {
		protected E element ;
		protected SNode next ;
	}

	/* ADD YOUR CODE HERE */
	
	
	
	
	
	/* ADD YOUR CODE HERE */

	public SLLIterator iterator() {
		return new SLLIterator(this);
	}

	private class SLLIterator implements Iterator<E> {

		SNode cur ;

		SLLIterator(MySinglyLinkedList<E> list) {
			cur = list.head ;
		}

		public boolean hasNext() {
			return (cur != null) ;
		}

		public E next() {
			SNode tmp = cur ;
			cur = cur.next ;
			return tmp.element ;
		}
	}
}

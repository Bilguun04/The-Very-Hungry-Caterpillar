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

	public MySinglyLinkedList(){
		this.length = 0;
		this.head = null;
		this.tail = null;
	}

	public boolean addFirst(E element) {
		SNode newNode = new SNode();
		newNode.element = element;
		newNode.next = head;
		if (tail == null) {
			tail = newNode;
		}
		head = newNode;
		length++;
		return true;
	}

	public boolean addLast(E element) {
		SNode newNode = new SNode();
		newNode.element = element;
		newNode.next = null;

		if (head == null) {
			head = newNode;
			tail = newNode;
		} else {
			tail.next = newNode;
			tail = newNode;
		}

		length++;
		return true;
	}

	public E removeFirst() {
		if (length == 0) {
			throw new NoSuchElementException("List is empty");
		}
		E removedElement = head.element;
		head = head.next;
		if (head == null) {
			tail = null;
		}
		length--;
		return removedElement;
	}

	public E removeLast() {
		if (length == 0) {
			throw new NoSuchElementException("List is empty");
		}
		E removedElement;
		if (head == tail) {
			removedElement = head.element;
			head = null;
			tail = null;
		} else {
			SNode secondToLast = head;
			while (secondToLast.next != tail) {
				secondToLast = secondToLast.next;
			}
			removedElement = tail.element;
			tail = secondToLast;
			tail.next = null;
		}
		length--;
		return removedElement;
	}

	public E peekFirst() {
		if (length == 0) {
			throw new NoSuchElementException("List is empty");
		}
		return head.element;
	}

	public E peekLast() {
		if (length == 0) {
			throw new NoSuchElementException("List is empty");
		}
		return tail.element;
	}

	public void clear(){
		head = null;
		tail = null;
		length = 0;
	}

	public boolean isEmpty(){
		return length == 0;
	}

	public int getSize(){
		return this.length;
	}
	
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

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

	public boolean addFirst(E obj){
		try {
			SNode newNode = new SNode();
			newNode.element = obj;
			newNode.next = head;
			if (tail == null) {
				tail = newNode;
			}
			head = newNode;
			length++;
			return true;
		} catch (Exception e){
			return false;
		}
	}

	public boolean addLast(E element) {
		try {
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
		} catch (Exception e){
			return false;
		}
	}

	public E removeFirst(){
		try{
			E element = head.element;
			head = head.next;
			if (head == null){
				tail = null;
			}
			length--;
			return element;
		} catch (Exception e){
			throw new NoSuchElementException("List is empty!");
		}
	}

	public E removeLast() {
		try {
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
		} catch (Exception e) {
			throw new NoSuchElementException("List is empty!");
		}
	}

	public E peekFirst(){
		try {
			return head.element;
		} catch (Exception e){
			throw new NoSuchElementException("List is empty!");
		}
	}

	public E peekLast(){
		try {
			return tail.element;
		} catch (Exception e){
			throw new NoSuchElementException("List is empty!");
		}
	}

	public void clear(){
		head = null;
		tail = null;
		length = 0;
	}

	public boolean isEmpty(){
		if (length == 0){
			return true;
		} else {
			return false;
		}
	}

	public int getSize(){
		return length;
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

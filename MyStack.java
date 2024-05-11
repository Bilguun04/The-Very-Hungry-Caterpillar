package assignment2;

import java.util.NoSuchElementException;

public class MyStack<E> {

	private MySinglyLinkedList<E> list ;

	/* ADD YOUR CODE HERE */
	
	public MyStack(){
		list = new MySinglyLinkedList<>();
	}

	public boolean push(E element){
		try {
			list.addFirst(element);
			return true;
		} catch (Exception e){
			return false;
		}
	}

	public E pop(){
		try {
			return list.removeFirst();
		} catch (Exception e){
			throw new NoSuchElementException("Stack is empty!");
		}
	}

	public E peek(){
		try{
			return list.head.element;
		} catch (Exception e){
			throw new NoSuchElementException("Stack is empty!");
		}
	}

	public boolean isEmpty(){
		if (list.length == 0){
			return true;
		} else {
			return false;
		}
	}

	public void clear(){
		list.clear();
	}

	public int getSize(){
		return list.length;
	}
	
	/* ADD YOUR CODE HERE */

	public String toString() {
		String msg = "" ;
		for ( E e : list) {
			msg = e.toString() + ","  + msg ;
		}
		return msg ;
	}
}

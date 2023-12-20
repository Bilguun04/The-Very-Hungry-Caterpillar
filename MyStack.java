package assignment2;

public class MyStack<E> {

	private MySinglyLinkedList<E> list ;

	/* ADD YOUR CODE HERE */
	
	public MyStack(){
		list = new MySinglyLinkedList<>();
	}

	public boolean push(E element){
		list.addFirst(element);
		return true;
	}

	public E pop() {
		if (list.length == 0) {
			throw new java.util.NoSuchElementException("Stack is empty");
		}
		return list.removeFirst();
	}

	public E peek(){
		if (list.length == 0){
			throw new java.util.NoSuchElementException("Stack is empty");
		}
		return list.peekFirst();
	}

	public boolean isEmpty(){
		if (list.length == 0){
			return true;
		}
		return false;
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

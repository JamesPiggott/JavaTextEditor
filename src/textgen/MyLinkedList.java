package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** 
	 * Create a new empty LinkedList
	 * This implementation uses sentinel nodes
	 */
	public MyLinkedList() {
		// Initialize MyLinkedList
		this.size = 0;
		this.head = new LLNode<E>();
		this.tail = new LLNode<E>();
		this.head.next = this.tail;
		this.tail.prev = this.head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		if (element == null) {
			throw new NullPointerException("No element input");
		}
		LLNode<E> newnode = new LLNode<E>(element); // create new node
		LLNode<E> prev = tail.prev;
		prev.next = newnode;  // add node to the end of the list
		newnode.prev = prev;
		newnode.next = tail;  // new node references the sentinel node
		tail.prev = newnode;  // and vice versa
		size++;				  // increase LinkedList size
		return true;

	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// check if index exists
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		LLNode<E> getIndex = head;
		
		// index through the entire list until value == index
		for (int i = 0; i <= index; i++) {
			getIndex = getIndex.next;
		}
		E data = getIndex.data; // retrieve value at index
		return data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// check if element exists
		if (element == null) {
			throw new NullPointerException("No element input");
		}
		// check if index exists
		if ((index < 0 || index > size - 1) && (index != 0 || size != 0)) {
			throw new IndexOutOfBoundsException("Invalid index input");
		}

		// index through the entire list until value == index
		LLNode<E> getIndex = head;
		for (int i = 0; i <= index; i++) {
			getIndex = getIndex.next;
		}
		
		LLNode<E> newnode = new LLNode<E>(element); // create new nod
		// same as other method add
		LLNode<E> prev = getIndex.prev;
		prev.next = newnode;
		newnode.prev = prev;
		newnode.next = getIndex;
		getIndex.prev = newnode;
		size++;
	}


	/** Return the size of the list */
	public int size() 
	{
		return this.size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		LLNode<E> removeIndex = head;
		for (int i = 0; i <= index; i++) {
			removeIndex = removeIndex.next;
		}

		// find nodes prev and next
		LLNode<E> prev = removeIndex.prev;
		LLNode<E> next = removeIndex.next;

		// overwrite their references
		prev.next = next;
		next.prev = prev;
		
		size--; // decrease LinkedList size
		E value = removeIndex.data; // return data value
		return value;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// check if element exists
		if (element == null) {
			throw new NullPointerException("No element input");
		}
		// check if index exists
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		
		// index through the entire list until value == index
		LLNode<E> getIndex = head;
		for (int i = 0; i <= index; i++) {
			getIndex = getIndex.next;
		}
		getIndex.data = element;
		return element;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

	public LLNode() {
		this.data = null;
		this.prev = null;
		this.next = null;
	}

}

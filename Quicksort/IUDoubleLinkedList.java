import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
/**
 *
 *IUDoubleLinkedList uses multiple methods to add, remove, set, and get elements within a 
 *double linked list node based implementation.  It implements the IndexedUnsorted interface
 *to help create the methods used in this class.  The class will use test classes we created
 *to test the functionality of how the double linked list operates.  A ListIterator was created
 *to help iterate through the list provided to use by the instructor.
 *
 * @author Alex Acevedo 
 * 
 * @param <T> Used for nodes. generic type of objects in list
 */
public class IUDoubleLinkedList<T> implements IndexedUnsortedList<T>{

	private DLLNode<T> head, tail; // first/last node in linked list 
	private int count; // number of elements in list 
	private int modCount; // counts how many modifications were made

	/*
	 * Default constructor
	 */
	public IUDoubleLinkedList()
	{
		//instantiates all variables
		count = 0; 
		head = null;
		tail = null; 
		modCount = 0;
	}


	//method used to add an element to the front of the list
	@Override
	public void addToFront(T element) 
	{
		DLLNode<T> newNode = new DLLNode<T>(element);  //creates a new node with element
		newNode.setNext(head);	//sets the new node to the head of the list

		//if statement checking if the head does not equal null, if so set previous to new node
		if(head != null)
		{
			head.setPrev(newNode);
		}
		//else set the tail to the new node
		else
		{
			tail = newNode;
		}

		head = newNode;  // sets head to new node
		count++;	//increments count
		modCount++;	//increments modCount

	}

	//method use to add an element to the rear of the list
	@Override
	public void addToRear(T element) 
	{
		DLLNode<T> newNode = new DLLNode<T>(element);	//creates a new node to add
		newNode.setPrev(tail);	//sets the previous to tail

		//if tail doesn't equal to null set tails next to new node
		if(tail != null)
		{
			tail.setNext(newNode);
		}
		//else set head to the new node
		else
		{
			head = newNode;
		}

		tail = newNode;	//sets tail to the new node
		count++;	//increments count
		modCount++;	//increments modCount

	}

	//method used to add an element to the end of the list 
	@Override
	public void add(T element) 
	{
		DLLNode<T> newNode = new DLLNode<T>(element); //creates a new node with element as a parameter
		// if list is empty 
		if(count == 0)
		{
			head = newNode; // if queue is empty 
		}
		else
		{
			tail.setNext(newNode);  // can't do if empty 
			newNode.setPrev(tail); //sets the new nodes previous to the old tail if list is not empty
		}		
		tail = newNode; 	//moves tail to the new node
		modCount++; 		//increments modCount
		count++; 			//increments count

	}

	//method used to add an element to a specific target index
	@Override
	public void addAfter(T element, T target) 
	{
		ListIterator<T> listItr = listIterator(); //use the listIterator method to go through list
		boolean found = false;	//sets a boolean to false
		//while the boolean is false and the iterator has a next, set its next to value
		//and compare it to the target to determine if it is true
		while(!found && listItr.hasNext())
		{
			T value = listItr.next();
			if(value.equals(target))
			{
				found = true; //sets found to true
			}
		}
		//if boolean is false throw an exception
		if(!found)
		{
			throw new NoSuchElementException("DLL - addAfter(element, target) - Target element can't be found");
		}
		listItr.add(element); //adds the element to the list


	}

	//method used to add a node to the list at a specific index
	@Override
	public void add(int index, T element) 
	{
		//if statement that checks if the index is valid or within bounds
		if(index < 0 || index > count)
		{
			throw new IndexOutOfBoundsException("DLL - add(index, element) - Index is out of bounds");

		}

		DLLNode<T> newNode = new DLLNode<T>(element);	//creates a new node with element in it
		DLLNode<T> current = head;	//creates a new node to head
		//for loops to go through the list
		for(int i = 0; i < index; i++)
		{
			current = current.getNext();
		}
		newNode.setNext(current);  //sets the new node to current
		//if doesn't equal null, then set previous to currents previous, and currents previous from the new node
		if(current != null)
		{
			newNode.setPrev(current.getPrev());
			current.setPrev(newNode);
		}
		//else set new node previous to tail and update tail
		else
		{
			newNode.setPrev(tail);
			tail = newNode;
		}
		//if current does not equal head, then new node will set the previous's next to new node
		if(current != head)
		{
			newNode.getPrev().setNext(newNode);
		}
		//else if no nodes, set the head to new node
		else
		{
			head = newNode;
		}
		modCount++;  //increments the mod count
		count++;	//increments count
	}

	//method used to remove the first node in the list
	@Override
	public T removeFirst() 
	{
		//if list is empty, throw an exception
		if(isEmpty())
		{
			throw new NoSuchElementException("DLL - remove - List is empty");
		}

		//creates a temporary element that gets the head element
		T returnValue = head.getElement();

		//checks if there are more than one node in list, sets heads previous to null
		if(count > 1)
		{
			head.getNext().setPrev(null);
		}
		//else set the tail to null
		else
		{
			tail = null;
		}

		head = head.getNext();	 //sets head to its next
		count--;                //decrements count
		modCount++;			   //increments count
		return returnValue;   //returns temporary element
	}

	//method used to remove the last node in the list
	@Override
	public T removeLast() 
	{
		//checks if the list is empty and throws an exceptions if it is
		if(isEmpty())
		{
			throw new NoSuchElementException("DLL - removeLast - List is empty");
		}

		//creates a temporary object that stores the tail element
		T returnValue = tail.getElement();
		//checks if the list has more than one node then gets the previous node and sets its next to null
		if(count > 1)
		{
			tail.getPrev().setNext(null);
		}
		//else set if only one, set the head to null
		else
		{
			head = null;
		}
		tail = tail.getPrev();	//sets tail to its previous
		count--;				//decrements count
		modCount++;				//increments count

		return returnValue;  	//returns temporary object
	}

	//method used to remove and element in the list
	@Override
	public T remove(T element) 
	{
		//creates a new node with head
		DLLNode<T> current = head;

		//while loop to go through the list finding the element
		while(current != null && !current.getElement().equals(element))
		{
			current = current.getNext();
		}
		//checks if current is null
		if(current == null)
		{
			throw new NoSuchElementException("DLL - remove(element) - List is empty");
		}
		//if there is only one node in the list, set head and tail to null
		if(count == 1)
		{
			head = tail = null;
		}
		//if its head, then move to the next and set its previous to null
		else if(current == head)
		{
			head = head.getNext();
			head.setPrev(null);
		}
		//if its tail, set it to the previous and set next to null
		else if(current == tail)
		{
			tail = tail.getPrev();
			tail.setNext(null);
		}
		//else in the middle, remove node and connect previous to next
		else
		{
			current.getPrev().setNext(current.getNext());
			current.getNext().setPrev(current.getPrev());
		}

		count--;		//decrements count
		modCount++;		//increments mod count

		return current.getElement();  //returns currents element
	}

	//method used to remove node at specific index
	@Override
	public T remove(int index) 
	{
		//checks if index is in bounds, throws exception if not
		if(index < 0 || index >= count)
		{
			throw new IndexOutOfBoundsException("DLL - remove(index) - Index out of bounds");
		}

		ListIterator<T> listItr = listIterator(index);  //use the listIterator method to go through list to specified index
		T returnValue = listItr.next();					//store the iterators next to an object
		listItr.remove();								//uses remove method to set listItr to null

		return returnValue;		//returns the stored object
	}

	//method used to set an element in a specific indexed node
	@Override
	public void set(int index, T element) 
	{
		//checks if index is within bound, throws exception if not
		if(index < 0 || index >= count)
		{
			throw new IndexOutOfBoundsException("DLL - set(index, element) - Index out of bounds");
		}
		ListIterator<T> listItr = listIterator(index);	//use the listIterator method to go through list to specified index
		listItr.next();									//uses the next method to go through the list
		listItr.set(element);							//sets the element to the node
	}

	//method used to get a node at a specific index
	@Override
	public T get(int index) 
	{
		//checks if index is within bound, throws exception if not
		if(index < 0 || index >= count)
		{
			throw new IndexOutOfBoundsException("DLL - set(index, element) - Index out of bounds");
		}
		ListIterator<T> listItr = listIterator(index);	//use the listIterator method to go through list to specified index
		return listItr.next();						    //returns object 
	}

	//method used to check the index of an element
	@Override
	public int indexOf(T element) 
	{
		boolean found = false;  	// whether item is found 
		int index = 0;  			// index of item in list 
		//creates a new node with head
		DLLNode<T> current = head;
		
		// look for item in list 
		while(!found && index < count)
		{
			if(current.getElement() == element)
			{
				found = true; 
			}
			else
			{
				index++; 						//increments index
				current = current.getNext(); 	//sets current to its next
			}
		}
		// if element not in list 
		if(!found)
		{
			index = -1; 
		}
		return index; //returns the index value
	}

	//method used to get the object at the head of the list
	@Override
	public T first() 
	{
		//checks if the list is empty, throws exception if it is
		if(isEmpty())
		{
			throw new NoSuchElementException("DLL - first - List is empty");
		}
		return head.getElement();  	//returns the head element
	}

	//method used to get the object at the tail of the list
	@Override
	public T last() 
	{
		//checks if the list is empty, throws exception if it is
		if(isEmpty())
		{
			throw new NoSuchElementException("DLL - last - List is empty");
		}
		return tail.getElement();		//returns the head element
	}

	//method used to checks if the list contains a target
	@Override
	public boolean contains(T target) 
	{
		return (indexOf(target) != -1);  //returns target by using indexOf method
	}

	//method to check if the list is empty
	@Override
	public boolean isEmpty() 
	{
		return (count==0);
	}

	//method to return the size of the list
	@Override
	public int size() 
	{
		return count;
	}

	//method that uses DLL Iterator to iterate through the list
	@Override
	public Iterator<T> iterator() 
	{
		return new DLLIterator();
	}
	
	//method that uses DLIterator to iterate through the ListIterator
	@Override
	public ListIterator<T> listIterator() 
	{
		return new DLLIterator();
	}

	//method that uses DLIterator to iterate through the ListIterator with a starting index
	@Override
	public ListIterator<T> listIterator(int startingIndex) 
	{
		return new DLLIterator(startingIndex);
	}

	/*
	 * Iterator class that will implement a ListIterator interface.
	 * Will be used to iterate through the Double Linked List and to test
	 * the functionality of test cases.
	 * 
	 */
	private class DLLIterator implements ListIterator<T>
	{
		//encapsulated variables used throughout the iterator
		private DLLNode<T> nextNode;
		private int nextIndex ;
		private int iterModCount;
		private DLLNode<T> lastReturned;

		//iterator constructor
		public DLLIterator()
		{
			this(0);
			iterModCount = modCount;		//sets the iterator mod count to global mod count
		}

		//method use to iterate with a starting index
		public DLLIterator(int startingIndex)
		{
			//checks if starting index is within bounds, throw exception if not
			if(startingIndex < 0 || startingIndex > count)
			{
				throw new IndexOutOfBoundsException("DLL ListIterator - DLLIterator(startingindex) - Index out of bounds");
			}

			nextNode = head;	// sets next node to the head
			//for loop that goes through the list to find the index element
			for(int i = 0; i < startingIndex; i++)
			{
				nextNode = nextNode.getNext();
			}
			nextIndex = startingIndex;		//sets next index to the startingIndex
			iterModCount = modCount;		//sets iterator mod count to modcount

			lastReturned = null;			//sets the last returned node to null
		}

		//method used to check if the iterator has a next node
		@Override
		public boolean hasNext() 
		{
			//checks if both modcounts aren't equal and throws an exception if so
			if(iterModCount != modCount)
			{
				throw new ConcurrentModificationException("DLL ListIterator - DLLIterator(hasNext) - no nodes");
			}

			return (nextNode != null); 	//returns true if next node does not equal null
		}

		//method that will call next
		@Override
		public T next() 
		{
			//checks if the method hasNext is true. if false throw the exception
			if(!hasNext())
			{
				throw new NoSuchElementException("DLL ListIterator - DLLIterator(next) - no elements");
			}

			lastReturned = nextNode;			//sets the global node to the next node
			nextNode = nextNode.getNext();		//sets next node to its next
			nextIndex++;						//increments next index


			return lastReturned.getElement();	//returns the element of the last returned
		}

		//method that will call has previous
		@Override
		public boolean hasPrevious() {
			//checks if the iterator mod count does not equal the mod count, throws excpetion if true
			if(iterModCount != modCount)
			{
				throw new ConcurrentModificationException("DLL ListIterator - DLLIterator(hasPrevious) - no nodes");
			}

			return nextNode != head;		//returns true if there is a previous node

		}

		//method that will call previous
		@Override
		public T previous() 
		{
			//if it does not have a previous throw an exception
			if(!hasPrevious())
			{
				throw new NoSuchElementException("DLL ListIterator - DLLIterator(previous) - no elements");
			}
			//checks if the next node does not equal null, sets next node to its previous
			if(nextNode != null)
			{
				nextNode = nextNode.getPrev();
			}
			//else set the next node to the tail
			else
			{
				nextNode = tail;
			}
			nextIndex--;						//decrements nextIndex
			lastReturned = nextNode;			//sets lastReturned to nextNode
			return nextNode.getElement();		//returns the nextNodes element

		}

		//method that calls nextIndex
		@Override
		public int nextIndex() 
		{
			//checks if both mod counts are not equal, throw an exception if so
			if(iterModCount != modCount)
			{
				throw new ConcurrentModificationException("DLL ListIterator - DLLIterator(nextIndex) - no nodes");
			}
			return nextIndex;		//returns the indexed value
		}

		//method that calls previousIndex
		@Override
		public int previousIndex() 
		{
			//checks if both mod counts are not equal, throw an exception if so
			if(iterModCount != modCount)
			{
				throw new ConcurrentModificationException("DLL ListIterator - DLLIterator(previousIndex) - no nodes");
			}
			return nextIndex-1;		//returns the previous index
		}

		//method that calls the remove function
		@Override
		public void remove() 
		{
			//checks if both mod counts are not equal, throw an exception if so
			if(iterModCount != modCount)
			{
				throw new ConcurrentModificationException("DLL ListIterator - DLLIterator(remove) - no nodes to remove");
			}
			//checks if the list is empty
			if(lastReturned == null)
			{
				throw new IllegalStateException("DLL ListIterator - DLLIterator(lastReturned) - list is empty");
			}
			//if head is equal to tail, set both to null
			if(head == tail)
			{
				head = tail = null;
			}
			//if head is the lastReturned, then set to the next and set its previous to null
			else if(head == lastReturned)
			{
				head = head.getNext();
				head.setPrev(null);

			}
			//if tail is the lastReturned, then set tail to its previous and its next to null
			else if(lastReturned == tail)
			{
				tail = tail.getPrev();
				tail.setNext(null);
			}
			//else if in the middle set next to lastReturned and previous to it
			else
			{
				lastReturned.getPrev().setNext(lastReturned.getNext());
				lastReturned.getNext().setPrev(lastReturned.getPrev());
			}
			//if lastReturned equal to next node then set the next node to next
			if(lastReturned == nextNode)
			{
				nextNode = nextNode.getNext();
			}
			//else decrement nextIndex
			else
			{
				nextIndex--;
			}

			lastReturned = null;		//sets lastReturn to null
			modCount++;					//increment mod count
			iterModCount++;				//increment iterator mod count
			count--;					//decrement count

		}

		//method that sets an element to a node
		@Override
		public void set(T e) 
		{
			//checks if both mod counts are not equal, throw an exception if so
			if(iterModCount != modCount)
			{
				throw new ConcurrentModificationException("DLL ListIterator - DLLIterator(set) - no nodes to set");
			}
			//checks if list is empty
			if(lastReturned == null)
			{
				throw new IllegalStateException("DLL ListIterator - DLLIterator(set) - list is empty");
			}
			modCount++;						//increments mod count
			iterModCount++;					//increments interator mod count

			lastReturned.setElement(e);		//returns the element from lastReturned

		}

		//method used to add and element to the node
		@Override
		public void add(T e) 
		{
			//checks if both mod counts are not equal, throw an exception if so
			if(iterModCount != modCount)
			{
				throw new ConcurrentModificationException("DLL ListIterator - DLLIterator(add) - no nodes to add");
			}
			//creates a new node
			DLLNode<T> newNode = new DLLNode<T>(e);

			//checks if list is empty, if so head and tail is set to new node
			if(isEmpty())
			{
				head = tail = newNode;
			}

			//else check where its being added
			else
			{
				//if at the head of the list, set the new node to the head and move it to the next, update head
				if(nextNode == head)
				{
					newNode.setNext(nextNode);
					nextNode.setPrev(newNode);
					head = newNode;
				}
				//else if at the tail, set it to the end and update tail
				else if(nextNode == null)
				{
					tail.setNext(newNode);
					newNode.setPrev(tail);
					tail = newNode;
				}
				//else in the middle, set the next and previous nodes connecting to it
				else
				{
					nextNode.getPrev().setNext(newNode);
					newNode.setNext(nextNode);
					newNode.setPrev(nextNode.getPrev());
					nextNode.setPrev(newNode);
				}
			}

			lastReturned = null;		//sets last returned to null
			nextIndex++;				//increments next index
			iterModCount++;				//increments iterator mod count
			modCount++;					//increments mod count
			count++;					//increments count
		}
	}
}

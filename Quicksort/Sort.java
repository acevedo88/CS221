import java.util.Comparator;
import java.util.ListIterator;

/**
 * Sort version for students: 
 * 
 * Class for sorting lists that implement the IndexedUnsortedList interface,
 * using ordering defined by class of objects in list or a Comparator.
 * As written uses Quicksort algorithm.
 *
 * @author CS221
 */
public class Sort
{	
	/**
	 * Returns a new list that implements the IndexedUnsortedList interface. 
	 * As configured, uses WrappedDLL. Must be changed if using 
	 * your own IUDoubleLinkedList class. 
	 * 
	 * @return a new list that implements the IndexedUnsortedList interface
	 */
	private static <T> IndexedUnsortedList<T> newList() 
	{
		//TODO: replace with your IUDoubleLinkedList for extra-credit
		return new WrappedDLL<T>(); 
	}

	/**
	 * Sorts a list that implements the IndexedUnsortedList interface 
	 * using compareTo() method defined by class of objects in list.
	 * DO NOT MODIFY THIS METHOD
	 * 
	 * @param <T>
	 *            The class of elements in the list, must extend Comparable
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @see IndexedUnsortedList 
	 */
	public static <T extends Comparable<T>> void sort(IndexedUnsortedList<T> list) 
	{
		quicksort(list);
	}

	/**
	 * Sorts a list that implements the IndexedUnsortedList interface 
	 * using given Comparator.
	 * DO NOT MODIFY THIS METHOD
	 * 
	 * @param <T>
	 *            The class of elements in the list
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @param c
	 *            The Comparator used
	 * @see IndexedUnsortedList 
	 */
	public static <T> void sort(IndexedUnsortedList <T> list, Comparator<T> c) 
	{
		quicksort(list, c);
	}

	/**
	 * Quicksort algorithm to sort objects in a list 
	 * that implements the IndexedUnsortedList interface, 
	 * using compareTo() method defined by class of objects in list.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The class of elements in the list, must extend Comparable
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 */
	private static <T extends Comparable<T>> void quicksort(IndexedUnsortedList<T> list)
	{
		// TODO: Implement recursive Quicksort algorithm 

		//if statement to check if the list has one or no elements in the list
		if(list.size() <= 1)
		{
			return;
		}

		//creates a lower and upper list
		IndexedUnsortedList<T> lower = newList();
		IndexedUnsortedList<T> upper = newList();

		//creates a generic pivot that grabs the first element in the list and removes it
		T pivot = list.removeFirst();

		//integer for the list size
		int listSize = list.size();

		//for loop that uses the compareTo method for the pivot and compareValue
		for(int i = 0; i < listSize; i++)
		{
			//creates a generic elements and removes the first element
			T compareValue = list.removeFirst();

			//if and else statement used to compare and add the elements in either upper or lower
			if(compareValue.compareTo(pivot) <= 0)
			{
				lower.add(compareValue);
			}

			else
			{
				upper.add(compareValue);
			}
		}

		//quicksort the upper and lower lists
		quicksort(lower);
		quicksort(upper);

		//while loop to add to the lower list
		while(!lower.isEmpty())
		{
			list.add(lower.removeFirst());
		}

		//adds the pivot to the list
		list.add(pivot);

		//while loop to add to the upper list
		while(!upper.isEmpty())
		{
			list.add(upper.removeFirst());
		}



	}

	/**
	 * Quicksort algorithm to sort objects in a list 
	 * that implements the IndexedUnsortedList interface,
	 * using the given Comparator.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The class of elements in the list
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @param c
	 *            The Comparator used
	 */
	private static <T> void quicksort(IndexedUnsortedList<T> list, Comparator<T> c)
	{
		// TODO: Implement recursive Quicksort algorithm using Comparator

		//if statement that checks if one or no elements are in the list.
		if(list.size() <=1)
		{
			return;
		}

		//creates a lower and upper list
		IndexedUnsortedList<T> lower = newList();
		IndexedUnsortedList<T> upper = newList();

		//creates a generic pivot that grabs the first element in the list and removes it
		T pivot = list.removeFirst();

		//integer for the list size
		int listSize = list.size();

		//for loop that will go through the list and compare the values to the pivot
		for(int i = 0; i < listSize; i++)
		{
			//the element being compared to the pivot
			T compareValue = list.removeFirst();
			
			//use the comparator to compare both pivot and compareValue. Add to lower or upper respectively
			if(c.compare(compareValue, pivot) <= 0)
			{
				lower.add(compareValue);
			}

			else
			{
				upper.add(compareValue);
			}

		}

		//Quick sort the upper and lower
		quicksort(lower, c);
		quicksort(upper, c);

		//while loop to add lower elements to the list in order
		while(!lower.isEmpty())
		{
			list.add(lower.removeFirst());
		}
		
		//adds the pivot to the list
		list.add(pivot);

		//while loop to add upper elements in order to the list
		while(!upper.isEmpty())
		{
			list.add(upper.removeFirst());
		}

	}

}
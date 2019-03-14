import java.util.Collections;

public class PriorityQueue <T extends Comparable<? super T>>
{
	//Heap heap = new Heap[];
	/*INSERT-
	 * requires: 
	 */
	// You must use a binary heap for the implementation of the Priority Queue ADT.
	private Heap<T> binHeap;
	
	public PriorityQueue()
	{
		binHeap = new Heap<T>(10);
	}
	
	public PriorityQueue(int capacity)
	{
		binHeap = new Heap<T>(capacity);
	}
	
	//requires- this.capacity > 0
	//ensures - this.heap.contains(object)
	public void insert(T object)
	{
		
	}
	
	//requires - this.size > 0
	//ensures - !this.heap.contains(Object)
	public void remove(T object)
	{
		
	}
}

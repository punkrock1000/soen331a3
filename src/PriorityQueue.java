import java.util.Collections;

public class PriorityQueue <ElementType extends Comparable<? super ElementType>>
{
	//Heap heap = new Heap[];
	/*INSERT-
	 * requires: 
	 */
	// You must use a binary heap for the implementation of the Priority Queue ADT.
	private Heap<ElementType> binHeap;
	
	public PriorityQueue()
	{
		binHeap = new Heap<ElementType>(10);
	}
	
	public PriorityQueue(int capacity)
	{
		binHeap = new Heap<ElementType>(capacity);
	}
	
	//requires- this.capacity > 0
	//ensures - this.heap.contains(object)
	public void insert(ElementType el)
	{
		binHeap.insert(el);
	}
	
	//requires - this.size > 0
	//ensures - !this.heap.contains(Object)
	public void remove()
	{
		binHeap.remove();
	}

	public ElementType min()
	{
		return binHeap.min();
	}
}

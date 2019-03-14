import be.ac.ua.ansymo.adbc.annotations.ensures;
import be.ac.ua.ansymo.adbc.annotations.invariant;
import be.ac.ua.ansymo.adbc.annotations.requires;

import java.util.ArrayList;
import java.util.Collections;

@invariant(value = {"$this.binHeap.heapList.length >= 0",
				    "$this.binHeap.size >= 0",
				    "$this.binHeap.maxSize > 0"})
public class PriorityQueue <ElementType extends Comparable<? super ElementType>>
{
	/* IMPORTANT NOTE:
		A lot of @requires and @ensures statement are "hidden" in class Heap.
		Indeed, it seemed redundant to ensure postconditions about the underlying Heap
		(called binHeap for Binary Heap). However, we still require minimum post conditions when
		they can end up not holding because of a user error.
		For instance, we require that the underlying binHeap be not empty when the user calls
		the min or remove methods.
	 */
	// You must use a binary heap for the implementation of the Priority Queue ADT.
	public Heap<ElementType> binHeap;

	@requires({"true"})
	@ensures({"$this.binHeap != null"})
	public PriorityQueue()
	{
		binHeap = new Heap<ElementType>(10);
	}

	@requires({"capacity > 0"})
	@ensures({"$this.binHeap != null"})
	public PriorityQueue(int capacity)
	{
		binHeap = new Heap<ElementType>(capacity);
	}
	
	@requires({"el != null"})
	@ensures({"true"})
	public void insert(ElementType el)
	{
		binHeap.insert(el);
	}

	@requires(value = {"$this.binHeap.size > 0"})
	@ensures({"$result == $old($this.binHeap.get(binHeap.FRONT))"})
	public ElementType remove()
	{
		return binHeap.remove();
	}

	@requires(value = {"$this.binHeap.size > 0"})
	@ensures({"$result == $this.binHeap.get(binHeap.FRONT)",
			  "$this.binHeap == $old($this.binHeap)"})
	public ElementType min()
	{
		return binHeap.min();
	}

	@requires(value = {"$this.binHeap.size > 0"})
	@ensures({"$this.binHeap == $old($this.binHeap)"})
	public void print()
	{
		System.out.println("Printing a Priority Queue.");
		binHeap.printHeap();
	}
}

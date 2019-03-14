import java.util.ArrayList;

import be.ac.ua.ansymo.adbc.annotations.ensures;
import be.ac.ua.ansymo.adbc.annotations.invariant;
import be.ac.ua.ansymo.adbc.annotations.requires;
import be.ac.ua.ansymo.adbc.exceptions.ContractEnforcementException;

@invariant	(value = {"$this.heapList.length >= 0",
              "$this.size > 0",
              "$this.maxSize > 0"})
public class Heap<T extends Comparable<? super T>> {
    public static final int FRONT = 1; 
    
	public ArrayList<T> heapList; 
    public int size; 
    public int maxSize; 

	@requires ({"maxsize > 0"})
	@ensures	({"$this.heapList != null",
				  "$this.maxsize > 0",
				  "$this.size == 0",
				  "$this.size <= this.maxsize"})
    public Heap(int maxsize) 
    	throws ContractEnforcementException 
    { 
        this.maxSize = maxsize; 
        this.size = 0; 
        heapList = new ArrayList<T>(this.maxSize);
    }
	
	@requires ({"$this.size > 0",
				"$this.heapList.length > 0",
				"position < $this.size"})
    // Get if node is a leaf
    public boolean isLeaf(int position) 
    { 
        if (position >= (size / 2) && position <= size) { 
            return true; 
        } 
        return false; 
    }

    // Get position of parent node
    public int parentNodePosition(int position)
    { 
        return position / 2; 
    } 


    // Get position of right child
    public int rightChildPosition(int position)
    { 
        return (2 * position) + 1; 
    } 
    
    // Get position of left child
    public int leftChildPosition(int position)
    { 
        return (2 * position); 
    } 
  
    //Make the heap
    public void heap() 
    { 
        for (int pos = (size / 2); pos >= 1; pos--) { 
            priorityHeap(pos); 
        } 
    } 
  
    
    // Convert binary tree to heap
    public void priorityHeap(int position) 
    { 
        if (!isLeaf(position)) { 
            if (heapList.get(position).compareTo(heapList.get(leftChildPosition(position))) > 0 ||
                    heapList.get(position).compareTo(heapList.get(rightChildPosition(position))) > 0)
            {
                if (heapList.get(leftChildPosition(position)).compareTo(heapList.get(rightChildPosition(position))) < 0) {
                    swap(position, leftChildPosition(position));
                    priorityHeap(leftChildPosition(position));
                }
                else { 
                    swap(position, rightChildPosition(position));
                    priorityHeap(rightChildPosition(position));
                } 
            } 
        } 
    } 
  
	@requires ({"$this.size < $this.maxSize"})
	@ensures	({"$this.heapList != null",
				  "$this.heapList.length == $old($this.heapList.length) + 1",
				  "$this.size == $old($this.size) + 1"})
    //Insert node to heap 
    public void insert(T element)
    {
        heapList.add(element);
        int currentNode = size++;

        int parentNodePosition = parentNodePosition(currentNode);
        while (parentNodePosition >= 0 && parentNodePosition < heapList.size() &&
                heapList.get(currentNode).compareTo(heapList.get(parentNodePosition)) < 0) {
            swap(currentNode, parentNodePosition(currentNode));
            currentNode = parentNodePosition(currentNode);
            parentNodePosition = parentNodePosition(currentNode);
        }
    }
	
	@requires ({"$this.size > 0",
				"$this.heapList.length > 0"})
	@ensures ({"$this.heapList != null",
			   "$this.size == $old($this.size) + 1",
			   "$this.heapList.length > 0"})
    //Remove function
    public T remove()
    {
        if (size == 0 || heapList.size() == 0)
            return null;
        System.out.println(heapList);
        T removedNode = heapList.get(FRONT);
        heapList.set(FRONT, heapList.get(size--));
        priorityHeap(FRONT);
        return removedNode;
    }

    @ensures({"this.heapList[position1] == $old($this.heapList[position2])",
    		  "this.heapList[position2] == $old($this.heapList[position1])"})
    // Swap nodes of the heap
    public void swap(int position1, int position2) 
    { 
        T temp;
        temp = heapList.get(position1);
        heapList.set(position1, heapList.get(position2));
        heapList.set(position2, temp);
    }
    
    public T min()
    {
        return null;
    }
    
	@requires ({"$this.size > 0",
				"$this.heapList.length > 0"})
    // Print the heap 
    public void printHeap() 
    { 
    	System.out.println("The Priority Binary Heap is ");
        for (int i = 0; i <= (size - 1) / 2; i++) {
            System.out.println("Parent: " + heapList.get(i));
            int leftChildPosition = leftChildPosition(i);
            int rightChildPosition = rightChildPosition(i);
            if (leftChildPosition > 0 && leftChildPosition < heapList.size())
            {
                System.out.print("Left: " + heapList.get(leftChildPosition));
                if (rightChildPosition > 0 && rightChildPosition < heapList.size())
                {
                    System.out.print("  Right: " + heapList.get(rightChildPosition));
                }
            }
            System.out.println("\n"); 
        } 
    } 

}

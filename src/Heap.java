import java.util.ArrayList;

import be.ac.ua.ansymo.adbc.annotations.ensures;
import be.ac.ua.ansymo.adbc.annotations.invariant;
import be.ac.ua.ansymo.adbc.annotations.requires;
import be.ac.ua.ansymo.adbc.exceptions.ContractEnforcementException;

@invariant	(value = {"$this.heapList.size() >= 0",
                      "$this.size >= 0",
                      "$this.maxSize > 0"})
public class Heap<T extends Comparable<? super T>> {
    public static final int FRONT = 0;
    
	private ArrayList<T> heapList;
    public int size; 
    public int maxSize; 

	@requires ({"maxsize > 0"})
	@ensures	({"$this.heapList != null",
				  "$this.maxsize > 0",
                  "$this.heapList.size() == 0",
				  "$this.size == 0",
				  "$this.size <= this.maxsize"})
    public Heap(int maxsize) 
    	throws ContractEnforcementException 
    { 
        this.maxSize = maxsize; 
        this.size = 0; 
        heapList = new ArrayList<T>(this.maxSize);
    }
  
	@requires ({"$this.size < $this.maxSize"})
	@ensures	({"$this.heapList != null",
				  "$this.heapList.length == $old($this.heapList.length) + 1",
				  "$this.size == $old($this.size) + 1"})
    //Insert node to heap 
    public void insert(T element)
    {
        if (heapList.size() == 0 || size > heapList.size() - 1)
            heapList.add(element);
        else
            heapList.set(size, element);
        // Sort up the tree from the very last/bottom position of the tree.
        sortUp(size++);
    }
	
	@requires ({"$this.size > 0",
				"$this.heapList.length > 0"})
	@ensures ({"$this.heapList != null",
			   "$this.size == $old($this.size) + 1",
               "$this.size > 0",
			   "$this.heapList.length > 0",
               "$result != null",
               "$result == $old($this.get(FRONT))"})
    //Remove function
    public T remove()
    {
        if (size == 0 || heapList.size() == 0)
            return null;
        if (size == 1)
            return heapList.remove(--size);
        T removedNode = heapList.get(FRONT);
        heapList.set(FRONT, heapList.remove(--size));
        sortDown(FRONT);
        return removedNode;
    }

    @requires ({"$this.size > 0",
                "$this.heapList.length > 0",
                "position >= 0",
                "position < $this.size",
                "position < $this.heapList.size()"})
    @ensures ({"$this.heapList != null",
               "$this.size == $old($this.size)",
               "$this.size > 0",
               "$this.heapList.size() > 0"})
    public void sortUp(int position)
    {
        int parentNodePosition = parentNodePosition(position);
        while (parentNodePosition >= 0 && parentNodePosition < heapList.size() &&
                heapList.get(position).compareTo(heapList.get(parentNodePosition)) < 0) {
            swap(position, parentNodePosition(position));
            position = parentNodePosition(position);
            parentNodePosition = parentNodePosition(position);
        }
    }

    @requires ({"$this.size > 0",
                "$this.heapList.length > 0",
                "position >= 0",
                "position < $this.size",
                "position < $this.heapList.size()"})
    @ensures ({"$this.heapList != null",
               "$this.size == $old($this.size)",
               "$this.size > 0",
               "$this.heapList.size() > 0"})
    public void sortDown(int position)
    {
        if (!isLeaf(position)) {
            int leftChildPosition = leftChildPosition(position);
            int rightChildPosition = rightChildPosition(position);
            // Check whether the left child's key value is greater than the current one.
            if (leftChildPosition >= 0 && leftChildPosition < size && heapList.get(position).compareTo(heapList.get(leftChildPosition(position))) > 0)
            {
                // If so, also check whether the right child's key value is greater than the current one.
                // If it is the case, compare which child has the greatest value.
                if (rightChildPosition >= 0 && rightChildPosition < size &&
                        heapList.get(position).compareTo(heapList.get(rightChildPosition(position))) > 0 &&
                        heapList.get(leftChildPosition).compareTo(heapList.get(rightChildPosition)) > 0)
                {
                    swap(position, rightChildPosition);
                    sortDown(rightChildPosition);
                }
                else
                {
                    swap(position, leftChildPosition);
                    sortDown(leftChildPosition);
                }
            }
        }
    }

    @ensures({"this.heapList.get(position1).equals($old($this.heapList.get(position2)))",
    		  "this.heapList.get(position2).equals($old($this.heapList.get(position1)))"})
    // Swap nodes of the heap
    public void swap(int position1, int position2) 
    { 
        T temp;
        temp = heapList.get(position1);
        heapList.set(position1, heapList.get(position2));
        heapList.set(position2, temp);
    }

    @requires ({"$this.size > 0",
                "$this.heapList.size() > 0"})
    @ensures ({"$this.heapList != null",
               "$this.size > 0",
               "$this.heapList == $old($this.heapList)",
               "$this.size == $old($this.size)",
               "$this.maxSize == $old($this.maxSize)",
               "$this.heapList.length > 0",
               "$result != null",
               "$result == $this.get(FRONT)"})
    public T min()
    {
        return heapList.get(FRONT);
    }

    @requires ({"$this.size > 0",
            "$this.heapList.size() > 0",
            "position < $this.size"})
    // Get if node is a leaf
    public boolean isLeaf(int position)
    {
        if (position >= (size / 2) && position <= size) {
            return true;
        }
        return false;
    }

    @requires(value = {"position / 2 >= 0",
            "position / 2 < this.size"})
    @ensures({"$result != null",
            "$result != position",
            "$result == position / 2"})
    // Get position of parent node
    public int parentNodePosition(int position)
    {
        return (int) Math.floor((double) position / 2);
    }

    @requires(value = {"(2 * position) + 2 >= 0",
            "(2 * position) + 2 < this.size"})
    @ensures({"$result != null",
            "$result == (2 * position) + 2"})
    // Get position of right child
    public int rightChildPosition(int position)
    {
        return (2 * position) + 2;
    }

    @requires(value = {"(2 * position) + 1 >= 0",
            "(2 * position) + 1 < this.size"})
    @ensures({"$result != null",
            "$result == (2 * position) + 1"})
    // Get position of left child
    public int leftChildPosition(int position)
    {
        return (2 * position) + 1;
    }

	@requires ({"$this.size > 0",
				"$this.heapList.size() > 0"})
    @ensures({"$this.heapList == $old($this.heapList)",
              "$this.size == $old($this.size)",
              "$this.maxSize == $old($this.maxSize)"})
    // Print the heap 
    public void printHeap() {
        System.out.println("Printing a Heap.");
        System.out.println(heapList);
        for (int i = 0; i < size; i++) {
            int leftChildPosition = leftChildPosition(i);
            int rightChildPosition = rightChildPosition(i);
            if (leftChildPosition >= 0 && leftChildPosition < heapList.size()) {
                System.out.println("Parent: " + heapList.get(i));
                System.out.print("Left: " + heapList.get(leftChildPosition));
                if (rightChildPosition >= 0 && rightChildPosition < heapList.size()) {
                    System.out.print("  Right: " + heapList.get(rightChildPosition));
                }
                System.out.println();
            }
            else
            {
                System.out.println("Leaf node: " + heapList.get(i));
            }
        }
    }
}

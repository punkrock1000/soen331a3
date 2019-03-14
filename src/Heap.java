import be.ac.ua.ansymo.adbc.annotations.ensures;
import be.ac.ua.ansymo.adbc.annotations.invariant;
import be.ac.ua.ansymo.adbc.annotations.requires;


public class Heap {
    public static final int FRONT = 1; 
    
	public int[] heapList; 
    public int size; 
    public int maxSize; 

	@requires ({"maxsize > 0"})
	@ensures	({
					"$this.heapList != null",
					"$this.maxsize > 0",
					"$this.size > 0",
					"$this.size <= this.maxsize"
    })
    public Heap(int maxsize) 
    { 
        this.maxSize = maxsize; 
        this.size = 0; 
        heapList = new int[this.maxSize + 1]; 
        heapList[0] = Integer.MIN_VALUE; 
    }
    
    @requires(value = { "" })
    // Get if node is a leaf
    public boolean isLeaf(int position) 
    { 
        if (position >= (size / 2) && position <= size) { 
            return true; 
        } 
        return false; 
    }

    // Get position of parent node
    public int parentNode(int position) 
    { 
        return position / 2; 
    } 


    // Get position of right child
    public int rightBranch(int position) 
    { 
        return (2 * position) + 1; 
    } 
    
    // Get position of left child
    public int leftBranch(int position) 
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
            if (heapList[position] > heapList[leftBranch(position)] || heapList[position] > heapList[rightBranch(position)]) { 
                if (heapList[leftBranch(position)] < heapList[rightBranch(position)]) { 
                    swap(position, leftBranch(position)); 
                    priorityHeap(leftBranch(position)); 
                }
                else { 
                    swap(position, rightBranch(position)); 
                    priorityHeap(rightBranch(position)); 
                } 
            } 
        } 
    } 
  
    //Insert node to heap 
    public void insert(int element) 
    { 
        heapList[++size] = element; 
        int currentNode = size; 
        while (heapList[currentNode] < heapList[parentNode(currentNode)]) { 
            swap(currentNode, parentNode(currentNode)); 
            currentNode = parentNode(currentNode); 
        } 
    } 

    //Remove function
    public int remove() 
    { 
        int remove = heapList[FRONT]; 
        heapList[FRONT] = heapList[size--]; 
        priorityHeap(FRONT); 
        return remove; 
    } 

    
    // Swap nodes of the heap
    public void swap(int position1, int position2) 
    { 
        int temp; 
        temp = heapList[position1]; 
        heapList[position1] = heapList[position2]; 
        heapList[position2] = temp; 
    } 
    
    // Print the heap 
    public void printHeap() 
    { 
    	System.out.println("The Priority Binary Heap is "); 
        for (int i = 1; i <= size / 2; i++) { 
            System.out.println("Parent: " + heapList[i]); 
            System.out.print("Left: " + heapList[2 * i] + "  Right: " + heapList[2 * i + 1]);
            System.out.println("\n"); 
        } 
    } 

}

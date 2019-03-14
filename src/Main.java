/*
 * Tran Hai Vong Thang 40004591
 */
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		boolean addNumber = true;
		Heap minHeap = new Heap(15); 
		try {
			while(addNumber) {
				minHeap.insert(userInput());
			}
		}catch(InputMismatchException error) {
			addNumber = false;
		}
        minHeap.printHeap(); 
        System.out.print("Min heap:"+minHeap.remove()+" ");
	}

    private static int userInput() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a number to add to the heap or a letter if finished adding.");
        int key = scan.nextInt();
        return key;
    }
}

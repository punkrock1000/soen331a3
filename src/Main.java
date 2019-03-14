/*
 * Tran Hai Vong Thang 40004591
 */
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		char userChoice = 'i';
		Scanner sc = new Scanner(System.in);
		Heap minHeap = new Heap<String>(15);
		PriorityQueue pq = new PriorityQueue<Integer>(10);

		pq.insert(6);
		pq.insert(4);
		pq.insert(58);
		pq.insert(35);
		pq.insert(1);
		pq.insert(3);
		pq.insert(-7);
		pq.insert(-3);
		pq.insert(100);
		pq.insert(31);

		minHeap.insert("c");
		minHeap.insert("cc");
		minHeap.insert("ddd");
		minHeap.insert("b");
		minHeap.insert("ee");
		minHeap.insert("e");
		minHeap.insert("a");
		minHeap.insert("0");
		minHeap.insert("f");
		minHeap.insert("g");
		minHeap.insert("aa");

		System.out.println("Testing the PriorityQueue class:");
		System.out.println("We initialized the queue with some test values already.");
		pq.print();
		while (userChoice == 'i' || userChoice == 'r' || userChoice == 'm') {
			System.out.println("What do you want to do? ('i' to Insert node, 'r' to remove node with smallest key, " +
					"'m' to return the node with smallest key without removing it, 'q' or anything else to quit)");
			userChoice = Character.toLowerCase(sc.next().charAt(0));
			sc.reset();
			System.out.println(userChoice);

			switch (userChoice) {
				case 'i':
					int userInput;
					try {
						System.out.println("Enter an integer to add to the priority queue.");
						pq.insert((int) sc.nextInt());
						sc.reset();
					} catch (InputMismatchException error) {
					}
					break;
				case 'r':
					System.out.println("Removed the element with smallest key " + pq.remove() + ".");
					System.out.println("Printing the Priority Queue after smallest key has been removed:");
					pq.print();
					break;
				case 'm': System.out.println("The element with the smallest key is: " + pq.min());
					break;
				default:
					System.out.println("Quitting the priority queue tests.");
					pq.print();
					userChoice = 'q';
			}
		}

		userChoice = 'i';
		System.out.println("Testing the Heap class:");
		System.out.println("We initialized the Heap with some test values already.");
		minHeap.printHeap();
		while (userChoice == 'i' || userChoice == 'r' || userChoice == 'm') {
			System.out.println("What do you want to do? ('i' to Insert node, 'r' to remove node with smallest key, " +
					"'m' to return the node with smallest key without removing it, 'q' or anything else to quit)");
			userChoice = Character.toLowerCase(sc.next().charAt(0));
			sc.reset();
			System.out.println(userChoice);

			switch (userChoice) {
				case 'i':
					int userInput;
					try {
						System.out.println("Enter a string to add to the priority queue.");
						minHeap.insert(sc.next());
						sc.reset();
					} catch (InputMismatchException error) {
					}
					break;
				case 'r':
					System.out.println("Removed the element with smallest key " + minHeap.remove() + ".");
					System.out.println("Printing the Heap after smallest key has been removed:");
					minHeap.printHeap();
					break;
				case 'm': System.out.println("The element with the smallest key is: " + minHeap.min());
					break;
				default:
					System.out.println("Quitting the priority queue tests.");
					minHeap.printHeap();
					userChoice = 'q';
			}
		}
	}
}

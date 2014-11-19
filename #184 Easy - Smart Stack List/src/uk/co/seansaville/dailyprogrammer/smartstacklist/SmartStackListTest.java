package uk.co.seansaville.dailyprogrammer.smartstacklist;

import java.util.Random;

public class SmartStackListTest {
	
	public static void main(String[] args) {
		SmartStackList test = new SmartStackList();
		
		// Generate a random number between 1 and 40
		int n = new Random().nextInt(40) + 1;
		System.out.println("Generating " + n + " numbers.\n");
		
		// Push n random numbers between -1000 and 1000 onto the list
		Random gen = new Random();
		for (int i = 0; i < n; i++) {
			int toPush = gen.nextInt(2000) - 999;
			test.push(toPush);
		}
		
		// Display stack and sorted order
		System.out.println("Stack order:");
		test.displayStack();
		System.out.println("\nSorted order:");
		test.displaySorted();
		
		System.out.println("\nNumber of items in stack: " + test.size());
		
		// Generate a random number between -1000 and 1000, remove every number
		// greater than this
		int x = gen.nextInt(2000) - 999;
		System.out.println("\nRemoving all numbers greater than " + x);
		test.removeGreater(x);
		
		// Display stack and sorted order
		System.out.println("\nStack order:");
		test.displayStack();
		System.out.println("\nSorted order:");
		test.displaySorted();
		
		// Pop half of the list
		System.out.print("\nPopped: ");
		for (int i = 0; i < n/2; i++) {
			System.out.print(test.pop() + " ");
		}
		
		// Display stack and sorted order
		System.out.println("\nStack order:");
		test.displayStack();
		System.out.println("\nSorted order:");
		test.displaySorted();
	}

}

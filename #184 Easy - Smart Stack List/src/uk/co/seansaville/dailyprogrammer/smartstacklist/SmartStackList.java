package uk.co.seansaville.dailyprogrammer.smartstacklist;

public class SmartStackList {
	
	SmartStackListNode firstSorted;
	SmartStackListNode firstStack;
	
	public SmartStackList() {
		firstSorted = null;
		firstStack = null;
	}
	
	public void push(int num) {
		SmartStackListNode newNode = new SmartStackListNode(num);
		
		// This new node needs to go on top of the stack, and in the correct place
		// in the sorted list.
		
		newNode.nextStack = this.firstStack;
		this.firstStack.prevStack = newNode;
		this.firstStack = newNode;
		
		if (firstSorted == null) {
			this.firstSorted = newNode;
		} else {
			boolean placed = false;
			SmartStackListNode current = firstSorted;
			while (!placed) {
				if (newNode.value > current.value) {
					current = current.nextSorted;
				} else {
					newNode.nextSorted = current.nextSorted;
					current.nextSorted.prevSorted = newNode;
					newNode.prevSorted = current;
					current.nextSorted = newNode;
					placed = true;
				}
			}
		}
	}
	
	public int pop() {
		SmartStackListNode removed = firstStack;
		int num = removed.value;
		firstStack = removed.nextStack;
		firstStack.prevStack = null;
		return num;
	}
	
	public int size() {
		SmartStackListNode current = firstStack;
		int size = 0;
		while (current != null) {
			current = current.nextStack;
			size++;
		}
		return size;
	}
	
	public void removeGreater(int num) {
		//TODO: remove all numbers greater than num from the list
		SmartStackListNode current = firstSorted;
		while (current.value <= num) {
			if (current.nextSorted != null) {
				current = current.nextSorted;
			} else {
				return;
			}
		}
		while (current != null) {
			// We need to remove current from the list
			current.prevStack.nextStack = current.nextStack;
			current.prevSorted.nextSorted = current.nextSorted;
			current.nextStack.prevStack = current.prevStack;
			current.nextSorted.prevSorted = current.prevSorted;
			current = current.nextSorted;
			
		}
	}
	
	public void displayStack() {
		SmartStackListNode current = firstStack;
		while (current != null) {
			System.out.print(current.value + " ");
			current = current.nextStack;
		}
		System.out.print("\n");
	}
	
	public void displaySorted() {
		SmartStackListNode current = firstSorted;
		while (current != null) {
			System.out.print(current.value + " ");
			current = current.nextSorted;
		}
		System.out.print("\n");
	}
}

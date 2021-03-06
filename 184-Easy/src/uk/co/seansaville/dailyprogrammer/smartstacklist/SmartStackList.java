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
		
		if (firstStack == null) {
			firstStack = newNode;
		} else {
			newNode.nextStack = this.firstStack;
			firstStack.prevStack = newNode;
			firstStack = newNode;
		}
		
		if (firstSorted == null) {
			firstSorted = newNode;
		} else {
			boolean placed = false;
			SmartStackListNode current = firstSorted;
			while (!placed) {
				if (newNode.value > current.value) {
					if (current.nextSorted != null) {
						current = current.nextSorted;
					} else {
						current.nextSorted = newNode;
						newNode.prevSorted = current;
						placed = true;
					}
				} else {
					if (current.prevSorted != null) {
						newNode.nextSorted = current;
						newNode.prevSorted = current.prevSorted;
						current.prevSorted.nextSorted = newNode;
						current.prevSorted = newNode;
						placed = true;
					} else {
						current.prevSorted = newNode;
						newNode.nextSorted = current;
						firstSorted = newNode;
						placed = true;
					}
				}
			}
		}
	}
	
	public int pop() {
		SmartStackListNode removed = firstStack;
		int num = removed.value;
		
		// Fix the stack order
		firstStack = removed.nextStack;
		if (firstStack != null) {
			firstStack.prevStack = null;
		}
		
		// Fix the sorted order
		if (removed.prevSorted == null) {
			firstSorted = removed.nextSorted;
		} else if (removed.nextSorted == null) {
			removed.prevSorted.nextSorted = removed.nextSorted;
		} else {
			removed.prevSorted.nextSorted = removed.nextSorted;
			removed.nextSorted.prevSorted = removed.prevSorted;	
		}

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
		SmartStackListNode current = firstSorted;
		while (current.value <= num) {
			if (current.nextSorted != null) {
				current = current.nextSorted;
			} else {
				return;
			}
		}

		while (current != null) {
			// We need to remove the node from the stack and from the sorted list
			// 3 cases: current is the start/middle/end of the list
			
			// Stack:
			if (current.prevStack == null) {
				firstStack = current.nextStack;
				if (firstStack != null) {
					firstStack.prevStack = null;
				}
			} else if (current.nextStack == null) {
				current.prevStack.nextStack = null;
			} else {
				current.prevStack.nextStack = current.nextStack;
				current.nextStack.prevStack = current.prevStack;
			}

			// Sorted:
			if (current.prevSorted == null) {
				firstSorted = current.nextSorted;
				if (firstSorted != null) {
					firstSorted.prevSorted = null;
				}
			} else if (current.nextSorted == null) {
				current.prevSorted.nextSorted = null;
			} else {
				current.prevSorted.nextSorted = current.nextSorted;
				current.nextSorted.prevSorted = current.prevSorted;
			}

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
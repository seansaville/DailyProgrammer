package uk.co.seansaville.dailyprogrammer.smartstacklist;

public class SmartStackListNode {
	
	int value;
	
	SmartStackListNode nextSorted;
	SmartStackListNode nextStack;
	
	SmartStackListNode prevSorted;
	SmartStackListNode prevStack;
	
	SmartStackListNode(int val) {
		value = val;
	}

}

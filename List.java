
// CLASS: List
     //
     // Author: Karlos Abellanosa, 7765856
     //
     // REMARKS: a Linked List that stores subclass variables of ListItem to allow polymorphism
     //
     //-----------------------------------------
public class List {
	
	//private variables
	private Node top;
	private Node last;
	private static int size;
	
	//default constructor
	public List() {
		top = null;
		last = null;
		size = 0;
	}
	
	//insert in the back of the list
	public void insert(String insert) {
		
		Node newNode = new Node (insert, null);
		
		//empty list
		if(top == null) {
			top = newNode;
			last = newNode;
		} else {
			last.setNext(newNode);
			last = newNode;
		}
		
		size++;
	}//insert in the back of the list
	
	public boolean search(String compare) {
		Node curr = top;
		boolean matches = false;
		while(curr != null && !matches) {
			
			if( curr.getItem().equals(compare) )
				matches = true;
			else
				curr = curr.getNext();
			
		}//while
		
		return matches;
	}//search's our list for the target specified by the parameter
	
	public String get(int index) {
		Node curr = top;
		int pos = 0;
		String get = null;
		
		while(curr != null && pos <= index) {
			get = curr.getItem();
			curr = curr.getNext();
			pos++;
		}//while
		
		return get;
	}//getter for item in the index specified by parameter
	
	public String delete(String toRemove) {
		String delete = null;
		Node curr = top;
		Node prev = null;
		
		while(curr != null && (!curr.getItem().equals(toRemove)) ) {
			prev = curr;
			curr = curr.getNext();
		}

		if(prev == null) {
			delete = curr.getItem();
			top = top.getNext();
		} else if (curr.getNext() == null) {
			delete = curr.getItem();
			prev.setNext(null);
		} else {
			delete = curr.getItem();
			prev.setNext(curr.getNext());
		}
		
		size--;
		
		return delete;
	}//deletes an item in our List
	
	public int getSize() {
		return size;
	}//getter for size 
	
	public boolean isEmpty() {
		return top == null;
	}//returns true if List if empty
	
	public void print() {
		Node curr = top;
		
		while(curr != null) {
			curr.print();
			curr = curr.getNext();
		}//while
		
	}//print method

}//List

// CLASS: Node
     //
     // Author: Karlos Abellanosa, 7765856
     //
     // REMARKS: Node that stores a string and a node pointer to next item object
     //
     //-----------------------------------------
public class Node {
	
	private String theItem;
	private Node link;
	
	//constructor
	public Node(String initItem, Node initLink){
		
		theItem = initItem;
		link = initLink;
	}
	
	public Node getNext() {
		return link;
	}//getter for link
	
	public void setNext(Node link) {
		this.link = link;
	}//setter for link
	
	public String getItem() {
		return theItem;
	}//getter for item
	
	public void setItem (String theItem) {
		this.theItem = theItem;
	}//setter for item
	
	public void print() {
		System.out.print(theItem);
	}//print the Item in the node -> polymorphism

}//Node

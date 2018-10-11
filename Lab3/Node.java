public class Node {
	rectangle info;
	Node next;
	
	public Node(rectangle info){
		this.info=info;
	}
	
	public Node(rectangle info, Node next){
		this.info=info;
		this.next=next;
	}
}
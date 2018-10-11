public class Stack{
	private Node top;
	private int count;
	
	public Stack() {
		top = null;
		count = 0;
	}
	
	public boolean isEmpty(){
		return (top == null);
	}
	
	public void push(rectangle newItem){
		top = new Node(newItem, top);
		count++;
	}
	
	public rectangle pop(){
		if (isEmpty()){
			System.out.println("Incomplete expression");
			return null;
		}else{
			Node temp = top;
			top = top.next;
			count--;
			return temp.info;
		}
	}
	
	void popAll(){
		top = null;
		count = 0;
	}
	
	public rectangle peek(){
		if (isEmpty()){
			System.out.println("Trying to peek when stack is empty");
			return null;
		}else{
			return top.info;
		}
	}
	
	public int size(){
		return count;
	}
}
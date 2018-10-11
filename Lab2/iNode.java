public class iNode{
	public int item;
	public iNode next;
	
	public iNode(){}
	
	public iNode(int i, iNode n){
		item = i;
		next = n;
	}
	
	public iNode(int i){
		item = i;
		next = null;
	}
	
	public int size() {
        int length = 0;
        iNode iter = this;
        while(iter.next != null) {
			length++;
			iter = iter.next;
		}
        return length;
    }
}
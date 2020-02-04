package main.swamy.linkedlist;

public class AddTwoLists {
	Node head;
	
	public class Node{
		int val;
		Node next;
		
		public Node(int val, Node next) {
			this.val = val;
			this.next = next;
		}
	}

	public AddTwoLists() {
		head = null;
	}
	public static void main(String[] args) {
		
			AddTwoLists list1 = new AddTwoLists();
			list1.addLast(5);
			list1.addLast(6);
			list1.addLast(7);
			
			System.out.println(list1);
			
			AddTwoLists list2 = new AddTwoLists();
			list2.addLast(5);
			list2.addLast(2);
			list2.addLast(1);
			System.out.println(list2);
			
			addLists(list1.head, list2.head);
	}
	
	//this works only if two lists size is equal
	//if size is not equal, do padding with 0 for less size list
	private static void addLists(Node listOne, Node listTwo) {
			Node first = listOne;
			Node second = listTwo;
			AddTwoLists res = new AddTwoLists();
			int carry = 0;
			int sum = 0;
			//Node dummyHead = new Node(0, null);
			while((first != null) && (second != null)) {
				int x = (first != null) ? first.val : 0;
				int y = (second != null ) ? second.val : 0;
				
				sum = x + y + carry;
				System.out.println("sum:"+sum);
				carry = sum/10;
				System.out.println("carry:"+carry);
				 res.addLast(sum%10);
				first = first.next;
				second = second.next;
			}
			if(carry > 0) {
				 res.addLast(carry);
			}
		System.out.println(res);
	}
	public String toString() {
		StringBuffer sb = new StringBuffer();
		Node current = head;
		while(current != null) {
			sb.append(current.val);
			sb.append("-->");
			current = current.next;
		}
		return sb.toString();
	}

	private void addLast(int val) {
		if(head == null) {
			addFirst(val);
		}
		else {
			Node t = head;
			while(t.next != null)
				t = t.next;
			
			t.next = new Node(val,null);
		}
	}

	private void addFirst(int val) {
		if(head == null)
			head = new Node(val, head);
	}

}

package main.swamy.linkedlist;

public class PartialSum {

	class Node{
		int val;
		Node next;
		public Node(int val) {
			this.val = val;
		}
	}
	
	
	int carry;
	Node head1, head2, result, cur;
	
	public static void main(String[] args) {
		PartialSum list = new PartialSum();
		list.head1 = null;
		list.head2 = null;
		list.result = null;
		
		list.carry = 0;
		int arr1[] = {9,9,9};
		int arr2[] = {1, 8};
		
		//create a first list
		for(int i = arr1.length -1; i >= 0; --i)
			list.push(arr1[i], 1);
		
		//create second list
		for(int j = arr2.length -1; j >= 0; --j) {
			list.push(arr2[j], 2);
			
			list.addLists();
			list.printlist(list.result);
		}
	}

	private void printlist(Node head) {
		while(head != null) {
			System.out.print(head.val+" ");
			head = head.next;
		}
		
	}

	private void addLists() {
		if(head1 == null) {
			result = head2;
			return;
		}
		
		if(head2 == null) {
			result = head1;
			return;
		}
		
		int size1 = getSize(head1);
		int size2 = getSize(head2);
		
		if(size1 == size2) {
			addsamesize(head1, head2);
		}else {
			if(size1 < size2) {
				Node temp = head1;
				head1 = head2;
				head2 = temp;
			}
			int diff = Math.abs(size1 - size2);
			
			Node temp = head1;
			while(diff-- >= 0) {
				cur = temp;
				temp = temp.next;
			}
			addsamesize(cur, head2);
			
			propagatecarry(head1);
		}
		
		if(carry > 0)
			push(carry, 3);
	}

	private void propagatecarry(Node head1) {
		if(head1 != cur) {
			propagatecarry(head1.next);
			int sum = carry + head1.val;
			carry = sum /10;
			sum %= 10;
			
			push(sum, 3);
		}
	}

	private void addsamesize(Node n, Node m) {
		if(n == null)
			return;
		addsamesize(n.next, m.next);
		
		int sum = n.val + m.val + carry;
		carry = sum / 10;
		sum = sum % 10;
		push(sum, 3);
		
	}

	private int getSize(Node head) {
		int count = 0;
		while(head != null) {
			count++;
			head = head.next;
		}
		return count;
	}

	private void push(int val, int list) {// TODO Auto-generated method stub
		Node newNode = new Node(val);
		if(list == 1) {
			newNode.next = head1;
			head1 = newNode;
		}
		else if(list == 2) {
			newNode.next = head2;
			head2 = newNode;
		}else {
			newNode.next = result;
			result = newNode;
		}
	}
}

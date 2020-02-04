package main.swamy.linkedlist;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

import main.swamy.linkedlist.LinkedList.LinkedListIterator;

public class LinkedList<AnyType> implements Iterable<AnyType> {
	
	public class LinkedListIterator implements Iterator<AnyType> {
		private Node<AnyType> nextNode;
		public  LinkedListIterator() {
			nextNode = head;
		}
		@Override
		public boolean hasNext() {
			
			return nextNode != null;
		}

		@Override
		public AnyType next() {
			if(!hasNext()){throw new NoSuchElementException();}
			AnyType res = nextNode.data;
			nextNode = nextNode.next;
			return res;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();

		}

	}

	private Node<AnyType> head ;
	public LinkedList(){
		head = null;
	}
	
	/*
	 * AddFirst
	 */
	public void addFirst(AnyType data){
		head = new Node<AnyType>(data,head, null);
	}
	
	/*
	 * AddLast
	 */
	public void addLast(AnyType data){
		if(head == null){
			addFirst(data);
		}else{
			Node<AnyType> tmp = head;
			while(tmp.next != null)
				tmp=tmp.next;
			
			tmp.next = new Node<AnyType>(data,null,null);
		}
		
	}
	/*
	 * get any position
	 */
	public AnyType get(int pos){
		if(head == null){
			throw new NoSuchElementException();
		}else{
			Node<AnyType> tmp = head;
			for(int i=0; i<pos; i++){
				tmp= tmp.next;
			}
			return tmp.data;
		}
		
	}
	
	/*
	 * getFirst
	 */
	public AnyType getFirst(){
		if(head  == null){
			throw new NoSuchElementException();
		}else{
			
			return head.data;
		}
	}
	
	/*
	 * removeFirst
	 */
	public AnyType removeFirst(){
		if(head == null){
			throw new NoSuchElementException();
		}else{
			AnyType tmp =  getFirst();
			head = head.next;
			return tmp;
		}
	}
	
	/*
	 * get any position
	 */
	public AnyType getLast(){
		if(head == null){
			throw new NoSuchElementException();
		}else{
			Node<AnyType> tmp = head;
			while(tmp.next != null) tmp = tmp.next;
			
			return tmp.data;
		}
	}
	
	/*
	 * contains
	 */
	public boolean contains(AnyType item){
		
	//	int j=nthToLastRecusrive(head,3) + 1;//just calling from this method to test kth to last element prob, not useful to this method
		//System.out.println("J:"+j);//just 
		//iterative approach to test kth to last element
		Node<AnyType> n = nthToLastIterative(head, 2);
		System.out.println("n:"+n.data);
		
		
		
		if(head == null){
			throw new NoSuchElementException();
		}else{
		//	Node<AnyType> tmp = head;
		//	while(tmp.next != null){
			for(AnyType x: this){
				if(x.equals(item))
				{
				//	deleteNode(this); //this method call to test deleteNode
					return true;
				}
				//tmp= tmp.next;
			}
			return false;
		}
		
	}
	
	/*
	 * Delete duplicates
	 * takes O(N) Time where N is number of input elements
	 */
	public void deleteDuplicates(){
		Hashtable ht = new Hashtable();
		Node<AnyType> previous = null;
		while(head != null){
			if(!ht.containsKey(head.data)){
			//	previous.next = head.next;
				ht.put(head.data, true);
			/*}else{
				ht.put(head.data, true);
			//	previous = head;*/
			}
			head = head.next;
			//System.out.println(previous.data);
		}
		System.out.println("without Duplicates:"+ht);
	}
	
	/*
	 * Delete duplicates without buffer
	 * using runner technique, that is second pointer iterates fastly
	 * O(1) space but O(n2) time
	 */
	public Node deleteDuplicatesWithoutBuffer(){
		
		if(head == null) return null;
		Node<AnyType> current = head;
		
		while(current != null){
			Node<AnyType> runner = current;
			while(runner.next != null){
				System.out.println("current:"+current.data+"--> runner:"+runner.data+"--> runner.next.data:"+runner.next.data);
				//Remove all future nodes that have the same value
			if(runner.next.data == current.data){
				runner.next = runner.next.next;
				
			}else{
				runner = runner.next;
				
			}
			}
			current = current.next;
		}
		
		System.out.println("String list :"+head);
		return head;
	}
	/*Trace:
 current:S--> runner:w
 current:S--> runner:a
 current:S--> runner:m
 current:S--> runner:y
 current:S--> runner:m
 current:S--> runner:y
 current:w--> runner:a
 current:w--> runner:m
 current:w--> runner:y
 current:w--> runner:m
 current:w--> runner:y
 current:a--> runner:m
 current:a--> runner:y
 current:a--> runner:m
 current:a--> runner:y
 current:m--> runner:y
 current:m--> runner:m
 current:m--> runner:y
 current:y--> runner:y
	 * 
	 */
	/*Numbers
current:1--> runner:1--> runner.next.data:2
current:1--> runner:2--> runner.next.data:3
current:1--> runner:3--> runner.next.data:4
current:1--> runner:4--> runner.next.data:1
current:1--> runner:4--> runner.next.data:2
current:1--> runner:2--> runner.next.data:5
current:2--> runner:2--> runner.next.data:3
current:2--> runner:3--> runner.next.data:4
current:2--> runner:4--> runner.next.data:2
current:2--> runner:4--> runner.next.data:5
current:3--> runner:3--> runner.next.data:4
current:3--> runner:4--> runner.next.data:5
current:4--> runner:4--> runner.next.data:5
	 * 
	 */
	
	/**find kth to last element in single linked list
	*
	*/
	/*
	 * 1. Use recursive approach
	 */
	public int nthToLastRecusrive(Node<AnyType> head,int k){
		if(head == null){
			return 0;
		}
		
		int i = nthToLastRecusrive(head.next,k) +1;
		System.out.println(i);
		if(i == k){
			System.out.println("data:"+head.data);
		}
		
		return i;
	}
	
	/*
	 * O(N) time O(1) space
	 */
	public Node<AnyType> nthToLastIterative(Node<AnyType> head, int k){
			if(k<=0)return null;
			Node<AnyType> p1 = head;
			Node<AnyType> p2 = head;
			for(int i=0; i<k-1;i++){
				if(p2==null) return null;
				
				System.out.println(" p2:"+p2.data);
				p2 = p2.next;
			}
			if(p2==null) return null;
			while(p2.next != null){
				System.out.println("p1:"+p1.data+" p2:"+p2.data);
				p1 = p1.next;
				p2 = p2.next;
				
			}
		return p1;
	}
	
	
	/*
	 * delete mid element in single linked list, only have access to that node
	 */
	public void deleteNode(AnyType x){
		
		if(x == null){
			return;
			
		}
		Node<Integer> n = (Node<Integer>)x ;
		Node<Integer> next = n.next;
		System.out.println("before delete:"+n.data);
		n.data = next.data;
		n.next = next.next;
		System.out.println("new data:"+n.data);
		
	}
	
	public void partition(Node<Integer> item, int x){
		
		Node<AnyType> partVal = null;
		//partVal.data = item;
		//int i = (int) item;
		Node<AnyType> beginStart = null;
		Node<AnyType> beginEnd = null;
		Node<AnyType> afterStart = null;
		Node<AnyType> afterEnd = null;
		while(head != null){
			Node<AnyType> next = head.next;
			next.next = null;
		//	if(  head.data < item.data ){ Need pass correct input type pending
			if(beginStart == null){
				beginStart = head;
				beginEnd = beginStart;
			}else{
				beginEnd.next = head;
				beginEnd = head;
			}
			//}else{
				if(afterStart == null){
					afterStart = head;
					afterEnd = afterStart;
				}else{
					afterStart.next = head;
					afterEnd = head;
				}
			//}
			head = next;
		}
		if(beginStart == null){
			System.out.println("All in after start"+afterStart);
		}
		//merge
		beginEnd.next = afterStart;
		System.out.println("after merger:"+beginStart);
	}
	
	/**
	 * add Lists 1-->2-->3-->4 and 
	 *               5-->6-->7 of different size
	 * padd short list with 0
	 *    1-->2-->3-->4
	 *    0-->5-->6-->7
	 * @return
	 */
	/*
	Node<AnyType> addLists(Node l1, Node l2) {
		//first check the lengths of lists
		int len1 = length(l1);
		int len2 = length(l2);
		
		//pad the shorter list with zeros
		if(len1 < len2) {
			l1 = padList(l1, len2 - len1);
		}else {
			l2 = padList(l2, len1 - len2);
		}
		
		//Add Lists
		PartialSum sum = addListsHelper(l1, l2);
		
		//if there was a carry value left over, insert this at the front of the list.
		//otherwise , just return the linked list
		if(sum.carry == 0) {
			return sum.sum;
		}else {
			Node result = insertBefore(sum.sum, sum.carry);
			return result;
		}
		
	}
	
	private PartialSum addListsHelper(Node l1, Node l2) {
		if(l1 == null && l2 == null) {
			PartialSum sum = new PartialSum();
			return  sum;
		}
		//add small digits recursively
		PartialSum sum = addListsHelper(l1.next, l2.next);
		
		//Add carry to the current data
		int val = sum.carry + l1.data + l2.data;
		
		//insert sum of currenct digits
		Node full_result = insertBefore(sum.sum, val %10);
		
		//return sum so far, and the carry value
		sum.sum = full_result;
		sum.carry = val / 10;
		return sum;
	}
//helper fucntion to insert the node in front of a linked list
	private Node insertBefore(LinkedList sum, int data) {
		Node node = new Node(data, null, null);
		if(sum != null) {
			sum.prev = node;
			node.next = sum;
		}
		return node;
	}

	private Node padList(Node l, int padding) {
		Node head = l;
		for(int i=0; i< padding; i++) {
			Node n = new Node(0, null, null);
			head.prev = n;
			n.next = head;
			head = n;
		}
		return null;
	}

	public int length(Node l1) {
		int count = 0;
		while(l1 != null) {
			count++;
		}
		return count;
	}
*/	/*
	 * circular list, find the beginning
	 * 1. Create two pointers, FastPointer and SlowPointer
2. Move FastPointer at a rate of 2 steps and SlowPointer at a rate of 1step.
3. When they collide, move SlowPointer to LinkedListHead.Keep FastPointer
where it is.
4. Move SlowPointer and FastPointer at a rate of one step. Return the new collision point.

	 */
	public Node<AnyType> findBegining(){
		// 1. Create two pointers, FastPointer and SlowPointer.
		Node<AnyType> slowRunner = head;
		Node<AnyType> fastRunner = head;
		
		//SR one step and FR two steps
		while(fastRunner != null && fastRunner.next != null){
			System.out.println("SR data:"+slowRunner.data+" FR data:"+fastRunner.data);
			//2. Move FastPointer at a rate of 2 steps and SlowPointer at a rate of 1step.
			slowRunner = slowRunner.next;
			fastRunner = fastRunner.next.next;
		//	System.out.println("SR data1:"+slowRunner.data+" FR data1:"+fastRunner.data);
			//this blcok added to check value of fastrunner next, if you dont add this block, 
			//its always null, no collision will happen
			if(fastRunner.next == null || fastRunner.next.next == null){
				System.out.println("bfastRunner:"+head.data);
				fastRunner.next = head;
				System.out.println("fastRunner:"+fastRunner.data);
			}
			
			
			if(slowRunner == fastRunner){ //collision
				System.out.println("colliions:"+slowRunner.data);
				break;
			}
		}
		//Error check- there is no meeting points so no loop
		if(fastRunner == null || fastRunner.next == null)
		{
			System.out.println("null fastRunner");
			return null;
			
		}
		
		//3. When they collide, move SlowPointer to LinkedListHead.Keep FastPointer
		//where it is.
		//move slowRunner to head
		/* Move slow to Head. Keep fast at Meeting Point. Each are k * steps from the Loop Start. If they move at the same pace, * they must meet at Loop Start. */
		slowRunner = head;
		while(slowRunner != fastRunner){
			slowRunner = slowRunner.next;
			System.out.println("SR head:"+slowRunner.data);
			fastRunner = fastRunner.next;
			System.out.println("FR collision point:"+fastRunner.data);
		}
		
		System.out.println("fast->>collision point:"+fastRunner.data);
		return fastRunner;
	}
	/*
	 * 
SR data:1 FR data:1
SR data:2 FR data:3
SR data:3 FR data:1
bfastRunner:1
fastRunner:5
SR data:4 FR data:5
SR data:1 FR data:2
SR data:2 FR data:4
SR data:5 FR data:2
colliions:1
fast->>collision point:1
	 */
	
	//findBeginig second approach
	public boolean findBeginingSeondApproach(){
		

		Node<AnyType> slowRunner = head;
		Node<AnyType> fastRunner = head;
		  while (true) {
			  slowRunner = slowRunner.next;

		      if (fastRunner.next != null) {
		    	  fastRunner = fastRunner.next.next;
		      } else {
		          return false;
		      }

		      if (slowRunner == null || fastRunner == null) {
		          return false;
		      }

		      if (slowRunner == fastRunner) {
		          return true;
		      }
		  }
	}
	/*
	 * 
SR data:1 FR data:1
SR data:2 FR data:3
bfastRunner:1
fastRunner:2
SR data:3 FR data:2
SR data:4 FR data:2
SR data:2 FR data:4
colliions:1
fast->>collision point:1
	 */
	/*
	 * isPolindrome
	 * Iterative approach
	 */
	public boolean isPolindromeIterative(){
		
			Node<AnyType> fast = head;
			Node<AnyType> slow = head;
			Stack<Integer> stack = new Stack<Integer>();
			while(fast!= null && fast.next != null){
				stack.push((Integer) slow.data);
				slow = slow.next;
				fast = fast.next.next;
			}
			System.out.println("Stack:"+stack);
			System.out.println("slow:"+slow.toString());
			//skip mid element
			if(fast != null){
				slow = slow.next;
			}
			while(slow != null){
				if(stack.pop() != slow.data){
					return false;
				}
				slow = slow.next;
			}
		return true;
	}
	
	
	/*String representation of List
	 * toString()
	 */
	public String toString(){
		StringBuffer result = new StringBuffer();
		Node<AnyType> current = head;
		while(current != null){
			result.append(current.data).append("-->");
			current = current.next;
		}
	//	result.delete(result.length()-3, result.length());
		/*for(AnyType x : this){
			result.append(x + " ");
			System.out.println("list as string:"+result);
		}*/
		
		return result.toString();
	}
	
	/*
	 * Node Inner  Class
	 */
	
	private static class Node<AnyType> {
		public Node<AnyType> prev;
		private AnyType data;
		Node<AnyType> next;
		public Node(AnyType data,Node<AnyType> next, Node<AnyType> prev){
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
		
	}
	public static void main(String[] args) {

	/*	LinkedList<String> list = new LinkedList<String>();
		list.addFirst("S");
		list.addLast("w");
		list.addLast("a");
		list.addLast("m");
		list.addLast("y");
		list.addLast("m");
		list.addLast("y");*/
		
		LinkedList<Integer> list = new LinkedList<Integer>();
		
		//list.addFirst(8);list.addFirst(9);
	//	list.addFirst(7);
		//list.addFirst(10);
		//Node<Integer> cycle= new Node<Integer>(21,null);
	//	list.addLast(cycle);
	/*	list.addLast(1);
		list.addLast(2);
		list.addLast(3);
		list.addLast(4);
	//	list.addLast(1);
		list.addLast(2);
		list.addLast(5);
	/*	list.addLast(6);
		list.addLast(7);
		list.addLast(8);
		list.addLast(9);
		list.addLast(10);
	//c	list.addLast(1);
	//c	list.addLast(2);
		list.addLast(3);
		list.addLast(4);
		list.addLast(5);
		list.addLast(6);
		list.addLast(7);
		list.addLast(8);
		list.addLast(9);
		list.addLast(10);
		list.addLast(1);
		list.addLast(2);
	list.addLast(3);
		list.addLast(4);
		list.addLast(5);
		list.addLast(6);
		list.addLast(7);
		list.addLast(8);
		list.addLast(9);
		list.addLast(10);*/
		
		//is polidrome
		list.addLast(3);
		list.addLast(2);
		list.addLast(1);
	//	list.addLast(0);
		list.addLast(1);
		list.addLast(2);
		list.addLast(3);
		
		//System.out.println("contains:"+list.contains(4));
		//System.out.println("RemoveFirst:"+list.removeFirst());
		//System.out.println("pos:"+list.get(1));
		//System.out.println("First:"+list.getFirst());
		//System.out.println("Last:"+list.getLast());
		
	//	list.deleteDuplicates();
		
	//System.out.println(list.deleteDuplicatesWithoutBuffer());
		System.out.println("contains:"+list.contains(2));
		//circular list
	//	partition(node)
		System.out.println("10,7,9,8,1,2,3,4,5,6");
		//delete(2);
	//	System.out.println("collision:"+list.findBegining());
		
	//	System.out.println("collisionsecond:"+list.findBeginingSeondApproach());
		
	//	boolean polindrome = list.isPolindromeIterative();
	//	System.out.println("is list  polindrome:"+polindrome);
	//	System.out.println("String list:"+list.toString());
		
		
		//Iterator<Integer> it = list.iterator();
		//while(it.hasNext())
		//System.out.println(it.next());
	}




	@Override
	public Iterator<AnyType> iterator() {
		// TODO Auto-generated method stub
		return new LinkedListIterator();
	}

}

/**
 *  use a doubly-linked list to store the history
 *  the history should support   
 *  	addToHead()
 *      removeLast()
 *      moveToHead(Node n)
 * To support moveToHead in O(n), doubly linked list is needed
 * 
 * Note: if in a linked list we have pointers head and tail, always check for update them. For e.g.  moveToHead(Node n) - if n == tail, need to update tail when move is done
 */

package algorithmfun;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRUCache{
	private Map<Integer, Node> cache;
	private HistoryList history;
	private int capacity;
	
	
	public static void main(String args[])
	{
		LRUCache cache = new LRUCache(2);

		cache.set(2, 1);
		cache.set(1, 1);
		System.out.println(cache.get(2));
		cache.set(4, 1);
		System.out.println(cache.get(1));
		System.out.println(cache.get(2));
	}

	public LRUCache(int capacity) {
		cache = new HashMap<Integer, Node>();
		history = new HistoryList();
		this.capacity = capacity;
	}

	public int get(int key) {
		if (cache.containsKey(key))
		{
			Node n = cache.get(key);
			history.moveToHead(n);
			return cache.get(key).value;
		}
		else
			return -1;
	}

	public void set(int key, int value) {
		if (cache.containsKey(key))
		{
			Node n = cache.get(key);
			history.moveToHead(n);
			n.value = value;
		}
		else
		{
			Node n = new Node();
			n.value = value;
			n.key = key;
			history.addToHead(n);
			cache.put(key, n);
			if (cache.size() > capacity)
			{
				int oldest = history.removeOldest();
				cache.remove(oldest);
			}
		}
	}
}

class HistoryList
{
	Node head;
	Node tail;
	
	public void addToHead(Node n){
		if (head == null)
		{
			head = n;
			tail = n;
			head.prev = tail;
			head.next = tail;
		}
		else
		{
			n.prev = tail;
			n.next = head;
			head.prev = n;
			tail.next = n;
			head = n;
		}
	}
	
	public void moveToHead (Node node){
		if (node == head)
			return;
		Node left = node.prev;
		Node right = node.next;
		left.next = right;
		right.prev = left;
		if (node == tail)
			tail = tail.prev;
		addToHead(node);
	}
	
	public int removeOldest (){ 
		if (head == null)
			return -1;
		if (head == tail)
		{
			int v =  head.value;
			head = null;
			tail = null;
			return v;
		}
		// head != tail
		Node left = tail.prev;
		Node right = tail.next;
		left.next = right;
		right.prev = left;
		int oldest = tail.key;
		tail = tail.prev;
		return oldest;
	}
	
}

class Node
{
	public int key;
	public int value;
	public Node prev;
	public Node next;
}

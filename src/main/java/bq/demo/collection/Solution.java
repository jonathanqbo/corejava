package bq.demo.collection;

import java.util.Stack;

/**
 * <b>  </b>
 *
 * <p>   </p>
 *
 * @author Jonathan Q. Bo (jonathan.q.bo@gmail.com)
 *
 * Created at Jun 12, 2015 7:55:55 PM
 *
 */
public class Solution {
	
	public static boolean isBalanced(String input){
		if(input == null || input.length() == 0)
			return true;
		
		char[] charArray = input.toCharArray();
		Stack<Character> stack = new Stack<>();
		for(int i = 0; i < charArray.length; i++){
			if(charArray[i] == '(' || charArray[i] == '['){
				stack.push(charArray[i]);
			}
			else if(charArray[i] == ')'){
				if(!stack.isEmpty() && stack.peek() == '(')
					stack.pop();
				else
					return false;
			}
			else if(charArray[i] == ']'){
				if(!stack.isEmpty() && stack.peek() == '[')
					stack.pop();
				else
					return false;
			}
		}
		
		if(stack.size() == 0)
			return true;
		else
			return false;
	}

	public static class LinkedListNode{
        int val;
        LinkedListNode next;
    
        LinkedListNode(int node_value) {
            val = node_value;
            next = null;
        }
    };
    
    
    public static LinkedListNode _insert_node_into_singlylinkedlist(LinkedListNode head, int val){
        if(head == null) {
            head = new LinkedListNode(val);
        }
        else {
            LinkedListNode end = head;
            while (end.next != null) {
                end = end.next;
            }
            LinkedListNode node = new LinkedListNode(val);
            end.next = node;
        }
        return head;
    }
    
    static LinkedListNode manipulateList(LinkedListNode root) {
    	
    	if(root == null)
    		return null;
    	
    	// find middle node
    	LinkedListNode node = root;
    	LinkedListNode middleNode = root;
    	while(node != null){
    		node = node.next;
    		middleNode = middleNode.next;
    		if(node != null){
    			node = node.next;
    		}
    	}
    	
    	// merge two linkedlist
    	LinkedListNode newHead = null;
    	LinkedListNode list1 = root;
    	LinkedListNode list2 = middleNode;
    	while(list2 != null){
    		newHead = _insert_node_into_singlylinkedlist(newHead, list1.val);
    		newHead = _insert_node_into_singlylinkedlist(newHead, list2.val);
    		
    		list1 = list1.next;
    		list2 = list2.next;
    	}
    	
    	if(list1 != middleNode)
    		newHead = _insert_node_into_singlylinkedlist(newHead, list1.val);
    	
    	return newHead;
    }
    
    public static void main(String[] args){
    	LinkedListNode head = null;
    	for(int i = 0; i < 3; i++)
    		head = _insert_node_into_singlylinkedlist(head, i);
    	
    	LinkedListNode node = head;
    	
    	LinkedListNode newlist = manipulateList(head);
    	
    	while(newlist != null){
    		System.out.print(newlist.val + ",");
    		newlist = newlist.next;
    	}
    	
//    	String str1 = "40[23(2([(])3)44]";
//    	System.out.println(isBalanced(str1));
    	
    }
}


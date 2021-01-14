package myqueue;

import java.util.Stack;

public class MyQueue {
	
	private Stack<Integer> stack1 = new Stack<>();
	private Stack<Integer> stack2 = new Stack<>();
	
	public void add(Integer x) {
		stack1.push(x);
	}
	
	public Integer remove() throws Exception {
		if (stack2.isEmpty()) {
			if (stack1.empty()) {
				throw new Exception("Empty!");
			}
			while (!stack1.empty()) {
				stack2.push(stack1.pop());
			}
		}
		return stack2.pop();
	}

}

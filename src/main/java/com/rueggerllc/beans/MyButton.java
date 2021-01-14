package com.rueggerllc.beans;

public class MyButton {
	
	private MyMouseListener listener;

	public void addListener(MyMouseListener listener) {
		this.listener = listener;
	}
	
	
	public void onClick(int x) {
		listener.doSomeWork(x);
	}
	
	
}

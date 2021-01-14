package com.rueggerllc.beans;

public class Foo {
	
	private int x = 42;
	
	public Foo() {
		System.out.println("FOO Constuctor BEGIN:" + x);
		x = 55;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

}

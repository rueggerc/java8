package com.rueggerllc.apps;

public class MyApp {
	
	private int foo = 42;
	
	
	static {
		System.out.println("Hello from static");
	}
	
	public MyApp() {
		// foo = 55;
	}
		
	public int getFoo() {
		return foo;
	}
	public void setFoo(int foo) {
		this.foo = foo;
	}

	public static void main(String args[]) {
		MyApp app = new MyApp();
		System.out.println(app.getFoo());
	}

}

package com.rueggerllc.threads.sums;

public class Summer implements Runnable {
	
	private int value = 0;
	
	private  void increment() {
		System.out.println("Incrementing");
		int tempValue = getValue();
		try {
			Thread.sleep((long)(Math.random()*500));
		} catch (Exception e) {
		}
		setValue(tempValue+1);
	}
	
	private synchronized void safeIncrement() {
		System.out.println("Safe Increment BEGIN");
		int tempValue = getValue();
		try {
			Thread.sleep((long)(Math.random()*500));
		} catch (Exception e) {
		}
		setValue(tempValue+1);
		System.out.println("Safe Increment END");
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	


	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			safeIncrement();
		}
	}
	
	public static void main(String[] args) {
		
		try {
			Summer summer = new Summer();
			Thread thread1 = new Thread(summer);
			Thread thread2 = new Thread(summer);
			Thread thread3 = new Thread(summer);
			
			thread1.start();
			thread2.start();
			thread3.start();
			
			thread1.join();
			thread2.join();
			thread3.join();
		
		System.out.println(summer.getValue());
		} catch (Exception e) {
			System.out.println("ERROR\n" + e);
		}
		
	}
	
	
	

}

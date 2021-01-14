package com.rueggerllc.threads.diners;

public class Philosopher implements Runnable {

	private String id;
	private Fork fork1;
	private Fork fork2;
	
	public Philosopher(String id, Fork fork1, Fork fork2) {
		this.id = id;
		this.fork1 = fork1;
		this.fork2 = fork2;
	}
	
	@Override
	public void run() {
		try {
			for (int i=0; i<3;i++) {
				fork1.pickUp(id);
				fork2.pickUp(id);
				eat();
				think();
				fork2.putDown(id);
				fork1.putDown(id);
			}
			System.out.println(String.format("Philosopher %s is Done",id));
			
		} catch (Exception e) {
			System.out.println("Error\n" + e);
		}
	}
	
	private void eat() throws Exception {
		System.out.println(String.format("Philosopher %s is Eating", id));
		Thread.sleep(500);
	}
	
	private void think() throws Exception {
		System.out.println(String.format("Philosopher %s is Thinking", id));
		Thread.sleep(1000);
	}
	
	

}

package com.rueggerllc.threads.diners;

public class Fork {
	
	private String id;
	private boolean available = true;
	
	public Fork(String id) {
		this.id = id;
	}
	
	
	public synchronized void pickUp(String philosopherId) throws Exception {
		while (!available) {
			wait();
		}
		available = false;
		System.out.println(String.format("Philosopher %s Picks Up Fork %s", philosopherId, id));
	}
	
	public synchronized void putDown(String philosopherId) throws Exception {
		while (available) {
			wait();
		}
		available = true;
		System.out.println(String.format("Philosopher %s Puts Down Fork %s", philosopherId, id));
		notifyAll();
	}

}

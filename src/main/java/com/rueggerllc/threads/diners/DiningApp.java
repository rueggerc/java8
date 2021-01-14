package com.rueggerllc.threads.diners;

public class DiningApp {
	
	public void execute() throws Exception {
		
		System.out.println("HERE WE GO DINERS");
		
		Fork fork1 = new Fork("1");
		Fork fork2 = new Fork("2");
		Fork fork3 = new Fork("3");
		Fork fork4 = new Fork("4");
		
		
		Thread thread1 = new Thread(new Philosopher("A", fork1, fork2));
		Thread thread2 = new Thread(new Philosopher("B", fork2, fork3));
		Thread thread3 = new Thread(new Philosopher("C", fork3, fork4));
		Thread thread4 = new Thread(new Philosopher("D", fork1, fork4));
		
		System.out.println("START THREADS");
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
				
		thread1.join();
		thread2.join();
		thread3.join();
		thread4.join();

	}

}

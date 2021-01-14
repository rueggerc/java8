package com.rueggerllc.threads.messagequeue;

public class Receiver implements Runnable {

	private MessageQueue messageQueue;
	
	public Receiver(MessageQueue messageQueue) {
		this.messageQueue = messageQueue;
	}
	

	@Override
	public void run() {
		String packet = "";
		while (!(packet=messageQueue.receive()).equals("END")) {
			System.out.println("RECEIVER GOT PACKET=" + packet);
		}
		System.out.println("RECEIVER DONE");
	}
	
	
	
}

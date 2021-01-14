package com.rueggerllc.threads.messagequeue;

public class Sender implements Runnable {
	
	private MessageQueue messageQueue;
	private String[] packets = {"packet1", "packet2", "packet3", "END"};
	
	public Sender(MessageQueue messageQueue) {
		this.messageQueue = messageQueue;
	}
	

	@Override
	public void run() {
		for (String packet : packets) {
			messageQueue.send(packet);
			System.out.println("SENDER SENT PACKET=" + packet);
			sleep();
		}
		System.out.println("SENDER DONE");
	}
	
	private void sleep() {
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
		}
	}
	
	

}

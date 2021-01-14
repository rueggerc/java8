package com.rueggerllc.threads.messagequeue;

public class MessageQueueApp {
	
	public static void main(String args[]) {
		try {
			
			MessageQueue messageQueue = new MessageQueue();
			Thread senderThread = new Thread(new Sender(messageQueue));
			Thread receiverThread = new Thread(new Receiver(messageQueue));
			senderThread.start();
			receiverThread.start();
			
		} catch (Exception e) {
			System.out.println("ERROR\n" + e);
		}		
	}

}

package com.rueggerllc.threads.messagequeue;

public class MessageQueue {
	
	private String packet;
     
    // True if receiver should wait
    // False if sender should wait
    private boolean queueAvailableForSending = true;
  
    public synchronized void send(String packet) {
        while (!queueAvailableForSending) {
            try {
            	System.out.println("SEND WAIT");
                wait();
            } catch (InterruptedException e)  {
                Thread.currentThread().interrupt(); 
                System.err.println("Thread interrupted\n" + e); 
            }
        }
        queueAvailableForSending = false;
        this.packet = packet;
        notifyAll();
    }
  
    public synchronized String receive() {
        while (queueAvailableForSending) {
            try {
            	System.out.println("RECEIVE WAIT");
                wait();
            } catch (InterruptedException e)  {
                Thread.currentThread().interrupt(); 
                System.err.println("Thread interrupted\n" + e); 
            }
        }
        queueAvailableForSending = true;
        notifyAll();
        return packet;
    }	

}

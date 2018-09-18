package com.thread;

public class InlineThread {
    
    public static void main(String[] args){
	
	Thread t1 = new Thread1();
	t1.start();
	
	Thread t2 = new Thread(new Thread2());
	t2.start();
	
	new Thread(
		new Runnable(){
		public void run(){
		    System.out.println("thread 3 starts");
		}
		}
		).start();
	
	new Thread(){
	    public void run(){
		System.out.println("thread 4 starts");
	    }
	}.start();
    }

}

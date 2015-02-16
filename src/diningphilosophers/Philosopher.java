package diningphilosophers;

import java.util.Date;

public class Philosopher implements Runnable {
	
	@SuppressWarnings("unused")
	private int id;
	private String name;
	private int eatingTimeMilliseconds;
	private Fork leftFork;
	private Fork rightFork;	
	
	private int eatingCount;
	private double timeSpentEating;
	private long thinkingTime;
	
	public Philosopher(int d, String name, int eatingTimeMilliseconds, Fork left, Fork right) {
		this.setName(name);
		this.setEatingTimeMilliseconds(eatingTimeMilliseconds);
		leftFork = left;
		rightFork = right;
		setEatingCount(0);
		setTimeSpentEating(0.0);
		thinkingTime = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEatingTimeMilliseconds() {
		return eatingTimeMilliseconds;
	}

	public void setEatingTimeMilliseconds(int eatingTimeMilliseconds) {
		this.eatingTimeMilliseconds = eatingTimeMilliseconds;
	}
	
	public void acquireForks() throws InterruptedException {
		 while(true) {
	            // Acquire locks
	            
	            boolean gotLeftFork = false;
	            boolean gotRightFork = false;
	            
	            try {
	            	gotLeftFork = leftFork.tryLock();
	            	gotRightFork = rightFork.tryLock();
	            }
	            finally {
	                if(gotLeftFork && gotRightFork) {
	                    return;
	                }
	                
	                if(gotLeftFork) {
	                	leftFork.unlock();
	                }
	                
	                if(gotRightFork) {
	                	rightFork.unlock();
	                }
	            }
	            
	            // Locks not acquired
	            Thread.sleep(1);
	        }
	}
	
	public void releaseForks() {
		leftFork.unlock();
        rightFork.unlock();
	}
	
	@Override
	public void run() {	
		Date date = null;
		long time1 = 0;
		long time2 = 0;
		
		for (;!App.end;) {
			try {
				acquireForks(); //Get forks
				date = new Date();
				time1 = date.getTime();
				if (time2 == 0) time1 = 0;
				thinkingTime += (time1 - time2);
				System.out.println(name + " is eating");
				eatingCount++;
				timeSpentEating += eatingTimeMilliseconds;
				Thread.sleep(eatingTimeMilliseconds); //eat 
				releaseForks();
				date = new Date();
				time2 = date.getTime();
				System.out.println(name + " is thinking");
				Thread.sleep(1); //think 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public int getEatingCount() {
		return eatingCount;
	}

	public void setEatingCount(int eatingCount) {
		this.eatingCount = eatingCount;
	}

	public double getTimeSpentEating() {
		return timeSpentEating;
	}

	public long getThinkingTime() {
		return thinkingTime;
	}
	
	public void setTimeSpentEating(double timeSpentEating) {
		this.timeSpentEating = timeSpentEating;
	}

}

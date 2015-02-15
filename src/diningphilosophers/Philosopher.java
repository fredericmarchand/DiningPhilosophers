package diningphilosophers;

public class Philosopher implements Runnable {
	
	@SuppressWarnings("unused")
	private int id;
	private String name;
	private int eatingTimeMilliseconds;
	private Fork leftFork;
	private Fork rightFork;	
	
	private int eatingCount;
	private double timeSpentEating;
	
	public Philosopher(int id, String name, int eatingTimeMilliseconds, Fork left, Fork right) {
		this.setName(name);
		this.setEatingTimeMilliseconds(eatingTimeMilliseconds);
		leftFork = left;
		rightFork = right;
		setEatingCount(0);
		setTimeSpentEating(0.0);
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
		for (int i = 0; i < 20; ++i) {
			try {
				acquireForks(); //Get forks
				System.out.println(name + " is eating");
				eatingCount++;
				timeSpentEating += eatingTimeMilliseconds;
				Thread.sleep(eatingTimeMilliseconds); //eat 
				releaseForks();
				System.out.println(name + " is thinking");
				Thread.sleep(eatingTimeMilliseconds); //think 
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

	public void setTimeSpentEating(double timeSpentEating) {
		this.timeSpentEating = timeSpentEating;
	}

}

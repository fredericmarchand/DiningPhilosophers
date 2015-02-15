package diningphilosophers;

public class Philosopher implements Runnable {

	public static final int THINKING = 0;
	public static final int EATING = 1;
	public static final int GETTING_FORKS = 2;
	
	private int id;
	private String name;
	private int eatingTimeMilliseconds;
	@SuppressWarnings("unused")
	private Fork leftFork;
	@SuppressWarnings("unused")
	private Fork rightFork;
	private int state;
	
	
	private int eatingCount;
	private double timeSpentEating;
	
	public Philosopher(int id, String name, int eatingTimeMilliseconds, Fork left, Fork right) {
		this.setName(name);
		this.setEatingTimeMilliseconds(eatingTimeMilliseconds);
		leftFork = left;
		rightFork = right;
		state = THINKING;
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
	
	public void acquireFork(Fork fork) {
		for (;;) {
			if (fork.getStatus().get() == Fork.NOT_TAKEN) {
				fork.getStatus().set(id);
				return;
			}
			else {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void releaseFork(Fork fork) {
		fork.getStatus().set(Fork.NOT_TAKEN);
	}

	@Override
	public void run() {
		for (;;) {
			if (state == THINKING) {
				state = GETTING_FORKS;
			}
			else {
				if () {
					acquireFork(leftFork);
					acquireFork(rightFork);
					eatingCount++;
					try {
						Thread.sleep(eatingTimeMilliseconds);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					releaseFork(leftFork);
					releaseFork(rightFork);
					state = THINKING;
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				else {
					try {
						Thread.sleep(eatingTimeMilliseconds);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
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

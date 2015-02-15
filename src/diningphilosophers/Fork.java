package diningphilosophers;

import java.util.concurrent.locks.ReentrantLock;

public class Fork extends ReentrantLock {

	private static final long serialVersionUID = 1L;
	
	private int id;
	
	public Fork(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}	
}

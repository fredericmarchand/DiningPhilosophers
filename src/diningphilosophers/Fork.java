package diningphilosophers;

import java.util.concurrent.atomic.AtomicInteger;

public class Fork {

	public static final int NOT_TAKEN = -1;
	
	private AtomicInteger status; 
	private int id;
	
	public Fork(int id) {
		status = new AtomicInteger(NOT_TAKEN);
		this.setId(id);
	}

	public AtomicInteger getStatus() {
		return status;
	}

	public void setStatus(AtomicInteger status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}

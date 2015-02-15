package diningphilosophers;

import java.util.ArrayList;

public class App {
	
	public static void main (String args[]) {
		int numPhilosophers = Integer.parseInt(args[0]);
		int eatingTimeMilliseconds = Integer.parseInt(args[1]);
		
		ArrayList<Philosopher> philosophers = new ArrayList<Philosopher>();
		ArrayList<Fork> forks = new ArrayList<Fork>();
		
		//Create forks
		for (int i = 0; i < numPhilosophers; ++i) {
			forks.add(new Fork(i));
		}
		
		//Create philosophers and assign left/right forks
		for (int i = 0; i < numPhilosophers; ++i) {
			philosophers.add(new Philosopher(i, "Philosopher-" + i, eatingTimeMilliseconds, forks.get(i), forks.get((i+1)%numPhilosophers)));
			System.out.println(philosophers.get(i).getName());
		}
		
		//Start threads
		for (Philosopher p : philosophers) {
			Thread thread = new Thread(p);
			thread.start();
		}
		
		for (Philosopher p : philosophers) {
            System.out.printf(p.getName() + " ate " + p.getEatingCount() + " times and spent " + p.getTimeSpentEating() + "ms eating");
		}
		
	}
}

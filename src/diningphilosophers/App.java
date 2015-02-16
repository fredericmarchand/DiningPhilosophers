package diningphilosophers;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
	
	public static boolean end;
	
	public static void main (String args[]) throws InterruptedException {
		int numPhilosophers = Integer.parseInt(args[0]);
		int eatingTimeMilliseconds = Integer.parseInt(args[1]);
		App.end = false;
		
		ArrayList<Philosopher> philosophers = new ArrayList<Philosopher>();
		ArrayList<Fork> forks = new ArrayList<Fork>();
		ArrayList<Thread> threads = new ArrayList<Thread>();
		
		//Create forks
		for (int i = 0; i < numPhilosophers; ++i) {
			forks.add(new Fork(i));
		}
		
		//Create philosophers and assign left/right forks
		for (int i = 0; i < numPhilosophers; ++i) {
			philosophers.add(new Philosopher(i, "Philosopher-" + i, eatingTimeMilliseconds, forks.get(i), forks.get((i+1)%numPhilosophers)));
		}
		
		//Press enter to stop the program
		Thread kill = new Thread() {
            @Override
            public void run()
            {
            	Scanner sc = new Scanner(System.in);
            	sc.nextLine();
            	sc.close();
            	App.end = true;
            }
        };
        kill.setPriority(Thread.MAX_PRIORITY);
        kill.start();
		
		//Start threads
		for (Philosopher p : philosophers) {
			Thread thread = new Thread(p);
			threads.add(thread);
			thread.start();
		}
        
        kill.join();

		for (Thread t: threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for (Philosopher p : philosophers) {
            System.out.println(p.getName() + " ate " + p.getEatingCount() + " times and spent " + 
            					p.getTimeSpentEating() + "ms eating and " + p.getThinkingTime() + "ms thinking");
		}		
	}
}

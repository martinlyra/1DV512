import java.util.ArrayList;

/**
 * Runnable service for detecting and resolving deadlocks. Can be configured
 * to abort simulation instead of trying to dissolve deadlocks.
 * 
 * Has to be run on a separate thread.
 * @author Martin Lyrå
 *
 */
public class DeadlockResolver implements Runnable {

	private final DiningPhilosopher master; // Dependency injection
	private final boolean debug;
	/*
	 * Resolve deadlocks to continue simulation when true
	 * Otherwise alerts the master thread to abort simulation on event of a deadlock (when false).
	 */
	private final boolean resolveLocks; 
	
	private int deadlockCount = 0;
	
	DeadlockResolver(DiningPhilosopher master, boolean resolveLocks)
	{
		this.master = master;
		this.resolveLocks = resolveLocks;
		debug = master.DEBUG;
	}
	
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				if (isDeadlocked()) {
					/*
					 * If true, solve deadlocks to continue simulation
					 */
					if (resolveLocks)
						tryResolveDeadlock();
					/*
					 * Otherwise report it and abort simulation
					 */
					else
					{
						System.out.printf("*** Deadlock detected - Stopping ***\n");
						master.stop();
						return;
					}
				}
				Thread.sleep(1); // Wait 1 ms before checking again.
			}
		}
		catch (InterruptedException e)
		{
			
		}
		finally {
			/*
			 * Report statistics when set to resolve deadlocks
			 */
			if (resolveLocks)
				System.out.printf("*** %d deadlocks resolved ***\n", deadlockCount);
		}
	}
	
	/**
	 * Determine if all philosophers are in a deadlock. When all the philosophers
	 * had grabbed their left chopsticks but waiting for their right ones to be available.
	 * @return
	 */
	private boolean isDeadlocked()
	{
		for (Philosopher p : master.getPhilosophers())
			if (!p.isWaiting())
				return false;
		return true;
	}
	
	/**
	 * Attempt to fix deadlocks. Every 2nd Philosopher in the list of Philosophers
	 * are made to surrender all their sticks, available to others who need them.
	 * 
	 * Example when simulating 5 philosophers at once:
	 * At first call, the 2nd, and 4th are affected.
	 * Second call, 1st, 3rd, and 5th are affected. 
	 * Rinse and repeat for the next call.
	 * 
	 * Only called when resolveLocks is set to true
	 */
	private void tryResolveDeadlock()
	{
		if (debug)
			System.out.printf("*** Deadlock %d detected ***\n", deadlockCount);
		
		ArrayList<Philosopher> philosophers = master.getPhilosophers();
		int size = philosophers.size();
		
		for (int i = deadlockCount%2; i < size; i += 2)
			philosophers.get(i).releaseAllLocks();
		
		deadlockCount++;
	}
	
}

import java.util.Random;

public class Philosopher implements Runnable {
	
	private int id;
	
	private final ChopStick leftChopStick;
	private final ChopStick rightChopStick;
	
	private Random randomGenerator = new Random();
	
	private int numberOfEatingTurns = 0;
	private int numberOfThinkingTurns = 0;
	private int numberOfHungryTurns = 0;

	private double thinkingTime = 0;
	private double eatingTime = 0;
	private double hungryTime = 0;
	
	private Thread workerThread; // a variable to save the current worker thread (set when run() is first executed)
	private final boolean debug; // the debug variable in DiningPhilosopher is not static
	
	/*
	 * An enumerator to keep track of current state the Philosopher is in
	 */
	private enum Intent {
		THINK,
		HUNGRY,
		EAT;
		
		public String toString()
		{
			switch(this){
			case THINK: return "Thinking";
			case EAT:	return "Eating";
			case HUNGRY:return "Hungry";
			}
			return "Nothing";
		}
	}
	
	public Philosopher(int id, ChopStick leftChopStick, ChopStick rightChopStick, int seed, boolean debug) {
		this.id = id;
		this.leftChopStick = leftChopStick;
		this.rightChopStick = rightChopStick;
		
		/*
		 * set the seed for this philosopher. To differentiate the seed from the other philosophers, we add the philosopher id to the seed.
		 * the seed makes sure that the random numbers are the same every time the application is executed
		 * the random number is not the same between multiple calls within the same program execution 
		 */
		
		randomGenerator.setSeed(id+seed);
		
		this.debug = debug;
	}
	public int getId() {
		return id;
	}

	public double getAverageThinkingTime() {
		return thinkingTime / numberOfThinkingTurns;
	}

	public double getAverageEatingTime() {
		return eatingTime / numberOfEatingTurns;
	}

	public double getAverageHungryTime() {
		return hungryTime / numberOfHungryTurns;
	}
	
	public int getNumberOfThinkingTurns() {
		return numberOfThinkingTurns;
	}
	
	public int getNumberOfEatingTurns() {
		return numberOfEatingTurns;
	}
	
	public int getNumberOfHungryTurns() {
		return numberOfHungryTurns;
	}

	public double getTotalThinkingTime() {
		return thinkingTime;
	}

	public double getTotalEatingTime() {
		return eatingTime;
	}

	public double getTotalHungryTime() {
		return hungryTime;
	}
	
	@Override
	public void run() {	
		// Initialize
		workerThread = Thread.currentThread();
		
		long timeStamp = 0;
		Intent intent = Intent.THINK;
		
		// Actual work
		try {
			while (!Thread.interrupted()){
				// Print the next action as a major event
				if (debug)
					System.out.printf("Philosopher %d is %s\n", id, intent);
				
				// Get a new random period of time (1-1000 ms)and save a time-stamp
				long time = nextTime();
				timeStamp = System.currentTimeMillis();
				
				switch (intent){
					/*
					 * Thinking
					 * 
					 * Idle state before proceeding to Eating state
					 */
					case THINK: {
						numberOfThinkingTurns++;
						
						Thread.sleep(time);
						
						thinkingTime += time;
						intent = Intent.HUNGRY;
						break;
					}
					/*
					 * Hungry
					 * 
					 * While left and right chopsticks aren't being used by the philosopher,
					 * try locking both. 
					 * When a deadlock occurs and steps in to resolve it assure that
					 * philosopher has both, else restart and call dibs on the remainder
					 * 
					 * When successful, proceed to Eating state
					 */
					case HUNGRY: {
						numberOfHungryTurns++;
						
						while (!leftChopStick.isUsedBy(workerThread) && !rightChopStick.isUsedBy(workerThread)) {
							while(!leftChopStick.pickUp());
								Thread.sleep(5);
							while(!rightChopStick.pickUp());
								Thread.sleep(5);
						}
						
						/*
						 * Since the time is not random between 1-1000, calculate
						 * the elapsed time since last saved time-stamp.
						 */
						time = System.currentTimeMillis() - timeStamp;
						
						hungryTime += time;
						intent = Intent.EAT;
						break;
					}
					/*
					 * Eating
					 * 
					 * Release chopsticks after the thread sleep.
					 * 
					 * Return back to thinking when done.
					 */
					case EAT: {
						numberOfEatingTurns++;
						
						Thread.sleep(time);
						
						releaseAllLocks();
						
						eatingTime += time;
						intent = Intent.THINK;
						break;
					}
				}
			}
		} 
		catch (InterruptedException e) {
			// When the thread is interrupted mid-sleep, add the time to the end result. 
			long it = System.currentTimeMillis() - timeStamp;
			switch (intent) {
				case EAT: 		eatingTime += it; break;
				case HUNGRY:	hungryTime += it; break;
				case THINK:		thinkingTime += it; break;
			}
			if (debug)
				System.out.printf("Philosopher %d got INTERRUPTED when %s for %d ms\n",
								id, intent, it);
		}
	}
	
	/**
	 * Determine whether the Philosopher is waiting for the right chopstick to be available
	 * while holding onto the left chopstick. Returns null when it is not run by a seperate
	 * thread.
	 * @return
	 */
	public boolean isWaiting()
	{
		
		if (workerThread == null) // Prevent NullPointerExceptions
			return false;
		return leftChopStick.isUsedBy(workerThread) && !rightChopStick.isUsedBy(workerThread);
	}
	
	/**
	 * Release "locks" on the chopsticks
	 */
	public void releaseAllLocks()
	{
		leftChopStick.putDown();
		rightChopStick.putDown();
	}
	
	/**
	 * Generate a period between 1 and 1000 ms
	 * @return
	 */
	private int nextTime()
	{
		return randomGenerator.nextInt(1000)+1;
	}
}

/*
 * Alternative dirty solution
public void run() {
	try {
		while (!Thread.interrupted()) {
			// Thinking
			if (debug)
				System.out.printf("Philosopher %d is THINKING\n", id);
			numberOfThinkingTurns++;
			
			long time = nextTime();
			Thread.sleep(time);
			thinkingTime += time;
			
			// Hungry
			if (debug)
				System.out.printf("Philosopher %d is HUNGRY\n", id);
			numberOfHungryTurns++;
			long ts = System.currentTimeMillis();
			
			synchronized (leftChopStick) {
				synchronized (rightChopStick) {
					// Eating
					if (debug)
						System.out.printf("Philosopher %d is EATING\n", id);
					numberOfEatingTurns++;
					
					time = nextTime();
					Thread.sleep(time);
					eatingTime += time;
				}
			}
			
			hungryTime += System.currentTimeMillis() - ts;
		}
	} catch (Exception e)
	{
		
	}
}*/

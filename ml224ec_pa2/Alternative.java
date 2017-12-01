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
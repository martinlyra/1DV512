public class ChopStick{
	private final int id;
	
	public ChopStick(int id) {
		this.id = id;
	}
	
	/* TODO
	 * Implement the pickup and put down chopstick logic
	 * Please note that the same chopstick can not be picked up by more than one philosopher at a time.
	 */
	
	public int getId()
	{
		return id;
	}
	
	/*
	 * Implementation heavily relies on Thread.currentThread()
	 * Inspired by how wait and notify() work in theory.
	 * 
	 * When the first thread to call dibs on the Chopstick, it is saved
	 * to the variable 'activeUser' (in 'pickUp()', when no longer used. It is nullified
	 * in the function 'putDown()' when executed by the same thread as
	 * 'activeUser'.
	 */
	private Thread activeUser;
	
	public boolean isUsedBy(Thread t)
	{
		return activeUser == t;
	}
	
	/**
	 * Pick up the Chopstick. Executing thread is accessed by Thread.currentThread()
	 * Active user of the stick is set as the executing thread when not being used by 
	 * an other thread.
	 * 
	 * Returns true when the active user has been set to the executing thread, and when
	 * the executing thread IS the active user. Otherwise false when it is already in use
	 * by an other thread. In other words; unavailable.
	 * @return
	 */
	public synchronized boolean pickUp()
	{
		if (activeUser == null)
		{
			activeUser = Thread.currentThread();
			return true;
		}
		if (Thread.currentThread() == activeUser)
			return true;
		return false;
	}
	
	/**
	 * Releases the chopstick for an other thread when called by the 
	 * same thread as the current owner thread.
	 */
	public synchronized void putDown()
	{
		if (activeUser == Thread.currentThread())
			activeUser = null;
	}
}

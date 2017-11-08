/*
 * File:	Process.java 
 * Course: 	Operating Systems
 * Code: 	1DV512
 * Author: 	Suejb Memeti
 * Date: 	November, 2017
 */

public class Process {
	private int processId;
	private int arrivalTime;
	private int burstTime;
	private int remainingBurstTime;
	private int completedTime;
	private int turnaroundTime;
	private int waitingTime;
	private boolean isScheduled;

	public Process(int processId, int arrivalTime, int burstTime) {
		this.processId = processId;
		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;
		this.remainingBurstTime = burstTime;
		
		this.isScheduled = false;
	}


	public void setRemainingBurstTime(int remainingBurstTime) {
		this.remainingBurstTime = remainingBurstTime;
		
		if(this.remainingBurstTime == 0)
			setIsScheduled(true);
	}
	
	public void setCompletedTime(int completedTime) {
		this.completedTime = completedTime;
	}

	public void setTurnaroundTime(int turnaroundTime) {
		this.turnaroundTime = turnaroundTime;
	}

	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}
	
	public void setIsScheduled(boolean isScheduled) {
		this.isScheduled = isScheduled;
	}
	
	public int getArrivalTime(){
		return arrivalTime;
	}
	
	public int getBurstTime(){
		return burstTime;
	}
	
	public int getRemainingBurstTime(){
		return remainingBurstTime;
	}
	
	public int getCompletedTime(){
		return completedTime;
	}
	
	public int getTurnaroundTime(){
		return turnaroundTime;
	}
	
	public int getWaitingTime(){
		return waitingTime;
	}
	
	public int getProcessId(){
		return processId;
	}
	
	public boolean isScheduled(){
		return isScheduled;
	}
}
/*
 * File:	RR.java
 * Course: 	Operating Systems
 * Code: 	1DV512
 * Author: 	Suejb Memeti
 * Date: 	November, 2017
 */

/*
 * Implementation
 * Author:	Martin Lyrå
 * Date:	8th November, 2017
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class RR{

	// The list of processes to be scheduled
	public ArrayList<Process> processes;

	// the quantum time - which indicates the maximum allowable time a process can run once it is scheduled
	int tq;

	// keeps track of which process should be executed next
	public Queue<Process> schedulingQueue;
	
	// List to keep all data necessary for an rudimentary Gantt Chart
	private ArrayList<String> eventList;

	// Class constructor
	public RR(ArrayList<Process> processes, int tq) {
		schedulingQueue = new LinkedList<Process>();
		this.processes = processes;
		this.tq = tq;
		
		eventList = new ArrayList<String>();
	}

	public void run() {		
		// Obligatory sorting by time of arrival
		processes.sort((p1, p2) -> p1.getArrivalTime() - p2.getArrivalTime());
		
		// Make a work copy of list, so enabling us to access elements and modify the list
		ArrayList<Process> pending = (ArrayList<Process>) processes.clone();
		
		Process p = null;
		int ct = 0;
		while (!pending.isEmpty() || !schedulingQueue.isEmpty() || p != null)
		{			
			// Check for new arrivals by comparing current time to expected time of arrival. Add arrivals to worker queue.
			for (Iterator<Process> it = pending.iterator(); it.hasNext();)
			{
				Process pp = it.next();
				if (ct >= pp.getArrivalTime()) 
				{
					schedulingQueue.add(pp);
					it.remove();
				}
			}
			
			// Add previous process if not complete
			if (p != null && p.getRemainingBurstTime() > 0)
				schedulingQueue.add(p);
			
			p = schedulingQueue.poll();
			
			// Idle process
			if (p == null)
			{
				ct++;
				eventList.add(String.format("IDLE\t| %d - %d", ct-1, ct));
				continue;
			}
			// else, we've got a process to work on
			int bt = p.getRemainingBurstTime();
			
			// Active time, time spent "on the CPU"
			int act = bt;
			if (act > tq)
				act = tq;
			ct += act;
			
			p.setRemainingBurstTime(bt - act);
			
			eventList.add(String.format("P%d\t| %d - %d", p.getProcessId(), ct - act, ct));
			
			// If process is completed, compile the statistics
			if (p.getRemainingBurstTime() < 1)
			{	
				p.setCompletedTime(ct);
				p.setTurnaroundTime(p.getCompletedTime() - p.getArrivalTime());
				p.setWaitingTime(p.getTurnaroundTime() - p.getBurstTime());
				p = null;
			}
		}
		
		// Uncomment these for more output verbosity
		//printProcesses();
		//printGanttChart();
	}

	public void printProcesses() {
		StringBuilder sb = new StringBuilder();
		sb.append("Process list\n");
		sb.append("PID\t| AT\t| BT\t| CT\t| TAT\t| WT\n");
		// Process ID, Arrival Time, Burst Time, Complete Time, TurnAround Time, Waiting Time
		
		for (Process p : processes)
			sb.append(String.format("%d\t| %d\t| %d\t| %d\t| %d\t| %d\n", 
					p.getProcessId(),
					p.getArrivalTime(),
					p.getBurstTime(),
					p.getCompletedTime(),
					p.getTurnaroundTime(),
					p.getWaitingTime()
					));
		
		System.out.print(sb.toString());
	}

	public void printGanttChart(){
		// Not an actual chart displayed on an external window/graphing library
		// Displays as a 2-column list instead
		System.out.println("Gantt's Chart (list form)");
		for (String str : eventList)
			System.out.println(str);
	}
}

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
				eventList.add(String.format(";%d;%d", ct-1, ct));
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
			
			eventList.add(String.format("%d;%d;%d", p.getProcessId(), ct - act, ct));
			
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
		ArrayList<String> list = (ArrayList<String>) eventList.clone();
		
		StringBuilder output = new StringBuilder();
		
		ArrayList<StringBuilder> lines = new ArrayList<StringBuilder>();
		for (int i = 0; i < processes.size(); i++)
			lines.add(new StringBuilder());
		
		// Every dot = 1 time unit, comma for each 5th time unit
		output.append(String.format("TQ = %d\t. ....,....,....,....,....,....,....,\n", tq));
		
		for (String str : list)
		{
			if (str.startsWith(";")) continue; // Do not parse IDLE procs
			
			String[] parts = str.split(";");
			int id = Integer.parseInt(parts[0]);
			StringBuilder sb = lines.get(id-1);
			
			int t0 = Integer.parseInt(parts[1]);
			int t1 = Integer.parseInt(parts[2]);
			
			for (int i = sb.length(); i < t1; i++)
				if (i >= t0 && i < t1)
					sb.append("=");
				else if ((i+1) % 5 == 0) // We would like have some nice formatting too (line for every 5th unoccupied space)...
					sb.append("|");
				else
					sb.append(" ");
		}
		
		// Post parsing formatting
		for (StringBuilder sb : lines)
			for (int i = sb.length(); i < 35; i++)
			{
				if ((i+1) % 5 == 0)
					sb.append("|");
				else 
					sb.append(" ");
			}
		
		for (int i = 0; i < lines.size(); i++)
			output.append(String.format("PID %d\t: %s\n", i+1, lines.get(i).toString()));
		output.append('\n');
		
		System.out.println(output);
	}
}

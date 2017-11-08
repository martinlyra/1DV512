
/*
 * File:	RRTest.java
 * Course: 	Operating Systems
 * Code: 	1DV512
 * Author: 	Suejb Memeti
 * Date: 	November, 2017
 */

import static org.junit.Assert.*;

import java.util.ArrayList;


public class RRTest {

	@org.junit.Test
	public void test1() {
		ArrayList<Process> listOfProcesses = new ArrayList<Process>();

		//expected results
		int[] processIds = new int[]{1, 2, 3, 4, 5, 6};
		int[] processCT  = new int[]{8, 18, 6, 9, 21, 19};
		int[] processTAT = new int[]{8, 17, 4, 6, 17, 13};
		int[] processWT  = new int[]{4, 12, 2, 5, 11, 10};

		//list of processes to be scheduled
		//the first method parameter is the process id
		//the second method parameter is the arrival time
		//the third method parameter is the burst time
		listOfProcesses.add(new Process(1, 0, 4));
		listOfProcesses.add(new Process(2, 1, 5));
		listOfProcesses.add(new Process(3, 2, 2));
		listOfProcesses.add(new Process(4, 3, 1));
		listOfProcesses.add(new Process(5, 4, 6));
		listOfProcesses.add(new Process(6, 6, 3));

		int tq = 2;

		RR scheduler = new RR(listOfProcesses, tq);
		scheduler.run();

		for (int i = 0; i < listOfProcesses.size(); i++) {
			assertEquals(processIds[i], listOfProcesses.get(i).getProcessId());
			assertEquals(processCT[i], listOfProcesses.get(i).getCompletedTime());
			assertEquals(processTAT[i], listOfProcesses.get(i).getTurnaroundTime());
			assertEquals(processWT[i], listOfProcesses.get(i).getWaitingTime());
		}
	}

	@org.junit.Test
	public void test2() {
		ArrayList<Process> listOfProcesses = new ArrayList<Process>();

		listOfProcesses.add(new Process(1, 0, 6));
		listOfProcesses.add(new Process(2, 1, 4));
		listOfProcesses.add(new Process(3, 2, 7));
		listOfProcesses.add(new Process(4, 4, 3));

		int[] processIds = new int[]{1, 2, 3, 4};
		int[] processCT  = new int[]{16, 12, 20, 17};
		int[] processTAT = new int[]{16, 11, 18, 13};
		int[] processWT  = new int[]{10, 7, 11, 10};

		int tq = 2;

		RR scheduler = new RR(listOfProcesses, tq);
		scheduler.run();

		for (int i = 0; i < listOfProcesses.size(); i++) {
			assertEquals(processIds[i], listOfProcesses.get(i).getProcessId());
			assertEquals(processCT[i], listOfProcesses.get(i).getCompletedTime());
			assertEquals(processTAT[i], listOfProcesses.get(i).getTurnaroundTime());
			assertEquals(processWT[i], listOfProcesses.get(i).getWaitingTime());
		}
	}

	@org.junit.Test
	public void test3() {
		ArrayList<Process> listOfProcesses = new ArrayList<Process>();

		listOfProcesses.add(new Process(1, 0, 6));
		listOfProcesses.add(new Process(2, 1, 4));
		listOfProcesses.add(new Process(3, 2, 7));
		listOfProcesses.add(new Process(4, 4, 3));

		int[] processIds = new int[]{1, 2, 3, 4};
		int[] processCT  = new int[]{12, 16, 20, 15};
		int[] processTAT = new int[]{12, 15, 18, 11};
		int[] processWT  = new int[]{6, 11, 11, 8};

		int tq = 3;

		RR scheduler = new RR(listOfProcesses, tq);
		scheduler.run();

		for (int i = 0; i < listOfProcesses.size(); i++) {
			assertEquals(processIds[i], listOfProcesses.get(i).getProcessId());
			assertEquals(processCT[i], listOfProcesses.get(i).getCompletedTime());
			assertEquals(processTAT[i], listOfProcesses.get(i).getTurnaroundTime());
			assertEquals(processWT[i], listOfProcesses.get(i).getWaitingTime());
		}
	}


	@org.junit.Test
	public void test4() {
		ArrayList<Process> listOfProcesses = new ArrayList<Process>();

		listOfProcesses.add(new Process(1, 0, 4));
		listOfProcesses.add(new Process(2, 2, 3));
		listOfProcesses.add(new Process(3, 1, 1));
		listOfProcesses.add(new Process(4, 3, 2));
		listOfProcesses.add(new Process(5, 4, 5));

		int[] processIds = new int[]{1, 3, 2, 4, 5};
		int[] processCT  = new int[]{10, 4, 7, 9, 15};
		int[] processTAT = new int[]{10, 3, 5, 6, 11};
		int[] processWT  = new int[]{6, 2, 2, 4, 6};

		int tq = 3;

		RR scheduler = new RR(listOfProcesses, tq);
		scheduler.run();

		for (int i = 0; i < listOfProcesses.size(); i++) {
			assertEquals(processIds[i], listOfProcesses.get(i).getProcessId());
			assertEquals(processCT[i], listOfProcesses.get(i).getCompletedTime());
			assertEquals(processTAT[i], listOfProcesses.get(i).getTurnaroundTime());
			assertEquals(processWT[i], listOfProcesses.get(i).getWaitingTime());
		}
	}

	@org.junit.Test
	public void test5() {
		ArrayList<Process> listOfProcesses = new ArrayList<Process>();

		listOfProcesses.add(new Process(1, 0, 18));
		listOfProcesses.add(new Process(2, 0, 5));
		listOfProcesses.add(new Process(3, 0, 7));


		int[] processIds = new int[]{1, 2, 3};
		int[] processCT  = new int[]{30, 10, 22};
		int[] processTAT = new int[]{30, 10, 22};
		int[] processWT  = new int[]{12, 5, 15};

		int tq = 5;

		RR scheduler = new RR(listOfProcesses, tq);
		scheduler.run();

		for (int i = 0; i < listOfProcesses.size(); i++) {
			assertEquals(processIds[i], listOfProcesses.get(i).getProcessId());
			assertEquals(processCT[i], listOfProcesses.get(i).getCompletedTime());
			assertEquals(processTAT[i], listOfProcesses.get(i).getTurnaroundTime());
			assertEquals(processWT[i], listOfProcesses.get(i).getWaitingTime());
		}
	}

	@org.junit.Test
	public void test6() {
		ArrayList<Process> listOfProcesses = new ArrayList<Process>();

		listOfProcesses.add(new Process(1, 0, 3));
		listOfProcesses.add(new Process(2, 3, 3));
		listOfProcesses.add(new Process(3, 4, 2));


		int[] processIds = new int[]{1, 2, 3};
		int[] processCT  = new int[]{3, 8, 7};
		int[] processTAT = new int[]{3, 5, 3};
		int[] processWT  = new int[]{0, 2, 1};

		int tq = 2;

		RR scheduler = new RR(listOfProcesses, tq);
		scheduler.run();

		for (int i = 0; i < listOfProcesses.size(); i++) {
			assertEquals(processIds[i], listOfProcesses.get(i).getProcessId());
			assertEquals(processCT[i], listOfProcesses.get(i).getCompletedTime());
			assertEquals(processTAT[i], listOfProcesses.get(i).getTurnaroundTime());
			assertEquals(processWT[i], listOfProcesses.get(i).getWaitingTime());
		}
	}

	@org.junit.Test
	public void test7() {
		ArrayList<Process> listOfProcesses = new ArrayList<Process>();

		listOfProcesses.add(new Process(1, 0, 1));
		listOfProcesses.add(new Process(2, 4, 2));
		listOfProcesses.add(new Process(3, 7, 3));


		int[] processIds = new int[]{1, 2, 3};
		int[] processCT  = new int[]{1, 6, 10};
		int[] processTAT = new int[]{1, 2, 3};
		int[] processWT  = new int[]{0, 0, 0};

		int tq = 2;

		RR scheduler = new RR(listOfProcesses, tq);
		scheduler.run();

		for (int i = 0; i < listOfProcesses.size(); i++) {
			assertEquals(processIds[i], listOfProcesses.get(i).getProcessId());
			assertEquals(processCT[i], listOfProcesses.get(i).getCompletedTime());
			assertEquals(processTAT[i], listOfProcesses.get(i).getTurnaroundTime());
			assertEquals(processWT[i], listOfProcesses.get(i).getWaitingTime());
		}
	}
}

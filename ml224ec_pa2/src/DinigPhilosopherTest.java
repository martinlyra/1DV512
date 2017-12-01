

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class DinigPhilosopherTest {

	@Test
	public void test1() throws InterruptedException {
		DiningPhilosopher dp = new DiningPhilosopher(); // create an instance to DiningPhilosopher class
		int simTime = 5000;
		int seed = 100;
		
		/*
		 * If DEBUG is set to true, your implementation should print in the console the major events that occur during the simulation 
		 * For instance, Philosopher_1 is THINKING, Philosopher_4 is EATING, Philosopher_3 is HUNGRY, Philosoper_2 released Chopstick_3, Philosopher_1 picked-up Chopstick_1, Deadlock detected …
		 */
		dp.DEBUG = false; 

		dp.initialize(simTime, seed); // initialize the required objects

		dp.start(); // start the simulation process

		ArrayList<Philosopher> philosophers = dp.getPhilosophers(); // get the philosophers
		
		dp.printTable();
		
		for (Philosopher p : philosophers) {
			assertTrue("The average eating times may not be correct!", p.getAverageEatingTime() > 10 && p.getAverageEatingTime() < 990);
			assertTrue("The average thinking times may not be correct!", p.getAverageThinkingTime() > 10 && p.getAverageThinkingTime() < 990);
			
			if(p.getId() == 0) {
				assertTrue("The average thinking time may not be correct!", Math.floor(p.getAverageThinkingTime()) == 693);
				assertTrue("The average eating time may not be correct!", Math.floor(p.getAverageEatingTime()) == 412);
			}
			else if(p.getId() == 1) {
				assertTrue("The average thinking time may not be correct!", Math.floor(p.getAverageThinkingTime()) == 315);
				assertTrue("The average eating time may not be correct!", Math.floor(p.getAverageEatingTime()) == 636);
			}
			else if(p.getId() == 2) {
				assertTrue("The average thinking time may not be correct!", Math.floor(p.getAverageThinkingTime()) == 571);
				assertTrue("The average eating time may not be correct!", Math.floor(p.getAverageEatingTime()) == 427);
			}
			else if(p.getId() == 3) {
				assertTrue("The average thinking time may not be correct!", Math.floor(p.getAverageThinkingTime()) == 335);
				assertTrue("The average eating time may not be correct!", Math.floor(p.getAverageEatingTime()) == 633);
			}
			else if(p.getId() == 4) {
				assertTrue("The average thinking time may not be correct!", Math.floor(p.getAverageThinkingTime()) == 313);
				assertTrue("The average eating time may not be correct!", Math.floor(p.getAverageEatingTime()) == 398);
			}
		}
	}
	
	@Test
	public void test2() throws InterruptedException {
		DiningPhilosopher dp = new DiningPhilosopher(); // create an instance to DiningPhilosopher class
		int simTime = 3000;
		int seed = 100;
		dp.DEBUG = false;

		dp.initialize(simTime, seed); // initialize the required objects

		dp.start(); // start the simulation process

		ArrayList<Philosopher> philosophers = dp.getPhilosophers(); // get the philosophers
		/*
		 * the following code prints a table where each row corresponds to one of the Philosophers, 
		 * columns correspond to the Philosopher ID (PID), average think time (ATT), average eating time (AET), average hungry time (AHT), number of thinking turns(#TT), number of eating turns(#ET), and number of hungry turns(#HT).
		 */
		
		dp.printTable();

		for (Philosopher p : philosophers) {
			
			assertTrue("The average eating times may not be correct!", p.getAverageEatingTime() > 10 && p.getAverageEatingTime() < 990);
			assertTrue("The average thinking times may not be correct!", p.getAverageThinkingTime() > 10 && p.getAverageThinkingTime() < 990);
			
			if(p.getId() == 0) {
				assertTrue("The average thinking time may not be correct!", Math.floor(p.getAverageThinkingTime()) == 894);
				assertTrue("The average eating time may not be correct!", Math.floor(p.getAverageEatingTime()) == 250);
			}
			else if(p.getId() == 1) {
				assertTrue("The average thinking time may not be correct!", Math.floor(p.getAverageThinkingTime()) == 421);
				assertTrue("The average eating time may not be correct!", Math.floor(p.getAverageEatingTime()) == 872);
			}
			else if(p.getId() == 2) {
				assertTrue("The average thinking time may not be correct!", Math.floor(p.getAverageThinkingTime()) == 774);
				assertTrue("The average eating time may not be correct!", Math.floor(p.getAverageEatingTime()) == 337);
			}
			else if(p.getId() == 3) {
				assertTrue("The average thinking time may not be correct!", Math.floor(p.getAverageThinkingTime()) == 416);
				assertTrue("The average eating time may not be correct!", Math.floor(p.getAverageEatingTime()) == 542);
			}
			else if(p.getId() == 4) {
				assertTrue("The average thinking time may not be correct!", Math.floor(p.getAverageThinkingTime()) == 370);
				assertTrue("The average eating time may not be correct!", Math.floor(p.getAverageEatingTime()) == 284);
			}
		}
	}
	
	@Test
	public void test3() throws InterruptedException {
		DiningPhilosopher dp = new DiningPhilosopher(); // create an instance to DiningPhilosopher class
		int simTime = 10000;
		int seed = 100;
		dp.DEBUG = false;
		
		dp.initialize(simTime, seed); // initialize the required objects

		dp.start(); // start the simulation process

		ArrayList<Philosopher> philosophers = dp.getPhilosophers(); // get the philosophers
		/*
		 * the following code prints a table where each row corresponds to one of the Philosophers, 
		 * columns correspond to the Philosopher ID (PID), average think time (ATT), average eating time (AET), average hungry time (AHT), number of thinking turns(#TT), number of eating turns(#ET), and number of hungry turns(#HT).
		 */
		dp.printTable();

		for (Philosopher p : philosophers) {
			
			assertTrue("The average eating times may not be correct!", p.getAverageEatingTime() > 10 && p.getAverageEatingTime() < 990);
			assertTrue("The average thinking times may not be correct!", p.getAverageThinkingTime() > 10 && p.getAverageThinkingTime() < 990);
			
			if(p.getId() == 0) {
				assertTrue("The average thinking time may not be correct!", Math.floor(p.getAverageThinkingTime()) == 531);
				assertTrue("The average eating time may not be correct!", Math.floor(p.getAverageEatingTime()) == 603);
			}
			else if(p.getId() == 1) {
				assertTrue("The average thinking time may not be correct!", Math.floor(p.getAverageThinkingTime()) == 429);
				assertTrue("The average eating time may not be correct!", Math.floor(p.getAverageEatingTime()) == 466);
			}
			else if(p.getId() == 2) {
				assertTrue("The average thinking time may not be correct!", Math.floor(p.getAverageThinkingTime()) == 610);
				assertTrue("The average eating time may not be correct!", Math.floor(p.getAverageEatingTime()) == 506);
			}
			else if(p.getId() == 3) {
				assertTrue("The average thinking time may not be correct!", Math.floor(p.getAverageThinkingTime()) == 285);
				assertTrue("The average eating time may not be correct!", Math.floor(p.getAverageEatingTime()) == 621);
			}
			else if(p.getId() == 4) {
				assertTrue("The average thinking time may not be correct!", Math.floor(p.getAverageThinkingTime()) == 352);
				assertTrue("The average eating time may not be correct!", Math.floor(p.getAverageEatingTime()) == 397);
			}
		}
	}

}

/*
 * @author Alex Acevedo
 * 
 * Clock Simulator is the main program for this project that takes in all the classes made
 * and prints out the drift times associated for each task.  Uses the given bag class to place 
 * in and pull the clock types with all its associated declared variables. 
 * 
 */

public class ClockSimulation {

	//Instantiates the data structure using the clock class and declares it as bag.
	private static Bag<Clock> bag;

	//Private time variables used to calculate the day, week, month, and year time in seconds
	private final static long SECONDS = 86400;
	private final static long WEEK = SECONDS * 7;
	private final static long MONTH = SECONDS * 30;
	private final static long YEAR = SECONDS * 365;


	//The main method for the program
	public static void main(String[] args) {

		//Creates a new Bag type called bag with the Clock class
		bag = new Bag<Clock>();

		//Adds each created class of clocks to the bag
		bag.add(new Sundial());
		bag.add(new CuckooClock());
		bag.add(new GrandfatherClock());
		bag.add(new AtomicClock());
		bag.add(new WristWatch());

		
		//Prints out the reset values before all clocks and time starts
		System.out.print("Times before start:\n");
		displayTime();

		//Prints out the time after one day has past with the drifts
		System.out.print("\nAfter one day:\n");
		timeSim(SECONDS);						

		//Prints out the time after a week has pass with the drifts
		System.out.print("\nAfter one week:\n");
		timeSim(WEEK);
		
		//Prints out the time after a month has pass with the drifts
		System.out.print("\nAfter one month:\n");
		timeSim(MONTH);

		//Prints out the time after a year has pass with the drifts
		System.out.print("\nAfter one year:\n");
		timeSim(YEAR);

	}

	//A for loop to go into the bag and displays it
	public static void displayTime() 
	{
		for (int i = 0; i < bag.getSize(); i++) 
		{
			
			bag.get(i).display();
			
		}
	}
	
	//A nested for loop that first goes into each clock at resets the time
	//then increments the tick based on the amount of time
	public static void timeSim(long timer) 
	{
		
		for (int i = 0; i < bag.getSize(); i++) 
		{
			
			bag.get(i).reset();
			for (int j = 0; j < timer; j++) 
			{
				
				bag.get(i).tick();
				
			}
		}
		
		//displays the new time added 
		displayTime();
	}


///for continuation see if i could add user inputs to determine how many weeks, months or years to calculate.


}

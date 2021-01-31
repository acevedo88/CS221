/*
 * @author Alex Acevedo
 * 
 * Child class from the Clock class that will create a Grand Father Clock time and drift variables.
 */

public class GrandfatherClock extends Clock{
	
	//Creates a private variable for the drift
	private final static double GRAND_DRIFT = 0.000347222;
	
	//Constructor that instantiates the type of clock and its drift.
	//Uses a super to call the parent class which is Clock().
	public GrandfatherClock()
	{
		//Uses super to call the parent class to set the clocktype and its drift
		super(ClockType.mechanical, GRAND_DRIFT);
	}

	//Sets the display for the clock type.
	@Override
	public void display() 
	{
		//prints out the information in a neat format with all information required
		System.out.printf("%-31s %-1s %.2f %-1s", getClockType() +" grandfather clock:", 
				"time [" + time.formattedTime() + "], total drift = " ,time.getTotalDrift() , "seconds\n");
		
	}

}

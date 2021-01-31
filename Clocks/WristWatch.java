/*
 * @author Alex Acevedo
 * 
 * Child class from the Clock class that will create a Wrist watch time and drift variables.
 */

public class WristWatch extends Clock{
	
	//Creates a private variable for the drift
	private final static double WRIST_DRIFT = 0.000034722;
	
	//Constructor that instantiates the type of clock and its drift.
	//Uses a super to call the parent class which is Clock().
	public WristWatch()
	{
		//Uses super to call the parent class to set the clocktype and its drift
		super(ClockType.digital, WRIST_DRIFT);
	}

	//Sets the display for the clock type.
	@Override
	public void display() 
	{
		//prints out the information in a neat format with all information required
		System.out.printf("%-31s %-1s %.2f %-1s", getClockType() +" wrist watch:", 
				"time [" + time.formattedTime() + "], total drift = " ,time.getTotalDrift() , "seconds\n");
		
	}

}

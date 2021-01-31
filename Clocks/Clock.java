/*
 * 
 * @author Alex Acevedo
 * 
 * Abstract Clock class that implements the Interface TimePiece. 
 * Has child classes that rely on this class.
 * 
 */


public abstract class Clock implements TimePiece{
	
	
	protected Time time;				//protected variable of Time.  Time class was given
	private ClockType clockType;		//Declares clockType as a private variable
	
	
	
	//Public enumerator that stores in various clock time keeping styles
	public enum ClockType
	{
		natural, mechanical, digital, quantum
	}

	
	//Constructor for the Clock method that takes in a clockType and drift double.
	public Clock(ClockType clockType, double drift)
	{
		this.clockType = clockType;
		time = new Time(0, 0, 0, drift);		//creates a new instance of Time
			
		
	}
	
	//Method used to return the clockType requested
	public ClockType getClockType()
	{
		return this.clockType;
	}
	
	//Method used to increment the time by using the Time() class
	@Override
	public void tick()
	{
		time.incrementTime();
	}
	
	//Method used to reset the start time when the user starts the program
	@Override
	public void reset()
	{
		time.resetToStartTime();
	}
	
	//An abstract method that does not return a value.
	@Override
	public abstract void display();

}

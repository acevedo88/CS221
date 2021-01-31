/*
 * @author Alex Acevedo
 * 
 * An interface class that does not return any values and is the top hierarchy of the classes
 * used in this project
 */
public interface TimePiece {

	//method that resets the timer but is not instantiated or returns anything in this interface
	public void reset();
	
	
	//Method that is used to tick the timer but is instantiated elsewhere
	public void tick();
	
	//Method that will display all the times and there drifts but is instantiated elsewhere
	public void display();
	
}

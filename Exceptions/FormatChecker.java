import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * @author Alex Acevedo 
 * 
 * FormatChecker is a program that will check a file to determine if the contents meet certain expectations.
 * It will throw exception errors based on what the found issue is.  I.E invalid files, incorrect dimensions, 
 * or input mismatch exceptions.  This program uses a new exception built by the instructors.
 * 
 *
 */
public class FormatChecker {

	public static void main(String[] args) {

		//Checks if the user inputed any information in the command line
		//Will print out template if not so
		if (args.length <= 0) 
		{

			System.err.println("FormatChecker file1 [file2 ... fileN]");

		} 


		//If the user inputed a valid command, the else statement will check the contents
		//to determine what to do next
		else 
		{

			//For loop will step through each command entry to determine what functions must be done
			for (int x = 0; x < args.length; x++) 
			{

				//Runs the method checkFomat at each iteration of x to determine if its correct and has
				//no exception.  If not correct and has errors, an exception will be thrown.
				checkFormat(args[x]);
				
			}

		}

	}
	
	
	//Method that will run and check the files inputed, then determine if its a file or not.
	//Will also check all other exceptions
	public static void checkFormat(String file) 
	{

		//Global variables used throughout the code.
		double[][] matrixGrid;		//a double array configuration that is not instantiate
		int rows;					//row number that will be used for the array position
		int cols;					//column number that will be used for the array position
		Scanner firstScan;			//used to scan the first integer of the matrix for rows
		Scanner secondScan;			//used to scan the second integer of the matrix for cols
		String matrixLines;			//will store the entire row of integers
		
		//Instantiates a new file based on the current x on the command line
		File files = new File(file);

		//Try - Catch methods used to throw exceptions if the files contain errors
		try {

			
			//Sets the first scan instance for the file
			firstScan = new Scanner(files);

			rows = firstScan.nextInt();			//Sets the first value of the matrix as rows
			cols = firstScan.nextInt();			//Sets the second value of the matrix as columns

			//Creates a grid based on the row and cols values
			matrixGrid = new double[rows][cols];

			int totalRows = 0;				//Instantiates total rows to 0 for incrementing. Used to find total row
			int totalCols = 0;				//Instantiates total cols to 0 for incrementing. Used to find total cols
			int colAmount;					//Used to determine the column amount when walking through the matrix
			int r = 0;						//Used for current row coordinate value in matrix
			int c = 0;						//Used for current cols coordinate value in matrix

			//While loop to check for a next line for the row section
			while (firstScan.hasNextLine()) 
			{

				//Will scan the first row of matrix values and the scan the column
				matrixLines = firstScan.nextLine();
				secondScan = new Scanner(matrixLines);

				//Sets the column amount to 0
				colAmount = 0;

				//Nested while loop to go through each column section
				while (secondScan.hasNext()) 
				{

					//Increments the column amount to determine its coordinate position
					colAmount++;

					//Scans the next value and stores it into a string
					String tokenValue = secondScan.next();

					//If statements checks if the dimensions expected are correct
					//If they aren't then it will throw the DimensionMismatchException
					if (r >= rows) 
					{

						System.out.println(file);
						throw new DimensionMismatchException();

					}

					//Stores tokenValue into its coordinate position then increments to the next column
					matrixGrid[r][c] = Double.parseDouble(tokenValue);
					c++;

					//Once the columns reaches the expected value, it will enter the if statement and
					//increment to the next row as well as set the c to 0 and increment total cols
					if (c >= cols) 
					{
						r++;
						c = 0;
						totalCols++;
					}

					//If statements checks if the dimensions expected are correct
					//If they aren't then it will throw the DimensionMismatchException
					if (colAmount > cols) 
					{

						System.out.println(file);
						throw new DimensionMismatchException();

					}
				}

				//Increments the total rows that will be used to determine DimensionMismatch issues
				totalRows++;
			}

			//Prints out the file name currently used
			System.out.println(file);


			//If any of the row or column values don't match their expected values it will enter the if statement
			 if ((totalRows - 1) != r && totalCols != c)
			{

				throw new DimensionMismatchException();

			}

			//If no issues occur, then the file is correct and will print valid
			else 
			{

				System.out.println("VALID" + "\n");

			}
		}


		//Catch statements used for each exception that occurs with the files.

		//DimensionMismatche is used to determine if the grid is in the correct value format stated in the file
		catch (DimensionMismatchException e) 
		{
			System.out.println(e.toString() + "\nInvalid" + "\n");

		} 

		//If the file is not in the correct format or cant be found, the this exception will be thrown
		catch (FileNotFoundException e) 
		{
			System.out.println(file + "\n" + e.toString() + "\nInvalid" + "\n");

		} 

		//If the row or col values are not integers, this expection will be thrown
		catch (InputMismatchException e) 
		{

			System.out.println(file + "\n" + e.toString() + "\nInvalid" + "\n");

		}

		//If a char value is in place of a int value in the matrix, this expection will be thrown
		catch (NumberFormatException e) 
		{
			System.out.println(file + "\n" + e.toString() + "\nInvalid" + "\n");

		} 
	}
	

}

package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class scoresDataEntry {
	
	// The following variables are used to access the text file, the score, a counter, and arrays that would be used to hold user names and their scores.
	private File dataFile = new File("userScores.txt");
	private int counter = 0;
	private int userscore;
	private String lineOfText;
	private String [][] userScoresArray;
	private String [] stringNames;
	private int [] userScoresNum;
	private String[][] userScoreList;
	private int location;

	// This is the constructor for this class.
	public scoresDataEntry() {

	}
	
	// This function is used to write the user's name and their score in the text file.
	public void writingScore(String name, int score) throws IOException
	{
		// This is used to append data to the end of the file.
		BufferedWriter writeFile = new BufferedWriter(new FileWriter(dataFile, true));
		userscore = score;
		try
		{
			// This block of code is used to write data on to the file.
			writeFile.write(name + " " + Integer.toString(score));
			writeFile.newLine();
			writeFile.close();
		}
		catch (IOException e)
		{
			System.out.println("Problem writing to file.");
			System.err.println("IOException: "+ e.getMessage());
		}
		
		// This is used to call the next function as it used to count the number of sets of data in the text file.
		countingScores();
	}
	
	// This function is used to count the number of sets of data in the text file.
	public void countingScores() throws FileNotFoundException {
		// The two lines of code allows us to read the data in the text file.
		FileReader fr = new FileReader(dataFile);
		BufferedReader readFile = new BufferedReader(fr);
		
		try
		{	
			// This while loop is used to read every single line that is presented.
			while ((lineOfText = readFile.readLine())!= null)
			{
				counter += 1;
			}
			readFile.close();
			fr.close(); 
		}
		catch (IOException e)
		{
			System.out.println("Problem reading file.");
			System.err.println("IOException: "+ e.getMessage());
		}
		
		// This would call the next function where we would take in the data from the text file and store into an array.
		readingScores();
	}
	
	// This is used to take in the data (user's name and score) from the text file and store into an array.
	public void readingScores() throws FileNotFoundException {
		// The two lines of code allows us to read the data in the text file.
		FileReader fr = new FileReader(dataFile);
		BufferedReader readFile = new BufferedReader(fr);
		
		// This is used create the array itself. This array is used to hold the scores from the data file.
		userScoresArray = new String[counter][2];
		int index = 0;
		try
		{
			// This while-loop is used to read every line from the line and to split the string into two, and then it would be added to a 2D array.
			// This while-loop will continue until it reaches a line that is null.
			while ((lineOfText = readFile.readLine())!= null)
			{
				String[] currentScoreArray = lineOfText.split(" ");
				userScoresArray[index] = currentScoreArray;
				index += 1;
			}
			readFile.close();
			fr.close(); 
		}
		catch (IOException e)
		{
			System.out.println("Problem reading file.");
			System.err.println("IOException: "+ e.getMessage());
		}
		
		// This would call a function that is used to sort the arrays from descending order.
		sortingAlgorithm();
	}
	
	// This function is used to sort the arrays from descending order.
	public void sortingAlgorithm() throws FileNotFoundException {
		// These two arrays are used to hold the names and scores separately.
		stringNames = new String[counter];
		userScoresNum = new int[counter];

		for (int rows = 0; rows < counter; rows++)
		{
			for (int cols = 0; cols < 2; cols++)
			{
				stringNames[rows] = userScoresArray[rows][0];
				userScoresNum[rows] = Integer.parseInt(userScoresArray[rows][1]);
			}
		}
		
		// This is a bubble sort algorithm. This algorithm is going to sort the data from the highest score to the lowest score.
		// This for-loop will run until all data is sorted.
		boolean done;
		for(int end = counter - 1; end > 0; end--)
		{
			done = true;
			for (int i = 0; i < end; i++)
			{
				if (userScoresNum[i] < userScoresNum[i+1])
				{
					done = false;
					int temp = userScoresNum[i + 1];
					String tempString = stringNames[i+1];
					userScoresNum[i + 1] = userScoresNum[i];
					stringNames[i+1] = stringNames[i];
					userScoresNum[i] = temp;
					stringNames[i] = tempString;
				}
			}
			if (done)
				break;
		}
		// This would call the last function, as it would be used to find the position of the current user in the leaderboard.
		searchingAlgorithm();
	}

	/* This is a binary search algorithm. What this algorithm is trying to do is to search through the sorted data set to find he position 
	 * of the user in the leaderboard. Once position is found, it would set the location to its leaderboard position.
	 */
	
	public void searchingAlgorithm() {
		// TODO Auto-generated method stub
		
		int start = 0;
		int end = userScoresNum.length - 1;
		int middle = 0;
		boolean found = false;
		while (start <= end && !found) {
			
			middle = (start + end) / 2;
			
			if (userScoresNum[middle] == userscore) {
				found = true;
				location = middle;
				break;
			}
			else if (userScoresNum[middle] > userscore) {
				start = middle - 1;
			}
			else {
				end = middle + 1;
			}
		}
	}
	
	// This function is used to return leaderboard position of the current user. 
	public int leaderBoardPosition() {
		return (location + 1);
	}
	
	// This function is used to return the array that has the various scores.
	public int[] scoresArray() {
		return userScoresNum;
	}
	
	// This function is used to return the array that has the various user names.
	public String[] namesArray() {
		return stringNames;
	}
}
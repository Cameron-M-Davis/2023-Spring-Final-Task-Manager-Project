import java.util.*;
import java.io.*;
import java.math.*;

public class ProgressBarTask extends BaseTask{
	//VARIABLES
	public ArrayList<Integer> percentages = new ArrayList<Integer>();
	public ArrayList<String>  itemNames   = new ArrayList<String>();
	//It's 12:48am 4/22/2023 and I just had a thought that the layout for this data
	// and for the Check list is Very similar and could have probably been made to be the same
	// kind of thing but like idk and it doesn't change anything for the better to figure it out
	// ..so yeah thanks for coming to my ted talk
	public int barLength = 20;


	//CONSTRUCTORS
	public ProgressBarTask(String title, String startDate, String endDate, String notImportant){
		//This constructor exists to let us programatically create this task without 
		// the user being asked to add fields at random
		super(title,startDate,endDate);
	}//
	
	public ProgressBarTask(String title, String startDate, String endDate){
		super(title,startDate,endDate);
		String inText = "";
		Scanner inScanner = new Scanner(System.in);
		boolean keepGoing = true;
		while(keepGoing){
			System.out.println("Please enter the name for the field you wish to add:");
			System.out.println("Fields in task: "+this.itemNames.size());
			System.out.println("(presss enter to stop adding fields)");
			inText = inScanner.nextLine();
			if(inText.equals("")){
				keepGoing = false;
			}
			else{
				this.percentages.add(0);
				this.itemNames.add(inText);
			}//
		}//END WHILE (keepGoing)
	}//END CONSTRUCTOR

	//METHODS
	public boolean getMenu(){
		String inText = "";
		String lineText = "";
		Scanner inScanner = new Scanner(System.in);
		boolean keepGoing = true;
		//Display all fields
		for(int i = 0; i < this.itemNames.size(); i++){
			lineText = "";
			//For every item in the check box array
			// - print its number in the array for use in options
			lineText += "["+i+"] - {";
			// - figure out how many filled versus empty marks to make
			// -- take the %# from the array and turn it into a float
			float deciNum = (float) 100 / this.percentages.get(i);
			int filledBars = (int) Math.floor(20/deciNum);
			int emptyBars = 20 - filledBars;
			for(int x = 0; x < filledBars; x++){
				lineText+="=";
			}
			for(int x = 0; x < emptyBars;x++){
				lineText+="-";
			}
			lineText += "} |"+this.percentages.get(i)+"%| ";

			// - print its name
			lineText += this.itemNames.get(i);
			System.out.println(lineText);

		}//END FOR
		//Display bottom line and then options
		System.out.println("=================================");
		System.out.println("[#] - Change item #");
		System.out.println("[E] - Exit Task");

		//Get input
		inText = inScanner.nextLine();

		//Handle those options
		if(inText.equals("E") || inText.equals("e")){//EXIT
			keepGoing = false;
		}//
		else if(inText.equals("N") || inText.equals("n")){
			this.getNotes();
		}//
		else{
			try{
				int inNumber = Integer.valueOf(inText);
				System.out.println("Set ["+inNumber+"] "+this.itemNames.get(inNumber)+ " to what percentage?");
				System.out.println("(Please Omit the % and do non-decimal format. IE 20% would be entered as 20. Thank you)");
				inText = inScanner.nextLine();
				int newNum = Integer.valueOf(inText);
				if(newNum < 0){
					newNum = 0;
				}
				else if(newNum > 100){
					newNum = 100;
				}

				this.percentages.set(inNumber, newNum);

			}
			catch(Exception e){
				System.out.println("Please try again with a valid input");
			}
		}

		return keepGoing;
	}//END GET MENU








}//END CLASS

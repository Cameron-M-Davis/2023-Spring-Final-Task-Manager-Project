import java.util.*;
import java.io.*;

public class CheckListTask extends BaseTask{
	//VARIABLES
	public ArrayList<Integer> checkBoxes = new ArrayList<Integer>();
	//0 - unchcked, 1 - checked, the strings are kind of useless I just thought it might help
	public ArrayList<String> cbNames    = new ArrayList<String>();
	public String unChecked = "[ ]";
	public String checked   = "[X]";


	//CONSTURCTORS
	public CheckListTask(String title, String startDate, String endDate, String notImportant){
		//This constructor exists to let us programatically create this task without 
		// the user being asked to add fields at random
		super(title,startDate,endDate);
	}//
	public CheckListTask(String title, String startDate, String endDate){
		super(title,startDate,endDate);
		String inText = "";
		Scanner inScanner = new Scanner(System.in);
		boolean keepGoing = true;
		//Loop for adding Check Boxes to the array
		while(keepGoing){
			//We're going to assume that all check boxes start unchecked
			//as it would make no sense to add a cb for a new task
			//that's already be done
			//or maybe it does make sense.. idk mate. idk..

			System.out.println("Please enter the name for the field you wish to add:");
			System.out.println("Fields in task: "+this.checkBoxes.size());
			System.out.println("(press enter to stop adding fields)");
			inText = inScanner.nextLine();
			if(inText.equals("")){
				keepGoing = false;
			}//
			else{
				this.cbNames.add(inText);
				this.checkBoxes.add(0);
			}
		}//END WHILE LOOP (keepGoing)
		System.out.println("");
	}

	//METHODS
	public boolean getMenu(){
		String inText = "";
		String lineText = "";
		Scanner inScanner = new Scanner(System.in);
		boolean keepGoing = true;
		//Display all fields
		for(int i = 0; i < this.checkBoxes.size(); i++){
			lineText = "";
			//For every item in the check box array
			// - print its number in the array for use in options
			lineText += "["+i+"]";
			// - print its status checked v unchecked
			if(this.checkBoxes.get(i) == 1){
				lineText += checked;
			}
			else{
				lineText += unChecked;
			}
			// - print its name
			lineText += this.cbNames.get(i);
			System.out.println(lineText);

		}//END FOR
		//Display bottom line and then options
		System.out.println("=================================");
		System.out.println("[#] - Toggle item #");
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
				int inNumber = -1;
				inNumber = Integer.valueOf(inText);
				if(this.checkBoxes.get(inNumber) == 1){
					this.checkBoxes.set(inNumber,Integer.valueOf(0));
				}
				else{
					this.checkBoxes.set(inNumber, Integer.valueOf(1));
				}

			}
			catch(Exception e){
				System.out.println("Please try again with a valid input");
			}
		}

		return keepGoing;
	}//END GET MENU





}

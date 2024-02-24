import java.util.*;
import java.io.*;

interface Task{

	public void getTaskDetails();// For reading and editing the FULL state of a task
	public void getTaskState();  // For USER view on a task
	public boolean getMenu();	     // For USER View on a task, and manipulation of
				     //  tasks fields

	public void setTaskID(int id);
	public int validateTaskID(int id);

}//END INTERFACE






public class BaseTask implements Task, Serializable{
	//VARIABLES
	//
	static ArrayList<Integer> taskIDs = new ArrayList<Integer>();
	protected ArrayList<String> users = new ArrayList<String>(); // holds string of USER IDs
	protected ArrayList<String> notes = new ArrayList<String>(); // holds strings of notes made on a task
	//
	protected int taskID = 0; // Internal ID, needs to be UNIQUE
	protected String taskTitle = "Unnamed Task";
	protected String startDate = "Month/Day/Year";
	protected String endDate   = "Month/Day/Year";
	//
	
	//CONSTRUCTORs
	public BaseTask(){
		//super empty, don't think we'll really need this
		//setTaskID(this.taskID);
		// Details kept for posterity but to do a trick where we "make" a task without knowing what kind of task it is we make a base task with nothing in it
		// so this blank space has to stay and just do nothing
	}
	public BaseTask(String title,String startDate, String endDate){
		setTaskID(this.taskID);
		this.taskTitle = title;
		this.startDate = startDate;
		this.endDate   = endDate;
	}	
	
	//METHODS
	public void getTaskDetails(){
		System.out.println(this.taskTitle);
		System.out.println("TaskID: "+this.taskID);
		

		System.out.println("");
	}//END GET DETAILS
	public void getTaskState(){
		boolean keepGoing = true;
		while(keepGoing){
		System.out.println(this.taskTitle+" ["+this.taskID+"]");
		System.out.println("=================================");
		System.out.println("[N] - Notes: "+this.notes.size());
		System.out.println("=================================");
		System.out.println("Fields:");
		keepGoing = this.getMenu();
		System.out.println("");
		}
	
	}//END GET STATE
	public boolean getMenu(){
		String inText = "";
		Scanner inScanner = new Scanner(System.in);
		System.out.println("");
		inText = inScanner.nextLine();
		return false; //Base version will always kill loop and should never be used

	}//END GET MENU
	public void getNotes(){
		String inText = "";
		Scanner inScanner = new Scanner(System.in);
		System.out.println("Notes: ");
		for(int i = 0; i < this.notes.size(); i++){
			System.out.println(this.notes.get(i));
		}//END FOR
		System.out.println("");
		System.out.println("[N] - Add a note");
		inText = inScanner.nextLine();
		if(inText.equals("n") || inText.equals("N")){
			this.addNote();
		}

	}//END GET NOTE
	public void addNote(){
		String inText = "";
		Scanner inScanner = new Scanner(System.in);
		Calendar timeStamp = Calendar.getInstance();
		System.out.println("Please type what you'd like to add:");
		inText = inScanner.nextLine();
		this.notes.add(timeStamp.getTime() + ": "+inText);
	}//END ADD NOTE
	public void setTaskID(int id){
	//Sets the task ID and addss it to the array
		this.taskID = this.validateTaskID(id);
		taskIDs.add(this.taskID);
	
	}//END SET TASK ID
	public int validateTaskID(int id){
		if(taskIDs.size() != 0){
		//There's anything in the array
			int num = 0; // the highest number in the array
			for(int i = 0; i < taskIDs.size(); i++){
				if(taskIDs.get(i) > num){
					num = taskIDs.get(i);
				}//End internal IF
			}//END FOR LOOP
			id = num + 1;
		}
		else{
		//Nothing in the array, start at 0
			id = 0;
		}//
		return id;
	}//END VALIDATE TASK ID



}

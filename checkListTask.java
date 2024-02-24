import java.util.*;
import java.io.*;

public class CheckListTask extends BaseTask{
	static ArrayList<Integer> taskIDs = new ArrayList<Integer>();
	protected int taskID = 0;
	protected String taskTitle = "";
	protected ArrayList<String> users = new ArrayList<String>();
	protected ArrayList<String> notes = new ArrayList<String>();
	//
		protected ArrayList<String> checkBoxes = new ArrayList<String>();
	//
	
	public CheckListTask(){
		

	}//END CONSTRUCTOR

	public void getTaskState(){
		System.out.println(this.taskTitle+" ["+this.taskID+"]");
		System.out.println("=================================");
		System.out.println("Fields:");
		for(int i = 0; i < this.checkBoxes.size(); i++){
			System.out.println("["+i+"] - "+this.checkBoxes.get(i));
		}//END FOR LOOP
	
	}//END GET STATE
	public void getMenu(){
	}//END GET MENU










}

import java.util.*;
import java.io.*;

public class Project implements Serializable{
	protected String name = "";
	protected ArrayList<String> Users = new ArrayList<String>();
	protected ArrayList<BaseTask> Tasks = new ArrayList<BaseTask>();

	public static void main(String[] args){



	}//End Main

	Project(String name){
		this.name = name;
	}//


	//public void addTask(Task inTask){
	public void addTask(ArrayList<User> userList){
		BaseTask newTask = new BaseTask();
	//Adds New Task to a project
		boolean keepGoing = true;
		String inText = "";
		Scanner inScanner = new Scanner(System.in);
		String newTitle ="";
		String newEndDate ="";
		String newStartDate = "";
	//gather pre creation information
	// - get title
		System.out.println("Please enter the title for this task.");
		newTitle = inScanner.nextLine();
	// - get start date
		System.out.println("Please enter the start date for "+newTitle);
		newStartDate = inScanner.nextLine();
	// - get end date
		System.out.println("Please enter the end date for "+newTitle);
		newEndDate = inScanner.nextLine();
	//make of the type desired
	// - get type
		while(keepGoing){
			keepGoing = false;
		System.out.println("What type of task would you like to add?");
		System.out.println("[1] - Check List: contains any number of check boxes for users to check as objectives are completed ");
		System.out.println("[2] - Progress Bar: holds any number of progress bars to associate percent completion to parts of an objective");
		inText = inScanner.nextLine();
		if(inText.equals("1")){//1 Create Check List
		//Instiantiate proper task type
			newTask = new CheckListTask(newTitle,newStartDate,newEndDate);
		}
		else if(inText.equals("2")){//2 Create Progress Bar
		//
			newTask = new ProgressBarTask(newTitle,newStartDate,newEndDate);
		}
		else{//ERROR cease all other operations
			System.out.println("Please try again with a correct type input");
			keepGoing = true;
			//starts false, if an invalid number is put in then flips to true,
			//and restarts the loop

		}//END OF IF STATEMENT
		
		System.out.println("");
		}//END WHILE LOOP
		keepGoing = true; //Set up for next loop
		boolean innerLoop = true;
	// - set the users by name or by roles
		while(keepGoing){
			
			System.out.println("Would you like to add users by Role or by User ID?");
			System.out.println("[1] - By Role");
			System.out.println("[2] - By ID");
			inText = inScanner.nextLine();
			if(inText.equals("2")){
				keepGoing = false;
				while(innerLoop){
					boolean flag = false;
					System.out.println("Please enter the userID you wish to add");
					System.out.println("(press enter to stop adding)");
					inText = inScanner.nextLine();
					if(inText.equals("")){//nothing entered, end adding
						innerLoop = false;
					}//
					else{
						for(int i = 0; i < userList.size(); i++){
						//Check all users in the user list to make sure they entered a valid user
							if(userList.get(i).userID.equals(inText)){
							//A valid user was found
							flag = true;
								if(!newTask.users.contains(inText)){
									//User was not found assigned to the task yet
									newTask.users.add(inText);
								}
								else{
									System.out.println("user already assigned to task");
								}
								//
								if(!this.Users.contains(inText)){
									//User was not found assigned to the project yet
									this.Users.add(inText);
								}
							}
						}//END FOR LOOP
						if(!flag){
							System.out.println("No user could be found with that ID");
						}

					}//END contains already check

					System.out.println("");
				}//END ADD BY ID INNER LOOP
			}//
			else if(inText.equals("1")){
				keepGoing = false;
				while(innerLoop){
					System.out.println("Please enter the Role you wish to add by");
					System.out.println("(press enter to stop adding)");
					inText = inScanner.nextLine();
					if(inText.equals("")){
						innerLoop = false;
					}//
					else{
						for(int i = 0; i < userList.size(); i++){
							if(userList.get(i).role.equals(inText)){
								if(!newTask.users.contains(userList.get(i).userID)){
									newTask.users.add(userList.get(i).userID);
								}//
								if(!this.Users.contains(userList.get(i).userID)){
									this.Users.add(userList.get(i).userID);
								}//END IFs
							}//
						}//END FOR LOOP
					}//END IF STATEMENT
					System.out.println("");
				}//END ADD BY ROLE INNER LOOP
			}//
			else{
				System.out.println("Please enter a valid selection");
			}//END IF statement
			System.out.println("");
			this.Tasks.add(newTask);
		}
	}//END ADD TASK



	public void openTaskNotes(){
		System.out.println("");
		String inText = "";
		Scanner inScanner = new Scanner(System.in);

		boolean keepGoing = true;
		while(keepGoing){
			for(int x = 0; x < this.Tasks.size(); x++){
				System.out.println("["+x+"] - "+this.Tasks.get(x).taskTitle);
				if(this.Tasks.get(x).notes.size() != 0){
					for(int y = 0; y < this.Tasks.get(x).notes.size(); y++){
						System.out.println("    {"+y+"} "+this.Tasks.get(x).notes.get(y));
					}
				}
				else{
					System.out.println("    No notes found");
				}//END IF
			}//END FOR LOOP
			System.out.println("==========================");
			System.out.println("[#] - Enter the # of Task you whish to manage");
			System.out.println("[E] - Exit");
			inText = inScanner.nextLine();
			
			if(inText.equals("E") || inText.equals("e")){
				keepGoing = false;
			}
			else{
				try{
					int inNum = Integer.valueOf(inText);
					System.out.println("\n[N] - add note to "+this.Tasks.get(inNum).taskTitle);
					System.out.println("[#] - Delete note of number entered");
					inText = inScanner.nextLine();
					if(inText.equals("N") || inText.equals("n")){
						this.Tasks.get(inNum).addNote();
					}
					else{
						try{
							int tNum = Integer.valueOf(inText);
							this.Tasks.get(inNum).notes.remove(tNum);
						}
						catch(Exception e){
							System.out.println("Please enter a valid input");
						}
					}//END IF
				}
				catch(Exception e){
					System.out.println("Please enter a valid input");
				}
			}//END IF
		}//END WHILE LOOP (keepGoing)
	}//END OPEN NOTES



}//End Class

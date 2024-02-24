import java.util.*;
import java.io.*;

public class TaskManager{
	public Scanner inScanner = new Scanner(System.in);
	public String inText = "";

	protected ArrayList<User> userList = new ArrayList<User>();
	protected ArrayList<Project> projectList = new ArrayList<Project>();
	
	public User presentUser = null;


	public static void main(String[] args){
		boolean keepGoing = true;

		TaskManager gary = new TaskManager(); 
		// I am going to be entirely honest with you here, I was going to call this manager. Then I thought it was funnier if this line read 'task manager gary' like the
		// object is just a dude named gary who manages all your tasks for you.
		// .. sometimes you don't need to write good code. Sometimes you need to write the code that keeps you sane or bolsters your spirit.

		if(gary.userList.size() <= 0){
			gary.createDefaultUsers();

		}
		if(gary.projectList.size() <= 0){
			gary.createDefaultProjects();

		}



		while(keepGoing){
			if(gary.presentUser == null){
				keepGoing = gary.login();

			}//
			
			else if(gary.presentUser != null){

				if(gary.presentUser.role.equals("ADMIN")){//ADMIN
					gary.adminMenu();			
				
				
				}//
				else{//Regular User
					gary.userMenu();	


				}
			}
			else{
				System.out.println("Something has gone horribly wrong and you shouldn't be seeing this..");
				
			}//


		}// End WHILE LOOP (KEEPGOING)
	
		gary.saveUsers();

		gary.saveProjects();


	}//End Main



	TaskManager(){
		//Whenever we're making a new task manager we're going to want to re-load all the arrays
		this.loadUsers();
		this.loadProjects();
	}//
	//End Constructors
	public void userMenu(){
		boolean keepGoing = true;
		int assignedNumber = 0;
		while(keepGoing){
			int counter = 0;
			ArrayList<Integer> projNum = new ArrayList<Integer>();

		System.out.println("User Menu:");
		System.out.println("Hello "+this.presentUser.name);
		System.out.println("==============");
		System.out.println("Projects Assigned:"+assignedNumber);

			for(int i = 0; i < this.projectList.size(); i++){
			//Check every project
				for(int x = 0; x < this.projectList.get(i).Users.size(); x++){
				//For every user in that project
					if(this.projectList.get(i).Users.get(x).equals(presentUser.userID)){
					//if it matches the user presently logged in
					// display the project and the counter number
						System.out.println("["+counter+"] "+this.projectList.get(i).name+": Tasks: "+this.projectList.get(i).Tasks.size());
					//
						projNum.add(i);
					//up the counter by one (1)
						counter +=1;
					}
				}
			}//END FOR LOOP (OUTSIDE)

		System.out.println("==============");
		System.out.println("[#] - Projects");
		System.out.println("[U] - Edit User Details");
		System.out.println("[E] - Log Out");
		inText = inScanner.nextLine();

		if(inText.equals("u") || inText.equals("U")){//EDIT USER DETAILS
		//Let a user change their full name, and their password
			this.presentUser.editDetails();


		}
		else if(inText.equals("e") || inText.equals("E")){//LOG OUT
			keepGoing = false;
			this.logOut();

		}
		else{//PROJECTS
			System.out.println("");
			int inNumber = -1;
			try{
				inNumber = Integer.valueOf(inText);
				this.projectUserView(projNum.get(inNumber));

			}
			catch(Exception e){
				System.out.println("Please enter a valid response and try again");
			}//END TRY CATCH
		}

		}//End WHILE LOOP (KEEPGOING)
		System.out.println("");
	}//END USER MENU

	public void projectUserView(int projectNumber){
		boolean keepGoing = true;
		while(keepGoing){
			System.out.println(this.projectList.get(projectNumber).name);
			System.out.println("======================");
			for(int i = 0; i < this.projectList.get(projectNumber).Tasks.size(); i++){
				System.out.println("["+i+"] - "+this.projectList.get(projectNumber).Tasks.get(i).taskTitle);
			}//END FOR LOOP
			System.out.println("[E] - Exit Project");
			System.out.println("======================");
			inText = inScanner.nextLine();
			if(inText.equals("E")||inText.equals("e")){
				keepGoing = false;
			}//
			else{
				try{
					int inNumber = -1;
					inNumber = Integer.valueOf(inText);
					this.taskUserView(projectNumber, inNumber);
				}
				catch(Exception e){
				}//END TRY CATCh
			}
		}//END WHILE LOOP (keepGoing)	
	}//END PROJECT USER VIEW


	public void taskUserView(int projectNumber, int taskNumber){
		this.projectList.get(projectNumber).Tasks.get(taskNumber).getTaskState();
	}//END TASK USER VIEW

	public void adminMenu(){
		boolean keepGoing = true;
		while(keepGoing){

		System.out.println("Admin Menu:");
		System.out.println("==============");
		System.out.println("[1] - Edit Users");
		System.out.println("[2] - Edit Projects");
		System.out.println("[0] - Log Out");
		inText = inScanner.nextLine();
		System.out.println("");

		if(inText.equals("1")){//Edit USERS
			this.editUsers();
	
		}
		else if(inText.equals("2")){//Edit PROJECTS
			this.editProjects();

		}
		else if(inText.equals("0")){//LOG OUT
			keepGoing = false;
			this.logOut();

		}
		else{//Something went wrong
			System.out.println("Error, please enter the number only of the action desired");
		}

		}//End WHILE LOOP (KEEPGOING)

	}//END ADMIN MENU

	public void editUsers(){
		boolean keepGoing = true;
		
		while(keepGoing){
		System.out.println("EDIT USERS :"+this.userList.size());
		System.out.println("======================");

		for(int i = 0; i < this.userList.size(); i++){
			System.out.println("["+i+"]: "
					+this.userList.get(i).userID+
					" '"+this.userList.get(i).name+
					"' Role: "+this.userList.get(i).role);
		}//End FOR LOOP: list all users
		System.out.println("======================");
		System.out.println("[N] - Make New User");
		System.out.println("[#] - Edit User #");
		System.out.println("[E] - Exit EDIT USERS");
		inText = inScanner.nextLine();
		if(inText.equals("n") || inText.equals("N")){
		//If they are trying to make a new user
				this.newUser();
		}
		else if(inText.equals("e") || inText.equals("E")){
		//Or IF they are trying to leave the program
			keepGoing = false;

		}
		else{
		//If they did not want to do either, lets see if they were trying to edit a user

			try{
				int inNumber = Integer.valueOf(inText);
				// Make sure the number is within the size of the array
				if(inNumber <= this.userList.size()){
				System.out.println(" press 'enter' to keep the current");
				// ask them about each detail to see if it needs changed

				// password (Do not show them the prior password)
				System.out.println("Password. Current: N/A for security reasons");
				inText = inScanner.nextLine();
					if(inText.equals("")){
						//Do nothing
					}
					else{
						this.userList.get(inNumber).password = inText;

					}


				// full name
				System.out.println("Name. Current: '"+this.userList.get(inNumber).name+"'");
				inText = inScanner.nextLine();
					if(inText.equals("")){
						//Do nothing
					}
					else{
						this.userList.get(inNumber).name = inText;

					}


				// role
				System.out.println("Role. Current: '"+this.userList.get(inNumber).role+"'");
				inText = inScanner.nextLine();
					if(inText.equals("")){
						//Do nothing
					}
					else{
						this.userList.get(inNumber).role = inText;

					}


				}
				else{
					System.out.println("Please enter an accurate number");

				}

				System.out.println(inNumber);//TEMP

			}
			catch(Exception e){
				System.out.println("Please enter only a valid option");

			}

		}//END OF IF STATEMENT 
			System.out.println("");	
		}//END OF WHILE LOOP (keepGoing)
	}//END EDIT USERS


	public void newUser(){
	
		System.out.println("Enter the new Users userID (this cannot be changed later)");
		inText = inScanner.nextLine();
		User newUser = new User(inText, "password", "UNSET");

		System.out.println("Enter the password for "+newUser.userID);
		inText = inScanner.nextLine();
		newUser.password = inText;

		System.out.println("Enter the full name for "+newUser.userID);
		inText = inScanner.nextLine();
		newUser.name = inText;

		System.out.println("Enter the role for "+newUser.userID);
		inText = inScanner.nextLine();
		newUser.role = inText;
			
		this.userList.add(newUser);

	}//END NEW USER




	public void editProjects(){
		boolean keepGoing = true;
		while(keepGoing){
		boolean internalLoop = true;

		System.out.println("EDIT PROJECTS :"+this.projectList.size());
		System.out.println("======================");
			
		for(int i = 0; i<this.projectList.size();i++){
			System.out.println("["+i+"] "+this.projectList.get(i).name+". Tasks: "+this.projectList.get(i).Tasks.size());
		
		
		}//End FOR LOOP
		System.out.println("======================");
		System.out.println("[N] - New Project");
		System.out.println("[#] - Edit Project #");
		System.out.println("[E] - Exit EDIT PROJECTS");
		inText = inScanner.nextLine();

		System.out.println();

		if(inText.equals("n") || inText.equals("N")){
		//create a new project.
			System.out.println("Please enter the name for the new project");
			inText = inScanner.nextLine();
			Project newProject = new Project(inText);
			//

			this.projectList.add(newProject);



		}
		else if(inText.equals("e") || inText.equals("E")){
			keepGoing = false;
		
		}
		else{
		//get the # from the inText, make sure its a number. then open up that project to edit it
			try{
				int inNumber = Integer.valueOf(inText);
				if(inNumber <= this.projectList.size()){
				//Make sure its within the bounds of the list IE actually have a project at that number
					while(internalLoop){
					boolean flag = true; //USED FOR PROCESS CHECKING

					System.out.println("Edit Project: "+this.projectList.get(inNumber).name);
					System.out.println("===================");
					System.out.println("Tasks Currently In The Project: ");
					for(int i = 0; i < this.projectList.get(inNumber).Tasks.size(); i++){
						System.out.println("- [ID :"+this.projectList.get(inNumber).Tasks.get(i).taskID+"] "
								+ this.projectList.get(inNumber).Tasks.get(i).taskTitle
								+ " Start: "+this.projectList.get(inNumber).Tasks.get(i).startDate
								+ " End: "+this.projectList.get(inNumber).Tasks.get(i).endDate);
					}//END FOR LOOP
					System.out.println("===================");
					System.out.println("Users Currently In The Project: ");
					for(int i = 0; i < this.projectList.get(inNumber).Users.size(); i++){
						System.out.println("- "+
						this.projectList.get(inNumber).Users.get(i));
					}//END FOR LOOP
					System.out.println("===================");
					System.out.println("[1] - Add User To Project");
					System.out.println("[2] - Add Task To Project");
					System.out.println("[3] - Remove User From Project");
					System.out.println("[4] - Remove Task From Project");
					System.out.println("[5] - Open Task Notes");
					System.out.println("[0] - Exit Edit Project");
					inText = inScanner.nextLine();
					if(inText.equals("0")){
						internalLoop = false;
					}
					else if(inText.equals("1")){// ADD USER
						System.out.println("Would you like to add users by Role or by User ID?");
						System.out.println("[1] - By Role");
						System.out.println("[2] - By ID");
						inText = inScanner.nextLine();
						if(inText.equals("1")){// BY ROLE
							System.out.println("Please enter the role you wish to add by");
							inText = inScanner.nextLine();
							for(int i = 0; i < this.userList.size(); i++){
								if(this.userList.get(i).role.equals(inText)){
									if(this.projectList.get(inNumber).Users.contains(this.userList.get(i).userID)){
										// Don't say anything
									}
									else{
									this.projectList.get(inNumber).Users.add(this.userList.get(i).userID);
									System.out.println("User "+this.userList.get(i).userID+" Added");
									}//END INTERNAL IF ELSe
								}//END OUTSIDE IF
							}//END FOR LOOP
						}
						else if(inText.equals("2")){// By Name
							boolean notFound = true;
							System.out.println("Please enter the userID you wish to add");
							inText = inScanner.nextLine();
							for(int i = 0; i < this.userList.size(); i++){
								if(this.userList.get(i).userID.equals(inText)){
									this.projectList.get(inNumber).Users.add(this.userList.get(i).userID);
									notFound = false;
								}
							}
							if(notFound){
								System.out.println("UserID could not be found");
							}//END FLAG IF
						}
						else{//ERROR
							System.out.println("Please enter a valid selection");
						}//END IF

					}
					else if(inText.equals("2")){// ADD TASK
						this.projectList.get(inNumber).addTask(this.userList);	
					}
					else if(inText.equals("3")){//REMOVE USER
						System.out.println("Please enter the user ID you wish to remove from the project");
						inText = inScanner.nextLine();

						for(int i = 0; i < this.projectList.get(inNumber).Users.size(); i++){
							if(this.projectList.get(inNumber).Users.get(i).equals(inText)){
								this.projectList.get(inNumber).Users.remove(i);
								System.out.println("User "+inText+" Removed");
								flag = false;
							}//END INTERNAL IF

						}//END FOR LOOP
						if(flag){
							System.out.println("User could not be found in the project");
						}//END IF

					}
					else if(inText.equals("4")){//REMOVE TASK
						int taskNum = -1;
						System.out.println("Please enter the task ID you wish to remove from the project");
						inText = inScanner.nextLine();
						//Validate 
						try{
							//# is a number
							taskNum = Integer.valueOf(inText);							
							if(taskNum >=0){
							//taskID from Task.java is a int that starts at 0 and only ever ADDS ONE (+1) to it each time a new ID is given
							// thus it should never be less then 0 to be a valid number
							//
								for(int i = 0; i < this.projectList.get(inNumber).Tasks.size(); i++){
									if(this.projectList.get(inNumber).Tasks.get(i).taskID == taskNum){
										this.projectList.get(inNumber).Tasks.remove(i);
										System.out.println("Task "+taskNum+" Removed");
										flag = false;
									}//END IF
								}//END FOR LOOP
								if(flag){
									System.out.println("Task ID could not be found in the project");
								}//END IF
							}
							else{
								System.out.println("This number cannot be a task ID please try again with a correct number");
							}
						}
						catch(Exception e){
							System.out.println("Please enter a number when removing ID's");
						}//END TRY CATCH
					}
					else if(inText.equals("5")){//OPEN NOTES
						this.projectList.get(inNumber).openTaskNotes();	
					}
					else{
						System.out.println("");
					}
					System.out.println("");
					}//END WHILE LOOP (internalLoop)
				}
				else{
				//They did not pick a number that was actually in the list
					System.out.println("Please enter only a valid option");

				}
				//this.projectList.get(inNumber).addTask();



			}
			catch(Exception e){
				System.out.println("Please enter only a valid option");


			}//END TRY CATCH

		}


		}//End WHILE LOOP (keepGoing)
	}//END EDIT PROJECT





	public void logOut(){
		System.out.println("\nLogging Out\n");
		this.presentUser = null;

	}// END LOG OUT

	public boolean login(){
		boolean keepGoing = true;
		boolean matchFound = false;
		User loginUser = null;
		System.out.println("Welcome to the task management system:");
		System.out.println("=========================================");
		System.out.println("[1] - Login ");
		System.out.println("[0] - Quit  ");
		inText = inScanner.nextLine();
		//
		System.out.println("\n//-//-//\n ");

		if(inText.equals("1")){//LOGIN
			System.out.println("Please Enter Your User ID: ");
			inText = inScanner.nextLine();
			

			for(int i = 0; i < this.userList.size(); i++){
				//System.out.println(":: "+userList.get(i).userID);

				if(inText.equals(userList.get(i).userID)){
					loginUser = userList.get(i);
					matchFound = true;
				}
			}

			if(matchFound){	
				System.out.println("Please enter your password");
				inText = inScanner.nextLine();
				if(inText.equals(loginUser.password)){
					System.out.println("Login Accepted\n");
					presentUser = loginUser;

				}
				else{
					System.out.println("Sorry that was incorrect\n");
				}

			}
			else{ // NO MATCH WAS FOUND
				System.out.println("No user could be found with that ID please contact your system administration if the error continues\n");
			}
		}//	End of LOGIN
		else if(inText.equals("0")){//QUIT
			System.out.println("Quitting application!");
			keepGoing = false;
		}//
		else{//ELSE
			System.out.println("Please only enter the number with the listed option you wish to select");

		}//
		//END OF IF STATEMENT



		return keepGoing;
	}




	//OBJECT SAVING AND LOADING

	// USERS
	public void saveUsers(){
		try{
			FileOutputStream fw = new FileOutputStream("users.dat");
			ObjectOutputStream ow = new ObjectOutputStream(fw);
			ow.writeObject(userList);
			ow.close();
			fw.close();
		}
		catch(Exception e){
		System.out.println("Error: "+e.getMessage());
		}//End TRY CATCH
	}//End Save

	public void loadUsers(){
		try{
			File usersFile = new File("users.dat");
			if(usersFile.exists()){//Can't read it if its not there
				FileInputStream fr = new FileInputStream("users.dat");
				ObjectInputStream or = new ObjectInputStream(fr);

				ArrayList<?> tempArray = (ArrayList<?>) or.readObject();
				for(int y = 0; y < tempArray.size(); y++){
					userList.add((User) tempArray.get(y));
				}
				//

				//Re add all user ID's to the User class list of ID's
				// this is to ensure we have only unique ID's
				for(int i = 0; i < userList.size(); i++){
					User.userIDs.add(userList.get(i).userID);
				}//END FOR LOOP
				or.close();
				fr.close();
				
			}//
			else{
				//if its not there just create the default users instead
				this.createDefaultUsers();
			}
		}
		catch(Exception e){
		System.out.println("Error: "+e.getMessage());
		}//End TRY CATCH

	}//End Load
	//


	// PROJECTS
	public void saveProjects(){
		try{
			FileOutputStream fw = new FileOutputStream("projects.dat");
			ObjectOutputStream ow = new ObjectOutputStream(fw);
			ow.writeObject(projectList);
			fw.close();
			ow.close();
		}
		catch(Exception e){
		System.out.println("Error: "+e.getMessage());
		}//End TRY CATCH
	}//End Save

	public void loadProjects(){
		try{
			File projectsFile = new File("projects.dat");
			if(projectsFile.exists()){
				FileInputStream fr = new FileInputStream("projects.dat");
				ObjectInputStream or = new ObjectInputStream(fr);

				ArrayList<?> tempArray = (ArrayList<?>) or.readObject();
				for(int y = 0; y < tempArray.size(); y++){
					projectList.add((Project) tempArray.get(y));
				}

				fr.close();
				or.close();
			}//
			else{
				//
				this.createDefaultProjects();
			}
		}
		catch(Exception e){
		System.out.println("Error: "+e.getMessage());
		}//End TRY CATCH

	}//End Load
	//END OBJECT SAVING AND LOADING


	//DEFAULT CASE GENERATION
	//these are called when the arrays of projects and users is at 0
	//ie after a make clean or a fresh install of the program
	public void createDefaultUsers(){
	//This method deletes the old arrayList and makes a new one
	// for changes to data structure and creation purposes
		this.userList = new ArrayList<User>();
		User defAdminUser = new User("admin","password","ADMIN");
		defAdminUser.name = "Administrator Account";
		User defRegUser   = new User("RegUser", "password", "REGULAR");
		defRegUser.name   = "Name A. Real";
		User defSupUser   = new User("SupUser", "password", "SUPPORT");
		defSupUser.name   = "Totally A. Person";
	
		userList.add(defAdminUser);
		userList.add(defRegUser);
		userList.add(defSupUser);

	}//END CREATE DEFEAULT USERS
	public void createDefaultProjects(){
		//clear the array, even though it should be cleared already
		this.projectList = new ArrayList<Project>();

		//make tasks and add items
		CheckListTask clt1 = new CheckListTask("Check List 1","n/a","n/a","");
		clt1.checkBoxes.addAll(Arrays.asList(0,0,0));
		clt1.cbNames.addAll(Arrays.asList("Item 1","Item 2","Item 3"));

		ProgressBarTask pbt1 = new ProgressBarTask("Progress Reports 1","n/a","n/a","");
		pbt1.percentages.addAll(Arrays.asList(0,50,90));
		pbt1.itemNames.addAll(Arrays.asList("Laser beam accuracy","Test Rodent Population","Critical flaw for evil master plan construction (gotta give the good guys a chance)"));

		//make projects and add tasks	
		Project defProject1 = new Project("Project 1");
		defProject1.Users.add("RegUser");
		defProject1.Tasks.add(clt1);
		defProject1.Tasks.add(pbt1);


		//make tasks and add items
		CheckListTask clt2 = new CheckListTask("Check List 1","n/a","n/a","");
		clt2.checkBoxes.addAll(Arrays.asList(0,0,0));
		clt2.cbNames.addAll(Arrays.asList("Item 1","Item 2","Item 3"));

		ProgressBarTask pbt2 = new ProgressBarTask("Progress Reports 1","n/a","n/a","");
		pbt2.percentages.addAll(Arrays.asList(0,50,90));
		pbt2.itemNames.addAll(Arrays.asList("Laser beam accuracy","Test Rodent Population","Critical flaw for evil master plan construction (gotta give the good guys a chance)"));

		//make projects and add tasks		
		Project defProject2 = new Project("Project 2");
		defProject2.Users.add("SupUser");
		defProject2.Tasks.add(clt2);
		defProject2.Tasks.add(pbt2);

		projectList.addAll(Arrays.asList(defProject1,defProject2));

	}//END CREATE DEFAULT PROJECTS









}//End Class

import java.util.*;
import java.io.*;

public class User implements Serializable {
	static ArrayList<String> userIDs = new ArrayList<String>();
	protected String userID = "";	// Internal ID, UNIQUE
	protected String password= "";	// Later down the line I want to change this to be a hash of a password.
	protected String name = ""; 	// Their human name
	protected String role = "";	// For role based task assignment.

	public static void main(String[] args){
		User testUser = new User("test","password","ADMIN");
		testUser.getUserDetails();

		User testTwoUser = new User("test","password","ADMIN");
		testTwoUser.getUserDetails();

		//testTwoUser.readArray();

	}//End Main


	User(String userId, String password, String role){
		this.setUserID(userId);
		this.password = password;
		this.role = role;

	}//End Full Constructor

	public void setUserID(String id){
		//System.out.println("UID: "+this.userID+"| IDIN: "+id);
		this.userID = this.validateUserID(id);
		userIDs.add(this.userID);

	}//End SET:USER ID

	public String validateUserID(String id){
	//This method functions to ensure that every userID is unique by checking an attempted ID against all ID's used in User classes
	//	+ will call itself recursively till a good ID is gained, and return that value	
	//
		
		Scanner input = new Scanner(System.in);

		boolean flag = true;
		while(flag){
			flag = false;
			for(int i = 0; i < userIDs.size(); i++){
				//System.out.println("ID["+i+"] '"+userIDs.get(i)+"'");
				if(id.equals(userIDs.get(i))){
				//If we find a match, change the flag state
					flag = true;
				}
			}//End FOR

			if(flag){
			//If we found a match, ask them to try again
				System.out.println("\nError, ID already in use. Please try again");
				id = input.nextLine();
				//this.validateUserID(input.nextLine());
			}//End IF

		}//END LOOP
	return id;
	}//End Validation for USER ID

	public void getUserDetails(){
		System.out.println("USER ID : "+this.userID);
		System.out.println("PASSWORD: "+this.password);
		System.out.println("NAME    : "+this.name);
		System.out.println("ROLE    : "+this.role);

	}

	public void editDetails(){
		String inText = "";
		Scanner inScanner = new Scanner(System.in);
		System.out.println("Changing user information:");
		System.out.println("Press enter to keep old value");
		System.out.println("What would you like to set your password too? (old password not given for security reasons)");

		inText = inScanner.nextLine();
		if(inText.equals("")){

		}
		else{
			this.password = inText;
		}

		System.out.println("\nName: "+this.name);
		System.out.println("What would you like to set your name too?");
		inText = inScanner.nextLine();
		if(inText.equals("")){

		}
		else{
			this.name = inText;
		}
	


		System.out.println("UserID cannot be changed, and if you'd like your role to be changed please contact an admin.\nThank you and have a nice day");
	
	}//END EDIT USER DETAILS


}

# Class Diagrams
Full UML included as image in this folder.


#Algorithims 

## TaskManager
Our main class. Handles the main loop and calling the user menu's to get the ball rolling for the functions of the program.
### Main()
Main main of the program. Operational loop.
1. create the taskmanager class, and empty variables we need. Array Lists to hold users and projects.
2. check the size of the arrays (after creation if needed)
3. begin the mainiest main loop that ever main'd
4. in that loop if there is no user at present call login
5. if there is a present user then figure out its role
6. if that role is admin then show it the admin menu
7. if that role is not admin then show it the regular menu
8. and if someone tells it to quit in the login then end the loop and end the program
 
### TaskManager()
creation of the taskManager class
1. call loadUsers
2. call loadProjects
 
### userMenu()
Menu users get to see to do user things
1. start a new loop till the user quits it
2. display text with all the options
3. if the user wants to edit a project they have control over, open that menu
4. if the user wants to edit their own details, open that menu
5. if they want to we log out
6. other wise ignore the input

### projectUserView()
1. start a new loop till the user quits
2. in that loop display all of the tasks inside the project that was selected to begin this method
3. get the task # that they enter
4. and if that # is a valid task number open that task view

### taskUserView()
1. call the getTaskState of the task that is entered by the prior menu

### adminMenu()
1. begin a loop that goes till the user enters an exit key
2. display the admin menu options:
3. edit users: call editUsers()
4. edit projects: call editProjects()

### editUsers()
1. create a loop till the user exits
2. display each of the users 
3. list all of the options:
4. make a new user: call newUser()
5. exit
6. if they enter a number we check to see if its a valid user number
7. when they enter a number that is valid, we let them change each of the bits of information as follows:
8. password, full name, role
9. note: password is not told, they will not be informed of the last password

### newUser()
1. ask the user to input the userID for the new user
2. ask the user for the new password
3. ask the user for the full name of the new user
4. ask the user for the new user's role

### editProjects()
1. create a loop that goes until the user enters a value that lets them exit
2. show them all the projects inside the program
3. display all menu options:
4. new Project: ask the user for the new projects name, then make the new project
5. edit a project of a valid number:
5. 1. displays the tasks in the project
5. 2. display all the users in the project
5. 3. display all the options: add user, add task, remove user, remove task, exit 
04/24/2023 - this entire class is a hot mess and I am only now realizing how nonsense it is having all of the functions be coded like this. We spend so much time just going through the list of arrays to get the project we're dealing with rather then doing it once and just making it done inside the project class  

### logOut()
1. set the present user to null

### login()
1. display login screen information
2. ask the user if they want to login or quit
3. if they hit quit, end program
4. if they want to log in ask for the UserID
5. check if that is a valid ID, if it isn't let them know it wasn't found
6. if it was then ask for the password
7. if the password they gave is corret log in, if it isn't give error and kick them out

### saveUsers()
1. create a file output stream to the file "users.dat"
2. create an object output stream to that file output stream
3. write the present contents of the user array to that file with those streams

### loadUsers()
1. check to see if the file "users.dat" exists
2. if it does do the following:
3. 1. create a file input stream from the file "users.dat"
3. 2. create an object input stream to that file output stream
3. 3. write the present contents of the file to the user array
4. if it doesn't then ignore all of this and load createDefaultUsers()

### saveProjects()
1. create a file output stream to the file "projects.dat"
2. create an object output stream to that file output stream
3. write the present contents of the user array to that file with those streams

### loadProjects()
1. check to see if the file "projects.dat" exists
2. if it does do the following:
3. 1. create a file input stream from the file "projects.dat"
3. 2. create an object input stream to that file output stream
3. 3. write the present contents of the file to the projects array
4. if it doesn't then ignore all of this and load createDefaultProjects()

### createDefaultUsers()
1. empty out the array of users
2. create a default admin, with the admin role, and password as password
3. create a default regular user, with the regular role, and password as password
4. create a default support user, with the support role, and password as password
5. add these users to the user array

### createDefaultProjects()
1. empty out the array of projects
2. create two sets new tasks of each type to add to the project with a range of statuses
3. create two projects and add one set of new tasks to each
4. add these both to the project array



## Project
This class holds tasks and a list of userID's as strings and handles the addition of those tasks and users.
### Project()
1. take a name when you call this, set the project name to that name

### addTask()
1. take an array of users ID's as an array of strings
2. make a new blank task of base task type
3. ask the user for the title of this new task
4. ask the user for the start date for this new task
5. ask the user for the end date for this new task
6. start a loop to get the type of task and make sure it is only a valid response
7. re-make the blank task we made before with the new task of the proper task type
8. once we get the task type made we ask how they want to add users
9. by id we ask them for the ID's till they hit enter
10. by role we check every user and see if its role matches the role entered, and do that as many times as they enter roles or end it once they hit enter.
11. add the task to the project's list of tasks

### openTaskNotes()
1. for every task inside the project print that task's place in the array and name
2. for every note inside that task print it out along with a its place in the array 
3. ask the user to input the task id they want to address or enter an escape character
4. if they enter an ID then we want to make sure its a valid id and then ask what they want to do from there:
5. either adding a note or deleting a note.
6. if they enter delete, ask which, and then remove it from the array. if they say add, get the addNote method from the task.
7. loop this till they enter the escape character at the first menu

## User
This class is the user, holds a name, an ID, a password, and a role
### User()
1. set the ID by calling the setUserID() method
2. set the password to the input text
3. set the role from the input text
Had I more time or vested interest I would have loved to set up a system that hashed and salted the password input. Right now it's just raw.

### setUserID()
1. call validateUserID with the ID this method was passed
2. once that's done set that result to this user's ID
3. then add the ID to the static Users ID list to make sure its unique

### validateUserID()
1. take the given ID input to the method
2. compare it against every id in the list and make sure that they do not match
3. if they do match ask the User to input a new ID. then check again and repeat till they input a valid non taken ID
4. return that ID

### getUserDetails()
1. print out the user ID
2. print out the password
3. print out the name
4. and print out the role.
5. end

### readArray()
Was included before for debugging purposes but has since been removed since it was not needed.

### editUserDetails()
1. ask the user what they want to set their new password too
2. ask them what they want to set their name to
3. and then let them know if they want their role to be changed they need to contact and administrator

## Task (INTERFACE)
This class is an interface to make sure that all the tasks have the same behaviors. The methods here do nothing.
### getTaskDetails()
### getTaskState()
### getMenu()
### setTaskID()
### validateTaskID()

## BaseTask
Basic version of a "Task" all tasks need to extend this to function with the rest of the program.
### BaseTask()
4/19/2023 this constructor had to be adjusted to allow for 'blank construction' of the most generic form of the class when we go to make new tasks. This method thus does nothing.
### BaseTask(String, String, String)
1. call setTaskID passing it the task id 0. Trust me this makes sense look at setTaskID.
2. set this instance's title to the passed title
3. set this start date to the given start date
4. set this end date to the given end date

### getTaskDetails()
1. print this task's title
2. print out the task's id

### getTaskState()
1. create loop
2. start by displaying the task title and the task id
3. show the amount of notes on this task
4. show all of the fields in this task
4. open up this tasks menu by calling getMenu()

### getMenu()
1. ask the user for nothing and return false
2. this getMenu() should never be used and exists to ensure that all tasks have something that is called should I have forgotten to add something here.

### getNotes()
1. for every note in the note array print it out
2. ask the user if they want to add another note
3. if they do call addNote()

### addNote()
1. ask them what they'd like to add to the list of notes
2. take whatever they put in and add the persent system's time stamp to the front of it
3. then add that composition to the list of notes for this task

### setTaskID()
1. when you call this take the input ID and send it to validate ID
2. take the result of validate ID and set it to this task's ID
3. then add this ID to the static array of task ID's
4. 4/24/2023 I just realized how un-needed this is, if we just add the task ID to the list in validate then we would just need to call validate and it would operate the same. oh well. 

### validateTaskID()
1. completly ignore whatever they sent as the ID
2. if the size of the static ID array is 0 then just return 0
3. if it isn't then do a loop through every item in the static ID array
4. find the highest number among them and then add + to that
5. return that value 


## CheckListTask
An extension of the BaseTask class that operates like a check list, with items that can be checked off once done.
### CheckListTask()
1. take in all the details we need for the BaseTask constructor and pass those to the super constructor
2. then we ask the user to add as many fields as they want
3. we assume all check list tasks start off being unchecked

### getMenu()
1. print out each check box that's in the array.
2. display the potential options they have
3. if the thing they input there is to exit then we exit this menu by returning false
4. if what they entered is nonsense we ignore it
5. if what they entered is a valid # for a check box in the array then we toggle the state of that check box

## ProgressBarTask
This class extends BaseTask and holds any number of precentage based 'bars' for reporting precent complete of a task.
### ProgressBarTask()
1. take all of the info we'd need to and call the super to set task titles, start and end dates
2. 1- will also make sure that the task ID is unique
3. create a loop to ask the user to add as many fields as they want for this task. assume all bars start at 0.

### getMenu()
1. loop through every single field in the task and print it out
2. to do this we figure out how much of 100 the % number in the array we have is and then divide that number from 20, rounding that result down to equate to a number of "full" segments we display
3. then take that number of full segments and get the "empty" segments to display by taking the total length and subtracting the number of full segements
4. then we display the options to present to the user
5. if they enter to exit we return a false and exit the outside loop
6. if they enter a number is a valid # for a field in the task we ask them what they'd like to set that field to.
7. when we ask the user this we make sure to tell them what they are supposed to enter. and ensure that the entry is filtered that only a good input is recieved.
8. any other input is ignored.




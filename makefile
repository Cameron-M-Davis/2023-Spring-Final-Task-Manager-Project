main:		TaskManager.class User.class Project.class BaseTask.class CheckListTask.class ProgressBarTask.class
		javac -g TaskManager.java


taskManager:	TaskManager.class
		java TaskManager

TaskManager.class:	TaskManager.java
			javac -g TaskManager.java


user:		User.class
		java User

User.class:	User.java
		javac -g User.java


project:	Project.class
		java Project

Project.class:	Project.java
		javac -g Project.java


baseTask:	BaseTask.class
		java BaseTask

BaseTask.class:	BaseTask.java
		javac -g BaseTask.java

checkList:	CheckListTask.class
		java CheckListTask

CheckListTask.class: CheckListTask.java
		javac -g CheckListTask.java

progressbar:	ProgressBarTask.class
		java ProgressBarTask

ProgressBarTask.class: ProgressBarTask.java
		javac -g ProgressBarTask.java

				


run:		main
		java TaskManager


clean:
		rm *.class
		rm *.dat

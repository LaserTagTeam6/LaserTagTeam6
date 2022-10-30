@echo off
javac -d "./bin" Main.java View.java Controller.java Model.java DBConnector.java
if %errorlevel% neq 0 (
	echo There was an error; exiting now.	
) else (
	echo Compiled correctly!  Running Game...
	java -cp "./lib/postgresql-42.5.0.jar;./bin;" Main
)
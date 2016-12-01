
If you are using a Linux machine (reccomended)

Compile by typing in the terminal:

	javac <file name>.java

Then run:

	java <Name of class without file extension> <textfile.example> <pattern>

Example:
	java stringMatching ./Wizard.oz Toto
	
	java KMP ./Wizard.oz Dorothy		

NOTE: IF YOU WANT A PATTERN THAT HAS SPACES, THEN ENCLOSE YOUR PATTERN WITH QUOTES

Example: 
	"This is an acceptable pattern"

===================================================================================

If you want to run a simulation with multiple trials, I included a python script

	testScript.py

Run it by typing:

	./testScript.py <algorithm> <input file> <pattern>

Example:
	./testScript.py KMP ./Wizard.oz Toto

Before it executes, it will ask you how many trials you want it to run. At the end, it will tell you
the average runtime per execution. I did this so I could run each algorithm hundreds of times and
automate it.



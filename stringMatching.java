import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * This program is best run through the terminal
 * A text file will be passed as a command line 
 * argument.
 * 
 * Compile by typing: javac stringMatching.java
 * Then run:
 * 
 * > java stringMatching <textfile.example>
 * 
 */

public class stringMatching {

	public static void main(String[] args) {
		File inFile = null;
		
		ArrayList string = new ArrayList();

		if (0 < args.length) {

			inFile = new File(args[0]);
		}

		else {
			System.err.println("Not enough arguments. Arg count:" + args.length);
			System.exit(0);
		}
		BufferedReader br = null;

		/* Read user input from commandline */

		System.out.print("Please enter a pattern: ");
		String name = System.console().readLine();
		System.out.println("Pattern is " + name);

		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader(inFile));

			while ((sCurrentLine = br.readLine()) != null) {
				// System.out.println(sCurrentLine);
				System.out.println( sCurrentLine.toCharArray() );
				System.out.println(">>");
			}

		}

		catch (IOException e) {
			e.printStackTrace();
		}

		finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}
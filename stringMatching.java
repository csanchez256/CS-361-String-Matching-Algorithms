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

			String currentLine;

			br = new BufferedReader(new FileReader(inFile));
			
			handleCharacters(br);

			while ((currentLine = br.readLine()) != null) {
				// System.out.println(sCurrentLine);
				
				System.out.println( currentLine.toCharArray() );
				
				
				//handleCharacters(br);
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
	} /* END MAIN */


	private static void handleCharacters(Reader reader) throws IOException {

		ArrayList<Character> stringInput = new ArrayList<Character>();
		
		int r;
		while ((r = reader.read()) != -1) {
			char ch = (char) r;
			// System.out.println("Do something with " + ch);
			stringInput.add(ch);
		}
		
		/* Cool slick way to iterate an Arraylist with lambdas */
		stringInput.forEach((a)->System.out.print(" " + a));
		
		
	}


}
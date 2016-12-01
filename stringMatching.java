import java.io.*;
import java.util.ArrayList;

/**
 * @author Christopher Santos Sanchez; CS-361 Algorithms & Data Structures; Fall 2016
 * 
 * This program is best run through the terminal
 * A text file will be passed as a command line 
 * argument.
 * 
 * Compile by typing: javac stringMatching.java
 * Then run:
 * 
 *  > java stringMatching <textfile.example> <pattern>
 */

public class stringMatching {

	int debug = 0;
	
	public static void main(String[] args) {
		
		File inFile = null;
		String pattern = null;

		if (0 < args.length) {

			inFile = new File(args[0]);
			pattern = args[1];
		}

		else {
			System.err.println("Not enough arguments. Arg count:" + args.length);
			System.exit(0);
		}
		BufferedReader br = null;

		try {

			br = new BufferedReader(new FileReader(inFile));

			handleCharacters(br, pattern);
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

	/**
	 * 
	 * @param reader
	 * @param pattern
	 * @throws IOException
	 */
	private static void handleCharacters(Reader reader, String pattern) throws IOException {

		ArrayList<Character> stringInput = new ArrayList<Character>();
		ArrayList<Character> patternInput = new ArrayList<Character>();

		int r, n, m;

		/* Store "String" into an ArrayList */
		while ((r = reader.read()) != -1) {
			char ch = (char) r;
			stringInput.add(ch);
		}

		/* Store "Pattern" into an ArrayList */
		for (int j = 0; j < pattern.length(); j++) {
			patternInput.add(pattern.charAt(j));
		}

		/*
		 * Cool slick way to iterate an Arraylist with lambdas... LAMBDAS ARE
		 * COOL NOW
		 */
		// stringInput.forEach((a)->System.out.print(" " + a));

		n = stringInput.size();
		m = patternInput.size();

		
		long startTime = System.currentTimeMillis();
		
		searchArrays(n,m,stringInput,patternInput);
		
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		
		System.out.println("Total Runtime: " + ( totalTime / 1000.0 ) + " Seconds");
	}
	
	
	/**
	 * 
	 * @param n - size of the String
	 * @param m - size of the Pattern
	 * @param stringInput
	 * @param patternInput
	 */
	private static void searchArrays(int n, int m, ArrayList<Character> stringInput,
			ArrayList<Character> patternInput) {

		int occurances = 0;
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {

				if (stringInput.get(i + j - 1) != patternInput.get(j)) break;

				else if (j == (m - 1) ) {
					occurances++;
				}
			}
		}
		if( occurances > 0 ) System.out.println("Number of occurances is " + occurances);
		else System.out.println("Pattern was not found");
	}
	
}/* Class block */
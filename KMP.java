import java.io.*;
import java.util.ArrayList;

/**
 * @author Christopher Santos Sanchez; CS-361 Algorithms & Data Structures; Fall 2016
 * 
 * This program is best run through the terminal
 * A text file will be passed as a command line 
 * argument.
 * 
 * Compile by typing: javac KMP.java
 * Then run:
 * 
 *  > java KMP <textfile.example> <pattern>
 *  NOTE: IF YOU WANT A PATTERN THAT HAS SPACES, THEN ENCLOSE YOUR PATTERN WITH QUOTES
 *  	EX: "This is an acceptable pattern"
 */

public class KMP {

	private static final String ArrayList = null;
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

		int r, n, m;
		
		ArrayList<Character> stringInput = new ArrayList<Character>();
		ArrayList<Character> patternInput = new ArrayList<Character>();
		

		/* Store "String" into an ArrayList */
		while ((r = reader.read()) != -1) {
			char ch = (char) r;
			stringInput.add(ch);
		}

		/* Store "Pattern" into an ArrayList */
		for (int j = 0; j < pattern.length(); j++) {
			patternInput.add(pattern.charAt(j));
		}

		n = stringInput.size();
		m = patternInput.size();
		
		//Allocate an empty ArrayList of size - m
		ArrayList<Integer> failTable = new ArrayList<Integer>(m);
		
		long startTime = System.nanoTime();
		
		//Now calculate the failure table
        failureTable(patternInput, failTable);
        kmpSearch(stringInput, patternInput, failTable);
        
        
        //failTable.forEach((a)->System.out.print(" " + a));
        
		long endTime   = System.nanoTime();
		long totalTime = endTime - startTime;
		
		System.out.println("Total Runtime: " + totalTime + " nano seconds");
		
	}/* End handle characters */


	private static void kmpSearch(ArrayList<Character> stringInput, 
			ArrayList<Character> patternInput, ArrayList<Integer> failTable) {
		failTable.forEach((a)->System.out.println(" " + a));
	}



	/**
	 * 
	 * @param stringInput - an array of characters, (the word we are searching for)
	 * @param failTable - We are going to fill this table with prefix/suffix frequencies
	 * Returns void ( it only fills up a table )
	 */
	private static void failureTable(ArrayList<Character> patternInput, 
			ArrayList<Integer> failTable) {
			int pos = 2; //current index in T
			int cnd = 0; // zero based index in stringInput
			
			//initialize first index of table
			failTable.add(-1);
			failTable.add(0);

			while( pos < patternInput.size() ){
				if ( patternInput.get(pos - 1 ) == patternInput.get(cnd) ){
					failTable.add(pos, cnd + 1 );
					cnd++;
					pos++;
				}
				else if ( cnd > 0 ){
					cnd = failTable.get( cnd );
					failTable.add(pos, 0);
				}
				else {
					failTable.add(pos, 0);
					pos++;
				}
			}
	}/* End failureTable function */
	
	
	
	
}/* Class block */
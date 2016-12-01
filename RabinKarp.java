import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Christopher Santos Sanchez; CS-361 Algorithms & Data Structures; Fall 2016
 * 
 * This program is best run through the terminal
 * A text file will be passed as a command line 
 * argument.
 * 
 * Compile by typing: javac rabinKarp.java
 * Then run:
 * 
 *  > java stringMatching <textfile.example> <pattern>
 */

public class RabinKarp {

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
        
		// I think it includes null characatr in size()
		n = stringInput.size() - 1;
		m = patternInput.size();

		
		long startTime = System.currentTimeMillis();
		
		rabinKarp(n,m,stringInput,patternInput);
		
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		
		System.out.println("Total Runtime: " + totalTime / 1000.0 + " Seconds");
	}
	
	
	/**
	 * 
	 * @param n - size of the String
	 * @param m - size of the Pattern
	 * @param stringInput
	 * @param patternInput
	 */
	
	private static void rabinKarp(int n, int m, ArrayList<Character> stringInput,
			ArrayList<Character> patternInput) {
		
		ArrayList<Integer> oldChar = new ArrayList<>();
		
		int occurances = 0;
		int prime = 101;
		int radix = 256;

		//Here is the initial hashing part
		long patternHash = hash(patternInput.subList(0, m), prime );
		long textHash = hash(stringInput.subList(0, m), prime );
		
		for (int i = 0; i < (n - m + 1); i++){
			
			if (patternHash == textHash){
				
				/* TODO this part doesn't seem to check correctly with large values */
				if (stringInput.subList( i  , i + m ).equals(patternInput.subList(0, m) )){
                    occurances++;
                    //System.out.println(" index is " + i);
				}
			}
			
		//	long x = (long) (Math.pow(prime, patternInput.size() - 1 ));

			textHash = (hash(stringInput.subList(i + 1, i + m + 1 ), prime ) );
			
			/* base * (old hash - old character ) + new character */
		//	System.out.println("old hash " + textHash);
		//	textHash = (101 * (textHash - stringInput.get(i)*x) + stringInput.get(i+m));
			
			//in case we get negative values
			//if (textHash < 0 ) textHash = (textHash + prime);
		}
		
		if( occurances > 0 ) System.out.println("Number of occurances is " + occurances);
		else System.out.println("Pattern was not found");
	}
	
	private static int hash(List<Character> list, int prime){
		int x = list.size();
		int exp = x - 1;
		int h = 0;
		
		for (int i = 0; i < x; i++){
			h += (list.get(i)) * (Math.pow(prime, exp));
			exp--;
		}
		return h;
	}
	
}/* Class block */
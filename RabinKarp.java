import java.io.*;
import java.util.ArrayList;

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

		n = stringInput.size();
		m = patternInput.size();

		
		long startTime = System.nanoTime();
		
		rabinKarp(n,m,stringInput,patternInput);
		
		long endTime   = System.nanoTime();
		long totalTime = endTime - startTime;
		
		System.out.println("Total Runtime: " + totalTime + " nano seconds");
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

		int occurances = 0;
		int d = 256;       // number of characters in d-ary alphabet
		int q = 127;
		int p = 0;
		int t = 0;
		
		n = n -1; //because size includes null character I think
		
		int h = (int) Math.pow(d, m-1) % q;
		
		// preprocessing, or hashing
		// This will also check if there's a match before any shifting
		for(int i = 0; i < m ; i++){
			p = ( d*p + patternInput.get(i) ) % q;
			t = ( d*t + stringInput.get(i) ) % q;
			System.out.println("t is " + t);

		}
		
		System.out.println("p is " + p);
//		System.out.println("t is " + t);
		
		if (p == t) occurances++;
		
//		System.out.println("n is " + n);
//		System.out.println("m is " + m);
		
		// Once a hash value matches, we have to check and make sure it's not a collision
		for(int s = 0; s < ( n - m ); s++ ){
			if ( p == t ){
				System.out.println(" called");
				for (int k = 0; k < m; k++){
					if( patternInput.get(k) != stringInput.get(k + s) ){
						break;
					}
					else if( s == ( m - 1 )) occurances++;
				}
			}
			if ( s < (n - m) ){
				//remove the high oder bit and add the low order bit
				t = ( d*( t - stringInput.get(s+1)*h) + stringInput.get(s + m + 1) ) % q;
				System.out.println(" t" + t);
			}
		}
		
		
		if( occurances > 0 ) System.out.println("Number of occurances is " + occurances);
		else System.out.println("Pattern was not found");
	}
	
	
}/* Class block */
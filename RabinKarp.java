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
//	function RabinKarp(string s[1..n], string pattern[1..m])
//	2   hpattern := hash(pattern[1..m]);  hs := hash(s[1..m])
//	3   for i from 1 to n-m+1
//	4     if hs = hpattern
//	5       if s[i..i+m-1] = pattern[1..m]
//	6         return i
//	7 
//	    hs := hash(s[i+1..i+m])
//	8   return not found
	
	
	private static void rabinKarp(int n, int m, ArrayList<Character> stringInput,
			ArrayList<Character> patternInput) {
		int occurances = 0;
		int previousChar = 0;
		int lastCharInSubstring = 0;
		int textHash = 0;
//		int h = 0;

		int patternHash = hash(patternInput.subList(0, m), previousChar, textHash, lastCharInSubstring);
		textHash = hash(stringInput.subList(0, m), previousChar, textHash, lastCharInSubstring );

		
		for (int i = 0; i < (n - m + 1); i++){
			
			if (patternHash == textHash){
				if (stringInput.subList( i  , i + m ).equals(patternInput.subList(0, m) )){
                    occurances++;					
				}
			}
			
		    previousChar = stringInput.get(i);
		    lastCharInSubstring = stringInput.get(i + m);
			textHash = hash(stringInput.subList(i + 1, i + m + 1 ), previousChar, textHash,lastCharInSubstring );
			
		}
		
		
		textHash = hashShift(textHash);
		
		if( occurances > 0 ) System.out.println("Number of occurances is " + occurances);
		else System.out.println("Pattern was not found");
	}
	
	private static int hash(List<Character> list, int previousChar, int oldHash, int lastCharInSubstring){
		int x = list.size();
		int exp = x - 1;
		int prime = 101;
		int h = 0;
		
//		 int firstChar = (int) (( list.get(0) ) *(Math.pow(prime, exp) ));
		//System.out.println("Previous char" + previousChar);
		
		if (previousChar == 0) {
			for (int i = 0; i < x; i++) {
				h += (list.get(i)) * (Math.pow(prime, exp));
				exp--;
			}
		}
		//Compute hash of next substring
		else{
			h = ( prime * (oldHash - (previousChar) ) ) + (lastCharInSubstring);
		}
		

		
		return h;
	}
	
	private static int hashShift(int oldHash){
		
////      base   old hash    old 'a'         new 'a'
//hash("bra") = [101 × (999,509 - (97 × 1012))] + (97 × 1010) = 1,011,309
		
		int prime = 101;
		return 0;
	}
	
	
}/* Class block */
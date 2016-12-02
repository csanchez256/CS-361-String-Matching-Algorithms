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
 * Compile by typing: javac RabinKarp.java
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

		// I think it includes null character in size() So I have to subtract 1
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
		
		int occurrences = 0;
		int prime = 101;
		int radix = 256;

		//Here is the initial hashing part
		long patternHash = hash(patternInput.subList(0, m), prime );
		long textHash = hash(stringInput.subList(0, m), prime );
		
		for (int i = 0; i < (n - m + 1); i++){
			
			if (patternHash == textHash){
				if (stringInput.subList( i  , i + m ).equals(patternInput.subList(0, m) )){
                    occurrences++;
				}
			}
			textHash = (hash(stringInput.subList(i + 1, i + m + 1 ), prime ) );
		}
		
		if( occurrences > 0 ) System.out.println("Number of occurances is " + occurrences);
		else System.out.println("Pattern was not found");
	}
	
	/**
	 * Hashes substrings and returns this hash value
	 * @param list
	 * @param prime
	 * @return
	 */
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
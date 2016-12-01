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
	
	private static void rabinKarp(int n, int m, ArrayList<Character> string,
			ArrayList<Character> pattern) {
		
			int occurances = 0;
			int h = 1;
			int prime = 101;
			int d = 256;
			int p = 0;
			int t = 0;
			
			
			
			// The value of h would be "pow(d, M-1)%q"
		    for (int i = 0; i < m -1; i++){
		    	h = (h*d )% prime;
		    }

		    p("h is " + h);
		    
		    // preprocessing
		    for (int i = 0; i < m ; i++){
		    	p = ( d*p + pattern.get(i) ) % prime ;
		    	t = ( d*t + string.get(i) ) % prime ; 
		    }
		    
		    p("p is " + p);
		    p("t is " + t);
		    
		    for ( int s = 0; s <= (n - m); s++){
		    	if ( p == t){
		    		p("this is being reached");
		    		for ( int i = 0; i <= m; i++){
		    			if ( pattern.get(i) != string.get(i + m) ){
		    				break;
		    			}
		    			occurances++;
		    		}
		    	}
		    	if( s < n - m){
		    		t = ( d*(t - (( string.get(s+1))*h % prime) ) + string.get(s+m+1) )  % prime;
		    	}
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
	
	/**
	 * Cool little trick to simplify print statements
	 * @param line
	 */
	static void p(Object line){
		System.out.println( line );
	}
	
}/* Class block */
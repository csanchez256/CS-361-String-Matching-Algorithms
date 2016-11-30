#!/usr/bin/env python
# Christopher Sanchez; CS-361; Data Stuctures and Algorithms
# TO RUN > ./testScript.py <program> <file name> <word to search>

import subprocess
import sys	   #for command line args
import os

def runSimulation(cmd, trials):
	
	runtimes = []

	for n in range(trials):

		# Enter command into terminal
		subprocess.call( cmd, shell = True )

		output = subprocess.check_output(cmd, shell = True)
	

		#parse the output from command prompt
		for row in output.split('\n'):
			if ': ' in row:
				key, value = row.split(': ')
         			x = value.strip('Seconds')
                        	num = float(x)
				runtimes.append(num)
		              			

	if (trials > 1):
		# Loops through list, and sums each element
		average = ( sum( float (n) for n in runtimes ) ) / trials 
		print "=================================="
		print "Average runtime is %.4f " % average , "Seconds"
		print "=================================="

try:
	programName = sys.argv[1]
	inputFile = sys.argv[2]
	pattern = sys.argv[3]

	cmd = 'java %s %s %s' % (programName, inputFile , pattern)

	trials = input('Enter number of trials: ')
	
	runSimulation(cmd, trials)

except IndexError:
	print "not enough commands"


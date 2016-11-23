#!/usr/bin/env python


import sys	  #for command line args
import os
import subprocess


def runSimulation(cmd):
	
	runtimes = []

	trials = 10

	for n in range(trials):
	
		os.system(cmd)
	
		output = subprocess.check_output(cmd, shell = True)
		
		#parse the output from command prompt
		for row in output.split('\n'):
			if ': ' in row:
				key, value = row.split(': ')
         			x = value.strip('nano seconds')
                        	num = int(x)
				runtimes.append(num)		              			

	# Loops through list, and sums each element 
	print "=================================="
	print "Average runtime is " , ( sum( int(n) for n in runtimes) ) / trials , " ns"
	print "=================================="

try:
	inputFile = sys.argv[1]
	cmd = 'java stringMatching %s' % inputFile
	runSimulation(cmd)

except IndexError:
	print "not enough commands"


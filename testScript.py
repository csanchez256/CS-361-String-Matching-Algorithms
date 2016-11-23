#!/usr/bin/env python

import os
import subprocess

runtimes = []

trials = 10

for n in range(trials):
	
	os.system("java stringMatching STRING.input")
	
	output = subprocess.check_output("java stringMatching STRING.input", shell = True)
	#parse the output from command prompt
	for row in output.split('\n'):
		if ': ' in row:
			key, value = row.split(': ')
			#result[key.strip('Total Runtime')] = value.strip('nano seconds')
         		x = value.strip('nano seconds')
                        num = int(x)
			runtimes.append(num)		              			


# Loops through list, and sums each element 
print "=================================="
print "Average runtime is " , ( sum( int(n) for n in runtimes) ) / trials , " ns"
print "=================================="


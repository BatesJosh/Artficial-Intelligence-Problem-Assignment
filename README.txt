This program solves the 8 puzzle and Farmer, Wolf, Goat, Cabbage (FWGC) problem using search algorithms.
The different search algorithms are depth-first search, breadth-first search, and a* search.
It should be noted FWGC does not run a* search. The a* search uses the manhattan or the numbers outofplace heuristic.
This program runs in the command line after being compiled.
In order to run the program, the correct commands depends on which search. 
In order to run debug, just add debug at the end of the command.


For FWGC:
	
	format: java Driver FWGC <search-type> <debug>
	<search-type> 
	search-type: 
				
		dfs 
				bfs
	
		<debug>: 
		debug //or leave it out 
	
		
		Examples:
			java Driver FWGC dfs
	
			java Driver FWGC bfs
	
			java Driver FWGC dfs debug
	
			java Driver FWGC bfs debug

F
For 8puzzle: 
	The puzzle needs to be 123456780 in any arrangement.
 
	The 0 is the space, and the first index in the arrangements represents the top left and goes to the right, the 4th index represents the first position in the middle row, and the 7th index represents the first position in last row.
	For example 12345680 would represent: 
		123
		456
		780				
	format: java Driver 8puzzle <puzzle> <search-type> <debug>
		
	<puzzle>: 123456780 in any arrangement.
		
	<search-type>: 

			a* manhattan
	
			a* numbersoutofplace
				
			bfs
				
			dfs
	
	Example:				
		
	java -Xmx256m Driver 8puzzle a* manhattan 087654321 debug
		
	java -Xmx256m Driver 8puzzle a* numbersoutofplace 087654321		
		
	java -Xmx256m Driver 8puzzle dfs 087654321 debug 		
		
	java -Xmx256m Driver 8puzzle bfs 012345678 debug

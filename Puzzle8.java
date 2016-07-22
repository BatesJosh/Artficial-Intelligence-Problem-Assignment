import java.util.*;

/**
 * Implementation of the 8 puzzle toy problem.
 *
 * This code was created by Joshua Bates and is his copyright.
 */
public class Puzzle8 implements Problem
{
    //8 puzzle board
    private int[][] box=new int[3][3];
    //cost
    private int cost;
    //row of space
    private int row;
    //column of space
    private int column;
    private int hValue;
    /**
     * Constructor that takes in a 2 dimensional array to create the problem.
     *
     * @param abox
     */
    public Puzzle8(int[][] abox)
    {
	cost=0;
	hValue=0;
	for(int i=0; i< 3; i++)
	    {
		for(int j=0; j<3; j++)
		    {
			if(0==abox[i][j])
			    {
				row=i;
				column=j;
			    }
			box[i][j]=abox[i][j];
		    }
	    }
    }
    
    //private constructor that takes in an Puzzle8, and the new space for row  and column, each time called, increases cost by 1.
    private Puzzle8(int[][] curr, int sRow, int sColumn, int pRow, int pColumn, int pCost)
    {
	cost=pCost + 1;
	row=sRow;
	column=sColumn;
	for(int i=0; i< 3; i++)
	    {
		for(int j=0; j<3; j++)
		    {
			//if at new space, make 0
			if(i==sRow && j==sColumn)
			    {
			        box[i][j]=0;
			    }
			else if(i==pRow && j==pColumn) //if at old space, make it number where new space is at
			    {
				box[i][j]=curr[sRow][sColumn];
			    }
			else //copy everything else
			    {
				box[i][j]=curr[i][j];
		  
			    }
		    }
	    }
    }

    /**
     * Check to see if at goal state.
     * 123
     * 456
     * 780
     */
    public boolean isGoal()
    {
	//used to check 
	int curr=1;
	boolean goal=true;
	int i=0;
	while(i<3 && goal)
	 {
	     int j=0;
	     while(j<3 && goal)
		 {
			if(!(i==2 && j==2))
			    {
				if(box[i][j]!= curr)
				    {
					goal= false;
				    }
			    }
			j++;
			curr++;
		    }
	     i++;
	    }
	return goal;
    }

    /**
     * Checks valid successors, and returns all possible successors.
     * @return 
     */
    @Override
    public ArrayList<Puzzle8> successors()
    {
	ArrayList<Puzzle8> arr=new ArrayList<>();
	if(row!=0) //go up
	    {
		Puzzle8 state=new Puzzle8(box, row-1, column, row, column, cost);
		arr.add(state);
	    }
	if(row!=2) //go down
	    {
		Puzzle8 state=new Puzzle8(box, row+1, column, row, column, cost);
		arr.add(state);
	    }
	if(column!=0) //go left
	    {
		Puzzle8 state=new Puzzle8(box, row, column-1, row, column, cost);
		arr.add(state);
	    }
	if(column!=2) //go right
	    {
		Puzzle8 state=new Puzzle8(box, row, column+1, row, column, cost);
		arr.add(state);
	    }
	return arr;
    }

    /**
     * Method for printing out the state
     * @return
     */
    @Override
    public String toString()
    {
	String str="------------------\n";
	for(int i=0; i<3; i++)
	    {
		str+="|";
		for(int j=0; j<3; j++)
		    {
			str+=box[i][j];
			if(j<2)
			    {
				str+="\t";
			    }
		    }
		str+="|\n";
	    }
	str+="------------------\n";
	return str;
    }

    /**
     * Heuristic value not yet implemented.
     * @return 
     */
    public int heuristicValue()
    {
	return cost+hValue;
    }

    /**
     * Setter method for cost.
     * @param value
     */
    @Override
    public void setCost(int value)
    {
	cost=value;
    }

    /**
     * Getter method for cost.
     */
    @Override
    public int getCost()
    {
	return cost;
    }


    /**
     * Compares two Puzzle8 classes. If they aren't puzzle8, it will return false.
     *If they aren't the same state, will also return false
     *
     * @param state
     */
   @Override
    public boolean compare(Problem state)
    {
	boolean isSame;
	if(state instanceof Puzzle8)
	    {
		Puzzle8 comp=(Puzzle8) state;
		int i=0;
		isSame=true;
		while(i<3 && isSame)
		    {
			int j=0;
			while(j<3 && isSame)
			    {
				if(comp.box[i][j]!=this.box[i][j])
				    {
					isSame=false;
				    }
				j++;
			    }
			i++;
		    }
	    }
	else
	    {
		isSame=false;
	    }
	return isSame;
    }

    /**
     * Sets the Heuristic used in the puzzle. 
     * 
     * @param str can be numbersoutofplace or manhattan
     */
    @Override
    public void setHeuristic(String str)
    {
	if(str.equalsIgnoreCase("numbersoutofplace"))
	    {
		setNumbersOutOfPlace();
	    }
	else if(str.equalsIgnoreCase( "manhattan"))
	    {
		setManhattan();
	    }
    }
    //private method to set numbers out of place heuristic
    private void setNumbersOutOfPlace()
    {
	hValue=0;
	int number=1;
	for( int i=0; i< 3; i++)
	    {
		for( int j=0; j<3; j++)
		    {
			if(box[i][j]!=number && box[i][j]!=0)
			    {
				hValue++;
			    }
			number++;
		    }
	    }
    }

    //private method to set manhattan heuristic
    private void setManhattan()
    {
	hValue=0;
	int number=1;
	for( int i=0; i< 3; i++)
	    {
		for( int j=0; j<3; j++)
		    {
			if(box[i][j]!=number && box[i][j]!=0)
			    {
				hValue+=manhattanPlace(box[i][j], i, j);
			    }
			number++;
		    }
	    }
    }

    //private method for manhattan heuristic for each place
    private int manhattanPlace(int value, int mRow, int mColumn)
    {
	int rCost=0;
	int cCost=0;
	if(mRow==0 && value>3)
	    {
		if(value>6)
		    {
			rCost=2;
		    }
		else
		    {
			rCost=1;
		    }
	    }
	else if(mRow==1 && ((value<4 && value!=0) || value >6))
	    {
		rCost=1;
	    }
	else if(mRow==2 && value<7 && value!=0)
	    {
		if(value<4)
		    {
			rCost=2;
		    }
		else //value >3 && value < 7
		    {
			rCost=1;
		    }
	    }
	if(mColumn==0 && !(value==1 || value==4 || value==7))
	    {
		if(value==2 || value==5 || value==8)
		    {
			cCost=1;
		    }
		else
		    {
			cCost=2;
		    }
	    }
	else if(mColumn==1 && !(value==2 || value==5 || value==8))
	    {
		cCost=1;
	    }
	else if(mColumn==2 && !(value==3 || value==6) )
	    {
		if(value==1 || value ==4 || value==7)
		    {
			cCost=2;
		    }
		else //value >3 && value < 7
		    {
			cCost=1;
		    }
	    }
	return rCost+cCost;
    }
}

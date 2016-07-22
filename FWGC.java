import java.util.*;
/**
 * Implementation of the Farmer Wolf Goat Cabbage problem.
 *
 * This code was created by Joshua Bates and is his copyright.
 */
public class FWGC implements Problem
{
    private boolean[] river=new boolean[4];
    private int cost;
    /**
     * Constructor to create a new FWGC
     * Cost is set to 0.
     */
    public FWGC()
    {
	cost=0;
    }

    //private constructor to create a new state
    private FWGC(boolean[] pstate, int oCost)
    {
	for(int i=0; i< 4; i++)
	    {
		this.river[i]=pstate[i];
	    }
	this.cost=oCost+1;
    }

    /**
     * Checks to see if state is goal state.
     * Goal state is everything is across the river.
     * @return
     */
    @Override
    public boolean isGoal()
    {
	for(int i=0; i<4; i++)
	    {
		if(!river[i])
		    return false;
	    }
	return true;
    }

    /**
     * Getter method for cost.
     * @return
     */
    @Override
    public int getCost()
    {
	return cost;
    }

    //Private method to switch farmer and something else across the river.
    private void changeRiver(int i)
    {
	if(river[0])
	    {
		river[0]=false;
		river[i]=false;
	    }
	else
	    {
		river[0]=true;
		river[i]=true;
	    }
    }
    //Private method to add to array, and change the position
    private void newState(ArrayList<FWGC> arr, int position)
    {
	FWGC state=new FWGC(river, cost);
	state.changeRiver(position);
	arr.add(state);
    }


    /**
     * Checks valid successors, and returns all possible successors.
     * @return
     */
    @Override
    public ArrayList<FWGC> successors()
    {
	ArrayList<FWGC> succ=new ArrayList<FWGC>();
	if(!river[0])
	    {
		if(!river[1] && !river[2] && !river[3])//everything on bank
		    {
		        newState(succ, 2);
		    }
		else if(!river[1] && river[2] && !river[3]) //goat across river, others on bank
		    {
		        newState(succ, 3);
			newState(succ, 1);
		    }
		else if(!river[1] && !river[2] && river[3]) //goat and wolf on bank, cabbage across river
		    {		
			newState(succ, 1);
			newState(succ, 2);
		    }
		else if(river[1] && !river[2] && !river[3]) //goat and cabbage on bank, wolf across bank.
		    {
			newState(succ, 3);
			newState(succ, 2);
		    }
		else if(river[1] && !river[2] && river[3]) //goat on bank, everything else across
		    {
			newState(succ, 2);
		    }
	    }
	else
	    {
		if((!river[1] && river[2] && !river[3])||(river[1] && !river[2] && river[3]))
		   {
		       newState(succ, 0);
		   }
		else if(!river[1] && river[2] && river[3])
		    {
			newState(succ, 2);
			newState(succ, 3);
		    }
		else if(river[1] && !river[2] && !river[3])
		    {
			newState(succ, 2);
			newState(succ, 3);
		    }
	    }
	return succ;
    }

    /**
     * Method for printing out the state. 
     *
     * @return
     */
    @Override
    public String toString()
    {
	String str="===============\n";
	for(int i=0; i< 4; i++)
	    {
		if(river[i]==false)
		    {
			str+=getPerson(i)+" |RIVER| \n";
		    }
		else
		    {
			str+="  |RIVER| "+getPerson(i)+" \n";
		    }
	    }
	return str;
    }

    // Private method used in toString to get F, W, G, or C
    private String getPerson(int i)
    {
	String str="";
	if(0==i)
	    {
		str= "F";
	    }
	else if(1==i)
	    {
		str= "W";
	    }
	else if(2==i)
	    {
		str= "G";
	    }
	else if(3==i)
	    {
		str= "C";
	    }
	return str;
    }

    /**
     * Gets heuristic value, which this problem will not have, so the value is 
     *  -1
     * @return
     */
    @Override
    public int heuristicValue()
    {
	return -1;
    }

    /**
     * Setter for cost
     * @param value
     */
    @Override
    public void setCost(int value)
    {
	cost=value;
    }

    /**
     * Compares two FWGC classes. If they aren't FWGC, it will return false.
     *If they aren't the same state, will also return false
     *
     * @param state
     */
    @Override
    public boolean compare( Problem state)
    {
	boolean isSame;
	if(state instanceof FWGC)
	    {
		FWGC comp=(FWGC) state;
		int i=0;
		isSame=true;//state instanceof FWGC;
		while(i<4 && isSame)
		    {
			if(comp.river[i]!=this.river[i])
			    {
				isSame=false;
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
}
